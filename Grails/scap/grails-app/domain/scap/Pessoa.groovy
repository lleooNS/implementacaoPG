package scap

class Pessoa {

    String nome 
    String email 
    String telefone 
    String sobreNome

    //Modificando o modo como a representação textual é gerada
    String toString() {
        this.nome + " " + this.sobreNome
    }

    //Regras de validação
    static constraints = 
    {
        nome nullable:false, blank:false, maxSize:128
        email nullable:false, blank:false, email:true
        telefone nullable:false, blank:false
        sobreNome nullable:false, blank:false
    }
}