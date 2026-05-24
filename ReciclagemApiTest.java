package com.reciclagem.test;

import com.reciclagem.model.Reciclagem;
import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReciclagemApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void fluxoCompletoCRUD() {

        Reciclagem reciclagem = new Reciclagem();
        reciclagem.setId(1L);
        reciclagem.setTipo("PLASTICO");
        reciclagem.setPeso(12.5);

        // POST
        given()
                .contentType("application/json")
                .body(reciclagem)
                .when()
                .post("/reciclagem")
                .then()
                .statusCode(200);

        // GET
        given()
                .when()
                .get("/reciclagem")
                .then()
                .statusCode(200);

        // DELETE
        given()
                .when()
                .delete("/reciclagem/1")
                .then()
                .statusCode(200);
    }
}