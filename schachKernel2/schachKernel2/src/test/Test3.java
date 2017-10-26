package test;
import kernel.*;
public class Test3
{
	private static Factory factory = new Factory();
	public static void main(String[] args) 
	{
		Regeln regeln = factory.getRegeln();
		Partie partie = factory.getPartie();
		Zug zug = factory.getZug();
		Feld von = factory.getFeld();
		Feld nach = factory.getFeld();
		von.setX(1);
		von.setY(1);
		nach.setX(1);
		nach.setY(3);
		zug.setVon(von);
		zug.setNach(nach);
		boolean erg = regeln.istHalbZugGueltig(partie,zug);
		System.out.println(erg);
	}
}
