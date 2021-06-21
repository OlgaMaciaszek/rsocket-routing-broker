import java.nio.ByteBuffer;

import io.rsocket.Payload;
import io.rsocket.routing.broker.rsocket.RoutingRSocket;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
// TODO: switch to routes?
@RestController
class RSocketController {

	private final RoutingRSocket routingRSocket;

	RSocketController(RoutingRSocket routingRSocket) {
		this.routingRSocket = routingRSocket;
	}

	@PostMapping
	ResponseEntity<Mono<Void>> fireAndForget(@RequestHeader HttpHeaders requestHeaders, @RequestBody ByteBuffer payload) {
		return ResponseEntity
				.ok(routingRSocket.fireAndForget(buildPayload(requestHeaders, payload)));
	}

	@PostMapping
	ResponseEntity<Mono<ByteBuffer>> requestResponse(@RequestHeader HttpHeaders requestHeaders, @RequestBody ByteBuffer payload) {
		Mono<Payload> responsePayload = routingRSocket
				.requestResponse(buildPayload(requestHeaders, payload));
		HttpHeaders headers = new HttpHeaders();
		Mono<ByteBuffer> processedResponsePayload = responsePayload
				.doOnNext(next -> headers.addAll(buildFromPayload(next.getMetadata())))
				.map(Payload::getData);
		return ResponseEntity.ok().headers(headers).body(processedResponsePayload);
	}

	@NonNull
	private HttpHeaders buildFromPayload(ByteBuffer responsePayload) {
		return null;
	}


	@NonNull
	private Payload buildPayload(HttpHeaders requestHeaders, ByteBuffer payload) {
		return null;
	}

}
