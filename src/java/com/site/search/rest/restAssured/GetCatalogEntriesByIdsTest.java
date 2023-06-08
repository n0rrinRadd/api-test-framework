package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static site.commerce.catalog.services.CatalogProto.AccessProfile.STORE_DETAILS;
import static org.hamcrest.Matchers.hasSize;

public class GetCatalogEntriesByIdsTest extends AbstractRestTest {

    private final int ids[] = {28669, 28670};

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogEntries().byIds(STORE_DETAILS, ids)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsCatalogIdsAndPartNumbers() {
        String responseString =
            given().
            when().
                get(new CatalogEntries().byIds(STORE_DETAILS, ids)).
            then().
                assertThat()
                .body("name", hasSize(2))
                .body("manufacturer", hasSize(2))
                .body("rxRequired", hasSize(2))
                .body("keywords", hasSize(2)).
                log().ifError().
            extract().
                jsonPath().get().toString();
        softly.assertThat(responseString).contains("id=" + ids[0]);
        softly.assertThat(responseString).contains("id=" + ids[1]);
        softly.assertThat(responseString).contains("partNumber=44929");
        softly.assertThat(responseString).contains("partNumber=44930");
        softly.assertThat(responseString).contains("parentCatalogEntryId=" + ids[0]);
        softly.assertThat(responseString).contains("parentCatalogEntryId=" + ids[1]);
    }
}
