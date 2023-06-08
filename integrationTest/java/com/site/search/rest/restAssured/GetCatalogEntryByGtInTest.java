package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static site.commerce.catalog.services.CatalogProto.AccessProfile.STORE_DETAILS;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

public class GetCatalogEntryByGtInTest extends AbstractRestTest {

    private final String GTIN = "079100758166";

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogEntries().byGTIN(STORE_DETAILS, GTIN)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsCatalogId() {
        given().
        when().
            get(new CatalogEntries().byGTIN(STORE_DETAILS, GTIN)).
        then().
            assertThat()
            .body("id", contains(181945))
            .body("type", contains("Item"))
            .body("manufacturer", notNullValue())
            .body("part_number", notNullValue())
            .body("name", notNullValue())
            .body("keywords", hasSize(1))
            .log().ifError().
        extract().
                path("type").equals("Item");
    }
}
