package coop.poc.client.jooq;

import com.codahale.metrics.annotation.Timed;
import coop.poc.client.api.StoresDatastore;
import coop.poc.client.exception.PersistenceFailureException;
import coop.poc.client.exception.StorePersistenceException;
import coop.poc.tables.records.StoresRecord;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static coop.poc.Tables.STORES;

public class JooqStoresDatastore implements StoresDatastore{

    private final DSLContext jooq;
    private static Logger LOG = LoggerFactory.getLogger(JooqStoresDatastore.class);
    private static final int SUCCESS = 1;
    private static final int FAIL = 0;

    public JooqStoresDatastore (final DSLContext jooqContext){
        this.jooq = jooqContext;
    }

    @Timed
    @Override
    public void createStore(String storeName, String postcode) throws PersistenceFailureException {
        StoresRecord record = new StoresRecord();
        record.setName(storeName);
        record.setPostcode(postcode);

        int didStore = record.store();

        if (didStore == FAIL) {
            LOG.warn("Failed to persist store to database storeId={} storeName={} postCode={}",
                     record.getStoreId(), storeName, postcode);
            throw new StorePersistenceException(record.getStoreId());
        }
    }

    @Timed
    @Override
    public StoresRecord fetchStore(int storeId) {
        return null;
    }

    @Timed
    @Override
    public Result<StoresRecord> fetchStores() {
        SelectWhereStep<StoresRecord> query = jooq.selectFrom(STORES);
        logRequest(query.getSQL());

        return query.fetch();
    }

    @Timed
    @Override
    public Result<StoresRecord> fetchStoresWithNameContaining(String name) {
        SelectConditionStep<StoresRecord> query = jooq.selectFrom(STORES)
                                                      .where(STORES.NAME.contains(name));
        logRequest(query.getSQL());

        return query.fetch();
    }

    @Timed
    @Override
    public boolean deleteStore(int storeId) {
        return false;
    }

    @Override
    public void updateStore(int storeId, String storeName, String postCode) throws PersistenceFailureException {

        UpdateConditionStep<StoresRecord> query = jooq.update(STORES)
                                                      .set(STORES.NAME, storeName)
                                                      .set(STORES.POSTCODE, postCode)
                                                      .where(STORES.STORE_ID.eq(storeId));

        logRequest(query.getSQL());

        int execute = query.execute();
        if (execute == FAIL) {
            LOG.warn("Failed to persist store to database storeId={} storeName={} postCode={}",
                     storeId, storeName, postCode);
            throw new StorePersistenceException(storeId);
        }
    }

    private void logRequest(String query){
        LOG.debug("Executing SQL {}", query);
    }

}
