import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class TesteAluno extends JFrame {
    private JTextField nomeField;
    private JTextField raField;
    private JTextArea displayArea;
    private ArrayList<Aluno> alunos;

    public TesteAluno() {
        setTitle("Cadastro de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        alunos = new ArrayList<>();

        // Configurações de Layout
        setLayout(new BorderLayout());

        // Painel de Entrada com GridBagLayout para melhor controle
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Label e Campo para Nome
        gbc.insets = new Insets(5, 5, 5, 5); // Margens
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        nomeField = new JTextField(20);
        inputPanel.add(nomeField, gbc);

        // Label e Campo para RA
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("RA:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        raField = new JTextField(10);
        inputPanel.add(raField, gbc);

        // Botões
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Adicionar Aluno");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAluno();
            }
        });
        inputPanel.add(addButton, gbc);

        gbc.gridy = 3;
        JButton showButton = new JButton("Mostrar Alunos");
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAlunos();
            }
        });
        inputPanel.add(showButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Área de Exibição
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Ajusta fonte para melhor legibilidade
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void addAluno() {
        String nome = nomeField.getText();
        long ra;
        try {
            ra = Long.parseLong(raField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "RA deve ser um número.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Aluno aluno = new Aluno(nome, ra);
        alunos.add(aluno);
        nomeField.setText("");
        raField.setText("");
    }

    private void showAlunos() {
        Collections.sort(alunos);
        displayArea.setText("Alunos (ordenados por nome):\n");
        for (Aluno aluno : alunos) {
            displayArea.append(aluno.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TesteAluno();
            }
        });
    }
}
