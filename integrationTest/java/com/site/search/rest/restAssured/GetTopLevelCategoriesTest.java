package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class GetTopLevelCategoriesTest extends AbstractRestTest {

    @Test
    public void responseStatusCodeIs200() {
        given().
        when(). get(new CatalogGroup().byTopLevel()).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsCatalogIdAndCatalogGroupKeyValues() {
        int size = 9;
        given().
        when().
            get(new CatalogGroup().byTopLevel()).
        then().
            assertThat()
            .body("catalogId", equalTo(1004))
            .body("catalogGroup.id", hasSize(size))
            .body("catalogGroup.ownerId", hasSize(size))
            .body("catalogGroup.identifier", hasSize(size))
            .body("catalogGroup.name", hasSize(size))
            .body("catalogGroup.shortDescription", hasSize(size))
            .body("catalogGroup.published", hasSize(size))
            .body("catalogGroup.lastUpdate", hasSize(size))
            .body("catalogGroup.sequence", hasSize(size))
            .log().ifError();
    }

}
