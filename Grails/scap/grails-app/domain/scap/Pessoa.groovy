package scap

class Pessoa {

    String nome
    String sobrenome 
    String email 
    String telefone 

    //Modificando o modo como a representação textual é gerada
    String toString() {
        this.nome + " " + this.sobrenome
    }

    //Regras de validação
    static constraints = 
    {
        nome nullable:false, blank:false, maxSize:128
        sobrenome nullable:false, blank:false
        email nullable:false, blank:false, email:true, unique:true
        telefone nullable:false, blank:false
    }

    static mapping =
    {
        //tablePerHierarchy false
        discriminator column: "cargo", value: "VAZIO"
    
    }
}