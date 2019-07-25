package scap

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class DocumentoController {

    DocumentoService documentoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond documentoService.list(params), model:[documentoCount: documentoService.count()]
    }

    def show(Long id) {
        respond documentoService.get(id)
    }

    def create() {
        respond new Documento(params)
    }

    def save(Documento documento) {
        if (documento == null) {
            notFound()
            return
        }

        try {
            documentoService.save(documento)
        } catch (ValidationException e) {
            respond documento.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documento.id])
                redirect documento
            }
            '*' { respond documento, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond documentoService.get(id)
    }

    def update(Documento documento) {
        if (documento == null) {
            notFound()
            return
        }

        try {
            documentoService.save(documento)
        } catch (ValidationException e) {
            respond documento.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documento.id])
                redirect documento
            }
            '*'{ respond documento, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        documentoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
