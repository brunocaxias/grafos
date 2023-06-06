
public class Aresta<T> implements Comparable<Aresta> {

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aresta other = (Aresta) obj;
        if (!origem.equals(other.origem) || !destino.equals(other.destino) || peso != other.peso)
            return false;
        return true;
    }

}
