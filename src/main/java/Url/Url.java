package Url;

import ResponseModels.Login;
import ResponseModels.LoginStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Url {

    private static Logger logger = LoggerFactory.getLogger(Url.class.getSimpleName());
    public static Url instance;
    private static int instanceCount = 0;

    private class Http {
        String url;
        Class response;
    }

    public Url() {
        instanceCount ++;
        if (instanceCount > 1) {
            logger.warn("Multiple Url instances created. Keep only 1 instance so that Url.instance points correctly");
        }
        Url.instance = this;
        logger.debug("Url.instance pointing to a new Url instance");
    }

    private Map<String, Http> http;
    private BaseUrl base_url;

    public String get(String key) throws UrlNotDefinedException {
        validateUrlNotNull(this.base_url.getUrl());
        validateNotNull(key, this.http.get(key));
        String url = this.http.get(key).url;
        validateUrlNotNull(url);
        return this.base_url.getUrl().concat(url);
    }

    public Class getResponseClass(String key) {
        validateNotNull(key, this.http.get(key));
        return this.http.get(key).response;
    }

    public void changeBaseUrl(BaseUrl newBaseUrl) {
        logger.warn("Changing baseUrl to: %s", newBaseUrl.name());
        this.base_url = newBaseUrl;
    }

    public void updateUrl(String urlName, String url, Class responseClass) {
        logger.warn("Url %s is updated. New urlString: %s, responseClass: %s", urlName, url, responseClass.getSimpleName());
        Http http = new Http();
        http.url = url;
        http.response = responseClass;

        this.http.put(urlName, http);
    }

    private void validateUrlNotNull(String url) throws UrlNotDefinedException {
        if (url == null || url.length() == 0) {
            throw new UrlNotDefinedException(url);
        }
    }

    private void validateNotNull(String key, Http http) {
        if (http == null) {
            throw new NullPointerException(String.format("Object associated with key %s is null/empty", key));
        }
    }

    public static Url getDefault() {
        Url url = new Url();

        url.updateUrl("login", "/login", Login.class);
        url.updateUrl("logout", "/logout", Login.class);
        url.updateUrl("loggedInUser", "/getLoggedInUser", LoginStatus.class);

        url.changeBaseUrl(BaseUrl.REMOTE);

        return url;
    }
}
