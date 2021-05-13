package scap

class Afastamento {

	Date dataSolic
	Date dataIniAfast
	Date dataFimAfast
	SituacaoSolicitacao situacaoSolic
	TipoAfastamento tipoAfast
	String motivoAfast
	Onus onus
	Date dataIniEvento
	Date dataFimEvento
	String nomeEvento

	//Relacionamento um-para-muitos
	//Um afastamento pertence a somente um professor
	static belongsTo = [professor:Professor, relator: Professor]

	//relacionamento bidirecional
	//Um afastamento pode possuir 0 ou muitos documentos
	static hasMany = [documentos:Documento]

	//Somente 0 ou um relator
	// static hasOne = [relator: Relator]

    static constraints = {

    	dataSolic nullable:false, blank:false
		dataIniAfast nullable:false, blank:false
		dataFimAfast nullable:false, blank:false
		dataIniEvento nullable:false, blank:false
		dataFimEvento nullable:false, blank:false
		nomeEvento nullable:false, blank:false
		professor nullable:false
		relator nullable:true, blank:true

    }

	//Modificando o modo como a representação textual é gerada
    String toString() {
        this.nomeEvento + ": " + this.professor
    }
}
