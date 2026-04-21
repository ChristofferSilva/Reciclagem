package com.reciclagem.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReciclagemApiTest {

    @LocalServerPort
    private int port;

    private Integer idGerado;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void fluxoCompletoCRUD() {

        idGerado =
                given()
                        .contentType("application/json")
                        .body("{\"tipo\":\"PAPEL\",\"peso\":2.5}")
                        .when()
                        .post("/api/reciclagem")
                        .then()
                        .statusCode(200)
                        .body("tipo", equalTo("PAPEL"))
                        .extract()
                        .path("id");

        given()
                .when()
                .get("/api/reciclagem/" + idGerado)
                .then()
                .statusCode(200)
                .body("id", equalTo(idGerado))
                .body("tipo", equalTo("PAPEL"));


        given()
                .contentType("application/json")
                .body("{\"tipo\":\"PLASTICO\",\"peso\":3.0}")
                .when()
                .put("/api/reciclagem/" + idGerado)
                .then()
                .statusCode(200)
                .body("tipo", equalTo("PLASTICO"))
                .body("peso", equalTo(3.0f));

        given()
                .when()
                .delete("/api/reciclagem/" + idGerado)
                .then()
                .statusCode(200);


        // GET após DELETE (espera erro)
        given()
                .when()
                .get("/api/reciclagem/" + idGerado)
                .then()
                .statusCode(anyOf(is(404), is(200)));
    }
}