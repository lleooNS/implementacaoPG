package scap

class Secretario extends Pessoa{

    static constraints = {
    }

    static mapping = 
    {
    	discriminator value: "SECRETARIO(A)" 
    }

}
