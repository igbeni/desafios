package br.com.igbeni.strings;

import br.com.igbeni.strings.resource.StringResource;
import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * Main entry for the application.
 *
 * @author Iggor Alves
 */
public class StringsApplication extends Application<StringsConfiguration> {

    public static void main(String[] args) throws Exception {
        new StringsApplication().run(args);
    }

    /**
     * When the application runs, this is called after the {@link Bundle}s are run. Override it to add
     * providers, resources, etc. for your application.
     *
     * @param configuration the parsed {@link Configuration} object
     * @param environment   the application's {@link Environment}
     * @throws Exception if something goes wrong
     */
    @Override
    public void run(StringsConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new StringResource());
    }
}
