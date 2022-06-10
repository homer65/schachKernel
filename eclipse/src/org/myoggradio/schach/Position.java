package org.myoggradio.schach;
public interface Position 
{
	public void setX(int i);
	public void setY(int i);
	public int getX();
	public int getY();
	public boolean istOnBrett();
	public String getNotation();
	public String getImageName(Figur figur);
	public void getFromNotation(String s);
}
