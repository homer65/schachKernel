package org.myoggradio.schach.impl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.myoggradio.schach.*;
public class SimpleStellung implements Stellung,Serializable
{
	private static final long serialVersionUID = 1L;
	private Figur[][] figuren = new Figur[8][8];
	public SimpleStellung()
	{
		for (int x = 0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				figuren[x][y] = Factory.getFigur();
			}
		}
		//
		figuren[0][0].setWeiss();
		figuren[0][0].setTurm();
		figuren[1][0].setWeiss();
		figuren[1][0].setSpringer();
		figuren[2][0].setWeiss();
		figuren[2][0].setLaeufer();
		figuren[3][0].setWeiss();
		figuren[3][0].setDame();
		figuren[4][0].setWeiss();
		figuren[4][0].setKoenig();
		figuren[5][0].setWeiss();
		figuren[5][0].setLaeufer();
		figuren[6][0].setWeiss();
		figuren[6][0].setSpringer();
		figuren[7][0].setWeiss();
		figuren[7][0].setTurm();
		//
		figuren[0][1].setWeiss();
		figuren[0][1].setBauer();
		figuren[1][1].setWeiss();
		figuren[1][1].setBauer();
		figuren[2][1].setWeiss();
		figuren[2][1].setBauer();
		figuren[3][1].setWeiss();
		figuren[3][1].setBauer();
		figuren[4][1].setWeiss();
		figuren[4][1].setBauer();
		figuren[5][1].setWeiss();
		figuren[5][1].setBauer();
		figuren[6][1].setWeiss();
		figuren[6][1].setBauer();
		figuren[7][1].setWeiss();
		figuren[7][1].setBauer();
		//
		figuren[0][7].setSchwarz();
		figuren[0][7].setTurm();
		figuren[1][7].setSchwarz();
		figuren[1][7].setSpringer();
		figuren[2][7].setSchwarz();
		figuren[2][7].setLaeufer();
		figuren[3][7].setSchwarz();
		figuren[3][7].setDame();
		figuren[4][7].setSchwarz();
		figuren[4][7].setKoenig();
		figuren[5][7].setSchwarz();
		figuren[5][7].setLaeufer();
		figuren[6][7].setSchwarz();
		figuren[6][7].setSpringer();
		figuren[7][7].setSchwarz();
		figuren[7][7].setTurm();
		//
		figuren[0][6].setSchwarz();
		figuren[0][6].setBauer();
		figuren[1][6].setSchwarz();
		figuren[1][6].setBauer();
		figuren[2][6].setSchwarz();
		figuren[2][6].setBauer();
		figuren[3][6].setSchwarz();
		figuren[3][6].setBauer();
		figuren[4][6].setSchwarz();
		figuren[4][6].setBauer();
		figuren[5][6].setSchwarz();
		figuren[5][6].setBauer();
		figuren[6][6].setSchwarz();
		figuren[6][6].setBauer();
		figuren[7][6].setSchwarz();
		figuren[7][6].setBauer();
	}
	@Override
	public void setFigur(Figur figur, Position position) 
	{
		figuren[position.getX()][position.getY()] = figur;
	}
	@Override
	public Figur getFigur(Position position) 
	{
		return figuren[position.getX()][position.getY()];
	}
	@Override
	public void ziehe(Zug zug) 
	{
		Figur frei = Factory.getFigur();
		frei.setFrei();
		Position von = zug.getVon();
		Position nach = zug.getNach();
		int vx = von.getX();
		int vy = von.getY();
		int nx = nach.getX();
		int ny = nach.getY();
		Figur figurVon = getFigur(von);
		if (figurVon.istKoenig())
		{
			Figur weisserTurm = Factory.getFigur();
			weisserTurm.setTurm();
			weisserTurm.setWeiss();
			Figur schwarzerTurm = Factory.getFigur();
			schwarzerTurm.setTurm();
			schwarzerTurm.setSchwarz();
			if (vx == 4 && vy == 0 && nx == 6 && ny == 0) //weisse kurze Rochache
			{
				Position vonTurm = Factory.getPosition();
				vonTurm.setX(7);
				vonTurm.setY(0);
				Position nachTurm = Factory.getPosition();
				nachTurm.setX(5);
				nachTurm.setY(0);
				setFigur(frei,vonTurm);
				setFigur(weisserTurm,nachTurm);
			}
			if (vx == 4 && vy == 0 && nx == 2 && ny == 0) //weisse lange Rochache
			{
				Position vonTurm = Factory.getPosition();
				vonTurm.setX(0);
				vonTurm.setY(0);
				Position nachTurm = Factory.getPosition();
				nachTurm.setX(3);
				nachTurm.setY(0);
				setFigur(frei,vonTurm);
				setFigur(weisserTurm,nachTurm);
			}
			if (vx == 4 && vy == 7 && nx == 6 && ny == 7) //schwarze kurze Rochache
			{
				Position vonTurm = Factory.getPosition();
				vonTurm.setX(7);
				vonTurm.setY(7);
				Position nachTurm = Factory.getPosition();
				nachTurm.setX(5);
				nachTurm.setY(7);
				setFigur(frei,vonTurm);
				setFigur(schwarzerTurm,nachTurm);
			}
			if (vx == 4 && vy == 7 && nx == 2 && ny == 7) //weisse lange Rochache
			{
				Position vonTurm = Factory.getPosition();
				vonTurm.setX(0);
				vonTurm.setY(7);
				Position nachTurm = Factory.getPosition();
				nachTurm.setX(3);
				nachTurm.setY(7);
				setFigur(frei,vonTurm);
				setFigur(schwarzerTurm,nachTurm);
			}
		}
		if (figurVon.istBauer())
		{
			int dx = Math.abs(nx-vx);
			if (dx == 1 && vy == 4 && getFigur(nach).istFrei()) // en pasant weiss
			{
				Position entfernen = Factory.getPosition();
				entfernen.setX(nx);
				entfernen.setY(vy);
				setFigur(frei,entfernen);
			}
			if (dx == 1 && vy == 3 && getFigur(nach).istFrei()) // en pasant schwarz
			{
				Position entfernen = Factory.getPosition();
				entfernen.setX(nx);
				entfernen.setY(vy);
				setFigur(frei,entfernen);
			}
		}
		setFigur(frei,von);
		setFigur(figurVon,nach);
		if (zug.getNeueFigur() != null)
		{
			setFigur(zug.getNeueFigur(),nach);
		}
		
	}
	@Override
	public Stellung copy() 
	{
		Stellung erg = null;
		try
		{
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(this);
		    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		    ObjectInputStream ois = new ObjectInputStream(bais);
		    erg = (Stellung)ois.readObject();
		}
		catch (Exception e)
		{
			Protokol.write("Stellung:copy:Exception:");
			Protokol.write(e.toString());
			System.exit(12);
		}
		return erg;
	}
}
