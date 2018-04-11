// File: Equilateral.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Equilateral.

import static java.lang.Math.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
public final class Equilateral extends Triangle
{
	// private int[] vertexX = new int[3];
	// private int[] vertexY = new int[3];
private int rotateValue = 0;
	public Equilateral ()
	{
	}

	public Equilateral (int S, int X, int Y, Color C)
	{
		side = S;
		centerX = X;
		centerY = Y;
		color = C;
	}

	public Equilateral (Equilateral E)
	{
		side = E.side;
		centerX = E.centerX;
		centerY = E.centerY;
		color = E.color;
	}

	public void setSide (int S)
	{
		side = S;
	}

	public double getSide ()
	{
		return side;
	}

	public double perimeter ()
	{
		return 3 * side;
	}

	public double area ()
	{
		return sqrt(3) * side * side / 4;
	}

	public String getName ()
	{
		return "Equilateral";
	}
	// public void resize(double N)
	// {
	// 	if (tempSide <= 20.0)
	// 	{
	// 		tempSide = 20.1;
	// 	}
	// 	tempSide += N * tempSide;
	// 	side = (int) tempSide;
	// 	//setVertices();
	// }
	public void setVertices()
	{
		if (!hasVertices)
		{

			double height = (side * side) - ((side * side) / 4); // a = sqrt(b^2 - c^2)
			height = sqrt(height);
			doubleVertexX[0] = (centerX);
			doubleVertexX[1] = (centerX - side / 2);
			doubleVertexX[2] = (centerX + side / 2);
			doubleVertexY[0] = (centerY - 2 * (height / 3));
			doubleVertexY[1] = (centerY + (height / 3));
			doubleVertexY[2] = (centerY + (height / 3));
		}
		vertexX[0] = (int) (doubleVertexX[0]);
		vertexX[1] = (int) (doubleVertexX[1]);
		vertexX[2] = (int) (doubleVertexX[2]);
		vertexY[0] = (int) (doubleVertexY[0]);
		vertexY[1] = (int) (doubleVertexY[1]);
		vertexY[2] = (int) (doubleVertexY[2]);
		tempSide = Math.sqrt(Math.pow(vertexX[1] - vertexX[0], 2) + Math.pow(vertexY[1] - vertexY[0], 2));
    if (hasVertices)
    {
      side = (int) tempSide;
    }
		hasVertices = false;
		polygon = new Polygon (vertexX, vertexY, 3);
	}

	public void paintComponent (Graphics2D g2)
	{

		//setVertices();
		g2.setPaint (color);
		g2.fillPolygon (vertexX, vertexY, 3);
		g2.drawPolygon (vertexX, vertexY, 3);
		if (this.isSelected)
		{
			g2.setPaint(Color.WHITE);
			g2.drawPolygon(vertexX, vertexY, 3) ;
			g2.setPaint(color);
		}
		else
		{
			g2.setPaint(color);

		}
		g2.setPaint (Color.BLACK);
		g2.fillOval (centerX-1, centerY-1, 2, 2);

	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			if (Integer.parseInt(parts[2]) > 0)
			{
				side = Integer.parseInt(parts[2]);
				color = new Color(Integer.parseInt(parts[3]));
			}
			else
			{
				color = new Color(Integer.parseInt(parts[2]));
				for (int i = 0; i < 3; i++)
				{
					doubleVertexX[i] = Double.parseDouble(parts[3 + (2 * i)]);
					doubleVertexY[i] = Double.parseDouble(parts[4 + (2 * i)]);
				}
				hasVertices = true;
			}
			setVertices();
		}
		catch (NumberFormatException e)
		{
			//System.out.println ("Numeric input error");
		}
	}
	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += color.getRGB() + " ";
		for (int i = 0; i < 3; i++)
		{
			string += doubleVertexX[i] + " ";
			string += doubleVertexY[i] + " ";
		}
		return string;
	}
	// public boolean isIn (int X, int Y)
	// {
	// 	return polygon.contains (X, Y);
	// }
}
