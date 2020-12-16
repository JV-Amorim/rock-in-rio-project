/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.gui;

public class PaginaInicial extends javax.swing.JFrame {

    public PaginaInicial() {
        initComponents();
    }
    
    public void abrirGerenciamentoOcorrencias(String cpfProfissionalSeguranca, String nomeProfissionalSeguranca) {
        var gerenciamentoOcorrenciasDialog =
            new GerenciamentoOcorrenciasDialog(this, cpfProfissionalSeguranca, nomeProfissionalSeguranca);
        gerenciamentoOcorrenciasDialog.setLocationRelativeTo(this);
        gerenciamentoOcorrenciasDialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tituloPrincipal = new javax.swing.JLabel();
        gerenciamentoOcorrenciaButton = new javax.swing.JButton();
        ingressoOcorrenciaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rock In Rio - Sistema de Ocorrências e Ingressos");
        setResizable(false);

        tituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPrincipal.setText("Rock In Rio - Sistema de Ocorrências e Ingressos");

        gerenciamentoOcorrenciaButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        gerenciamentoOcorrenciaButton.setText("Acessar Sistema de Gerenciamento de Ocorrências");
        gerenciamentoOcorrenciaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gerenciamentoOcorrenciaButtonMouseReleased(evt);
            }
        });
        gerenciamentoOcorrenciaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerenciamentoOcorrenciaButtonActionPerformed(evt);
            }
        });

        ingressoOcorrenciaButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ingressoOcorrenciaButton.setText("Acessar Sistema de Gerenciamento de Ingressos");
        ingressoOcorrenciaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ingressoOcorrenciaButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gerenciamentoOcorrenciaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ingressoOcorrenciaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addComponent(tituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tituloPrincipal)
                .addGap(18, 18, 18)
                .addComponent(gerenciamentoOcorrenciaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ingressoOcorrenciaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gerenciamentoOcorrenciaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerenciamentoOcorrenciaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gerenciamentoOcorrenciaButtonActionPerformed

    private void gerenciamentoOcorrenciaButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gerenciamentoOcorrenciaButtonMouseReleased
        var autenticacaoDialog = new AutenticacaoProfissionalSeguranca(this);
        autenticacaoDialog.setLocationRelativeTo(this);
        autenticacaoDialog.setVisible(true);
    }//GEN-LAST:event_gerenciamentoOcorrenciaButtonMouseReleased

    private void ingressoOcorrenciaButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingressoOcorrenciaButtonMouseReleased
        var gerenciamentoIngressosDialog = new GerenciamentoIngressosDialog(this);
        gerenciamentoIngressosDialog.setLocationRelativeTo(this);
        gerenciamentoIngressosDialog.setVisible(true);
    }//GEN-LAST:event_ingressoOcorrenciaButtonMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaginaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gerenciamentoOcorrenciaButton;
    private javax.swing.JButton ingressoOcorrenciaButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel tituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
