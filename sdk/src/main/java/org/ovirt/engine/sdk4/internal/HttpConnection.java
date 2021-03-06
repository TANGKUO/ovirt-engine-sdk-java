/*
Copyright (c) 2016 Red Hat, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.ovirt.engine.sdk4.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.ovirt.api.metamodel.runtime.util.ListWithHref;
import org.ovirt.engine.sdk4.Connection;
import org.ovirt.engine.sdk4.Error;
import org.ovirt.engine.sdk4.HttpClient;
import org.ovirt.engine.sdk4.Service;
import org.ovirt.engine.sdk4.internal.services.SystemServiceImpl;
import org.ovirt.engine.sdk4.services.SystemService;
import org.ovirt.engine.sdk4.types.Identified;

/**
 * This class is the HTTP implementation of the connection contract. Refrain from using it directly, as backwards
 * compatibility isn't guaranteed, use the {@link org.ovirt.engine.sdk4.ConnectionBuilder} class instead.
 */
public class HttpConnection implements Connection {

    private HttpClient client;
    private String url;
    private String user;
    private String password;
    private boolean kerberos;
    private String ssoToken = null;
    private String ssoTokenName = null;
    private String ssoUrl = null;
    private String ssoRevokeUrl = null;


    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsoUrl() {
        return ssoUrl;
    }

    public void setSsoUrl(String ssoUrl) {
        this.ssoUrl = ssoUrl;
    }

    public String getSsoTokenName() {
        return ssoTokenName;
    }

    public void setSsoTokenName(String ssoTokenName) {
        this.ssoTokenName = ssoTokenName;
    }

    public void setKerberos(boolean kerberos) {
        this.kerberos = kerberos;
    }

    public String getSsoRevokeUrl() {
        return ssoRevokeUrl;
    }

    public void setSsoRevokeUrl(String ssoRevokeUrl) {
        this.ssoRevokeUrl = ssoRevokeUrl;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    @Override
    public SystemService systemService() {
        return new SystemServiceImpl(this, "");
    }

    private String getHref(Object object) {
        if (object instanceof Identified) {
            return ((Identified) object).href();
        }
        else if (object instanceof ListWithHref) {
            return ((ListWithHref) object).href();
        }
        return null;
    }

    @Override
    public boolean isLink(Object object) {
        return getHref(object) != null;
    }

    @Override
    public <TYPE> TYPE followLink(TYPE object) {
        if (!isLink(object)) {
            throw new Error("Can't follow link because object don't have any");
        }

        String href = getHref(object);
        if (href == null) {
            throw new Error("Can't follow link because the 'href' attribute does't have a value");
        }
        try {
            URL url = new URL(getUrl());
            String prefix = url.getPath();
            if (!prefix.endsWith("/")) {
                prefix += "/";
            }
            if (!href.startsWith(prefix)) {
                throw new Error("The URL '" + href + "' isn't compatible with the base URL of the connection");
            }

            // Get service based on path
            String path = href.substring(prefix.length());
            Service service = systemService().service(path);

            // Obtain method which provides result object and invoke it:
            Method get;
            if (object instanceof ListWithHref) {
                get = service.getClass().getMethod("list");
            } else {
                get = service.getClass().getMethod("get");
            }
            Object getRequest = get.invoke(service);
            Method send = getRequest.getClass().getMethod("send");
            send.setAccessible(true);
            Object getResponse = send.invoke(getRequest);
            Method obtainObject = getResponse.getClass().getDeclaredMethods()[0];
            obtainObject.setAccessible(true);
            return (TYPE) obtainObject.invoke(getResponse);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            throw new Error(String.format("Unexpected error while following link \"%1$s\"", href), ex);
        }
        catch (MalformedURLException ex) {
            throw new Error(String.format("Error while creating URL \"%1$s\"", getUrl()), ex);
        }
    }

    @Override
    public void close() throws Exception {
        close(true);
    }

    @Override
    public void close(boolean logout) throws Exception {
        // revoke access token:
        if (logout) {
            revokeAccessToken();
        }

        // Close HttpClient connection:
        if (client != null) {
            client.close();
        }
    }

    public HttpResponse send(HttpUriRequest request) {
        try {
            injectHeaders(request);
            return client.execute(request);
        }
        catch (Exception e) {
            throw new Error("Failed to send request", e);
        }
    }

    @Override
    public String authenticate() {
        if (ssoToken == null) {
            ssoToken = getAccessToken();
        }
        return ssoToken;
    }

    /**
     * Injects HTTP headers in to request
     *
     * @param request
     */
    private void injectHeaders(HttpUriRequest request) {
        List<Header> updated = excludeNullHeaders(request.getAllHeaders());
        if (updated != null && !updated.isEmpty()) {
            request.setHeaders(updated.toArray(new Header[updated.size()]));
        }

        for (NameValuePair nameValuePair : URLEncodedUtils.parse(request.getURI(), HTTP.UTF_8)) {
            if (nameValuePair.getName().equalsIgnoreCase("all_content")) {
                request.addHeader("All-Content", nameValuePair.getValue());
            }
        }

        request.addHeader("Version", "4");
        request.addHeader("Content-type", "application/xml");
        request.addHeader("User-Agent", "JavaSDK");
        request.addHeader("Accept", "application/xml");
        request.addHeader("Authorization", "Bearer " + getAccessToken());
    }

    private List<Header> excludeNullHeaders(Header[] headers) {
        if (headers != null && headers.length > 0) {
            List<Header> updated = new ArrayList<Header>();
            for (Header header : headers) {
                if (header.getValue() != null) {
                    updated.add(header);
                }
            }
            return updated;
        }

        return null;
    }

    private String getAccessToken() {
        if (ssoToken == null) {
            // Build SSO URL if necessary:
            URI ssoURI = ssoUrl != null ? SsoUtils.buildUrl(ssoUrl) :
                kerberos ? SsoUtils.buildSsoUrlKerberos(url) : SsoUtils.buildSsoUrlBasic(url);

            // Construct POST body:
            List<NameValuePair> params = new ArrayList<>(4);
            params.add(new BasicNameValuePair("scope", "ovirt-app-api"));
            if (kerberos) {
                params.add(new BasicNameValuePair("grant_type", "urn:ovirt:params:oauth:grant-type:http"));
            }
            else {
                params.add(new BasicNameValuePair("username", user));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("grant_type", "password"));
            }

            // Send request to obtain SSO token:
            JsonNode node = getSsoResponse(ssoURI, params);
            if (node.isArray()) {
                node = node.get(0);
            }

            if (node.get("error") != null) {
                throw new Error(
                    String.format(
                        "Error during SSO authentication %1$s : %2$s", node.get("error_code"), node.get("error")
                    )
                );
            }

            ssoToken = node.get(ssoTokenName).getTextValue();
        }

        return ssoToken;
    }

    private void revokeAccessToken() {
        // Build SSO revoke URL:
        URI ssoRevokeURI = ssoRevokeUrl != null ? SsoUtils.buildUrl(ssoRevokeUrl) :
            ssoToken != null ? SsoUtils.buildSsoRevokeUrl(url) : null;

        // Construct POST body:
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("scope", "ovirt-app-api"));
        params.add(new BasicNameValuePair("token", ssoToken));

        // Send request to revoke SSO token:
        if (ssoRevokeURI != null) {
            JsonNode node = getSsoResponse(ssoRevokeURI, params);
            if (node.isArray()) {
                node = node.get(0);
            }

            if (node.get("error") != null) {
                throw new Error(
                    String.format(
                        "Error during SSO token revoke %1$s : %2$s", node.get("error_code"), node.get("error")
                    )
                );
            }
        }
    }

    private JsonNode getSsoResponse(URI uri, List<NameValuePair> params) {
        HttpResponse response = null;
        try {
            // Send request and parse token:
            HttpPost requestToken = new HttpPost(uri);
            requestToken.addHeader("User-Agent", "JavaSDK");
            requestToken.addHeader("Accept", "application/json");
            requestToken.setEntity(new UrlEncodedFormEntity(params));
            response = client.execute(requestToken);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.getEntity().getContent());
        }
        catch (IOException ex) {
            throw new Error("Failed to parse JSON response", ex);
        }
        catch (Exception ex) {
            throw new Error("Failed to send SSO request", ex);
        }
        finally {
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }
}