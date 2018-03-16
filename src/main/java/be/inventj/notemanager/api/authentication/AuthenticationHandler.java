package be.inventj.notemanager.api.authentication;

import be.inventj.notemanager.api.health.VersionHandler;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class AuthenticationHandler {

    protected static String SECRET = "joeriforpresident";

    @Autowired
    private TokenRepository repository;

    public Mono<ServerResponse> verify(ServerRequest request) {

        Mono<Credentials> secret = request.bodyToMono(Credentials.class);

        String secretValue = secret.block().getSecret();

        if (!secretValue.contains(SECRET)) {
            return ServerResponse.badRequest()
                                 .contentType(APPLICATION_JSON)
                                 .body(fromObject("You don't know the secret"));
        }

        String theOnlyToken = Jwts.builder()
                                  .setIssuer(VersionHandler.VERSION)
                                  .signWith(SignatureAlgorithm.HS256, secretValue)
                                  .compact();

        //todo debug
        // repository.save(new Token(null, theOnlyToken));

        return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(theOnlyToken));
    }
}
