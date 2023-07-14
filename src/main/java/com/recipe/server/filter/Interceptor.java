package com.recipe.server.filter;

import com.recipe.server.exceptions.RecipeServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Interceptor extends OncePerRequestFilter {

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
            String completeUrl = url + "/" + token;
            HttpResponse<String> httpResponse = callExternalApi(completeUrl);
            logger.info("Http Status code from the Token service : "+httpResponse.statusCode());
            if (httpResponse.statusCode()!=200) {
                throw new RecipeServiceException("Invalid Token!", "102");
            }else{
                filterChain.doFilter(request, response);
            }
        } catch (Exception exception) {
            logger.error("Exception occurred while calling external API : " + exception.getLocalizedMessage());
        }
    }

    /**
     * @param url
     * @return
     * @throws Exception
     */
    private HttpResponse<String> callExternalApi(String url) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
