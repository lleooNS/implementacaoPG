package scap

import grails.gorm.services.Service

@Service(Secretario)
interface SecretarioService {

    Secretario get(Serializable id)

    List<Secretario> list(Map args)

    Long count()

    void delete(Serializable id)

    Secretario save(Secretario secretario)

}