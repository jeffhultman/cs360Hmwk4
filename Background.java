// File: Background.java
// Author: Dr. Watts
// Contents: This file contains the implementation of a small
// GUI application that uses the Shape class hierarchy.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Background extends JPanel implements ActionListener, MouseMotionListener, MouseListener,
KeyListener
{
	private JButton saveButton;
	private boolean inFrame = true;
	private int currentX, currentY;
	private ArrayList <Shape> S = new ArrayList <Shape> ();
	private Shape selected = null;
	private boolean shift, ctrl = false;

	public Background ()
	{
		repaint();
	}
	public Background (String [] files)
	{
		saveButton = new JButton ("Save Shapes");
		add (saveButton);
		saveButton.addActionListener (this);
		setBackground (Color.BLACK);
		addMouseMotionListener(this);
		addMouseListener(this);
		ShapeIO shapeIO = new ShapeIO ();
		for (int i = 0; i < files.length; i++)
		{
			shapeIO.readShapes (files[i], S);
		}
		repaint();
	}
	public void mouseDragged(MouseEvent e)
	{
		//System.out.println ("Mouse dragged to " + e.getX() + ", " + e.getY());
		if (inFrame && selected != null)
		{
			//System.out.println ("Moving " + selected);
			selected.move (e.getX() - currentX, e.getY() - currentY);
			repaint();
		}
		currentX = e.getX();
		currentY = e.getY();



	}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < S.size(); i++)
		{
			S.get(i).paintComponent (g2);
		}
	}
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == saveButton)
		{
			ShapeIO shapeIO = new ShapeIO ();
			shapeIO.writeShapes ("SavedShapes.io", S);
		}
	}
	public void mousePressed (MouseEvent e)
	{
		//System.out.println ("Mouse pressed at " + e.getX() + ", " + e.getY());
		inFrame = true;
		currentX = e.getX();
		currentY = e.getY();
		selected = null;
		if (e.getButton() == e.BUTTON1) // Left mouse button
		{
		//System.out.println ("BUTTON1 pressed at " + e.getX() + ", " + e.getY());
			for (int i = S.size()-1; selected == null && i >= 0; i--)
			{
				if (S.get(i).isIn(currentX, currentY))
				{
					selected = S.get(i);
					S.get(i).isSelected = true;
					System.out.println ("Selected " + selected);
				}
				else
				{
					S.get(i).isSelected = false;
				}
				repaint();
			}
		}

	}
	public void mouseReleased (MouseEvent e) {}
	public void mouseEntered (MouseEvent e)
	{
		inFrame = true;
	}
	public void mouseExited (MouseEvent e)
	{
		inFrame = false;
		selected = null;
	}
	public void mouseClicked (MouseEvent e) {}


	public void keyPressed(KeyEvent e)
	{
		//displayInfo(e, "KEY PRESSED: ");
		if (e.getKeyCode() == 37) // left key
		{
			// System.out.println(e.getKeyCode());
			selected.move(-5,0);
		}
		else if (e.getKeyCode() == 38) // up key
		{
			// System.out.println(e.getKeyCode());
			selected.move(0,-5);
		}
		else if (e.getKeyCode() == 39) // right key
		{
			// System.out.println(e.getKeyCode());
			selected.move(5,0);
		}
		else if (e.getKeyCode() == 40) // down key
		{
			// System.out.println(e.getKeyCode());
			selected.move(0,5);
		}
		else if (e.getKeyCode() == 61) // + key
		{
			// System.out.println(e.getKeyCode());
			selected.resize(1);
		}
		else if (e.getKeyCode() == 45) // - kwy
		{
			// System.out.println(e.getKeyCode());
			selected.resize(-1);
		}
		else if (e.getKeyCode() == 76) // L kwy
		{
			// System.out.println(e.getKeyCode());
			selected.rotateValue -= 1;
			selected.rotate(-5);
			selected.hasVertices = true;
			// selected.hasVertices = true;
		}
		else if (e.getKeyCode() == 82) // R kwy
		{
			// System.out.println(e.getKeyCode());
			selected.rotateValue += 1;
			selected.rotate(5);
			selected.hasVertices = true;
			// selected.hasVertices = true;

		}
		repaint();
	}


	/** Handle the key pressed event from the text field. */
	public void keyTyped(KeyEvent e) {
		//displayInfo(e, "KEY PRESSED: ");
	}

	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {
		//displayInfo(e, "KEY RELEASED: ");
	}
}
