package implement;
import kernel.*;
import java.io.Serializable;
public class SimpleBrett implements Brett,Serializable 
{
	private static final long serialVersionUID = 1L;
	Factory factory = new Factory();
	Figur[][] figuren = new Figur[8][8];
	public SimpleBrett()
	{
		Figur frei = factory.getFigur();
		frei.setFrei();
		Figur weisserBauer = factory.getFigur();
		weisserBauer.setWeiss();
		weisserBauer.setBauer();
		Figur weisserSpringer = factory.getFigur();
		weisserSpringer.setWeiss();
		weisserSpringer.setSpringer();
		Figur weisserLaeufer = factory.getFigur();
		weisserLaeufer.setWeiss();
		weisserLaeufer.setLaeufer();
		Figur weisserTurm = factory.getFigur();
		weisserTurm.setWeiss();
		weisserTurm.setTurm();
		Figur weisseDame = factory.getFigur();
		weisseDame.setWeiss();
		weisseDame.setDame();
		Figur weisserKoenig = factory.getFigur();
		weisserKoenig.setWeiss();
		weisserKoenig.setKoenig();
		Figur schwarzerBauer = factory.getFigur();
		schwarzerBauer.setSchwarz();
		schwarzerBauer.setBauer();
		Figur schwarzerSpringer = factory.getFigur();
		schwarzerSpringer.setSchwarz();
		schwarzerSpringer.setSpringer();
		Figur schwarzerLaeufer = factory.getFigur();
		schwarzerLaeufer.setSchwarz();
		schwarzerLaeufer.setLaeufer();
		Figur schwarzerTurm = factory.getFigur();
		schwarzerTurm.setSchwarz();
		schwarzerTurm.setTurm();
		Figur schwarzeDame = factory.getFigur();
		schwarzeDame.setSchwarz();
		schwarzeDame.setDame();
		Figur schwarzerKoenig = factory.getFigur();
		schwarzerKoenig.setSchwarz();
		schwarzerKoenig.setKoenig();
		figuren[0][0] = weisserTurm;
		figuren[1][0] = weisserSpringer;
		figuren[2][0] = weisserLaeufer;
		figuren[3][0] = weisseDame;
		figuren[4][0] = weisserKoenig;
		figuren[5][0] = weisserLaeufer;
		figuren[6][0] = weisserSpringer;
		figuren[7][0] = weisserTurm;
		figuren[0][1] = weisserBauer;
		figuren[1][1] = weisserBauer;
		figuren[2][1] = weisserBauer;
		figuren[3][1] = weisserBauer;
		figuren[4][1] = weisserBauer;
		figuren[5][1] = weisserBauer;
		figuren[6][1] = weisserBauer;
		figuren[7][1] = weisserBauer;
		figuren[0][2] = frei;
		figuren[1][2] = frei;
		figuren[2][2] = frei;
		figuren[3][2] = frei;
		figuren[4][2] = frei;
		figuren[5][2] = frei;
		figuren[6][2] = frei;
		figuren[7][2] = frei;
		figuren[0][3] = frei;
		figuren[1][3] = frei;
		figuren[2][3] = frei;
		figuren[3][3] = frei;
		figuren[4][3] = frei;
		figuren[5][3] = frei;
		figuren[6][3] = frei;
		figuren[7][3] = frei;
		figuren[0][4] = frei;
		figuren[1][4] = frei;
		figuren[2][4] = frei;
		figuren[3][4] = frei;
		figuren[4][4] = frei;
		figuren[5][4] = frei;
		figuren[6][4] = frei;
		figuren[7][4] = frei;
		figuren[0][5] = frei;
		figuren[1][5] = frei;
		figuren[2][5] = frei;
		figuren[3][5] = frei;
		figuren[4][5] = frei;
		figuren[5][5] = frei;
		figuren[6][5] = frei;
		figuren[7][5] = frei;
		figuren[0][6] = schwarzerBauer;
		figuren[1][6] = schwarzerBauer;
		figuren[2][6] = schwarzerBauer;
		figuren[3][6] = schwarzerBauer;
		figuren[4][6] = schwarzerBauer;
		figuren[5][6] = schwarzerBauer;
		figuren[6][6] = schwarzerBauer;
		figuren[7][6] = schwarzerBauer;
		figuren[0][7] = schwarzerTurm;
		figuren[1][7] = schwarzerSpringer;
		figuren[2][7] = schwarzerLaeufer;
		figuren[3][7] = schwarzeDame;
		figuren[4][7] = schwarzerKoenig;
		figuren[5][7] = schwarzerLaeufer;
		figuren[6][7] = schwarzerSpringer;
		figuren[7][7] = schwarzerTurm;
	}
	@Override
	public Figur getFigur(Feld feld) 
	{
		Figur erg = null;
		if (feld.istGueltig())
		{
			int x = feld.getX() - 1;
			int y = feld.getY() - 1;
			erg = figuren[x][y];
		}
		return erg;
	}
	@Override
	public void setFigur(Feld feld, Figur figur) 
	{
		if (feld.istGueltig())
		{
			int x = feld.getX() - 1;
			int y = feld.getY() - 1;
			figuren[x][y] = figur;
		}
	}
	@Override
	public void addHalbZug(Zug zug) 
	{
		Feld von = zug.getVon();
		Feld nach = zug.getNach();
		Figur neueFigur = zug.getNeueFigur();
		Figur alteFigur = getFigur(von);
		Figur frei = factory.getFigur();
		frei.setFrei();
		setFigur(von,frei);
		setFigur(nach,alteFigur);
		if (!(neueFigur == null))
		{
			if (!neueFigur.istFrei())
			{
				setFigur(nach,neueFigur);
			}	
		}
	}
	@Override
	public boolean istWeissImSchach() 
	{
		boolean erg = false;
		for (int i=1;i<9;i++)
		{
			for (int j=1;j<9;j++)
			{
				Feld feld = factory.getFeld();
				feld.setX(i);
				feld.setY(j);
				Figur figur = getFigur(feld);
				if (figur.istSchwarz())
				{
					if (figur.istBauer()) erg = istWeissDurchBauerImSchach(feld);
					else if (figur.istSpringer()) erg = istWeissDurchSpringerImSchach(feld);
					else if (figur.istLaeufer()) erg = istWeissDurchLaeuferImSchach(feld);
					else if (figur.istTurm()) erg = istWeissDurchTurmImSchach(feld);
					else if (figur.istDame()) erg = istWeissDurchDameImSchach(feld);
					else if (figur.istKoenig()) erg = istWeissDurchKoenigImSchach(feld);
				}
				if (erg) break;
			}
			if (erg) break;
		}
		return erg;
	}
	private boolean istWeissDurchKoenigImSchach(Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		int x1 = x + 1;
		int y1 = y + 1;
		int x2 = x + 1;
		int y2 = y;
		int x3 = x + 1;
		int y3 = y - 1;
		int x4 = x - 1;
		int y4 = y + 1;
		int x5 = x - 1;
		int y5 = y;
		int x6 = x - 1;
		int y6 = y - 1;
		int x7 = x;
		int y7 = y + 1;
		int x8 = x;
		int y8 = y - 1;
		if (istWeisserKoenig(x1,y1)) erg = true;
		if (istWeisserKoenig(x2,y2)) erg = true;
		if (istWeisserKoenig(x3,y3)) erg = true;
		if (istWeisserKoenig(x4,y4)) erg = true;
		if (istWeisserKoenig(x5,y5)) erg = true;
		if (istWeisserKoenig(x6,y6)) erg = true;
		if (istWeisserKoenig(x7,y7)) erg = true;
		if (istWeisserKoenig(x8,y8)) erg = true;
		return erg;
	}
	private boolean istWeissDurchDameImSchach(Feld feld)
	{
		boolean erg = false;
		if (istWeissDurchTurmImSchach(feld)) erg = true;
		if (istWeissDurchLaeuferImSchach(feld)) erg = true;
		return erg;
	}
	private boolean istWeissDurchTurmImSchach(Feld feld)
	{
		boolean erg = false;
		int dx = 1;
		int dy = 0;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = -1;
		dy = 0;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = 0;
		dy = 1;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = 0;
		dy = -1;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		return erg;
	}
	private boolean istWeissDurchLaeuferImSchach(Feld feld)
	{
		boolean erg = false;
		int dx = 1;
		int dy = 1;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = 1;
		dy = -1;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = -1;
		dy = 1;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = -1;
		dy = -1;
		if (istWeissDurchDeltaImSchach(dx,dy,feld)) erg = true;
		return erg;
	}
	private boolean istWeissDurchDeltaImSchach(int dx,int dy,Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		for (int i=1;i<8;i++)
		{
			x = x + dx;
			y = y + dy;
			Feld tfeld = factory.getFeld();
			tfeld.setX(x);
			tfeld.setY(y);
			if (!tfeld.istGueltig()) break;
			Figur tfigur = getFigur(tfeld);
			if (tfigur.istSchwarz()) break;
			if (tfigur.istWeiss())
			{
				if (tfigur.istKoenig()) erg = true;
				break;
			}
		}
		return erg;
	}
	private boolean istWeissDurchSpringerImSchach(Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		int x1 = x + 2;
		int y1 = y + 1;
		int x2 = x + 2;
		int y2 = y - 1;
		int x3 = x - 2;
		int y3 = y + 1;
		int x4 = x - 2;
		int y4 = y - 1;
		int x5 = x + 1;
		int y5 = y + 2;
		int x6 = x - 1;
		int y6 = y + 2;
		int x7 = x + 1;
		int y7 = y - 2;
		int x8 = x - 1;
		int y8 = y - 2;
		if (istWeisserKoenig(x1,y1)) erg = true;
		if (istWeisserKoenig(x2,y2)) erg = true;
		if (istWeisserKoenig(x3,y3)) erg = true;
		if (istWeisserKoenig(x4,y4)) erg = true;
		if (istWeisserKoenig(x5,y5)) erg = true;
		if (istWeisserKoenig(x6,y6)) erg = true;
		if (istWeisserKoenig(x7,y7)) erg = true;
		if (istWeisserKoenig(x8,y8)) erg = true;
		return erg;
	}
	private boolean istWeissDurchBauerImSchach(Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		int x1 = x+1;
		int y1 = y+1;
		int x2 = x-1;
		int y2 = y+1;
		if (istWeisserKoenig(x1,y1)) erg = true;
		if (istWeisserKoenig(x2,y2)) erg = true;
		return erg;
	}
	private boolean istWeisserKoenig(int x,int y)
	{
		boolean erg = false;
		Feld feld = factory.getFeld();
		feld.setX(x);
		feld.setY(y);
		if (feld.istGueltig())
		{
			Figur figur = getFigur(feld);
			if (figur.istWeiss() && figur.istKoenig()) erg = true;
		}
		return erg;
	}
	@Override
	public boolean istSchwarzImSchach() 
	{
		boolean erg = false;
		for (int i=1;i<9;i++)
		{
			for (int j=1;j<9;j++)
			{
				Feld feld = factory.getFeld();
				feld.setX(i);
				feld.setY(j);
				Figur figur = getFigur(feld);
				if (figur.istWeiss())
				{
					if (figur.istBauer()) erg = istSchwarzDurchBauerImSchach(feld);
					else if (figur.istSpringer()) erg = istSchwarzDurchSpringerImSchach(feld);
					else if (figur.istLaeufer()) erg = istSchwarzDurchLaeuferImSchach(feld);
					else if (figur.istTurm()) erg = istSchwarzDurchTurmImSchach(feld);
					else if (figur.istDame()) erg = istSchwarzDurchDameImSchach(feld);
					else if (figur.istKoenig()) erg = istSchwarzDurchKoenigImSchach(feld);
				}
				if (erg) break;
			}
			if (erg) break;
		}
		return erg;
	}
	private boolean istSchwarzDurchKoenigImSchach(Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		int x1 = x + 1;
		int y1 = y + 1;
		int x2 = x + 1;
		int y2 = y;
		int x3 = x + 1;
		int y3 = y - 1;
		int x4 = x - 1;
		int y4 = y + 1;
		int x5 = x - 1;
		int y5 = y;
		int x6 = x - 1;
		int y6 = y - 1;
		int x7 = x;
		int y7 = y + 1;
		int x8 = x;
		int y8 = y - 1;
		if (istSchwarzerKoenig(x1,y1)) erg = true;
		if (istSchwarzerKoenig(x2,y2)) erg = true;
		if (istSchwarzerKoenig(x3,y3)) erg = true;
		if (istSchwarzerKoenig(x4,y4)) erg = true;
		if (istSchwarzerKoenig(x5,y5)) erg = true;
		if (istSchwarzerKoenig(x6,y6)) erg = true;
		if (istSchwarzerKoenig(x7,y7)) erg = true;
		if (istSchwarzerKoenig(x8,y8)) erg = true;
		return erg;
	}
	private boolean istSchwarzDurchDameImSchach(Feld feld)
	{
		boolean erg = false;
		if (istSchwarzDurchTurmImSchach(feld)) erg = true;
		if (istSchwarzDurchLaeuferImSchach(feld)) erg = true;
		return erg;
	}
	private boolean istSchwarzDurchTurmImSchach(Feld feld)
	{
		boolean erg = false;
		int dx = 1;
		int dy = 0;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = -1;
		dy = 0;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = 0;
		dy = 1;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = 0;
		dy = -1;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		return erg;
	}
	private boolean istSchwarzDurchLaeuferImSchach(Feld feld)
	{
		boolean erg = false;
		int dx = 1;
		int dy = 1;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = 1;
		dy = -1;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = -1;
		dy = 1;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		dx = -1;
		dy = -1;
		if (istSchwarzDurchDeltaImSchach(dx,dy,feld)) erg = true;
		return erg;
	}
	private boolean istSchwarzDurchDeltaImSchach(int dx,int dy,Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		for (int i=1;i<8;i++)
		{
			x = x + dx;
			y = y + dy;
			Feld tfeld = factory.getFeld();
			tfeld.setX(x);
			tfeld.setY(y);
			if (!tfeld.istGueltig()) break;
			Figur tfigur = getFigur(tfeld);
			if (tfigur.istWeiss()) break;
			if (tfigur.istSchwarz())
			{
				if (tfigur.istKoenig()) erg = true;
				break;
			}
		}
		return erg;
	}
	private boolean istSchwarzDurchSpringerImSchach(Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		int x1 = x + 2;
		int y1 = y + 1;
		int x2 = x + 2;
		int y2 = y - 1;
		int x3 = x - 2;
		int y3 = y + 1;
		int x4 = x - 2;
		int y4 = y - 1;
		int x5 = x + 1;
		int y5 = y + 2;
		int x6 = x - 1;
		int y6 = y + 2;
		int x7 = x + 1;
		int y7 = y - 2;
		int x8 = x - 1;
		int y8 = y - 2;
		if (istSchwarzerKoenig(x1,y1)) erg = true;
		if (istSchwarzerKoenig(x2,y2)) erg = true;
		if (istSchwarzerKoenig(x3,y3)) erg = true;
		if (istSchwarzerKoenig(x4,y4)) erg = true;
		if (istSchwarzerKoenig(x5,y5)) erg = true;
		if (istSchwarzerKoenig(x6,y6)) erg = true;
		if (istSchwarzerKoenig(x7,y7)) erg = true;
		if (istSchwarzerKoenig(x8,y8)) erg = true;
		return erg;
	}
	private boolean istSchwarzDurchBauerImSchach(Feld feld)
	{
		boolean erg = false;
		int x = feld.getX();
		int y = feld.getY();
		int x1 = x+1;
		int y1 = y+1;
		int x2 = x-1;
		int y2 = y+1;
		if (istSchwarzerKoenig(x1,y1)) erg = true;
		if (istSchwarzerKoenig(x2,y2)) erg = true;
		return erg;
	}
	private boolean istSchwarzerKoenig(int x,int y)
	{
		boolean erg = false;
		Feld feld = factory.getFeld();
		feld.setX(x);
		feld.setY(y);
		if (feld.istGueltig())
		{
			Figur figur = getFigur(feld);
			if (figur.istSchwarz() && figur.istKoenig()) erg = true;
		}
		return erg;
	}
}
