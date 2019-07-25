package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class MandatoController {

    MandatoService mandatoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond mandatoService.list(params), model:[mandatoCount: mandatoService.count()]
    }

    def show(Long id) {
        respond mandatoService.get(id)
    }

    def create() {
        respond new Mandato(params)
    }

    def save(Mandato mandato) {
        if (mandato == null) {
            notFound()
            return
        }

        try {
            mandatoService.save(mandato)
        } catch (ValidationException e) {
            respond mandato.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mandato.label', default: 'Mandato'), mandato.id])
                redirect mandato
            }
            '*' { respond mandato, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond mandatoService.get(id)
    }

    def update(Mandato mandato) {
        if (mandato == null) {
            notFound()
            return
        }

        try {
            mandatoService.save(mandato)
        } catch (ValidationException e) {
            respond mandato.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mandato.label', default: 'Mandato'), mandato.id])
                redirect mandato
            }
            '*'{ respond mandato, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        mandatoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mandato.label', default: 'Mandato'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mandato.label', default: 'Mandato'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
