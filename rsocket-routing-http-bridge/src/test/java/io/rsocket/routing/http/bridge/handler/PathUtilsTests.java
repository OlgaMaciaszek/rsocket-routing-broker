package io.rsocket.routing.http.bridge.handler;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Olga Maciaszek-Sharma
 */
class PathUtilsTests {

	private URI uri = new URI("http://test.org:8080/fireAndForget/address/route");

	PathUtilsTests() throws URISyntaxException {
	}

	@Test
	void shouldThrowExceptionWhenTooLittleSegments() throws URISyntaxException {
		URI uri = new URI("http://test.org:8080/fireAndForget");
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> PathUtils.resolveInteractionMode(uri));
	}

	@Test
	void shouldThrowExceptionWhenTooManySegments() throws URISyntaxException {
		URI uri = new URI("http://test.org:8080/fireAndForget/address/route/anotherRoute");
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> PathUtils.resolveInteractionMode(uri));
	}

	@Test
	void shouldResolveInteractionMode() {
		HttpRSocketRequester.InteractionMode resolvedMode = PathUtils
				.resolveInteractionMode(uri);
		assertThat(resolvedMode)
				.isEqualTo(HttpRSocketRequester.InteractionMode.FIRE_AND_FORGET);
	}

	@Test
	void shouldThrowExceptionForIncorrectInteractionMode() throws URISyntaxException {
		URI uri = new URI("http://test.org:8080/x/address/route");
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> PathUtils.resolveInteractionMode(uri));
	}

}