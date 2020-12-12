/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.gui;

import br.edu.ifnmg.rockinrio.dao.OcorrenciaDao;
import br.edu.ifnmg.rockinrio.dao.PessoaDao;
import br.edu.ifnmg.rockinrio.entity.Ocorrencia;
import br.edu.ifnmg.rockinrio.entity.Pessoa;
import java.time.LocalDate;
import java.util.ArrayList;

public class EdicaoOcorrenciaDialog extends javax.swing.JDialog {
    
    private final GerenciamentoOcorrencias gerenciamentoOcorrencias;
    private final Ocorrencia ocorrenciaEmEdicao;
    
    public EdicaoOcorrenciaDialog(
        GerenciamentoOcorrencias gerenciamentoOcorrencias,
        Ocorrencia ocorrenciaEmEdicao
    ) {
        super(gerenciamentoOcorrencias, true);
        initComponents();
        
        this.gerenciamentoOcorrencias = gerenciamentoOcorrencias;
        this.ocorrenciaEmEdicao = ocorrenciaEmEdicao;
        initPessoasDropdown();
        preencherCamposOcorrencia();
    }
    
    private void initPessoasDropdown() {
        ArrayList<Pessoa> pessoas = PessoaDao.obterTodos();
        Integer indicePessoaOcorrenciaAtual = null;
        
        for (int i = 0; i < pessoas.size(); i++) {
            String cpf = pessoas.get(i).getCpf();
            String nome = pessoas.get(i).getNome();
            
            pessoasDropdown.addItem("CPF: " + cpf + " | Nome: " + nome);
            
            if (cpf.equals(ocorrenciaEmEdicao.getCpfPessoa())) {
                indicePessoaOcorrenciaAtual = i;
            }
        }
        
        if (indicePessoaOcorrenciaAtual != null) {
            pessoasDropdown.setSelectedIndex(indicePessoaOcorrenciaAtual);
        }
    }
    
    private void preencherCamposOcorrencia() {
        tituloPrincipal.setText("Edição da ocorrência Nº " + ocorrenciaEmEdicao.getNumero());
        descricaoTextField.setText(ocorrenciaEmEdicao.getDescricao());
        dataTextField.setText(ocorrenciaEmEdicao.getDataOcorrenciaEmString(true));
        latitudeTextField.setText(ocorrenciaEmEdicao.getLatitude().toString());
        longitudeTextField.setText(ocorrenciaEmEdicao.getLongitude().toString());
    }
    
    private void salvarAlteracoes() {
        String[] dropdownSelectedItem =((String)pessoasDropdown.getSelectedItem()).split("\\|");
        
        String cpfPessoa = dropdownSelectedItem[0].trim().replace("CPF: ", "");
        String nomePessoa = dropdownSelectedItem[1].trim().replace("Nome: ", "");
        String descricao = descricaoTextField.getText();
        LocalDate dataOcorrencia = getDataTextFieldConteudo();
        Double latitude = getLatitudeTextFieldConteudo();
        Double longitude = getLongitudeTextFieldConteudo();
        
        if (dataOcorrencia == null || latitude == null || longitude == null) {
            String errosText = "";
            
            if (dataOcorrencia == null) {
                errosText += "- O conteúdo inserido no campo Data é inválido!\n";
            }
            if (latitude == null) {
                errosText += "- O conteúdo inserido no campo Latitude é inválido!\n";
            }
            if (longitude == null) {
                errosText += "- O conteúdo inserido no campo Longitude é inválido!";
            }
            
            var mensagemErroDialog = new MensagemErroDialog(this, errosText);
            mensagemErroDialog.setLocationRelativeTo(this);
            mensagemErroDialog.setVisible(true);
            
            return;
        }
        
        ocorrenciaEmEdicao.setCpfPessoa(cpfPessoa);
        ocorrenciaEmEdicao.setNomePessoa(nomePessoa);
        ocorrenciaEmEdicao.setDataOcorrencia(dataOcorrencia);
        ocorrenciaEmEdicao.setDescricao(descricao);
        ocorrenciaEmEdicao.setLatitude(latitude);
        ocorrenciaEmEdicao.setLongitude(longitude);
        
        OcorrenciaDao.atualizar(ocorrenciaEmEdicao);
        gerenciamentoOcorrencias.atualizarModelo(ocorrenciaEmEdicao);
        dispose();
    }
    
    private LocalDate getDataTextFieldConteudo() {
        LocalDate dataOcorrencia;
        
        try {
            String[] dataSplitted = dataTextField.getText().split("/");
            int dia = Integer.parseInt(dataSplitted[0]);
            int mes = Integer.parseInt(dataSplitted[1]);
            int ano = Integer.parseInt(dataSplitted[2]);
            
            dataOcorrencia = LocalDate.of(ano, mes, dia);
        }
        catch (Exception e) {
            return null;
        }
        
        return dataOcorrencia;
    }
    
    private Double getLatitudeTextFieldConteudo() {
        Double latitude;
        try {
            latitude = Double.parseDouble(latitudeTextField.getText());
        }
        catch (Exception e) {
            return null;
        }
        return latitude;
    }
    
    private Double getLongitudeTextFieldConteudo() {
        Double longitude;
        try {
            longitude = Double.parseDouble(longitudeTextField.getText());
        }
        catch (Exception e) {
            return null;
        }
        return longitude;
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
        nomeUsuarioLogado = new javax.swing.JLabel();
        pessoaLabel = new javax.swing.JLabel();
        pessoasDropdown = new javax.swing.JComboBox<>();
        detalhesPessoaButton = new javax.swing.JButton();
        adicionarPessoaButton = new javax.swing.JButton();
        descricaoLabel = new javax.swing.JLabel();
        descricaoScrollRect = new javax.swing.JScrollPane();
        descricaoTextField = new javax.swing.JTextArea();
        dataLabel = new javax.swing.JLabel();
        latitudeLabel = new javax.swing.JLabel();
        latitudeTextField = new javax.swing.JTextField();
        longitudeLabel = new javax.swing.JLabel();
        longitudeTextField = new javax.swing.JTextField();
        salvarButton = new javax.swing.JButton();
        dataTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        retornarMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        retornarMenuPrincipal.setText("Retornar à lista de ocorrências");

        buttonRetornarMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonRetornarMenuPrincipal.setText("<");
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
        tituloPrincipal.setText("Edição da ocorrência Nº _____________");

        nomeUsuarioLogado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nomeUsuarioLogado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeUsuarioLogado.setText("Usuário: _______________________");

        pessoaLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pessoaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pessoaLabel.setText("Pessoa");

        pessoasDropdown.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        detalhesPessoaButton.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        detalhesPessoaButton.setText("Detalhes");
        detalhesPessoaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalhesPessoaButtonActionPerformed(evt);
            }
        });

        adicionarPessoaButton.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        adicionarPessoaButton.setText("Adicionar");
        adicionarPessoaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarPessoaButtonActionPerformed(evt);
            }
        });

        descricaoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        descricaoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descricaoLabel.setText("Descrição");

        descricaoTextField.setColumns(20);
        descricaoTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        descricaoTextField.setLineWrap(true);
        descricaoTextField.setRows(5);
        descricaoScrollRect.setViewportView(descricaoTextField);

        dataLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dataLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dataLabel.setText("Data");

        latitudeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        latitudeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        latitudeLabel.setText("Latitude");

        latitudeTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        latitudeTextField.setToolTipText("");

        longitudeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        longitudeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        longitudeLabel.setText("Longitude");

        longitudeTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        longitudeTextField.setToolTipText("");

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

        dataTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(tituloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nomeUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(descricaoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descricaoScrollRect))
            .addComponent(salvarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(pessoaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pessoasDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detalhesPessoaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adicionarPessoaButton))
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(dataLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(latitudeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(longitudeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelPrincipalLayout.createSequentialGroup()
                        .addComponent(buttonRetornarMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(retornarMenuPrincipal)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRetornarMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retornarMenuPrincipal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeUsuarioLogado)
                    .addComponent(tituloPrincipal))
                .addGap(18, 18, 18)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pessoasDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pessoaLabel)
                    .addComponent(detalhesPessoaButton)
                    .addComponent(adicionarPessoaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descricaoScrollRect, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descricaoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataLabel)
                    .addComponent(longitudeLabel)
                    .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(latitudeLabel)
                    .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(salvarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRetornarMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRetornarMenuPrincipalActionPerformed
        // TODO add your handling code here:
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

    private void detalhesPessoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalhesPessoaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detalhesPessoaButtonActionPerformed

    private void adicionarPessoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarPessoaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adicionarPessoaButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarPessoaButton;
    private javax.swing.JButton buttonRetornarMenuPrincipal;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JTextField dataTextField;
    private javax.swing.JLabel descricaoLabel;
    private javax.swing.JScrollPane descricaoScrollRect;
    private javax.swing.JTextArea descricaoTextField;
    private javax.swing.JButton detalhesPessoaButton;
    private javax.swing.JLabel latitudeLabel;
    private javax.swing.JTextField latitudeTextField;
    private javax.swing.JLabel longitudeLabel;
    private javax.swing.JTextField longitudeTextField;
    private javax.swing.JLabel nomeUsuarioLogado;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JLabel pessoaLabel;
    private javax.swing.JComboBox<String> pessoasDropdown;
    private javax.swing.JLabel retornarMenuPrincipal;
    private javax.swing.JButton salvarButton;
    private javax.swing.JLabel tituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
