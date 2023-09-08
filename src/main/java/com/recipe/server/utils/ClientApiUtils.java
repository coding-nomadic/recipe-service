import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class ClientApiUtils {

    private ClientApiUtils() {
        // Prevent instantiation
    }

    /**
     * Calls an external API using HTTP GET.
     *
     * @param url The URL of the external API.
     * @return The HTTP response from the external API.
     * @throws Exception If an error occurs during the API call.
     */
    public static HttpResponse<String> callExternalApi(String url) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
