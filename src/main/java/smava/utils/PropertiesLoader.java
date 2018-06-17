package smava.utils;

import ru.qatools.properties.PropertyLoader;

public class PropertiesLoader {
    private PropertiesLoader() {
    }

    private static final Config config = init();

    public static Config getConfig() {
        if (config != null) {
            return config;
        }
        return init();
    }

    private static Config init() {
        return PropertyLoader.newInstance()
                .populate(Config.class);
    }
}
