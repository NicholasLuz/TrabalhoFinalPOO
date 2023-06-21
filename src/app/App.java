package src.app;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.*;
import src.com.acmehandel.util.CSVReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Distancias distancias = new Distancias();
  private TiposCargas tiposCargas = new TiposCargas();
  private Scanner teclado = new Scanner(System.in);
  private PrintStream standard = System.out;
  private PrintStream streamSaida;

  public Frota getFrota() {
    return frota;
  }

  public Cargas getCargas() {
    return cargas;
  }

  public Clientes getClientes() {
    return clientes;
  }

  public Portos getPortos() {
    return portos;
  }

  public Distancias getDistancias() {
    return distancias;
  }

  public TiposCargas getTiposCargas() {
    return tiposCargas;
  }

  public int escolherOpcaoMenuAdicionarDado() {
    boolean continuar = false;
    int resposta = 0;
    while (!continuar) {
      try {
        resposta = Integer.parseInt(teclado.nextLine());

        if (resposta < 0 || resposta > 6) {
          throw new InputMismatchException();
        }
        continuar = true;
      } catch (InputMismatchException | NumberFormatException e) {
        System.out.println("Valor inválido, insira valor da lista.");
      }
    }
    return resposta;
  }

  public void tratarRespostaMenuAdicionarDado(int resposta) {
    switch (resposta) {
      case 1:
        cadastraNovoPorto();
        break;
      case 2:
        cadastraNovoNavio();
        break;
      case 3:
        cadastraNovoCliente();
        break;
      case 4:
        cadastraNovoTipoCarga();
        break;
      case 5:
        cadastraNovaCarga();
        break;
      case 6:
        break;
      case 0:
        System.out.println("Encerrando o programa");
        System.exit(0);
        break;
      default:
        System.out.println("Erro inesperado aconteceu. Encerrando o programa.");
        System.exit(0);
    }
  }

  public void readFiles(String filePrefix) {
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
          throw new IllegalArgumentException("Ocorreu um erro: id do porto ja cadastrado");
        }
        adicionaDistanciaPortos(p);
        portos.sort();
        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (InputMismatchException e) {
      System.out.println("Input inválido, insira número inteiro");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  public void adicionaDistanciaPortos(Porto p) {
    if (portos.size() > 1) {
      for (int i = 0; i < portos.size(); i++) {
        if (p.getId() != portos.getPortoId(i).getId()) {
          int idOrigem = p.getId();
          int idDestino = portos.getPortoId(i).getId();
          Distancia d = new Distancia(idOrigem, idDestino, 100.0);
          distancias.adicionaDistancias(d);
          if (distancias.checkDistanciaJaExiste(idOrigem,idDestino) && !distancias.checkDistanciaJaExiste(idDestino,idOrigem) ) {
            int aux = idOrigem;
            idOrigem = idDestino;
            idDestino = aux;
            Distancia d2 = new Distancia(idOrigem, idDestino, 100.0);
            distancias.adicionaDistancias(d2);
          }
        }
      }
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

        frota.sort();
        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (IllegalStateException e) {
      e.getMessage();
    }
  }

  public void cadastraNovoTipoCarga() {
    int sair = 1;
    try {
      do {
        System.out.println("Digite o codigo do tipo de carga (codigo tem que ser unico):");
        int codigo = teclado.nextInt();
        if (tiposCargas.checkTipoCargaJaExiste(codigo)) {
          throw new IllegalArgumentException("Codigo do tipo de carga ja cadastrado");
        }
        System.out.println("Digite a descricao do tipo de carga:");
        String descricao = teclado.nextLine();
        teclado.nextLine();
        System.out.println("Digite a categoria (DURAVEL ou PERECIVEL):");
        String categoria = teclado.nextLine();
        teclado.nextLine();
        try {
          CategoriaCarga.valueOf(categoria);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("Categoria deve ser DURAVEL ou PERECIVEL!!");
        }

        switch (CategoriaCarga.valueOf(categoria)) {
          case DURAVEL:
            System.out.println("Digite o setor:");
            String setor = teclado.nextLine();
            teclado.nextLine();
            System.out.println("Digite o material principal:");
            String materialPrincipal = teclado.nextLine();
            teclado.nextLine();
            System.out.println("Digite o percentual do IPI:");
            double percentualIPI = teclado.nextDouble();
            CargaDuravel cargaD = new CargaDuravel(codigo, descricao, setor, materialPrincipal, percentualIPI);
            tiposCargas.adicionaTipoCarga(cargaD);
            break;
          case PERECIVEL:
            System.out.println("Digite a origem:");
            String origem = teclado.nextLine();
            teclado.nextLine();
            System.out.println("Digite o tempo maximo de validade:");
            int validade = teclado.nextInt();
            CargaPerecivel cargaP = new CargaPerecivel(codigo, descricao, origem, validade);
            tiposCargas.adicionaTipoCarga(cargaP);
            break;
        }
        tiposCargas.sort();
        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (IllegalArgumentException i) {
      i.getMessage();
    }
  }

  public void cadastraNovaCarga() {
    int sair = 1;
    try {
      do {
        System.out.println("Digite o codigo da carga (codigo tem que ser unico):");
        int codigo = teclado.nextInt();
        if (cargas.checkCargaIdJaExiste(codigo)) {
          throw new IllegalArgumentException("codigo ja cadastrado");
        }
        System.out.println("Digite o id do cliente:");
        int id = teclado.nextInt();
        System.out.println("Digite o id do porto de origem:");
        int idOrigem = teclado.nextInt();
        System.out.println("Digite o id do porto de destino:");
        int idDestino = teclado.nextInt();
        System.out.println("Digite o peso:");
        int peso = teclado.nextInt();
        System.out.println("Digite o valor declarado:");
        double valorDeclarado = teclado.nextDouble();
        System.out.println("Digite o tempo maximo:");
        int tempoMaximo = teclado.nextInt();
        System.out.println("Digite o id do tipo de carga:");
        int idTipoCarga = teclado.nextInt();
        System.out.println("Digite a prioridade da entrega (RAPIDO ou BARATO):");
        String prioridade = teclado.nextLine();
        try {
          Prioridade.valueOf(prioridade);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("Prioridade deve ser RAPIDO ou BARATO!!");
        }
        teclado.nextLine();

        Carga carga = new Carga(codigo, id, idOrigem, idDestino, peso, valorDeclarado, tempoMaximo, idTipoCarga,
            prioridade, "PENDENTE");

        cargas.adicionaCarga(carga);
        cargas.sort();

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

        clientes.sort();

        System.out.println("Para sair, digite \"0\": ");
        sair = teclado.nextInt();
      } while (sair != 0);
    } catch (IllegalStateException e) {
      e.getMessage();
    }
  }

  public String consultarCargas() {
    return cargas.mostrarCargas();
  }

  public String alterarCarga(int codigo, String situacao) {
      Carga c = cargas.getCargaId(codigo);
      String sit = c.getSituacao();
      switch (Situacao.valueOf(sit)) {
        case FINALIZADO:
          return "A carga está finalizada, não pode ser alterada.";
        case CANCELADO:
          return alterarCargaCancelada(situacao, c);
        case PENDENTE:
          return alterarCargaPendente(situacao, c);
        case LOCADO:
          return alterarCargaLocada(situacao, c);
        default:
          break;
      }
      return "NAO EXISTEM CARGAS COM ESTE IDENTIFICADOR";
  }

  public String alterarCargaCancelada(String sit, Carga c) {
    String frase = "";
    if (sit.equals(Situacao.PENDENTE.name())) {
      c.setSituacao(sit);
      frase = "A carga "+c.getId()+" foi alterada para " + c.getSituacao();
    } else if (sit.equals(Situacao.LOCADO.name())) {
      frase = "Locando o melhor navio disponível para a carga... " + fretarCargaEspecifica(c.getId());
    } else if (sit.equals(Situacao.CANCELADO.name())){
      frase = "A carga j\u00E1 se encontra no estado cancelado.";
    }
    else if ((sit.equals(Situacao.FINALIZADO.name()))){
      frase = "Não é possivel finalizar uma carga cancelada.";
    }
    else {
      return "ERRO";
    }
    return frase;
  }

  public String alterarCargaPendente(String sit, Carga c) {
    String frase = "";
    if (sit.equals(Situacao.CANCELADO.name())) {
      c.setSituacao(sit);
      frase = "A carga "+c.getId()+" foi alterada para " + c.getSituacao();
    } else if (sit.equals(Situacao.LOCADO.name())) {
      frase = "Locando o melhor navio disponível para a carga... " + fretarCargaEspecifica(c.getId());
    } else if (sit.equals(Situacao.PENDENTE.name())){
      frase = "A carga já se encontra no estado pendente.";
    }
    else if ((sit.equals(Situacao.FINALIZADO.name()))){
      frase = "Não é possivel finalizar uma carga pendente.";
    }
    else {
      return "ERRO";
    }
    return frase;
  }

  public String alterarCargaLocada(String sit, Carga c) {
    if (sit.equals(Situacao.CANCELADO.name())) {
      c.setSituacao(sit);
      String nomeNav = c.getNomeNavio();
      Navio n = frota.getNavioNome(nomeNav);
      n.setIsTransportingFalse();
    } else if (sit.equals(Situacao.FINALIZADO.name())) {
      c.setSituacao(sit);
      String nomeNav = c.getNomeNavio();
      Navio n = frota.getNavioNome(nomeNav);
      n.setIsTransportingFalse();
      n.addCarga(c);
    } else if (sit.equals(Situacao.PENDENTE.name())) {
      c.setSituacao(sit);
      String nomeNav = c.getNomeNavio();
      Navio n = frota.getNavioNome(nomeNav);
      n.setIsTransportingFalse();
      c.removerNavio();
    } else {
      return ("A carga já está locada.");
    }
    return ("A carga "+c.getId()+"foi alterada para " + c.getSituacao());
  }

  public String fretarCargasPendentes() {
    List<Carga> cargasPendentes = cargas.getPendentes();

    return fretarCargas(cargasPendentes);
  }

  public String fretarCargaEspecifica(int codigo) {
    Carga c = cargas.getCargaId(codigo);
    ArrayList<Carga> cargasP = new ArrayList<Carga>();
    cargasP.add(c);
    List<Carga> cargasPendentes = cargasP.stream().collect(Collectors.toList());

    return fretarCargas(cargasPendentes);
  }

  public String fretarCargas(List<Carga> cargasPendentes) {
    String fretados ="";
    if (cargasPendentes.isEmpty()){
      fretados="Não há cargas pendentes.";
    }
    for (Carga c : cargasPendentes) {
      try {
        double distancia = distancias.getDistanciaPortos(c.getIdPortoOrigem(), c.getIdPortoDestino());

        if (distancia == -1) {
          fretados +=("Carga: "+c.getId()+" - Distância entre portos não cadastrada, ou código de portos inválidos.");
          continue;
        }
        double precoPeso = tiposCargas.getPrecoPeso(c.getIdTipoCarga(), c.getPeso(),
            c.getValorDeclarado());

        double precoRegiao = (portos.isNacional(c.getIdPortoOrigem(), c.getIdPortoDestino()) ? 10000.0 : 50000.0);

        boolean haNavioAutonomia = frota.haNavioAutonomia(distancia);
        if (!haNavioAutonomia) {
          c.setSituacao(Situacao.CANCELADO.name());
          fretados +=("Não há navios com autonomia suficiente para realizar o transporte da carga de id: " + c.getId()+"\n");
          continue;
        }

        Navio melhorNavio = frota.getMelhorNavio(c.getPrioridade(), distancia);

        if (melhorNavio == null) {
          fretados +=("Não há navios que possam realizar o transporte da carga de id: " + c.getId() + " no momento.\n");
          continue;
        }

        double custoAjustado = (c.getPrioridade().equals(Prioridade.RAPIDO.name())
            ? (melhorNavio.getCustoPorMilhaBasico() * 2)
            : melhorNavio.getCustoPorMilhaBasico());

        double valorFrete = (distancia * custoAjustado) + precoPeso + precoRegiao;
        c.alocarNavio(melhorNavio, valorFrete);
        melhorNavio.setIsTransportingTrue();
        fretados +=("Navio " + melhorNavio.getNome() + " designado para carga " + c.getId()+"\n");

      } catch (Exception e) {
        return "Erro.";
      }
    }
    return fretados;
  }

  public String salvarDados(String pathname) {

    try {
      streamSaida = new PrintStream(new File(pathname));

      JsonObject jsonObject = new JsonObject();
      jsonObject.add("cargas", createJsonArray(cargas.getCargas()));
      jsonObject.add("frota", createJsonArray(frota.getFrota()));
      jsonObject.add("portos", createJsonArray(portos.getPortos()));
      jsonObject.add("distancias", createJsonArray(distancias.getDistancias()));
      jsonObject.add("clientes", createJsonArray(clientes.getClientes()));
      jsonObject.add("tiposCargas", createJsonArray(tiposCargas.getTiposCargas()));
      System.setOut(streamSaida);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(jsonObject);
      System.out.println(json);

      System.setOut(standard);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "Dados salvados com sucesso em: " + pathname;
  }

  private static JsonArray createJsonArray(List<?> list) {
        JsonArray jsonArray = new JsonArray();
        for (Object item : list) {
            Gson gson = new Gson();
            JsonObject itemObject = gson.toJsonTree(item).getAsJsonObject();
            jsonArray.add(itemObject);
        }
        return jsonArray;
    }

}

