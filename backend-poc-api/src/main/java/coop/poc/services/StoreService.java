package coop.poc.services;

import coop.poc.api.forms.StoreForm;
import coop.poc.api.stores.Member;
import coop.poc.api.stores.Store;
import org.jooq.DSLContext;

import java.util.List;

/**
 * Service providing CRUD operations for stores.
 */
public interface StoreService {

    void persistStore(StoreForm store);

    Store fetchStore(int id);

    List<Store> findStore(String storeName);

    void deleteStore(int id);

    List<Store> listStores();

    Store update(int id, StoreForm store);

    List<Member> getLocalMembers(int id);
}
