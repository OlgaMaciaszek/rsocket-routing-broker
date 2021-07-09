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
	void shouldThrowExceptionForIncorrectInteractionMode() throws URISyntaxException {
		URI uri = new URI("http://test.org:8080/x/address/route");
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> PathUtils.resolveInteractionMode(uri));
	}

	@Test
	void shouldResolveInteractionMode() {
		HttpRSocketExecutor.InteractionMode resolvedMode = PathUtils
				.resolveInteractionMode(uri);
		assertThat(resolvedMode)
				.isEqualTo(HttpRSocketExecutor.InteractionMode.FIRE_AND_FORGET);
	}

	@Test
	void shouldResolveAddress() {
		String address = PathUtils.resolveAddress(uri);
		assertThat(address).isEqualTo("address");
	}

	@Test
	void shouldResolveRoute() {
		String address = PathUtils.resolveRoute(uri);
		assertThat(address).isEqualTo("route");
	}

}