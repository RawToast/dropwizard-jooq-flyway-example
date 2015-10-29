package training.dropwizard.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import training.dropwizard.example.health.TemplateHealthCheck;
import training.dropwizard.example.resources.SayingResource;

public class TrainingApplication extends Application<TrainingConfiguration> {
	public static void main(String[] args) throws Exception {
		new TrainingApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<TrainingConfiguration> bootstrap) {
	}

	@Override
	public void run(TrainingConfiguration configuration, Environment environment) {
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

		final SayingResource resource = new SayingResource(configuration.getTemplate(), configuration.getDefaultName());
		environment.jersey().register(resource);
	}
}
