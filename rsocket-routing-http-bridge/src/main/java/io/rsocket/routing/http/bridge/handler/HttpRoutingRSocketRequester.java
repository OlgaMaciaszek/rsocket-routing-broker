package io.rsocket.routing.http.bridge.handler;

import io.rsocket.routing.client.spring.RoutingClientProperties;
import io.rsocket.routing.client.spring.RoutingRSocketRequester;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.RouteMatcher;

/**
 * @author Olga Maciaszek-Sharma
 */
public class HttpRoutingRSocketRequester extends RoutingRSocketRequester {

	HttpRoutingRSocketRequester(RSocketRequester delegate, RoutingClientProperties properties,
			RouteMatcher routeMatcher) {
		super(delegate, properties, routeMatcher);
	}

	// TODO: implement without using properties
	@Override
	public RoutingRequestSpec route(String route, Object... routeVars) {
		String expandedRoute = expand(route, routeVars);
		RoutingRequestSpec requestSpec = new RoutingRequestSpec(delegate.route(route, routeVars), properties
				.isFailIfMissingRoutingMetadata(), expandedRoute);

		// needs to be expanded with routeVars
		RouteMatcher.Route parsed = routeMatcher.parseRoute(expandedRoute);
		return super.route(route, routeVars);
	}
}
