package src.com.acmehandel.util;

import java.io.*;
import java.util.*;
import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.*;

public class CSVReader {
  private Scanner entrada = null;
  private String[] nomesArquivos = { "PORTOS", "DISTANCIAS", "NAVIOS", "CLIENTES", "TIPOSCARGAS", "CARGAS" };

  public void readFiles(String filePrefix, Portos portos, Distancias distancias,
      Frota frota, Clientes clientes, TiposCargas tiposCargas, Cargas cargas) {
    try {
      for (String file : nomesArquivos) {
        BufferedReader streamEntrada = new BufferedReader(
            new FileReader("resources/csv/" + filePrefix + "-" + file + ".csv"));
        entrada = new Scanner(streamEntrada);
        entrada.useLocale(Locale.ENGLISH);
        ArrayList<String> linhas = new ArrayList<>();
        entrada.nextLine();
        while (entrada.hasNextLine()) {
          linhas.add(entrada.nextLine());
        }
        switch (file) {
          case "PORTOS":
            readPortos(linhas, file, portos);
            break;
          case "DISTANCIAS":
            readDistancias(linhas, file, distancias);
            break;
          case "NAVIOS":
            readNavios(linhas, file, frota);
            break;
          case "CLIENTES":
            readClientes(linhas, file, clientes);
            break;
          case "TIPOSCARGAS":
            readTiposCargas(linhas, file, tiposCargas);
            break;
          case "CARGAS":
            readCargas(linhas, file, cargas, portos, clientes, tiposCargas);
            break;
          default:
            break;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  private void readPortos(ArrayList<String> linhas, String fileName, Portos portos) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int idPorto = Integer.parseInt(campos[0]);
        String nomePorto = campos[1];
        String paisPorto = campos[2];
        Porto p = new Porto(idPorto, nomePorto, paisPorto);
        portos.adicionaPorto(p);
      } catch (Exception e) {
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + " apresenta erros. Ajuste o arquivo.");
      }
    }
    portos.sort();
  }

  private void readDistancias(ArrayList<String> linhas, String fileName, Distancias distancias) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int idOrigem = Integer.parseInt(campos[0]);
        int idDestino = Integer.parseInt(campos[1]);
        double distancia = Double.parseDouble(campos[2].replaceAll(",", "."));
        Distancia d = new Distancia(idOrigem, idDestino, distancia);
        distancias.adicionaDistancias(d);
      } catch (Exception e) {
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
      }
    }
  }

  private void readNavios(ArrayList<String> linhas, String fileName, Frota frota) {
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
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
      }
    }
    frota.sort();
  }

  private void readClientes(ArrayList<String> linhas, String fileName, Clientes clientes) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int codCli = Integer.parseInt(campos[0]);
        String nomeCli = campos[1];
        String emailCli = campos[2];
        Cliente c = new Cliente(codCli, nomeCli, emailCli);
        clientes.adicionaCliente(c);
      } catch (Exception e) {
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
      }
    }
    clientes.sort();
  }

  private void readTiposCargas(ArrayList<String> linhas, String fileName, TiposCargas tiposCargas) {
    for (int i = 0; i < linhas.size(); i++) {
      try {
        String[] campos = linhas.get(i).split(";");
        int numero = Integer.parseInt(campos[0]);
        String descricao = campos[1];
        String categoria = campos[2];
        if (categoria.equals(CategoriaCarga.DURAVEL.name())) {
          String setor = campos[3];
          String material = campos[4];
          double ipi = Double.parseDouble(campos[5].replaceAll(",", "."));
          CargaDuravel c = new CargaDuravel(numero, descricao, setor, material, ipi);
          tiposCargas.adicionaTipoCarga(c);
        } else if (categoria.equals(CategoriaCarga.PERECIVEL.name())) {
          String origem = campos[3];
          int tempoMaximo = Integer.parseInt(campos[4]);
          CargaPerecivel c = new CargaPerecivel(numero, descricao, origem, tempoMaximo);
          tiposCargas.adicionaTipoCarga(c);

        }
      } catch (Exception e) {
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
      }
    }
    tiposCargas.sort();
  }

  private void readCargas(ArrayList<String> linhas, String fileName, Cargas cargas, Portos portos, Clientes clientes,
      TiposCargas tiposCargas) {
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
          System.out.println("Linha " + (i + 2) + " do arquivo " + fileName
              + "apresenta erros. Informações não existentes no cadastro da carga.");
        }

      } catch (Exception e) {
        System.out.println("Linha " + (i + 2) + " do arquivo " + fileName + "apresenta erros. Ajuste o arquivo.");
      }
    }
    cargas.sort();
  }
}