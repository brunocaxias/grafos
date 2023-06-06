
public class Aresta<T> implements Comparable<Aresta>{

    private Vertice<T> origem, destino;
    private float peso;

    public Aresta(Vertice<T> verticeOrigem, Vertice<T> verticeDestino, float peso) {
        this.origem = verticeOrigem;
        this.destino = verticeDestino;
        this.peso = peso;
    
    }

    public Vertice<T> getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice<T> getDestino() {
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

    @Override
    public int compareTo(Aresta aresta) {
        return Float.compare(this.peso, aresta.peso);
    }

    @Override
    public String toString() {
        return ("Origem: " + this.origem.toString() + " -> " + "Destino: " + this.destino.toString());
    }

  


    
}
