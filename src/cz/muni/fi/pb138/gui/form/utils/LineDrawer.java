/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.gui.form.utils;

import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.gui.Main;
import cz.muni.fi.pb138.Managers.PortManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JRootPane;

/**
 *
 * @author Mags
 */
public class LineDrawer extends JComponent {

    private Map<Port, PortConnection> portConnections = new HashMap<>();
    private PortManager portManager = Main.getPortManager();

    public LineDrawer() {
        super();
    }

    public void link(Port port, Point c1, Point c2, Color color) {
        if (!portConnections.containsKey(port)) {
            portConnections.put(port, new PortConnection(c1, c2, color));
        }
        
        repaint();
    }
    
    
   public void unlink(Port p) {
       portManager.deletePort(p);
       portConnections.remove(p);
       repaint(); 
   }
   
   public Set<Port> getOpenPorts() {
       return Collections.unmodifiableSet(portConnections.keySet());
   }
    
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (PortConnection portConnection : portConnections.values()) {
            g2d.setPaint(portConnection.getColor());
            Point p1 = portConnection.getStart();
            Point p2 = portConnection.getEnd();
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

    }

    private class PortConnection {

        private Point start;
        private Point end;
        private Color color;

        public PortConnection(Point start, Point end, Color color) {
            this.start = start;
            this.end = end;
            this.color = color;
        }

        public Point getStart() {
            return start;
        }

        public void setStart(Point start) {
            this.start = start;
        }

        public Point getEnd() {
            return end;
        }

        public void setEnd(Point end) {
            this.end = end;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

    }

}
