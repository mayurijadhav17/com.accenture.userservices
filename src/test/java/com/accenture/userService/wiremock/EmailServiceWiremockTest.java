package com.accenture.userService.wiremock;

import com.accenture.userservice.feignClient.EmailClientImpl;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Slf4j

public class EmailServiceWiremockTest {
  
  @Mock
  EmailClientImpl emailClient;
  WireMockServer wireMockServer;
  
  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(options().port(8888));
    wireMockServer.start();
  }
  
  @AfterEach
  void tearDown() {
  }
  
  @Test
  void sendEmailTest() {
    wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/api/email-service"))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                    .withStatus(200).withBody("{\"token\":\"123456\"}/n")));
    log.error("Server Start");
    log.error(wireMockServer.toString());
    log.error(wireMockServer.getStubMappings().toString());
    emailClient = new EmailClientImpl();
    emailClient.sendEmail(); // This function is tested
    
    //assertEquals("123456", "654321");
  }
  
}