package com.softideas.bursary.notification.microservice.application;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Service
public class SmsService {

//    @Value("${infobip.api.url}")
//    private String url;
//
//    @Value("${infobip.api.key}")
//    private String apiKey;

  ;

    private final WebClient webClient;

  ///  private final InfoBipSmsConfig smsConfig;

    public SmsService() {

       // this.smsConfig = smsConfig;
        this.webClient = WebClient.builder()
                .baseUrl("https://kqvxw3.api.infobip.com/sms/2/text/advanced")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "App " +"65cc2c6b813e5da2733f62d512687197-defcfce0-d67e-4daa-b5fd-c28335dc9f9b")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    public Mono<String> sendSms(String phoneNumber, String message) {

        Map<String, Object> smsPayload = Map.of(
                "messages", Collections.singletonList(
                        Map.of(
                                "from","447491163443",
                                "text", message,
                                "destinations", Collections.singletonList(Map.of("to", phoneNumber))
                        )
                )
        );

        return webClient.post()
                .bodyValue(smsPayload)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("Failed to send SMS: " + e.getMessage()));
    }
}
