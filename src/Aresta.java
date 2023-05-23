
public class Aresta {

    private Vertice origem, destino;
    private float peso;

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino, float peso) {
        this.origem = verticeOrigem;
        this.destino = verticeDestino;
        this.peso = peso;
    
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    
}
