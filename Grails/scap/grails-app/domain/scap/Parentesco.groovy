package scap

class Parentesco {

	TipoParentesco parentesco

	// Modificando o modo como a representação textual é gerada
	String toString() {
		this.parentesco
	}

	static belongsTo = Professor
	static hasMany = [professores: Professor]

    static constraints = {
    }
}
