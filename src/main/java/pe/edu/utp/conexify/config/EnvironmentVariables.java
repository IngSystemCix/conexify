package pe.edu.utp.conexify.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;

public class EnvironmentVariables {
    private static final Dotenv ENV = Dotenv.configure().load();
    public static final String EMAIL = ENV.get("GMAIL_EMAIL");
    public static final String APP_CODE = ENV.get("GMAIL_APP_PASS");
    public static final String HOST_NAME = ENV.get("GMAIL_HOSTNAME");
    public static final int SMTP_PORT = Integer.parseInt(ENV.get("GMAIL_SMTP_PORT"));
    public static final Duration SOCKET_TIMEOUT = Duration.ofSeconds(20);
}
