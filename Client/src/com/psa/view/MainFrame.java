package com.psa.view;

import com.psa.algorithms.FirstComeFirstServed;
import com.psa.algorithms.RoundRobin;
import com.psa.model.Process;

import com.psa.util.ExcelRead;

import com.psa.util.ExcelWrite;

import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.Queue;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author omaar
 */
public class MainFrame extends javax.swing.JFrame {

    private List<Process> inputProcesses = new ArrayList<>();
    private Queue outputQueue = new LinkedList<>();

    private String currentAlgorithmTitle = "";

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        
        //set frame title
        setTitle("Task Scheduling Application");
        
        //clear input and output tables
        clearOutputTable();
        clearInputTable();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        frameTitleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTable = new javax.swing.JTable();
        algorithmComboBox = new javax.swing.JComboBox<>();
        runAlgorithmButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        quantumTestField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chooseExcelButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        frameTitleLabel.setFont(new java.awt.Font("Acme", 0, 24)); // NOI18N
        frameTitleLabel.setText("Task Scheduling Application (GROUP 3)");
        getContentPane().add(frameTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 32));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Choose Algorithm");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        exportButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });
        jPanel1.add(exportButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 100, 25));

        outputTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        outputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TASK", "START", "END", "DURATION", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outputTable.setMaximumSize(new java.awt.Dimension(240, 64));
        outputTable.setPreferredSize(new java.awt.Dimension(300, 5000));
        outputTable.setRowHeight(25);
        jScrollPane1.setViewportView(outputTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 390, 440));

        algorithmComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        algorithmComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "FCFS", "SJF", "SRTF", "RR", "EDF" }));
        algorithmComboBox.setPreferredSize(new java.awt.Dimension(100, 25));
        algorithmComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                algorithmComboBoxItemStateChanged(evt);
            }
        });
        jPanel1.add(algorithmComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, -1));

        runAlgorithmButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        runAlgorithmButton.setText("Run Algorithm");
        runAlgorithmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runAlgorithmButtonActionPerformed(evt);
            }
        });
        jPanel1.add(runAlgorithmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 100, 25));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 410, 540));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        quantumTestField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel2.add(quantumTestField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 100, 25));

        jLabel2.setText("Quantum");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        chooseExcelButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chooseExcelButton.setText("Choose Excel File");
        chooseExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseExcelButtonActionPerformed(evt);
            }
        });
        jPanel2.add(chooseExcelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 150, 25));

        inputTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TASK", "ARRIVAL", "DURATION", "DEADLINE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inputTable.setMaximumSize(new java.awt.Dimension(240, 64));
        inputTable.setPreferredSize(new java.awt.Dimension(300, 500));
        inputTable.setRowHeight(25);
        jScrollPane2.setViewportView(inputTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 370, 440));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 390, 540));

        pack();
    }//GEN-END:initComponents

    private void chooseExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseExcelButtonActionPerformed
        onChooseExcelButtonClick();
    }//GEN-LAST:event_chooseExcelButtonActionPerformed

    private void runAlgorithmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runAlgorithmButtonActionPerformed
        onRunAlgorithmButtonClick();
    }//GEN-LAST:event_runAlgorithmButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        exportExcel();
    }//GEN-LAST:event_exportButtonActionPerformed

    private void algorithmComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_algorithmComboBoxItemStateChanged
        clearOutputTable();
    }//GEN-LAST:event_algorithmComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> algorithmComboBox;
    private javax.swing.JButton chooseExcelButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel frameTitleLabel;
    private javax.swing.JTable inputTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable outputTable;
    private javax.swing.JTextField quantumTestField;
    private javax.swing.JButton runAlgorithmButton;
    // End of variables declaration//GEN-END:variables

    //-------------------------------Input methods--------------------------------------------

    private void onChooseExcelButtonClick() {

        //Create JFileChooser Object
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("xls files", "xls"); //filter to show only that
        fileChooser.addChoosableFileFilter(filter); //add filter to the fileChooser
        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
        //set mode to view files and directories
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //set USER DESKTOP as the opening directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        int returnValue = fileChooser.showOpenDialog(null); //get returned value
        //if returned value equals APPROVE_OPTION
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); //get selected file
            String filePath = selectedFile.getAbsolutePath(); //get selected path
            try {

                //clear table and last selections
                clearInputTable();
                inputProcesses.clear();

                //create excel read file object
                ExcelRead excelRead = new ExcelRead();
                //get processes from the selected excel file
                inputProcesses = excelRead.readProcesses(filePath);
                try {
                    //set processes to the input tablee
                    setInputTableModel(inputProcesses);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Unkown Error Occured, Please try again later");
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                                              "Unkown Error Occured, Please try again later or make sure excel file is in right format");
            }

        }


    }
    
    //-------------------------------Algorithms methods--------------------------------------------

    private void onRunAlgorithmButtonClick() {

        //get algorithm combo box selected index
        int algorithmComboBoxIndex = algorithmComboBox.getSelectedIndex();

        //if user didnot choose any item
        if (algorithmComboBoxIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please Select Algorithm first");

        } else if (algorithmComboBoxIndex == 0) {
            //if user choosed all algorithms



        }else if (algorithmComboBoxIndex == 1) {
            
            //if user choosed first come first served algorithm

            //run algorithm
            firstComeFirstServed(inputProcesses);


        } else if (algorithmComboBoxIndex == 4) {
            
            //if user choosed round robin algorithm
            int quantum;
            
            try {
                quantum = Integer.parseInt(quantumTestField.getText());
                if (quantum < 1) {
                    JOptionPane.showMessageDialog(null, "Please enter a positive integer for the quantum");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a numerical value for the quantum");
                return;
            }

            //run algorithm
            roundRobin(inputProcesses, quantum);

        }

    }

    private void roundRobin(List<Process> inputProcesses, int quantum) {

        //clear output queue and table
        clearOutputTable();
        outputQueue.clear();

        //create round robin algorithm object
        RoundRobin RR = new RoundRobin();
        //run algorithm and store result in a queue
        Queue<Process> outputQueuee = RR.runAlgorithm(inputProcesses, quantum);
        outputQueue = outputQueuee ;
        //set current algorithm title variable to round robin
        currentAlgorithmTitle = "Round Robin";

        //convert queue to list and pass it to the table
        List<Process> outputProcess = new ArrayList<>(outputQueue);
        setOutputTableModel(outputProcess);

    }

    private void firstComeFirstServed(List<Process> inputProcesses) {

        //clear output queue and table
        clearOutputTable();
        outputQueue.clear();

        //create round robin algorithm object
        FirstComeFirstServed FCFS = new FirstComeFirstServed();
        //run algorithm and store result in a queue
        outputQueue = FCFS.runAlgorithm(inputProcesses);
        //set current algorithm title variable to round robin
        currentAlgorithmTitle = "First Come First Served";

        //convert queue to list and pass it to the table
        List<Process> outputProcess = new ArrayList<>(outputQueue);
        setOutputTableModel(outputProcess);

    }
    
    //-------------------------------Export methods--------------------------------------------

    private void exportExcel() {

        //Create JFileChooser Object
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Save");
        //set mode to view files and directories
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //set USER DESKTOP as the opening directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        int returnValue = fileChooser.showOpenDialog(null); //get returned value
        //if returned value equals APPROVE_OPTION
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); //get selected file
            String filePath = selectedFile.getAbsolutePath(); //get selected path

            //check if file already exist
            File xmlFile = new File(filePath + ".xls");
            if (xmlFile.exists()) {
                int response = JOptionPane.showConfirmDialog(null, //
                                                             "File already exists, Do you want to replace it ?", //
                                                             "Confirm", JOptionPane.YES_NO_OPTION, //
                                                             JOptionPane.QUESTION_MESSAGE);
                if (response != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try {
                //write output excel file
                ExcelWrite excelWrite = new ExcelWrite();
                excelWrite.writeProcesses(outputQueue, currentAlgorithmTitle, filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Unkown Error Occured, Please try again later");
            }
        }
    }
    
    //-------------------------------Tabels methods--------------------------------------------

    private void setInputTableModel(List<Process> processes) {

        Object[][] processesArr = new Object[processes.size()][4];

        for (int i = 0; i < processes.size(); i++) {
            processesArr[i][0] = processes.get(i).getProcessName();
            processesArr[i][1] = processes.get(i).getArriveTime();
            processesArr[i][2] = processes.get(i).getBurstTime();
            processesArr[i][3] = processes.get(i).getDeadline();
        }
        inputTable.setModel(new DefaultTableModel(processesArr,
                                                  new String[] { "TASK", "ARRIVAL", "DURATION", "DEADLINE" }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

        //set table dimension
        Dimension dim = new Dimension(300, outputTable.getRowCount() * 25) ;
        inputTable.setPreferredSize(dim);
        inputTable.setFillsViewportHeight(true);
    }

    private void clearInputTable() {

        Object[][] processesArr = new Object[0][4];

        inputTable.setModel(new DefaultTableModel(processesArr,
                                                  new String[] { "TASK", "ARRIVAL", "DURATION", "DEADLINE" }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

        //set table dimension
        Dimension dim = new Dimension(300, 0) ;
        inputTable.setPreferredSize(dim);
        inputTable.setFillsViewportHeight(true);
    }

    private void setOutputTableModel(List<Process> processes) {

        Object[][] processesArr = new Object[processes.size()][5];

        for (int i = 0; i < processes.size(); i++) {
            processesArr[i][0] = processes.get(i).getProcessName();
            processesArr[i][1] = processes.get(i).getStartTime();
            processesArr[i][2] = processes.get(i).getEndTime();
            processesArr[i][3] = processes.get(i).getBurstTime();

            if (processes.get(i).getEndTime() > processes.get(i).getDeadline())
                processesArr[i][4] = "F";
            else
                processesArr[i][4] = "S";

        }
        outputTable.setModel(new DefaultTableModel(processesArr,
                                                   new String[] { "TASK", "START", "END", "DURATION", "STATUS" }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

        //set table dimension
        Dimension dim = new Dimension(300, outputTable.getRowCount() * 25) ;
        outputTable.setPreferredSize(dim);
        outputTable.setFillsViewportHeight(true);
    }

    private void clearOutputTable() {

        Object[][] processesArr = new Object[0][5];

        outputTable.setModel(new DefaultTableModel(processesArr,
                                                   new String[] { "TASK", "START", "END", "DURATION", "STATUS" }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
    
        //set table dimension
        Dimension dim = new Dimension(300, 0) ;
        outputTable.setPreferredSize(dim);
        outputTable.setFillsViewportHeight(true);
    }

}