package scap

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ParecerServiceSpec extends Specification {

    ParecerService parecerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Parecer(...).save(flush: true, failOnError: true)
        //new Parecer(...).save(flush: true, failOnError: true)
        //Parecer parecer = new Parecer(...).save(flush: true, failOnError: true)
        //new Parecer(...).save(flush: true, failOnError: true)
        //new Parecer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //parecer.id
    }

    void "test get"() {
        setupData()

        expect:
        parecerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Parecer> parecerList = parecerService.list(max: 2, offset: 2)

        then:
        parecerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        parecerService.count() == 5
    }

    void "test delete"() {
        Long parecerId = setupData()

        expect:
        parecerService.count() == 5

        when:
        parecerService.delete(parecerId)
        sessionFactory.currentSession.flush()

        then:
        parecerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Parecer parecer = new Parecer()
        parecerService.save(parecer)

        then:
        parecer.id != null
    }
}
