package com.example.APIAutomation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiPage {

    private static final String BASE_URL = "https://api.restful-api.dev/objects";

    public Response getAllObjects() {
        return RestAssured.get(BASE_URL);
    }

    public Response getObjectById(String id) {
        return RestAssured.get(BASE_URL + "/" + id);
    }

    public Response createObject(String payload) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post(BASE_URL);
    }

    public Response updateObject(String id, String payload) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .put(BASE_URL + "/" + id);
    }

    public Response deleteObject(String id) {
        return RestAssured.delete(BASE_URL + "/" + id);
    }
}