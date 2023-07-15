package com.recipe.server.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientApiUtils {

    ClientApiUtils(){}
    public static HttpResponse<String> callExternalApi(String url) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
