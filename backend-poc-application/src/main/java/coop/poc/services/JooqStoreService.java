package coop.poc.services;

import coop.poc.api.forms.StoreForm;
import coop.poc.api.stores.Store;
import coop.poc.client.api.StoresDatastore;
import coop.poc.client.exception.PersistenceFailureException;
import coop.poc.tables.records.StoresRecord;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.InternalServerErrorException;
import java.util.List;
import java.util.stream.Collectors;

import static coop.poc.services.util.Functions.CONVERT_STORE;
import static coop.poc.services.util.Functions.CONVERT_STORES;

public class JooqStoreService implements StoreService {

    private static final Logger LOG = LoggerFactory.getLogger(JooqStoreService.class);
    private final StoresDatastore datastore;


    public JooqStoreService(StoresDatastore storesDatastore) {
        this.datastore = storesDatastore;
    }

    @Override
    public void persistStore(StoreForm store) {
        LOG.info("Creating new store record with {}", store);

        try {
            datastore.createStore(store.getName(), store.getPostcode());
        } catch (PersistenceFailureException e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @Override
    public Store fetchStore(int id) {

        StoresRecord storeRecord = datastore.fetchStore(id);

        return CONVERT_STORE.apply(storeRecord);

    }

    @Override
    public List<Store> findStore(String storeName) {

        Result<StoresRecord> records = datastore.fetchStoresWithNameContaining(storeName);

        return records
                .stream()
                .map(CONVERT_STORES)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStore(int id) {
        //jooq.update(MEMBERS)
        //    .set(MEMBERS.FAVOURITE_STORE, (Integer) null)
        //    .where(MEMBERS.FAVOURITE_STORE.eq(id));

        datastore.deleteStore(id);
    }

    @Override
    public List<Store> listStores() {

        Result<StoresRecord> storesRecords = datastore.fetchStores();

        List<Store> stores = storesRecords.stream()
                                          .map(CONVERT_STORES)
                                          .collect(Collectors.toList());

        return stores;
    }

    @Override
    public Store update(int id, StoreForm storeForm) {
        LOG.info("Updating store with id {} with {}", storeForm);

        try {
            datastore.updateStore(id, storeForm.getName(), storeForm.getPostcode());
        } catch (PersistenceFailureException e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }

        return fetchStore(id);
    }

}
