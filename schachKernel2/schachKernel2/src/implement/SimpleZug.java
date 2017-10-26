package implement;
import kernel.*;
import java.io.*;
public class SimpleZug implements Zug,Serializable
{
	private static final long serialVersionUID = 1L;
	public Factory factory = new Factory();
	public Feld von = factory.getFeld();
	public Feld nach = factory.getFeld();
	public Figur neueFigur = factory.getFigur();
	@Override
	public Feld getVon() 
	{
		return von;
	}
	@Override
	public Feld getNach() 
	{
		return nach;
	}
	@Override
	public Figur getNeueFigur() 
	{
		return neueFigur;
	}
	@Override
	public void setVon(Feld feld) 
	{
		von = feld;	
	}
	@Override
	public void setNach(Feld feld) 
	{
		nach = feld;	
	}
	@Override
	public void setNeueFigur(Figur figur) 
	{
		neueFigur = figur;		
	}
	@Override
	public String getNotation() 
	{
		String erg = "";
		erg += von.getNotation();
		erg += "-";
		erg += nach.getNotation();
		if (!(neueFigur == null))
		{
			if (!neueFigur.istFrei());
			{
				erg += ":";
				erg += neueFigur.getNotation();
			}
		}
		return erg;
	}
}
