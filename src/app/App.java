package src.app;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.*;
import src.com.acmehandel.util.CSVReader;
import com.google.gson.*;

public class App {
  private Frota frota = new Frota();
  private Cargas cargas = new Cargas();
  private Clientes clientes = new Clientes();
  private Portos portos = new Portos();
  private Distancias distancias = new Distancias();
  private TiposCargas tiposCargas = new TiposCargas();
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

  public void readFiles(String filePrefix) {
    CSVReader filesRead = new CSVReader();
    filesRead.readFiles(filePrefix, portos, distancias, frota, clientes, tiposCargas, cargas);
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

