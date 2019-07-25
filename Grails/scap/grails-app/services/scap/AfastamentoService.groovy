package scap

import grails.gorm.services.Service

@Service(Afastamento)
interface AfastamentoService {

    Afastamento get(Serializable id)

    List<Afastamento> list(Map args)

    Long count()

    void delete(Serializable id)

    Afastamento save(Afastamento afastamento)

}