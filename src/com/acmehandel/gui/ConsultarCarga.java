package src.com.acmehandel.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarCarga extends JFrame{
private JButton voltar;
    public ConsultarCarga(String todasCargas) {
        super();
        setTitle("Consultar cargas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,800);

        JTextArea areaTexto = new JTextArea(todasCargas);
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
