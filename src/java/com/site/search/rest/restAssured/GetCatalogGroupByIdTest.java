package com.site.search.rest.restAssured;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

public class GetCatalogGroupByIdTest extends AbstractRestTest {

    private final String group = "6452";
    private final String id = "1007";

    @Test
    public void responseStatusCodeIs200() {
        given().
        when().
            get(new CatalogGroup().byId(group, id)).
        then().
            assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void responseContainsCatalogIdAndGroupId() {
        String catalogGroupString =
            given().
            when().
                    get(new CatalogGroup().byId(group, id)).
            then().
                    assertThat()
                    .body("catalogId", equalTo(Integer.parseInt(id)))
                    .body("catalogGroup.published", notNullValue())
                    .body("catalogGroup.thumbnail", notNullValue())
                    .body("breadcrumb.value", hasSize(2))
                    .body("breadcrumb.label", hasSize(2))
                    .log().ifError().
            extract().
                    jsonPath().get("catalogGroup").toString();
        softly.assertThat(catalogGroupString).contains("id=" + group);
        softly.assertThat(catalogGroupString).contains("identifier=b-arm-hammer");
        softly.assertThat(catalogGroupString).contains("name=Arm & Hammer");
        softly.assertThat(catalogGroupString).contains("ownerId=1");
        softly.assertThat(catalogGroupString).contains("shortDescription=Arm & Hammer");
    }
}
