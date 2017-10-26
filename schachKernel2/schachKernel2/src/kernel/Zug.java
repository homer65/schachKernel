package kernel;
public interface Zug 
{
	public Feld getVon();
	public Feld getNach();
	public Figur getNeueFigur();
	public void setVon(Feld feld);
	public void setNach(Feld feld);
	public void setNeueFigur(Figur figur);
	public String getNotation();
}
