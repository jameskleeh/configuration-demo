package configuration.demo

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("url")
class UrlConfiguration {

    String host
    Integer port
    String username
    String password
    String scheme = "http"

    String buildUrl() {
        "${scheme}://${username}@${password}:${host}:${port}"
    }

}
