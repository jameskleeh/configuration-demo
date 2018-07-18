package configuration.demo

import io.micronaut.context.ApplicationContext
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/')
class ConfigController {

    final ApplicationContext ctx
    final ValueAnnotation valueAnnotation

    ConfigController(ValueAnnotation valueAnnotation, ApplicationContext ctx) {
        this.valueAnnotation = valueAnnotation
        this.ctx = ctx
    }

    @Get('/')
    String index() {
        valueAnnotation.val
    }
}
