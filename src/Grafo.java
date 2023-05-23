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

        while(fila.size()>0){
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());
            ArrayList<Aresta> destinos = this.obterDestinos(atual);
            Vertice proximo;
            for(int i = 0; i < destinos.size(); i++){
                proximo = destinos.get(i).getDestino();
                if(!marcados.contains(proximo)){
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
