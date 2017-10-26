package implement;
import kernel.*;

import java.io.*;
public class SimpleFeld implements Feld,Serializable
{
	private static final long serialVersionUID = 1L;
	public int x = 1;
	public int y = 1;
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
	public String getNotation() 
	{
		String erg = "";
		if (x == 1) erg += "a";
		if (x == 2) erg += "b";
		if (x == 3) erg += "c";
		if (x == 4) erg += "d";
		if (x == 5) erg += "e";
		if (x == 6) erg += "f";
		if (x == 7) erg += "g";
		if (x == 8) erg += "h";
		erg += y;
		return erg;
	}
	@Override
	public boolean istGueltig() 
	{
		boolean erg = true;
		if (x < 1) erg = false;
		if (y < 1) erg = false;
		if (x > 8) erg = false;
		if (y > 8) erg = false;
		return erg;
	}
	@Override
	public String getImageName(Figur figur) 
	{
		String pre = "?";
		if (figur.istFrei()) pre = "f";
		else if (figur.istWeiss()) pre = "w";
		else if (figur.istSchwarz()) pre = "s";
		if (figur.istFrei()) pre += "r";
		else if (figur.istBauer()) pre += "b";
		else if (figur.istSpringer()) pre += "s";
		else if (figur.istLaeufer()) pre += "l";
		else if (figur.istTurm()) pre += "t";
		else if (figur.istDame()) pre += "d";
		else if (figur.istKoenig()) pre += "k";
		if (istGerade(x+y)) pre += "w";
		else pre += "s";
		return pre + ".gif";
	}
	private boolean istGerade(int i)
	{
		boolean erg = false;
		int i2 = i / 2;
		int j = 2 * i2;
		if (i == j) erg = true;
		return erg;
	}
}
