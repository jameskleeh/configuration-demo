package configuration.demo

import io.micronaut.context.annotation.Value
import io.micronaut.runtime.context.scope.Refreshable

import javax.inject.Inject

@Refreshable
class ValueAnnotation {

    private String val
    final String constructorVal

    ValueAnnotation(@Value('${foo.baz:a.b:b.c:c.d:d.e:e.f:not set}') String constructorVal) {
        this.constructorVal = constructorVal
    }

    String getVal() {
        return val
    }

    @Inject
    void setVal(@Value('${foo.bar}') String val) {
        this.val = val
    }
}
