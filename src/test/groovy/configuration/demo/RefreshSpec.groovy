package configuration.demo

import io.micronaut.context.ApplicationContext
import io.micronaut.discovery.config.ConfigurationClient
import io.micronaut.discovery.consul.client.v1.ConsulClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Flowable
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class RefreshSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer,
            ['endpoints.refresh.sensitive': false]
    )

    @Shared
    ConsulClient client = embeddedServer.applicationContext.getBean(ConsulClient)

    void "test refreshing"() {
        given:
        RxHttpClient rxClient = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

        when:
        ValueAnnotation vAnn = embeddedServer.applicationContext.getBean(ValueAnnotation)

        then:
        vAnn.constructorVal == "not set"
        vAnn.val == "consul global"

        when:
        int rand = (Math.random() * 10).toInteger()
        Flowable.fromPublisher(client.putValue("config/application/foo.bar", "updated ${rand}".toString())).blockingFirst()
        rxClient.exchange(HttpRequest.POST("/refresh", new byte[0]), String).blockingFirst()


        then:
        noExceptionThrown()

        when:
        PollingConditions conditions = new PollingConditions(timeout: 3)

        then:
        conditions.eventually {
            assert embeddedServer.applicationContext.getBean(ValueAnnotation).val == "updated ${rand}".toString()
            assert embeddedServer.applicationContext.getBean(ConfigController).valueAnnotation.val == "updated ${rand}".toString()
            assert rxClient.exchange("/", String).blockingFirst().body() == "updated ${rand}".toString()
        }

        cleanup:
        Flowable.fromPublisher(client.putValue("config/application/foo.bar", "consul global")).blockingFirst()
        rxClient.close()
    }
}
