package src.com.acmehandel.gui;

import src.app.App;
import src.com.acmehandel.dados.*;
import src.com.acmehandel.util.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class CarregarDado extends JFrame {
    private JTextField prefixo;
    private JPanel painel;
    private JLabel label;
    private JButton ok, voltar;
    private String filePrefix;

    public CarregarDado(Clientes clientes, Cargas cargas, Distancias distancias, Frota frota, Portos portos,
            TiposCargas tiposCargas) {
        super();
        setTitle("Carregar Dado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        painel = new JPanel();
        label = new JLabel("Insira o prefixo do arquivo: ");
        prefixo = new JTextField(20);
        ok = new JButton("Ok");
        voltar = new JButton("Voltar");
        painel.add(label);
        painel.add(prefixo);
        painel.add(ok);
        painel.add(voltar);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    if (prefixo.getText().isEmpty()) {
                        throw new InputMismatchException("Preencha todos os campos!");
                    }
                    String filePrefix = prefixo.getText();
                    readFiles(filePrefix, portos, distancias, frota, clientes, tiposCargas, cargas);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(CarregarDado.this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                prefixo.setText("");
                dispose();
            }
        });
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
        add(painel);
    }

    public void readFiles(String filePrefix, Portos portos, Distancias distancias, Frota frota, Clientes clientes,
            TiposCargas tiposCargas, Cargas cargas) {
        CSVReader filesRead = new CSVReader();
        filesRead.readFiles(filePrefix, portos, distancias, frota, clientes, tiposCargas, cargas);
    }
}
