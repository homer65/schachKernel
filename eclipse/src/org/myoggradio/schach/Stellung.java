package org.myoggradio.schach;
public interface Stellung 
{
	public void setFigur(Figur figur,Position position);
	public Figur getFigur(Position position);
	public void ziehe(Zug zug);
	public Stellung copy();
}
