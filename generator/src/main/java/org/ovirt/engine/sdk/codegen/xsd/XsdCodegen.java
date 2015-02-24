//
// Copyright (c) 2012 Red Hat, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//           http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.ovirt.engine.sdk.codegen.xsd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.ovirt.engine.sdk.codegen.templates.CopyrightTemplate;
import org.ovirt.engine.sdk.codegen.utils.StringUtils;

/**
 * Provides XSD schema related services
 */
public class XsdCodegen {
    private static final String ENTITIES_PACKAGE = "org.ovirt.engine.sdk.entities";

    private static final List<String> copyrightLines = Arrays.asList(new CopyrightTemplate().getTemplate().split("\n"));

    /**
     * The location of the XSD file.
     */
    private String xsdPath;

    /**
     * The location of the JAXB bindings file.
     */
    private String xjbPath;

    public XsdCodegen(String xsdPath, String xjbPath) {
        this.xsdPath = xsdPath;
        this.xjbPath = xjbPath;
    }

    /**
     * Generates entity classes.
     *
     * @param distPath directory to generates the code at
     */
    public void generate(String distPath) throws IOException {
        // Remove all the previously generate classes, so that classes corresponding to types that have been
        // removed from the XML schema will be later removed from the source code repository:
        File packageDir = new File(distPath, ENTITIES_PACKAGE.replace('.', File.separatorChar));
        if (packageDir.exists()) {
            FileUtils.cleanDirectory(packageDir);
        }

        // Run the XJC compiler to generate all the classes:
        System.setProperty("javax.xml.accessExternalSchema", "all");
        try {
            com.sun.tools.xjc.Driver.run(
                new String[] {
                    "-extension",
                    "-no-header",
                    "-enableIntrospection",
                    "-d", distPath,
                    "-p", ENTITIES_PACKAGE,
                    "-b", xjbPath,
                    xsdPath
                },
                System.out,
                System.err
            );
        }
        catch (Exception exception) {
            throw new IOException(exception);
        }
    }

    /**
     * Modifies public getters to return {@code Object} so inheriting classes could override them with different
     * signature.
     *
     * @param distPath the top level directory of the source code
     * @param accessors a list of possible getter types
     */
    public static void modifyGetters(String distPath, Map<String, List<String>> accessors) throws IOException {
        String templateOriginal = "    public $accessor$ get$accessor$() {";
        String templateReplace = "    public Object get$accessor$() {";
        String placeHolder = "$accessor$";
        boolean isInAccessor = false;

        // change shadowed getters
        File entitiesDir = new File(distPath, ENTITIES_PACKAGE.replace('.', File.separatorChar));
        for (File file : entitiesDir.listFiles()) {
            List<String> finalLines = new ArrayList<>(copyrightLines);
            List<String> tempLines = new ArrayList<>();

            List<String> accessorsToCheck = accessors.get(file.getName().replace(".java", ""));
            if (accessorsToCheck == null) {
                injectCopyrightHeader(file);
                continue;
            }

            for (String line : FileUtils.readLines(file)) {
                if (line.isEmpty()) {
                    isInAccessor = false;
                    finalLines.addAll(tempLines);
                    finalLines.add(line);
                    tempLines.clear();
                } else if (line.equals("}")) {
                    isInAccessor = false;
                    finalLines.add("}");
                } else {
                    if (!isInAccessor) {
                        for (String accessor : accessorsToCheck) {
                            if (line.toLowerCase().equals(templateOriginal.replace(placeHolder, accessor)
                                    .toLowerCase())) {
                                isInAccessor = true;
                                tempLines.add(templateReplace.replace(placeHolder, accessor));
                                break;
                            }
                        }
                        if (!isInAccessor) {
                            tempLines.add(line);
                        } else {
                            isInAccessor = false;
                        }
                    }
                }
            }

            // save new content
            finalLines = StringUtils.removeTrailingWhitespace(finalLines);
            FileUtils.writeLines(file, finalLines);
        }
    }

    /**
     * Inject CopyrightHeader in to file
     *
     * @param file
     *            file to inject to
     */
    private static void injectCopyrightHeader(File file) throws IOException {
        List<String> lines = new ArrayList<>(copyrightLines);
        lines.addAll(FileUtils.readLines(file));
        lines = StringUtils.removeTrailingWhitespace(lines);
        FileUtils.writeLines(file, lines);
    }
}