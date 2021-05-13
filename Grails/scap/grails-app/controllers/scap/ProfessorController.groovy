package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class ProfessorController {

    ProfessorService professorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond professorService.list(params), model:[professorCount: professorService.count()]
    }

    def show(Long id) {
        respond professorService.get(id)
    }

    def create() {
        respond new Professor(params)
    }

    def save(Professor professor) {
        if (professor == null) {
            notFound()
            return
        }

        try {
            professorService.save(professor)
        } catch (ValidationException e) {
            respond professor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'professor.label', default: 'Professor'), professor.id])
                redirect professor
            }
            '*' { respond professor, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond professorService.get(id)
    }

    def update(Professor professor) {
        if (professor == null) {
            notFound()
            return
        }

        try {
            professorService.save(professor)
        } catch (ValidationException e) {
            respond professor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'professor.label', default: 'Professor'), professor.id])
                redirect professor
            }
            '*'{ respond professor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        professorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'professor.label', default: 'Professor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'professor.label', default: 'Professor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def buscarPorEmail(){
        def lista = []
        lista = Professor.findAllByEmailIlike("%"+params.email+"%")
        render(view:"index", model: [listaFiltroEmail: lista]) 
    }

    def buscarChefe(){
        def lista = []
        lista = Professor.createCriteria().list{
            sizeEq("mandato",1)
        }
        render(view:"index", model: [chefe: lista]) 
    }
}
