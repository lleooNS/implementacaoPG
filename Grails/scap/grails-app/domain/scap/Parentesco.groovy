package scap

class Parentesco {

	TipoParentesco parentesco
	Professor professor
	Professor parente

	// Modificando o modo como a representação textual é gerada
	String toString() {
		this.parentesco
	}

	static belongsTo = [Professor]
	
	//static hasMany = [professores: Professor]

    static constraints = {
    	//professor nullable:false
    }
}
