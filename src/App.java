public class App {
    public static void main(String[] args) throws Exception {
       
        Grafo grafo = new Grafo<Cidade>();
        grafo = Menu.lerArquivo("entrada.txt");

        //grafo.buscaEmLargura();


    }
}
