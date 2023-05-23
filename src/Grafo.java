import java.util.ArrayList;

public class Grafo<T> {

    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new ArrayList<Aresta>();
    }

    public Vertice<T> adicionarVertice(T valor){
        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);
        return novo;
    }

    private Vertice obterVertice(T valor){
        Vertice v;
        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getValor().equals(valor)){
                return (Vertice) this.vertices.get(i).getValor();
            }
        }

        return null;
    }

    public void adicionarAresta(T origem, T destino, float peso){
        Vertice verticeOrigem, verticeDestino;
        Aresta novaAresta;

        // Procura se vertice origem ja existe e se nao ele adiciona
        verticeOrigem = obterVertice(origem);

        if(verticeOrigem == null){
            verticeOrigem = adicionarVertice(origem);
        }

        // Procura se vertice destino ja existe e se nao ele adiciona
        verticeDestino = obterVertice(destino);

        if(verticeDestino == null){
            verticeDestino = adicionarVertice(destino);
        }

        novaAresta = new Aresta(verticeOrigem, verticeDestino, peso);

        this.arestas.add(novaAresta);
    }

    public void buscaEmLargura(){
        // Lista de vertices ja visitados
        ArrayList<Vertice> marcados = new ArrayList<Vertice>();
        // Lista de vertices ainda para serem visitados e que estao sendo visitados
        ArrayList<Vertice> fila = new ArrayList<Vertice>();
        // Variavel com o vertice atual sendo visitado
        Vertice atual = this.vertices.get(0);
        // Adicionando vertice atual sendo visitado a fila 
        fila.add(atual);

        while(fila.size() > 0){
            // Obtém o primeiro vértice da fila
            atual = fila.get(0);
            // Remove o vértice atual da fila
            fila.remove(0);
            // Marca o vértice atual como visitado
            marcados.add(atual);
            // Imprime o valor do vértice atual
            System.out.println(atual.getValor());
            // Obtém as arestas de saída do vértice atual
            ArrayList<Aresta> destinos = this.obterDestinos(atual);
            // Variável para armazenar o próximo vértice a ser visitado
            Vertice proximo;
            // Percorre todas as arestas de saída do vértice atual
            for(int i = 0; i < destinos.size(); i++){
                // Obtém o destino da aresta atual
                proximo = destinos.get(i).getDestino();
                // Verifica se o próximo vértice ainda não foi visitado
                if(!marcados.contains(proximo)){
                    // Adiciona o próximo vértice à fila de visita
                    fila.add(proximo);
                }
            }
        }
        
    }

    private ArrayList<Aresta> obterDestinos(Vertice v){
        ArrayList<Aresta> destinos = new ArrayList<Aresta>();
        Aresta atual;
        for(int i = 0; i<this.arestas.size(); i++){
            atual = this.arestas.get(i);
            if(atual.getOrigem().equals(v)){
                destinos.add(atual);
            }
        }
        return destinos;
    }

    
}
