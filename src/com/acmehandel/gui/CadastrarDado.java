package src.com.acmehandel.gui;

import src.app.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarDado extends JFrame {
    private JButton ok, voltar;

    public CadastrarDado(App app) {
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
                        new FormularioCliente(app.getClientes());
                        dispose();
                        break;
                    case "Navio":
                        new FormularioNavio(app.getFrota());
                        dispose();
                        break;
                    case "Porto":
                        new FormularioPorto(app.getPortos(), app);
                        dispose();
                        break;
                    case "Carga":
                        new FormularioCarga(app.getCargas());
                        dispose();
                        break;
                    case "Tipo de Carga":
                        new FormularioTipoCarga(app.getTiposCargas());
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
