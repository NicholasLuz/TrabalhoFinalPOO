package src.com.acmehandel.gui;

import src.app.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class SalvarDados extends JFrame {
    private JTextField pathName;
    private JPanel painel;
    private JLabel label;
    private JButton ok, voltar;
    public SalvarDados(App app){
        setTitle("Salvar Dados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        painel = new JPanel();
        label = new JLabel("Insira o nome do arquivo: ");
        pathName = new JTextField(20);
        ok = new JButton("Ok");
        voltar = new JButton("Voltar");
        painel.add(label);
        painel.add(pathName);
        painel.add(ok);
        painel.add(voltar);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    if (pathName.getText().isEmpty()) {
                        throw new InputMismatchException("Preencha todos os campos!");
                    }
                    app.salvarDados(pathName.getText());
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(SalvarDados.this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(SalvarDados.this, "Arquivo carregado com sucesso!");

                pathName.setText("");
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
}
