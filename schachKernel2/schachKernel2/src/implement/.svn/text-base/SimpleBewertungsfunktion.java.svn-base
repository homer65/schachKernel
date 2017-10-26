package implement;
import kernel.*;
public class SimpleBewertungsfunktion implements Bewertungsfunktion
{
	private Factory factory = new Factory();
	@Override
	public double bewerte(Partie partie) 
	{
		double erg = 0.0;
		Brett brett = partie.getBrett();
		Feld feld = factory.getFeld();
		for (int i=1;i<9;i++)
		{
			for (int j=1;j<9;j++)
			{
				feld.setX(i);
				feld.setY(j);
				Figur figur = brett.getFigur(feld);
				double wert = figur.getWert();
				erg += wert;
			}
		}
		return erg;
	}
}
