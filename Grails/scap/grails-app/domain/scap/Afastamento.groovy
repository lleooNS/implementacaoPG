package scap

class Afastamento {

	Calendar dataSolic
	Calendar dataIniAfast
	Calendar dataFimAfast
	SituacaoSolicitacao situacaoSolic
	TipoAfastamento tipoAfast
	String motivoAfast
	Onus onus
	Calendar dataIniEvento
	Calendar dataFimEvento
	String nomeEvento

	//Relacionamento um-para-muitos
	//Um afastamento pertence a somente um professor
	static belongsTo = [professor:Professor]

	//relacionamento bidirecional
	//Um afastamento pode possuir 0 ou muitos documentos
	static hasMany = [documentos:Documento]

	//Somente 0 ou um relator
	static hasOne = [relator: Relator]

    static constraints = {

    	dataSolic nullable:false, blank:false
		dataIniAfast nullable:false, blank:false
		dataFimAfast nullable:false, blank:false
		dataIniEvento nullable:false, blank:false
		dataFimEvento nullable:false, blank:false
		nomeEvento nullable:false, blank:false
		professor nullable:false

    }
}
