import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VanDerWaerdenGUI extends JFrame {

    private JTextField numCoresField;
    private JTextField tamPAField;
    private JPanel sequencePanel;

    public VanDerWaerdenGUI() {
        setTitle("Teorema de Van der Waerden - Coloração de Inteiros");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior para inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("nº de cores:"));
        numCoresField = new JTextField(5);
        inputPanel.add(numCoresField);

        inputPanel.add(new JLabel("Tamanho da Sequência:"));
        tamPAField = new JTextField(5);
        inputPanel.add(tamPAField);

        JButton gerarBtn = new JButton("Gerar Sequência");
        inputPanel.add(gerarBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Painel para mostrar a sequência
        sequencePanel = new JPanel();
        sequencePanel.setLayout(new FlowLayout());
        add(sequencePanel, BorderLayout.CENTER);

        JLabel explicacao = new JLabel("<html>Os números inteiros {1,...,N} são coloridos, cada um com uma de r cores diferentes</html>");
        add(explicacao, BorderLayout.SOUTH);

        gerarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSequencia();
            }
        });
    }

    private void gerarSequencia() {
        sequencePanel.removeAll();

        try {
            int r = Integer.parseInt(numCoresField.getText());
            int N = Integer.parseInt(tamPAField.getText());

            Color[] cores = gerarCores(r);
            System.out.println(cores[0]);
            Random rand = new Random();

            for (int i = 1; i <= N; i++) {
                int corIndex = rand.nextInt(r);
                JLabel numLabel = new JLabel(String.valueOf(i));
                numLabel.setOpaque(true);
                numLabel.setBackground(Color.WHITE);
                numLabel.setForeground(aux[corIndex]);
                numLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                numLabel.setPreferredSize(new Dimension(30, 30));
                numLabel.setHorizontalAlignment(SwingConstants.CENTER);
                sequencePanel.add(numLabel);
            }

            sequencePanel.revalidate();
            sequencePanel.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira apenas números válidos.");
        }
    }

    private Color[] gerarCores(int r) {
        Color[] aux = new Color[4];
        aux[0] = new Color(0,0,255);    // Blue
        aux[1] = new Color(255,0,0);    // Red
        aux[2] = new Color(255,255,0); // Yellow
        aux[3] = new Color(0,128,0);   // Green
        
        Color[] cores = new Color[r];
        for (int i = 0; i < r; i++) {
            cores[i] = aux[i];
        }
        return cores;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VanDerWaerdenGUI gui = new VanDerWaerdenGUI();
            gui.setVisible(true);
        });
    }
}
