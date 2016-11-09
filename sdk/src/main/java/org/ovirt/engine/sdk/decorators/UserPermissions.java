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
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.ovirt.engine.sdk.common.CollectionDecorator;
import org.ovirt.engine.sdk.exceptions.ServerException;
import org.ovirt.engine.sdk.utils.CollectionUtils;
import org.ovirt.engine.sdk.utils.HttpHeaderBuilder;
import org.ovirt.engine.sdk.utils.HttpHeaderUtils;
import org.ovirt.engine.sdk.utils.UrlBuilder;
import org.ovirt.engine.sdk.utils.UrlBuilder;
import org.ovirt.engine.sdk.utils.UrlHelper;
import org.ovirt.engine.sdk.web.HttpProxyBroker;
import org.ovirt.engine.sdk.web.UrlParameterType;
import org.ovirt.engine.sdk.entities.Action;

/**
 * <p>UserPermissions providing relation and functional services
 * <p>to {@link org.ovirt.engine.sdk.entities.Permissions }.
 */
@SuppressWarnings("unused")
public class UserPermissions extends
        CollectionDecorator<org.ovirt.engine.sdk.entities.Permission,
                            org.ovirt.engine.sdk.entities.Permissions,
                            UserPermission> {

    private User parent;

    /**
     * @param proxy HttpProxyBroker
     * @param parent User
     */
    public UserPermissions(HttpProxyBroker proxy, User parent) {
        super(proxy, "permissions");
        this.parent = parent;
    }

    /**
     * Lists UserPermission objects.
     *
     * @return
     *     List of {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    @Override
    public List<UserPermission> list() throws ClientProtocolException,
            ServerException, IOException {
        String url = this.parent.getHref() + SLASH + getName();
        return list(url, org.ovirt.engine.sdk.entities.Permissions.class, UserPermission.class);
    }

    /**
     * Fetches UserPermission object by id.
     *
     * @return
     *     {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    @Override
    public UserPermission get(UUID id) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.parent.getHref() + SLASH + getName() + SLASH + id.toString();
        return getProxy().get(url, org.ovirt.engine.sdk.entities.Permission.class, UserPermission.class);
    }

    /**
     * Fetches UserPermission object by id.
     *
     * @return
     *     {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    @Override
    public UserPermission getById(String id) throws ClientProtocolException,
            ServerException, IOException {
        String url = this.parent.getHref() + SLASH + getName() + SLASH + id;
        return getProxy().get(url, org.ovirt.engine.sdk.entities.Permission.class, UserPermission.class);
    }

    /**
     * Adds Permission object.
     *
     * @param permission {@link org.ovirt.engine.sdk.entities.Permission}
     *    <pre>
     *    Overload 1:
     *
     *      add a new role permission for
     *      the user on the data center
     *
     *      permission.role.id|name
     *      permission.data_center.id
     *
     *    Overload 2:
     *
     *      add a new role permission for
     *      the user on the cluster
     *
     *      permission.role.id|name
     *      permission.cluster.id
     *
     *    Overload 3:
     *
     *      add a new role permission for
     *      the user on the host
     *
     *      permission.role.id|name
     *      permission.host.id
     *
     *    Overload 4:
     *
     *      add a new role permission for
     *      the user on the storage domain
     *
     *      permission.role.id|name
     *      permission.storage_domain.id
     *
     *    Overload 5:
     *
     *      add a new role permission for
     *      the user on the vm
     *
     *      permission.role.id|name
     *      permission.vm.id
     *
     *    Overload 6:
     *
     *      add a new role permission for
     *      the user on the vm pool
     *
     *      permission.role.id|name
     *      permission.vmpool.id
     *
     *    Overload 7:
     *
     *      add a new role permission for
     *      the user on the template
     *
     *      permission.role.id|name
     *      permission.template.id
     *    </pre>
     *
     *
     * @return
     *     {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public UserPermission add(org.ovirt.engine.sdk.entities.Permission permission) throws
            ClientProtocolException, ServerException, IOException {
        String url = this.parent.getHref() + SLASH + getName();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().add(url, permission,
                org.ovirt.engine.sdk.entities.Permission.class,
                UserPermission.class, headers);
    }
    /**
     * Adds Permission object.
     *
     * @param permission {@link org.ovirt.engine.sdk.entities.Permission}
     *    <pre>
     *    Overload 1:
     *
     *      add a new role permission for
     *      the user on the data center
     *
     *      permission.role.id|name
     *      permission.data_center.id
     *
     *    Overload 2:
     *
     *      add a new role permission for
     *      the user on the cluster
     *
     *      permission.role.id|name
     *      permission.cluster.id
     *
     *    Overload 3:
     *
     *      add a new role permission for
     *      the user on the host
     *
     *      permission.role.id|name
     *      permission.host.id
     *
     *    Overload 4:
     *
     *      add a new role permission for
     *      the user on the storage domain
     *
     *      permission.role.id|name
     *      permission.storage_domain.id
     *
     *    Overload 5:
     *
     *      add a new role permission for
     *      the user on the vm
     *
     *      permission.role.id|name
     *      permission.vm.id
     *
     *    Overload 6:
     *
     *      add a new role permission for
     *      the user on the vm pool
     *
     *      permission.role.id|name
     *      permission.vmpool.id
     *
     *    Overload 7:
     *
     *      add a new role permission for
     *      the user on the template
     *
     *      permission.role.id|name
     *      permission.template.id
     *    </pre>
     *
     * @param expect
     *    <pre>
     *    [201-created]
     *    </pre>
     * @return
     *     {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public UserPermission add(org.ovirt.engine.sdk.entities.Permission permission, String expect) throws
            ClientProtocolException, ServerException, IOException {
        String url = this.parent.getHref() + SLASH + getName();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (expect != null) {
            headersBuilder.add("Expect", expect);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().add(url, permission,
                org.ovirt.engine.sdk.entities.Permission.class,
                UserPermission.class, headers);
    }
    /**
     * Adds Permission object.
     *
     * @param permission {@link org.ovirt.engine.sdk.entities.Permission}
     *    <pre>
     *    Overload 1:
     *
     *      add a new role permission for
     *      the user on the data center
     *
     *      permission.role.id|name
     *      permission.data_center.id
     *
     *    Overload 2:
     *
     *      add a new role permission for
     *      the user on the cluster
     *
     *      permission.role.id|name
     *      permission.cluster.id
     *
     *    Overload 3:
     *
     *      add a new role permission for
     *      the user on the host
     *
     *      permission.role.id|name
     *      permission.host.id
     *
     *    Overload 4:
     *
     *      add a new role permission for
     *      the user on the storage domain
     *
     *      permission.role.id|name
     *      permission.storage_domain.id
     *
     *    Overload 5:
     *
     *      add a new role permission for
     *      the user on the vm
     *
     *      permission.role.id|name
     *      permission.vm.id
     *
     *    Overload 6:
     *
     *      add a new role permission for
     *      the user on the vm pool
     *
     *      permission.role.id|name
     *      permission.vmpool.id
     *
     *    Overload 7:
     *
     *      add a new role permission for
     *      the user on the template
     *
     *      permission.role.id|name
     *      permission.template.id
     *    </pre>
     *
     * @param expect
     *    <pre>
     *    [201-created]
     *    </pre>
     * @param correlationId
     *    <pre>
     *    [any string]
     *    </pre>
     * @return
     *     {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public UserPermission add(org.ovirt.engine.sdk.entities.Permission permission, String expect, String correlationId) throws
            ClientProtocolException, ServerException, IOException {
        String url = this.parent.getHref() + SLASH + getName();

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        if (expect != null) {
            headersBuilder.add("Expect", expect);
        }
        if (correlationId != null) {
            headersBuilder.add("Correlation-Id", correlationId);
        }
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(url);
        url = urlBuilder.build();

        return getProxy().add(url, permission,
                org.ovirt.engine.sdk.entities.Permission.class,
                UserPermission.class, headers);
    }
    /**
     * Lists UserPermission objects.
     *
     * @param max
     *    <pre>
     *    [max results]
     *    </pre>
     *
     * @return List of {@link UserPermission }
     *
     * @throws ClientProtocolException
     *             Signals that HTTP/S protocol error has occurred.
     * @throws ServerException
     *             Signals that an oVirt api error has occurred.
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred.
     */
    public List<UserPermission> list(Integer max) throws ClientProtocolException,
            ServerException, IOException {

        HttpHeaderBuilder headersBuilder = new HttpHeaderBuilder();
        List<Header> headers = headersBuilder.build();

        UrlBuilder urlBuilder = new UrlBuilder(this.parent.getHref() + SLASH + getName());
        if (max != null) {
            urlBuilder.add("max", max, UrlParameterType.MATRIX);
        }

        String url = urlBuilder.build();

        return list(url, org.ovirt.engine.sdk.entities.Permissions.class,
                UserPermission.class, headers);
    }

}