package scap

import grails.gorm.services.Service

@Service(Parecer)
interface ParecerService {

    Parecer get(Serializable id)

    List<Parecer> list(Map args)

    Long count()

    void delete(Serializable id)

    Parecer save(Parecer parecer)

}