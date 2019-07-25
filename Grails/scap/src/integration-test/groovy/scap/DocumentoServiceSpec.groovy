package scap

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DocumentoServiceSpec extends Specification {

    DocumentoService documentoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Documento(...).save(flush: true, failOnError: true)
        //new Documento(...).save(flush: true, failOnError: true)
        //Documento documento = new Documento(...).save(flush: true, failOnError: true)
        //new Documento(...).save(flush: true, failOnError: true)
        //new Documento(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //documento.id
    }

    void "test get"() {
        setupData()

        expect:
        documentoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Documento> documentoList = documentoService.list(max: 2, offset: 2)

        then:
        documentoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        documentoService.count() == 5
    }

    void "test delete"() {
        Long documentoId = setupData()

        expect:
        documentoService.count() == 5

        when:
        documentoService.delete(documentoId)
        sessionFactory.currentSession.flush()

        then:
        documentoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Documento documento = new Documento()
        documentoService.save(documento)

        then:
        documento.id != null
    }
}
