// File: Triangle.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Triangle.

import java.awt.*;
import java.awt.geom.AffineTransform;
public class Triangle extends Shape
{
    private int rotateValue = 0;
    protected int [] vertexX = new int [3];
    protected int [] vertexY = new int [3];
    protected double [] doubleVertexX = new double [3];
    protected double [] doubleVertexY = new double [3];
    protected double [] dist = new double [3];
    protected Polygon polygon = new Polygon (vertexX, vertexY, 3);

    Triangle ()
    {
    }

    public String getName ()
    {
        return "Triangle";
    }
    public String sides()
    {
        return "3";
    }

    public void paintComponent (Graphics2D g2)
    {

        g2.setPaint (color);
        g2.fillPolygon (vertexX, vertexY, 3);
        g2.drawPolygon (vertexX, vertexY, 3);
        if (this.isSelected)
        {
            g2.setPaint(Color.WHITE);
            g2.drawPolygon(vertexX, vertexY, 3) ;
            g2.setPaint(color);
        }
        g2.setPaint (Color.BLACK);
        g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point

    }

    protected void setVertices ()
    {
    }

    public boolean isIn (int X, int Y)
    {
        return polygon.contains (X, Y);
    }

    public void move (int deltaX, int deltaY)
    {
        centerX += deltaX;
        centerY += deltaY;
        for (int i = 0; i < 3; i++)
        {
            doubleVertexX[i] += deltaX;
            doubleVertexY[i] += deltaY;
        }
        polygon = new Polygon(vertexX, vertexY, 3);
        for (int i = 0; i < 3; i++)
        {
            vertexX[i] = (int) (doubleVertexX[i] + .5);
            vertexY[i] = (int) (doubleVertexY[i] + .5);
        }
        polygon = new Polygon(vertexX, vertexY, 3);

    }
    public void resize(double N)
    {
        // if (tempSide <= 20.0)
        // {
        //     tempSide = 20.1;
        // }
        // dist[0] = Math.sqrt(Math.pow(doubleVertexX[0] - centerX, 2) + Math.pow(doubleVertexY[0] - centerY, 2));
        //
        // dist[1] = Math.sqrt(Math.pow(doubleVertexX[1] - centerX, 2) + Math.pow(doubleVertexY[1] - centerY, 2));
        //
        // dist[2] = Math.sqrt(Math.pow(doubleVertexX[2] - centerX, 2) + Math.pow(doubleVertexY[2] - centerY, 2));
        // if (dist[0] < 10.0 && N > 0)
        // {
        //   dist[0] += .1;
        // }
        // else if (dist[1] < 10.0 && N > 0)
        // {
        //   dist[1] += .1;
        // }
        // else if (dist[2] < 10.0 && N > 0)
        // {
        //   dist[2] += .1;
        // }
        // if (dist[0] > 10.0 && dist[1] > 10.0 && dist[2] > 10.0)
        // {
        //   dist[0] *= .1 * N;
        //   dist[1] *= .1 * N;
        //   dist[2] *= .1 * N;
        // for (int i = 0; i < 3; i++)
        // {
        //     if (doubleVertexX[i] > centerX)
        //     {
        //         doubleVertexX[i] += dist[i];
        //     }
        //     else if (doubleVertexX[i] == centerX)
        //     {
        //
        //     }
        //     else
        //     {
        //         if (i == 1)
        //         {
        //             System.out.println("Here");
        //             System.out.println(doubleVertexX[i]);
        //             System.out.println(dist[i]);
        //
        //         }
        //         doubleVertexX[i] -= dist[i];
        //     }
        //     if (doubleVertexY[i] > centerY)
        //     {
        //         doubleVertexY[i] += dist[i];
        //     }
        //     else if (doubleVertexY[i] == centerY)
        //     {
        //
        //     }
        //     else
        //     {
        //         doubleVertexY[i] -= dist[i];
        //     }
        // }
        double transX, transY = 0;
        for (int i = 0; i < 3; i++)
        {
            transX = doubleVertexX[i] - centerX;
            transY = doubleVertexY[i] - centerY;
            System.out.println(doubleVertexX[i]);

            if (N > 0)
            {
            doubleVertexX[i] = transX * 1.1;
            doubleVertexY[i] = transY * 1.1;
            }
            else
            {
              doubleVertexX[i] = transX * .9;
              doubleVertexY[i] = transY * .9;
            }
            System.out.println(doubleVertexX[i]);
            doubleVertexX[i] += centerX;
            doubleVertexY[i] += centerY;
            System.out.println(doubleVertexX[i]);

        }
        for (int i = 0; i < 3; i++)
        {
            vertexX[i] = (int) (doubleVertexX[i] + .5);
            vertexY[i] = (int) (doubleVertexY[i] + .5);
        }
      // }
        polygon = new Polygon(vertexX, vertexY, 3);
        //setVertices();
    }
    public void rotate(double degs)
    {
        double transX, transY = 0;
        for (int i = 0; i < 3; i++)
        {
            transX = doubleVertexX[i] - centerX;
            transY = doubleVertexY[i] - centerY;
            doubleVertexX[i] = (transX * Math.cos(Math.toRadians(degs)) - transY * Math.sin(Math.toRadians(degs)));
            doubleVertexY[i] = (transX * Math.sin(Math.toRadians(degs)) + transY * Math.cos(Math.toRadians(degs)));
            doubleVertexX[i] += centerX;
            doubleVertexY[i] += centerY;
        }

        for (int i = 0; i < 3; i++)
        {
            vertexX[i] = (int) doubleVertexX[i];
            vertexY[i] = (int) doubleVertexY[i];
        }
        polygon = new Polygon(vertexX, vertexY, 3);

    }
}
