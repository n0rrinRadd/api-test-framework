package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static site.commerce.catalog.services.CatalogProto.AccessProfile.STORE_DETAILS;
import static org.hamcrest.Matchers.notNullValue;

public class GetCatalogEntryByPartNumberTest extends AbstractRestTest {

    private final int partNumbers[] = {44929, 44930};

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogEntries().byPartNumbers(STORE_DETAILS, partNumbers)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsKeyValues() {
        given().
        when().
            get(new CatalogEntries().byPartNumbers(STORE_DETAILS, partNumbers)).
        then().
            assertThat()
            .body("name", notNullValue())
            .body("manufacturer", notNullValue())
            .body("rxRequired", notNullValue())
            .body("keywords", notNullValue())
            .log().ifError();
    }
}