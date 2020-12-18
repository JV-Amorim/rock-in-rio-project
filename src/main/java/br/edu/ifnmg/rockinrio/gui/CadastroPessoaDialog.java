/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.gui;

import br.edu.ifnmg.rockinrio.dao.PessoaDao;
import br.edu.ifnmg.rockinrio.entity.Endereco;
import br.edu.ifnmg.rockinrio.entity.Pessoa;
import java.time.LocalDate;

public class CadastroPessoaDialog extends javax.swing.JDialog {

    private final java.awt.Dialog parent;
    
    public CadastroPessoaDialog(java.awt.Dialog parent) {
        super(parent, true);
        initComponents();
        
        this.parent = parent;
    }
    
    /**
     * Busca as informações dos campos disponíveis para criação de um novo
     * Objeto do tipo pessoa que será persistido no Banco de dados através
     * da classe PessoaDao
     */
    private void salvarAlteracoes() {
        String cpf = getCpfTextFieldConteudo();
        String nome = nomeTextField.getText();
        String tipoPessoa = (String)tipoDropdown.getSelectedItem();
        LocalDate dataNascimento = getDataNascimentoTextFieldConteudo();
        String cep = getCepTextFieldConteudo();
        String bairro = bairroTextField.getText();
        String rua = ruaTextField.getText();
        Integer numero = getNumeroTextFieldConteudo();
        
        
        String errosText = "";

        if (PessoaDao.obter(cpf) != null) {
            errosText += "- A pessoa que possui este CPF já está cadastrada!\n";
        }
        if (cpf == null) {
            errosText += "- O conteúdo inserido no campo CPF é inválido!\n";
        }
        if (nome.length() == 0) {
            errosText += "- O campo Nome é obrigatório!\n";
        }
        if (nome.length() > 50) {
            errosText += "- O nome pode ter no máximo 50 caracteres!\n";
        }
        if (dataNascimento == null) {
            errosText += "- O conteúdo inserido no campo Data de Nascimento é inválido!\n";
        }
        if (cep == null) {
            errosText += "- O conteúdo inserido no campo CEP é inválido!\n";
        }
        if (bairro.length() > 30) {
            errosText += "- O bairro pode ter no máximo 30 caracteres!\n";
        }
        if (rua.length() > 20) {
            errosText += "- A rua pode ter no máximo 20 caracteres!\n";
        }
        if (numero == null) {
            errosText += "- O conteúdo inserido no campo Número é inválido!\n";
        }
        
        if (errosText.length() > 0) {
            var mensagemErroDialog = new MensagemErroDialog(this, errosText);
            mensagemErroDialog.setLocationRelativeTo(this);
            mensagemErroDialog.setVisible(true);
            return;
        }
        
        Pessoa pessoa =
            new Pessoa(
                cpf,
                nome,
                tipoPessoa,
                dataNascimento,
                new Endereco(cep, bairro, rua, numero)
            );
        
        PessoaDao.inserir(pessoa);
        
        if (parent.getClass().toString().equals("br.edu.ifnmg.rockinrio.gui.CadastroOcorrenciaDialog")) {
            ((CadastroOcorrenciaDialog)parent).atualizarListaComNovaPessoa(pessoa);
        }
        else if (parent.getClass().toString().equals("br.edu.ifnmg.rockinrio.gui.EdicaoOcorrenciaDialog")) {
            ((EdicaoOcorrenciaDialog)parent).atualizarListaPessoaEditada(pessoa);
        }
        
        dispose();
    }
    /**
     * Aplica mascara no campo CPF
     * @return 
     */
    private String getCpfTextFieldConteudo() {
        String cpf = cpfTextField.getText();
        
        boolean isNumeric = cpf.chars().allMatch(Character::isDigit);
        
        if (!isNumeric || cpf.length() != 11) return null;
        
        return cpf;
    }
    
    /**
     * Máscara do campo Data, verifica se é válida ou não
     * @return 
     */
    private LocalDate getDataNascimentoTextFieldConteudo() {
        LocalDate dataNascimento;
        
        try {
            String[] dataSplitted = dataNascimentoTextField.getText().split("/");
            int dia = Integer.parseInt(dataSplitted[0]);
            int mes = Integer.parseInt(dataSplitted[1]);
            int ano = Integer.parseInt(dataSplitted[2]);
            
            dataNascimento = LocalDate.of(ano, mes, dia);
        }
        catch (Exception e) {
            return null;
        }
        
        return dataNascimento;
    }
    
    private String getCepTextFieldConteudo() {
        String cep = cepTextField.getText();
        
        boolean isNumeric = cep.chars().allMatch(Character::isDigit);
        
        if (!isNumeric || cep.length() != 8) return null;
        
        return cep;
    }
    
    private Integer getNumeroTextFieldConteudo() {
        Integer numero;
        
        try {
            numero = Integer.parseInt(numeroTextField.getText());
        }
        catch (Exception e) {
            return null;
        }
        
        return numero;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        retornarMenuPrincipal = new javax.swing.JLabel();
        buttonRetornarMenuPrincipal = new javax.swing.JButton();
        tituloPrincipal = new javax.swing.JLabel();
        cpfLabel = new javax.swing.JLabel();
        cpfTextField = new javax.swing.JTextField();
        nomeLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        dataNascimentoLabel = new javax.swing.JLabel();
        dataNascimentoTextField = new javax.swing.JTextField();
        tipoLabel = new javax.swing.JLabel();
        tipoDropdown = new javax.swing.JComboBox<>();
        separador = new javax.swing.JSeparator();
        cepLabel = new javax.swing.JLabel();
        cepTextField = new javax.swing.JTextField();
        bairroLabel = new javax.swing.JLabel();
        bairroTextField = new javax.swing.JTextField();
        ruaLabel = new javax.swing.JLabel();
        ruaTextField = new javax.swing.JTextField();
        numeroLabel = new javax.swing.JLabel();
        numeroTextField = new javax.swing.JTextField();
        salvarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rock In Rio - Cadastro de Pessoa");
        setModal(true);
        setResizable(false);

        painelPrincipal.setToolTipText("");

        retornarMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        retornarMenuPrincipal.setText("Retornar");

        buttonRetornarMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonRetornarMenuPrincipal.setText("<");
        buttonRetornarMenuPrincipal.setToolTipText("Nenhum dado será salvo.");
        buttonRetornarMenuPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonRetornarMenuPrincipalMouseReleased(evt);
            }
        });
        buttonRetornarMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRetornarMenuPrincipalActionPerformed(evt);
            }
        });

        tituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tituloPrincipal.setText("Cadastro de pessoa");

        cpfLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cpfLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cpfLabel.setText("CPF");

        cpfTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cpfTextField.setToolTipText("Apenas números.");

        nomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nomeLabel.setText("Nome");

        nomeTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nomeTextField.setToolTipText("O nome pode ter no máximo 50 caracteres.");

        dataNascimentoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dataNascimentoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dataNascimentoLabel.setText("Data de Nascimento");

        dataNascimentoTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        dataNascimentoTextField.setToolTipText("Formato: DD/MM/AAAA");

        tipoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tipoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tipoLabel.setText("Tipo");

        tipoDropdown.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tipoDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Espectador", "Funcionario", "Integrante" }));

        separador.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cepLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cepLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cepLabel.setText("CEP");

        cepTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cepTextField.setToolTipText("Apenas números.");

        bairroLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bairroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bairroLabel.setText("Bairro");

        bairroTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        bairroTextField.setToolTipText("O bairro pode ter no máximo 30 caracteres.");

        ruaLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ruaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ruaLabel.setText("Rua");

        ruaTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ruaTextField.setToolTipText("A rua pode ter no máximo 20 caracteres.");

        numeroLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        numeroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numeroLabel.setText("Número");

        numeroTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        numeroTextField.setToolTipText("Apenas números.");

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(buttonRetornarMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(retornarMenuPrincipal))
                    .addComponent(tituloPrincipal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(tipoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(dataNascimentoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataNascimentoTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelPrincipalLayout.createSequentialGroup()
                        .addComponent(nomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeTextField))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(cpfLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelPrincipalLayout.createSequentialGroup()
                                .addComponent(cepLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelPrincipalLayout.createSequentialGroup()
                                .addComponent(bairroLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bairroTextField)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(ruaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ruaTextField))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(numeroLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroTextField))))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRetornarMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retornarMenuPrincipal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tituloPrincipal)
                .addGap(18, 18, 18)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separador)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelPrincipalLayout.createSequentialGroup()
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cpfLabel)
                                    .addComponent(cpfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nomeLabel)
                                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dataNascimentoLabel)
                                    .addComponent(dataNascimentoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tipoLabel)
                                    .addComponent(tipoDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(painelPrincipalLayout.createSequentialGroup()
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cepLabel)
                                    .addComponent(cepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bairroLabel)
                                    .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ruaLabel)
                                    .addComponent(ruaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numeroLabel)
                                    .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );

        salvarButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salvarButton.setText("Salvar");
        salvarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                salvarButtonMouseReleased(evt);
            }
        });
        salvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salvarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salvarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRetornarMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRetornarMenuPrincipalActionPerformed

    }//GEN-LAST:event_buttonRetornarMenuPrincipalActionPerformed

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salvarButtonActionPerformed

    private void buttonRetornarMenuPrincipalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRetornarMenuPrincipalMouseReleased
        dispose();
    }//GEN-LAST:event_buttonRetornarMenuPrincipalMouseReleased

    private void salvarButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvarButtonMouseReleased
        salvarAlteracoes();
    }//GEN-LAST:event_salvarButtonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bairroLabel;
    private javax.swing.JTextField bairroTextField;
    private javax.swing.JButton buttonRetornarMenuPrincipal;
    private javax.swing.JLabel cepLabel;
    private javax.swing.JTextField cepTextField;
    private javax.swing.JLabel cpfLabel;
    private javax.swing.JTextField cpfTextField;
    private javax.swing.JLabel dataNascimentoLabel;
    private javax.swing.JTextField dataNascimentoTextField;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JLabel numeroLabel;
    private javax.swing.JTextField numeroTextField;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JLabel retornarMenuPrincipal;
    private javax.swing.JLabel ruaLabel;
    private javax.swing.JTextField ruaTextField;
    private javax.swing.JButton salvarButton;
    private javax.swing.JSeparator separador;
    private javax.swing.JComboBox<String> tipoDropdown;
    private javax.swing.JLabel tipoLabel;
    private javax.swing.JLabel tituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
