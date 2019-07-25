package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class SecretarioController {

    SecretarioService secretarioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond secretarioService.list(params), model:[secretarioCount: secretarioService.count()]
    }

    def show(Long id) {
        respond secretarioService.get(id)
    }

    def create() {
        respond new Secretario(params)
    }

    def save(Secretario secretario) {
        if (secretario == null) {
            notFound()
            return
        }

        try {
            secretarioService.save(secretario)
        } catch (ValidationException e) {
            respond secretario.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secretario.label', default: 'Secretario'), secretario.id])
                redirect secretario
            }
            '*' { respond secretario, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond secretarioService.get(id)
    }

    def update(Secretario secretario) {
        if (secretario == null) {
            notFound()
            return
        }

        try {
            secretarioService.save(secretario)
        } catch (ValidationException e) {
            respond secretario.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'secretario.label', default: 'Secretario'), secretario.id])
                redirect secretario
            }
            '*'{ respond secretario, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        secretarioService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'secretario.label', default: 'Secretario'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secretario.label', default: 'Secretario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
