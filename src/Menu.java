import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static Grafo<Cidade> lerArquivo(String nomeArquivo) {
        try {
            File arquivo = new File(nomeArquivo);
            Scanner scanner = new Scanner(arquivo);

            int quantidadeCidades = Integer.parseInt(scanner.nextLine());
            List<Cidade> cidades = new ArrayList<>();

            // Leitura das cidades
            for (int i = 0; i < quantidadeCidades; i++) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(",");
                int codigo = Integer.parseInt(partes[0]);
                String nome = partes[1];
                Cidade cidade = new Cidade(codigo, nome);
                cidades.add(cidade);
            }

            Grafo<Cidade> grafo = new Grafo<Cidade>();

            // Adicionar as cidades ao grafo
            for (Cidade cidade : cidades) {
                grafo.adicionarVertice(cidade);
            }

            // Leitura das distâncias entre as cidades
            for (int i = 0; i < quantidadeCidades; i++) {
                String linha = scanner.nextLine();
                String[] valores = linha.split(",");
                for (int j = 0; j < quantidadeCidades; j++) {
                    float distancia = Float.parseFloat(valores[j]);
                    if (distancia != 0.0) {
                        grafo.adicionarAresta(cidades.get(i), cidades.get(j), distancia);
                    }
                }
            }

            scanner.close();

            return grafo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Erro ao limpar o console: " + ex.getMessage());
        }
    }

    
}
