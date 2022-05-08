package io.dropwizard;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import io.dropwizard.Application;
import io.dropwizard.health.TemplateHealthCheck;
import io.dropwizard.resources.SayingResource;
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
        // TODO: application initialization
    }

    @Override
    public void run(final dropwizardConfiguration configuration,
                    final Environment environment) {
        final SayingResource sayingResource = new SayingResource(
                configuration.getTemplate(), configuration.getDefaultName()
        );
        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", templateHealthCheck);
        environment.jersey().register(sayingResource);

        ScheduledExecutorService scheduledExecutorService = environment
                .lifecycle()
                .scheduledExecutorService("nameFormat")
                .build();
    }

}
