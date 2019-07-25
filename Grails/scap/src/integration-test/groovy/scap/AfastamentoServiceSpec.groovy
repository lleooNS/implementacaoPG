package scap

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AfastamentoServiceSpec extends Specification {

    AfastamentoService afastamentoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Afastamento(...).save(flush: true, failOnError: true)
        //new Afastamento(...).save(flush: true, failOnError: true)
        //Afastamento afastamento = new Afastamento(...).save(flush: true, failOnError: true)
        //new Afastamento(...).save(flush: true, failOnError: true)
        //new Afastamento(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //afastamento.id
    }

    void "test get"() {
        setupData()

        expect:
        afastamentoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Afastamento> afastamentoList = afastamentoService.list(max: 2, offset: 2)

        then:
        afastamentoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        afastamentoService.count() == 5
    }

    void "test delete"() {
        Long afastamentoId = setupData()

        expect:
        afastamentoService.count() == 5

        when:
        afastamentoService.delete(afastamentoId)
        sessionFactory.currentSession.flush()

        then:
        afastamentoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Afastamento afastamento = new Afastamento()
        afastamentoService.save(afastamento)

        then:
        afastamento.id != null
    }
}
