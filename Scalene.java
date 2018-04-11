import static java.lang.Math.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
public final class Scalene extends Triangle
{
private int rotateValue = 0;
  private double side1, side2, side3;
  // private int[] vertexX = new int[3];
	// private int[] vertexY = new int[3];

  // public void rotate(double degs)
  // {
  //   double transX, transY = 0;
  //   for (int i = 0; i < 3; i++)
  //   {
  //     transX = doubleVertexX[i] - centerX;
  //     transY = doubleVertexY[i] - centerY;
  //     doubleVertexX[i] = (transX * Math.cos(Math.toRadians(degs)) - transY * Math.sin(Math.toRadians(degs)));
  //     doubleVertexY[i] = (transX * Math.sin(Math.toRadians(degs)) + transY * Math.cos(Math.toRadians(degs)));
  //     doubleVertexX[i] += centerX;
  //     doubleVertexY[i] += centerY;
  //   }
  //
  //   for (int i = 0; i < 3; i++)
  //   {
  //     vertexX[i] = (int) doubleVertexX[i];
  //     vertexY[i] = (int) doubleVertexY[i];
  //   }
  //   polygon = new Polygon(vertexX, vertexY, 3);
  //
  // }

  public void setVertices()
  {
    double alpha = acos(((side * side) + (side2 * side2) - (side3 * side3)) / (2 * side * side2));
    double X = side * cos(alpha);
    double height = side * sin(alpha);
    double slope1, slope2, slope3, slope4;
    slope1 = height / -(side2 - X);
    slope2 = -1 / slope1;
    slope3 = height / X;
    slope4 = -1 / slope3;
    double altEq1, altEq2; // y cooridnate of orthocenter
    altEq1 = slope2 * X;
    altEq2 = slope4 * (X - side2);
    int x1, x2, x3, y1, y2, y3;
    double newCenY = slope2 * (X - side2);

    doubleVertexX[0] = (int) (centerX - (side2 / 2 - X));
    doubleVertexX[1] = (int) (centerX - (side2 / 2));
    doubleVertexX[2] = (int) (centerX + (side2 / 2));
    doubleVertexY[0] = (int) (centerY - height / 2);
    doubleVertexY[1] = (int) (centerY + height / 2);
    doubleVertexY[2] = (int) (centerY + height / 2);
    for (int i = 0; i < 3; i++)
    {
      vertexX[i] = (int) (doubleVertexX[i] + .5);
      vertexY[i] = (int) (doubleVertexY[i] + .5);
    }
    polygon = new Polygon(vertexX, vertexY, 3);
  }

  public void paintComponent (Graphics2D g2)
	{

    g2.setPaint(Color.BLACK);
    g2.drawOval(centerX - 1, centerY - 1, 2, 2);

    // double newCX = (x1 + x2 + x3) / 3;
    // double newCY = (y1 + y2 + y3) / 3;
    // vertexX = {(int) (x1), (int) (x2), (int) (x3)};
    //int xS[] = {(int) (x1 + newCX), (int) (x2 + newCX), (int) (x3 + newCX)};
    // vertexY = {y1, y2, y3};
    // for (int i = 0; i < 3; i++)
    // {
    //   xS[i] += newCX;
    //   yS[i] += newCY;
    // }

    g2.setPaint(color);
		g2.drawPolygon(vertexX, vertexY, 3);
    g2.fillPolygon(vertexX, vertexY, 3);
    if (this.isSelected)
    {
      g2.setPaint(Color.WHITE);
      g2.drawPolygon(vertexX, vertexY, 3) ;
      g2.setPaint(color);
    }

}

  public Scalene (int S1, int S2, int S3, int X, int Y, Color C)
  {
    side = S1;
    side2 = S2;
    side3 = S3;
    centerX = X;
    centerY = Y;
    color = C;
  }

  public Scalene()
  {
  }
  public Scalene(Scalene S)
  {
    side1 = S.side1;
    side2 = S.side2;
    side3 = S.side3;
  }
  public Scalene(double S1, double S2, double S3)
  {
    side1 = S1;
    side2 = S2;
    side3 = S3;
  }
  public void setSide1(double S1)
  {
    side1 = S1;
  }
  public void setSide2(double S2)
  {
    side2 = S2;
  }
  public void setSide3(double S3)
  {
    side3 = S3;
  }
  public double getSide1()
  {
    return side1;
  }
  public double getSide2()
  {
    return side2;
  }
  public double getSide3()
  {
    return side3;
  }
  public double perimeter()
  {
    return side1 + side2 + side3;
  }
  public double area()
  {
    double s = (side1 + side2 + side3) / 2.0;
    return Math.sqrt(((s - side1) * (s - side2) * (s - side3)) * s);
  }
  public String getName()
  {
    return "Scalene";
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
        side3 = Integer.parseInt(parts[4]);
				color = new Color(Integer.parseInt(parts[5]));
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
//   public static void main(String [] args)
//   {
//     Scalene S = new Scalene();
//     S.write(); System.out.println();
//     S = new Scalene(8, 5, 12);
//     S.write(); System.out.println();
//     S.setSide1(12);
//     S.setSide2(6);
//     S.setSide3(14);
//     S.write(); System.out.println();
//   }
}
