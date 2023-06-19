import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author: Bruno Carvalho Caxias
 * Implementacao de Kruskal
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
     * Metodo para retornar a aresta inversa a aresta enviada
     * @param a
     * @return
     */
    private Aresta obterArestaInversa(Aresta a){

        Aresta aux = new Aresta(a.getDestino(), a.getOrigem(), a.getPeso());

        for (Aresta aresta : this.arestas){
            if(aresta.equals(aux)){
                return aresta;
            }
        }
        return null;
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
        for(int i = 0; i < arestas.size()/2; i++){
            System.out.println(arestas.get(i).toString());
            pesoTotal += arestas.get(i).getPeso();
        }

        System.out.println("Peso total das arestas: " + pesoTotal);
    }

    /**
     * Algoritmo de criacao da arvore geradora minima utilizando Kruskal
     * @return 
     */
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
                pai[representanteOrigem] = representanteDestino; // Unir os conjuntos dos vértices apontando o representante pai do conjunto origem para o conjunto destino
            }
        }

        // Adicionar novamente a aresta contraria para deixar o grafo novamente nao direcionado
        int arestasiterativo = mst.arestas.size();

        for(int i = 0; i < arestasiterativo; i++){
            Aresta aux = obterArestaInversa(mst.arestas.get(i));
            mst.arestas.add(aux);
        }

    
        return mst;
    }
    
    /**
     * Esse metodo serve para encontrar o representante pai de cada vertice no grafo, o representante pai é o vertice que 
     * representa um grupo de vertices, ele existe para facilitar busca e uniao de conjuntos.
     * 
     * @param pai lista que armazena o representate pai de cada vertice
     * @param verticeIndex indeice do do vertice que queremos encontrar o representante pai
     * @return
     */
    private int encontrarRepresentante(int[] pai, int verticeIndex) {
        if (pai[verticeIndex] != verticeIndex) { // Verifica se possui ele mesmo como representante pai
            pai[verticeIndex] = encontrarRepresentante(pai, pai[verticeIndex]);
            // Atualizar o pai do vértice para o seu representante
        }
        return pai[verticeIndex];
    }


    /**
     * Metodo de implementacaoi de Dijkstra utilizando 3 listas (vertices para simbolizar vertice pai, booleanos para simbolizar
     * se foram vistados ou nao e outro de inteiros para guardar distancias)
     * com relacao 1 : 1 com a lista de vertices presentes no grafo
     */
    public ArrayList<Vertice<T>> obterMenorCaminho(Vertice<T> verticeOrigem, Vertice<T> verticeDestino) {

        // Inicializaxao das estruturas que serao utilizadas, todas tem relação 1 para 1 com o array de vertices do grafo
        ArrayList<Vertice<T>> caminhoMinimo = new ArrayList<>(); //Arraylist guardando o caminho de vertices encontrado
        int[] distancias = new int[vertices.size()]; //array guardando as distancias do vertice de origem ate eles
        Vertice<T>[] verticesAnteriores = new Vertice[vertices.size()]; //array guardando os vertices anteriores
        boolean[] visitados = new boolean[vertices.size()]; //array guardando se o vertice foi ou nao visitado

        // Coloca todos os vertices como nao visitados e deixa sua distancia como infinita (maxima permitida pela linguagem)
        for (int i = 0; i < vertices.size(); i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }

        // Coloca o vertice de origem com distancia zero, simbolizando que ele eh o começo
        distancias[vertices.indexOf(verticeOrigem)] = 0;


        for (int i = 0; i < vertices.size(); i++) {
            // Procura o indice de menor distancia da lista de distancias
            int indiceMenorDistancia = encontrarMenorDistancia(distancias, visitados);
            // Coloca ele como visitado
            visitados[indiceMenorDistancia] = true;
            // Pega o vertice que representa essa menor distancia
            Vertice<T> verticeAtual = vertices.get(indiceMenorDistancia);

            // Se vertice atual for igual ao destino entao o loop é quebrado
            if (verticeAtual.equals(verticeDestino)) {
                break;
            }

            // Loop para iterar entre todas as arestas conectas ao vertice atual
            for (Aresta aresta : obterDestinos(verticeAtual)) {
                // Pega o destino da aresta atual
                Vertice<T> vizinho = aresta.getDestino();
                // Pega o peso da aresta atual (distancia)
                int pesoAresta = (int) aresta.getPeso();
                // Salva a nova distancia que sera utilizada no vertice de destino, sendo essa distancia a distancia do vertice atual mais o peso da aresta
                int novaDistancia = distancias[vertices.indexOf(verticeAtual)] + pesoAresta;

                // Verifica se a nova distancia é menor que a distancia do vertice vizinho
                if (novaDistancia < distancias[vertices.indexOf(vizinho)]) {
                    // Se for a distancia do vertice vizinho vira a nova distancia
                    distancias[vertices.indexOf(vizinho)] = novaDistancia;
                    // Salva nos vertices anteriores (o vertice que levou ate o caminho) o vertice atual
                    verticesAnteriores[vertices.indexOf(vizinho)] = verticeAtual;
                }
            }
        }

        // Vertice atual vira verticeDestino
        Vertice<T> verticeAtual = verticeDestino;
        // Loop para verifica se o vertice atual nao eh nulo
        while (verticeAtual != null) {
            // Adiciona o verticeAtual ao caminho minimo
            caminhoMinimo.add(verticeAtual);
            // VerticeAtual ganha o valor de seu vertice anterior
            verticeAtual = verticesAnteriores[vertices.indexOf(verticeAtual)];
        }

        // Organiza o arraylist devido a forma como os inserts sao feitos
        Collections.reverse(caminhoMinimo);

        // Retorna uma lista de vertices que geram o caminho minimo
        return caminhoMinimo;
    }


    /**
     * Metodo de procura de menor distancia de vertices nao visitados 
     * @param distancias
     * @param visitados
     * @return
     */
    private int encontrarMenorDistancia(int[] distancias, boolean[] visitados) {
        int menorDistancia = Integer.MAX_VALUE;
        int indiceMenorDistancia = -1;

        for (int i = 0; i < distancias.length; i++) {
            // Se o vertice nao tiver sido visitado e a distancia estiver menor que infinito (maximo integer aceito)
            if (!visitados[i] && distancias[i] < menorDistancia) {
                // menorDistancia ganha a distancia do vertice
                menorDistancia = distancias[i];
                // indice fica com o indice da menor distancia
                indiceMenorDistancia = i;
            }
        }

        //retorna o indice de menor distancia encontrado na lista de distancias
        return indiceMenorDistancia;
    }

    
    
}


    



