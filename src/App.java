import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Grafo<Cidade> grafo = new Grafo<Cidade>();
        grafo = Menu.lerArquivo("entrada.txt");

        // Calculo da arvore geradora minima feita no inicio para facilitar o print e
        // uso da mesma na opcao de obterCaminho
        Grafo mst = grafo.obterArvoreGeradoraMinima();

        // grafo.buscaEmLargura();

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 6) {

            Menu.clearConsole();

            System.out.println("Selecione uma opção:");
            System.out.println("1. Obter cidades vizinhas");
            System.out.println("2. Obter todos os caminhos a partir de uma cidade");
            System.out.println("3. Calcular Árvore geradora mínima");
            System.out.println("4. Calcular caminho mínimo entre duas cidades");
            System.out.println("5. Calcular caminho mínimo entre duas cidades considerando apenas a AGM");
            System.out.println("6. Sair");

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
                    mst.imprimirArestas();

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;

                case 4:
                    Menu.clearConsole();

                    System.out.println("Informe o código da cidade:");
                    int codigoCidadeInicio = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    Cidade cidadeInicio = new Cidade(codigoCidadeInicio);
                    Vertice<Cidade> verticeCidadeInicio = grafo.obterVertice(cidadeInicio);


                    System.out.println("Informe o código da cidade:");
                    int codigoCidadeFim = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    Cidade cidadeFim = new Cidade(codigoCidadeFim);
                    Vertice<Cidade> verticeCidadeFim = grafo.obterVertice(cidadeFim);

                    ArrayList<Vertice<Cidade>> menorCaminho = grafo.obterMenorCaminho(verticeCidadeInicio, verticeCidadeFim);

                    for(Vertice vertice : menorCaminho){
                        System.out.println(vertice.toString());
                    }

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();

                    break;

                case 5:
                    Menu.clearConsole();

                    System.out.println("Informe o código da cidade:");
                    int codigoCidadeInicio1 = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    Cidade cidadeInicio1 = new Cidade(codigoCidadeInicio1);
                    Vertice<Cidade> verticeCidadeInicio1 = grafo.obterVertice(cidadeInicio1);


                    System.out.println("Informe o código da cidade:");
                    int codigoCidadeFim2 = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    Cidade cidadeFim2 = new Cidade(codigoCidadeFim2);
                    Vertice<Cidade> verticeCidadeFim2 = grafo.obterVertice(cidadeFim2);

                    ArrayList<Vertice<Cidade>> menorCaminhoMst = mst.obterMenorCaminho(verticeCidadeInicio1, verticeCidadeFim2);

                    for(Vertice vertice : menorCaminhoMst){
                        System.out.println(vertice.toString());
                    }

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();

                    break;

                case 6:
                    Menu.clearConsole();
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
