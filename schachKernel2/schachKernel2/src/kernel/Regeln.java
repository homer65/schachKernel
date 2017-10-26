package kernel;
import java.util.ArrayList;
public interface Regeln 
{
	public boolean istHalbZugGueltig(Partie partie,Zug zug);
	public ArrayList<Zug> gueltigeZuege(Partie partie);
	public boolean istMatt(Partie partie);
	public boolean istPatt(Partie partie);
}
