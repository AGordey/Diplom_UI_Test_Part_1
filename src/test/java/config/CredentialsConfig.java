package config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:config/credentials.properties")
public interface CredentialsConfig extends Config {
    String loginForSelenoid();

    String passwordForSelenoid();

    String login21vek();

    String password21vek();
}
