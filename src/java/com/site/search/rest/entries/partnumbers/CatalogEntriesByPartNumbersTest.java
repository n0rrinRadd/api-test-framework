package com.site.search.rest.entries.partnumbers;

import com.site.search.rest.AbstractServletBaseConfiguration;
import com.site.search.v1.model.CatalogEntry;
import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogEntriesByPartNumbersTest extends AbstractServletBaseConfiguration {

  @Test(description = "Test catalog entry returns 200")
  void testCatalogEntriesReturns200_whenRequestByPartNumber() throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("accessProfile", "STORE_DETAILS");
    String url = buildCatalogEntriesByPartNumbersUrl(
      Lists.newArrayList(testCatalogEntryPartNumber), params);
    List<CatalogEntry> catalogEntries
      = httpClientContextHandler.sendGetRequestAndMapToClassList(url, CatalogEntry.class);
    Assert.assertFalse(catalogEntries.isEmpty(), "Catalog Entries returned a response");
  }
}
