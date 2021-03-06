package io.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "test");
        if (!saying.contains("test")) {
            return Result.unhealthy("template does not include a name");
        }
        return Result.healthy();
    }
}
