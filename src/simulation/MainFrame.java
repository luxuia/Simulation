/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;


/**
 *
 * @author luxuia
 */
public class MainFrame extends javax.swing.JFrame {

    class DrawPanel extends javax.swing.JPanel {

        public DrawPanel() {
           
            try {
                xcount = widthSlider.getValue();//Integer.valueOf(widthText.getText().trim());
                ycount = heightSlider.getValue();//Integer.valueOf(heightText.getText().trim());
            } catch (java.lang.NumberFormatException ex) {
                System.out.println("xcount or ycount Parse Error");
            }
            
            
            shapesList = new ArrayList<>();
            statesList = new ArrayList<>();
            connectList = new ArrayList[xcount*ycount];
            for (int i = 0; i < xcount*ycount; i++) {
                connectList[i] = new ArrayList<>();
            }
            //updateEllipse();
        }

        public void updateEllipse() {
            
           // shapeList.clear();
            float detaX = this.getBounds().width / xcount,
                  detaY = this.getBounds().height / ycount;
            
            float radio = Math.min(detaX, detaY)*0.5f;
            
            Shapes = new RectangularShape[xcount][];
            States = new state[xcount][];
            
            for (int i = 0; i < xcount; i++ ) {
                
                Shapes[i] = new RectangularShape[ycount];
                States[i] = new state[ycount];
                
                for (int j = 0; j < ycount; j++) {
            
                    Ellipse2D tmp = new Ellipse2D.Float(detaX*(i+1)-radio, 
                                                detaY*(j+1)-radio, radio, radio);
                 
                   Shapes[i][j] = tmp;
                   States[i][j] = state.normal;
                   
                  // shapeList.add(tmp);
                }
            }
            
            
            shapesList.add(Shapes);
            statesList.add(States);
            
            this.repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
           
            paintMan(g2);
            
            if (showConnect.isSelected()) {
                if (Dragging == true) {
                    paintDraggingLine(g2);
                }
                if (connectList != null) {
                    paintConnectLine(g2);
                }
            }
            
            
            
        }
        
        protected void paintDraggingLine(Graphics2D g2) {
            g2.setColor(Color.yellow);
            
            int sx = (int)Shapes[clicks.x][clicks.y].getCenterX(),
                    sy = (int)Shapes[clicks.x][clicks.y].getCenterY();
                    //ex = (int)Shapes[clicke.x][clicke.y].getCenterX(),
                   // ey = (int)Shapes[clicke.x][clicke.y].getCenterY();
            
            
            g2.drawLine(sx, sy,  clicke.x, clicke.y);
        }
         protected void paintMan(Graphics2D g2) {
            for (int i = 0; i < xcount; i++) {
                    for (int j = 0; j < ycount; j++) {
                        if (States[i][j] == state.infect) {
                            g2.setPaint(Color.red);

                           // g2.setPaint(Color.green);
                        } else {
                            g2.setPaint(Color.green);

                        } 
                        g2.fill(Shapes[i][j]);
                    }
                }
        }

        protected void paintConnectLine(Graphics2D g2) {
            g2.setColor(Color.yellow);
            for (int i = 0; i < xcount*ycount; i++) {
                RectangularShape tmp = Shapes[i%xcount][i/xcount];
                for (int s = 0; s < connectList[i].size(); s++) {
                    int j = connectList[i].get(s);
                    RectangularShape t = Shapes[j%xcount][j/xcount];
                    g2.drawLine((int)tmp.getCenterX(),
                            (int)tmp.getCenterY(), 
                            (int)t.getCenterX(), 
                            (int)t.getCenterY()
                            );


                }
            }
        }
    }
    
   
    
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        stepSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        stepState = new javax.swing.JLabel();
        Caculate = new javax.swing.JButton();
        showConnect = new javax.swing.JCheckBox();
        randLine = new javax.swing.JCheckBox();
        randInfect = new javax.swing.JCheckBox();
        widthSlider = new javax.swing.JSlider();
        heightSlider = new javax.swing.JSlider();
        drawPanel = new DrawPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        stepSlider.setMajorTickSpacing(10);
        stepSlider.setMinorTickSpacing(1);
        stepSlider.setPaintLabels(true);
        stepSlider.setPaintTicks(true);
        stepSlider.setToolTipText("Time");
        stepSlider.setValue(0);
        stepSlider.setAutoscrolls(true);
        stepSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                stepSliderStateChanged(evt);
            }
        });

        jLabel1.setText("width");

        jLabel2.setText("height");

        update.setText("Update");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateMousePressed(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        Caculate.setText("Caculate");
        Caculate.setToolTipText("");
        Caculate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CaculateMousePressed(evt);
            }
        });
        Caculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaculateActionPerformed(evt);
            }
        });

        showConnect.setText("ShowConnectLine");
        showConnect.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                showConnectStateChanged(evt);
            }
        });
        showConnect.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                showConnectPropertyChange(evt);
            }
        });

        randLine.setText("Random Line");

        randInfect.setText("Random Infected");

        widthSlider.setMajorTickSpacing(10);
        widthSlider.setMinimum(10);
        widthSlider.setMinorTickSpacing(2);
        widthSlider.setPaintTicks(true);
        widthSlider.setToolTipText("");
        widthSlider.setValue(10);
        widthSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                widthSliderStateChanged(evt);
            }
        });
        widthSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                widthSliderPropertyChange(evt);
            }
        });

        heightSlider.setMajorTickSpacing(10);
        heightSlider.setMinimum(10);
        heightSlider.setMinorTickSpacing(2);
        heightSlider.setPaintTicks(true);
        heightSlider.setValue(10);
        heightSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                heightSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stepSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(showConnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(randLine))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(widthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(randInfect)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepState, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Caculate))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(stepSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stepState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(widthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(update)
                                .addComponent(Caculate))
                            .addComponent(heightSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showConnect)
                    .addComponent(randLine)
                    .addComponent(randInfect))
                .addContainerGap())
        );

        drawPanel.setBorder(new javax.swing.border.MatteBorder(null));
        drawPanel.setName("drawPanel"); // NOI18N
        drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                drawPanelMouseReleased(evt);
            }
        });
        drawPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                drawPanelComponentResized(evt);
            }
        });
        drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                drawPanelMouseDragged(evt);
            }
        });
        drawPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                drawPanelPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void makeRandLine() {
        connectList = new ArrayList[xcount*ycount];
        for (int i = 0; i < xcount*ycount; i++) {
            connectList[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < xcount*ycount; i++ ) {
        
            for (int j = 0; j < i; j++) {
                boolean t = Math.random()>0.95?true:false;
                if (t) {
                    connectList[i].add(j);
                    connectList[j].add(i);
                }
            }
        }
    }    
    
    private void makeRandInfect() {
        for (int i = 0; i < xcount; i++) {
            for (int j = 0; j < ycount; j++) {
                boolean t = Math.random() > 0.8?true:false;
                if (t) {
                    changeState(Shapes, States, i, j);
                    //States[i][j] = state.infect;
                }
            }
        }
        shapesList.set(Slidercnt, Shapes);
        statesList.set(Slidercnt, States);
        
        this.repaint();
    }
    
    
    private void changeState(RectangularShape[][] mshapeArray, state[][] mstate, int i, int j) {
        if (mstate[i][j] == state.normal) {
            mshapeArray[i][j] = new Rectangle2D.Float(
                    mshapeArray[i][j].getBounds().x, mshapeArray[i][j].getBounds().y, 
                    mshapeArray[i][j].getBounds().width, mshapeArray[i][j].getBounds().height);
            mstate[i][j] = state.infect;
        }  else {
            mshapeArray[i][j] = new Ellipse2D.Float(
                    mshapeArray[i][j].getBounds().x, mshapeArray[i][j].getBounds().y, 
                    mshapeArray[i][j].getBounds().width, mshapeArray[i][j].getBounds().height);
            mstate[i][j] = state.normal;
        }
    }
    
    private void drawPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseClicked
        // TODO add your handling code here:
        System.out.println(evt.getX() + ", " + evt.getY());
        
        out:
        for (int i = 0; i < xcount; i++) {
            for (int j = 0; j < ycount; j++) {
                RectangularShape shape = Shapes[i][j];
                if (shape.contains(evt.getPoint())) {
                    changeState(Shapes, States, i, j);
                    
                    shapesList.set(Slidercnt, Shapes);
                    statesList.set(Slidercnt, States);
                    
                    break out;
                }
            }
        }
    
        this.repaint();
        
    }//GEN-LAST:event_drawPanelMouseClicked

    private double weightOfInfect(int i, int j) {
        
        float s = 0;
        int t = i + j*xcount;
        for (int c = 0; c < connectList[t].size(); c++) {
            int x = connectList[t].get(c)%xcount;
            int y = connectList[t].get(c)/xcount;
            
            
            if (States[x][y] == state.infect)
                s += InfectWeight;
        }
        
        if (connectList[t].size() == 0)
            return 0;
        return s/connectList[t].size();
    }
    
    private boolean infect(int i, int j) {
        double rand;
        rand = Math.random();
        
        //System.out.println(rand);
        if (weightOfInfect(i, j) < 0.5*rand) {
            return false;
        }
        else {
            return true;
        }
    }
    
    private static int oldStep = 0;
    private void drawPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_drawPanelComponentResized
        // TODO add your handling code here:
        
        ((DrawPanel)drawPanel).updateEllipse();
    }//GEN-LAST:event_drawPanelComponentResized

    private void drawPanelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_drawPanelPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_drawPanelPropertyChange

    
    private Point getClosedPoint(Point p) {
        int ansx=0, ansy=0;
        float dist = Integer.MAX_VALUE;
        for (int i = 0; i < xcount; i++) {
            for (int j = 0; j < ycount; j++) {
                double x = Shapes[i][j].getCenterX();
                double y = Shapes[i][j].getCenterY();
                if (p.distance(x, y) < dist) {
                    dist = (float)p.distance(x, y);
                    ansx = i;
                    ansy = j;
                }
            }
        }
        return new Point(ansx, ansy);
    }
    
    private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMousePressed
        // TODO add your handling code here:
        
        clicks = evt.getPoint();
        
        clicks = getClosedPoint(clicks);
         //System.out.print(ClickSX + ", " + ClickSY + ",\n " );
        
    }//GEN-LAST:event_drawPanelMousePressed

    private void drawPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseDragged
        // TODO add your handling code here:
      
        Dragging = true;
        clicke = evt.getPoint();
        
        this.repaint();
    }//GEN-LAST:event_drawPanelMouseDragged

    private void drawPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseReleased
        // TODO add your handling code here:
        
        if (!Dragging)
            return;
        Dragging = false;
        clicke = getClosedPoint(clicke);
        connectList[clicks.x + clicks.y*xcount].add(clicke.x + clicke.y*xcount);
        connectList[clicke.x + clicke.y*xcount].add(clicks.x + clicks.y*xcount);
        this.repaint();
    }//GEN-LAST:event_drawPanelMouseReleased

    private void showConnectPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_showConnectPropertyChange
        // TODO add your handling code here:
        this.repaint();
    }//GEN-LAST:event_showConnectPropertyChange

    private void showConnectStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_showConnectStateChanged
        // TODO add your handling code here:
        this.repaint();
    }//GEN-LAST:event_showConnectStateChanged

    private void CaculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaculateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaculateActionPerformed

    private void CaculateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaculateMousePressed
        // TODO add your handling code here:

        shapesList.clear();
        statesList.clear();

        for (int i = 1; i < 102; i++) {

            RectangularShape[][] tmpr = new RectangularShape[xcount][ycount];
            state[][] tmps = new state[xcount][ycount];
            for (int x = 0; x < xcount; x++) {
                System.arraycopy(Shapes[x], 0, tmpr[x], 0, ycount);
                System.arraycopy(States[x], 0, tmps[x], 0, ycount);
            }
            shapesList.add(tmpr);
            statesList.add(tmps);

            for (int x = 0; x < xcount; x++) {
                for (int y = 0; y < ycount; y++) {
                    if (infect(x, y)) {
                        changeState(Shapes, States, x, y);
                    }
                }
            }
        }
        stepSlider.setValue(0);
    }//GEN-LAST:event_CaculateMousePressed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void updateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMousePressed
        // TODO add your handling code here:
        try {
            xcount = widthSlider.getValue();//Integer.valueOf(widthText.getText().trim());
            ycount = heightSlider.getValue();//Integer.valueOf(heightText.getText().trim());
        } catch (java.lang.NumberFormatException ex) {
            System.out.println("xcount or ycount Parse Error");
        }

        ((DrawPanel)drawPanel).updateEllipse();

        connectList = null;
        if (randLine.isSelected()) {
            makeRandLine();
        }

        if (randInfect.isSelected()) {
            makeRandInfect();
        }
    }//GEN-LAST:event_updateMousePressed

    private void stepSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_stepSliderStateChanged
        // TODO add your handling code here:

        Slidercnt = stepSlider.getValue();
        if (Slidercnt == oldStep) {
            return;
        }

        Shapes = shapesList.get(Slidercnt);
        States = statesList.get(Slidercnt);

        System.out.println(Slidercnt);
        this.repaint();
        oldStep = Slidercnt;
    }//GEN-LAST:event_stepSliderStateChanged

    private void heightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_heightSliderStateChanged
        // TODO add your handling code here:
        try {
            xcount = widthSlider.getValue();//Integer.valueOf(widthText.getText().trim());
            ycount = heightSlider.getValue();//Integer.valueOf(heightText.getText().trim());
        } catch (java.lang.NumberFormatException ex) {
            System.out.println("xcount or ycount Parse Error");
        }

        ((DrawPanel)drawPanel).updateEllipse();

        connectList = null;
        if (randLine.isSelected()) {
            makeRandLine();
        }

        if (randInfect.isSelected()) {
            makeRandInfect();
        }
    }//GEN-LAST:event_heightSliderStateChanged

    private void widthSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_widthSliderStateChanged
        // TODO add your handling code here:
        heightSliderStateChanged(evt);
    }//GEN-LAST:event_widthSliderStateChanged

    private void widthSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_widthSliderPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_widthSliderPropertyChange

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    

    
    private Point clicks, clicke;
    private boolean Dragging;
    private static enum state{normal, infect};
    private RectangularShape[][] Shapes;
    private state[][]    States;

    
    private ArrayList<RectangularShape[][]> shapesList = null;
    private ArrayList<state[][]>    statesList = null;
    
    private ArrayList< Integer >[] connectList = null;
    
    private int Slidercnt = 0;
    private float InfectWeight =  0.5f;
    private int xcount = 10, ycount = 10;
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Caculate;
    private javax.swing.JPanel drawPanel;
    private javax.swing.JSlider heightSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox randInfect;
    private javax.swing.JCheckBox randLine;
    private javax.swing.JCheckBox showConnect;
    private javax.swing.JSlider stepSlider;
    private javax.swing.JLabel stepState;
    private javax.swing.JButton update;
    private javax.swing.JSlider widthSlider;
    // End of variables declaration//GEN-END:variables
}
