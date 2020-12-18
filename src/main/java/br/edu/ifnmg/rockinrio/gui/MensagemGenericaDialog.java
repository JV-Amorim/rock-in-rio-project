/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.gui;

public class MensagemGenericaDialog extends javax.swing.JDialog {

    /**
     * Dialog Genérico para exibição de informações.
     *
     * @param parent JFrame que invocou este dialog.
     * @param windowTitulo Título da janela.
     * @param mensagemTitulo Mensagem principal, que identifica as informações
     * contidas na janela.
     * @param mensagem Mensagem completa, com os detalhes
     */
    public MensagemGenericaDialog(java.awt.Frame parent, String windowTitulo, String mensagemTitulo, String mensagem) {
        super(parent, true);
        initComponents();

        super.setTitle(windowTitulo);
        mensagemTituloLabel.setText(mensagemTitulo);
        mensagemTextArea.setText(mensagem);
        mensagemTextArea.setEditable(false);
    }

    /**
     * Dialog Genérico para exibição de informações.
     *
     * @param parent JDialog que invocou este dialog.
     * @param windowTitulo Título da janela.
     * @param mensagemTitulo Mensagem principal, que identifica as informações
     * contidas na janela.
     * @param mensagem Mensagem completa, com os detalhes
     */
    public MensagemGenericaDialog(java.awt.Dialog parent, String windowTitulo, String mensagemTitulo, String mensagem) {
        super(parent, true);
        initComponents();

        super.setTitle(windowTitulo);
        mensagemTituloLabel.setText(mensagemTitulo);
        mensagemTextArea.setText(mensagem);
        mensagemTextArea.setEditable(false);
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
        mensagemTituloLabel = new javax.swing.JLabel();
        mensagemScrollPanel = new javax.swing.JScrollPane();
        mensagemTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lorem Ipsum");
        setModal(true);
        setResizable(false);

        mensagemTituloLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mensagemTituloLabel.setText("Lorem Ipsum");

        mensagemTextArea.setEditable(false);
        mensagemTextArea.setColumns(20);
        mensagemTextArea.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        mensagemTextArea.setLineWrap(true);
        mensagemTextArea.setRows(5);
        mensagemScrollPanel.setViewportView(mensagemTextArea);

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(mensagemTituloLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(mensagemScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(mensagemTituloLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
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
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane mensagemScrollPanel;
    private javax.swing.JTextArea mensagemTextArea;
    private javax.swing.JLabel mensagemTituloLabel;
    private javax.swing.JPanel painelPrincipal;
    // End of variables declaration//GEN-END:variables
}
