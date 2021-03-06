/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicecomb.core.context;

import javax.ws.rs.core.Response.StatusType;

import org.junit.Assert;
import org.junit.Test;

import io.servicecomb.swagger.invocation.context.HttpStatus;
import io.servicecomb.swagger.invocation.context.HttpStatusManager;

public class TestHttpStatusManager {
    @Test
    public void test() {
        HttpStatusManager mgr = new HttpStatusManager();

        StatusType st = mgr.getOrCreateByStatusCode(200);
        Assert.assertEquals(200, st.getStatusCode());

        st = mgr.getOrCreateByStatusCode(250);
        Assert.assertEquals(250, st.getStatusCode());

        try {
            mgr.addStatusType(new HttpStatus(250, "250"));
            throw new Error("not allowed");
        } catch (Throwable e) {
            Assert.assertEquals("repeated status code: 250", e.getMessage());
        }
    }
}
