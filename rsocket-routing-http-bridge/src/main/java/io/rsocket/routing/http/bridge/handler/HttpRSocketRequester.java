package io.rsocket.routing.http.bridge.handler;

import java.util.Arrays;

import io.rsocket.routing.client.spring.RoutingRSocketRequester;

/**
 * @author Olga Maciaszek-Sharma
 */
public class HttpRSocketRequester {


	private final RoutingRSocketRequester requester;


	public HttpRSocketRequester(RoutingRSocketRequester requester) {
		this.requester = requester;
	}

	// shrink method signature
	void run(InteractionMode interactionMode, String route, String address, Object data) {
		requester.route(route)
				.address(address)
				// TODO: handle empty body
				.data(data);
	}

	enum InteractionMode {
		FIRE_AND_FORGET,
		REQUEST_RESPONSE;


		static InteractionMode getValue(String value) {
			return Arrays.stream(values())
					.filter(enumValue -> enumValue
					.toString()
					.replaceAll("_", "")
					.equalsIgnoreCase(value))
					.findAny()
					.orElseThrow(() -> new IllegalArgumentException("\"" + value + "\" is not a correct RSocket Interaction Mode"));
		}

	}

}

