package com.alvachien;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class App {
    private static HttpClient client;

    public static void main(String[] args) throws IOException {
        client = HttpClient.newHttpClient();

        System.out.println("Demo 1: Check URL's connectivity within synchronous way");
        Files.lines(Path.of("urls.txt"))
            .map(App::validateLink)
            .forEach(System.out::println);

        System.out.println("Demo 2: Check URL's connectivity within asynchronous way");
        var futures = Files.lines(Path.of("urls.txt"))
                .map(App::validateLinkAsync)
                .collect(Collectors.toList());
        
        futures.stream()
            .map(CompletableFuture::join)
            .forEach(System.out::println);

    }

    private static String validateLink(String link) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(link))
            .GET()
            .build();
        try {
            HttpResponse<Void> response = client.send(request,
                HttpResponse.BodyHandlers.discarding());
            return responseToString(response);
        } catch(IOException | InterruptedException e) {
            return String.format("%s -> %s", link, false);
        }
    }

    private static CompletableFuture<String> validateLinkAsync(String link) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(link))
            .GET()
            .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.discarding())
            .thenApply(App::responseToString)
            .exceptionally(e -> String.format("%s -> %s", link, false));
    }

    private static String responseToString(HttpResponse<Void> response) {
        int status = response.statusCode();
        boolean success = status >= 200 && status <= 299;
        return String.format("%s -> %s (status: %s", response.uri(), success, status);
    }
}
