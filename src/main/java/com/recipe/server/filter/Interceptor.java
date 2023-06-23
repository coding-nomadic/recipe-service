package com.recipe.server.filter;

import com.recipe.server.exceptions.RecipeServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Interceptor extends OncePerRequestFilter {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${token.service.url}")
    private String url;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new RecipeServiceException("Authorisation in header cannot be null", "102");
        }
        String token = authorizationHeader.substring(7);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "/" + token, String.class);
        } catch (Exception exception) {
            if(exception.getLocalizedMessage().contains("500")){
                throw new RecipeServiceException("Invalid Token!", "102");
            }
        }
        filterChain.doFilter(request, response);
    }
}
