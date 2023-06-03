import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.naming.directory.NoSuchAttributeException;

//testando tudo
public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Distancias distancias = new Distancias();
  private TiposCargas tiposCargas = new TiposCargas();
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
    frota.mostrarNavios();
    clientes.mostrarClientes();
    tiposCargas.mostrarTiposCargas();
    consultarCargas();
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
    portos.sort();
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
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        String nome = campos[0];
        double velocidade = Double.parseDouble(campos[1].replaceAll(",", "."));
        double autonomia = Double.parseDouble(campos[2].replaceAll(",", "."));
        double custoMilha = Double.parseDouble(campos[3].replaceAll(",", "."));
        Navio n = new Navio(nome, velocidade, autonomia, custoMilha);
        frota.adicionaNavio(n);
      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
        System.setOut(streamSaida);
      }
    }
    frota.sort();
  }

  public void readClientes(ArrayList<String> linhas, String fileName) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int codCli = Integer.parseInt(campos[0]);
        String nomeCli = campos[1];
        String emailCli = campos[2];
        Cliente c = new Cliente(codCli, nomeCli, emailCli);
        clientes.adicionaCliente(c);
      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
        System.setOut(streamSaida);
      }
    }
    clientes.sort();
  }

  public void readTiposCargas(ArrayList<String> linhas, String fileName) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int numero = Integer.parseInt(campos[0]);
        String descricao = campos[1];
        String categoria = campos[2];
        if (categoria.equals("DURAVEL")) {
          String setor = campos[3];
          String material = campos[4];
          double ipi = Double.parseDouble(campos[5].replaceAll(",", "."));
          CargaDuravel c = new CargaDuravel(numero, descricao, setor, material, ipi);
          tiposCargas.adicionaTipoCarga(c);
        } else if (categoria.equals("PERECIVEL")) {
          String origem = campos[3];
          int tempoMaximo = Integer.parseInt(campos[4]);
          CargaPerecivel c = new CargaPerecivel(numero, descricao, origem, tempoMaximo);
          tiposCargas.adicionaTipoCarga(c);

        }
      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
        System.setOut(streamSaida);
      }
    }
    tiposCargas.sort();
  }

  public void readCargas(ArrayList<String> linhas, String fileName) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int codigo = Integer.parseInt(campos[0]);
        int codCli = Integer.parseInt(campos[1]);
        int idOrigem = Integer.parseInt(campos[2]);
        int idDestino = Integer.parseInt(campos[3]);
        int peso = Integer.parseInt(campos[4]);
        double valorDeclarado = Double.parseDouble(campos[5].replaceAll(",", "."));
        int tempoMaximo = Integer.parseInt(campos[6]);
        int idTipoCarga = Integer.parseInt(campos[7]);
        String prioridade = campos[8];
        String situacao = campos[9];

        if (portos.checkPortoIdJaExiste(idOrigem) && portos.checkPortoIdJaExiste(idDestino)
            && clientes.checkCodClienteJaExiste(codCli) && tiposCargas.checkTipoCargaJaExiste(idTipoCarga)) {
          Carga c = new Carga(codigo, codCli, idOrigem, idDestino, peso, valorDeclarado, tempoMaximo, idTipoCarga,
              prioridade, situacao);
          cargas.adicionaCarga(c);
        } else {
          System.setOut(standard);
          System.out.println("Linha " + (i + 2) + " do arquivo " + fileName
              + "apresenta erros. Informações não existentes no cadastro da carga.");
          System.setOut(streamSaida);
        }

      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
        System.setOut(streamSaida);
      }
    }
    cargas.sort();
  }

  public boolean cadastraNovoPorto(Porto p) {
    return portos.adicionaPorto(p);
  }

  public boolean cadastraNovoNavio(Navio n) {
    return frota.adicionaNavio(n);
  }

  public boolean cadastraNovoCliente(Cliente c) {
    return clientes.adicionaCliente(c);
  }

  public void consultarCargas() {
    cargas.mostrarCargas();
  }

  public void fretarCargas() {
    List<Carga> cargasPendentes = cargas.getPendentes();
    for (Carga c : cargasPendentes) {
      try {
        double distancia = distancias.getDistanciaPortos(c.getIdPortoOrigem(), c.getIdPortoDestino());

        if (distancia == -1) {
          System.setOut(standard);
          System.out.println("Distância entre portos não cadastrada, ou código de portos inválidos.");
          System.setOut(streamSaida);
          continue;
        }
        double precoPeso = tiposCargas.getPrecoPeso(c.getIdTipoCarga(), c.getPeso(),
            c.getValorDeclarado());

        double precoRegiao = (portos.isNacional(c.getIdPortoOrigem(), c.getIdPortoDestino()) ? 10000.0 : 50000.0);

        boolean haNavioAutonomia = frota.haNavioAutonomia(distancia);
        if (!haNavioAutonomia) {
          c.setSituacao(Situacao.CANCELADO.name());
          System.setOut(standard);
          System.out.println(
              "Não há navios com autonomia suficiente para realizar o transporte da carga de id: " + c.getId());
          System.setOut(streamSaida);
          continue;
        }

        Navio melhorNavio = frota.getMelhorNavio(c.getPrioridade(), distancia);

        if (melhorNavio == null) {
          System.setOut(standard);
          System.out.println("Não há navios que possam realizar o transporte da carga de id: " + c.getId() + ".");
          System.setOut(streamSaida);
          continue;
        }

        double custoAjustado = (c.getPrioridade().equals(Prioridade.RAPIDO.name())
            ? (melhorNavio.getCustoPorMilhaBasico() * 2)
            : melhorNavio.getCustoPorMilhaBasico());

        double valorFrete = (distancia * custoAjustado) + precoPeso + precoRegiao;

      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Erro.");
        System.setOut(streamSaida);
      }
    }

  }
}
