package pack;
import kernel.*;
public class Main 
{
	private static Factory factory = new Factory();
	public static void main(String[] args) 
	{
		Partie partie = factory.getPartie();
		PartieMenu pmenu = new PartieMenu(partie);
		pmenu.anzeigen();
	}
}
