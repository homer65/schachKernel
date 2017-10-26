package implement;
import java.util.ArrayList;

import kernel.*;

import java.io.*;
public class SimplePartie implements Partie,Serializable
{
	private static final long serialVersionUID = 1L;
	Factory factory = new Factory();
	Brett brett = factory.getBrett();
	ArrayList<Zug> zuege = new ArrayList<Zug>();
	boolean wwkb = false; // wurdeWeisserKoenigBewegt
	boolean wskb = false; // wurdeSchwarzerKoenigBewegt
	boolean iwaz = true; // istWeissAmZug
	boolean isaz = false; // istSchwarzAmZug
	@Override
	public Zug letzterZug()
	{
		Zug erg = null;
		int n = zuege.size();
		if (n > 0)
		{
			erg = zuege.get(n-1);
		}
		return erg;
	}
	@Override
	public void addHalbZug(Zug zug) 
	{
		Zug lzug = letzterZug();
		zuege.add(zug);
		brett.addHalbZug(zug);
		if (iwaz)
		{
			iwaz = false;
			isaz = true;
		}
		else
		{
			iwaz = true;
			isaz = false;
		}
		Feld von = zug.getVon();
		int vx = von.getX();
		int vy = von.getY();
		Feld nach = zug.getNach();
		int nx = nach.getX();
		int ny = nach.getY();
		int dx = nx - vx;
		Figur neueFigur = zug.getNeueFigur();
		if (neueFigur != null)
		{
			brett.setFigur(nach,neueFigur);
		}
		if (vx == 5 && vy == 1)
		{
			wwkb = true;
			if (nx == 7 && ny == 1) //rechteWeisseRochade
			{
				Figur frei = factory.getFigur();
				Figur weisserTurm = factory.getFigur();
				weisserTurm.setWeiss();
				weisserTurm.setTurm();
				Feld feld = factory.getFeld();
				feld.setX(6);
				feld.setY(1);
				brett.setFigur(feld,weisserTurm);
				feld.setX(8);
				brett.setFigur(feld,frei);
			}
			else if (nx == 3 && ny == 1) //linkeWeisseRochade
			{
				Figur frei = factory.getFigur();
				Figur weisserTurm = factory.getFigur();
				weisserTurm.setWeiss();
				weisserTurm.setTurm();
				Feld feld = factory.getFeld();
				feld.setX(4);
				feld.setY(1);
				brett.setFigur(feld,weisserTurm);
				feld.setX(2);
				brett.setFigur(feld,frei);
				feld.setX(1);
				brett.setFigur(feld,frei);
			}
		}
		if (vx == 5 && vy == 8)
		{
			wskb = true;
			if (nx == 7 && ny == 8) //rechteSchwarzeRochade
			{
				Figur frei = factory.getFigur();
				Figur schwarzerTurm = factory.getFigur();
				schwarzerTurm.setSchwarz();
				schwarzerTurm.setTurm();
				Feld feld = factory.getFeld();
				feld.setX(6);
				feld.setY(8);
				brett.setFigur(feld,schwarzerTurm);
				feld.setX(8);
				brett.setFigur(feld,frei);
			}
			else if (nx == 3 && ny == 8) //linkeSchwarzeRochade
			{
				Figur frei = factory.getFigur();
				Figur schwarzerTurm = factory.getFigur();
				schwarzerTurm.setSchwarz();
				schwarzerTurm.setTurm();
				Feld feld = factory.getFeld();
				feld.setX(4);
				feld.setY(8);
				brett.setFigur(feld,schwarzerTurm);
				feld.setX(2);
				brett.setFigur(feld,frei);
				feld.setX(1);
				brett.setFigur(feld,frei);
			}
		}
		if (vy == 5 && ny == 6 && Math.abs(dx) == 1) // Schlagen en passat 
		{
			Feld tfeld = factory.getFeld();
			tfeld.setX(nx);
			tfeld.setY(vy);
			Figur tfigur = brett.getFigur(tfeld);
			if (tfigur.istSchwarz() && tfigur.istBauer())
			{
				if (lzug != null)
				{
					Feld lnfeld = lzug.getNach();
					int lnx = lnfeld.getX();
					int lny = lnfeld.getY();
					Feld lvfeld = lzug.getVon();
					int lvy = lvfeld.getY();
					if (lny == 5 && lnx == nx && lvy == 7)
					{
						Figur frei = factory.getFigur();
						brett.setFigur(tfeld,frei);
					}
				}
			}
		}
		if (vy == 3 && ny == 2 && Math.abs(dx) == 1) // Schlagen en passat
		{
			Feld tfeld = factory.getFeld();
			tfeld.setX(nx);
			tfeld.setY(vy);
			Figur tfigur = brett.getFigur(tfeld);
			if (tfigur.istWeiss() && tfigur.istBauer())
			{
				if (lzug != null)
				{
					Feld lfeld = lzug.getNach();
					int lnx = lfeld.getX();
					int lny = lfeld.getY();
					Feld lvfeld = lzug.getVon();
					int lvy = lvfeld.getY();
					if (lny == 3 && lnx == nx && lvy == 1)
					{
						Figur frei = factory.getFigur();
						brett.setFigur(tfeld,frei);  
					}
				}
			}
		}
	}
	@Override
	public int getAnzahlHalbZuege() 
	{
		return zuege.size();
	}
	@Override
	public Zug getHalbZug(int i) 
	{
		return zuege.get(i);
	}
	@Override
	public Brett getBrett() 
	{
		return brett;
	}
	@Override
	public boolean istWeissAmZug() 
	{
		return iwaz;
	}
	@Override
	public boolean istSchwarzAmZug() 
	{
		return isaz;
	}
	@Override
	public boolean wurdeWeisserKoenigBewegt() 
	{
		return wwkb;
	}
	@Override
	public boolean wurdeSchwarzerKoenigBewegt() 
	{
		return wskb;
	}
	@Override
	public Partie einZugBack() 
	{
		Partie erg = factory.getPartie();
		for (int i=0;i<zuege.size() - 1;i++)
		{
			Zug zug = zuege.get(i);
			erg.addHalbZug(zug);
		}
		return erg;
	}
}
