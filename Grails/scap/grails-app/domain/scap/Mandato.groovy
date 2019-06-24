package scap

class Mandato {

	Calendar dataInicio
	Calendar dataFim

	//relacionamento um-para-muitos
	//um mandato pertence a somente um professor
	static belongsTo = [professor:Professor]

    static constraints = {

    	dataInicio nullable:false, blank:false
		dataFim nullable:false, blank:false
		professor nullable:false
    }
}
