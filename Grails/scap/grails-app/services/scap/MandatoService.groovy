package scap

import grails.gorm.services.Service

@Service(Mandato)
interface MandatoService {

    Mandato get(Serializable id)

    List<Mandato> list(Map args)

    Long count()

    void delete(Serializable id)

    Mandato save(Mandato mandato)

}