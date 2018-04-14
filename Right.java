// File: Right.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Right.

import static java.lang.Math.*;
import java.awt.*;

public final class Right extends Triangle
{
	private int side2;

	public Right ()
	{
		side2 = 0;
	}

	public Right (Right R)
	{
		side = R.side;
		side2 = R.side2;
		centerX = R.centerX;
		centerY = R.centerY;
		color = R.color;
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] = R.vertexX[i];
			vertexY[i] = R.vertexY[i];
		}
	}

	public Right (int S1, int S2, int X, int Y, Color C)
	{
		side = S1;
		side2 = S2;
		centerX = X;
		centerY = Y;
		color = C;
		numVertices = 3;
		//setVertices ();
	}

	public void setVertices ()
	{
		if (!hasVertices)
		{
			doubleVertexX[0] = doubleVertexY[0] = 0;
			doubleVertexX[1] = 0; doubleVertexY[1] = -side2;
			doubleVertexX[2] = side; doubleVertexY[2] = 0;
			double hyp = sqrt (side * side + side2 * side2);
			double perim = perimeter ();
			double inX = 0, inY = 0;
			if (perim > 0)
			{
				inX = ((doubleVertexX[0]* hyp + doubleVertexX[1] * side + doubleVertexX[2] * side2) / perim);
				inY = ((doubleVertexY[0]* hyp + doubleVertexY[1] * side + doubleVertexY[2] * side2) / perim);
			}
			for (int i = 0; i < 3; i++)
			{
				doubleVertexX[i] += (centerX - inX);
				doubleVertexY[i] += (centerY - inY);
			}
		}
		hasVertices = false;
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] = (int) (doubleVertexX[i] + .5);
			vertexY[i] = (int) (doubleVertexY[i] + .5);
		}
		polygon = new Polygon (vertexX, vertexY, 3);
	}

	public void setSide1 (int S1)
	{
		side = S1;
		// setVertices ();
	}

	public int getSide1 ()
	{
		return side;
	}

	public void setSide2 (int S2)
	{
		side2 = S2;
		// setVertices ();
	}

	public int getSide2 ()
	{
		return side2;
	}

	public double perimeter ()
	{
		return side + side2 + sqrt (side * side + side2 * side2);
	}

	public double area ()
	{
		return side * side2 / 2;
	}

	public String getName ()
	{
		return "Right";
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
				side2 = Integer.parseInt(parts[3]);
				color = new Color(Integer.parseInt(parts[4]));
			}
			else
			{
				color = new Color(Integer.parseInt(parts[2]));
				numVertices = Integer.parseInt(parts[3]);
				for (int i = 0; i < 3; i++)
				{
					doubleVertexX[i] = Double.parseDouble(parts[4 + (2 * i)]);
					doubleVertexY[i] = Double.parseDouble(parts[5 + (2 * i)]);
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
		string += numVertices + " ";
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
