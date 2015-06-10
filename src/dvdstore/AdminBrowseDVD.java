/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdstore;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author carleqladios
 */
public class AdminBrowseDVD extends javax.swing.JFrame {

    /**
     * Creates new form BrowseDVDAdmin
     */
    public AdminBrowseDVD() {
        initComponents();
        this.setLocationRelativeTo(null);
                dvdList.addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent arg0) {
                 if (!arg0.getValueIsAdjusting()) {
                     displayInfo();
                 }
             }
         }); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackBtn = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ArrayList <DVD> DVDList = User.browseDVD();
        final String [] DVDTitle = new String [DVDList.size()];
        for(int i=0;i<DVDList.size();i++)
        {
            DVDTitle[i]=DVDList.get(i).getName();
        }
        dvdList = new javax.swing.JList(DVDTitle);
        jScrollPane2 = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        Add = new javax.swing.JButton();
        Update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackBtn.setText("Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        dvdList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = DVDTitle;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(dvdList);

        infoArea.setEditable(false);
        infoArea.setColumns(20);
        infoArea.setRows(5);
        jScrollPane2.setViewportView(infoArea);

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Update)
                            .addComponent(Delete)
                            .addComponent(Add))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Loads Information Into the jTextArea
     */
    public static void displayInfo()
    {
        ArrayList <DVD> DVDList = User.browseDVD();
        DVD x = DVDList.get(dvdList.getSelectedIndex());
        String temp;
        if(x.getAvailability())
        temp="Available";
        else
        temp="Not Available";
        infoArea.setText("ID: "+x.getID()+"\nName: "+x.getName()+"\nPrice: "+x.getPrice()+"\nGenre: "+x.getGenre()+"\nYear: "+x.getYear()+"\nAvailability: "+temp);
    }
    
    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        // TODO add your handling code here:
        AdminMain am= new AdminMain();
        am.setVisible(true);
        am.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_BackBtnActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        ArrayList <DVD> DVDList = User.browseDVD();
        DVD x = DVDList.get(dvdList.getSelectedIndex());
        Admin.deleteDVD(x.getName());
        JOptionPane.showMessageDialog(this, "Successfully removed.");
        AdminBrowseDVD b= new AdminBrowseDVD();
        b.setVisible(true);
        b.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_DeleteActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        AdminAddDVDMenu am = new AdminAddDVDMenu();
        am.setVisible(true);
        am.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_AddActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        ArrayList <DVD> DVDList = User.browseDVD();
        DVD x = DVDList.get(dvdList.getSelectedIndex());
        String name,genre;
        int id,q,year;
        double price;
        id= Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the new id"));
        name=JOptionPane.showInputDialog(this, "Enter the new name");
        if (name==null)
            return;
        while(name.isEmpty())
        {
        JOptionPane.showMessageDialog(this, "You entered null value."); 
        name=JOptionPane.showInputDialog(this, "Enter the new name");
        }
        price= Double.parseDouble(JOptionPane.showInputDialog(this, "Enter the new price"));
        q= Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the new quantity"));
        genre= JOptionPane.showInputDialog(this, "Enter the new genre");
        year= Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the new year"));
         if(Admin.searchDVD(name))
           JOptionPane.showMessageDialog(this, "DVD Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
        if(Admin.updateDVD(x.getName(), id, name, price, q, genre, year))
        {
            JOptionPane.showMessageDialog(this, "Successfully updated.");
            AdminBrowseDVD b= new AdminBrowseDVD();
            b.setVisible(true);
            b.setLocationRelativeTo(null);
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(this, "Update failed.");
    }//GEN-LAST:event_UpdateActionPerformed

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
            java.util.logging.Logger.getLogger(AdminBrowseDVD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminBrowseDVD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminBrowseDVD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminBrowseDVD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminBrowseDVD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Update;
    private static javax.swing.JList dvdList;
    private static javax.swing.JTextArea infoArea;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}