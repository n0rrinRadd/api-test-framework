package com.site.search.rest.restAssured;

import com.site.search.EnvironmentStore;
import com.site.search.PropertiesStore;
import site.commerce.catalog.services.CatalogProto;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AbstractRestTest {

        private final EnvironmentStore environmentStore = new EnvironmentStore();

        public String restTargetString() {
                return environmentStore.getBaseUrl(EnvironmentStore.Protocol.HTTP, EnvironmentStore.Host.AMUNRA) + "/api/v1";
        }

        private String getBrandsEndPoint(){
                return restTargetString() + "/brands";
        }
        private String getCatalogEntriesEndPoint(){
                return restTargetString() + "/entries";
        }
        private String getCatalogGroupEndPoint(){
                return restTargetString() + "/groups";
        }
        private String getSearchEndPoint(){
                return restTargetString() + "/search";
        }
        public SoftAssertions softly;

        @BeforeClass()
        public void Before(){
                this.softly = new SoftAssertions();
        }

        @AfterClass()
        public void After(){
                softly.assertAll();
        }

        public class Brands
        {
                public String ByBrandName(String name){
                        return getBrandsEndPoint() + "/" + name;
                }
        }

        public class CatalogEntries {

                public String byIds(CatalogProto.AccessProfile accessProfile, int... ids) {
                        return getCatalogEntriesEndPoint() + "/" + StringUtils.join(ArrayUtils.toObject(ids), ",") + "/ids?accessProfile=" + accessProfile.toString();
                }

                public String byPartNumbers(CatalogProto.AccessProfile accessProfile, int... partNumbers) {
                        return getCatalogEntriesEndPoint() + "/" + StringUtils.join(ArrayUtils.toObject(partNumbers), ",") + "/partNumbers?accessProfile=" + accessProfile.toString();
                }

                public String byGTIN(CatalogProto.AccessProfile accessProfile, String GTIN) {
                        return getCatalogEntriesEndPoint() + "/" + GTIN + "/gtin?accessProfile=" + accessProfile.toString();
                }

                public String byForeignSku(CatalogProto.AccessProfile accessProfile, String foreignSku) {
                        return getCatalogEntriesEndPoint() + "/" + foreignSku + "/foreignsku?accessProfile=" + accessProfile.toString();
                }
        }

        public class CatalogGroup {

                public String byIdentifier(String identifier) {
                        return byTopLevel() + "/" + identifier + "/identifier";
                }

                public String byId(String group, String id) {
                        return byTopLevel() + "/" + group + "/id?catalogId=" + id;
                }

                public String byTopLevel() {
                        return getCatalogGroupEndPoint();
                }
        }

        public class Search {
                public String bySearchTerm(String searchTerms) {
                        return getSearchEndPoint() + "?searchTerm=" + searchTerms;
                }

                public String byRelax(String... searchTerms) {
                        return getSearchEndPoint() + "/" + String.join("+", searchTerms) + "/relax";
                }

                public String byRelaxMaxTerms(int maxTerms, String... searchTerms) {
                        return getSearchEndPoint() + "/" + String.join("+", searchTerms) + "/relax?maxTerms=" + maxTerms;
                }
        }
}

