package com.shevchenkostas77.rest;

import com.shevchenkostas77.rest.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class SpringRestClient {
    private static final String URL = "http://94.198.50.185:7081/api/users";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();


    public static void main(String[] args) {
        User newUser = User.builder().id(3L).name("James").lastName("Brown").age((byte) 3).build();
        User updatedUser = User.builder().id(3L).name("Thomas").lastName("Shelby").age((byte) 3).build();
        User deleteUser = User.builder().id(3L).name("Thomas").lastName("Shelby").age((byte) 3).build();

        SpringRestClient.getAll();
        SpringRestClient.create(newUser);
        SpringRestClient.update(updatedUser);
        SpringRestClient.delete(deleteUser);
    }

    public static void getAll() {
        ResponseEntity<String> result = restTemplate
                .exchange(URL,
                        HttpMethod.GET,
                        null,
                        String.class);

        List<String> sessionId = Objects.requireNonNull(result
                .getHeaders().get("Set-Cookie"));
        headers.set("Cookie", String.join(";", sessionId));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public static void create(User user) {
        ResponseEntity<String> result = restTemplate
                .exchange(URL,
                        HttpMethod.POST,
                        new HttpEntity<>(user, headers),
                        String.class);

        System.out.println("Первая часть кода = " + result.getBody());
    }

    public static void update(User user) {
        ResponseEntity<String> result = restTemplate
                .exchange(URL,
                        HttpMethod.PUT,
                        new HttpEntity<>(user, headers),
                        String.class);

        System.out.println("Вторая часть кода = " + result.getBody());
    }

    public static void delete(User user) {
        ResponseEntity<String> result = restTemplate
                .exchange(URL + "/" + user.getId(),
                        HttpMethod.DELETE,
                        new HttpEntity<>(user, headers),
                        String.class);

        System.out.println("Третья часть кода = " + result.getBody());
    }
}
