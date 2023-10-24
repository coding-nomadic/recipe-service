package com.recipe.server.filter;

import com.recipe.server.exceptions.RecipeServiceException;
import com.recipe.server.utils.ClientApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;

@Component
public class TokenValidationInterceptor extends OncePerRequestFilter {

    private final String tokenServiceUrl;

    public TokenValidationInterceptor(@Value("${token.service.url}") String tokenServiceUrl) {
        this.tokenServiceUrl = tokenServiceUrl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RecipeServiceException("Authorization header is missing or invalid", "102");
        }
        String token = authorizationHeader.substring(7);
        try {
            String completeUrl = tokenServiceUrl + "/" + token;
            HttpResponse<String> httpResponse = ClientApiUtils.callExternalApi(completeUrl);
            int statusCode = httpResponse.statusCode();
            if (statusCode != 200) {
                throw new RecipeServiceException("Invalid Token!", "102");
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (IOException exception) {
            throw new RecipeServiceException("Error occurred while calling external API: " + exception.getMessage(), "102");
        }
    }
}
