package kernel;
public interface Partie 
{
	public void addHalbZug(Zug zug);
	public int getAnzahlHalbZuege();
	public Zug getHalbZug(int i);
	public Brett getBrett();
	public boolean istWeissAmZug();
	public boolean istSchwarzAmZug();
	public boolean wurdeWeisserKoenigBewegt();
	public boolean wurdeSchwarzerKoenigBewegt();
	public Partie einZugBack();
	public Zug letzterZug();
	public String getNotation(Zug zug);
}
