/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.Gui;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Gui.List.DeviceList;
import cz.muni.fi.pb138.Gui.List.PortList;
import cz.muni.fi.pb138.Gui.form.DeviceForm;
import cz.muni.fi.pb138.Gui.form.PortForm;
import cz.muni.fi.pb138.Main.ListOfDevices;
import cz.muni.fi.pb138.Managers.DeviceManager;
import cz.muni.fi.pb138.Managers.DeviceManagerImpl;
import cz.muni.fi.pb138.Managers.PortManager;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Magdalena Kunikova
 */
public class Main extends javax.swing.JFrame {
    private SwingWorker swingWorker;
    private DeviceManager deviceManager;
    private DeviceForm deviceForm;
    private List<Device> devices;
    private int rowIndex;
    public final int REMOVE = 0;
    public final int ADD = 1;
    public final int EDIT = 2;
    private Port port;
    int spot;
    String name;
    DeviceType deviceType;
    
    private static final String computerPath = "C:\\Users\\Mags\\Desktop\\computer.png";// "computer.png";
    private static final String hubPath = "hub.png";
    private static final String modemPath = "modem.png";
    private static final String routerPath = "router.png";
    private static final String switch12Path = "12switch.png";
    private static final String switch24Path = "24switch.png";
    private static final String switch48Path = "48switch.png";
    

    public Main() {
        ListOfDevices listOfDevices = new ListOfDevices();
        deviceManager = new DeviceManagerImpl();
        devices = listOfDevices.getListOfDevices();
        rowIndex = -1;
        deviceForm = new DeviceForm(deviceManager);
        
        initComponents();
        deviceForm.addWindowListener( new WindowAdapter() { 
            @Override
            public void windowClosed(WindowEvent we) {
                spot = deviceForm.getSpot();
                deviceType = deviceForm.getDeviceType();
                name = deviceForm.getName();
                editLabel(name, deviceType, spot);
            }
        } );
    }

    private class editSwingWorker extends SwingWorker<Integer, Void> {
        Main frame;
        Integer deviceNumber;
        DeviceManager deviceManager;

        public editSwingWorker(Main frame, Integer deviceNumber, DeviceManager deviceManager) {
            this.frame = frame;
            this.deviceNumber = deviceNumber;
            this.deviceManager = deviceManager;
        }

        @Override
        protected Integer doInBackground() throws Exception {
            //Device device = deviceManager.findDeviceById( deviceNumber );
            //DeviceForm deviceForm = new DeviceForm( frame, device );
            //deviceForm.setVisible(true);
            return 0;
        }

        @Override
        protected void done() {
            swingWorker = null;
        }
    }

    private class deleteSwingWorker extends SwingWorker<Integer, Void> {
        DeviceManager deviceManager;

        public deleteSwingWorker(DeviceManager deviceManager) {
            this.deviceManager = deviceManager;
        }

        @Override
        protected Integer doInBackground() throws Exception {
        //    rowIndex = tableDevice.getSelectedRow();
            //    Main_TableModel deviceModel = (Main_TableModel) tableDevice.getModel();
            //    Integer deviceNumber = (Integer) deviceModel.getValueAt( rowIndex, 0 );
            //    Device device = deviceManager.findDeviceById( deviceNumber );
            //    deviceManager.removeDevice( device.getDid() );
            //    refreshDevices( device.get( rowIndex ), REMOVE );
            return 0;
        }

        @Override
        protected void done() {
            swingWorker = null;
        }
    }

    private void editLabel(String name, DeviceType deviceType, int spot) {
        this.name = name;
        this.deviceType = deviceType;
        this.spot = spot;
        JLabel label = new JLabel();
        String labelNumber = "labelSpot" + spot;
        try {
            label = (JLabel) getClass().getDeclaredField(labelNumber).get(this);
        } catch ( Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        label.setText(name);
        
        Icon image = null;
        String deviceTypeString = deviceType.toString().toUpperCase();
        switch(deviceTypeString) {
            case "COMPUTER" : 
                image = new ImageIcon(computerPath);
                break;
            case "HUB" :
                image = new ImageIcon(hubPath);
                break;
            case "MODEM" :
                image = new ImageIcon(modemPath);
                break;
            case "ROUTER" :
                image = new ImageIcon(routerPath);
                break;
            case "SWITCH12" :
                image = new ImageIcon(switch12Path);
                break;
            case "SWITCH24" :
                image = new ImageIcon(switch24Path);
                break;
            case "SWITCH48" :
                image = new ImageIcon(switch48Path);
                break;
        }
        label.setIcon(image);
        label.setHorizontalAlignment( SwingConstants.CENTER );
        label.setVerticalAlignment( SwingConstants.CENTER );
        //label.setHorizontalTextPosition( SwingConstants.CENTER );
	//label.setVerticalTextPosition( SwingConstants.BOTTOM ); 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAddDevice = new javax.swing.JButton();
        buttonDeleteDevice = new javax.swing.JButton();
        buttonAddPort = new javax.swing.JButton();
        buttonDeletePort = new javax.swing.JButton();
        buttonEditDevice = new javax.swing.JButton();
        labelDevice = new javax.swing.JLabel();
        labelPort = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        labelSpot1 = new javax.swing.JLabel();
        labelSpot2 = new javax.swing.JLabel();
        labelSpot3 = new javax.swing.JLabel();
        labelSpot4 = new javax.swing.JLabel();
        labelSpot5 = new javax.swing.JLabel();
        labelSpot6 = new javax.swing.JLabel();
        labelSpot7 = new javax.swing.JLabel();
        labelSpot8 = new javax.swing.JLabel();
        labelSpot9 = new javax.swing.JLabel();
        labelSpot10 = new javax.swing.JLabel();
        labelSpot11 = new javax.swing.JLabel();
        labelSpot12 = new javax.swing.JLabel();
        labelSpot13 = new javax.swing.JLabel();
        labelSpot14 = new javax.swing.JLabel();
        labelSpot15 = new javax.swing.JLabel();
        labelSpot16 = new javax.swing.JLabel();
        labelSpot17 = new javax.swing.JLabel();
        labelSpot18 = new javax.swing.JLabel();
        labelSpot19 = new javax.swing.JLabel();
        labelSpot20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonAddDevice.setText("Add device");
        buttonAddDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddDeviceActionPerformed(evt);
            }
        });

        buttonDeleteDevice.setText("Delete device");
        buttonDeleteDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteDeviceActionPerformed(evt);
            }
        });

        buttonAddPort.setText("Add port");
        buttonAddPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddPortActionPerformed(evt);
            }
        });

        buttonDeletePort.setText("Delete port");
        buttonDeletePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeletePortActionPerformed(evt);
            }
        });

        buttonEditDevice.setText("Edit device");

        labelDevice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelDevice.setText("Device");

        labelPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPort.setText("Port");

        panelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelSpot1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot1.setText("Spot 1");
        labelSpot1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot2.setText("Spot 2");
        labelSpot2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot3.setText("Spot 3");
        labelSpot3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot4.setText("Spot 4");
        labelSpot4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot5.setText("Spot 5");
        labelSpot5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot6.setText("Spot 6");
        labelSpot6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot7.setText("Spot 7");
        labelSpot7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot8.setText("Spot 8");
        labelSpot8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot9.setText("Spot 9");
        labelSpot9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot10.setText("Spot 10");
        labelSpot10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot11.setText("Spot 11");
        labelSpot11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot12.setText("Spot 12");
        labelSpot12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot13.setText("Spot 13");
        labelSpot13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot14.setText("Spot 14");
        labelSpot14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot15.setText("Spot 15");
        labelSpot15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot16.setText("Spot 16");
        labelSpot16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot17.setText("Spot 17");
        labelSpot17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot18.setText("Spot 18");
        labelSpot18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot19.setText("Spot 19");
        labelSpot19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelSpot20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSpot20.setText("Spot 20");
        labelSpot20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(labelSpot16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(labelSpot17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(labelSpot18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(labelSpot19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(labelSpot20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContentLayout.createSequentialGroup()
                            .addComponent(labelSpot11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(labelSpot12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(labelSpot13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(labelSpot14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(labelSpot15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(labelSpot6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(labelSpot1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(labelSpot5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSpot1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSpot6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSpot11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSpot16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSpot20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelPort)
                    .addComponent(labelDevice)
                    .addComponent(buttonDeleteDevice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddDevice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDeletePort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEditDevice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDevice)
                .addGap(18, 18, 18)
                .addComponent(buttonAddDevice)
                .addGap(11, 11, 11)
                .addComponent(buttonEditDevice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDeleteDevice)
                .addGap(29, 29, 29)
                .addComponent(labelPort)
                .addGap(18, 18, 18)
                .addComponent(buttonAddPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDeletePort)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDeviceActionPerformed
        deviceForm.setVisible(true);
    }//GEN-LAST:event_buttonAddDeviceActionPerformed

    private void buttonAddPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddPortActionPerformed
        PortForm portForm = new PortForm(devices, deviceManager);
        portForm.setVisible(true);
    }//GEN-LAST:event_buttonAddPortActionPerformed

    private void buttonDeleteDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteDeviceActionPerformed
        //rowIndex = tableInvoices.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(rootPane, "No device was selected.");
        } else {
            int choice = JOptionPane.showConfirmDialog(rootPane, "Do you really want to delete the device?", null, JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                if (swingWorker != null) {
                    throw new IllegalStateException("Operation was not accomplished yet.");
                }
                //swingWorker = new deleteSwingWorker(deviceManager);
                swingWorker.execute();
            }
        }
    }//GEN-LAST:event_buttonDeleteDeviceActionPerformed

    private void buttonDeletePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeletePortActionPerformed
        //rowIndex = tableInvoices.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(rootPane, "No port was selected.");
        } else {
            int choice = JOptionPane.showConfirmDialog(rootPane, "Do you really want to delete the port?", null, JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                if (swingWorker != null) {
                    throw new IllegalStateException("Operation was not accomplished yet.");
                }
                //swingWorker = new deleteSwingWorker(deviceManager);
                swingWorker.execute();
            }
        }
    }//GEN-LAST:event_buttonDeletePortActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddDevice;
    private javax.swing.JButton buttonAddPort;
    private javax.swing.JButton buttonDeleteDevice;
    private javax.swing.JButton buttonDeletePort;
    private javax.swing.JButton buttonEditDevice;
    private javax.swing.JLabel labelDevice;
    private javax.swing.JLabel labelPort;
    private javax.swing.JLabel labelSpot1;
    private javax.swing.JLabel labelSpot10;
    private javax.swing.JLabel labelSpot11;
    private javax.swing.JLabel labelSpot12;
    private javax.swing.JLabel labelSpot13;
    private javax.swing.JLabel labelSpot14;
    private javax.swing.JLabel labelSpot15;
    private javax.swing.JLabel labelSpot16;
    private javax.swing.JLabel labelSpot17;
    private javax.swing.JLabel labelSpot18;
    private javax.swing.JLabel labelSpot19;
    private javax.swing.JLabel labelSpot2;
    private javax.swing.JLabel labelSpot20;
    private javax.swing.JLabel labelSpot3;
    private javax.swing.JLabel labelSpot4;
    private javax.swing.JLabel labelSpot5;
    private javax.swing.JLabel labelSpot6;
    private javax.swing.JLabel labelSpot7;
    private javax.swing.JLabel labelSpot8;
    private javax.swing.JLabel labelSpot9;
    private javax.swing.JPanel panelContent;
    // End of variables declaration//GEN-END:variables
}
