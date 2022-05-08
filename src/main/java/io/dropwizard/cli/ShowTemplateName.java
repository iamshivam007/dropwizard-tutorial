package io.dropwizard.cli;

import io.dropwizard.dropwizardConfiguration;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;

public class ShowTemplateName extends ConfiguredCommand<dropwizardConfiguration> {

    public ShowTemplateName() {
        super("showTemplateName", "Show template field value from config file");
    }

    @Override
    protected void run(Bootstrap<dropwizardConfiguration> bootstrap, Namespace namespace, dropwizardConfiguration configuration) {
        System.out.println("Template name is: " + configuration.getTemplate());
    }
}
