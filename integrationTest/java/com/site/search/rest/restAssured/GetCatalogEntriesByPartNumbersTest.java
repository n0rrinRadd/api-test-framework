package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static site.commerce.catalog.services.CatalogProto.AccessProfile.STORE_DETAILS;
import static org.hamcrest.Matchers.hasSize;


public class GetCatalogEntriesByPartNumbersTest extends AbstractRestTest {

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
    public void responseContainsCatalogIdsAndPartNumbers() {
        String ids[] = {"28669", "28670"};
        String responseString =
            given().
            when().
                get(new CatalogEntries().byPartNumbers(STORE_DETAILS, partNumbers)).
            then().
                assertThat()
                .body("name", hasSize(2))
                .body("manufacturer", hasSize(2))
                .body("rxRequired", hasSize(2))
                .body("keywords", hasSize(2))
                .log().ifError().
            extract().
                jsonPath().get().toString();
        softly.assertThat(responseString).contains("id=" + ids[0]);
        softly.assertThat(responseString).contains("id=" + ids[1]);
        softly.assertThat(responseString).contains("partNumber=" + partNumbers[0]);
        softly.assertThat(responseString).contains("partNumber=" + partNumbers[1]);
        softly.assertThat(responseString).contains("parentCatalogEntryId=" + ids[0]);
        softly.assertThat(responseString).contains("parentCatalogEntryId=" + ids[1]);
    }
}