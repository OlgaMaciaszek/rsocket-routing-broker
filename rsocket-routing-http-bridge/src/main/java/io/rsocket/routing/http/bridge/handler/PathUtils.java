package io.rsocket.routing.http.bridge.handler;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.http.server.PathContainer;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
class PathUtils {

	private static Log LOG = LogFactory.getLog(PathUtils.class);

	private PathUtils(){
		throw new IllegalStateException("Must not instantiate utility class");
	}

	static HttpRSocketRequester.InteractionMode resolveInteractionMode(URI uri) {
		PathContainer path = PathContainer.parsePath(uri.getRawPath());
		List<PathContainer.Element> pathElements = getElements(path);
		return HttpRSocketRequester.InteractionMode.getValue(pathElements.get(0).value());
	}

	private static List<PathContainer.Element> getElements(PathContainer path) {
		List<PathContainer.Element> pathElements =  path.elements()
				.stream().filter(element -> !element.value().equals("/"))
				.collect(Collectors.toList());
		if (pathElements.size() != 3) {
			if (LOG.isErrorEnabled()){
				LOG.error("The path does not contain enough elements. InteractionMode, Address and Route segments expected.");
			}
			throw new IllegalArgumentException("The path should have 3 segments");
		}
		return pathElements;
	}
}
