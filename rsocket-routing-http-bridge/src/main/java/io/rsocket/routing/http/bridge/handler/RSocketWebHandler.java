package io.rsocket.routing.http.bridge.handler;

import java.util.List;

import io.rsocket.Payload;
import io.rsocket.routing.client.spring.RoutingRSocketRequester;
import reactor.core.publisher.Mono;

import org.springframework.http.server.PathContainer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;

import static io.rsocket.routing.http.bridge.handler.PathUtils.resolveInteractionMode;

/**
 * @author Olga Maciaszek-Sharma
 */
public class RSocketWebHandler implements WebHandler {

	private final HttpRSocketRequester httpRSocketRequester;

	public RSocketWebHandler(HttpRSocketRequester httpRSocketRequester) {
		this.httpRSocketRequester = httpRSocketRequester;
	}


	@Override
	public Mono<Void> handle(ServerWebExchange exchange) {
		httpRSocketRequester
				.run(resolveInteractionMode(exchange.getRequest().getURI()), resolveRoute(exchange), resolveAddress(exchange),
						exchange.getRequest().getBody());

		return Mono.empty();
	}

	private String resolveAddress(ServerWebExchange exchange) {
		return null;
		// TODO
	}

	private String resolveRoute(ServerWebExchange exchange) {
		return null;
		// TODO
	}

	// TODO
	private Payload buildPayload(ServerWebExchange exchange) {
		return null;
	}




}


