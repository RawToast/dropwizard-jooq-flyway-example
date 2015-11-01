package coop.poc.client.api;

import coop.poc.client.exception.PersistenceFailureException;
import coop.poc.tables.records.StoresRecord;
import org.jooq.Result;

import java.io.IOException;

public interface StoresDatastore {

    void createStore(String storeName, String postcode) throws PersistenceFailureException;

    void updateStore(int storeId, String storeName, String postCode) throws PersistenceFailureException;

    StoresRecord fetchStore(int storeId);

    Result<StoresRecord> fetchStores();

    Result<StoresRecord> fetchStoresWithNameContaining(String name);

    boolean deleteStore(int storeId);


}


