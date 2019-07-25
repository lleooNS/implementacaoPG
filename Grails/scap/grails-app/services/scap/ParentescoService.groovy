package scap

import grails.gorm.services.Service

@Service(Parentesco)
interface ParentescoService {

    Parentesco get(Serializable id)

    List<Parentesco> list(Map args)

    Long count()

    void delete(Serializable id)

    Parentesco save(Parentesco parentesco)

}