/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.projectaviaclient.gui;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import kz.projectaviaclient.client.SearchClient;
import kz.projectaviaclient.ws.Reservation;


public class DialogCheck extends javax.swing.JDialog {

    private Reservation reservation;

    /**
     * Creates new form DialogBuy
     */
    public DialogCheck(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        showBusy(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCheckCode = new org.jdesktop.swingx.JXTextField();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        btnCheck = new org.jdesktop.swingx.JXButton();
        busyCheck = new org.jdesktop.swingx.JXBusyLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Покупка билета");
        setResizable(false);

        jXLabel1.setText("Код билета");

        btnCheck.setText("Проверить");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtCheckCode, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(busyCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(busyCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCheckCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed

        showBusy(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {


                // loading...
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(300);
                }

                reservation = SearchClient.getInstance().checkReservationByCode(txtCheckCode.getText());//вызов метода для проверки брони

                String message = null;

                //диалог проверки брони
                if (reservation != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Рейс: ").append(reservation.getFlight().getCode()).append("\n");
                    sb.append("Самолет: ").append(reservation.getFlight().getAircraft().getName()).append("\n");
                    sb.append("Дата вылета: ").append(reservation.getFlight().getDateDepart()).append("\n");
                    sb.append("Дата прилета: ").append(reservation.getFlight().getDateCome()).append("\n");
                    sb.append("Место: ").append(reservation.getPlace()).append("\n");
                    
                    message = sb.toString();
                } else {
                    message = "Ничего не найдено"; //если возвратится null то выводим сообщение
                }

                JOptionPane.showMessageDialog(DialogCheck.this,
                        message,
                         "Результаты поиска",
                        JOptionPane.PLAIN_MESSAGE);

                return null;
            }

            @Override
            protected void done() {
                showBusy(false);
            }
        }.execute();
    }//GEN-LAST:event_btnCheckActionPerformed

    private void showBusy(boolean show) {   //
        busyCheck.setVisible(show);         //вызов индикатора загрузки   
        busyCheck.setBusy(show);            //
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btnCheck;
    private org.jdesktop.swingx.JXBusyLabel busyCheck;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXTextField txtCheckCode;
    // End of variables declaration//GEN-END:variables
}
