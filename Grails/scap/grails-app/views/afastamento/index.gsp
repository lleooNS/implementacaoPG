<!DOCTYPE html>
<html>
    <head>
        <asset:javascript src="jquery-3.3.1.min.js" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'afastamento.label', default: 'Afastamento')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-afastamento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifNotGranted roles="ROLE_ADMIN">
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifNotGranted>
            </ul>
        </div>
        <div class="nav" >
            <ul>
                <li>
                    <g:form method="GET" name="formBuscarNomeProfessor" url="[controller:'Afastamento', action:'buscarPorNomeProfessor']" >
                        &nbsp; Buscar por nome do Professor:
                        <g:textField name="nome" />
                        <input type="submit" value="Buscar">
                    </g:form>
                    <br/>
                </li>   
                <li>
                    <g:form method="GET" name="formBuscarNomeEvento" url="[controller:'Afastamento', action:'buscarPorNomeEvento']" >
                        &nbsp; Buscar por nome do Evento:
                        <g:textField name="nome" />
                        <input type="submit" value="Buscar">
                    </g:form>
                </li>
            </ul>
        </div>
        <div class="nav" >    
            <ul>    
                <li>
                    &nbsp; Buscar por Data de Solicitação:
                    <g:form method="GET" name="formBuscarDatas" url="[controller:'Afastamento', action:'buscarPorDatas']" >
                        &nbsp; Data Inicial:
                        <g:datePicker name="dataInicial" value="${new Date()}" precision="day" />
                        <br/>
                        &nbsp; Data Final:&nbsp;&nbsp;
                        <g:datePicker name="dataFinal" value="${(new Date())+30}" precision="day" />
                        <input type="submit" value="Buscar">
                    </g:form>
                    <br/>
                </li>
                <li>
                    <g:form method="GET" name="formBuscarSituacao" url="[controller:'Afastamento', action:'buscarPorSituacao']" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Buscar por Situação do Afastamento:
                        <g:select name="situacoes" from="${['INICIADO', 'BLOQUEADO', 'LIBERADO', 'APROVADO_DI', 'APROVADO_CT', 'APROVADO_PRPPG', 'ARQUIVADO', 'CANCELADO', 'REPROVADO']}" />
                        <input type="submit" value="Buscar">
                    </g:form>
                </li>
            </ul>
        </div>
        <div id="list-afastamento" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${afastamentoList}" />
            
            <f:table collection="${listaFiltroNomeEvento}" />

            <f:table collection="${listaFiltroNomeProfessor}" />

            <f:table collection="${listaFiltroDatas}" />

            <f:table collection="${listaFiltroSituacoes}" />

            <div class="pagination">
                <g:paginate total="${afastamentoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>