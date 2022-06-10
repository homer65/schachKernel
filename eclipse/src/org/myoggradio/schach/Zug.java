package org.myoggradio.schach;
public interface Zug 
{
	public void setVon(Position position);
	public void setNach(Position position);
	public void setNeueFigur(Figur figur);
	public Position getVon();
	public Position getNach();
	public Figur getNeueFigur();
	public String getNotation(String symbol);
	public long getTime();
	public void setTime(long time);
}
