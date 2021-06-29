package io.rsocket.routing.http.bridge.route;

import reactor.core.publisher.Flux;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;

/**
 * @author Olga Maciaszek-Sharma
 */
public class RSocketRouteLocator implements RouteLocator {
	@Override
	public Flux<Route> getRoutes() {
		// TODO: remove this altogether
		return Flux.empty();
	}
}
