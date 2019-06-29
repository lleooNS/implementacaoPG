package scap

class Relator {

	Afastamento afastamento

	//Modificando o modo como a representação textual é gerada
    String toString() {
        this.afastamento
    }

	//Relacionamento um-para-muitos
	//A função de relator só pode ser ocupada por um professor
	static belongsTo = [professor:Professor]

    static constraints = {

    	professor nullable:false
    	afastamento nullable:false
    }
}
