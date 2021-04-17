package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class AfastamentoController {

    AfastamentoService afastamentoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond afastamentoService.list(params), model:[afastamentoCount: afastamentoService.count()]
    }

    def show(Long id) {
        respond afastamentoService.get(id)
    }

    def create() {
        respond new Afastamento(params)
    }

    def save(Afastamento afastamento) {
        if (afastamento == null) {
            notFound()
            return
        }

        try {
            afastamentoService.save(afastamento)
        } catch (ValidationException e) {
            respond afastamento.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'afastamento.label', default: 'Afastamento'), afastamento.id])
                redirect afastamento
            }
            '*' { respond afastamento, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond afastamentoService.get(id)
    }

    def update(Afastamento afastamento) {
        if (afastamento == null) {
            notFound()
            return
        }

        try {
            afastamentoService.save(afastamento)
        } catch (ValidationException e) {
            respond afastamento.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'afastamento.label', default: 'Afastamento'), afastamento.id])
                redirect afastamento
            }
            '*'{ respond afastamento, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        afastamentoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'afastamento.label', default: 'Afastamento'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'afastamento.label', default: 'Afastamento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def buscarPorNomeEvento(){
        def lista = []
        lista = Afastamento.findAllByNomeEventoIlike("%"+params.nome+"%")
        render(view:"index", model: [listaFiltroNomeEvento: lista]) 
    }

    def buscarPorNomeProfessor(){
        def lista = []
    
        String hql = "Select afast from Afastamento afast"+
        " where lower((concat(afast.professor.nome, ' ', afast.professor.sobrenome)))"+
        " like lower('%"+params.nome+"%')"

        lista = Afastamento.executeQuery(hql)

        render(view:"index", model: [listaFiltroNomeProfessor: lista]) 

        // lista = Afastamento.createCriteria().list{
        //     professor{
        //         or{
        //             ilike("nome","%"+params.nome+"%")
        //             ilike("sobrenome","%"+params.nome+"%")
        //         }
                
        //     }
        // }
    }

    def buscarPorDatas(){
        def lista = []

        String strDataInicial = ''+params.dataInicial_year+'-'+params.dataInicial_month+'-'+params.dataInicial_day+' 00:00:00'
        Date dataInicialSolic = Date.parse('yyyy-MM-dd HH:mm:ss', strDataInicial)

        String strDataFinal = ''+params.dataFinal_year+'-'+params.dataFinal_month+'-'+params.dataFinal_day+' 00:00:00'
        Date dataFinalSolic = Date.parse('yyyy-MM-dd HH:mm:ss', strDataFinal)
        
        lista = Afastamento.findAllByDataSolicBetween(dataInicialSolic, dataFinalSolic)

        render(view:"index", model: [listaFiltroDatas: lista]) 
    }

    def buscarPorSituacao(){
        def lista = []
        lista = Afastamento.findAllBySituacaoSolic(params.situacoes)
        render(view:"index", model: [listaFiltroSituacoes: lista]) 
    }

}
