import java.net.URI;
import java.net.http.*;
import java.net.http.HttpOption.Http3DiscoveryMode;

void main() throws java.io.IOException, InterruptedException {
    // System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$s %2$s %5$s%6$s%n");
    // System.setProperty("jdk.httpclient.HttpClient.log", "all");
    System.setProperty("jdk.internal.httpclient.debug", "true");

    try (var client = HttpClient.newHttpClient()) {
        for (int i = 0; i < 2; i++) {
            var uri = URI.create("https://www.google.com/");
            var request = HttpRequest.newBuilder(uri)
                .version(HttpClient.Version.HTTP_3)
                .setOption(HttpOption.H3_DISCOVERY, Http3DiscoveryMode.ANY)
                .GET().build();

            HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
            IO.println("Status code: " + response.statusCode());
            IO.println("Version: " + response.version());
        }
    }
}
