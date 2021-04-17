package scap

class Mandato {

	Date dataInicio
	Date dataFim

	//relacionamento um-para-muitos
	//um mandato pertence a somente um professor
	static belongsTo = [professor:Professor]

    static constraints = {

    	dataInicio nullable:false, blank:false
		dataFim nullable:false, blank:false
		professor nullable:false
    }

	//Modificando o modo como a representação textual é gerada
    String toString() {
        this.professor
    }
}
