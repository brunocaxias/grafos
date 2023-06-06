import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: Bruno Carvalho Caxias
 */
public class Grafo<T> {

    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta> arestas;

    /**
     * Constructor inicializador da classe
     */
    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    /**
     * Metodo para adicionar vertice a lista de vertices do grafo por meio do valor
     * @param valor
     * @return
     */
    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<>(valor);
        this.vertices.add(novo);
        return novo;
    }

    /**
     * Metodo para obter vertice na lista de vertices do grafo
     * @param valor
     * @return
     */
    public Vertice<T> obterVertice(T valor) {
        for (Vertice<T> vertice : this.vertices) {
            if (vertice.getValor().equals(valor)) {
                return vertice;
            }
        }
        return null;
    }


    /**
     * Metodo para adicionar aresta a lista de arestas do grafo
     * @param origem
     * @param destino
     * @param peso
     */
    public void adicionarAresta(T origem, T destino, float peso) {

        // Verifica se o vertice de origem existe e se nao ele cria o vertice
        Vertice<T> verticeOrigem = obterVertice(origem);
        if (verticeOrigem == null) {
            verticeOrigem = adicionarVertice(origem);
        }

        //Verifica se o vertice de destino existe e se nao ele cria o vertice
        Vertice<T> verticeDestino = obterVertice(destino);
        if (verticeDestino == null) {
            verticeDestino = adicionarVertice(destino);
        }

        // Adiciona a lista de arestas a nova aresta a ser criada
        Aresta novaAresta = new Aresta(verticeOrigem, verticeDestino, peso);
        this.arestas.add(novaAresta);
    }

    /**
     * Metodo para adicionar aresta a lista de arestas do grafo por meio de vertices ja criados
     * @param origem
     * @param destino
     * @param peso
     */
    private void adicionarAresta(Vertice<T> origem, Vertice<T> destino, float peso) {

        // Adiciona a lista de arestas a nova aresta a ser criada
        Aresta novaAresta = new Aresta(origem, destino, peso);
        this.arestas.add(novaAresta);
    }

    /**
     * Metodo para percorrer o grafo por meio de 2 listas, uma fila a ser percorrida e uma de vertices visitados
     */
    public void buscaEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<>();         // Lista de vértices visitados
        ArrayList<Vertice<T>> fila = new ArrayList<>();             // Fila para armazenar os vértices a serem visitados
        Vertice<T> atual = this.vertices.get(0);
        fila.add(atual);
        marcados.add(atual);

        while (!fila.isEmpty()) {
            atual = fila.get(0);                              // Obtém o próximo vértice da fila
            fila.remove(0);                                   // Remove o vértice atual da fila
            System.out.println(atual.getValor());                   // Imprime o valor do vértice atual

            ArrayList<Aresta> destinos = obterDestinos(atual);

            for (Aresta aresta : destinos) {

                Vertice<T> proximo = aresta.getDestino();

                if (!marcados.contains(proximo)) {                  // Verifica se o vértice destino ainda não foi visitado
                    fila.add(proximo);                              // Adiciona o vértice destino à fila para visita
                    marcados.add(proximo);                          // Marca o vértice destino como visitado
                }
            }
        }
    }

    /**
     * Metodo similar a busca em largura geral, mas recebe como parametro um vertice especifico para comecar a percorrer
     * @param v
     */
    public void buscaEmLargura(T v) {
        ArrayList<Vertice<T>> marcados = new ArrayList<>();         // Lista de vértices visitados
        ArrayList<Vertice<T>> fila = new ArrayList<>();             // Fila para armazenar os vértices a serem visitados
        Vertice<T> atual = obterVertice(v);
        fila.add(atual);
        marcados.add(atual);

        while (!fila.isEmpty()) {
            atual = fila.get(0);                              // Obtém o próximo vértice da fila
            fila.remove(0);                                   // Remove o vértice atual da fila
            System.out.println(atual.getValor());                   // Imprime o valor do vértice atual

            ArrayList<Aresta> destinos = obterDestinos(atual);

            for (Aresta aresta : destinos) {

                Vertice<T> proximo = aresta.getDestino();

                if (!marcados.contains(proximo)) {                  // Verifica se o vértice destino ainda não foi visitado
                    fila.add(proximo);                              // Adiciona o vértice destino à fila para visita
                    marcados.add(proximo);                          // Marca o vértice destino como visitado
                }
            }
        }
    }

    /**
     * Metodo para retornar todas as arestas que possuem como origem um vertice enviado
     * @param v
     * @return
     */
    private ArrayList<Aresta> obterDestinos(Vertice<T> v) {
        ArrayList<Aresta> destinos = new ArrayList<>();

        // Loop para encontrar quais arestas possuem o vertice como origem
        for (Aresta aresta : this.arestas) {
            if (aresta.getOrigem().equals(v)) {
                destinos.add(aresta);
            }
        }
        return destinos;
    }

    /**
     * Metodo para printar todos os vizinhos de um vertice
     * @param v
     */
    public void imprimirVizinhos(Vertice<T> v) {
        ArrayList<Aresta> destinos = obterDestinos(v);

        // Imprime a lista de destinos vinda da funcao obterDestinos();
        for(Aresta aresta : destinos){
            System.out.println(aresta.getDestino().getValor().toString());
        }
    }

    /**
     * Metodo para printar todas as arestas do grafo
     */
    public void imprimirArestas(){
        float pesoTotal = 0;
        for(Aresta aresta : arestas){
            System.out.println(aresta.toString());
            pesoTotal += aresta.getPeso();
        }

        System.out.println("Peso total das arestas: " + pesoTotal);
    }

    /**
     * Implementação (nao convencional nem eficiente) de kruskal 
     * @return
     */
    public Grafo<T> kruskal(){

        // Grafo da arvore geradora minima
        Grafo<T> mst = new Grafo<T>();


        //Colocando em ordem a lista de arestas
        Collections.sort(this.arestas);

        //Adicionando os vertices a arvore geradora minima
        mst.vertices = new ArrayList<>(this.vertices);

        //Pega a menor aresta e adciona a lista de arestasMst
        for(Aresta aresta : this.arestas){ 
            mst.arestas.add(new Aresta<>(aresta.getOrigem(), aresta.getDestino(), aresta.getPeso()));

            // Caso forme loop dentro da arvore geradora minima a aresta é removida
            if(mst.possuiLoop()){
                mst.arestas.remove(mst.arestas.size()-1);
            }


        }

        return mst;
    }


    public void buscaEmProfundidade() {
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
    
        // Itera sobre todos os vértices do grafo
        for (Vertice<T> vertice : this.vertices) {
            if (!visitados.contains(vertice)) {
                dfs(vertice, visitados);
            }
        }
    }
    
    private boolean dfs(Vertice<T> vertice, ArrayList<Vertice<T>> visitados) {
        visitados.add(vertice);
    
        for (Aresta aresta : this.arestas) {
            if (aresta.getOrigem().equals(vertice)) {
                Vertice<T> destino = aresta.getDestino();
    
                if (visitados.contains(destino)) {
                    return true; // Retorna true indicando a existência de um loop
                }
    
                if (dfs(destino, visitados)) {
                    return true; // Retorna true caso um loop seja encontrado em uma chamada recursiva
                }
            }
        }
    
        return false; // Retorna false se nenhum loop for encontrado
    }
    


    public boolean possuiLoop() {
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
    
        for (Vertice<T> vertice : this.vertices) {
            if (!visitados.contains(vertice)) {
                if (dfs(vertice, visitados)) {
                    return true; // Há um loop encontrado, retorna true
                }
            }
        }


    
        return false; // Não há loops no grafo, retorna false
    }
    
    
    public Grafo<T> obterArvoreGeradoraMinima() {
        // Criar um novo grafo para a árvore geradora mínima
        Grafo<T> mst = new Grafo<T>();
        mst.vertices = new ArrayList<>(vertices);
    
        // Ordenar as arestas em ordem crescente de peso
        Collections.sort(arestas);
    
        // Criar um array para rastrear o conjunto pai de cada vértice
        int[] pai = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            pai[i] = i; // Inicialmente, cada vértice é seu próprio pai
        }
    
        // Percorrer todas as arestas em ordem crescente de peso
        for (Aresta<T> aresta : arestas) {
            Vertice<T> origem = aresta.getOrigem();
            Vertice<T> destino = aresta.getDestino();
    
            // Encontrar o representante (pai) de cada vértice na árvore
            int representanteOrigem = encontrarRepresentante(pai, vertices.indexOf(origem));
            int representanteDestino = encontrarRepresentante(pai, vertices.indexOf(destino));
    
            // Verificar se a inclusão da aresta forma um ciclo no grafo
            if (representanteOrigem != representanteDestino) {
                mst.arestas.add(aresta); // Adicionar a aresta à árvore geradora mínima
                unirConjuntos(pai, representanteOrigem, representanteDestino); // Unir os conjuntos dos vértices
            }
        }
    
        return mst;
    }
    
    // Encontrar o representante (pai) de um vértice no conjunto
    private int encontrarRepresentante(int[] pai, int verticeIndex) {
        if (pai[verticeIndex] != verticeIndex) {
            pai[verticeIndex] = encontrarRepresentante(pai, pai[verticeIndex]);
            // Atualizar o pai do vértice para o seu representante
        }
        return pai[verticeIndex];
    }
    
    // Unir os conjuntos de dois vértices na árvore
    private void unirConjuntos(int[] pai, int representanteOrigem, int representanteDestino) {
        pai[representanteOrigem] = representanteDestino; // Definir o pai do representante da origem como o representante do destino
    }
    
    
}


    



