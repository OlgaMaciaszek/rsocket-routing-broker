/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket.routing.http.bridge.support;

import io.rsocket.routing.common.spring.ClientTransportFactory;
import io.rsocket.routing.common.spring.TransportProperties;
import io.rsocket.transport.ClientTransport;

/**
 * @author Olga Maciaszek-Sharma
 */
public class SimpleClientTransportFactory implements ClientTransportFactory {

	@Override
	public boolean supports(TransportProperties broker) {
		return true;
	}

	@Override
	public ClientTransport create(TransportProperties broker) {
		return new SimpleClientTransport(broker);
	}
}
