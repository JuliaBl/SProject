package smava.utils;
import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.Classpath("config.properties")
public interface Config {
    @Property("url")
    String getUrl();
    @Property("remote_url")
    String getRemoteUrl();
    @Property("is_remote")
    String isRemote();
}
