package src.com.acmehandel.gui;

import src.app.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FretarCarga extends JFrame {
    private App app;
    private JButton voltar;
    public FretarCarga(App app) {
        super();
        this.app = app;
        setTitle("Fretar cargas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,400);

        JTextArea areaTexto = new JTextArea(app.fretarCargasPendentes());
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(scroll,BorderLayout.CENTER);
        painel.add(voltar,BorderLayout.SOUTH);

        getContentPane().add(painel);
        setVisible(true);
    }
}
