package io.rsocket.routing.http.bridge.handler;

import java.net.URI;
import java.util.List;

import io.rsocket.Payload;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;

import static io.rsocket.routing.http.bridge.handler.PathUtils.resolveAddress;
import static io.rsocket.routing.http.bridge.handler.PathUtils.resolveInteractionMode;
import static io.rsocket.routing.http.bridge.handler.PathUtils.resolveRoute;

/**
 * @author Olga Maciaszek-Sharma
 */
public class RSocketWebHandler implements WebHandler {

	private final HttpRSocketExecutor httpRSocketExecutor;

	public RSocketWebHandler(HttpRSocketExecutor httpRSocketExecutor) {
		this.httpRSocketExecutor = httpRSocketExecutor;
	}


	@Override
	public Mono<Void> handle(ServerWebExchange exchange) {
		URI uri = exchange.getRequest().getURI();
		// TODO: discuss any metadata reduced to Strings?
		HttpHeaders headers = exchange.getRequest().getHeaders();
		List<String> metadataHeaders = headers.get("X-RSOCKET-Metadata");
		httpRSocketExecutor
				.run(resolveInteractionMode(uri),
						resolveRoute(uri), resolveAddress(uri),
						exchange.getRequest().getBody(), metadataHeaders);
		return Mono.empty();
	}


	// TODO
	private Payload buildPayload(ServerWebExchange exchange) {
		return null;
	}




}


