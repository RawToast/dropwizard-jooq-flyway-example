package coop.poc.api.stores;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Store JSON representation
 */
public class Store {

    public Store (StoreBuilder builder){
        this.setStoreId(builder.storeId);
        this.setName(builder.name);
        this.setPostcode(builder.postcode);
    }

    @JsonProperty
    @NotNull
    private int storeId;

    @JsonProperty
    private String name;

    @NotNull
    @Length(min=5, max=8)
    @JsonProperty
    private String postcode;


    public String getPostcode() {
        return postcode;
    }

    public String getName() {
        return name;
    }

    public int getStoreId() {
        return storeId;
    }

    @JsonProperty
    private void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @JsonProperty
    private void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public static class StoreBuilder {
        private int storeId;
        private String name;
        private String postcode;

        public StoreBuilder withStoreId(int storeId) {
            this.storeId = storeId;
            return this;
        }

        public StoreBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public StoreBuilder withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Store createStore() {
            return new Store(this);
        }
    }
}
