import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Grafo<Cidade> grafo = new Grafo<Cidade>();
        grafo = Menu.lerArquivo("entrada.txt");

        // grafo.buscaEmLargura();

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {

            Menu.clearConsole();

            System.out.println("Selecione uma opção:");
            System.out.println("1. Obter cidades vizinhas");
            System.out.println("2. Obter todos os caminhos a partir de uma cidade");
            System.out.println("3. Calcular Árvore geradora mínima");
            System.out.println("4. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    Menu.clearConsole();

                    System.out.println("Informe o código da cidade:");
                    int codigoCidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    Cidade cidade = new Cidade(codigoCidade);
                    Vertice<Cidade> verticeCidade = grafo.obterVertice(cidade);
                    if (verticeCidade != null) {
                        grafo.imprimirVizinhos(verticeCidade);
                    } else {
                        System.out.println("Cidade não encontrada.");
                    }

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;

                case 2:

                    Menu.clearConsole();

                    System.out.println("Informe o código da cidade:");
                    int codigoCidade2 = scanner.nextInt();

                    scanner.nextLine(); // Limpar o buffer do scanner

                    Cidade cidade2 = new Cidade(codigoCidade2);
                    Vertice<Cidade> verticeCidade2 = grafo.obterVertice(cidade2);
                    if (verticeCidade2 != null) {
                        grafo.buscaEmLargura(cidade2);
                    } else {
                        System.out.println("Cidade não encontrada.");
                    }

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;

                case 3:
                    Menu.clearConsole();
                    Grafo mst = grafo.obterArvoreGeradoraMinima();
                    mst.imprimirArestas();

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;


                case 4:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }

        scanner.close();
    }

}
