<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Bem-vindo ao SCAP</title>
    </head>
    <body>
        <content tag="nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
                    <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
                    <li class="dropdown-item"><a href="#">App version:
                        <g:meta name="info.app.version"/></a>
                    </li>
                    <li role="separator" class="dropdown-divider"></li>
                    <li class="dropdown-item"><a href="#">Grails version:
                        <g:meta name="info.app.grailsVersion"/></a>
                    </li>
                    <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
                    <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
                    <li role="separator" class="dropdown-divider"></li>
                    <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
                    <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
                    <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
                    <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                        <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
                    </g:each>
                </ul>
            </li>
        </content>

        <div class="svg" role="presentation">
            <div class="grails-logo-container">
                <asset:image src="ufes.png" class="grails-logo"/>
            </div>
        </div>

        <div id="content" role="main">
            <section class="row colset-2-its">
                <h1>Bem-vindo ao SCAP</h1>

                <p>
                    O Sistema de Controle de Afastamento de Professores foi criado para apoiar um departamento de universidade a realizar o controle das solicitações de afastamento de seus professores efetivos.
                </p>

                <sec:ifLoggedIn>
                    <div id="controllers" role="navigation">
                        <h2>Controladores:</h2>
                        <br/> 
                    </div>         
                    <div class="flex">
                        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                            <g:if test="${c.naturalName != 'Pessoa Controller' && c.naturalName != 'Main Controller' && c.naturalName != 'Login Controller' && c.naturalName != 'Logout Controller'}">
                                <sec:ifNotGranted roles="ROLE_ADMIN">
                                    <g:if test="${c.naturalName == 'Afastamento Controller' || c.naturalName == 'Documento Controller' || c.naturalName == 'Parecer Controller' || c.naturalName == 'Relator Controller' }">
                                        <div>                            
                                            <%-- <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link> --%>                                                 
                                            <g:link controller="${c.logicalPropertyName}">
                                                <button type="button" class="button" style="height: 3em">
                                                    ${c.naturalName}
                                                </button>    
                                            </g:link> 
                                        </div>
                                    </g:if>
                                </sec:ifNotGranted>
                                <sec:ifNotGranted roles="ROLE_USER">
                                    <g:if test="${c.naturalName != 'Relator Controller' }">                              
                                        <div>                            
                                            <g:link controller="${c.logicalPropertyName}">
                                                <button type="button" class="button" style="height: 3em">
                                                    ${c.naturalName}
                                                </button>    
                                            </g:link> 
                                        </div>
                                        </g:if>
                                </sec:ifNotGranted>                          
                            </g:if>
                        </g:each>                       
                    </div>                   
                </sec:ifLoggedIn>
                <br/> <br/>      
            </section>
        </div>
    </body>
</html>
