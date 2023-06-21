package src.com.acmehandel.gui;

import src.com.acmehandel.dados.*;
import src.com.acmehandel.util.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class CarregarDado extends JFrame {
    private JTextField prefixo;
    private JPanel painel;
    private JLabel label;
    private JButton ok, voltar;
    private Clientes clientes;
    private Cargas cargas;
    private Distancias distancias;
    private Frota frota;
    private Portos portos;
    private TiposCargas tiposCargas;

    public CarregarDado(Clientes clientes, Cargas cargas, Distancias distancias, Frota frota, Portos portos,
            TiposCargas tiposCargas) {
        super();
        setTitle("Carregar Dado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        this.cargas=cargas;
        this.frota=frota;
        this.distancias=distancias;
        this.portos=portos;
        this.clientes=clientes;
        this.tiposCargas = tiposCargas;

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
                if(prefixo.getText().equals("EXEMPLO")){
                    setVisible(false);
                    getContentPane().repaint();
                    carregaExemplo();
                }
                prefixo.setText("");
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

    public void carregaExemplo(){
        setTitle("Exemplo carregado.");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,800);
        getContentPane().removeAll();

        String exemplos = ("Clientes: \n"+clientes.mostrarClientes() +"__________________________________\n"+
                "Cargas: \n"+cargas.mostrarCargas2()+"__________________________________\n"+
                "Tipos de cargas: \n"+tiposCargas.mostrarTiposCargas()+"__________________________________\n"+
                "Navios: \n"+frota.mostrarNavios()+"__________________________________\n"+
                "Portos: \n"+portos.mostrarPortos()+"__________________________________\n"+
                "Distancias: \n"+distancias.mostrarDistancias()+"__________________________________\n");

        JTextArea areaTexto = new JTextArea(exemplos);
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        JPanel painelEx = new JPanel(new BorderLayout());
        painelEx.add(scroll,BorderLayout.CENTER);
        painelEx.add(voltar,BorderLayout.SOUTH);
        add(painelEx);
        setVisible(true);
    }

    public void readFiles(String filePrefix, Portos portos, Distancias distancias, Frota frota, Clientes clientes,
            TiposCargas tiposCargas, Cargas cargas) {
        try {
            CSVReader filesRead = new CSVReader();
            if (!filesRead.readFiles(filePrefix, portos, distancias, frota, clientes, tiposCargas, cargas)){
                throw new FileNotFoundException("Arquivo não encontrado.");
            }
            else{
                JOptionPane.showMessageDialog(CarregarDado.this, "Arquivo carregado com sucesso!");
                dispose();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(CarregarDado.this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}
