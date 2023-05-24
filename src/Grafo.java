import java.util.ArrayList;

public class Grafo<T> {

    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<>(valor);
        this.vertices.add(novo);
        return novo;
    }

    private Vertice<T> obterVertice(T valor) {
        for (Vertice<T> vertice : this.vertices) {
            if (vertice.getValor().equals(valor)) {
                return vertice;
            }
        }
        return null;
    }

    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem = obterVertice(origem);
        if (verticeOrigem == null) {
            verticeOrigem = adicionarVertice(origem);
        }

        Vertice<T> verticeDestino = obterVertice(destino);
        if (verticeDestino == null) {
            verticeDestino = adicionarVertice(destino);
        }

        Aresta novaAresta = new Aresta(verticeOrigem, verticeDestino, peso);
        this.arestas.add(novaAresta);
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<>();  // Lista de vértices visitados
        ArrayList<Vertice<T>> fila = new ArrayList<>();      // Fila para armazenar os vértices a serem visitados
        Vertice<T> atual = this.vertices.get(0);             
        fila.add(atual);                                     
        marcados.add(atual);                                 
    
        while (!fila.isEmpty()) {
            atual = fila.get(0);                             // Obtém o próximo vértice da fila
            fila.remove(0);                                  // Remove o vértice atual da fila
            System.out.println(atual.getValor());            // Imprime o valor do vértice atual
    
            ArrayList<Aresta> destinos = obterDestinos(atual);   

            for (Aresta aresta : destinos) {

                Vertice<T> proximo = aresta.getDestino();     

                if (!marcados.contains(proximo)) {           // Verifica se o vértice destino ainda não foi visitado
                    fila.add(proximo);                        // Adiciona o vértice destino à fila para visita
                    marcados.add(proximo);                     // Marca o vértice destino como visitado
                }
            }
        }
    }

    private ArrayList<Aresta> obterDestinos(Vertice<T> v) {
        ArrayList<Aresta> destinos = new ArrayList<>();
        
        for (Aresta aresta : this.arestas) {
            if (aresta.getOrigem().equals(v)) {
                destinos.add(aresta);
            }
        }
        return destinos;
    }

    
    
    

}
