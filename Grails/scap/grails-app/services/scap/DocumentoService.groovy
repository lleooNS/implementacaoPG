package scap

import grails.gorm.services.Service

@Service(Documento)
interface DocumentoService {

    Documento get(Serializable id)

    List<Documento> list(Map args)

    Long count()

    void delete(Serializable id)

    Documento save(Documento documento)

}