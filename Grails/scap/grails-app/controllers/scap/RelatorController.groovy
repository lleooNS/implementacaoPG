package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class RelatorController {

    RelatorService relatorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond relatorService.list(params), model:[relatorCount: relatorService.count()]
    }

    def show(Long id) {
        respond relatorService.get(id)
    }

    def create() {
        respond new Relator(params)
    }

    def save(Relator relator) {
        if (relator == null) {
            notFound()
            return
        }

        try {
            relatorService.save(relator)
        } catch (ValidationException e) {
            respond relator.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relator.label', default: 'Relator'), relator.id])
                redirect relator
            }
            '*' { respond relator, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond relatorService.get(id)
    }

    def update(Relator relator) {
        if (relator == null) {
            notFound()
            return
        }

        try {
            relatorService.save(relator)
        } catch (ValidationException e) {
            respond relator.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'relator.label', default: 'Relator'), relator.id])
                redirect relator
            }
            '*'{ respond relator, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        relatorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'relator.label', default: 'Relator'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relator.label', default: 'Relator'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
