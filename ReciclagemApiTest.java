package com.reciclagem.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReciclagemApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void deveCriar() {
        given()
                .contentType("application/json")
                .body("{\"tipo\":\"PAPEL\",\"peso\":2.5}")
                .when()
                .post("/api/reciclagem")
                .then()
                .statusCode(200)
                .body("tipo", equalTo("PAPEL"));
    }
}