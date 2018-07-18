package configuration.demo

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.EachProperty
import io.micronaut.context.annotation.Parameter

@EachProperty(value = "services", primary = "default")
class UrlProperties {
    String host
    Integer port
    String username
    String password
    String scheme

    UrlProperties(@Parameter String name) {}
}
