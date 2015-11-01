package coop.poc.services.util;

import coop.poc.api.stores.Store;
import coop.poc.tables.records.StoresRecord;

import java.util.function.Function;

public class Functions
{
    public static final Function<StoresRecord, Store> CONVERT_STORE = storesRecord ->
            new Store.StoreBuilder().withName(storesRecord.getName())
                                    .withStoreId(storesRecord.getStoreId())
                                    .withPostcode(storesRecord.getPostcode())
                                    .createStore();

    public static final Function<StoresRecord, Store> CONVERT_STORES =
            storesRecord -> new Store.StoreBuilder().withName(storesRecord.getName())
                                                    .withStoreId(storesRecord.getStoreId())
                                                    .withPostcode(storesRecord.getPostcode())
                                                    .createStore();


}
