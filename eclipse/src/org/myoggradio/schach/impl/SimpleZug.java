package org.myoggradio.schach.impl;
import java.io.Serializable;
import java.util.Date;

import org.myoggradio.schach.*;
public class SimpleZug implements Zug,Serializable
{
	private static final long serialVersionUID = 1L;
	private Position von = null;
	private Position nach = null;
	private Figur neueFigur = null;
	private long time = new Date().getTime();
	@Override
	public void setVon(Position position) 
	{
		von = position;		
	}
	@Override
	public void setNach(Position position) 
	{
		nach = position;		
	}
	@Override
	public void setNeueFigur(Figur figur) 
	{
		neueFigur = figur;		
	}
	@Override
	public Position getVon() 
	{
		return von;
	}
	@Override
	public Position getNach() 
	{
		return nach;
	}
	@Override
	public Figur getNeueFigur() 
	{
		return neueFigur;
	}
	@Override
	public String getNotation(String symbol) 
	{
		String svon = von.getNotation();
		String snach = nach.getNotation();
		String erg = svon + symbol + snach;
		if (neueFigur != null)
		{
			erg += " / " + neueFigur.getNotation();
		}
		return erg;
	}
	@Override
	public long getTime() 
	{
		return time;
	}
	@Override
	public void setTime(long l)
	{
		time = l;		
	}
}
