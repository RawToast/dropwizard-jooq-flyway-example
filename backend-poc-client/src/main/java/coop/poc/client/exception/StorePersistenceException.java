package coop.poc.client.exception;

public class StorePersistenceException extends PersistenceFailureException {
    public StorePersistenceException(int storeId) {
        super("Failed to persist store with storeId=" + storeId);
    }
}
