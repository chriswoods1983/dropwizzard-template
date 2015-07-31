package me.uk.chriswoods.dropwizzardtemplate;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.uk.chriswoods.dropwizzardtemplate.health.TemplateHealthCheck;
import me.uk.chriswoods.dropwizzardtemplate.resources.DropwizzardTemplateResource;

/**
 * Created by chris on 31/07/15.
 */
public class DropwizzardTemplateApplication extends Application<DropwizzardTemplateConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropwizzardTemplateApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizzardTemplateConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropwizzardTemplateConfiguration configuration,
                    Environment environment) {
        final DropwizzardTemplateResource resource = new DropwizzardTemplateResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
