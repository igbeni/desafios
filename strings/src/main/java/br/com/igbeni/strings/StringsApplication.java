package br.com.igbeni.strings;

import br.com.igbeni.strings.resource.StringResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class StringsApplication extends Application<StringsConfiguration> {

    public static void main(String[] args) throws Exception {
        new StringsApplication().run(args);
    }

    @Override
    public void run(StringsConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new StringResource());
    }
}
