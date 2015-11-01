package coop.poc.client.util;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CoopCollectors {

    public static <T> Collector<T, ?, T> singletonCollector() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
                                           );
    }
}

