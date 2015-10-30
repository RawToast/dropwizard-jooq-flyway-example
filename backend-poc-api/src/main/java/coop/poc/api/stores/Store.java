package coop.poc.api.stores;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Store JSON representation
 */
public class Store {

    public Store (StoreBuilder builder){
        this.storeId = builder.storeId;
        this.name = builder.name;
        this.postcode = builder.postcode;
    }

    @JsonCreator
    public Store (String storeId, String name, String postcode){
        this.storeId = storeId;
        this.name = name;
        this.postcode = postcode;
    }

    @JsonProperty
    @NotNull
    private String storeId;

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

    public String getStoreId() {
        return storeId;
    }

    public class StoreBuilder {
        private String storeId;
        private String name;
        private String postcode;

        public StoreBuilder withStoreId(String storeId) {
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
