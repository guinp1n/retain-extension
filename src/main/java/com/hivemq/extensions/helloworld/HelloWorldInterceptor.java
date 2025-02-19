/*
 * Copyright 2018-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hivemq.extensions.helloworld;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.interceptor.publish.PublishInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.publish.parameter.PublishInboundInput;
import com.hivemq.extension.sdk.api.interceptor.publish.parameter.PublishInboundOutput;
import com.hivemq.extension.sdk.api.packets.publish.ModifiablePublishPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * This is a {@link PublishInboundInterceptor},
 * it changes every incoming PUBLISH from the client1 and sets the Retain flag to true.
 *
 * @author Dasha Samkova
 * @since 4.36.0
 */
public class HelloWorldInterceptor implements PublishInboundInterceptor {
    private static final @NotNull Logger log = LoggerFactory.getLogger(HelloWorldInterceptor.class);

    @Override
    public void onInboundPublish(
            final @NotNull PublishInboundInput publishInboundInput,
            final @NotNull PublishInboundOutput publishInboundOutput) {

        log.info("Received inbound publish from {}", publishInboundInput.getClientInformation().getClientId());
        final ModifiablePublishPacket modifiablePublishPacket = publishInboundOutput.getPublishPacket();
        if ("client1".equals(publishInboundInput.getClientInformation().getClientId())) {
            modifiablePublishPacket.setRetain(true);
            log.info("Retaining client1 packet");
        }
    }
}