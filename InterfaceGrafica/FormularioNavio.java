import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioNavio extends JFrame {

    // Componentes principais
    private JTextField nomeNavio;
    private JTextField velocidadeNavio;
    private JTextField autonomiaNavio;
    private JTextField custoPorMilhaBasico;
    private JButton botao;
    private JLabel mensagem;

    public FormularioNavio() {
        super();

        JLabel formTitle = new JLabel("Digite as informações do navio.");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        GridLayout gridCampos = new GridLayout(4, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel nomeNavioLabel = new JLabel("nome");
        JLabel velocidadeNavioLabel = new JLabel("velocidade");
        JLabel autonomiaNavioLabel = new JLabel("autonomia");
        JLabel custoPorMilhaBasicoLabel = new JLabel("custo por milha basico");
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

        // Tratamento de evento do botao
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensagem.setForeground(Color.RED);
                mensagem.setText("Botao 'Enviar' pressionado: " +
                        "Nome: " + nomeNavio.getText() + ", Velocidade: " + velocidadeNavio.getText() +
                        "Autonomia: " + autonomiaNavio.getText() + ", Custo Por Milha Basico: "
                        + custoPorMilhaBasico.getText());
            }
        });

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botao);
        painel.add(botaoPainel);
        painel.add(mensagem);

        this.setTitle("Formulario - Swing");
        this.add(painel);
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FormularioNavio janela = new FormularioNavio();
    }

}