package scap

class Professor extends Pessoa{

	static hasMany = [afastamentos:Afastamento, mandatos:Mandato, pareceres:Parecer, funcoesDeRelator:Relator, parentesco:Parentesco]

    static constraints = {
    }
}
