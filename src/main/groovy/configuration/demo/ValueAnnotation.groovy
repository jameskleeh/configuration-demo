package configuration.demo

import io.micronaut.context.annotation.Value
import io.micronaut.runtime.context.scope.Refreshable

@Refreshable
class ValueAnnotation {

    @Value('${foo.bar}')
    String val

    final String constructorVal

    ValueAnnotation(@Value('${foo.baz:not set}') String constructorVal) {
        this.constructorVal = constructorVal
    }
}
