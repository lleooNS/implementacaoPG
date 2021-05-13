package scap

class Professor extends Pessoa{

	// static hasMany = [afastamentos:Afastamento, mandatos:Mandato, pareceres:Parecer, funcoesDeRelator:Afastamento, parentescos:Parentesco]
    static hasMany = [afastamentos:Afastamento, mandato:Mandato, pareceres:Parecer, parentescos:Parentesco]

    static constraints = {
    }

    static mapping = 
    {
    	discriminator value: "PROFESSOR(A)" 
    }
}
