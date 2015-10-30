package coop.poc.services;

import coop.poc.api.forms.StoreForm;
import coop.poc.api.stores.Store;
import coop.poc.tables.records.MembersRecord;
import coop.poc.tables.records.StoresRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.UpdateConditionStep;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static coop.poc.Tables.MEMBERS;
import static coop.poc.Tables.STORES;
import static coop.poc.api.stores.Store.StoreBuilder;
import static coop.poc.util.SingletonCollector.singletonCollector;

public class JooqStoreService implements StoreService {

    private DSLContext jooq;

    private static final Function<StoresRecord, Store> CONVERT_STORES =
            storesRecord -> new StoreBuilder().withName(storesRecord.getName())
                                              .withStoreId(storesRecord.getStoreId())
                                              .withPostcode(storesRecord.getPostcode())
                                              .createStore();

    private Function<StoresRecord, Store> CONVERT_STORE = storesRecord -> new StoreBuilder().withName(storesRecord.getName())
                                                                                            .withStoreId(storesRecord.getStoreId())
                                                                                            .withPostcode(storesRecord.getPostcode())
                                                                                            .createStore();

    //private Function<StoreForm, StoresRecord> CREATE_STORE_RECORD = storeForm -> new StoresRecord(DSL.val(STORES.s)).

    public JooqStoreService(DSLContext jooqContext) {
        this.jooq = jooqContext;
    }

    @Override
    public void persistStore(StoreForm store) {

        StoresRecord record = new StoresRecord();
        record.setName(store.getName());
        record.setPostcode(store.getPostcode());
        jooq.executeInsert(record);

    }

    @Override
    public Store fetchStore(int id) {
        return jooq.selectFrom(STORES)
                   .where(STORES.STORE_ID.eq(id))
                   .fetch()
                   .stream()
                   .map(CONVERT_STORE).collect(singletonCollector());

    }

    @Override
    public List<Store> findStore(String storeName) {
        //Result<StoresRecord> storesRecords = context.selectFrom(STORES)
        SelectConditionStep<StoresRecord> storeRecords = jooq.selectFrom(STORES)
                                                             .where(STORES.NAME.contains(storeName));
        Result<StoresRecord> records = storeRecords.fetch();

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

        jooq.deleteFrom(STORES).where(STORES.STORE_ID.eq(id)).execute();
    }

    @Override
    public List<Store> listStores() {

        Result<StoresRecord> storesRecords = jooq.selectFrom(STORES).fetch();

        List<Store> stores = storesRecords.stream()
                                          .map(CONVERT_STORES)
                                          .collect(Collectors.toList());

        return stores;
    }
}
