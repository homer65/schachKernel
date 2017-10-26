package implement;
import java.util.ArrayList;
import java.io.*;
import kernel.*;
public class SimpleRegeln implements Regeln,Serializable
{
	private static final long serialVersionUID = 1L;
	private Factory factory = new Factory();
	@Override
	public boolean istHalbZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		boolean ok1 = istHalbZugGueltigOhneSchach(partie,zug);
		if (ok1)
		{
			Partie tpartie = (Partie) deepClone(partie);
			tpartie.addHalbZug(zug);
			Brett brett = tpartie.getBrett();
			if (brett == null) System.out.println("SimpleRegeln:istHalbZugGueltig:brett ist Null");
			if (tpartie.istWeissAmZug())
			{
				if (brett.istSchwarzImSchach()) return false;
			}
			else 
			{
				if (brett.istWeissImSchach()) return false;
			}
			erg = true;
		}
		return erg;
	}
	private Object deepClone(Object object) 
	{
		   try 
		   {
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ObjectOutputStream oos = new ObjectOutputStream(baos);
		     oos.writeObject(object);
		     oos.flush();
		     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		     ObjectInputStream ois = new ObjectInputStream(bais);
		     Object erg = ois.readObject();
		     baos.close();
		     bais.close();
		     return erg;
		   }
		   catch (Exception e) {
		     e.printStackTrace();
		     return null;
		   }
		 }

	private boolean istHalbZugGueltigOhneSchach(Partie partie, Zug zug) 
	{
		Brett brett = partie.getBrett();
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		if (!von.istGueltig()) return false;
		if (!nach.istGueltig()) return false;
		Figur vfigur = brett.getFigur(von);
		if (vfigur.istSchwarz() && partie.istWeissAmZug()) return false;
		if (vfigur.istWeiss() && partie.istSchwarzAmZug()) return false;
		Figur neuefigur = zug.getNeueFigur();
		if (neuefigur != null)
		{
			if (!vfigur.istBauer())
			{
				System.out.println("SimpleRegeln:istHalbZugGueltigOhneSchach:Nur Bauer kann umwandeln");
				return false;
			}
		}
		if (vfigur.istFrei()) return false;
		else if (vfigur.istBauer() && vfigur.istWeiss())
		{
			boolean ok1 = istWeisserBauerZugGueltig(partie,zug);
			if (ok1)
			{
				if (neuefigur == null) return true;
				else
				{
					int ny = nach.getY();
					if (ny == 8) return true;
					else return false;
				}
			}
			else return false;
		}
		else if (vfigur.istBauer() && vfigur.istSchwarz())
		{
			boolean ok1 = istSchwarzerBauerZugGueltig(partie,zug);
			if (ok1)
			{
				if (neuefigur == null) return true;
				else
				{
					int ny = nach.getY();
					if (ny == 1) return true;
					else return false;
				}
			}
			else return false;
		}
		else if (vfigur.istSpringer() && vfigur.istWeiss()) return istWeisserSpringerZugGueltig(partie,zug);
		else if (vfigur.istSpringer() && vfigur.istSchwarz()) return istSchwarzerSpringerZugGueltig(partie,zug);
		else if (vfigur.istLaeufer() && vfigur.istWeiss()) return istWeisserLaeuferZugGueltig(partie,zug);
		else if (vfigur.istLaeufer() && vfigur.istSchwarz()) return istSchwarzerLaeuferZugGueltig(partie,zug);
		else if (vfigur.istTurm() && vfigur.istWeiss()) return istWeisserTurmZugGueltig(partie,zug);
		else if (vfigur.istTurm() && vfigur.istSchwarz()) return istSchwarzerTurmZugGueltig(partie,zug);
		else if (vfigur.istDame() && vfigur.istWeiss()) return istWeiseDameZugGueltig(partie,zug);
		else if (vfigur.istDame() && vfigur.istSchwarz()) return istSchwarzeDameZugGueltig(partie,zug);
		else if (vfigur.istKoenig() && vfigur.istWeiss()) return istWeisserKoenigZugGueltig(partie,zug);
		else if (vfigur.istKoenig() && vfigur.istSchwarz()) return istSchwarzerKoenigZugGueltig(partie,zug);
		return false;
	}
	private boolean istWeisserKoenigZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Brett brett = partie.getBrett();
		Figur nfigur = brett.getFigur(nach);
		boolean ok1 = false;
		if (nfigur.istFrei()) ok1 = true;
		else if (nfigur.istSchwarz()) ok1 = true;
		if (ok1)
		{
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			int dx = nx - vx;
			int dy = ny - vy;
			if (dx == 0 & dy == 0) return false;
			dx = Math.abs(dx);
			dy = Math.abs(dy);
			if (dx < 2 && dy < 2) erg = true;
			if (vx == 5 && vy == 1 && nx == 7 && ny == 1) erg = istRechteWeisseRochadeGueltig(partie,zug);
			else if (vx == 5 && vy == 1 && nx == 3 && ny == 1) erg = istLinkeWeisseRochadeGueltig(partie,zug);
		}
		return erg;
	}
	private boolean istRechteWeisseRochadeGueltig(Partie partie,Zug zug)
	{
		boolean erg = true;
		if (partie.wurdeWeisserKoenigBewegt()) return false;
		Brett brett = partie.getBrett();
		Feld feld = factory.getFeld();
		feld.setX(6);
		feld.setY(1);
		Figur figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(7);
		figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(8);
		figur = brett.getFigur(feld);
		if (!(figur.istWeiss() && figur.istTurm())) return false;
		Figur weisserKoenig = factory.getFigur();
		weisserKoenig.setWeiss();
		weisserKoenig.setKoenig();
		feld.setX(6);
		brett.setFigur(feld,weisserKoenig);
		feld.setX(7);
		brett.setFigur(feld,weisserKoenig);
		if (brett.istWeissImSchach()) erg = false;
		Figur frei = factory.getFigur();
		feld.setX(6);
		brett.setFigur(feld,frei);
		feld.setX(7);
		brett.setFigur(feld,frei);
		return erg;
	}
	private boolean istLinkeWeisseRochadeGueltig(Partie partie,Zug zug)
	{
		boolean erg = true;
		if (partie.wurdeWeisserKoenigBewegt()) return false;
		Brett brett = partie.getBrett();
		Feld feld = factory.getFeld();
		feld.setX(4);
		feld.setY(1);
		Figur figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(3);
		figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(2);
		figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(1);
		figur = brett.getFigur(feld);
		if (!(figur.istWeiss() && figur.istTurm())) return false;
		Figur weisserKoenig = factory.getFigur();
		weisserKoenig.setWeiss();
		weisserKoenig.setKoenig();
		feld.setX(4);
		brett.setFigur(feld,weisserKoenig);
		feld.setX(3);
		brett.setFigur(feld,weisserKoenig);
		if (brett.istWeissImSchach()) erg = false;
		Figur frei = factory.getFigur();
		feld.setX(4);
		brett.setFigur(feld,frei);
		feld.setX(3);
		brett.setFigur(feld,frei);
		return erg;
	}
	private boolean istSchwarzerKoenigZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Brett brett = partie.getBrett();
		Figur nfigur = brett.getFigur(nach);
		boolean ok1 = false;
		if (nfigur.istFrei()) ok1 = true;
		else if (nfigur.istWeiss()) ok1 = true;
		if (ok1)
		{
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			int dx = nx - vx;
			int dy = ny - vy;
			if (dx == 0 & dy == 0) return false;
			dx = Math.abs(dx);
			dy = Math.abs(dy);
			if (dx < 2 && dy < 2) erg = true;
			if (vx == 5 && vy == 8 && nx == 7 && ny == 8) erg = istRechteSchwarzeRochadeGueltig(partie,zug);
			else if (vx == 5 && vy == 8 && nx == 3 && ny == 8) erg = istLinkeSchwarzeRochadeGueltig(partie,zug);
		}
		return erg;
	}
	private boolean istRechteSchwarzeRochadeGueltig(Partie partie,Zug zug)
	{
		boolean erg = true;
		if (partie.wurdeSchwarzerKoenigBewegt()) return false;
		Brett brett = partie.getBrett();
		Feld feld = factory.getFeld();
		feld.setX(6);
		feld.setY(8);
		Figur figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(7);
		figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(8);
		figur = brett.getFigur(feld);
		if (!(figur.istSchwarz() && figur.istTurm())) return false;
		Figur schwarzerKoenig = factory.getFigur();
		schwarzerKoenig.setSchwarz();
		schwarzerKoenig.setKoenig();
		feld.setX(6);
		brett.setFigur(feld,schwarzerKoenig);
		feld.setX(7);
		brett.setFigur(feld,schwarzerKoenig);
		if (brett.istSchwarzImSchach()) erg = false;
		Figur frei = factory.getFigur();
		feld.setX(6);
		brett.setFigur(feld,frei);
		feld.setX(7);
		brett.setFigur(feld,frei);
		return erg;
	}
	private boolean istLinkeSchwarzeRochadeGueltig(Partie partie,Zug zug)
	{
		boolean erg = true;
		if (partie.wurdeSchwarzerKoenigBewegt()) return false;
		Brett brett = partie.getBrett();
		Feld feld = factory.getFeld();
		feld.setX(4);
		feld.setY(8);
		Figur figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(3);
		figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(2);
		figur = brett.getFigur(feld);
		if (!figur.istFrei()) return false;
		feld.setX(1);
		figur = brett.getFigur(feld);
		if (!(figur.istSchwarz() && figur.istTurm())) return false;
		Figur schwarzerKoenig = factory.getFigur();
		schwarzerKoenig.setSchwarz();
		schwarzerKoenig.setKoenig();
		feld.setX(4);
		brett.setFigur(feld,schwarzerKoenig);
		feld.setX(3);
		brett.setFigur(feld,schwarzerKoenig);
		if (brett.istSchwarzImSchach()) erg = false;
		Figur frei = factory.getFigur();
		feld.setX(4);
		brett.setFigur(feld,frei);
		feld.setX(3);
		brett.setFigur(feld,frei);
		return erg;
	}
	private boolean istWeiseDameZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		if (istWeisserLaeuferZugGueltig(partie,zug)) return true;
		else if (istWeisserTurmZugGueltig(partie,zug)) return true;
		return erg;
	}
	private boolean istSchwarzeDameZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		if (istSchwarzerLaeuferZugGueltig(partie,zug)) return true;
		else if (istSchwarzerTurmZugGueltig(partie,zug)) return true;
		return erg;
	}
	private boolean istWeisserTurmZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Brett brett = partie.getBrett();
		Figur nfigur = brett.getFigur(nach);
		boolean ok1 = false;
		if (nfigur.istFrei()) ok1 = true;
		else if (nfigur.istSchwarz()) ok1 = true;
		if (ok1)
		{
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			int dx = nx - vx;
			int dy = ny - vy;
			if (dx == 0 && dy == 0) return false;
			int ix = 0;
			int iy = 0;
			int dm = 0;
			if (dx == 0)
			{
				dm = dy;
				if (dy > 0) iy = 1;
				else iy = -1;
			}
			else if (dy == 0)
			{
				dm = dx;
				if (dx > 0) ix = 1;
				else ix = -1;
			}
			else return false;
			int px = vx;
			int py = vy;
			for (int i = 1;i < Math.abs(dm);i++)
			{
				px = px + ix;
				py = py + iy;
				Feld pfeld = factory.getFeld();
				pfeld.setX(px);
				pfeld.setY(py);
				Figur pfigur = brett.getFigur(pfeld);
				if (!pfigur.istFrei()) return false;
			}
			erg = true;
		}
		return erg;
	}
	private boolean istSchwarzerTurmZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Brett brett = partie.getBrett();
		Figur nfigur = brett.getFigur(nach);
		boolean ok1 = false;
		if (nfigur.istFrei()) ok1 = true;
		else if (nfigur.istWeiss()) ok1 = true;
		if (ok1)
		{
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			int dx = nx - vx;
			int dy = ny - vy;
			if (dx == 0 && dy == 0) return false;
			int ix = 0;
			int iy = 0;
			int dm = 0;
			if (dx == 0)
			{
				dm = dy;
				if (dy > 0) iy = 1;
				else iy = -1;
			}
			else if (dy == 0)
			{
				dm = dx;
				if (dx > 0) ix = 1;
				else ix = -1;
			}
			else return false;
			int px = vx;
			int py = vy;
			for (int i = 1;i < Math.abs(dm);i++)
			{
				px = px + ix;
				py = py + iy;
				Feld pfeld = factory.getFeld();
				pfeld.setX(px);
				pfeld.setY(py);
				Figur pfigur = brett.getFigur(pfeld);
				if (!pfigur.istFrei()) return false;
			}
			erg = true;
		}
		return erg;
	}
	private boolean istWeisserLaeuferZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Brett brett = partie.getBrett();
		Figur nfigur = brett.getFigur(nach);
		boolean ok1 = false;
		if (nfigur.istFrei()) ok1 = true;
		else if (nfigur.istSchwarz()) ok1 = true;
		if (ok1)
		{
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			int dx = nx - vx;
			int dy = ny - vy;
			if (dx == 0 && dy == 0) return false;
			if (!(Math.abs(dx) == Math.abs(dy))) return false;
			int ix = 1;
			if (dx < 0) ix = -1;
			int iy = 1;
			if (dy < 0) iy = -1;
			int px = vx;
			int py = vy;
			for (int i = 1;i < Math.abs(dx);i++)
			{
				px = px + ix;
				py = py + iy;
				Feld pfeld = factory.getFeld();
				pfeld.setX(px);
				pfeld.setY(py);
				Figur pfigur = brett.getFigur(pfeld);
				if (!pfigur.istFrei()) return false;
			}
			erg = true;
		}
		return erg;
	}
	private boolean istSchwarzerLaeuferZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Brett brett = partie.getBrett();
		Figur nfigur = brett.getFigur(nach);
		boolean ok1 = false;
		if (nfigur.istFrei()) ok1 = true;
		else if (nfigur.istWeiss()) ok1 = true;
		if (ok1)
		{
			int vx = von.getX();
			int vy = von.getY();
			int nx = nach.getX();
			int ny = nach.getY();
			int dx = nx - vx;
			int dy = ny - vy;
			if (dx == 0 && dy == 0) return false;
			if (!(Math.abs(dx) == Math.abs(dy))) return false;
			int ix = 1;
			if (dx < 0) ix = -1;
			int iy = 1;
			if (dy < 0) iy = -1;
			int px = vx;
			int py = vy;
			for (int i = 1;i < Math.abs(dx);i++)
			{
				px = px + ix;
				py = py + iy;
				Feld pfeld = factory.getFeld();
				pfeld.setX(px);
				pfeld.setY(py);
				Figur pfigur = brett.getFigur(pfeld);
				if (!pfigur.istFrei()) return false;
			}
			erg = true;
		}
		return erg;
	}
	private boolean istWeisserSpringerZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		int vx = von.getX();
		int vy = von.getY();
		int nx = nach.getX();
		int ny = nach.getY();
		int dx = nx - vx;
		dx = Math.abs(dx);
		int dy = ny - vy;
		dy = Math.abs(dy);
		boolean ok1 = false;
		if (dx == 1 && dy == 2) ok1 = true;
		else if (dx == 2 && dy == 1) ok1 = true;
		if (ok1)
		{
			Brett brett = partie.getBrett();
			Figur nfigur = brett.getFigur(nach);
			if (nfigur.istFrei()) return true;
			else if (nfigur.istSchwarz()) return true;
		}
		return erg;
	}
	private boolean istSchwarzerSpringerZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		int vx = von.getX();
		int vy = von.getY();
		int nx = nach.getX();
		int ny = nach.getY();
		int dx = nx - vx;
		dx = Math.abs(dx);
		int dy = ny - vy;
		dy = Math.abs(dy);
		boolean ok1 = false;
		if (dx == 1 && dy == 2) ok1 = true;
		else if (dx == 2 && dy == 1) ok1 = true;
		if (ok1)
		{
			Brett brett = partie.getBrett();
			Figur nfigur = brett.getFigur(nach);
			if (nfigur.istFrei()) return true;
			else if (nfigur.istWeiss()) return true;
		}
		return erg;
	}
	private boolean istWeisserBauerZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		int vx = von.getX();
		int vy = von.getY();
		int nx = nach.getX();
		int ny = nach.getY();
		int dy = ny - vy;
		int dx = nx - vx;
		Brett brett = partie.getBrett();
		if (vy == 2)
		{
			if (ny == 4)
			{
				if (dx == 0)
				{
					Feld tf1 = factory.getFeld();
					tf1.setX(vx);
					tf1.setY(vy+1);
					if (brett.getFigur(tf1).istFrei())
					{
						Feld tf2 = factory.getFeld();
						tf2.setX(vx);
						tf2.setY(vy+2);
						if (brett.getFigur(tf2).istFrei()) return true;
					}
				}
			}
		}
		if (vy == 5 && ny == 6 && Math.abs(dx) == 1)
		{
			Feld tfeld = factory.getFeld();
			tfeld.setX(nx);
			tfeld.setY(vy);
			Figur tfigur = brett.getFigur(tfeld);
			if (tfigur.istSchwarz() && tfigur.istBauer())
			{
				Zug lzug = partie.letzterZug();
				if (lzug != null)
				{
					Feld lnfeld = lzug.getNach();
					int lnx = lnfeld.getX();
					int lny = lnfeld.getY();
					Feld lvfeld = lzug.getVon();
					int lvy = lvfeld.getY();
					if (lny == 5 && lnx == nx && lvy == 7) return true; // Schlagen en passat 
				}
			}
		}
		if (dy != 1) return false;
		Figur nfigur = brett.getFigur(nach);
		if (dx == 0)
		{
			if (nfigur.istFrei()) return true;
			else return false;
		}
		else if (dx == 1)
		{
			if (nfigur.istSchwarz()) return true;
			else return false;
		}
		else if (dx == -1)
		{
			if (nfigur.istSchwarz()) return true;
			else return false;
		}
		return erg;
	}
	private boolean istSchwarzerBauerZugGueltig(Partie partie,Zug zug)
	{
		boolean erg = false;
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		int vx = von.getX();
		int vy = von.getY();
		int nx = nach.getX();
		int ny = nach.getY();
		int dy = ny - vy;
		int dx = nx - vx;
		Brett brett = partie.getBrett();
		if (vy == 7)
		{
			if (ny == 5)
			{
				if (dx == 0)
				{
					Feld tf1 = factory.getFeld();
					tf1.setX(vx);
					tf1.setY(vy-1);
					if (brett.getFigur(tf1).istFrei())
					{
						Feld tf2 = factory.getFeld();
						tf2.setX(vx);
						tf2.setY(vy-2);
						if (brett.getFigur(tf2).istFrei()) return true;
					}
				}
			}
		}
		if (vy == 3 && ny == 2 && Math.abs(dx) == 1)
		{
			Feld tfeld = factory.getFeld();
			tfeld.setX(nx);
			tfeld.setY(vy);
			Figur tfigur = brett.getFigur(tfeld);
			if (tfigur.istWeiss() && tfigur.istBauer())
			{
				Zug lzug = partie.letzterZug();
				if (lzug != null)
				{
					Feld lfeld = lzug.getNach();
					int lnx = lfeld.getX();
					int lny = lfeld.getY();
					Feld lvfeld = lzug.getVon();
					int lvy = lvfeld.getY();
					if (lny == 3 && lnx == nx && lvy == 1) return true; // Schlagen en passat 
				}
			}
		}
		if (dy != -1) return false;
		Figur nfigur = brett.getFigur(nach);
		if (dx == 0)
		{
			if (nfigur.istFrei()) return true;
			else return false;
		}
		else if (dx == 1)
		{
			if (nfigur.istWeiss()) return true;
			else return false;
		}
		else if (dx == -1)
		{
			if (nfigur.istWeiss()) return true;
			else return false;
		}
		return erg;
	}
	@Override
	public ArrayList<Zug> gueltigeZuege(Partie partie) 
	{
		long start = System.nanoTime();
		ArrayList<Zug> erg = new ArrayList<Zug>();
		for (int vx = 1;vx < 9;vx++)
		{
			for (int vy = 1;vy < 9; vy++)
			{
				for (int nx = 1;nx < 9;nx++)
				{
					for (int ny = 1;ny < 9;ny++)
					{
						Feld von = factory.getFeld();
						Feld nach = factory.getFeld();
						von.setX(vx);
						von.setY(vy);
						nach.setX(nx);
						nach.setY(ny);
						Zug zug = factory.getZug();
						zug.setVon(von);
						zug.setNach(nach);
						zug.setNeueFigur(null);
						if (istHalbZugGueltig(partie,zug))
						{
							erg.add(zug);
						}
					}
				}
			}
		}
		long ende = System.nanoTime();
		long delta = ende - start;
		System.out.println("SimpleRegeln:gueltigeZuege:Nano Sekunden:" + delta);
		return erg;
	}
	@Override
	public boolean istMatt(Partie partie) 
	{
		boolean erg = false;
		ArrayList<Zug> zuege = gueltigeZuege(partie);
		if (zuege.size() == 0)
		{
			Brett brett = partie.getBrett();
			if (partie.istWeissAmZug())
			{
				if (brett.istWeissImSchach()) erg = true;
			}
			else if (partie.istSchwarzAmZug())
			{
				if (brett.istSchwarzImSchach()) erg = true;
			}
		}
		return erg;
	}
	@Override
	public boolean istPatt(Partie partie) 
	{
		boolean erg = false;
		ArrayList<Zug> zuege = gueltigeZuege(partie);
		if (zuege.size() == 0)
		{
			Brett brett = partie.getBrett();
			if (partie.istWeissAmZug())
			{
				if (!brett.istWeissImSchach()) erg = true;
			}
			else if (partie.istSchwarzAmZug())
			{
				if (!brett.istSchwarzImSchach()) erg = true;
			}
		}
		return erg;
	}
}
