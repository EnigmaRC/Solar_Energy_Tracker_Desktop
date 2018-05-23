package presentatie.JavaOO_dl4;

import presentatie.JavaOO_dl3.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 *
 * @author Olivier PC
 */
public class Panel_2Days extends javax.swing.JPanel {

    private ArrayList<Double> measurements;
    private Graphics g;
    private double maximum;
    private int width;
    private int height;

    /**
     * Creates new form Panel_1Day
     */
    public Panel_2Days() {
        initComponents();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.measurements = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        this.g.setColor(Color.BLACK);
        this.drawAxis();
        this.drawGraph();
        this.repaint();
    }

    private void drawGraph() {
        if (!this.measurements.isEmpty()) {
            this.width = this.getWidth();
            this.height = this.getHeight();
            for (int i = 0; i < this.measurements.size(); i++) { // for loop going over all the measurements and plotting them on the graph.
                this.g.drawOval(i * 5, this.height - 2 - (int) (this.height * this.measurements.get(i) / 4), 4, 4);
                if (i >= 1) {
                    int firstX = i * 5 - 3;
                    int secondX = i * 5 + 2;
                    int firstY = this.height - (int) (this.height * this.measurements.get(i - 1) / 4);
                    int secondY = this.height - (int) (this.height * this.measurements.get(i) / 4);
                    this.g.drawLine(firstX, firstY, secondX, secondY);
                }
            }
            this.repaint();
        }
    }

    private void drawAxis() {
        if (!this.measurements.isEmpty()) {
            for (int i = 0; i <= this.width; i += 60) {
                this.g.drawLine(i, 0, i, this.height);
            }
            for (int i = 0; i <= this.height; i += 60) {
                this.g.drawLine(0, i, this.width, i);
                
            }
        }
    }

    /**
     * Fills an ArrayList with only the measurements of a certain day, excluding
     * the times. If there is no value, a 0.0 will be added.
     *
     * @param arr String[][] filled with times and their measurement.
     */
    protected void fillMeasurements(String[][] arr) {
        this.measurements = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length >= 2 && Double.parseDouble(arr[i][1]) > 0) {
                double measurement = Double.parseDouble(arr[i][1]);
                this.measurements.add(measurement);
            } else {
                this.measurements.add(0.0);
            }
        }
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(480, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    void ffillMeasurements(String[][] fillCustomData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
