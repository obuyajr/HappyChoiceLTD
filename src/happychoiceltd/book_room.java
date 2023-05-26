/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happychoiceltd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author felix
 */
public class book_room extends javax.swing.JFrame {

    /**
     * Creates new form book_room
     */
    public book_room() {
        initComponents();
        connect();
        loadToJcombo();
       calculateTotal();
      
       txt_price.setEditable(false);
       txt_total.setEditable(false);
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    //DB connection method
     public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/happy-choice","root","");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch(SQLException ex){
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }



    public void loadToJcombo() {
    try {
        // Retrieve room types from the database
        PreparedStatement pst = con.prepareStatement("SELECT DISTINCT room_type FROM rooms");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            jcombo_roomType.addItem(rs.getString("room_type"));
        }

        // Close the result set and statement
        rs.close();
        pst.close();

        // Add selection listener to the room type combo box
        jcombo_roomType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRoomType = (String) jcombo_roomType.getSelectedItem();

                if (selectedRoomType != null) {
                    // Retrieve the rooms based on the selected room type from the database
                    try {
                        PreparedStatement pst2 = con.prepareStatement("SELECT room_no FROM rooms WHERE room_type = ? AND Status <> 'BOOKED'");
                        pst2.setString(1, selectedRoomType);
                        ResultSet rs2 = pst2.executeQuery();

                        // Clear existing items in the second combo box
                        jcombo_availableRooms.removeAllItems();

                        while (rs2.next()) {
                            jcombo_availableRooms.addItem(rs2.getString("room_no"));
                        }

                        // Close the result set and statement
                        rs2.close();
                        pst2.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        //
                jcombo_availableRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRoom = (String) jcombo_availableRooms.getSelectedItem();

                if (selectedRoom != null) {
                    // Retrieve the price based on the selected room  from the database
                    try {
                        PreparedStatement pst3 = con.prepareStatement("SELECT price FROM rooms WHERE room_no = ? ");
                        pst3.setString(1, selectedRoom);
                        ResultSet rs2 = pst3.executeQuery();

                        // Clear existing items in the second combo box
                        txt_price.setText("");


                        StringBuilder sb = new StringBuilder();
                        while (rs2.next()) {
                        sb.append(rs2.getString("price"));
                        sb.append(" "); // Add a space or any separator between the values
                        }
                        txt_price.setText(sb.toString());


                        // Close the result set and statement
                        rs2.close();
                        pst3.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
              
                //
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    //calculate total
    public void calculateTotal(){

    }

   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcombo_availableRooms = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jcombo_roomType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_custName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_days = new javax.swing.JTextField();
        btn_bookRoom = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_print = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BOOK ROOM");
        setBackground(new java.awt.Color(102, 102, 102));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("AVAILABLE ROOMS");

        jcombo_availableRooms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel3.setText("ROOM TYPE");

        jcombo_roomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jcombo_roomType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcombo_roomTypeMouseClicked(evt);
            }
        });
        jcombo_roomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombo_roomTypeActionPerformed(evt);
            }
        });

        jLabel4.setText("PRICE");

        txt_price.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_price.setForeground(new java.awt.Color(0, 0, 204));

        jLabel5.setText("CUSTOMER NAME");

        jLabel6.setText("PHONE");

        jLabel7.setText("DAYS");

        txt_days.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_daysActionPerformed(evt);
            }
        });

        btn_bookRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-open-regular-24.png"))); // NOI18N
        btn_bookRoom.setText("BOOK ROOM");
        btn_bookRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bookRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bookRoom)
                    .addComponent(jcombo_availableRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcombo_roomType, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_custName, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_days, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_phone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcombo_roomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcombo_availableRooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_custName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addComponent(btn_bookRoom)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("BOOK ROOM");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("customer name");

        jLabel10.setText("room type");

        jLabel12.setText("phone");

        txt_total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_total.setForeground(new java.awt.Color(0, 0, 204));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("TOTAL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer-solid-24.png"))); // NOI18N
        btn_print.setText("PRINT RECEIPT");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btn_print)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btn_print)
                .addGap(17, 17, 17))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_close.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btn_close.setText("CLOSE");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addComponent(btn_close)
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_close)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new login().setVisible(true);
    }//GEN-LAST:event_btn_closeActionPerformed

    private void jcombo_roomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombo_roomTypeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jcombo_roomTypeActionPerformed

    private void jcombo_roomTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcombo_roomTypeMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jcombo_roomTypeMouseClicked

    private void btn_bookRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bookRoomActionPerformed
        // TODO add your handling code here:
                 String numOfDaysText = txt_days.getText();
        String priceText = txt_price.getText();
        try{
                
            if (!numOfDaysText.isEmpty() && !priceText.isEmpty()) {
                int numOfDays = Integer.parseInt(numOfDaysText);
                double price = Double.parseDouble(priceText);
                double amount = numOfDays * price;
                txt_total.setText(String.valueOf(amount));
            } else {
                txt_total.setText("");
            }
                
            
       
        } catch (Exception e) {
        e.printStackTrace();
    }
        // Assuming you have obtained the required input values from the user
        String roomType = (String) jcombo_roomType.getSelectedItem();
        String roomNumber = (String) jcombo_availableRooms.getSelectedItem();
        String prices = txt_price.getText();
        String customerName = txt_custName.getText();
        String phoneNumber = txt_phone.getText();
        String noOfDaysText = txt_days.getText();
        String totalAmountText = txt_total.getText();

        // Display the JOptionPane with Yes/No option
        int option = JOptionPane.showConfirmDialog(null, "Confirm booking?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                // Convert price, numOfDays, and totalAmount to appropriate data types
                double price = Double.parseDouble(prices);
                int numOfDays = Integer.parseInt(noOfDaysText);
                double totalAmount = Double.parseDouble(totalAmountText);

                // Update room status to 'BOOKED'
                try {
                    PreparedStatement pst1 = con.prepareStatement("UPDATE rooms SET Status = 'BOOKED' WHERE room_no = ?");
                    pst1.setString(1, roomNumber);
                    pst1.executeUpdate();
                    pst1.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Insert booking information into the 'bookings' table
                PreparedStatement pst = con.prepareStatement("INSERT INTO bookings (room_type, room_no, price, custName, phoneNumber, days, total) VALUES (?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, roomType);
                pst.setString(2, roomNumber);
                pst.setDouble(3, price);
                pst.setString(4, customerName);
                pst.setString(5, phoneNumber);
                pst.setInt(6, numOfDays);
                pst.setDouble(7, totalAmount);

                pst.executeUpdate();
                pst.close();

                // Display success message or perform any additional actions
                JOptionPane.showMessageDialog(rootPane, "Room Booked Successfully");

                // Clear the fields
                // Clear and repopulate the available rooms combo box
                jcombo_availableRooms.removeAllItems();

                try {
                    PreparedStatement pst2 = con.prepareStatement("SELECT room_no FROM rooms WHERE room_type = ? AND Status <> 'BOOKED'");
                    pst2.setString(1, roomType);
                    ResultSet rs2 = pst2.executeQuery();

                    while (rs2.next()) {
                        jcombo_availableRooms.addItem(rs2.getString("room_no"));
                    }

                    rs2.close();
                    pst2.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                //
// Schedule a task to reset the room status to "UNBOOKED" after 1 minute
ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
executorService.schedule(new Runnable() {
    @Override
    public void run() {
        try {
            String roomNo = (String) jcombo_availableRooms.getSelectedItem();
            PreparedStatement pst5 = con.prepareStatement("UPDATE rooms SET Status = 'UNBOOKED' WHERE room_no = ?");
            pst5.setString(1, roomNo);
            pst5.executeUpdate();
            pst5.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}, 1, TimeUnit.MINUTES);


                
                
               //jcombo_roomType.removeAllItems();
            
                txt_price.setText("");
                txt_custName.setText("");
                txt_phone.setText("");
                txt_days.setText("");
                txt_total.setText("");
                jcombo_roomType.requestFocus();
                
                //
             
                //
            } catch (SQLException | NumberFormatException ex) {
                // Handle any errors that may occur during the insertion
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "ROOM BOOKING CANCELED");
        }

        
    
    }//GEN-LAST:event_btn_bookRoomActionPerformed

    
    
    private void txt_daysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_daysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_daysActionPerformed

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
            java.util.logging.Logger.getLogger(book_room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(book_room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(book_room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(book_room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new book_room().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bookRoom;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_print;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox<String> jcombo_availableRooms;
    private javax.swing.JComboBox<String> jcombo_roomType;
    private javax.swing.JTextField txt_custName;
    private javax.swing.JTextField txt_days;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
