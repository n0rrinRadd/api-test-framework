package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class SearchTest extends AbstractRestTest {

    private final String queryParam = "search";
    private final String searchTerm = "purina";
    private final Integer catalogId = 1004;

    @Test
    public void responseStatusCodeIs200() {
        given().
            queryParam(queryParam).
        when().
            get(new Search().bySearchTerm(searchTerm)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsRecordSetTotalAndCatalogId() {
        String searchEntriesList=
            given().
                queryParam(queryParam).
            when().
                get(new Search().bySearchTerm(searchTerm)).
            then().
                assertThat()
                .body("recordSetTotal", notNullValue())
                .body("catalogId", equalTo(catalogId)).
            extract().
                jsonPath().getList("catalogEntry").toString();
        softly.assertThat(searchEntriesList).contains("id=");
        softly.assertThat(searchEntriesList).contains("type=");
        softly.assertThat(searchEntriesList).contains("manufacturer=");
        softly.assertThat(searchEntriesList).contains("gtin=");
        softly.assertThat(searchEntriesList).contains("keywords=");
        softly.assertThat(searchEntriesList).contains("name=");
    }

    @Test(enabled = false) //SEA-3637
    public void responseContainsFacets() {
        String searchEntriesList=
            given().
                queryParam(queryParam).
            when().
                get(new Search().bySearchTerm(searchTerm)).
            then().
                assertThat()
                .body("recordSetTotal", notNullValue())
                .body("catalogId", equalTo(catalogId))
                .log().ifError().
            extract().
                jsonPath().getList("facet").toString();
        softly.assertThat(searchEntriesList).contains("value=categoryId");
        softly.assertThat(searchEntriesList).contains("value=brand_facet");
        softly.assertThat(searchEntriesList).contains("value=ProductWeightPounds");
        softly.assertThat(searchEntriesList).contains("value=AssemblyRequired");
        softly.assertThat(searchEntriesList).contains("value=price_d");
        softly.assertThat(searchEntriesList).contains("value=rating");
    }


}
