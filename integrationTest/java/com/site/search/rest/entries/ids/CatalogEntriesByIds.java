package com.site.search.rest.entries.ids;

import com.site.search.rest.AbstractServletBaseConfiguration;
import com.site.search.v1.model.CatalogEntry;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogEntriesByIds extends AbstractServletBaseConfiguration {

  @Test
  void testCatalogEntriesReturned_whenRequestById() {
    Map<String, Object> params = new HashMap<>();
    String url = buildCatalogEntriesByIdsUrl(Lists.newArrayList(testCatalogEntryId), params);
    List<CatalogEntry> catalogEntries
      = httpClientContextHandler.sendGetRequestAndMapToClassList(url, CatalogEntry.class);
    Assertions.assertFalse(catalogEntries.isEmpty());
  }
}
