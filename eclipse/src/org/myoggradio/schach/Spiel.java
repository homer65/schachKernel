package org.myoggradio.schach;
import java.util.ArrayList;
public interface Spiel 
{
	public Stellung getStellung();
	public ArrayList<Zug> getZuege();
	public void ziehe(Zug zug);
	public boolean istGueltig(Zug zug);
	public Spiel einZugZurueck();
	public boolean istWeissAmZug();
	public boolean istMatt();
	public boolean istPatt();
	public ArrayList<Zug> gueltigeZuege();
	public String getNotation();
	public String getNotationsHTML();
}
