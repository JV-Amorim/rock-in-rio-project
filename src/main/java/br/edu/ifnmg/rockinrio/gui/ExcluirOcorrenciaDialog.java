/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.gui;

import br.edu.ifnmg.rockinrio.dao.OcorrenciaDao;
import br.edu.ifnmg.rockinrio.entity.Ocorrencia;

public class ExcluirOcorrenciaDialog extends javax.swing.JDialog {
    
    private final GerenciamentoOcorrenciasDialog gerenciamentoOcorrencias;
    private final Ocorrencia ocorrenciaSelecionada;
    
    /**
     * Janela simples para confirmação da Exclusão da ocorrência
     * @param gerenciamentoOcorrencias Dialog que chama esta janela
     * @param ocorrencia ocorrência a ser excluida
     */
    public ExcluirOcorrenciaDialog(GerenciamentoOcorrenciasDialog gerenciamentoOcorrencias, Ocorrencia ocorrencia) {
        super(gerenciamentoOcorrencias, true);
        initComponents();
        this.gerenciamentoOcorrencias = gerenciamentoOcorrencias;
        ocorrenciaSelecionada = ocorrencia;
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
        titulo = new javax.swing.JLabel();
        affirmativeButton = new javax.swing.JButton();
        negativeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rock In Rio - Exclusão de Ocorrência");
        setModal(true);
        setResizable(false);

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titulo.setText("Deseja realmente excluir essa ocorrência?");

        affirmativeButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        affirmativeButton.setMnemonic('s');
        affirmativeButton.setText("Sim");
        affirmativeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                affirmativeButtonMouseReleased(evt);
            }
        });

        negativeButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        negativeButton.setMnemonic('n');
        negativeButton.setText("Não");
        negativeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                negativeButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(affirmativeButton)
                .addGap(13, 13, 13)
                .addComponent(negativeButton)
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(painelPrincipalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titulo)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(affirmativeButton)
                    .addComponent(negativeButton))
                .addGap(21, 21, 21))
            .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(painelPrincipalLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(titulo)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void negativeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_negativeButtonMouseReleased
        dispose();
    }//GEN-LAST:event_negativeButtonMouseReleased

    private void affirmativeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_affirmativeButtonMouseReleased
        OcorrenciaDao.excluir(ocorrenciaSelecionada);
        gerenciamentoOcorrencias.removerOcorrenciaDaLista();
        dispose();
    }//GEN-LAST:event_affirmativeButtonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton affirmativeButton;
    private javax.swing.JButton negativeButton;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
