package coop.poc.services;

import coop.poc.api.stores.Store;

import java.util.List;

/**
 * Service providing CRUD operations for stores.
 */
public interface StoreService {

    void persistStore(Store store);

    void fetchStore(String id);

    List<Store> findStore(String storeName);

    void deleteStore(String id);

    List<Store> listStores();

}
