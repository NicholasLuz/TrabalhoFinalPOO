package src.com.acmehandel.gui;

import src.com.acmehandel.dados.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarDado extends JFrame {
    private JButton ok, voltar;

    public CadastrarDado(Clientes clientes, Cargas cargas, Distancias distancias, Frota frota, Portos portos,
            TiposCargas tiposCargas) {
        super("Cadastrar dado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        String[] opcao = { "Cliente", "Navio", "Porto", "Carga", "Tipo de Carga" };
        JComboBox<String> dropdown = new JComboBox<>(opcao);
        add(dropdown);

        ok = new JButton("Cadastrar");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) dropdown.getSelectedItem();
                switch (selecionado) {
                    case "Cliente":
                        new FormularioCliente(clientes);
                        dispose();
                        break;
                    case "Navio":
                        new FormularioNavio();
                        dispose();
                        break;
                    case "Porto":
                        new FormularioPorto();
                        dispose();
                        break;
                    case "Carga":
                        new FormularioCarga();
                        dispose();
                        break;
                    case "Tipo de Carga":
                        new FormularioTipoCarga();
                        dispose();
                        break;
                }
            }
        });
        add(ok);
        voltar = new JButton("Voltar ao menu inicial");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(voltar);
        setVisible(true);
    }
}
