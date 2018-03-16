package be.inventj.notemanager.api.authentication;

import be.inventj.notemanager.api.health.VersionHandler;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * A custom webflux filter doing naughty JWT authentication
 *
 * @author Joeri Peeters
 */
public class JwtAuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain) {

        if (Arrays.asList("/auth/token", "/author/version")
                  .contains(serverWebExchange.getRequest().getPath().pathWithinApplication().value())) {
            return webFilterChain.filter(serverWebExchange);
        }

        String token = serverWebExchange.getRequest().getHeaders().getFirst("x-token");

        try {
            Jwts.parser()
                .setSigningKey(AuthenticationHandler.SECRET)
                .requireIssuer(VersionHandler.VERSION)
                .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return Mono.empty();
        }

        return webFilterChain.filter(serverWebExchange);
    }
}
