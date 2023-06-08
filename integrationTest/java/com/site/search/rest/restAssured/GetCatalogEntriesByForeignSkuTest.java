package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static site.commerce.catalog.services.CatalogProto.AccessProfile.STORE_DETAILS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

public class GetCatalogEntriesByForeignSkuTest extends AbstractRestTest {

    private final String valid_foreign_sku = "RX1-158-01";
    private final String invalid_foreign_sku = "abc-123-01";

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogEntries().byForeignSku(STORE_DETAILS, valid_foreign_sku)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseStatusCodeIs404() {
        given().
        when().
            get(new CatalogEntries().byForeignSku(STORE_DETAILS, invalid_foreign_sku)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body("errors", contains("Could not find foreign sku: ["+ invalid_foreign_sku +"]"));
    }

    @Test
    public void responseContainsForeignSku() {

        given().
        when().
            get(new CatalogEntries().byForeignSku(STORE_DETAILS, valid_foreign_sku)).
        then().
            assertThat()
            .body("foreignSku", contains(valid_foreign_sku));
    }
}
