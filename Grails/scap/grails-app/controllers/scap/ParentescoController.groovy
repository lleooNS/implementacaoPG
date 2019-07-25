package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class ParentescoController {

    ParentescoService parentescoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond parentescoService.list(params), model:[parentescoCount: parentescoService.count()]
    }

    def show(Long id) {
        respond parentescoService.get(id)
    }

    def create() {
        respond new Parentesco(params)
    }

    def save(Parentesco parentesco) {
        if (parentesco == null) {
            notFound()
            return
        }

        try {
            parentescoService.save(parentesco)
        } catch (ValidationException e) {
            respond parentesco.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'parentesco.label', default: 'Parentesco'), parentesco.id])
                redirect parentesco
            }
            '*' { respond parentesco, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond parentescoService.get(id)
    }

    def update(Parentesco parentesco) {
        if (parentesco == null) {
            notFound()
            return
        }

        try {
            parentescoService.save(parentesco)
        } catch (ValidationException e) {
            respond parentesco.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'parentesco.label', default: 'Parentesco'), parentesco.id])
                redirect parentesco
            }
            '*'{ respond parentesco, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        parentescoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'parentesco.label', default: 'Parentesco'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'parentesco.label', default: 'Parentesco'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
