import static java.lang.Math.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
public final class Rectangle extends Quadrilateral
{
  private int rotateValue = 0;
  private int side2;
  private double tempSide2;
  public void resize(double N)
  {
    if (tempSide <= 20.0)
    {
      tempSide = 20.1;
    }
    double dist = Math.sqrt(Math.pow(doubleVertexX[1] - centerX, 2) + Math.pow(doubleVertexY[1] - centerY, 2));
    dist *= .1 * N;
    for (int i = 0; i < 4; i++)
    {
      if (doubleVertexX[i] > centerX)
      {
        doubleVertexX[i] += dist;
      }
      else if (doubleVertexX[i] == centerX)
			{

			}
      else
      {
        doubleVertexX[i] -= dist;
      }
      if (doubleVertexY[i] > centerY)
      {
        doubleVertexY[i] += dist;
      }
      else if (doubleVertexY[i] == centerY)
			{

			}
      else
      {
        doubleVertexY[i] -= dist;
      }
    }
    for (int i = 0; i < 4; i++)
    {
      vertexX[i] = (int) (doubleVertexX[i] + .5);
      vertexY[i] = (int) (doubleVertexY[i] + .5);
    }
    polygon = new Polygon(vertexX, vertexY, 4);
    //setVertices();
  }

  public void setVertices()
  {
    if (!hasVertices)
    {
      // System.out.println("Math");
      doubleVertexX[0] = (centerX + (side / 2));
      doubleVertexX[1] = (centerX - (side / 2));
      doubleVertexX[2] = (centerX - (side / 2));
      doubleVertexX[3] = (centerX + (side / 2));
      doubleVertexY[0] = (centerY - (side2 / 2));
      doubleVertexY[1] = (centerY - (side2 / 2));
      doubleVertexY[2] = (centerY + (side2 / 2));
      doubleVertexY[3] = (centerY + (side2 / 2));
    }
    vertexX[0] = (int) (doubleVertexX[0]);
    vertexX[1] = (int) (doubleVertexX[1]);
    vertexX[2] = (int) (doubleVertexX[2]);
    vertexX[3] = (int) (doubleVertexX[3]);
    vertexY[0] = (int) (doubleVertexY[0]);
    vertexY[1] = (int) (doubleVertexY[1]);
    vertexY[2] = (int) (doubleVertexY[2]);
    vertexY[3] = (int) (doubleVertexY[3]);
    tempSide = Math.sqrt(Math.pow(vertexX[1] - vertexX[0], 2) + Math.pow(vertexY[1] - vertexY[0], 2));
    tempSide2 = Math.sqrt(Math.pow(vertexX[2] - vertexX[1], 2) + Math.pow(vertexY[2] - vertexY[1], 2));
    if (hasVertices)
    {
      side = (int) tempSide;
      side2 = (int) tempSide2;
    }
    hasVertices = false;
    polygon = new Polygon (vertexX, vertexY, 4);


  }

  public void paintComponent (Graphics2D g2)
  {

    g2.setPaint(color);
    //setVertices();
    g2.drawPolygon(vertexX, vertexY, 4);
    g2.fillPolygon(vertexX, vertexY, 4);
    if (this.isSelected)
    {
      g2.setPaint(Color.WHITE);
      g2.drawPolygon(vertexX, vertexY, 4) ;
      g2.setPaint(color);
    }
    // g2.drawRect((int) (centerX - side / 2), (int) (centerY - side2 / 2), (int) (side), (int) (side2));
    // g2.fillRect((int) (centerX - side / 2), (int) (centerY - side2 / 2), (int) (side), (int) (side2));
    g2.setPaint(Color.BLACK);
    g2.fillOval(centerX - 1, centerY - 1, 2, 2);

  }


  public Rectangle(int S1, int S2, int X, int Y, Color C)
  {
    side = S1;
    side2 = S2;
    centerX = X;
    centerY = Y;
    color = C;
  }

  public Rectangle()
  {
  }
  public Rectangle(Rectangle R)
  {
    side = R.side;
    side2 = R.side2;
    centerX = R.centerX;
    centerY = R.centerY;
    color = R.color;
  }
  public Rectangle(int S1, int S2)
  {
    side = S1;
    side2 = S2;
  }
  public void setSide(int S1)
  {
    side = S1;
  }
  public void setSide2(int S2)
  {
    side2 = S2;
  }
  public double getSide()
  {
    return side;
  }
  public double getSide2()
  {
    return side2;
  }
  public double perimeter()
  {
    return (side * 2) + (side2 * 2);
  }
  public double area()
  {
    return (side * side2);
  }
  public String getName()
  {
    return "Rectangle";
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
        setVertices();
      }
      else
      {
        color = new Color(Integer.parseInt(parts[2]));
        for (int i = 0; i < 4; i++)
        {
          doubleVertexX[i] = Double.parseDouble(parts[3 + (2 * i)]);
          doubleVertexY[i] = Double.parseDouble(parts[4 + (2 * i)]);
        }
        hasVertices = true;
        setVertices();
      }
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
    for (int i = 0; i < 4; i++)
    {
      string += doubleVertexX[i] + " ";
      string += doubleVertexY[i] + " ";
    }
    return string;
  }
}
