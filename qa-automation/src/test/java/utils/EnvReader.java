package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvReader {
    private static Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}
