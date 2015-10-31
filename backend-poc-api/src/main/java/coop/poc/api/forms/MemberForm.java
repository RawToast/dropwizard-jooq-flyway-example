package coop.poc.api.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import coop.poc.api.stores.Store;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class MemberForm {

    @JsonProperty
    private String name;

    @NotNull
    @Length(min=5, max=8)
    @JsonProperty
    private String postcode;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private int favouriteStore;

    private MemberForm(){
        // Jackson constructor
    }

    public MemberForm(MemberFormBuilder builder){
        this.name = builder.name;
        this.postcode = builder.postcode;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.favouriteStore = builder.favouriteStore;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getFavouriteStore() {
        return favouriteStore;
    }

    @JsonProperty
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    private void setFavouriteStore(int favouriteStore) {
        this.favouriteStore = favouriteStore;
    }

    @JsonProperty
    private void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }



    public class MemberFormBuilder {

        private String name;
        private String postcode;
        private String firstName;
        private String lastName;
        private int favouriteStore;

        public MemberFormBuilder withName(final String name){
            this.name = name;
            return this;
        }

        public MemberFormBuilder withPostcode(final String postcode){
            this.postcode = postcode;
            return this;
        }

        public MemberFormBuilder withFirstName(final String firstName){
            this.firstName = firstName;
            return this;
        }

        public MemberFormBuilder withLastName(final String lastName){
            this.lastName = lastName;
            return this;
        }

        public MemberFormBuilder withFavouriteStore(int favouriteStoreId){
            this.favouriteStore = favouriteStoreId;
            return this;
        }

        public MemberFormBuilder withFavouriteStore(final Store store){
            this.favouriteStore = store.getStoreId();
            return this;
        }
    }
}
