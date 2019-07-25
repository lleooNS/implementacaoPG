package scap

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MandatoServiceSpec extends Specification {

    MandatoService mandatoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Mandato(...).save(flush: true, failOnError: true)
        //new Mandato(...).save(flush: true, failOnError: true)
        //Mandato mandato = new Mandato(...).save(flush: true, failOnError: true)
        //new Mandato(...).save(flush: true, failOnError: true)
        //new Mandato(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //mandato.id
    }

    void "test get"() {
        setupData()

        expect:
        mandatoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Mandato> mandatoList = mandatoService.list(max: 2, offset: 2)

        then:
        mandatoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        mandatoService.count() == 5
    }

    void "test delete"() {
        Long mandatoId = setupData()

        expect:
        mandatoService.count() == 5

        when:
        mandatoService.delete(mandatoId)
        sessionFactory.currentSession.flush()

        then:
        mandatoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Mandato mandato = new Mandato()
        mandatoService.save(mandato)

        then:
        mandato.id != null
    }
}
