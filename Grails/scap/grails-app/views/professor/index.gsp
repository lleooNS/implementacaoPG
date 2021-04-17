<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'professor.label', default: 'Professor')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-professor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifNotGranted roles="ROLE_USER">
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifNotGranted>
                <li>
                    <g:form method="GET" name="formBuscarPorEmail" url="[controller:'Professor', action:'buscarPorEmail']" >
                        Buscar por email do Professor:
                        <g:textField name="email" />
                        <input type="submit" value="Buscar">
                    </g:form>
                </li>
                <li>
                    <g:form method="GET" name="formBuscarChefe" url="[controller:'Professor', action:'buscarChefe']" >
                        Retornar o Chefe do Departamento:
                        <input type="submit" value="Retornar">
                    </g:form>
                </li>
            </ul>
        </div>
        <div id="list-professor" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${professorList}" />

            <f:table collection="${listaFiltroEmail}" />

            <f:table collection="${chefe}" />

            <div class="pagination">
                <g:paginate total="${professorCount ?: 0}" />
            </div>
        </div>
    </body>
</html>