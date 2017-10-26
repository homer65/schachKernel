package kernel;
import implement.*;
import java.io.*;
public class Factory implements Serializable 
{
	private static final long serialVersionUID = 1L;
	public Bewertungsfunktion getBewertungsfunktion()
	{
		return new SimpleBewertungsfunktion();
	}
	public Brett getBrett()
	{
		return new SimpleBrett();
	}
	public Feld getFeld()
	{
		return new SimpleFeld();
	}
	public Figur getFigur()
	{
		return new SimpleFigur();
	}
	public Partie getPartie()
	{
		return new SimplePartie();
	}
	public Regeln getRegeln()
	{
		return new SimpleRegeln();
	}
	public Zug getZug()
	{
		return new SimpleZug();
	}
}
