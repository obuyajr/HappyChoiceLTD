/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happychoiceltd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewClass extends JFrame {

    private List<String> availableRooms;
    private JComboBox<String> roomComboBox;
    private JButton bookButton;

    public NewClass() {
        availableRooms = new ArrayList<>();
        availableRooms.add("Room 1");
        availableRooms.add("Room 2");
        availableRooms.add("Room 3");

        roomComboBox = new JComboBox<>(availableRooms.toArray(new String[0]));
        roomComboBox.setBounds(50, 50, 200, 30);
        add(roomComboBox);

        bookButton = new JButton("Book");
        bookButton.setBounds(100, 100, 100, 30);
        add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRoom = (String) roomComboBox.getSelectedItem();
                bookRoom(selectedRoom);
            }
        });

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void bookRoom(String room) {
        JOptionPane.showMessageDialog(this, "Room " + room + " booked successfully!");
        // Add your booking logic here, such as updating a database or storing the booking information.
    }

    public static void main(String[] args) {
        new NewClass();
    }
}

