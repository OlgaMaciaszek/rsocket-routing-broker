package io.rsocket.routing.http.bridge.handler;

import reactor.core.publisher.Mono;

import org.springframework.web.reactive.handler.AbstractHandlerMapping;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
public class RSocketHandlerMapping extends AbstractHandlerMapping {

	private final RSocketWebHandler rSocketWebHandler;

	public RSocketHandlerMapping(RSocketWebHandler rSocketWebHandler) {
		this.rSocketWebHandler = rSocketWebHandler;
	}


	@Override
	protected Mono<?> getHandlerInternal(ServerWebExchange serverWebExchange) {
		return Mono.just(rSocketWebHandler);
	}
}
