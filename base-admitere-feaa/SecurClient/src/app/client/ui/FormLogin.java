/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormLogin.java
 *
 * Created on Jul 22, 2009, 10:31:39 AM
 */

package app.client.ui;

import app.client.controller.RichClientApplication;
import app.controller.AppActionEvent;
import app.controller.patterns.IApplicationEvent;
import app.domain.model.AppUser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author catalin
 */
public class FormLogin extends javax.swing.JFrame
{

    /** Creates new form FormLogin */
    public FormLogin() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        Component mainForm = RichClientApplication.INSTANCE.getMainForm();
        if (mainForm != null){
            int xMain = mainForm.getX();
            int yMain = mainForm.getY();
            int wMain = mainForm.getWidth();
            int hMain = mainForm.getHeight();
            int w = this.getWidth();
            int h = this.getHeight();

            int xiFrame = xMain + ((wMain - w) / 6);
            int yiFrame = yMain + ((hMain - h) / 3);
            this.setLocation(xiFrame, yiFrame);
        }
        this.txtUsername.grabFocus();
    }

    private void login(){
        IApplicationEvent event = new AppActionEvent("FormLogin", "LoginAction");
        event.setValue("user", new AppUser(null, this.txtUsername.getText().toUpperCase()));
        event.setValue("password", this.txtPassword.getPassword());
        event = RichClientApplication.getActionController().actionPerformed(event);
        if (event.getValue("login").equals(true)){
            JFrame mainForm = (JFrame) RichClientApplication.INSTANCE.getMainForm();
            if (mainForm != null){
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainForm.setFocusableWindowState(true);
                mainForm.setEnabled(true);
                mainForm.setVisible(true);
            }
            this.dispose();
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("L O G I N -- S E C U R I T A T E --  A D M I T E R E");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nume utilizator");

        txtUsername.setFont(new java.awt.Font("Arial", 1, 14));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Parola");

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/client/ui/icons/Access key 32.png"))); // NOI18N
        btnLogin.setText("Log in");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login();
}//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == 10){
           login();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
