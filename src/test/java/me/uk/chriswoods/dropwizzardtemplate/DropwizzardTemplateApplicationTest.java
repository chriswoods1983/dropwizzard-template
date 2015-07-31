package me.uk.chriswoods.dropwizzardtemplate;

import com.codahale.metrics.health.HealthCheckRegistry;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import me.uk.chriswoods.dropwizzardtemplate.resources.DropwizzardTemplateResource;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Created by chris on 31/07/15.
 */
public class DropwizzardTemplateApplicationTest{
    private final Environment environment = mock(Environment.class);
    private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
    private final HealthCheckRegistry healthCheckRegistry = mock(HealthCheckRegistry.class);
    private final DropwizzardTemplateApplication application = new DropwizzardTemplateApplication();
    private final DropwizzardTemplateConfiguration config = new DropwizzardTemplateConfiguration();

    @Before
    public void setup() throws Exception {
        config.setTemplate("Hello, %s!");
        config.setDefaultName("Stranger");
        when(environment.healthChecks()).thenReturn(healthCheckRegistry);
        when(environment.jersey()).thenReturn(jersey);
    }

    @Test
    public void buildsAThingResource() throws Exception {
        application.run(config, environment);

        verify(jersey).register(isA(DropwizzardTemplateResource.class));
    }
}