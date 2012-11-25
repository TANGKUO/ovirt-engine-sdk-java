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

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.ovirt.engine.sdk.entities.Action;
import org.ovirt.engine.sdk.entities.Response;
import org.ovirt.engine.sdk.exceptions.ServerException;
import org.ovirt.engine.sdk.utils.HttpHeaderUtils;
import org.ovirt.engine.sdk.web.HttpProxyBroker;

public class VMSnapshot extends
        org.ovirt.engine.sdk.entities.Snapshot {

    private HttpProxyBroker proxy;

    private VMSnapshotNics vMSnapshotNics;
    private VMSnapshotDisks vMSnapshotDisks;
    private VMSnapshotCdRoms vMSnapshotCdRoms;


    public VMSnapshot(HttpProxyBroker proxy) {
        this.proxy = proxy;
    }

    private HttpProxyBroker getProxy() {
        return proxy;
    }

    public synchronized VMSnapshotNics getVMSnapshotNics() {
        if (this.vMSnapshotNics == null) {
            this.vMSnapshotNics = new VMSnapshotNics(proxy, this);
        }
        return vMSnapshotNics;
    }

    public synchronized VMSnapshotDisks getVMSnapshotDisks() {
        if (this.vMSnapshotDisks == null) {
            this.vMSnapshotDisks = new VMSnapshotDisks(proxy, this);
        }
        return vMSnapshotDisks;
    }

    public synchronized VMSnapshotCdRoms getVMSnapshotCdRoms() {
        if (this.vMSnapshotCdRoms == null) {
            this.vMSnapshotCdRoms = new VMSnapshotCdRoms(proxy, this);
        }
        return vMSnapshotCdRoms;
    }



   public Action restore(Action action) throws ClientProtocolException, ServerException, IOException, JAXBException {
        String url = this.getHref() + "/restore";
        return getProxy().action(url, action, Action.class, Action.class);
    }


}
