package coop.poc;

import com.bendb.dropwizard.jooq.JooqBundle;
import com.bendb.dropwizard.jooq.JooqFactory;
import coop.poc.resources.StoreResource;
import coop.poc.services.IncrementingSayingService;
import coop.poc.services.JooqStoreService;
import coop.poc.services.SayingService;
import coop.poc.services.StoreService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import coop.poc.health.TemplateHealthCheck;
import coop.poc.resources.SayingResource;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;


public class PocApplication extends Application<PocConfiguration> {
    public static void main(String[] args) throws Exception {
        new PocApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PocConfiguration> bootstrap) {
        // ...
        bootstrap.addBundle(new JooqBundle<PocConfiguration>() {

            /**
             * Required override to define default DataSourceFactory.
             */
            @Override
            public DataSourceFactory getDataSourceFactory(PocConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public JooqFactory getJooqFactory(PocConfiguration configuration) {
                return configuration.getJooq();
            }
        });
    }

    @Override
    public void run(PocConfiguration configuration, Environment environment) {
        registerHealthChecks(configuration, environment);
        registerResources(configuration, environment);
    }

    private void registerResources(PocConfiguration configuration, Environment environment) {
        final SayingService sayingService = new IncrementingSayingService(configuration);
        final SayingResource resource = new SayingResource(sayingService);

        DSLContext using = DSL.using(configuration.getDataSourceFactory().getUrl(), configuration.getDataSourceFactory().getUser(),
                                     configuration.getDataSourceFactory().getPassword());
        final StoreService storeService = new JooqStoreService(using);



        final StoreResource storeResource = new StoreResource(storeService);

        environment.jersey().register(resource);
        environment.jersey().register(storeResource);
    }


    public void registerHealthChecks(PocConfiguration configuration, Environment environment) {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}

