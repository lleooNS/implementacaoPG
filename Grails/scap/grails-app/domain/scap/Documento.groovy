package scap

class Documento {

	String tituloDocumento
	String nomeArquivo
	Date dataJuntada

	//Relacionamento um-para-muitos
	//Um documento pertence a somente um afastamento
	static belongsTo = [afastamento:Afastamento]

    static constraints = {

    	tituloDocumento nullable:false, blank:false
    	nomeArquivo nullable:false, blank:false
    	dataJuntada nullable:false, blank:false
    	afastamento nullable:false
    }

	//Modificando o modo como a representação textual é gerada
    String toString() {
        this.nomeArquivo
    }
}
