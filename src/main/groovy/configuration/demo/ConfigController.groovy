package configuration.demo

import io.micronaut.context.ApplicationContext
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/')
class ConfigController {

    final ApplicationContext ctx
    final ValueAnnotation valueAnnotation
    private final UrlConfiguration urlConfiguration
    private final List<UrlProperties> urlProperties
    private final Building building

    ConfigController(ValueAnnotation valueAnnotation,
                     ApplicationContext ctx,
                     List<UrlProperties> urlProperties,
                     UrlConfiguration urlConfiguration,
                     BuildingProperties buildingProperties) {
        this.building = buildingProperties.builder.build()
        this.urlProperties = urlProperties
        this.urlConfiguration = urlConfiguration
        this.valueAnnotation = valueAnnotation
        this.ctx = ctx
    }

    @Get(uri = '/direct', produces = MediaType.TEXT_PLAIN)
    String direct() {
        "Direct API: ${ctx.getProperty("foo.bar", String)}"
    }

    @Get(uri = '/value', produces = MediaType.TEXT_PLAIN)
    String value() {
        """
ValueAnnotation val: ${valueAnnotation.val}
ValueAnnotation constructorVal: ${valueAnnotation.constructorVal}
"""
    }

    @Get(uri = '/eachProperty', produces = MediaType.TEXT_PLAIN)
    String eachProperty() {
        urlProperties*.buildUrl().join(', ')
    }

    @Get(uri = '/configProperty', produces = MediaType.TEXT_PLAIN)
    String configProperty() {
        urlConfiguration.buildUrl()
    }

    @Get(uri = '/buildingProperty', produces = MediaType.TEXT_PLAIN)
    String buildingProperty() {
        "The ${building.name} in ${building.city} has ${building.floors} floors!"
    }
}
