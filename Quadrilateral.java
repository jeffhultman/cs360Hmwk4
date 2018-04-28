import java.awt.*;
import static java.lang.Math.*;
public class Quadrilateral extends Shape
{
  protected int [] vertexX = new int [4];
	protected int [] vertexY = new int [4];
  protected double [] doubleVertexX = new double [4];
	protected double [] doubleVertexY = new double [4];
	protected Polygon polygon = new Polygon (vertexX, vertexY, 4);
  Quadrilateral()
  {
  }
  public String sides()
  {
    return "4";
  }
  public String getName()
  {
    return "Quadrilateral";
  }

  public boolean isIn (int X, int Y)
  {
    return polygon.contains (X, Y);
  }

  public void resize(double N)
  {
    double transX, transY = 0;
    for (int i = 0; i < 4; i++)
    {
        // Translate vertices to origin to perform scale algorithm
        transX = doubleVertexX[i] - centerX;
        transY = doubleVertexY[i] - centerY;
       // System.out.println(doubleVertexX[i]);
        if (N > 0)
        {
        // Algorithm to increase shape size
        doubleVertexX[i] = transX * 1.1;
        doubleVertexY[i] = transY * 1.1;
        }
        else
        {
          // Algorithm to decrease shape size
          doubleVertexX[i] = transX * .9;
          doubleVertexY[i] = transY * .9;
        }
        //System.out.println(doubleVertexX[i]);
        // Translate back to centerX, centerY
        doubleVertexX[i] += centerX;
        doubleVertexY[i] += centerY;
        //System.out.println(doubleVertexX[i]);

    }
    for (int i = 0; i < 4; i++)
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

    for (int i = 0; i < 4; i++)
    {
      transX = doubleVertexX[i] - centerX;
      transY = doubleVertexY[i] - centerY;
      // System.out.println(transX);
      doubleVertexX[i] = (transX * Math.cos(Math.toRadians(degs)) - transY * Math.sin(Math.toRadians(degs)));
      doubleVertexY[i] = (transX * Math.sin(Math.toRadians(degs)) + transY * Math.cos(Math.toRadians(degs)));
      // System.out.println(doubleVertexX[i] + " " + i + " ");

      doubleVertexX[i] += centerX;
      doubleVertexY[i] += centerY;
      // System.out.println(doubleVertexX[i] + " " + i + " ");
    }

    for (int i = 0; i < 4; i++)
    {
      vertexX[i] = (int) doubleVertexX[i];
      vertexY[i] = (int) doubleVertexY[i];
      // System.out.println(vertexX[i] + " " + i + " ");

    }
    polygon = new Polygon(vertexX, vertexY, 4);
    // System.out.println("Rotate!");
  }
  public void move (int deltaX, int deltaY)
  {
    centerX += deltaX;
    centerY += deltaY;
    for (int i = 0; i < 4; i++)
    {
      doubleVertexX[i] += deltaX;
      doubleVertexY[i] += deltaY;
      // System.out.println(vertexX[i]);
    }
    for (int i = 0; i < 4; i++)
    {
      vertexX[i] = (int) (doubleVertexX[i] + .5);
      vertexY[i] = (int) (doubleVertexY[i] + .5);
    }
    polygon = new Polygon(vertexX, vertexY, 4);
    //System.out.println ("Moving shape " + deltaX + "," + deltaY + " units");
  }


}
