import static java.lang.Math.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public final class Square extends Quadrilateral
{
  private int rotateValue = 0;
  // public void resize(double N)
  // {
  //   if (tempSide <= 20.0)
  //   {
  //     tempSide = 20.1;
  //   }
  //   double dist = Math.sqrt(Math.pow(doubleVertexX[1] - centerX, 2) + Math.pow(doubleVertexY[1] - centerY, 2));
  //   dist *= .1 * N;
  //   for (int i = 0; i < 4; i++)
  //   {
  //     if (doubleVertexX[i] > centerX)
  //     {
  //       doubleVertexX[i] += dist;
  //     }
  //     else
  //     {
  //       doubleVertexX[i] -= dist;
  //     }
  //     if (doubleVertexY[i] > centerY)
  //     {
  //       doubleVertexY[i] += dist;
  //     }
  //     else
  //     {
  //       doubleVertexY[i] -= dist;
  //     }
  //   }
  //   for (int i = 0; i < 4; i++)
  //   {
  //     vertexX[i] = (int) (doubleVertexX[i] + .5);
  //     vertexY[i] = (int) (doubleVertexY[i] + .5);
  //   }
  //   polygon = new Polygon(vertexX, vertexY, 4);
  //   //setVertices();
  // }

  public void paintComponent (Graphics2D g2)
  {
    g2.setPaint(color);
    //setVertices();
    polygon = new Polygon (vertexX, vertexY, 4);
    g2.drawPolygon(vertexX, vertexY, 4);
    g2.fillPolygon(vertexX, vertexY, 4);
    if (this.isSelected)
    {
      g2.setPaint(Color.WHITE);
      g2.drawPolygon(vertexX, vertexY, 4) ;
      g2.setPaint(color);
    }
    g2.setPaint(Color.BLACK);
    g2.fillOval(centerX - 1, centerY - 1, 2, 2);

  }

  public Square(int S, int X, int Y, Color C)
  {
    side = S;
    centerX = X;
    centerY = Y;
    color = C;
  }
  public void setVertices()
  {
    if (!hasVertices)
    {
      doubleVertexX[0] = (centerX + (side / 2));
      doubleVertexX[1] = (centerX - (side / 2));
      doubleVertexX[2] = (centerX - (side / 2));
      doubleVertexX[3] = (centerX + (side / 2));
      doubleVertexY[0] = (centerY - (side / 2));
      doubleVertexY[1] = (centerY - (side / 2));
      doubleVertexY[2] = (centerY + (side / 2));
      doubleVertexY[3] = (centerY + (side / 2));
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
    for(int i = 0; i < Math.abs(rotateValue); i++)
    {

        this.rotate(5);
    }
    if (hasVertices)
    {
      // System.out.println("Here");

      side = (int) tempSide;
    }
    hasVertices = false;
    polygon = new Polygon (vertexX, vertexY, 4);

  }
  public Square()
  {

  }
  public Square(Square S)
  {
    side = S.side;
    color = S.color;
    centerX = S.centerX;
    centerY = S.centerY;
  }
  public Square(int S)
  {
    side = S;
  }
  public void setSide(int S)
  {
    side = S;
  }
  public double getSide()
  {
    return side;
  }
  public double perimeter()
  {
    return side * 4;
  }
  public double area()
  {
    return side * side;
  }
  public String getName()
  {
    return "Square";
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
