package scap

class Parecer {

	Date dataParecer
	TipoParecer julgamento
	String motivo

	//Relacionamento um-para-muitos
	//Um parecer pertence a somente um afastamento
	//Um parecer pertence a somente um professor
	static belongsTo = [afastamento:Afastamento, professor:Professor]

    static constraints = {

    	dataParecer nullable:false, blank:false
    	afastamento nullable:false
    	professor nullable:false
    }
}
