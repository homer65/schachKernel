package org.myoggradio.schach.impl;
import java.io.Serializable;
import java.util.ArrayList;
import org.myoggradio.schach.*;
public class SimpleSpiel implements Spiel,Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Zug> zuege = new ArrayList<Zug>();
	private Stellung stellung = Factory.getStellung();
	private boolean hasWhiteKingMoved = false;
	private boolean hasBlackKingMoved = false;
	private boolean hasLeftWhiteRookMoved = false;
	private boolean hasRightWhiteRookMoved = false;
	private boolean hasLeftBlackRookMoved = false;
	private boolean hasRightBlackRookMoved = false;
	@Override
	public Stellung getStellung() 
	{
		return stellung;
	}
	@Override
	public ArrayList<Zug> getZuege() 
	{
		return zuege;
	}
	@Override
	public void ziehe(Zug zug) 
	{
		zuege.add(zug);
		stellung.ziehe(zug);
		Position von = zug.getVon();
		int vx = von.getX();
		int vy = von.getY();
		if (vx == 4 && vy == 0) hasWhiteKingMoved = true;
		if (vx == 4 && vy == 7) hasBlackKingMoved = true;
		if (vx == 0 && vy == 0) hasLeftWhiteRookMoved = true;
		if (vx == 0 && vy == 7) hasLeftBlackRookMoved = true;
		if (vx == 7 && vy == 0) hasRightWhiteRookMoved = true;
		if (vx == 7 && vy == 7) hasRightBlackRookMoved = true;
	}
	public boolean istWeissAmZug()
	{
		boolean erg = false;
		int n = zuege.size();
		int x = (n / 2) * 2;
		if (n == x) erg = true;
		return erg;
	}
	private boolean istSchwarzerKoenigImSchach(Stellung testStellung)
	{
		Stellung original = stellung;
		stellung = testStellung;
		boolean erg = false;
		Position positionSK = Factory.getPosition();
		Position position = Factory.getPosition();
		for (int x=0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				position.setX(x);
				position.setY(y);
				Figur figur = testStellung.getFigur(position);
				if (figur.istKoenig())
				{
					if (figur.istSchwarz())
					{
						positionSK.setX(x);
						positionSK.setY(y);
						break;
					}
				}
			}
		}
		Position von = Factory.getPosition();
		{
			for (int x = 0;x<8;x++)
			{
				for (int y = 0;y<8;y++)
				{
					von.setX(x);
					von.setY(y);
					Figur figur = testStellung.getFigur(von);
					if (figur.istWeiss())
					{
						Zug testZug = Factory.getZug();
						testZug.setVon(von);
						testZug.setNach(positionSK);
						if (figur.istKoenig())
						{
							if (istGueltigerWeisserKoenigZug(testZug))
							{
								erg = true;
								break;
							}
						}
						else if (figur.istDame())
						{
							if (istGueltigerWeisseDameZug(testZug))
							{
								erg = true;
								break;
							}
						}
						else if (figur.istTurm())
						{
							if (istGueltigerWeisserTurmZug(testZug))
							{
								erg = true;
								break;
							}
						}
						else if (figur.istLaeufer())
						{
							if (istGueltigerWeisserLaeuferZug(testZug))
							{
								erg = true;
								break;
							}
						}
						else if (figur.istSpringer())
						{
							if (istGueltigerWeisserSpringerZug(testZug))
							{
								erg = true;
								break;
							}
						}
						else if (figur.istBauer())
						{
							if (istGueltigerWeisserBauernZug(testZug))
							{
								erg = true;
								break;
							}
						}
					}
				}
			}
		}
		stellung = original;
		return erg;
	}
	private boolean istWeisserKoenigImSchach(Stellung testStellung)
	{
		Stellung original = stellung;
		stellung = testStellung;
		boolean erg = false;
		Position position = Factory.getPosition();
		Position positionWK = Factory.getPosition();
		for (int x=0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				position.setX(x);
				position.setY(y);
				Figur figur = testStellung.getFigur(position);
				if (figur.istKoenig())
				{
					if (figur.istWeiss())
					{
						positionWK.setX(x);
						positionWK.setY(y);
						break;
					}
				}
			}
		}
		Position von = Factory.getPosition();
		for (int x = 0;x<8;x++)
		{
			for (int y = 0;y<8;y++)
			{
				von.setX(x);
				von.setY(y);
				Figur figur = testStellung.getFigur(von);
				if (figur.istSchwarz())
				{
					Zug testZug = Factory.getZug();
					testZug.setVon(von);
					testZug.setNach(positionWK);
					if (figur.istKoenig())
					{
						if (istGueltigerSchwarzerKoenigZug(testZug))
						{
							erg = true;
							break;
						}
					}
					else if (figur.istDame())
					{
						if (istGueltigerSchwarzeDameZug(testZug))
						{
							erg = true;
							break;
						}
					}
					else if (figur.istTurm())
					{
						if (istGueltigerSchwarzerTurmZug(testZug))
						{
							erg = true;
							break;
						}
					}
					else if (figur.istLaeufer())
					{
						if (istGueltigerSchwarzerLaeuferZug(testZug))
						{
							erg = true;
							break;
						}
					}
					else if (figur.istSpringer())
					{
						if (istGueltigerSchwarzerSpringerZug(testZug))
						{
							erg = true;
							break;
						}
					}
					else if (figur.istBauer())
					{
						if (istGueltigerSchwarzerBauernZug(testZug))
						{
							erg = true;
							break;
						}
					}
				}
			}
		}
		stellung = original;
		return erg;
	}
	private boolean istLangeSchwarzeRochadeGueltig(Zug zug)
	{
		if (hasBlackKingMoved) return false;
		if (hasLeftBlackRookMoved) return false;
		boolean erg = true;
		Position e8 = Factory.getPosition();
		e8.setX(4);
		e8.setY(7);
		Position d8 = Factory.getPosition();
		d8.setX(3);
		d8.setY(7);
		Position c8 = Factory.getPosition();
		c8.setX(2);
		c8.setY(7);
		Figur figurD8 = stellung.getFigur(d8);
		Figur figurC8 = stellung.getFigur(c8);
		if (!figurD8.istFrei()) return false;
		if (!figurC8.istFrei()) return false;
		for (int x=0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				Position position = Factory.getPosition();
				position.setX(x);
				position.setY(y);
				Figur figur = stellung.getFigur(position);
				if (figur.istWeiss())
				{
					Zug testE8 = Factory.getZug();
					testE8.setVon(position);
					testE8.setNach(e8);
					Zug testD8 = Factory.getZug();
					testD8.setVon(position);
					testD8.setNach(d8);
					Zug testC8 = Factory.getZug();
					testC8.setVon(position);
					testC8.setNach(c8);
					if (figur.istKoenig())
					{
						if (istGueltigerWeisserKoenigZug(testE8)) return false;
						if (istGueltigerWeisserKoenigZug(testD8)) return false;
						if (istGueltigerWeisserKoenigZug(testC8)) return false;
					}
					else if (figur.istDame())
					{
						if (istGueltigerWeisseDameZug(testE8)) return false;
						if (istGueltigerWeisseDameZug(testD8)) return false;
						if (istGueltigerWeisseDameZug(testC8)) return false;
					}
					else if (figur.istTurm())
					{
						if (istGueltigerWeisserTurmZug(testE8)) return false;
						if (istGueltigerWeisserTurmZug(testD8)) return false;
						if (istGueltigerWeisserTurmZug(testC8)) return false;
					}
					else if (figur.istLaeufer())
					{
						if (istGueltigerWeisserLaeuferZug(testE8)) return false;
						if (istGueltigerWeisserLaeuferZug(testD8)) return false;
						if (istGueltigerWeisserLaeuferZug(testC8)) return false;
					}	
					else if (figur.istSpringer())
					{
						if (istGueltigerWeisserSpringerZug(testE8)) return false;
						if (istGueltigerWeisserSpringerZug(testD8)) return false;
						if (istGueltigerWeisserSpringerZug(testC8)) return false;
					}
					else if (figur.istBauer()) // Pruefen
					{
						if (istGueltigerWeisserBauernZug(testE8)) return false;
						if (istGueltigerWeisserBauernZug(testD8)) return false;
						if (istGueltigerWeisserBauernZug(testC8)) return false;
					}
				}
			}
		}
		return erg;
	}
	private boolean istKurzeSchwarzeRochadeGueltig(Zug zug)
	{
		if (hasBlackKingMoved) return false;
		if (hasRightBlackRookMoved) return false;
		boolean erg = true;
		Position e8 = Factory.getPosition();
		e8.setX(4);
		e8.setY(7);
		Position f8 = Factory.getPosition();
		f8.setX(5);
		f8.setY(7);
		Position g8 = Factory.getPosition();
		g8.setX(6);
		g8.setY(7);
		Figur figurF8 = stellung.getFigur(f8);
		Figur figurG8 = stellung.getFigur(g8);
		if (!figurF8.istFrei()) return false;
		if (!figurG8.istFrei()) return false;
		for (int x=0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				Position position = Factory.getPosition();
				position.setX(x);
				position.setY(y);
				Figur figur = stellung.getFigur(position);
				if (figur.istWeiss())
				{
					Zug testE8 = Factory.getZug();
					testE8.setVon(position);
					testE8.setNach(e8);
					Zug testF8 = Factory.getZug();
					testF8.setVon(position);
					testF8.setNach(f8);
					Zug testG8 = Factory.getZug();
					testG8.setVon(position);
					testG8.setNach(g8);
					if (figur.istKoenig())
					{
						if (istGueltigerWeisserKoenigZug(testE8)) return false;
						if (istGueltigerWeisserKoenigZug(testF8)) return false;
						if (istGueltigerWeisserKoenigZug(testG8)) return false;
					}
					else if (figur.istDame())
					{
						if (istGueltigerWeisseDameZug(testE8)) return false;
						if (istGueltigerWeisseDameZug(testF8)) return false;
						if (istGueltigerWeisseDameZug(testG8)) return false;
					}
					else if (figur.istTurm())
					{
						if (istGueltigerWeisserTurmZug(testE8)) return false;
						if (istGueltigerWeisserTurmZug(testF8)) return false;
						if (istGueltigerWeisserTurmZug(testG8)) return false;
					}
					else if (figur.istLaeufer())
					{
						if (istGueltigerWeisserLaeuferZug(testE8)) return false;
						if (istGueltigerWeisserLaeuferZug(testF8)) return false;
						if (istGueltigerWeisserLaeuferZug(testG8)) return false;
					}	
					else if (figur.istSpringer())
					{
						if (istGueltigerWeisserSpringerZug(testE8)) return false;
						if (istGueltigerWeisserSpringerZug(testF8)) return false;
						if (istGueltigerWeisserSpringerZug(testG8)) return false;
					}
					else if (figur.istBauer()) // Pruefen
					{
						if (istGueltigerWeisserBauernZug(testE8)) return false;
						if (istGueltigerWeisserBauernZug(testF8)) return false;
						if (istGueltigerWeisserBauernZug(testG8)) return false;
					}
				}
			}
		}
		return erg;
	}
	private boolean istKurzeWeisseRochadeGueltig(Zug zug)
	{
		if (hasWhiteKingMoved) return false;
		if (hasRightWhiteRookMoved) return false;
		boolean erg = true;
		Position e1 = Factory.getPosition();
		e1.setX(4);
		e1.setY(0);
		Position f1 = Factory.getPosition();
		f1.setX(5);
		f1.setY(0);
		Position g1 = Factory.getPosition();
		g1.setX(6);
		g1.setY(0);
		Figur figurF1 = stellung.getFigur(f1);
		Figur figurG1 = stellung.getFigur(g1);
		if (!figurF1.istFrei()) return false;
		if (!figurG1.istFrei()) return false;
		for (int x=0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				Position position = Factory.getPosition();
				position.setX(x);
				position.setY(y);
				Figur figur = stellung.getFigur(position);
				if (figur.istSchwarz())
				{
					Zug testE1 = Factory.getZug();
					testE1.setVon(position);
					testE1.setNach(e1);
					Zug testF1 = Factory.getZug();
					testF1.setVon(position);
					testF1.setNach(f1);
					Zug testG1 = Factory.getZug();
					testG1.setVon(position);
					testG1.setNach(g1);
					if (figur.istKoenig())
					{
						if (istGueltigerSchwarzerKoenigZug(testE1)) return false;
						if (istGueltigerSchwarzerKoenigZug(testF1)) return false;
						if (istGueltigerSchwarzerKoenigZug(testG1)) return false;
					}
					else if (figur.istDame())
					{
						if (istGueltigerSchwarzeDameZug(testE1)) return false;
						if (istGueltigerSchwarzeDameZug(testF1)) return false;
						if (istGueltigerSchwarzeDameZug(testG1)) return false;
					}
					else if (figur.istTurm())
					{
						if (istGueltigerSchwarzerTurmZug(testE1)) return false;
						if (istGueltigerSchwarzerTurmZug(testF1)) return false;
						if (istGueltigerSchwarzerTurmZug(testG1)) return false;
					}
					else if (figur.istLaeufer())
					{
						if (istGueltigerSchwarzerLaeuferZug(testE1)) return false;
						if (istGueltigerSchwarzerLaeuferZug(testF1)) return false;
						if (istGueltigerSchwarzerLaeuferZug(testG1)) return false;
					}	
					else if (figur.istSpringer())
					{
						if (istGueltigerSchwarzerSpringerZug(testE1)) return false;
						if (istGueltigerSchwarzerSpringerZug(testF1)) return false;
						if (istGueltigerSchwarzerSpringerZug(testG1)) return false;
					}
					else if (figur.istBauer()) // Pruefen
					{
						if (istGueltigerSchwarzerBauernZug(testE1)) return false;
						if (istGueltigerSchwarzerBauernZug(testF1)) return false;
						if (istGueltigerSchwarzerBauernZug(testG1)) return false;
					}
				}
			}
		}
		return erg;
	}
	private boolean istLangeWeisseRochadeGueltig(Zug zug)
	{
		if (hasWhiteKingMoved) return false;
		if (hasLeftWhiteRookMoved) return false;
		boolean erg = true;
		Position e1 = Factory.getPosition();
		e1.setX(4);
		e1.setY(0);
		Position d1 = Factory.getPosition();
		d1.setX(3);
		d1.setY(0);
		Position c1 = Factory.getPosition();
		c1.setX(2);
		c1.setY(0);
		Figur figurD1 = stellung.getFigur(d1);
		Figur figurC1 = stellung.getFigur(c1);
		if (!figurD1.istFrei()) return false;
		if (!figurC1.istFrei()) return false;
		for (int x=0;x<8;x++)
		{
			for (int y=0;y<8;y++)
			{
				Position position = Factory.getPosition();
				position.setX(x);
				position.setY(y);
				Figur figur = stellung.getFigur(position);
				if (figur.istSchwarz())
				{
					Zug testE1 = Factory.getZug();
					testE1.setVon(position);
					testE1.setNach(e1);
					Zug testD1 = Factory.getZug();
					testD1.setVon(position);
					testD1.setNach(d1);
					Zug testC1 = Factory.getZug();
					testC1.setVon(position);
					testC1.setNach(c1);
					if (figur.istKoenig())
					{
						if (istGueltigerSchwarzerKoenigZug(testE1)) return false;
						if (istGueltigerSchwarzerKoenigZug(testD1)) return false;
						if (istGueltigerSchwarzerKoenigZug(testC1)) return false;
					}
					else if (figur.istDame())
					{
						if (istGueltigerSchwarzeDameZug(testE1)) return false;
						if (istGueltigerSchwarzeDameZug(testD1)) return false;
						if (istGueltigerSchwarzeDameZug(testC1)) return false;
					}
					else if (figur.istTurm())
					{
						if (istGueltigerSchwarzerTurmZug(testE1)) return false;
						if (istGueltigerSchwarzerTurmZug(testD1)) return false;
						if (istGueltigerSchwarzerTurmZug(testC1)) return false;
					}
					else if (figur.istLaeufer())
					{
						if (istGueltigerSchwarzerLaeuferZug(testE1)) return false;
						if (istGueltigerSchwarzerLaeuferZug(testD1)) return false;
						if (istGueltigerSchwarzerLaeuferZug(testC1)) return false;
					}	
					else if (figur.istSpringer())
					{
						if (istGueltigerSchwarzerSpringerZug(testE1)) return false;
						if (istGueltigerSchwarzerSpringerZug(testD1)) return false;
						if (istGueltigerSchwarzerSpringerZug(testC1)) return false;
					}
					else if (figur.istBauer()) // Pruefen
					{
						if (istGueltigerSchwarzerBauernZug(testE1)) return false;
						if (istGueltigerSchwarzerBauernZug(testD1)) return false;
						if (istGueltigerSchwarzerBauernZug(testC1)) return false;
					}
				}
			}
		}
		return erg;
	}
	private boolean istGueltigerWeisserKoenigZug(Zug zug) // Ohne Rochade
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		if (dx == 1 && dy == 1)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
		}
		if (dx == 1 && dy == 0)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
		}
		if (dx == 0 && dy == 1)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
		}
		return erg;
	}
	private boolean istGueltigerSchwarzerKoenigZug(Zug zug) // Ohne Rochade
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		if (dx == 1 && dy == 1)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
		}
		if (dx == 1 && dy == 0)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
		}
		if (dx == 0 && dy == 1)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
		}
		return erg;
	}
	private boolean istGueltigerWeisseDameZug(Zug zug)
	{
		boolean erg = false;
		if (istGueltigerWeisserTurmZug(zug)) return true;
		if (istGueltigerWeisserLaeuferZug(zug)) return true;
		return erg;
	}
	private boolean istGueltigerSchwarzeDameZug(Zug zug)
	{
		boolean erg = false;
		if (istGueltigerSchwarzerTurmZug(zug)) return true;
		if (istGueltigerSchwarzerLaeuferZug(zug)) return true;
		return erg;
	}
	private boolean istGueltigerSchwarzerTurmZug(Zug zug)
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		if (dx == 0 && dy != 0)
		{
			int iy = (ny-vy) / dy;
			for (int i=0;i<dy-1;i++)
			{
				vy += iy;
				Position test = Factory.getPosition();
				test.setX(vx);
				test.setY(vy);
				if (!stellung.getFigur(test).istFrei()) return false;
			}
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
		}
		if (dx != 0 && dy == 0)
		{
			int ix = (nx-vx) / dx;
			for (int i=0;i<dx-1;i++)
			{
				vx += ix;
				Position test = Factory.getPosition();
				test.setX(vx);
				test.setY(vy);
				if (!stellung.getFigur(test).istFrei()) return false;
			}
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
		}
		return erg;
	}
	private boolean istGueltigerWeisserTurmZug(Zug zug)
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		if (dx == 0 && dy != 0)
		{
			int iy = (ny-vy) / dy;
			for (int i=0;i<dy-1;i++)
			{
				vy += iy;
				Position test = Factory.getPosition();
				test.setX(vx);
				test.setY(vy);
				if (!stellung.getFigur(test).istFrei()) return false;
			}
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
		}
		if (dx != 0 && dy == 0)
		{
			int ix = (nx-vx) / dx;
			for (int i=0;i<dx-1;i++)
			{
				vx += ix;
				Position test = Factory.getPosition();
				test.setX(vx);
				test.setY(vy);
				if (!stellung.getFigur(test).istFrei()) return false;
			}
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
		}
		return erg;
	}
	private boolean istGueltigerWeisserLaeuferZug(Zug zug)
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		if (dx == 0) return false;
		if (dx != dy) return false;
		int ix = (nx-vx) / dx;
		int iy = (ny-vy) / dy;
		for (int i=0;i<dx-1;i++)
		{
			vx += ix;
			vy += iy;
			Position test = Factory.getPosition();
			test.setX(vx);
			test.setY(vy);
			if (!stellung.getFigur(test).istFrei()) return false;
		}
		Figur nachFigur = stellung.getFigur(zug.getNach());
		if (nachFigur.istFrei()) return true;
		if (nachFigur.istSchwarz()) return true;
		return erg;
	}
	private boolean istGueltigerSchwarzerLaeuferZug(Zug zug)
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		if (dx == 0) return false;
		if (dx != dy) return false;
		int ix = (nx-vx) / dx;
		int iy = (ny-vy) / dy;
		for (int i=0;i<dx-1;i++)
		{
			vx += ix;
			vy += iy;
			Position test = Factory.getPosition();
			test.setX(vx);
			test.setY(vy);
			if (!stellung.getFigur(test).istFrei()) return false;
		}
		Figur nachFigur = stellung.getFigur(zug.getNach());
		if (nachFigur.istFrei()) return true;
		if (nachFigur.istWeiss()) return true;
		return erg;
	}
	private boolean istGueltigerSchwarzerSpringerZug(Zug zug)
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		Figur nachFigur = stellung.getFigur(zug.getNach());
		if (dx == 1 && dy == 2)
		{
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
			return false;
		}
		if (dx == 2 && dy == 1)
		{
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istWeiss()) return true;
			return false;
		}
		return erg;
	}
	private boolean istGueltigerWeisserSpringerZug(Zug zug)
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = Math.abs(ny-vy);
		Figur nachFigur = stellung.getFigur(zug.getNach());
		if (dx == 1 && dy == 2)
		{
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
			return false;
		}
		if (dx == 2 && dy == 1)
		{
			if (nachFigur.istFrei()) return true;
			if (nachFigur.istSchwarz()) return true;
			return false;
		}
		return erg;
	}
	private boolean istGueltigerWeisserBauernZug(Zug zug) 
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = ny-vy;
		Figur neueFigur = zug.getNeueFigur();
		if ((neueFigur != null) && (ny != 7)) return false;
		if (dx == 0 && dy == 2)
		{
			if (vy != 1) return false;
			Position test = Factory.getPosition();
			test.setX(vx);
			test.setY(2);
			if (!stellung.getFigur(test).istFrei()) return false;
			test.setY(3);
			if (!stellung.getFigur(test).istFrei()) return false;
			return true;
		}
		if (dx == 0 && dy == 1)
		{
			if (!stellung.getFigur(zug.getNach()).istFrei()) return false;
			return true;
		}
		if (dx == 1 && dy == 1)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei())
			{
				if (vy == 4)
				{
					Position test = Factory.getPosition();
					test.setX(nx);
					test.setY(4);
					if (stellung.getFigur(test).istBauer())
					{
						Zug lzug = zuege.get(zuege.size()-1);
						Position lvon = lzug.getVon();
						Position lnach = lzug.getNach();
						int lvy = lvon.getY();
						int lnx = lnach.getX();
						int lny = lnach.getY();
						if (lvy == 6 && lny == 4 && lnx == nx)
						{
							return true; // en pasant
						}
					}
				}
				return false;
			}
			if (nachFigur.istWeiss()) return false;
			return true;
		}
		return erg;
	}
	private boolean istGueltigerSchwarzerBauernZug(Zug zug) 
	{
		boolean erg = false;
		int vx = zug.getVon().getX();
		int vy = zug.getVon().getY();
		int nx = zug.getNach().getX();
		int ny = zug.getNach().getY();
		int dx = Math.abs(nx-vx);
		int dy = ny-vy;
		Figur neueFigur = zug.getNeueFigur();
		if ((neueFigur != null) && (ny != 0)) return false;
		if (dx == 0 && dy == -2)
		{
			if (vy != 6) return false;
			Position test = Factory.getPosition();
			test.setX(vx);
			test.setY(5);
			if (!stellung.getFigur(test).istFrei()) return false;
			test.setY(4);
			if (!stellung.getFigur(test).istFrei()) return false;
			return true;
		}
		if (dx == 0 && dy == -1)
		{
			if (!stellung.getFigur(zug.getNach()).istFrei()) return false;
			return true;
		}
		if (dx == 1 && dy == -1)
		{
			Figur nachFigur = stellung.getFigur(zug.getNach());
			if (nachFigur.istFrei())
			{
				if (vy == 3)
				{
					Position test = Factory.getPosition();
					test.setX(nx);
					test.setY(3);
					if (stellung.getFigur(test).istBauer())
					{
						Zug lzug = zuege.get(zuege.size()-1);
						Position lvon = lzug.getVon();
						Position lnach = lzug.getNach();
						int lvy = lvon.getY();
						int lnx = lnach.getX();
						int lny = lnach.getY();
						if (lvy == 1 && lny == 3 && lnx == nx)
						{
							return true; // en pasant
						}
					}
				}
				return false;
			}
			if (nachFigur.istSchwarz()) return false;
			return true;
		}
		return erg;
	}
	public boolean istGueltig(Zug zug)
	{
		boolean erg = true;
		Position von = zug.getVon();
		if (!von.istOnBrett())
		{
			return false;
		}
		Position nach = zug.getNach();
		if (!nach.istOnBrett())
		{
			return false;
		}
		Figur vonFigur = stellung.getFigur(zug.getVon());
		if (vonFigur == null)
		{
			return false;
		}
		if (vonFigur.istFrei())
		{
			return false;
		}
		Figur neueFigur = zug.getNeueFigur();
		boolean iWAZ = istWeissAmZug();
		boolean iWF = vonFigur.istWeiss();
		if (iWAZ && iWF)
		{
			if (vonFigur.istBauer())
			{
				erg = istGueltigerWeisserBauernZug(zug);
			}
			else if (vonFigur.istSpringer())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerWeisserSpringerZug(zug);
				}
				else
				{
					erg = false;
				}
			}
			else if (vonFigur.istLaeufer())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerWeisserLaeuferZug(zug);
				}
				else
				{
					erg = false;
				}
			}
			else if (vonFigur.istTurm())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerWeisserTurmZug(zug);
				}
				else
				{
					erg = false;
				}
			}
			else if (vonFigur.istDame())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerWeisseDameZug(zug);
				}
				else
				{
					erg = false;
				}
			}
			else if (vonFigur.istKoenig())
			{
				if (neueFigur == null)
				{	
					erg = istGueltigerWeisserKoenigZug(zug);
					if (!erg)
					{
						int vx = von.getX();
						int vy = von.getY();
						int nx = nach.getX();
						int ny = nach.getY();
						if (vx == 4 && vy == 0)
						{
							if (nx == 6 && ny == 0) // kurze weisse Rochade
							{
								erg = istKurzeWeisseRochadeGueltig(zug);
							}
							if (nx == 2 && ny == 0) // lange weisse Rochade
							{
								erg = istLangeWeisseRochadeGueltig(zug);
							}
						}
					}
				}
				else
				{
					erg = false;
				}
			}
			else 
			{
				erg = false;
			}
		}
		if ((!iWAZ) && (!iWF))
		{
			if (vonFigur.istBauer())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerSchwarzerBauernZug(zug);
				}
			}
			else if (vonFigur.istSpringer())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerSchwarzerSpringerZug(zug);
				}
			}
			else if (vonFigur.istLaeufer())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerSchwarzerLaeuferZug(zug);
				}
			}
			else if (vonFigur.istTurm())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerSchwarzerTurmZug(zug);
				}
			}
			else if (vonFigur.istDame())
			{
				if (neueFigur == null)
				{	
					erg = istGueltigerSchwarzeDameZug(zug);
				}
			}
			else if (vonFigur.istKoenig())
			{
				if (neueFigur == null)
				{
					erg = istGueltigerSchwarzerKoenigZug(zug);
					if (!erg)
					{
						int vx = von.getX();
						int vy = von.getY();
						int nx = nach.getX();
						int ny = nach.getY();
						if (vx == 4 && vy == 7)
						{
							if (nx == 6 && ny == 7) // kurze schwarze Rochade
							{
								erg = istKurzeSchwarzeRochadeGueltig(zug);
							}
							if (nx == 2 && ny == 7) // lange schwarze Rochade
							{
								erg = istLangeSchwarzeRochadeGueltig(zug);
							}
						}
					}
				}
			}
			else 
			{
				erg = false;
			}
		}
		if (iWAZ && (!iWF))
		{
			erg = false;
		}
		if ((!iWAZ) && iWF)
		{
			erg = false;
		}
		if (erg)
		{
			Stellung testStellung = stellung.copy();
			testStellung.ziehe(zug);
			if (iWAZ)
			{
				boolean nichtok = istWeisserKoenigImSchach(testStellung);
				if (nichtok)
				{
					erg = false;
				}
			}
			else
			{
				boolean nichtok = istSchwarzerKoenigImSchach(testStellung);
				if (nichtok) erg = false;
			}
		}
		return erg;
	}
	@Override
	public Spiel einZugZurueck() 
	{
		Spiel erg = Factory.getSpiel();
		int n = zuege.size();
		if (n > 0)
		{
			for (int i=0;i<n-1;i++)
			{
				Zug zug = zuege.get(i);
				erg.ziehe(zug);
			}
		}
		return erg;
	}
	@Override
	public boolean istMatt() 
	{
		boolean erg = false;
		ArrayList<Zug> zuege = gueltigeZuege();
		if (zuege.size() == 0)
		{
			if (istWeissAmZug())
			{
				if (istWeisserKoenigImSchach(stellung))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if (istSchwarzerKoenigImSchach(stellung))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return erg;
	}
	@Override
	public boolean istPatt()
	{
		boolean erg = false;
		ArrayList<Zug> zuege = gueltigeZuege();
		if (zuege.size() == 0)
		{
			if (istWeissAmZug())
			{
				if (istWeisserKoenigImSchach(stellung))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				if (istSchwarzerKoenigImSchach(stellung))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		return erg;
	}
	@Override
	public ArrayList<Zug> gueltigeZuege()
	{
		ArrayList<Zug> erg = new ArrayList<Zug>();
		for (int xv = 0;xv<8;xv++)
		{
			for (int yv = 0;yv<8;yv++)
			{
				for (int xn = 0;xn<8;xn++)
				{
					for (int yn = 0;yn<8;yn++)
					{
						Position von = Factory.getPosition();
						Position nach = Factory.getPosition();
						von.setX(xv);
						von.setY(yv);
						nach.setX(xn);
						nach.setY(yn);
						Zug zug = Factory.getZug();
						zug.setVon(von);
						zug.setNach(nach);
						if (istGueltig(zug))
						{
							if (stellung.getFigur(von).istBauer() && yn == 0)
							{
								Zug zug1 = Factory.getZug();
								Figur figur1 = Factory.getFigur();
								figur1.setSchwarz();
								figur1.setDame();
								zug1.setVon(von);
								zug1.setNach(nach);
								zug1.setNeueFigur(figur1);
								erg.add(zug1);
								Zug zug2 = Factory.getZug();
								Figur figur2 = Factory.getFigur();
								figur2.setSchwarz();
								figur2.setTurm();
								zug2.setVon(von);
								zug2.setNach(nach);
								zug2.setNeueFigur(figur2);
								erg.add(zug2);
								Zug zug3 = Factory.getZug();
								Figur figur3 = Factory.getFigur();
								figur3.setSchwarz();
								figur3.setLaeufer();
								zug3.setVon(von);
								zug3.setNach(nach);
								zug3.setNeueFigur(figur3);
								erg.add(zug3);
								Zug zug4 = Factory.getZug();
								Figur figur4 = Factory.getFigur();
								figur4.setSchwarz();
								figur4.setSpringer();
								zug4.setVon(von);
								zug4.setNach(nach);
								zug4.setNeueFigur(figur4);
								erg.add(zug4);
							}
							else if (stellung.getFigur(von).istBauer() && yn == 7)
							{
								Zug zug1 = Factory.getZug();
								Figur figur1 = Factory.getFigur();
								figur1.setWeiss();
								figur1.setDame();
								zug1.setVon(von);
								zug1.setNach(nach);
								zug1.setNeueFigur(figur1);
								erg.add(zug1);
								Zug zug2 = Factory.getZug();
								Figur figur2 = Factory.getFigur();
								figur2.setWeiss();
								figur2.setTurm();
								zug2.setVon(von);
								zug2.setNach(nach);
								zug2.setNeueFigur(figur2);
								erg.add(zug2);
								Zug zug3 = Factory.getZug();
								Figur figur3 = Factory.getFigur();
								figur3.setWeiss();
								figur3.setLaeufer();
								zug3.setVon(von);
								zug3.setNach(nach);
								zug3.setNeueFigur(figur3);
								erg.add(zug3);
								Zug zug4 = Factory.getZug();
								Figur figur4 = Factory.getFigur();
								figur4.setWeiss();
								figur4.setSpringer();
								zug4.setVon(von);
								zug4.setNach(nach);
								zug4.setNeueFigur(figur4);
								erg.add(zug4);
							}
							else
							{
								erg.add(zug);
							}
						}
					}
				}
			}
		}
		return erg;
	}
	@Override
	public String getNotationsHTML()
	{
		Stellung brett = Factory.getStellung();
		String erg = "<table>" + "\n";
		boolean links = true;
		int nr = 0;
		for (int i=0;i<zuege.size();i++)
		{
			Zug zug = zuege.get(i);
			Figur vonFigur = brett.getFigur(zug.getVon());
			Figur nachFigur = brett.getFigur(zug.getNach());
			String symbol = "x";
			if (nachFigur.istFrei()) symbol = "-";
			if (links)
			{
				erg += "<tr><td>";
				nr++;
				if (istKurzeRochade(zug,vonFigur))
				{
					erg += nr + ". " + " 0-0 " + " ";
				}
				else if (istLangeRochade(zug,vonFigur))
				{
					erg += nr + ". " + "0-0-0" + " ";
				}
				else
				{
					erg += nr + ". " + vonFigur.getNotation() + zug.getNotation(symbol) + " ";
				}
				links = false;
			}
			else
			{
				if (istKurzeRochade(zug,vonFigur))
				{
					erg += " " + " 0-0 ";
				}
				else if (istLangeRochade(zug,vonFigur))
				{
					erg += " " + "0-0-0";
				}
				else
				{	
					erg += " " + vonFigur.getNotation() + zug.getNotation(symbol);
				}
				erg += "</td></tr>" + "\n";
				links = true;
			}
			brett.ziehe(zug);
		}
		erg += "</table>" + "\n";
		return erg;
	}
	@Override
	public String getNotation() 
	{
		Stellung brett = Factory.getStellung();
		String erg = "*--------Partie Notation--------*" + "\n" + "\n";
		boolean links = true;
		int nr = 0;
		for (int i=0;i<zuege.size();i++)
		{
			Zug zug = zuege.get(i);
			Figur vonFigur = brett.getFigur(zug.getVon());
			Figur nachFigur = brett.getFigur(zug.getNach());
			String symbol = "x";
			if (nachFigur.istFrei()) symbol = "-";
			if (links)
			{
				nr++;
				if (istKurzeRochade(zug,vonFigur))
				{
					erg += nr + ". " + " 0-0 " + " ";
				}
				else if (istLangeRochade(zug,vonFigur))
				{
					erg += nr + ". " + "0-0-0" + " ";
				}
				else
				{
					erg += nr + ". " + vonFigur.getNotation() + zug.getNotation(symbol) + " ";
				}
				links = false;
			}
			else
			{
				if (istKurzeRochade(zug,vonFigur))
				{
					erg += " " + " 0-0 " + "\n";
				}
				else if (istLangeRochade(zug,vonFigur))
				{
					erg += " " + "0-0-0" + "\n";
				}
				else
				{	
					erg += " " + vonFigur.getNotation() + zug.getNotation(symbol) + "\n";
				}
				links = true;
			}
			brett.ziehe(zug);
		}
		return erg;
	}
	private boolean istKurzeRochade(Zug zug,Figur figur)
	{
		boolean erg = false;
		if (figur.istKoenig())
		{
			Position von = zug.getVon();
			Position nach = zug.getNach();
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			if (vx == 4 && vy == 0 && nx == 6 && ny == 0) erg = true;
			if (vx == 4 && vy == 7 && nx == 6 && ny == 7) erg = true;
		}
		return erg;
	}
	private boolean istLangeRochade(Zug zug,Figur figur)
	{
		boolean erg = false;
		if (figur.istKoenig())
		{
			Position von = zug.getVon();
			Position nach = zug.getNach();
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			if (vx == 4 && vy == 0 && nx == 2 && ny == 0) erg = true;
			if (vx == 4 && vy == 7 && nx == 2 && ny == 7) erg = true;
		}
		return erg;
	}
}
