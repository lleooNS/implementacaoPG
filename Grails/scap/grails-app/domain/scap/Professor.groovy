package scap

class Professor extends Pessoa{

	static hasMany = [afastamentos:Afastamento, mandatos:Mandato, pareceres:Parecer, funcoesDeRelator:Relator, parentescos:Parentesco]

    static constraints = {
    }

    static mapping = 
    {
    	discriminator value: "PROFESSOR(A)" 
    }
}
