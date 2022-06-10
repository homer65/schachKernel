package org.myoggradio.schach.impl;
import java.io.Serializable;
import org.myoggradio.schach.Figur;
public class SimpleFigur implements Figur,Serializable
{
	private static final long serialVersionUID = 1L;
	private int farbe = -1;
	private int typ = -1;
	@Override
	public int getFarbe()
	{
		return farbe;
	}
	@Override
	public int getTyp()
	{
		return typ;
	}
	@Override
	public void setFarbe(int i)
	{
		farbe = i;
	}
	@Override
	public void setTyp(int i)
	{
		typ = i;
	}
	@Override
	public void setFrei() 
	{
		farbe = -1;
		typ = -1;
	}
	@Override
	public void setSchwarz() 
	{
		farbe = 0;
	}
	@Override
	public void setWeiss() 
	{
		farbe = 1;		
	}
	@Override
	public void setBauer() 
	{
		typ = 0;		
	}
	@Override
	public void setSpringer() 
	{
		typ = 1;		
	}
	@Override
	public void setLaeufer() 
	{
		typ = 2;		
	}
	@Override
	public void setTurm() 
	{
		typ = 3;		
	}
	@Override
	public void setDame() 
	{
		typ = 4;		
	}
	@Override
	public void setKoenig() 
	{
		typ = 5;		
	}
	@Override
	public boolean istFrei() 
	{
		boolean erg = false;
		if (typ == -1) erg = true;
		return erg;
	}
	@Override
	public boolean istSchwarz() 
	{
		boolean erg = false;
		if (farbe == 0) erg = true;
		return erg;
	}
	@Override
	public boolean istWeiss() 
	{
		boolean erg = false;
		if (farbe == 1) erg = true;
		return erg;
	}
	@Override
	public boolean istBauer() 
	{
		boolean erg = false;
		if (typ == 0) erg = true;
		return erg;
	}
	@Override
	public boolean istSpringer() 
	{
		boolean erg = false;
		if (typ == 1) erg = true;
		return erg;
	}
	@Override
	public boolean istLaeufer() 
	{
		boolean erg = false;
		if (typ == 2) erg = true;
		return erg;
	}
	@Override
	public boolean istTurm()
	{
		boolean erg = false;
		if (typ == 3) erg = true;
		return erg;
	}
	@Override
	public boolean istDame() 
	{
		boolean erg = false;
		if (typ == 4) erg = true;
		return erg;
	}
	@Override
	public boolean istKoenig()
	{
		boolean erg = false;
		if (typ == 5) erg = true;
		return erg;
	}
	@Override
	public String getNotation() 
	{
		String erg = "";
		if (istSpringer()) erg += "S";
		if (istLaeufer()) erg += "L";
		if (istTurm()) erg += "T";
		if (istDame()) erg += "D";
		if (istKoenig()) erg += "K";
		return erg;
	}
}
