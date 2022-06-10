package org.myoggradio.schach.impl;
import java.io.Serializable;
import org.myoggradio.schach.Figur;
import org.myoggradio.schach.Position;
public class SimplePosition implements Position,Serializable
{
	private static final long serialVersionUID = 1L;
	private int x = -1;
	private int y = -1;
	@Override
	public void setX(int i) 
	{
		x = i;		
	}
	@Override
	public void setY(int i) 
	{
		y = i;		
	}
	@Override
	public int getX() 
	{
		return x;
	}
	@Override
	public int getY() 
	{
		return y;
	}
	@Override
	public boolean istOnBrett() 
	{
		boolean erg = true;
		if (x < 0) erg = false;
		if (y < 0) erg = false;
		if (x > 7) erg = false;
		if (y > 7) erg = false;
		return erg;
	}
	@Override
	public void getFromNotation(String s)
	{
		// Protokol.write("SimplePosition:getFromString:in:" + s);
		String s1 = s.substring(0,1);
		String s2 = s.substring(1,2);
		// Protokol.write("SimplePosition:getFromString:out:" + s1 + ":" + s2);
		if (s1.equals("a")) setX(0);
		if (s1.equals("b")) setX(1);
		if (s1.equals("c")) setX(2);
		if (s1.equals("d")) setX(3);
		if (s1.equals("e")) setX(4);
		if (s1.equals("f")) setX(5);
		if (s1.equals("g")) setX(6);
		if (s1.equals("h")) setX(7);
		setY(Integer.parseInt(s2) - 1);
	}
	@Override
	public String getNotation()
	{
		String erg = "";
		if (x == 0) erg += "a";
		if (x == 1) erg += "b";
		if (x == 2) erg += "c";
		if (x == 3) erg += "d";
		if (x == 4) erg += "e";
		if (x == 5) erg += "f";
		if (x == 6) erg += "g";
		if (x == 7) erg += "h";
		int iy = y+1;
		erg += iy;
		return erg;
	}
	@Override
	public String getImageName(Figur figur) 
	{
		String erg = "";
		if (figur.istFrei())
		{
			erg += "fr";
		}
		else
		{
			if (figur.istWeiss())
			{
				erg += "w";
			}
			else
			{
				erg += "s";
			}
			if (figur.istBauer()) erg += "b";
			if (figur.istSpringer()) erg += "s";
			if (figur.istLaeufer()) erg += "l";
			if (figur.istTurm()) erg += "t";
			if (figur.istDame()) erg += "d";
			if (figur.istKoenig()) erg += "k";
		}
		int n = x+y;
		int nh = n / 2;
		if (n == 2*nh)
		{
			erg += "s";
		}
		else
		{
			erg += "w";
		}
		return erg;
	}
}
