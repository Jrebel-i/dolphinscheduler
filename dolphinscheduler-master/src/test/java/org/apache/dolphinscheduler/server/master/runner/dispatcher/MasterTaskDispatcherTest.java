/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.server.master.runner.dispatcher;

import org.apache.dolphinscheduler.extract.base.utils.Host;
import org.apache.dolphinscheduler.server.master.config.MasterConfig;
import org.apache.dolphinscheduler.server.master.processor.queue.TaskEventService;
import org.apache.dolphinscheduler.server.master.runner.execute.TaskExecuteRunnable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MasterTaskDispatcherTest {

    @Test
    public void getTaskExecuteHost() {
        TaskEventService taskEventService = Mockito.mock(TaskEventService.class);
        MasterConfig masterConfig = Mockito.mock(MasterConfig.class);
        Mockito.when(masterConfig.getMasterAddress()).thenReturn("localhost:5678");
        TaskExecuteRunnable taskExecuteRunnable = Mockito.mock(TaskExecuteRunnable.class);

        MasterTaskDispatcher masterTaskDispatcher =
                new MasterTaskDispatcher(taskEventService, masterConfig);
        Host taskInstanceDispatchHost = masterTaskDispatcher.getTaskInstanceDispatchHost(taskExecuteRunnable)
                .orElseThrow(() -> new IllegalArgumentException("Cannot get the "));
        Assertions.assertEquals(masterConfig.getMasterAddress(), taskInstanceDispatchHost.getAddress());
    }
}
