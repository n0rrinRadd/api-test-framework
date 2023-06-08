package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class GetCatalogGroupByIdentifierTest extends AbstractRestTest {

    private final String identifier = "dog-toys";

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogGroup().byIdentifier(identifier)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsCatalogGroupIdsAndIdentifiers() {
        given().
        when().
            get(new CatalogGroup().byIdentifier(identifier)).
        then().
            assertThat()
            .body("catalogId", equalTo(0))
            .body("catalogGroup.id", contains(315))
            .body("catalogGroup.ownerId", contains(1))
            .body("catalogGroup.identifier", contains(identifier))
            .body("catalogGroup.name", contains("Toys"))
            .body("catalogGroup.shortDescription", contains("Dog Toys"))
            .body("catalogGroup.thumbnail", notNullValue())
            .body("catalogGroup.published", notNullValue())
            .body("catalogGroup.sequence", notNullValue())
            .log().ifError();
    }
}
