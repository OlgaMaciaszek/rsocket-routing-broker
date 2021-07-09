package io.rsocket.routing.http.bridge.config;

import io.rsocket.routing.client.spring.RoutingRSocketRequester;
import io.rsocket.routing.http.bridge.handler.HttpRSocketExecutor;
import io.rsocket.routing.http.bridge.handler.RSocketHandlerMapping;
import io.rsocket.routing.http.bridge.handler.RSocketWebHandler;
import io.rsocket.routing.http.bridge.route.RSocketRouteLocator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

/**
 * @author Olga Maciaszek-Sharma
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "spring.cloud.rsocket.routing.http.bridge.enabled", matchIfMissing = true)
@ConditionalOnClass(DispatcherHandler.class)
// TODO: handle CORS?
@EnableConfigurationProperties(GlobalCorsProperties.class)
@ConditionalOnBean(RoutingRSocketRequester.class)
public class RSocketHttpBridgeAutoConfiguration {

//	@Bean
//	public RoutePredicateHandlerMapping routePredicateHandlerMapping(FilteringWebHandler webHandler,
//			RouteLocator routeLocator, GlobalCorsProperties globalCorsProperties, Environment environment) {
//		return new RoutePredicateHandlerMapping(webHandler, routeLocator, globalCorsProperties, environment);
//	}
//
//	@Bean
//	public FilteringWebHandler webHandler(List<GlobalFilter> globalFilters){
//		return new RSocketFilteringWebHandler(globalFilters);
//	}

	// TODO: hide methods, classes and constructors!

	@Bean
	public RSocketHandlerMapping rSocketPredicateHandlerMapping(RSocketWebHandler rSocketWebHandler) {
		return new RSocketHandlerMapping(rSocketWebHandler);
	}

	@Bean
	public RSocketWebHandler rSocketWebHandler(HttpRSocketExecutor httpRSocketExecutor) {
		return new RSocketWebHandler(httpRSocketExecutor);
	}

	@Bean
	public HttpRSocketExecutor httpRSocketRequester(RoutingRSocketRequester routingRSocketRequester) {
		return new HttpRSocketExecutor(routingRSocketRequester);
	}

	@Bean
	public RSocketRouteLocator routeLocator() {
		return new RSocketRouteLocator();
	}
}
