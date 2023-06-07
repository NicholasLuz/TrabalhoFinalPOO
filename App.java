import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

//testando tudo
public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Distancias distancias = new Distancias();
  private TiposCargas tiposCargas = new TiposCargas();
  private Scanner teclado = new Scanner(System.in);
  private Scanner entrada = null, entradaTerminal; // Atributo para entrada de dados
  private PrintStream standard = System.out; // variavel para trocar scanner para terminal
  private PrintStream streamSaida;
  private String[] nomesArquivos = { "PORTOS", "DISTANCIAS", "NAVIOS", "CLIENTES", "TIPOSCARGAS", "CARGAS" };

  public App() {
    entradaTerminal = new Scanner(System.in);
    System.out.println("Insira o prefixo dos arquivos a serem lidos: "); //depois tem que mudar de EXEMPLOS pra porto/distancias/navio
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

  public void cadastraNovoPorto() {
    int sair = 1;
    try {
      do {
        System.out.println("Digite o id do porto (id tem que ser unico):");
        int id = teclado.nextInt();
        if (portos.checkPortoIdJaExiste(id)) {
          throw new IllegalArgumentException("id do porto ja cadastrado");
        }
        System.out.println("Digite o nome do porto:");
        String nome = teclado.nextLine();
        teclado.nextLine();
        System.out.println("Digite o país do porto:");
        String pais = teclado.nextLine();
        teclado.nextLine();

        Porto p = new Porto(id, nome, pais);
        if (!portos.adicionaPorto(p)){
          System.out.println("Ocorreu um erro: id do porto ja cadastrado");
        }

        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    }catch (IllegalStateException e){e.getMessage();}
  }

  public void cadastraNovoNavio() {
    int sair = 1;
    try{
    do {
      System.out.println("Digite o nome do navio (nomes tem que ser unicos):");
      String nome = teclado.nextLine();
      teclado.nextLine();
      if (frota.checkNomeNavioJaExiste(nome)){
        throw new IllegalArgumentException("Nome do navio ja cadastrado");
      }
      System.out.println("Digite a velocidade do navio:");
      double velocidade = teclado.nextDouble();
      System.out.println("Digite a autonomia do navio:");
      double autonomia = teclado.nextDouble();
      System.out.println("Digite o custo por milha basico do navio:");
      double custoPorMilha = teclado.nextDouble();

      Navio n = new Navio(nome,velocidade,autonomia,custoPorMilha);
      if (!frota.adicionaNavio(n)){
        System.out.println("Ocorreu um erro: Ja existe navio com este nome");
      }

      System.out.println("Para sair, digite \"0\": ");
      sair = teclado.nextInt();
    }while (sair != 0);
  }catch (IllegalStateException e){e.getMessage();}
  }

  public void cadastraNovoCliente() {
    int sair = 1;
    try {
      do {
        System.out.println("Digite o codigo do cliente (codigo tem que ser unico):");
        int codigo = teclado.nextInt();
        if (clientes.checkCodClienteJaExiste(codigo)) {
          throw new IllegalArgumentException("codigo ja cadastrado");
        }
        System.out.println("Digite o nome do cliente:");
        String nome = teclado.nextLine();
        teclado.nextLine();
        System.out.println("Digite o email do cliente:");
        String email = teclado.nextLine();
        teclado.nextLine();
        if (clientes.checkEmailClienteJaExiste(email)) {
          throw new IllegalArgumentException("email ja cadastrado");
        }

        Cliente c = new Cliente(codigo, nome, email);
        if (!clientes.adicionaCliente(c)){
          System.out.println("Ocorreu um erro: codigo ou email ja cadastrados.");
        }

        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    }catch (IllegalStateException e){e.getMessage();}
  }

  public void consultarCargas() {
    cargas.mostrarCargas();
  }

  public void alterarSituacaoCarga(int identificador, String situacao){
    Carga c = cargas.getCargaId(identificador);
    if ( c != null){
      c.toString();
      c.setSituacao(situacao);
    }
    else{
      System.out.println("NAO EXISTEM CARGAS COM ESTE IDENTIFICADOR");
    }
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
        c.setSituacao("LOCADO");

      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Erro.");
        System.setOut(streamSaida);
      }
    }

  }
}
