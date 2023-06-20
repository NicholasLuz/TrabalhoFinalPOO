package src.com.acmehandel.gui;

import src.com.acmehandel.dados.*;
import src.com.acmehandel.modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {
    private JButton cadastrarNovoDado, consultarCargas, alterarCargas, fretarCargas, salvarDados, carregarDados, sair;
    private JPanel painel;
    private Clientes clientes;
    private Cargas cargas;
    private Distancias distancias;
    private Frota frota;
    private Portos portos;
    private TiposCargas tiposCargas;

    public MainInterface(Clientes clientes, Cargas cargas, Distancias distancias, Frota frota, Portos portos,
            TiposCargas tiposCargas) {
        super("Menu inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel = new JPanel();
        painel.setLayout(new GridLayout(7, 1));

        cadastrarNovoDado = new JButton("Cadastrar novo dado");
        consultarCargas = new JButton("Consultar cargas");
        alterarCargas = new JButton("Alterar cargas");
        fretarCargas = new JButton("Fretar cargas");
        salvarDados = new JButton("Salvar dados");
        carregarDados = new JButton("Carregar dados");
        sair = new JButton("Sair");

        painel.add(cadastrarNovoDado);
        cadastrarNovoDado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastrarDado(clientes, cargas, distancias, frota, portos, tiposCargas);
            }
        });
        painel.add(consultarCargas);
        consultarCargas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultarCarga cc = new ConsultarCarga();
            }
        });
        painel.add(alterarCargas);
        painel.add(fretarCargas);
        painel.add(salvarDados);
        painel.add(carregarDados);
        carregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarregarDado cd = new CarregarDado(clientes, cargas, distancias, frota, portos, tiposCargas);
            }
        });
        painel.add(sair);

        add(painel);
        setVisible(true);
    }
}
