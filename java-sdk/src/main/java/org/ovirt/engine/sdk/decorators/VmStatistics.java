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

package org.ovirt.engine.sdk.decorators;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;
import org.ovirt.engine.sdk.common.CollectionDecorator;
import org.ovirt.engine.sdk.exceptions.ServerException;
import org.ovirt.engine.sdk.web.HttpProxyBroker;

public class VmStatistics extends CollectionDecorator<org.ovirt.engine.sdk.entities.Statistic, org.ovirt.engine.sdk.entities.Statistics, VmStatistic> {

    private String parentId;

    public VmStatistics(HttpProxyBroker proxy, String parentId) {
        super(proxy);
        this.parentId = parentId;
    }

    private String getParentId() {
        return parentId;
    }

    @Override
    public List<VmStatistic> list() throws ClientProtocolException, ServerException, IOException, JAXBException {
        String url = "/vms/" + this.getParentId() + "/statistics";
        return list(url, org.ovirt.engine.sdk.entities.Statistics.class, VmStatistic.class);
    }

    @Override
    public VmStatistic get(String id) throws ClientProtocolException, ServerException, IOException, JAXBException {
        String url = "/vms/" + this.getParentId() + "/statistics/" + id;
        return getProxy().get(url, org.ovirt.engine.sdk.entities.Statistic.class, VmStatistic.class);
    }
}