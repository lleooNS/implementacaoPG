package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class ParecerController {

    ParecerService parecerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond parecerService.list(params), model:[parecerCount: parecerService.count()]
    }

    def show(Long id) {
        respond parecerService.get(id)
    }

    def create() {
        respond new Parecer(params)
    }

    def save(Parecer parecer) {
        if (parecer == null) {
            notFound()
            return
        }

        try {
            parecerService.save(parecer)
        } catch (ValidationException e) {
            respond parecer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'parecer.label', default: 'Parecer'), parecer.id])
                redirect parecer
            }
            '*' { respond parecer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond parecerService.get(id)
    }

    def update(Parecer parecer) {
        if (parecer == null) {
            notFound()
            return
        }

        try {
            parecerService.save(parecer)
        } catch (ValidationException e) {
            respond parecer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'parecer.label', default: 'Parecer'), parecer.id])
                redirect parecer
            }
            '*'{ respond parecer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        parecerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'parecer.label', default: 'Parecer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'parecer.label', default: 'Parecer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
