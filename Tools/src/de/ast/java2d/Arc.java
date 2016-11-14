package de.ast.java2d;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/* 
 * This is like the FontDemo applet in volume 1, except that it 
 * uses the Java 2D APIs to define and render the graphics and text.
 */

@SuppressWarnings("serial")
public class Arc extends JApplet
{
  final static int maxCharHeight = 15;
  final static int minFontSize = 6;

  final static Color BG = Color.white;
  final static Color FG = Color.black;
  final static Color RED = Color.red;
  final static Color WHITE = Color.white;

  /**
   * Setze diverse Flags, die das Rendering des Images beeinflussen
   * @param g2d
   */
  private void applyQualityRenderingHints(Graphics2D g2d) {
    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
  }
  
  private Double getPiAngle(Double angle)
  {
    return (angle / 180.0) * Math.PI;
  }
  
  public void drawArc(Graphics g, Double innerRadius, Double outerRadius, Double startAngle, Double angleExtend, Color foreGround)
  {
    Graphics2D g2 = (Graphics2D) g;
    
    Double centerX = outerRadius;
    Double centerY = outerRadius;
    
    System.out.println("centerX : " + centerX);
    System.out.println("centerY : " + centerY);
    
    // calculate starting point
    Double piStartAngle = getPiAngle(startAngle);
    Double startX = centerX + innerRadius * Math.cos(piStartAngle);
    Double startY = centerY - innerRadius * Math.sin(piStartAngle);
    
    Double upperLeftX = outerRadius - innerRadius;
    Double upperLeftY = outerRadius - innerRadius;

    System.out.println("upperLeftX : " + upperLeftX);
    System.out.println("upperLeftY : " + upperLeftY);
    System.out.println("Math.cos(getPiAngle(piStartAngle)) : " + Math.cos(piStartAngle));
    System.out.println("Math.sin(getPiAngle(piStartAngle)) : " + Math.sin(piStartAngle));
    System.out.println("piStartAngle : " + piStartAngle);
    System.out.println("innerRadius : " + innerRadius);

    System.out.println("startX : " + startX);
    System.out.println("startY : " + startY);
    
    GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    
    // draw inner arc
    Arc2D.Double arc1 = new Arc2D.Double(upperLeftX, upperLeftY, innerRadius * 2.0, innerRadius * 2.0, startAngle, angleExtend, Arc2D.OPEN);
    path.append(arc1, true);
    
    // line to from endpoint to outer point
    Double piCurrentAngle = getPiAngle(startAngle + angleExtend);
    Double outerX = centerX + outerRadius * Math.cos(piCurrentAngle);
    Double outerY = centerY - outerRadius * Math.sin(piCurrentAngle);
    
    System.out.println("outerX : " + outerX);
    System.out.println("outerY : " + outerY);
    
    path.lineTo(outerX, outerY);
    
    // draw outer arc
    Arc2D.Double arc2 = new Arc2D.Double(0.0, 0.0, outerRadius * 2.0, outerRadius * 2.0, startAngle + angleExtend, -1.0 * angleExtend, Arc2D.OPEN);
    path.append(arc2,  true);
    
    // line to starting point
    path.lineTo(startX, startY);
    
    path.closePath();
    
    g2.setPaint(foreGround);
    g2.fill(path);
    g2.setPaint(FG);
    g2.draw(path);
  }
  
  
  public void paint(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    applyQualityRenderingHints(g2);
    
    Dimension d = getSize();
    // draw white background
    g2.setColor(BG);
    g2.fillRect(0,  0,  d.width, d.height);
    
    drawArc(g, 120.0, 140.0, 0.0, 20.0, Color.red);
    drawArc(g, 140.0, 160.0, 0.0, 40.0, Color.blue);
    drawArc(g, 160.0, 180.0, 0.0, 60.0, Color.yellow);
    drawArc(g, 180.0, 200.0, 0.0, 80.0, Color.green);
//    drawArc(g, 160.0, 200.0, 70.0, 60.0, Color.blue);
//    drawArc(g, 140.0, 200.0, 140.0, 60.0, Color.yellow);
//    drawArc(g, 120.0, 200.0, 210.0, 60.0, Color.green);
    
//    drawArc(g, 100.0, 180.0, 0.0, 80.0, Color.red);
//    drawArc(g,  80.0, 160.0, 90.0, 80.0, Color.blue);
//    drawArc(g,  60.0, 140.0, 180.0, 80.0, Color.yellow);
//    drawArc(g,  40.0, 120.0, 270.0, 80.0, Color.green);
  }

  public static void main(String s[])
  {
    JFrame f = new JFrame("Draw Arcs");
    f.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });
    JApplet applet = new Arc();
    f.getContentPane().setBackground(BG);
    f.getContentPane().add("Center", applet);

    f.pack();
    f.setSize(new Dimension(850, 600));
    f.setVisible(true);
  }

}