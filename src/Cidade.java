
/**
 *  Classe de cidade
 */
public class Cidade {

    private int codigo;
    private String nome;


    public Cidade(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Cidade(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "Cidade [codigo=" + codigo + ", nome=" + nome + "]";
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cidade other = (Cidade) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    
    
}
