package io.rsocket.routing.http.bridge.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Olga Maciaszek-Sharma
 */
class PathUtilsTests {

	private static final URI URI_WITH_MODE = URI
			.create("http://test.org:8080/rr/address/route");

	private static final URI URI_WITHOUT_MODE = URI
			.create("http://test.org:8080/rr/address/route");

	PathUtilsTests() throws URISyntaxException {
	}

	@Test
	void shouldThrowExceptionWhenTooLittleSegments() throws URISyntaxException {
		URI uri = new URI("http://test.org:8080/fireAndForget");
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> PathUtils.resolveAddress(uri));
	}

	@Test
	void shouldThrowExceptionWhenTooManySegments() throws URISyntaxException {
		URI uri = new URI("http://test.org:8080/fireAndForget/address/route/anotherRoute");
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> PathUtils.resolveAddress(uri));
	}

	@ParameterizedTest
	@ArgumentsSource(UriArgumentsProvider.class)
	void shouldResolveAddress(URI uri) {
		String address = PathUtils.resolveAddress(uri);
		assertThat(address).isEqualTo("address");
	}

	@ParameterizedTest
	@ArgumentsSource(UriArgumentsProvider.class)
	void shouldResolveRoute(URI uri) {
		String address = PathUtils.resolveRoute(uri);
		assertThat(address).isEqualTo("route");
	}

	static class UriArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
			return Stream.of(URI_WITH_MODE, URI_WITHOUT_MODE).map(Arguments::of);
		}
	}

}