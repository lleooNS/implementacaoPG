package scap

class Relator {

	Afastamento afastamento

	//Relacionamento um-para-muitos
	//A função de relator só pode ser ocupada por um professor
	static belongsTo = [professor:Professor]

    static constraints = {

    	professor nullable:false
    	afastamento nullable:false
    }
}