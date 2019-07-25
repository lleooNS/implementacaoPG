package scap

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SecretarioServiceSpec extends Specification {

    SecretarioService secretarioService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Secretario(...).save(flush: true, failOnError: true)
        //new Secretario(...).save(flush: true, failOnError: true)
        //Secretario secretario = new Secretario(...).save(flush: true, failOnError: true)
        //new Secretario(...).save(flush: true, failOnError: true)
        //new Secretario(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //secretario.id
    }

    void "test get"() {
        setupData()

        expect:
        secretarioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Secretario> secretarioList = secretarioService.list(max: 2, offset: 2)

        then:
        secretarioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        secretarioService.count() == 5
    }

    void "test delete"() {
        Long secretarioId = setupData()

        expect:
        secretarioService.count() == 5

        when:
        secretarioService.delete(secretarioId)
        sessionFactory.currentSession.flush()

        then:
        secretarioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Secretario secretario = new Secretario()
        secretarioService.save(secretario)

        then:
        secretario.id != null
    }
}
