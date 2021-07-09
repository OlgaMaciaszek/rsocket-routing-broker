package io.rsocket.routing.http.bridge.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.rsocket.routing.client.spring.RoutingRSocketRequester;
import reactor.util.function.Tuple2;

import org.springframework.util.MimeType;

/**
 * @author Olga Maciaszek-Sharma
 */
public class HttpRSocketExecutor {

	// TODO: create various instances based on transport instead of getting the one that comes with the client
	// TODO: probably should not use rsocket client autoconfig - create any requester instances here
	private final RoutingRSocketRequester requester;


	public HttpRSocketExecutor(RoutingRSocketRequester requester) {
		this.requester = requester;
	}

	// TODO: Metadata: Map<Object, MimeType> ?
	// shrink method signature
	void run(InteractionMode interactionMode, String route, String address, Object data, List<String> metadataHeaders) {
		requester.route(route)
				.address(address)
				// TODO: handle any metadata
//				.metadata()
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

