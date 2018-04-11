// File: ShapeIO.java
// Author: Dr. Watts
// Contents: This file contains a class called ShapeIO that can be used to read a file of
// shape descriptions or write a file of shape descriptions.

import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;

public class ShapeIO
{
	public ShapeIO ()
	{
	}

	public void readShapes (String fileName, ArrayList <Shape> shapeList)
	{
		try
		{
			BufferedReader inFile = new BufferedReader (new FileReader (fileName));
			String string = inFile.readLine();
			int count = Integer.parseInt (string);
			Shape shape = new Shape ();
			for (int i = 0; i < count; i++)
			{
				String name = inFile.readLine();
				string = inFile.readLine();
				if (name.equals("Circle"))
				shape = new Circle ();
				else if (name.equals("Right"))
				shape = new Right ();
				else if (name.equals("Equilateral"))
				shape = new Equilateral ();
				else if (name.equals("Square"))
				shape = new Square ();
				else if (name.equals("Rectangle"))
				shape = new Rectangle ();
				else if (name.equals("Scalene"))
				shape = new Scalene ();
				shape.fromString (string);
				shapeList.add (shape);
			}
			inFile.close();
		}
		catch (IOException e)
		{
			System.out.println ("Error reading file " + fileName);
		}
		catch (NumberFormatException e)
		{
			System.out.println ("Numeric input error in " + fileName);
		}
	}

	public void writeShapes (String fileName, ArrayList <Shape> shapeList)
	{
		try
		{
			BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
			outFile.write (((Integer)shapeList.size()).toString());
			outFile.newLine ();
			for (int i = 0; i < shapeList.size(); i++)
			{
				outFile.write (shapeList.get(i).getName());
				outFile.newLine ();
				outFile.write (shapeList.get(i).toString());
				outFile.newLine ();
			}
			outFile.close();
		}
		catch (IOException e)
		{
			System.out.println ("Error writing file " + fileName);
		}
	}

	public static void main (String[] args)
	{
		ShapeIO shapeIO = new ShapeIO ();
		ArrayList <Shape> S = new ArrayList <Shape> ();
		shapeIO.readShapes (args[0], S);

		S.add (new Circle (20, 100, 250, Color.BLUE));
		S.add (new Circle (30, 200, 250, Color.RED));
		S.add (new Circle (40, 300, 250, Color.GREEN));
		S.add (new Circle (50, 100, 150, Color.PINK));
		S.add (new Circle (70, 200, 150, Color.ORANGE));
		S.add (new Circle (47, 300, 150, new Color (82, 8,125)));
		S.add (new Right (40, 80, 100, 350, Color.MAGENTA));
		S.add (new Right (60, 60, 200, 350, Color.YELLOW));
		S.add (new Right (80, 40, 300, 350, Color.CYAN));

		shapeIO.writeShapes (args[1], S);
	}
}
