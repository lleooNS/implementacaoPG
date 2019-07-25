package scap

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RelatorServiceSpec extends Specification {

    RelatorService relatorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Relator(...).save(flush: true, failOnError: true)
        //new Relator(...).save(flush: true, failOnError: true)
        //Relator relator = new Relator(...).save(flush: true, failOnError: true)
        //new Relator(...).save(flush: true, failOnError: true)
        //new Relator(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //relator.id
    }

    void "test get"() {
        setupData()

        expect:
        relatorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Relator> relatorList = relatorService.list(max: 2, offset: 2)

        then:
        relatorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        relatorService.count() == 5
    }

    void "test delete"() {
        Long relatorId = setupData()

        expect:
        relatorService.count() == 5

        when:
        relatorService.delete(relatorId)
        sessionFactory.currentSession.flush()

        then:
        relatorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Relator relator = new Relator()
        relatorService.save(relator)

        then:
        relator.id != null
    }
}
