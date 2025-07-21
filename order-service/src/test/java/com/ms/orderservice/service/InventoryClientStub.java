package com.ms.orderservice.service;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubInventoryCall(String skuCode,Integer quantity) {

        stubFor(get(urlEqualTo("/api/v1/inventory/stockAvailable/" + skuCode + "/" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));

    }
}
