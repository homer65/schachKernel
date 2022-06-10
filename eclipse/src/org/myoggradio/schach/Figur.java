package org.myoggradio.schach;
public interface Figur 
{
	public void setFrei();
	public void setSchwarz();
	public void setWeiss();
	public void setBauer();
	public void setSpringer();
	public void setLaeufer();
	public void setTurm();
	public void setDame();
	public void setKoenig();
	public boolean istFrei();
	public boolean istSchwarz();
	public boolean istWeiss();
	public boolean istBauer();
	public boolean istSpringer();
	public boolean istLaeufer();
	public boolean istTurm();
	public boolean istDame();
	public boolean istKoenig();
	public int getFarbe();
	public int getTyp();
	public void setFarbe(int i);
	public void setTyp(int i);
	public String getNotation();
}
