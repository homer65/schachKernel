package test;
import java.util.ArrayList;
import kernel.*;
public class Test4
{
	private static Factory factory = new Factory();
	public static void main(String[] args) 
	{
		Partie partie = factory.getPartie();
		Regeln regeln = factory.getRegeln();
		Zug zug1 = factory.getZug();
		Feld von = factory.getFeld();
		von.setX(1);
		von.setY(2);
		Feld nach = factory.getFeld();
		nach.setX(2);
		nach.setY(4);
		zug1.setVon(von);
		zug1.setNach(nach);
		partie.addHalbZug(zug1);
		ArrayList<Zug> zuege = regeln.gueltigeZuege(partie);
		for (int i=0;i<zuege.size();i++)
		{
			Zug zug = zuege.get(i);
			System.out.println(zug.getNotation());
		}
	}
}
