import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Distancias distancias = new Distancias();
  private Scanner entrada = null, entradaTerminal; // Atributo para entrada de dados
  private PrintStream standard = System.out; // variavel para trocar scanner para terminal
  private PrintStream streamSaida;
  private String[] nomesArquivos = { "PORTOS", "DISTANCIAS", "NAVIOS", "CLIENTES", "TIPOSCARGAS", "CARGAS" };

  public App() {
    entradaTerminal = new Scanner(System.in);
    System.out.println("Insira o prefixo dos arquivos a serem lidos: ");
    String filePrefix = entradaTerminal.nextLine();
    try {
      for (String file : nomesArquivos) {
        BufferedReader streamEntrada = new BufferedReader(new FileReader(filePrefix + "-" + file + ".csv"));
        entrada = new Scanner(streamEntrada); // Usa como entrada um arquivo
        entrada.useLocale(Locale.ENGLISH);
        ArrayList<String> linhas = new ArrayList<>();
        entrada.nextLine();
        while (entrada.hasNextLine()) {
          linhas.add(entrada.nextLine());
        }
        switch (file) {
          case "PORTOS":
            readPortos(linhas, file);
            break;
          case "DISTANCIAS":
            readDistancias(linhas, file);
            break;
          case "NAVIOS":
            readNavios(linhas, file);
            break;
          case "CLIENTES":
            readClientes(linhas, file);
            break;
          case "TIPOSCARGAS":
            readTiposCargas(linhas, file);
            break;
          case "CARGAS":
            readCargas(linhas, file);
            break;
          default:
            break;
        }
        streamSaida = new PrintStream(new File("resultado.csv"));
        System.setOut(streamSaida); // Usa como saida um arquivo
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void executar() {
    portos.mostrarPortos();
    distancias.mostrarDistancias();
  }

  public void readPortos(ArrayList<String> linhas, String fileName) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int idPorto = Integer.parseInt(campos[0]);
        String nomePorto = campos[1];
        String paisPorto = campos[2];
        Porto p = new Porto(idPorto, nomePorto, paisPorto);
        portos.adicionaPorto(p);
      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + " apresenta erros. Ajuste o arquivo.");
        System.setOut(streamSaida);
      }
    }
  }

  public void readDistancias(ArrayList<String> linhas, String fileName) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int idOrigem = Integer.parseInt(campos[0]);
        int idDestino = Integer.parseInt(campos[1]);
        double distancia = Double.parseDouble(campos[2].replaceAll(",", "."));
        Distancia d = new Distancia(idOrigem, idDestino, distancia);
        distancias.adicionaDistancias(d);
      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
        System.setOut(streamSaida);
      }
    }
  }

  public void readNavios(ArrayList<String> linhas, String fileName) {

  }

  public void readClientes(ArrayList<String> linhas, String fileName) {

  }

  public void readTiposCargas(ArrayList<String> linhas, String fileName) {

  }

  public void readCargas(ArrayList<String> linhas, String fileName) {

  }

  public boolean cadastraPorto(Porto p) {
    return portos.adicionaPorto(p);
  }

  public boolean cadastraNavio(Navio n) {
    return frota.adicionaNavio(n);
  }

  public boolean cadastraCliente(Cliente c) {
    return clientes.adicionaCliente(c);
  }

  public void mostrarCargas() {
    // cargas
  }
}
