package scap

import grails.gorm.services.Service

@Service(Relator)
interface RelatorService {

    Relator get(Serializable id)

    List<Relator> list(Map args)

    Long count()

    void delete(Serializable id)

    Relator save(Relator relator)

}