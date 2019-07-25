package teste

import scap.*
import restrito.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class MainController {

    def index() { 

    	def username = "admin"
    	[user: username]

    }
}
