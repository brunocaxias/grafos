import java.util.ArrayList;

public class Grafo<T> {

    private ArrayList<Vertice<T>> vertices;
    private float arestas[][];
    int quantVertices;

    public Grafo(int quantVertices){
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new float[quantVertices][quantVertices];
        this.quantVertices = quantVertices;
    }

    public Vertice<T> adicionarVertice(T valor){

        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);
        return novo;
        
    }

    private int obterIndiceVertice(T valor){
        Vertice v;
        for(int i = 0; i<this.vertices.size();i++){
            v = this.vertices.get(i);
            if(v.getValor().equals(valor))
                return i;
        }
        return -1;
    }

    public void adicionarAresta(T origem, T destino, float peso){
        Vertice verticeOrigem, verticeDestino;
        Aresta novaAresta;

        int indiceOrigem = obterIndiceVertice(origem);

        if(indiceOrigem == -1){
            verticeOrigem = adicionarVertice(origem);
            indiceOrigem = this.vertices.indexOf(verticeOrigem);
        }

        int indiceDestino = obterIndiceVertice(destino);

        if(indiceDestino == -1){
            verticeDestino = adicionarVertice(destino);
            indiceDestino = this.vertices.indexOf(verticeDestino);
        }

        this.arestas[indiceOrigem][indiceDestino] = peso;
    }

    public void buscaEmLargura(){
        boolean marcados[] = new boolean[this.quantVertices];
        int atual = 0;
        ArrayList<Integer> fila = new ArrayList<Integer>();
        
        fila.add(atual);
        while(fila.size() > 0){
            atual = fila.get(0);
            fila.remove(0);
            marcados[atual] = true;
            System.out.println(this.vertices.get(atual).getValor());
            for(int dest=0; dest<this.quantVertices;dest++){
                if(arestas[atual][dest] > 0){
                    if(!marcados[dest]){
                        fila.add(dest);
                    }
                }
            }
        }
    }

    
    
}
