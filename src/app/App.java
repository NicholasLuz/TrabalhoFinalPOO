package src.app;

import java.io.*;
import java.util.*;
import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.*;
import src.com.acmehandel.util.CSVReader;

public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Distancias distancias = new Distancias();
  private TiposCargas tiposCargas = new TiposCargas();
  private Scanner teclado = new Scanner(System.in);
  private Scanner entradaTerminal; // Atributo para entrada de dados
  private PrintStream standard = System.out; // variavel para trocar scanner para terminal
  private PrintStream streamSaida;

  public void executar() {
    menu();
    readFiles();
    portos.mostrarPortos();
    distancias.mostrarDistancias();
    frota.mostrarNavios();
    clientes.mostrarClientes();
    tiposCargas.mostrarTiposCargas();
    consultarCargas();
  }

  private void menu() {
    System.out.println("Bem vindo(a) ao ACMEHandelsschifffahrtsgesellschaft!");
    System.out.println("----------- O que deseja fazer? -----------");
    System.out.println("| (1) Cadastrar novo dado                 |");
    System.out.println("| (2) Consultar todas as cargas           |");
    System.out.println("| (3) Alterar a situação de uma carga     |");
    System.out.println("-------------------------------------------");
  }

  private void readFiles() {
    entradaTerminal = new Scanner(System.in);
    System.out.println("Insira o prefixo dos arquivos a serem lidos: ");
    String filePrefix = entradaTerminal.nextLine();
    CSVReader filesRead = new CSVReader();
    filesRead.readFiles(filePrefix, portos, distancias, frota, clientes, tiposCargas, cargas);
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
        if (!portos.adicionaPorto(p)) {
          System.out.println("Ocorreu um erro: id do porto ja cadastrado");
        }

        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (IllegalStateException e) {
      e.getMessage();
    }
  }

  public void cadastraNovoNavio() {
    int sair = 1;
    try {
      do {
        System.out.println("Digite o nome do navio (nomes tem que ser unicos):");
        String nome = teclado.nextLine();
        teclado.nextLine();
        if (frota.checkNomeNavioJaExiste(nome)) {
          throw new IllegalArgumentException("Nome do navio ja cadastrado");
        }
        System.out.println("Digite a velocidade do navio:");
        double velocidade = teclado.nextDouble();
        System.out.println("Digite a autonomia do navio:");
        double autonomia = teclado.nextDouble();
        System.out.println("Digite o custo por milha basico do navio:");
        double custoPorMilha = teclado.nextDouble();

        Navio n = new Navio(nome, velocidade, autonomia, custoPorMilha);
        if (!frota.adicionaNavio(n)) {
          System.out.println("Ocorreu um erro: Ja existe navio com este nome");
        }

        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (IllegalStateException e) {
      e.getMessage();
    }
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
        if (!clientes.adicionaCliente(c)) {
          System.out.println("Ocorreu um erro: codigo ou email ja cadastrados.");
        }

        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (IllegalStateException e) {
      e.getMessage();
    }
  }

  public void consultarCargas() {
    cargas.mostrarCargas();
  }

  public void alterarSituacaoCarga(int identificador, String situacao) {
    Carga c = cargas.getCargaId(identificador);
    if (c != null) {
      c.toString();
      c.setSituacao(situacao);
    } else {
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
          System.out
              .println("Não há navios que possam realizar o transporte da carga de id: " + c.getId() + " no momento.");
          System.setOut(streamSaida);
          continue;
        }

        double custoAjustado = (c.getPrioridade().equals(Prioridade.RAPIDO.name())
            ? (melhorNavio.getCustoPorMilhaBasico() * 2)
            : melhorNavio.getCustoPorMilhaBasico());

        double valorFrete = (distancia * custoAjustado) + precoPeso + precoRegiao;
        c.alocarNavio(melhorNavio, valorFrete);
        melhorNavio.setIsTransportingTrue();
        melhorNavio.addCarga(c);

      } catch (Exception e) {
        System.setOut(standard);
        System.out.println("Erro.");
        System.setOut(streamSaida);
      }
    }

  }
}
