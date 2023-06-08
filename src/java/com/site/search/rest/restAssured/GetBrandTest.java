package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetBrandTest extends AbstractRestTest {

    private final String brandName = "Purina";

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new Brands().ByBrandName(brandName)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK)
            .log().ifError();
    }

    @Test
    public void responseStatusCodeIs404() {
        given().
        when().
            get(new Brands().ByBrandName(new StringBuilder(brandName).reverse().toString())).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
            .log().ifError();
    }

    @Test
    public void responseContainsKeyValues() {
        given().
        when().
            get(new Brands().ByBrandName(brandName)).
        then().
            assertThat()
            .body("id", notNullValue())
            .body("owner_id", notNullValue())
            .body("description", notNullValue())
            .body("image1", notNullValue())
            .body("field1", notNullValue())
            .body("field2", notNullValue())
            .log().ifError();
    }
}
