package configuration.demo

import io.micronaut.context.annotation.ConfigurationBuilder
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("building")
class BuildingProperties {

    @ConfigurationBuilder(prefixes = "with")
    Building.Builder builder = Building.builder()
}
