package com.accenture.userservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Slf4j

public class EmailSendApplication {
  
  public static void main(String args[]) {
    WireMockServer wireMockServer = new WireMockServer(options().port(8081));
    wireMockServer.start();
    
    wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/email/send"))
            .willReturn(aResponse().withStatus(200)));
    log.info("Server Start");
    log.error(wireMockServer.toString());
    log.error(wireMockServer.getStubMappings().toString());
    
  }
}
