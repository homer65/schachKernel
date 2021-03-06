package pack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import kernel.*;
public class PartieMenu extends Menu implements ActionListener,BrettListener
{
	static final long serialVersionUID = 1;
	private Factory factory = new Factory();
	private Regeln regeln = factory.getRegeln();
	private JLabel lab1 = new JLabel("spiel beginnt");
	private JButton butt1 = new JButton("<");
	private JButton butt2 = new JButton("Matt?");
	private JButton butt3 = new JButton("Patt?");
	private JButton butt4 = new JButton("Drehen");
	private JButton butt5 = new JButton("Kernel");
	private JButton butt6 = new JButton("Notation");
	private JPanel cpan = null;
	private JPanel span = null;
	private JPanel npan = null;
	private boolean ok = false;
	private Feld von = null;
	private Feld nach = null;
	private Zug zug = null;
	private Partie partie = null;
	private BrettPanel bpan = null;
	private boolean viewWeiss = true;
	public PartieMenu(Partie partie)
	{
		this.partie = partie;
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		butt4.addActionListener(this);
		butt5.addActionListener(this);
		butt6.addActionListener(this);
		init();
	}
	public void init()
	{
		bpan = new BrettPanel(partie.getBrett(),viewWeiss);
		bpan.addListener(this);
		span = new JPanel();
		span.setLayout(new BorderLayout());
		span.add(butt1,BorderLayout.WEST);
		span.add(lab1,BorderLayout.CENTER);
		npan = new JPanel();
		npan.setLayout(new GridLayout(1,5));
		npan.add(butt2);
		npan.add(butt3);
		npan.add(butt4);
		npan.add(butt5);
		npan.add(butt6);
		cpan = new JPanel();
		cpan.setLayout(new BorderLayout());
		cpan.add(bpan,BorderLayout.CENTER);
		cpan.add(span,BorderLayout.SOUTH);
		cpan.add(npan,BorderLayout.NORTH);
		setContentPane(cpan);
		validate();
	}
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if (source == butt1)
		{
			partie = partie.einZugBack();
			init();
		}
		if (source == butt2)
		{
			boolean matt = regeln.istMatt(partie);
			if (matt) System.out.println("Partie ist matt");
			else System.out.println("Partie ist nicht matt");
		}
		if (source == butt3)
		{
			boolean patt = regeln.istPatt(partie);
			if (patt) System.out.println("Partie ist patt");
			else System.out.println("Partie ist nicht patt");
		}
		if (source == butt4)
		{
			if (viewWeiss)
			{
				viewWeiss = false;
			}
			else
			{
				viewWeiss = true;
			}
			init();
		}
		if (source == butt5)
		{
			Version version = new Version();
			String info = version.getVersion();
			System.out.println(info);
		}
		if (source == butt6)
		{
			String text = partie.getNotation();
			System.out.println(text);
		}
	}
	@Override
	public void event(BrettEvent be) 
	{
		Figur nf = null;
		Feld feld = be.getFeld();
		int x = feld.getX();
		int y = feld.getY();
		if (ok)
		{
			int vx = von.getX();
			int vy = von.getY();
			if (x == vx && y == vy)
			{
				String text = "von anulliert";
				lab1.setText(text);	
			}
			else
			{	
				nach = feld;
				if (partie.istWeissAmZug())
				{
					if (partie.getBrett().getFigur(von).istBauer())
					{
						if (partie.getBrett().getFigur(von).istWeiss())
						{
							if (nach.getY() == 8)
							{
								Figur weisseDame = factory.getFigur();
								weisseDame.setWeiss();
								weisseDame.setDame();
								String[] values = {"Dame","Turm","Laeufer","Springer"};
								Object selected = JOptionPane.showInputDialog(
										null,
										"Bitte Figur aussuchen",
										"?",
										JOptionPane.DEFAULT_OPTION,
										null,
										values,"Dame"
										);
								if (selected != null)
								{
									String s = selected.toString();
									System.out.println("PartieMenu:event:Neue Figur:" + s);
									if (s.equals("Turm")) weisseDame.setTurm();
									if (s.equals("Laeufer")) weisseDame.setLaeufer();
									if (s.equals("Springer")) weisseDame.setSpringer();
								}
								nf = weisseDame;
							}
						}
					}
				}
				else
				{
					if (partie.getBrett().getFigur(von).istBauer())
					{
						if (partie.getBrett().getFigur(von).istSchwarz())
						{
							if (nach.getY() == 1)
							{
								Figur schwarzeDame = factory.getFigur();
								schwarzeDame.setSchwarz();
								schwarzeDame.setDame();
								String[] values = {"Dame","Turm","Laeufer","Springer"};
								Object selected = JOptionPane.showInputDialog(
										null,
										"Bitte Figur aussuchen",
										"?",
										JOptionPane.DEFAULT_OPTION,
										null,
										values,"Dame"
										);
								if (selected != null)
								{
									String s = selected.toString();
									System.out.println("PartieMenu:event:Neue Figur:" + s);
									if (s.equals("Turm")) schwarzeDame.setTurm();
									if (s.equals("Laeufer")) schwarzeDame.setLaeufer();
									if (s.equals("Springer")) schwarzeDame.setSpringer();
								}
								nf = schwarzeDame;
							}
						}
					}
				}
				zug = factory.getZug();
				zug.setVon(von);
				zug.setNach(nach);
				zug.setNeueFigur(nf);
				boolean gueltig = regeln.istHalbZugGueltig(partie,zug);
				if (gueltig)
				{
					String text = partie.getNotation(zug);
					lab1.setText(text);
					partie.addHalbZug(zug);
				}
				else
				{
					lab1.setText("ungueltig");	
				}
			}
			ok = false;
			init();
		}
		else
		{
			von = feld;
			int vonx = von.getX();
			int vony = von.getY();
			lab1.setText(von.getNotation());
			ok = true;
			ArrayList<Zug> mzuege = regeln.gueltigeZuege(partie);
			for (int i=0;i<mzuege.size();i++)
			{
				Zug mzug = mzuege.get(i);
				Feld vfeld = mzug.getVon();
				int vx = vfeld.getX();
				int vy = vfeld.getY();
				if (vx == vonx && vy == vony)
				{
					Feld nfeld = mzug.getNach();
					int nx = nfeld.getX();
					int ny = nfeld.getY();
					bpan.setColor(nx,ny,Color.blue);
				}
			}
		}
	}
}
