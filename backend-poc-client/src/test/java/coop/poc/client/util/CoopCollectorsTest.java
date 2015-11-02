package coop.poc.client.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoopCollectorsTest {

    @Test
    public void testSingletonCollectorReturnsSingleItem(){
        // WHEN - given list with multiple items
        List<String> stringList = new ArrayList<>();
        stringList.add("abc");

        // THEN - stream and collect a single element
        String collectedString = stringList.stream()
                                           .collect(CoopCollectors.singletonCollector());

        assertThat(collectedString).describedAs("String was empty/null").isNotNull();

    }

    @Test(expected=IllegalStateException.class)
    public void testSingletonCollectorThrowsExceptionIfMultipleItemsExist(){
        // WHEN - given list with multiple items
        List<String> stringList = new ArrayList<>();
        stringList.add("abc");
        stringList.add("def");
        stringList.add("123");

        // THEN - stream and collect a single element
        stringList.stream()
                  .collect(CoopCollectors.singletonCollector());

    }

}
