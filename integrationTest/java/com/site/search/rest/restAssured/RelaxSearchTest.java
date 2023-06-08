package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

public class RelaxSearchTest extends AbstractRestTest {

    private final String[] searchTerms = {"cat", "fish", "dog", "water", "bowl"};

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new Search().byRelax(searchTerms)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsSearchTermKeyValue() {
        given().
        when().
            get(new Search().byRelax(searchTerms)).
        then().
            assertThat()
            .body("searchTerm", notNullValue())
            .log().ifError();
    }

    @Test
    public void responseContainsMaxSearchTermsSize() {
        int maxTerms = 4;
        given().
        when().
            get(new Search().byRelaxMaxTerms(maxTerms, searchTerms)).
        then().
            assertThat()
            .body("searchTerm", hasSize(maxTerms))
            .log().ifError();
    }


}
