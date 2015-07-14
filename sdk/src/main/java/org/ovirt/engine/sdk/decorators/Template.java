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

// *********************************************************************
// ********************* GENERATED CODE - DO NOT MODIFY ****************
// *********************************************************************

package org.ovirt.engine.sdk.decorators;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.ovirt.engine.sdk.entities.Action;
import org.ovirt.engine.sdk.entities.Response;
import org.ovirt.engine.sdk.exceptions.ServerException;
import org.ovirt.engine.sdk.utils.HttpHeaderBuilder;
import org.ovirt.engine.sdk.utils.HttpHeaderUtils;
import org.ovirt.engine.sdk.utils.UrlBuilder;
import org.ovirt.engine.sdk.web.HttpProxyBroker;
import org.ovirt.engine.sdk.web.UrlParameterType;

/**
 * <p>Template providing relation and functional services
 * <p>to {@link org.ovirt.engine.sdk.entities.Template }.
 */
@SuppressWarnings("unused")
public class Template extends
        org.ovirt.engine.sdk.entities.Template {

    private HttpProxyBroker proxy;
    private final Object LOCK = new Object();

    private volatile TemplateCdRoms templateCdRoms;
    private volatile TemplateDisks templateDisks;
    private volatile TemplateNICs templateNICs;
    private volatile TemplatePermissions templatePermissions;
    private volatile TemplateTags templateTags;
    private volatile TemplateWatchDogs templateWatchDogs;


    /**
     * @param proxy HttpProxyBroker
     */
    public Template(HttpProxyBroker proxy) {
        this.proxy = proxy;
    }

    /**
     * @return HttpProxyBroker
     */
    private HttpProxyBroker getProxy() {
        return proxy;
    }

    /**
     * Gets the value of the TemplateCdRoms property.
     *
     * @return
     *     {@link TemplateCdRoms }
     */
    public TemplateCdRoms getCdRoms() {
        if (this.templateCdRoms == null) {
            synchronized (this.LOCK) {
                if (this.templateCdRoms == null) {
                    this.templateCdRoms = new TemplateCdRoms(proxy, this);
                }
            }
        }
        return templateCdRoms;
    }
    /**
     * Gets the value of the TemplateDisks property.
     *
     * @return
     *     {@link TemplateDisks }
     */
    public TemplateDisks getDisks() {
        if (this.templateDisks == null) {
            synchronized (this.LOCK) {
                if (this.templateDisks == null) {
                    this.templateDisks = new TemplateDisks(proxy, this);
                }
            }
        }
        return templateDisks;
    }
    /**
     * Gets the value of the TemplateNICs property.
     *
     * @return
     *     {@link TemplateNICs }
     */
    public TemplateNICs getNics() {
        if (this.templateNICs == null) {
            synchronized (this.LOCK) {
                if (this.templateNICs == null) {
                    this.templateNICs = new TemplateNICs(proxy, this);
                }
            }
        }
        return templateNICs;
    }
    /**
     * Gets the value of the TemplatePermissions property.
     *
     * @return
     *     {@link TemplatePermissions }
     */
    public TemplatePermissions getPermissions() {
        if (this.templatePermissions == null) {
            synchronized (this.LOCK) {
                if (this.templatePermissions == null) {
                    this.templatePermissions = new TemplatePermissions(proxy, this);
                }
            }
        }
        return templatePermissions;
    }
    /**
     * Gets the value of the TemplateTags property.
     *
     * @return
     *     {@link TemplateTags }
     */
    public TemplateTags getTags() {
        if (this.templateTags == null) {
            synchronized (this.LOCK) {
                if (this.templateTags == null) {
                    this.templateTags = new TemplateTags(proxy, this);
                }
            }
        }
        return templateTags;
    }
    /**
     * Gets the value of the TemplateWatchDogs property.
     *
     * @return
     *     {@link TemplateWatchDogs }
     */
    public TemplateWatchDogs getWatchDogs() {
        if (this.templateWatchDogs == null) {
            synchronized (this.LOCK) {
                if (this.templateWatchDogs == null) {
                    this.templateWatchDogs = new TemplateWatchDogs(proxy, this);
                }
            }
        }
        return templateWatchDogs;
    }


    /**
     * Deletes object.
     *
     * @return
     *     {@link Response }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Response delete() throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().delete(url, Response.class, headers);
    }
    /**
     * Deletes object.
     * @param async
     *    <pre>
     *    [true|false]
     *    </pre>
     * @return
     *     {@link Response }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Response delete(Boolean async) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        if (async != null) {
            urlBuilder.add("async", async, UrlParameterType.MATRIX);
        }

        url = urlBuilder.build();

        return getProxy().delete(url, Response.class, headers);
    }
    /**
     * Deletes object.
     * @param async
     *    <pre>
     *    [true|false]
     *    </pre>
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @return
     *     {@link Response }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Response delete(Boolean async, String correlationId) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        if (async != null) {
            urlBuilder.add("async", async, UrlParameterType.MATRIX);
        }

        url = urlBuilder.build();

        return getProxy().delete(url, Response.class, headers);
    }
    /**
     * Performs exportTemplate action.
     *
     * @param action {@link org.ovirt.engine.sdk.entities.Action}
     *    <pre>
     *    action.storage_domain.id|name
     *    [action.exclusive]
     *    [action.async]
     *    [action.grace_period.expiry]
     *    </pre>
     *
     *
     * @return
     *     {@link Action }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Action exportTemplate(Action action) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref() + "/export";

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().action(url, action, Action.class, Action.class, headers);
    }
    /**
     * Performs exportTemplate action.
     *
     * @param action {@link org.ovirt.engine.sdk.entities.Action}
     *    <pre>
     *    action.storage_domain.id|name
     *    [action.exclusive]
     *    [action.async]
     *    [action.grace_period.expiry]
     *    </pre>
     *
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @return
     *     {@link Action }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Action exportTemplate(Action action, String correlationId) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref() + "/export";

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().action(url, action, Action.class, Action.class, headers);
    }
    /**
     * Performs exportTemplate action.
     *
     * @param action {@link org.ovirt.engine.sdk.entities.Action}
     *    <pre>
     *    action.storage_domain.id|name
     *    [action.exclusive]
     *    [action.async]
     *    [action.grace_period.expiry]
     *    </pre>
     *
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @param async
     *    <pre>
     *    [true|false]
     *    </pre>
     * @return
     *     {@link Action }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Action exportTemplate(Action action, String correlationId, Boolean async) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref() + "/export";

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        if (async != null) {
            urlBuilder.add("async", async, UrlParameterType.MATRIX);
        }

        url = urlBuilder.build();

        return getProxy().action(url, action, Action.class, Action.class, headers);
    }
    /**
     * Updates Template object.
     *
     * @param template {@link org.ovirt.engine.sdk.entities.Template}
     *    <pre>
     *    [template.name]
     *    [template.memory]
     *    [template.io.threads]
     *    [template.cpu.topology.cores]
     *    [template.high_availability.enabled]
     *    [template.os.cmdline]
     *    [template.origin]
     *    [template.high_availability.priority]
     *    [template.timezone]
     *    [template.time_zone.name]
     *    [template.domain.name]
     *    [template.type]
     *    [template.stateless]
     *    [template.delete_protected]
     *    [template.sso.methods.method]
     *    [vm.rng_device.rate.bytes]
     *    [vm.rng_device.rate.period]
     *    [vm.rng_device.source]
     *    [template.console.enabled]
     *    [template.placement_policy.affinity]
     *    [template.description]
     *    [template.comment]
     *    [template.custom_properties.custom_property]
     *    [template.os.type]
     *    [template.os.boot]
     *    [template.cpu.topology.sockets]
     *    [template.cpu_shares]
     *    [template.cpu.architecture]
     *    [template.os.kernel]
     *    [template.display.type]
     *    [template.display.monitors]
     *    [vm.display.single_qxl_pci]
     *    [template.display.allow_override]
     *    [template.display.smartcard_enabled]
     *    [vm.display.file_transfer_enabled]
     *    [vm.display.copy_paste_enabled]
     *    [template.display.keyboard_layout]
     *    [template.display.disconnect_action]
     *    [template.os.initRd]
     *    [template.usb.enabled]
     *    [template.usb.type]
     *    [template.tunnel_migration]
     *    [template.migration_downtime]
     *    [template.virtio_scsi.enabled]
     *    [template.soundcard_enabled]
     *    [template.custom_emulated_machine]
     *    [template.custom_cpu_model]
     *    [template.version.version_name]
     *    [template.serial_number.policy]
     *    [template.serial_number.value]
     *    [template.bios.boot_menu.enabled]
     *    [template.start_paused]
     *    [template.cpu_profile.id]
     *    [template.migration.auto_converge]
     *    [template.migration.compressed]
     *    [template.small_icon.id]
     *    [template.large_icon.id]
     *    [template.large_icon.media_type]
     *    [template.large_icon.data]
     *    </pre>
     *
     *
     * @return
     *     {@link Template }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Template update() throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().update(
                url,
                this,
                org.ovirt.engine.sdk.entities.Template.class,
                Template.class,
                headers);
    }
    /**
     * Updates Template object.
     *
     * @param template {@link org.ovirt.engine.sdk.entities.Template}
     *    <pre>
     *    [template.name]
     *    [template.memory]
     *    [template.io.threads]
     *    [template.cpu.topology.cores]
     *    [template.high_availability.enabled]
     *    [template.os.cmdline]
     *    [template.origin]
     *    [template.high_availability.priority]
     *    [template.timezone]
     *    [template.time_zone.name]
     *    [template.domain.name]
     *    [template.type]
     *    [template.stateless]
     *    [template.delete_protected]
     *    [template.sso.methods.method]
     *    [vm.rng_device.rate.bytes]
     *    [vm.rng_device.rate.period]
     *    [vm.rng_device.source]
     *    [template.console.enabled]
     *    [template.placement_policy.affinity]
     *    [template.description]
     *    [template.comment]
     *    [template.custom_properties.custom_property]
     *    [template.os.type]
     *    [template.os.boot]
     *    [template.cpu.topology.sockets]
     *    [template.cpu_shares]
     *    [template.cpu.architecture]
     *    [template.os.kernel]
     *    [template.display.type]
     *    [template.display.monitors]
     *    [vm.display.single_qxl_pci]
     *    [template.display.allow_override]
     *    [template.display.smartcard_enabled]
     *    [vm.display.file_transfer_enabled]
     *    [vm.display.copy_paste_enabled]
     *    [template.display.keyboard_layout]
     *    [template.display.disconnect_action]
     *    [template.os.initRd]
     *    [template.usb.enabled]
     *    [template.usb.type]
     *    [template.tunnel_migration]
     *    [template.migration_downtime]
     *    [template.virtio_scsi.enabled]
     *    [template.soundcard_enabled]
     *    [template.custom_emulated_machine]
     *    [template.custom_cpu_model]
     *    [template.version.version_name]
     *    [template.serial_number.policy]
     *    [template.serial_number.value]
     *    [template.bios.boot_menu.enabled]
     *    [template.start_paused]
     *    [template.cpu_profile.id]
     *    [template.migration.auto_converge]
     *    [template.migration.compressed]
     *    [template.small_icon.id]
     *    [template.large_icon.id]
     *    [template.large_icon.media_type]
     *    [template.large_icon.data]
     *    </pre>
     *
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @return
     *     {@link Template }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Template update(String correlationId) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().update(
                url,
                this,
                org.ovirt.engine.sdk.entities.Template.class,
                Template.class,
                headers);
    }
    /**
     * Updates Template object.
     *
     * @param template {@link org.ovirt.engine.sdk.entities.Template}
     *    <pre>
     *    [template.name]
     *    [template.memory]
     *    [template.io.threads]
     *    [template.cpu.topology.cores]
     *    [template.high_availability.enabled]
     *    [template.os.cmdline]
     *    [template.origin]
     *    [template.high_availability.priority]
     *    [template.timezone]
     *    [template.time_zone.name]
     *    [template.domain.name]
     *    [template.type]
     *    [template.stateless]
     *    [template.delete_protected]
     *    [template.sso.methods.method]
     *    [vm.rng_device.rate.bytes]
     *    [vm.rng_device.rate.period]
     *    [vm.rng_device.source]
     *    [template.console.enabled]
     *    [template.placement_policy.affinity]
     *    [template.description]
     *    [template.comment]
     *    [template.custom_properties.custom_property]
     *    [template.os.type]
     *    [template.os.boot]
     *    [template.cpu.topology.sockets]
     *    [template.cpu_shares]
     *    [template.cpu.architecture]
     *    [template.os.kernel]
     *    [template.display.type]
     *    [template.display.monitors]
     *    [vm.display.single_qxl_pci]
     *    [template.display.allow_override]
     *    [template.display.smartcard_enabled]
     *    [vm.display.file_transfer_enabled]
     *    [vm.display.copy_paste_enabled]
     *    [template.display.keyboard_layout]
     *    [template.display.disconnect_action]
     *    [template.os.initRd]
     *    [template.usb.enabled]
     *    [template.usb.type]
     *    [template.tunnel_migration]
     *    [template.migration_downtime]
     *    [template.virtio_scsi.enabled]
     *    [template.soundcard_enabled]
     *    [template.custom_emulated_machine]
     *    [template.custom_cpu_model]
     *    [template.version.version_name]
     *    [template.serial_number.policy]
     *    [template.serial_number.value]
     *    [template.bios.boot_menu.enabled]
     *    [template.start_paused]
     *    [template.cpu_profile.id]
     *    [template.migration.auto_converge]
     *    [template.migration.compressed]
     *    [template.small_icon.id]
     *    [template.large_icon.id]
     *    [template.large_icon.media_type]
     *    [template.large_icon.data]
     *    </pre>
     *
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @param async
     *    <pre>
     *    [true|false]
     *    </pre>
     * @return
     *     {@link Template }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Template update(String correlationId, Boolean async) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        if (async != null) {
            urlBuilder.add("async", async, UrlParameterType.MATRIX);
        }

        url = urlBuilder.build();

        return getProxy().update(
                url,
                this,
                org.ovirt.engine.sdk.entities.Template.class,
                Template.class,
                headers);
    }
    /**
     * Updates Template object.
     *
     * @param template {@link org.ovirt.engine.sdk.entities.Template}
     *    <pre>
     *    [template.name]
     *    [template.memory]
     *    [template.io.threads]
     *    [template.cpu.topology.cores]
     *    [template.high_availability.enabled]
     *    [template.os.cmdline]
     *    [template.origin]
     *    [template.high_availability.priority]
     *    [template.timezone]
     *    [template.time_zone.name]
     *    [template.domain.name]
     *    [template.type]
     *    [template.stateless]
     *    [template.delete_protected]
     *    [template.sso.methods.method]
     *    [vm.rng_device.rate.bytes]
     *    [vm.rng_device.rate.period]
     *    [vm.rng_device.source]
     *    [template.console.enabled]
     *    [template.placement_policy.affinity]
     *    [template.description]
     *    [template.comment]
     *    [template.custom_properties.custom_property]
     *    [template.os.type]
     *    [template.os.boot]
     *    [template.cpu.topology.sockets]
     *    [template.cpu_shares]
     *    [template.cpu.architecture]
     *    [template.os.kernel]
     *    [template.display.type]
     *    [template.display.monitors]
     *    [vm.display.single_qxl_pci]
     *    [template.display.allow_override]
     *    [template.display.smartcard_enabled]
     *    [vm.display.file_transfer_enabled]
     *    [vm.display.copy_paste_enabled]
     *    [template.display.keyboard_layout]
     *    [template.display.disconnect_action]
     *    [template.os.initRd]
     *    [template.usb.enabled]
     *    [template.usb.type]
     *    [template.tunnel_migration]
     *    [template.migration_downtime]
     *    [template.virtio_scsi.enabled]
     *    [template.soundcard_enabled]
     *    [template.custom_emulated_machine]
     *    [template.custom_cpu_model]
     *    [template.version.version_name]
     *    [template.serial_number.policy]
     *    [template.serial_number.value]
     *    [template.bios.boot_menu.enabled]
     *    [template.start_paused]
     *    [template.cpu_profile.id]
     *    [template.migration.auto_converge]
     *    [template.migration.compressed]
     *    [template.small_icon.id]
     *    [template.large_icon.id]
     *    [template.large_icon.media_type]
     *    [template.large_icon.data]
     *    </pre>
     *
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @param async
     *    <pre>
     *    [true|false]
     *    </pre>
     * @param expect
     *    <pre>
     *    [202-accepted]
     *    </pre>
     * @return
     *     {@link Template }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public Template update(String correlationId, Boolean async, String expect) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.getHref();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        if (expect != null) {
            headersBuilder.add("Expect", expect);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        if (async != null) {
            urlBuilder.add("async", async, UrlParameterType.MATRIX);
        }

        url = urlBuilder.build();

        return getProxy().update(
                url,
                this,
                org.ovirt.engine.sdk.entities.Template.class,
                Template.class,
                headers);
    }

}