package app.api.Filter;

import app.api.Service.JwtService;
import app.api.Security.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final MyUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Autowired
    public JwtAuthFilter(MyUserDetailsService userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken;
        String username = null;

        jwtToken = getJwtFromCookie(request);

        System.out.println("JWT token received from the Cookie"+jwtToken);

        // if (jwtToken == null) {
        //     String authHeader = request.getHeader("Authorization");
        //     if (authHeader != null && authHeader.startsWith("Bearer ")) {
        //         jwtToken = authHeader.substring(7);
        //     }
        // }

        if (jwtToken != null) {
            username = jwtService.extractUsername(jwtToken);

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    // Set proper authentication details
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

        }
        filterChain.doFilter(request, response);

    }
    public Long extractUserId(HttpServletRequest request) {
        String token = getJwtFromCookie(request);
        if (token != null) {
            Long userId=jwtService.extractUserId(token);
            return userId ;
        }
        return null;
    }


    private String getJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println("Debug:"+"Cookie Name:"+cookie.getName() +"\nCookie Value:" + cookie.getValue());
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        else System.out.println("Cookie is empty");
        return null;
    }
}