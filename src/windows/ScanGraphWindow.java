package windows;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

import radarscangraph.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ScanGraphWindow extends javax.swing.JFrame {

    private Scan currentScan;
    private ScanType scanType;
    private final javax.swing.JPanel[] specialScanPanels;    
    
    private final javax.swing.JComponent[] conicalTargetMovementComponents;
    private final javax.swing.JComponent[] circularTargetMovementComponents;
    private final javax.swing.JComponent[] helicalTargetMovementComponents;
    private final javax.swing.JComponent[] rasterTargetMovementComponents;
    
    private final javax.swing.JComponent[][] targetMovementComponentMatrix;
    private final javax.swing.JCheckBox[] targetMovementCheckBoxes;
    private final Map<String, Integer> scanIndexMap;
    private final String CONICAL_SCAN_NAME = "conical";
    private final String CIRCULAR_SCAN_NAME = "circular";
    private final String HELICAL_SCAN_NAME = "helical";
    private final String RASTER_SCAN_NAME = "raster";

    public ScanGraphWindow() {
        initComponents();

        specialScanPanels = new javax.swing.JPanel[]{jPanel11, jPanel12, jPanel13, jPanel14};
        
        conicalTargetMovementComponents = 
                new javax.swing.JComponent[]{conicalDirectionCB, periodOfCircularMotionTextField3};
        circularTargetMovementComponents =
                new javax.swing.JComponent[]{circularDirectionCB, periodOfCircularMotionTextField4};
        helicalTargetMovementComponents =
                new javax.swing.JComponent[]{helicalDirectionCB, helicalAxisOfMotionCB,
                    periodOfCircularMotionTextField5};
        rasterTargetMovementComponents =
                new javax.swing.JComponent[]{rasterDirectionCB, periodOfCircularMotionTextField6,
                    radiusOfCircularMotionTextField};
        
        targetMovementComponentMatrix =
                new javax.swing.JComponent[][]{conicalTargetMovementComponents,
                                               circularTargetMovementComponents,
                                               helicalTargetMovementComponents,
                                               rasterTargetMovementComponents};
        
        targetMovementCheckBoxes = 
                new javax.swing.JCheckBox[]{conicalTargetMovementCB,
                                            circularTargetMovementCB,
                                            helicalTargetMovementCB,
                                            rasterTargetMovementCB};
        
        scanType = ScanType.CONICAL;
        
        scanIndexMap = new TreeMap<String, Integer>();
        scanIndexMap.put(CONICAL_SCAN_NAME, 0);
        scanIndexMap.put(CIRCULAR_SCAN_NAME, 1);
        scanIndexMap.put(HELICAL_SCAN_NAME, 2);
        scanIndexMap.put(RASTER_SCAN_NAME, 3);
        
        for (int i = 0; i < targetMovementComponentMatrix.length; ++i)
            for (int j = 0; j < targetMovementComponentMatrix[i].length; ++j)
                targetMovementComponentMatrix[i][j].setEnabled(false);

        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jComboBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0)
            {
                int index = jComboBox1.getSelectedIndex();
                activatePanel(index);

            }
        });
        
        specialScanPanels[0].setVisible(true);
        
        for (int i = 1; i < specialScanPanels.length; ++i)
            specialScanPanels[i].setVisible(false);
        
    }
    
    public javax.swing.JComponent[] getConicalTargetMovementComponents()
    {
        return conicalTargetMovementComponents;
    }
    
    public javax.swing.JComponent[] getCircularTargetMovementComponents()
    {
        return circularTargetMovementComponents;
    }
    
    public javax.swing.JComponent[] getHelicalTargetMovementComponents()
    {
        return helicalTargetMovementComponents;
    }
    
    public javax.swing.JComponent[] getRasterTargetMovementComponents()
    {
        return rasterTargetMovementComponents;
    }

    private void activatePanel(int panelNo)
    {
        scanType = ScanType.values()[panelNo];
        
        for (int i = 0; i < specialScanPanels.length; ++i)
        {
            if (i == panelNo)
                specialScanPanels[i].setVisible(true);
            else
                specialScanPanels[i].setVisible(false);
        }
        jPanel1.validate();
        jPanel1.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        targetRangeTextField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        squintAngleTextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        degreeOfTargetTextField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        periodOfCircularMotionTextField = new javax.swing.JTextField();
        conicalTargetMovementCB = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        conicalDirectionCB = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        periodOfCircularMotionTextField3 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        targetRangeTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        elevationAngleTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        degreeOfTargetTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        periodOfCircularMotionTextField2 = new javax.swing.JTextField();
        circularTargetMovementCB = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        circularDirectionCB = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        periodOfCircularMotionTextField4 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        targetRangeTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        elevationOfTargetTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        azimuthOfTargetTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        helicalScanTimeTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        pitchOfHelixTextField = new javax.swing.JTextField();
        helicalTargetMovementCB = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        helicalDirectionCB = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        periodOfCircularMotionTextField5 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        helicalAxisOfMotionCB = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        targetRangeTextField4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        elevationOfTargetTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        azimuthOfTargetTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lengthOfBarTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        decreasePerBarTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        linearSpeedOfBeamTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        numberOfBarsTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        initialAzimuthOfBeamTextField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        initialElevationOfBeamTextField = new javax.swing.JTextField();
        rasterTargetMovementCB = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        rasterDirectionCB = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        periodOfCircularMotionTextField6 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        radiusOfCircularMotionTextField = new javax.swing.JTextField();
        plotGraphButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        graphPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        tableStepTextField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        timeIncreaseStepTextField = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        numberOfMotionRepetitionTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        powerOfAntennaTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        antennaGainTextField = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        maximumGainTextField = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        azimuthWidthTextField = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        elevationWidthTextField = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        frequencyTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel34.setText("Target Range (m):");

        jLabel35.setText("Squint Angle (degrees):");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Parameters for Conical Scan");

        jLabel38.setText("Degree of Target:");

        degreeOfTargetTextField.setText("0");
        degreeOfTargetTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                degreeOfTargetTextFieldActionPerformed(evt);
            }
        });

        jLabel39.setText("Period of Circular Motion (s):");

        conicalTargetMovementCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        conicalTargetMovementCB.setText("Target Movement");
        conicalTargetMovementCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conicalTargetMovementCBActionPerformed(evt);
            }
        });

        jLabel25.setText("Direction:");

        conicalDirectionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clockwise", "Anticlockwise" }));

        jLabel26.setText("Period of Circular Motion (s):");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(degreeOfTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(targetRangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(squintAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel39)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(periodOfCircularMotionTextField)
                            .addComponent(conicalDirectionCB, 0, 200, Short.MAX_VALUE)
                            .addComponent(periodOfCircularMotionTextField3))))
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(conicalTargetMovementCB))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(targetRangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(squintAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(degreeOfTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(periodOfCircularMotionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conicalTargetMovementCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(conicalDirectionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(periodOfCircularMotionTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Parameters for Circular Scan");

        jLabel48.setText("Target Range (m):");

        jLabel2.setText("Elevation Angle (degrees):");

        jLabel4.setText("Degree of Target (Azimuth) (degrees):");

        degreeOfTargetTextField2.setText("0");

        jLabel5.setText("Period of Circular Motion (s):");

        circularTargetMovementCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        circularTargetMovementCB.setText("Target Movement");
        circularTargetMovementCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circularTargetMovementCBActionPerformed(evt);
            }
        });

        jLabel27.setText("Direction:");

        circularDirectionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clockwise", "Anticlockwise" }));

        jLabel28.setText("Period of Circular Motion (s):");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(circularTargetMovementCB))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(jLabel48)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(degreeOfTargetTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(elevationAngleTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(targetRangeTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(periodOfCircularMotionTextField2)
                    .addComponent(circularDirectionCB, 0, 200, Short.MAX_VALUE)
                    .addComponent(periodOfCircularMotionTextField4))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(targetRangeTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(elevationAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(degreeOfTargetTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(periodOfCircularMotionTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(circularTargetMovementCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(circularDirectionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(periodOfCircularMotionTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Parameters for Helical Scan");

        jLabel49.setText("Target Range (m):");

        jLabel7.setText("Elevation of Target (degrees):");

        jLabel8.setText("Azimuth of Target (degrees):");

        azimuthOfTargetTextField.setText("0");

        jLabel13.setText("Helical Scan Time (s):");

        jLabel15.setText("Pitch of Helix (m):");

        helicalTargetMovementCB.setText("Target Movement");
        helicalTargetMovementCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helicalTargetMovementCBActionPerformed(evt);
            }
        });

        jLabel29.setText("Direction:");

        helicalDirectionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clockwise", "Anticlockwise" }));

        jLabel30.setText("Period of Circular Motion (s):");

        jLabel32.setText("Axis of Motion:");

        helicalAxisOfMotionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "X", "Y", "Z" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helicalScanTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(helicalTargetMovementCB)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(0, 143, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel32))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(elevationOfTargetTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(targetRangeTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pitchOfHelixTextField)
                            .addComponent(azimuthOfTargetTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(helicalDirectionCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(helicalAxisOfMotionCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(periodOfCircularMotionTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(targetRangeTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(elevationOfTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(azimuthOfTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(helicalScanTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(pitchOfHelixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(helicalTargetMovementCB)
                .addGap(1, 1, 1)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helicalDirectionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(helicalAxisOfMotionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(periodOfCircularMotionTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Parameters for Raster Scan");

        jLabel50.setText("Target Range (m):");

        jLabel17.setText("Elevation of Target (degrees):");

        jLabel18.setText("Azimuth of Target (degrees):");

        azimuthOfTargetTextField1.setText("0");

        jLabel19.setText("Length of Bar (m):");

        jLabel20.setText("Decrease per Bar (m):");

        jLabel21.setText("Linear Speed of Beam (m/s):");

        jLabel22.setText("Number of Bars:");

        jLabel23.setText("Initial Azimuth of Beam (degrees):");

        jLabel24.setText("Initial Elevation of Beam (degrees):");

        rasterTargetMovementCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rasterTargetMovementCB.setText("Target Movement");
        rasterTargetMovementCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rasterTargetMovementCBActionPerformed(evt);
            }
        });

        jLabel31.setText("Direction:");

        rasterDirectionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clockwise", "Anticlockwise" }));

        jLabel33.setText("Period of Circular Motion (s):");

        jLabel36.setText("Radius of Circular Motion (m):");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(206, Short.MAX_VALUE)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(targetRangeTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(rasterTargetMovementCB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel33))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(initialAzimuthOfBeamTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberOfBarsTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linearSpeedOfBeamTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(decreasePerBarTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lengthOfBarTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(azimuthOfTargetTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(elevationOfTargetTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(initialElevationOfBeamTextField)
                            .addComponent(rasterDirectionCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                            .addComponent(periodOfCircularMotionTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radiusOfCircularMotionTextField, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(targetRangeTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(elevationOfTargetTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(azimuthOfTargetTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lengthOfBarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreasePerBarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(linearSpeedOfBeamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfBarsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initialAzimuthOfBeamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initialElevationOfBeamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(rasterDirectionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rasterTargetMovementCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(periodOfCircularMotionTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(radiusOfCircularMotionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plotGraphButton.setText("Plot Graph");
        plotGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotGraphButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(plotGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(415, Short.MAX_VALUE)
                .addComponent(plotGraphButton)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 156, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 159, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 93, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 109, Short.MAX_VALUE)))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Energy - Time Graph of RF Energy at Target");

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(209, Short.MAX_VALUE))
            .addComponent(graphPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Scan Properties");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conical Scan", "Circular Scan", "Helical Scan", "Raster Scan" }));

        jLabel12.setText("Type of Scan:");

        jLabel40.setText("Azimuth - Elevation Table Step:");

        tableStepTextField.setText("0.25");
        tableStepTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableStepTextFieldActionPerformed(evt);
            }
        });

        jLabel44.setText("Time Increase Step (s):");

        timeIncreaseStepTextField.setText("0.01");

        jLabel45.setText("Number of Motion Repetition:");

        numberOfMotionRepetitionTextField.setText("10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel40)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableStepTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeIncreaseStepTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numberOfMotionRepetitionTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(tableStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(timeIncreaseStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(numberOfMotionRepetitionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Properties of Antenna");

        jLabel10.setText("Transmitting Power Of Antenna (dBm):");

        jLabel11.setText("Antenna Gain (dBi):");

        jLabel41.setText("Maximum Gain of the Antenna (dB):");

        maximumGainTextField.setText("20");
        maximumGainTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximumGainTextFieldActionPerformed(evt);
            }
        });

        jLabel42.setText("Azimuth Width (degrees):");

        jLabel43.setText("Elevation Width (degrees):");

        jLabel46.setText("Frequency (MHz):");

        frequencyTextField.setText("2000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(powerOfAntennaTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(antennaGainTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maximumGainTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(azimuthWidthTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elevationWidthTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frequencyTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(powerOfAntennaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(antennaGainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maximumGainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(azimuthWidthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(elevationWidthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(frequencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void degreeOfTargetTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_degreeOfTargetTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_degreeOfTargetTextFieldActionPerformed

    private void plotGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotGraphButtonActionPerformed

        String[] generalFieldNames = {"Transmitting Power of Antenna",
            "Antenna Gain",
            "Maximum Gain of the Antenna",
            "Azimuth Width",
            "Elevation Width",
            "Frequency",
            "Azimuth - Elevation Table Step",
            "Time Increase Step",
            "Number of Motion Repetition"
        };
        
        javax.swing.JTextField[] generalTextFields = {powerOfAntennaTextField,
            antennaGainTextField,
            maximumGainTextField,
            azimuthWidthTextField,
            elevationWidthTextField,
            frequencyTextField,
            tableStepTextField,
            timeIncreaseStepTextField,
            numberOfMotionRepetitionTextField
        };
        
        
        int[] generalIntegerFieldIndexes = {8};
        
        boolean[] validnessListGeneral = HelperMethods.getValidnessList(generalTextFields, generalIntegerFieldIndexes);

        if (!HelperMethods.checkValidness(validnessListGeneral, generalFieldNames, this))
            return;

        double powerOfAntenna = Double.parseDouble(powerOfAntennaTextField.getText());
        double antennaGain = Double.parseDouble(antennaGainTextField.getText());
        double maximumGain = Double.parseDouble(maximumGainTextField.getText());
        double azimuthWidth = Double.parseDouble(azimuthWidthTextField.getText());
        double elevationWidth = Double.parseDouble(elevationWidthTextField.getText());
        double frequency = Double.parseDouble(frequencyTextField.getText());
        double tableStep = Double.parseDouble(tableStepTextField.getText());
        double timeIncreaseStep = Double.parseDouble(timeIncreaseStepTextField.getText());
        int numberOfMotionRepetition = Integer.parseInt(numberOfMotionRepetitionTextField.getText());
        
        Antenna antenna = new Antenna(powerOfAntenna, antennaGain, azimuthWidth, elevationWidth, maximumGain, frequency);

        switch(scanType)
        {
            case CONICAL:
                currentScan = new ConicalScan(this, antenna, tableStep, numberOfMotionRepetition, timeIncreaseStep,
                        targetMovementCheckBoxes[scanIndexMap.get(CONICAL_SCAN_NAME)].isSelected());
                break;
            case CIRCULAR:
                currentScan = new CircularScan(this, antenna, tableStep, numberOfMotionRepetition, timeIncreaseStep,
                        targetMovementCheckBoxes[scanIndexMap.get(CIRCULAR_SCAN_NAME)].isSelected());
                break;
            case HELICAL:
                currentScan = new HelicalScan(this, antenna, tableStep, numberOfMotionRepetition, timeIncreaseStep,
                        targetMovementCheckBoxes[scanIndexMap.get(HELICAL_SCAN_NAME)].isSelected(),
                        ((javax.swing.JComboBox) helicalTargetMovementComponents[1]).getSelectedIndex());
                break;
            case RASTER:
                currentScan = new RasterScan(this, antenna, tableStep, numberOfMotionRepetition, timeIncreaseStep,
                        targetMovementCheckBoxes[scanIndexMap.get(RASTER_SCAN_NAME)].isSelected());
                break;
        }
        
        if (!currentScan.fetchValues())
            return;

        
        Map<Double, Double> powerMap = new TreeMap();
        currentScan.fillPowerMap(powerMap);

        drawGraph(powerMap);

    }//GEN-LAST:event_plotGraphButtonActionPerformed

    private void tableStepTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableStepTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableStepTextFieldActionPerformed

    private void maximumGainTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximumGainTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maximumGainTextFieldActionPerformed

    private void conicalTargetMovementCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conicalTargetMovementCBActionPerformed
        processTargetMovementIndexCBChanged(scanIndexMap.get(CONICAL_SCAN_NAME));
        
    }//GEN-LAST:event_conicalTargetMovementCBActionPerformed

    private void circularTargetMovementCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circularTargetMovementCBActionPerformed
        processTargetMovementIndexCBChanged(scanIndexMap.get(CIRCULAR_SCAN_NAME));
    }//GEN-LAST:event_circularTargetMovementCBActionPerformed

    private void helicalTargetMovementCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helicalTargetMovementCBActionPerformed
        processTargetMovementIndexCBChanged(scanIndexMap.get(HELICAL_SCAN_NAME));
    }//GEN-LAST:event_helicalTargetMovementCBActionPerformed

    private void rasterTargetMovementCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rasterTargetMovementCBActionPerformed
        processTargetMovementIndexCBChanged(scanIndexMap.get(RASTER_SCAN_NAME));
    }//GEN-LAST:event_rasterTargetMovementCBActionPerformed

    private void processTargetMovementIndexCBChanged(int index)
    {
        if (targetMovementCheckBoxes[index].isSelected())
            setEnabledOfComponents(targetMovementComponentMatrix[index], true);
        else
            setEnabledOfComponents(targetMovementComponentMatrix[index], false);
    }
    
    private void setEnabledOfComponents(javax.swing.JComponent[] componentList, boolean value)
    {
        for (int i = 0; i < componentList.length; ++i)
            componentList[i].setEnabled(value);
    }
    
    public void setText(String text)
    {
        jLabel1.setText(text);
    }
    
    public void drawGraph(Map<Double, Double> powerMap)
    {
        final XYSeries power = new XYSeries("Power");
        Double[] X = powerMap.keySet().toArray(new Double[powerMap.size()]);
        for (int i = 0; i < X.length; ++i)
            power.add(X[i], powerMap.get(X[i]));
        
        final XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(power);
        
        JFreeChart chart = ChartFactory.createXYLineChart("Power - Time Chart", "Time (s)", "Power (dBm)", dataSet,
                PlotOrientation.VERTICAL, true, true, false);
        graphPanel.setLayout(new java.awt.BorderLayout());
        
        ChartPanel CP = new ChartPanel(chart);
        graphPanel.removeAll();
        graphPanel.add(CP, BorderLayout.CENTER);
        graphPanel.validate();
    }
    
    public List<javax.swing.JTextField> getSpecialTextFields()
    {
        return List.of(targetRangeTextField, squintAngleTextField, degreeOfTargetTextField, periodOfCircularMotionTextField,
                targetRangeTextField2, elevationAngleTextField, degreeOfTargetTextField2, periodOfCircularMotionTextField2,
                targetRangeTextField3, elevationOfTargetTextField, azimuthOfTargetTextField, helicalScanTimeTextField, pitchOfHelixTextField,
                targetRangeTextField4, elevationOfTargetTextField1, azimuthOfTargetTextField1, lengthOfBarTextField, decreasePerBarTextField, linearSpeedOfBeamTextField, numberOfBarsTextField, initialAzimuthOfBeamTextField, initialElevationOfBeamTextField);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField antennaGainTextField;
    private javax.swing.JTextField azimuthOfTargetTextField;
    private javax.swing.JTextField azimuthOfTargetTextField1;
    private javax.swing.JTextField azimuthWidthTextField;
    private javax.swing.JComboBox<String> circularDirectionCB;
    private javax.swing.JCheckBox circularTargetMovementCB;
    private javax.swing.JComboBox<String> conicalDirectionCB;
    private javax.swing.JCheckBox conicalTargetMovementCB;
    private javax.swing.JTextField decreasePerBarTextField;
    private javax.swing.JTextField degreeOfTargetTextField;
    private javax.swing.JTextField degreeOfTargetTextField2;
    private javax.swing.JTextField elevationAngleTextField;
    private javax.swing.JTextField elevationOfTargetTextField;
    private javax.swing.JTextField elevationOfTargetTextField1;
    private javax.swing.JTextField elevationWidthTextField;
    private javax.swing.JTextField frequencyTextField;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JComboBox<String> helicalAxisOfMotionCB;
    private javax.swing.JComboBox<String> helicalDirectionCB;
    private javax.swing.JTextField helicalScanTimeTextField;
    private javax.swing.JCheckBox helicalTargetMovementCB;
    private javax.swing.JTextField initialAzimuthOfBeamTextField;
    private javax.swing.JTextField initialElevationOfBeamTextField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField lengthOfBarTextField;
    private javax.swing.JTextField linearSpeedOfBeamTextField;
    private javax.swing.JTextField maximumGainTextField;
    private javax.swing.JTextField numberOfBarsTextField;
    private javax.swing.JTextField numberOfMotionRepetitionTextField;
    private javax.swing.JTextField periodOfCircularMotionTextField;
    private javax.swing.JTextField periodOfCircularMotionTextField2;
    private javax.swing.JTextField periodOfCircularMotionTextField3;
    private javax.swing.JTextField periodOfCircularMotionTextField4;
    private javax.swing.JTextField periodOfCircularMotionTextField5;
    private javax.swing.JTextField periodOfCircularMotionTextField6;
    private javax.swing.JTextField pitchOfHelixTextField;
    private javax.swing.JButton plotGraphButton;
    private javax.swing.JTextField powerOfAntennaTextField;
    private javax.swing.JTextField radiusOfCircularMotionTextField;
    private javax.swing.JComboBox<String> rasterDirectionCB;
    private javax.swing.JCheckBox rasterTargetMovementCB;
    private javax.swing.JTextField squintAngleTextField;
    private javax.swing.JTextField tableStepTextField;
    private javax.swing.JTextField targetRangeTextField;
    private javax.swing.JTextField targetRangeTextField2;
    private javax.swing.JTextField targetRangeTextField3;
    private javax.swing.JTextField targetRangeTextField4;
    private javax.swing.JTextField timeIncreaseStepTextField;
    // End of variables declaration//GEN-END:variables
}
