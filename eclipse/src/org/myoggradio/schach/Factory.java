package org.myoggradio.schach;
import org.myoggradio.schach.impl.*;
public class Factory 
{
	public static Figur getFigur()
	{
		return new SimpleFigur();
	}
	public static Stellung getStellung()
	{
		return new SimpleStellung();
	}
	public static Position getPosition()
	{
		return new SimplePosition();
	}
	public static Zug getZug()
	{
		return new SimpleZug();
	}
	public static Spiel getSpiel()
	{
		return new SimpleSpiel();
	}
}
