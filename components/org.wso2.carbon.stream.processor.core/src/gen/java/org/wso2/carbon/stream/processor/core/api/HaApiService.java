/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.stream.processor.core.api;

import java.io.IOException;
import javax.ws.rs.core.Response;

@javax.annotation.Generated(//todo check whether we need this
        value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen",
        date = "2017-09-21T09:31:22.101Z")
public abstract class HaApiService {

    public abstract Response haOutputSyncTimestampGet() throws NotFoundException;

    public abstract Response haStateGet(String siddhiAppName) throws NotFoundException, IOException;
}
