package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static site.commerce.catalog.services.CatalogProto.AccessProfile.STORE_DETAILS;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;

public class GetCatalogEntryByIDTest extends AbstractRestTest {

    private final int id = 28669;

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogEntries().byIds(STORE_DETAILS, id)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsTypeProductAndCatalogId() {
        given().
        when().
            get(new CatalogEntries().byIds(STORE_DETAILS, id)).
        then().
            assertThat()
            .body("type", contains("Product"))
            .body("id", contains(id))
            .body("part_number", notNullValue())
            .body("manufacturer", notNullValue())
            .body("name", notNullValue())
            .body("keywords", notNullValue())
            .log().ifError();
    }
}
