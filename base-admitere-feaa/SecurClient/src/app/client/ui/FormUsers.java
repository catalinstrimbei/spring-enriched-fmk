/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormUsers.java
 *
 * Created on Jul 20, 2009, 11:16:56 PM
 */

package app.client.ui;

import app.client.controller.RichClientApplication;
import app.controller.AppActionEvent;
import app.controller.patterns.IApplicationEvent;
import app.domain.model.AppRole;
import app.domain.model.AppUser;
import app.domain.model.RoleAttachment;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author catalin
 */
public class FormUsers extends javax.swing.JFrame {

    /** Creates new form FormUsers */
    public FormUsers() {
        initComponents();
        IApplicationEvent evt = new AppActionEvent("FormUsers", "LoadUsers");
        evt = RichClientApplication.getActionController().actionPerformed(evt);
        this.users.addAll((List) evt.getValue("users"));

        IApplicationEvent evtRoles = new AppActionEvent("FormBatchRoleOperations", "LoadRoles");
        evtRoles = RichClientApplication.getActionController().actionPerformed(evtRoles);
        this.appRoles.addAll((List) evtRoles.getValue("roles"));

        this.pack();
        this.bindingGroup.getBinding("bindLstUsers").unbind();
        this.bindingGroup.getBinding("bindLstUsers").bind();
        this.lstUsers.setSelectedIndex(0);
    }

    public void setCurrentUser(AppUser user){
        if (user != null)
            this.lstUsers.setSelectedIndex(this.users.indexOf(user));
        else
            this.lstUsers.setSelectedIndex(0);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        users = new java.util.ArrayList<app.domain.model.AppUser>();
        userEnabledConvertor = new app.client.ui.UserEnabledConvertor();
        appRoles = new java.util.ArrayList<app.domain.model.AppRole>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstUsers = new javax.swing.JList();
        txtDbuser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        btnRefresh = new javax.swing.JButton();
        btnAbandonRefresh = new javax.swing.JButton();
        btnSalveazaModificarile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        chkEnabled = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        cmdAddRole = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, users, lstUsers, "bindLstUsers");
        jListBinding.setDetailBinding(org.jdesktop.beansbinding.ELProperty.create("${dbUser}"));
        bindingGroup.addBinding(jListBinding);

        jScrollPane1.setViewportView(lstUsers);

        txtDbuser.setEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstUsers, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.dbUser}"), txtDbuser, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel1.setText("Roluri atasate");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedElement.attachedRoles}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstUsers, eLProperty, jTable1, "bindTblRoles");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${role}"));
        columnBinding.setColumnName("Role");
        columnBinding.setColumnClass(app.domain.model.AppRole.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enabled}"));
        columnBinding.setColumnName("Enabled");
        columnBinding.setColumnClass(Boolean.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setText("Nume utilizator");

        jLabel3.setText("Stare");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPEN", "LOCKED", "NOT_CREATED" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstUsers, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.status}"), jComboBox1, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnAbandonRefresh.setText("Abandon&Refresh");
        btnAbandonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonRefreshActionPerformed(evt);
            }
        });

        btnSalveazaModificarile.setText("Salveaza modificarile");
        btnSalveazaModificarile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalveazaModificarileActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstUsers, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enabled}"), chkEnabled, org.jdesktop.beansbinding.BeanProperty.create("selected"), "bindChkEnabled");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, lstUsers, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enabled}"), chkEnabled, org.jdesktop.beansbinding.BeanProperty.create("text"), "bindChkEnabled_Text");
        binding.setSourceNullValue("Inactiv");
        binding.setConverter(userEnabledConvertor);
        bindingGroup.addBinding(binding);

        chkEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEnabledActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(chkEnabled, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(chkEnabled)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jButton1.setText("Reseteaza parola");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmdAddRole.setText("Adauga rol");
        cmdAddRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalveazaModificarile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAbandonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                        .addGap(45, 45, 45)))
                                .addGap(22, 22, 22))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdAddRole, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jComboBox1, 0, 300, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addComponent(txtDbuser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDbuser)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1)
                            .addComponent(jLabel3))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRefresh)
                                .addComponent(cmdAddRole)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAbandonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addComponent(btnSalveazaModificarile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        AppUser selectedUser = this.users.get(this.lstUsers.getSelectedIndex());
        IApplicationEvent event = new AppActionEvent("FormUsers", "LoadUser");
        event.setValue("user", selectedUser);
        event = RichClientApplication.getActionController().actionPerformed(event);
        this.users.set(this.users.indexOf(selectedUser), (AppUser)event.getValue("user"));
        this.bindingGroup.getBinding("bindLstUsers").unbind();
        this.bindingGroup.getBinding("bindLstUsers").bind();
        this.bindingGroup.getBinding("bindTblRoles").unbind();
        this.bindingGroup.getBinding("bindTblRoles").bind();
        if (selectedUser != null)
            this.lstUsers.setSelectedIndex(this.users.indexOf(selectedUser));
        else
            this.lstUsers.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSalveazaModificarileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalveazaModificarileActionPerformed
        AppUser selectedUser = this.users.get(this.lstUsers.getSelectedIndex());
        IApplicationEvent event = new AppActionEvent("FormUsers", "SetUserState");
        event.setValue("user", selectedUser);
        event = RichClientApplication.getActionController().actionPerformed(event);
        this.users.set(this.users.indexOf(selectedUser), (AppUser)event.getValue("user"));
        this.bindingGroup.getBinding("bindLstUsers").unbind();
        this.bindingGroup.getBinding("bindLstUsers").bind();
        this.bindingGroup.getBinding("bindTblRoles").unbind();
        this.bindingGroup.getBinding("bindTblRoles").bind();
        if (selectedUser != null)
            this.lstUsers.setSelectedIndex(this.users.indexOf(selectedUser));
        else
            this.lstUsers.setSelectedIndex(0);
}//GEN-LAST:event_btnSalveazaModificarileActionPerformed

    private void btnAbandonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonRefreshActionPerformed
        this.btnRefreshActionPerformed(evt);
}//GEN-LAST:event_btnAbandonRefreshActionPerformed

    private void chkEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEnabledActionPerformed
        if (this.chkEnabled.isSelected())
            this.chkEnabled.setText("Activ");
        else
            this.chkEnabled.setText("Inactiv");
    }//GEN-LAST:event_chkEnabledActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AppUser selectedUser = this.users.get(this.lstUsers.getSelectedIndex());
        IApplicationEvent event = new AppActionEvent("FormUsers", "ResetUserPassword");
        event.setValue("user", selectedUser);
        event = RichClientApplication.getActionController().actionPerformed(event);
        this.users.set(this.users.indexOf(selectedUser), (AppUser)event.getValue("user"));
        this.bindingGroup.getBinding("bindLstUsers").unbind();
        this.bindingGroup.getBinding("bindLstUsers").bind();
        this.bindingGroup.getBinding("bindTblRoles").unbind();
        this.bindingGroup.getBinding("bindTblRoles").bind();
        if (selectedUser != null)
            this.lstUsers.setSelectedIndex(this.users.indexOf(selectedUser));
        else
            this.lstUsers.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmdAddRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddRoleActionPerformed
      AppUser selectedUser = this.users.get(this.lstUsers.getSelectedIndex());
      ArrayList<Object> userRoles = new ArrayList<Object>();
      for (RoleAttachment attachedRole: selectedUser.getAttachedRoles()){
          userRoles.add(attachedRole.getRole());
      }

      System.out.println(" Before selection dialog !!! " + this.appRoles.size());

      List<AppRole> selectedRoles = LOVSelectionPanel.showSelectionDialog(
              "Selectati un sau mai multe roluri: ", this.appRoles, "roleName", (Component) evt.getSource());
       
       System.out.println(" After selection dialog !!! ");
       for (AppRole selectedRole : selectedRoles){
           System.out.println("Selected roles: " + selectedRole);
           if (!userRoles.contains(selectedRole)){
               selectedUser.getAttachedRoles().add(new RoleAttachment(selectedUser, selectedRole, "Y"));
           }
       }

        this.bindingGroup.getBinding("bindLstUsers").unbind();
        this.bindingGroup.getBinding("bindLstUsers").bind();
        this.bindingGroup.getBinding("bindTblRoles").unbind();
        this.bindingGroup.getBinding("bindTblRoles").bind();
        if (selectedUser != null)
            this.lstUsers.setSelectedIndex(this.users.indexOf(selectedUser));
        else
            this.lstUsers.setSelectedIndex(0);
    }//GEN-LAST:event_cmdAddRoleActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.ArrayList<app.domain.model.AppRole> appRoles;
    private javax.swing.JButton btnAbandonRefresh;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSalveazaModificarile;
    private javax.swing.JCheckBox chkEnabled;
    private javax.swing.JButton cmdAddRole;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JList lstUsers;
    private javax.swing.JTextField txtDbuser;
    private app.client.ui.UserEnabledConvertor userEnabledConvertor;
    private java.util.ArrayList<app.domain.model.AppUser> users;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
