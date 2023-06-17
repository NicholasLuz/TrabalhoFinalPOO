package InterfaceGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioNavio extends JFrame {

    // Componentes principais
    private JTextField nomeNavio;
    private JTextField velocidadeNavio;
    private JTextField autonomiaNavio;
    private JTextField custoPorMilhaBasico;
    private JButton botao;
    private JButton botao2;
    private JLabel mensagem;
    public ArrayList<String> cadastro = new ArrayList<>();

    public FormularioNavio() {
        super();
        JLabel formTitle = new JLabel("Digite as informações do navio:");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        formTitle.setAlignmentX(JLabel.CENTER);
        formTitle.setAlignmentY(JLabel.CENTER);

        GridLayout gridCampos = new GridLayout(4, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel nomeNavioLabel = new JLabel("Nome:");
        JLabel velocidadeNavioLabel = new JLabel("Velocidade (km/h):");
        JLabel autonomiaNavioLabel = new JLabel("Autonomia (km):");
        JLabel custoPorMilhaBasicoLabel = new JLabel("Custo por milha basico (R$/milha):");
        nomeNavio = new JTextField();
        velocidadeNavio = new JTextField();
        autonomiaNavio = new JTextField();
        custoPorMilhaBasico = new JTextField();
        painelCampos.add(nomeNavioLabel);
        painelCampos.add(nomeNavio);
        painelCampos.add(velocidadeNavioLabel);
        painelCampos.add(velocidadeNavio);
        painelCampos.add(autonomiaNavioLabel);
        painelCampos.add(autonomiaNavio);
        painelCampos.add(custoPorMilhaBasicoLabel);
        painelCampos.add(custoPorMilhaBasico);

        botao = new JButton("Enviar");
        mensagem = new JLabel();
        botao2 = new JButton("Sair");
        mensagem = new JLabel();

        // Tratamento de evento do botao
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensagem.setForeground(Color.BLUE);
                cadastro.add(nomeNavio.getText());
                cadastro.add(velocidadeNavio.getText());
                cadastro.add(autonomiaNavio.getText());
                cadastro.add(custoPorMilhaBasico.getText());

                mensagem.setText("Navio cadastrado:"+"Nome: "+nomeNavio.getText()+"Velocidade: "+velocidadeNavio.getText()
                        +"Autonomia: " + autonomiaNavio.getText() +"Custo Por Milha Basico:" + custoPorMilhaBasico.getText());

            }
        });
        botao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        FlowLayout botao2Layout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        JPanel botao2Painel = new JPanel(botao2Layout);
        botaoPainel.add(botao);
        botao2Painel.add(botao2);
        painel.add(botaoPainel);
        painel.add(botao2Painel);
        painel.add(mensagem);

        this.setTitle("Formulario - Swing");
        this.add(painel);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FormularioNavio janela = new FormularioNavio();
    }

}