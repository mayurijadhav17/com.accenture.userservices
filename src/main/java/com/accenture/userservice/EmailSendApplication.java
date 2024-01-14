package com.accenture.userservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Slf4j

public class EmailSendApplication {
  
  public static void main(String args[]) {
    WireMockServer wireMockServer = new WireMockServer(options().port(8082));
    wireMockServer.start();
    
    wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/email/send"))
            .willReturn(aResponse().withStatus(200)

    .withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody("Email sent")));
    log.info("Server Start");
    log.info(wireMockServer.toString());
    log.info(wireMockServer.getStubMappings().toString());
    
  }
}
