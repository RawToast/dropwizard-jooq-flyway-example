package coop.poc;

import com.bendb.dropwizard.jooq.JooqFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicLong;

public class PocConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @NotNull
    private AtomicLong sayingCounter;

    @JsonProperty
    @NotNull
    private JooqFactory jooq = new JooqFactory(); // Defaults are acceptable

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();


    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    @JsonProperty
    public AtomicLong getSayingCounter() {
        return sayingCounter;
    }

    @JsonProperty
    public void setSayingCounter(AtomicLong sayingCounter) {
        this.sayingCounter = sayingCounter;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty
    public JooqFactory getJooq() {
        return jooq;
    }
}