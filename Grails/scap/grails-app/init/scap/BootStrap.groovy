package scap

import scap.*
import restrito.*

class BootStrap {

    def init = { servletContext ->

    	def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
    	def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

    	def admin = User.findOrSaveWhere(username: 'admin', password: '123', firstName: 'Admin', lastName: 'A')
    	def user = User.findOrSaveWhere(username: 'prof', password: '123', firstName: 'Prof', lastName: 'B')

    	if(!admin.authorities.contains(adminRole))
    	{
    		UserRole.create(admin, adminRole, true) 
    	}

    	if(!user.authorities.contains(adminRole))
    	{
    		UserRole.create(user, userRole, true) 
    	}


    }
    def destroy = {
    }
}
