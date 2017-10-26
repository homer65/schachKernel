package implement;
import kernel.*;
import java.io.*;
public class SimpleFigur implements Figur,Serializable
{
	private static final long serialVersionUID = 1L;
	private int farbe = 0; // 0=frei 1=weiss 2=schwarz
	private int typ = 0; // 0=frei 1=Bauer 2=Springer 3=Laeufer 4=Turm 5=Dame 6=Koenig
	@Override
	public boolean istFrei() 
	{
		boolean erg = false;
		if (farbe == 0) erg = true;
		if (typ == 0) erg = true;
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
	public boolean istSchwarz() 
	{
		boolean erg = false;
		if (farbe == 2) erg = true;
		return erg;
	}
	@Override
	public boolean istBauer() 
	{
		boolean erg = false;
		if (typ == 1) erg = true;
		return erg;
	}
	@Override
	public boolean istSpringer() 
	{
		boolean erg = false;
		if (typ == 2) erg = true;
		return erg;
	}
	@Override
	public boolean istLaeufer() 
	{
		boolean erg = false;
		if (typ == 3) erg = true;
		return erg;
	}
	@Override
	public boolean istTurm() 
	{
		boolean erg = false;
		if (typ == 4) erg = true;
		return erg;
	}
	@Override
	public boolean istDame() 
	{
		boolean erg = false;
		if (typ == 5) erg = true;
		return erg;
	}
	@Override
	public boolean istKoenig() 
	{
		boolean erg = false;
		if (typ == 6) erg = true;
		return erg;
	}
	@Override
	public void setFrei() 
	{
		farbe = 0;
		typ = 0;
	}
	@Override
	public void setWeiss() 
	{
		farbe = 1;		
	}
	@Override
	public void setSchwarz() 
	{
		farbe = 2;		
	}
	@Override
	public void setBauer() 
	{
		typ = 1;		
	}
	@Override
	public void setSpringer() 
	{
		typ = 2;		
	}
	@Override
	public void setLaeufer() 
	{
		typ = 3;		
	}
	@Override
	public void setTurm() 
	{
		typ = 4;		
	}
	@Override
	public void setDame() 
	{
		typ = 5;		
	}
	@Override
	public void setKoenig() 
	{
		typ = 6;		
	}
	@Override
	public double getWert() 
	{
		double erg = 0.0;
		if (typ == 1) erg = 1.0; // Bauer
		if (typ == 2) erg = 3.0; // Springer
		if (typ == 3) erg = 3.0; // Laeufer
		if (typ == 4) erg = 5.0; // Turm
		if (typ == 5) erg = 9.0; // Dame
		if (farbe == 2) erg = erg * (-1.0); // schwarz
		return erg;
	}
	@Override
	public String getNotation()
	{
		String erg = "";
		if (typ == 1 && farbe == 1) erg += "wB";
		if (typ == 2 && farbe == 1) erg += "wS";
		if (typ == 3 && farbe == 1) erg += "wL";
		if (typ == 4 && farbe == 1) erg += "wT";
		if (typ == 5 && farbe == 1) erg += "wD";
		if (typ == 6 && farbe == 1) erg += "wK";
		if (typ == 1 && farbe == 2) erg += "sB";
		if (typ == 2 && farbe == 2) erg += "sS";
		if (typ == 3 && farbe == 2) erg += "sL";
		if (typ == 4 && farbe == 2) erg += "sT";
		if (typ == 5 && farbe == 2) erg += "sD";
		if (typ == 6 && farbe == 2) erg += "sK";
		return erg;
	}
}
