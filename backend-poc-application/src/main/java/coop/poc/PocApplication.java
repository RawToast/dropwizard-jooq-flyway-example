package coop.poc;

import coop.poc.services.IncrementingSayingService;
import coop.poc.services.SayingService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import coop.poc.health.TemplateHealthCheck;
import coop.poc.resources.SayingResource;


public class PocApplication extends Application<PocConfiguration> {
    public static void main(String[] args) throws Exception {
        new PocApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PocConfiguration> bootstrap) {
    }

    @Override
    public void run(PocConfiguration configuration, Environment environment) {

        registerHealthChecks(configuration, environment);
        registerResources(configuration, environment);
    }

    private void registerResources(PocConfiguration configuration, Environment environment) {
        final SayingService sayingService = new IncrementingSayingService(configuration);
        final SayingResource resource = new SayingResource(sayingService);

        environment.jersey().register(resource);
    }


    public void registerHealthChecks(PocConfiguration configuration, Environment environment){
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }
}
