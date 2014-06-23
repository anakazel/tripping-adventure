package com.composition.ui;

import com.composition.Deployer;
import com.composition.model.Operation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * @author alexg
 */
public final class MainJFrame extends javax.swing.JFrame {

    private static String configFilePath = null;

    public MainJFrame() throws IOException {
        initComponents();
        customInit();
    }

    private void checkDemo() throws IOException {
        if(configFilePath != null){
            // load demo data
            final Properties propFile = new Properties();
            final InputStream input = new FileInputStream(configFilePath);
            propFile.load(input);

            final int count = Integer.parseInt(propFile.getProperty("operations"));
            final DefaultTableModel model = (DefaultTableModel) tableOperations.getModel();

            for(int i = 1; i <= count; ++i){
                final Vector rowData = new Vector();
                rowData.add(propFile.getProperty("operation" + i + ".serviceName"));
                rowData.add(propFile.getProperty("operation" + i + ".baseUrl"));
                rowData.add(propFile.getProperty("operation" + i + ".location"));
                rowData.add(propFile.getProperty("operation" + i + ".name"));
                rowData.add(propFile.getProperty("operation" + i + ".httpMethod"));
                rowData.add(propFile.getProperty("operation" + i + ".request.contentType"));
                rowData.add(propFile.getProperty("operation" + i + ".response.contentType"));
                final List<String> params = new ArrayList<String>();
                final String paramsProp = propFile.getProperty("operation" + i + ".params");
                if(paramsProp.contains(",")){
                    String[] split = paramsProp.split(",");
                    for(int j = 0; j < split.length; ++j){
                        params.add(split[j]);
                    }
                }else{
                    params.add(paramsProp);
                }
                params.add(propFile.getProperty("operation" + i + ".params"));
                rowData.add(params.get(0));
                model.addRow(rowData);
            }
        }
    }

    private void customInit() throws IOException {
        final TableColumn columnHttpMethod = tableOperations.getColumnModel().getColumn(UIConstants.HTTP_METHOD_COLUMN_IDX);
        columnHttpMethod.setCellEditor(new CustomTableCellEditor(UIConstants.HTTP_METHODS));
        final TableColumn columnRequestType1 = tableOperations.getColumnModel().getColumn(UIConstants.REQUEST_CONTENT_TYPE_COLUMN_IDX);
        final TableColumn columnRequestType2 = tableOperations.getColumnModel().getColumn(UIConstants.RESPONSE_CONTENT_TYPE_COLUMN_IDX);
        columnRequestType1.setCellEditor(new CustomTableCellEditor(UIConstants.REQUEST_TYPES));
        columnRequestType2.setCellEditor(new CustomTableCellEditor(UIConstants.REQUEST_TYPES));

        final DefaultTableModel model = (DefaultTableModel) tableOperations.getModel();
        if(model.getRowCount() == 1){
            model.removeRow(0);
        }

        checkDemo();

        // redirect output to textarea
        final JTextAreaOutputStream out = new JTextAreaOutputStream (textAreaLog);
        System.setOut(new PrintStream(out));
    }

    private void initComponents() {

        panel = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();
        panelOperations = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOperations = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaLog = new javax.swing.JTextArea();
        buttonStart = new javax.swing.JButton();
        labelPort = new javax.swing.JLabel();
        textFieldPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textFieldContextName = new javax.swing.JTextField();

        textFieldPort.setText(String.valueOf(Deployer.port));
        textFieldContextName.setText(Deployer.context);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REST2WSDL");
        setResizable(false);

        buttonAdd.setText("Add REST Operation");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonRemove.setText("Remove REST Operation");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        tableOperations.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null}
                },
                new String[]{
                        "Service Name", "Base URL", "Location URL", "Name", "HTTP Method", "Request Content Type", "Response Content Type", "Params"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        tableOperations.setRowHeight(20);
        jScrollPane1.setViewportView(tableOperations);

        textAreaLog.setColumns(20);
        textAreaLog.setRows(5);
        jScrollPane2.setViewportView(textAreaLog);

        org.jdesktop.layout.GroupLayout panelOperationsLayout = new org.jdesktop.layout.GroupLayout(panelOperations);
        panelOperations.setLayout(panelOperationsLayout);
        panelOperationsLayout.setHorizontalGroup(
                panelOperationsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jScrollPane1)
                        .add(jScrollPane2)
        );
        panelOperationsLayout.setVerticalGroup(
                panelOperationsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(panelOperationsLayout.createSequentialGroup()
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 359, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonStart.setText("Create Proxy and WSDL");
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        labelPort.setText("Server port:");
        jLabel1.setText("Context name:");

        org.jdesktop.layout.GroupLayout panelLayout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(panelOperations, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(panelLayout.createSequentialGroup()
                                .add(buttonAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(buttonRemove, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 258, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(buttonStart, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                        .add(panelLayout.createSequentialGroup()
                                .add(labelPort)
                                .add(39, 39, 39)
                                .add(textFieldPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(48, 48, 48)
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(textFieldContextName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(panelLayout.createSequentialGroup()
                                .add(panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(textFieldPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(labelPort)
                                        .add(jLabel1)
                                        .add(textFieldContextName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(buttonAdd)
                                        .add(buttonRemove)
                                        .add(buttonStart))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(panelOperations, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(panel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {
        final Vector rowData = new Vector();
        final DefaultTableModel model = (DefaultTableModel) tableOperations.getModel();
        model.addRow(rowData);
    }

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        final DefaultTableModel model = (DefaultTableModel) tableOperations.getModel();
        if(model.getRowCount() != 0){
            model.removeRow(model.getRowCount() - 1);
        }
    }

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {
        final int port = Integer.parseInt(textFieldPort.getText());
        final String contextName = textFieldContextName.getText();
        final List<Operation> operations = new ArrayList<Operation>();
        final TableModel tableModel = tableOperations.getModel();

        for(int row = 0;row < tableModel.getRowCount();row++) {
            final Operation o = new Operation();
            for(int col = 0;col < tableModel.getColumnCount();col++) {
                o.setServiceName((String) tableModel.getValueAt(row, UIConstants.SERVICE_NAME_COLUMN_IDX));
                o.setBaseUrl((String) tableModel.getValueAt(row, UIConstants.BASE_URL_COLUMN_IDX));
                o.setLocation((String) tableModel.getValueAt(row, UIConstants.LOCATION_URL_COLUMN_IDX));
                o.setName((String) tableModel.getValueAt(row, UIConstants.NAME_COLUMN_IDX));
                o.setHttpMethod((String) tableModel.getValueAt(row, UIConstants.HTTP_METHOD_COLUMN_IDX));
                o.setRequestContentType((String) tableModel.getValueAt(row, UIConstants.REQUEST_CONTENT_TYPE_COLUMN_IDX));
                o.setResponseContentType((String) tableModel.getValueAt(row, UIConstants.RESPONSE_CONTENT_TYPE_COLUMN_IDX));
                final List<String> params = new ArrayList<String>();
                params.add((String) tableModel.getValueAt(row, UIConstants.PARAMS_COLUMN_IDX));
                o.setParams(params);
            }
            operations.add(o);
            System.out.println(o);
        }

        Deployer.OPERATIONS = operations;
        Deployer.port = port;
        Deployer.context = contextName;

        try {
            Deployer.startServerAndCreateWSDL();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        buttonStart.setEnabled(false);
        buttonAdd.setEnabled(false);
        buttonRemove.setEnabled(false);
        textFieldContextName.setEnabled(false);
        textFieldPort.setEnabled(false);
        JOptionPane.showMessageDialog(this, "WSDL document created at /tmp/wsdl.xml.", "Info", 1);

    }

    public static void main(String args[]) {
        try{
            Deployer.port = Integer.parseInt(args[0]);
            Deployer.context = args[1];
            configFilePath = args[2];
        }catch(Exception ex){
            System.out.println("configFilePath is null.");
            configFilePath = null;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final MainJFrame frame;
                try {
                    frame = new MainJFrame();
                    frame.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textFieldPort;
    private javax.swing.JLabel labelPort;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelOperations;
    private javax.swing.JTable tableOperations;
    private javax.swing.JTextArea textAreaLog;
    private javax.swing.JTextField textFieldContextName;
}