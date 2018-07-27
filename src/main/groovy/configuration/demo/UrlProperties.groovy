package configuration.demo

import io.micronaut.context.annotation.EachProperty
import io.micronaut.context.annotation.Parameter

@EachProperty(value = "services", primary = "default")
class UrlProperties {

    String host
    Integer port = null
    String username = null
    String password = null
    String scheme = "http"

    UrlProperties(@Parameter String name) {}

    String buildUrl() {
        StringBuilder sb = new StringBuilder(scheme)
        sb.append("://")
        if (username) {
            sb.append(username).append('@')
        }
        if (password) {
            sb.append(password).append(":")
        }
        sb.append(host)
        if (port) {
            sb.append(":").append(port)
        }
        sb.toString()
    }
}
