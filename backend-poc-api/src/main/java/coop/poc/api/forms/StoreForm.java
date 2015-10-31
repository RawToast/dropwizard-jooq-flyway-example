package coop.poc.api.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.binding.StringFormatter;
import coop.poc.api.stores.Store;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class StoreForm {

    @JsonProperty
    private String name;

    @NotNull
    @Length(min=5, max=8)
    @JsonProperty
    private String postcode;

    private StoreForm(){
        // Jackson constructor
    }

    public StoreForm(StoreFormBuilder builder){
        this.name = builder.name;
        this.postcode = builder.postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getName() {
        return name;
    }

    @JsonProperty
    private void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString()
    {
        return StringFormatter.format("type=%s name=%s postcode=%s", StoreForm.class, name, postcode).getValue();
    }


    public class StoreFormBuilder {

        private String name;
        private String postcode;

        public StoreFormBuilder withName(String name){
            this.name = name;
            return this;
        }

        public StoreFormBuilder withPostcode(String postcode){
            this.postcode = postcode;
            return this;
        }
    }

}
