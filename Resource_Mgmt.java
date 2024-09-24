package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ResourceManagementApp extends JFrame {

    private JTextField resourceIdField, resourceNameField, timelineField, quantityField, costField;
    private JTextArea resourceListArea;

    public ResourceManagementApp() {
        super("Resource Management System");

        
        resourceIdField = new JTextField(10);
        resourceNameField = new JTextField(20);
        timelineField = new JTextField(10);
        quantityField = new JTextField(5);
        costField = new JTextField(10);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertResource();
            }
        });

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                listResources();
            }
        });

        resourceListArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(resourceListArea);

      
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Resource ID:"));
        inputPanel.add(resourceIdField);
        inputPanel.add(new JLabel("Resource Name:"));
        inputPanel.add(resourceNameField);
        inputPanel.add(new JLabel("Timeline:"));
        inputPanel.add(timelineField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Cost:"));
        inputPanel.add(costField);
        inputPanel.add(insertButton);
        inputPanel.add(selectButton);

      
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); 
    }

    private void insertResource() {
        
        String resourceName = resourceNameField.getText();
        String timeline = timelineField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double cost = Double.parseDouble(costField.getText());

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_db", "root", "password")) {
            String sql = "INSERT INTO resource (resource_name, timeline, quantity, cost) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, resourceName);
            stmt.setString(2, timeline);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, cost);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Resource added successfully");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting resource: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listResources() {
        resourceListArea.setText(""); 

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_db", "root", "password")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM resource");

            while (rs.next()) {
                int resourceId = rs.getInt("resource_id");
                String resourceName = rs.getString("resource_name");
                String timeline = rs.getString("timeline");
                int quantity = rs.getInt("quantity");
                double cost = rs.getDouble("cost");

                String resourceInfo = String.format("ID: %d, Name: %s, Timeline: %s, Quantity: %d, Cost: %.2f\n",
                        resourceId, resourceName, timeline, quantity, cost);
                resourceListArea.append(resourceInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching resources: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        resourceNameField.setText("");
        timelineField.setText("");
        quantityField.setText("");
        costField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ResourceManagementApp app = new ResourceManagementApp();
                app.setVisible(true);
            }
        });
    }
}
