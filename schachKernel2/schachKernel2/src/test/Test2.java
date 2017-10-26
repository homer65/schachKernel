package test;
import java.util.ArrayList;
import kernel.*;
public class Test2
{
	private static Factory factory = new Factory();
	public static void main(String[] args) 
	{
		Partie partie = factory.getPartie();
		Regeln regeln = factory.getRegeln();
		ArrayList<Zug> zuege = regeln.gueltigeZuege(partie);
		for (int i=0;i<zuege.size();i++)
		{
			Zug zug = zuege.get(i);
			System.out.println(zug.getNotation());
		}
	}
}
