package io.dropwizard;

import io.dropwizard.cli.MyCommand;
import io.dropwizard.cli.ShowTemplateName;
import io.dropwizard.health.TemplateHealthCheck;
import io.dropwizard.resources.SayingResource;
import io.dropwizard.resources.UserResource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.ScheduledExecutorService;

public class dropwizardApplication extends Application<dropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<dropwizardConfiguration> bootstrap) {
        bootstrap.addCommand(new MyCommand());
        bootstrap.addCommand(new ShowTemplateName());
    }

    @Override
    public void run(final dropwizardConfiguration configuration,
                    final Environment environment) {
        final SayingResource sayingResource = new SayingResource(
                configuration.getTemplate(), configuration.getDefaultName()
        );
        final UserResource userResource = new UserResource();
        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", templateHealthCheck);
        environment.jersey().register(sayingResource);
        environment.jersey().register(userResource);

        ScheduledExecutorService scheduledExecutorService = environment
                .lifecycle()
                .scheduledExecutorService("nameFormat")
                .build();
    }

}
