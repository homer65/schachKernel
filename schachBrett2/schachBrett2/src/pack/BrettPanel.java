package pack;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.*;
import kernel.*;
import schachfiguren.*;
public class BrettPanel extends JPanel implements ImagePanelListener
{
	public static final long serialVersionUID = 1;
	private Factory factory = new Factory();
	private ImagePanel[][] feldPanel = new ImagePanel[8][8];
	private Locator locator = new Locator();
	private ArrayList<BrettListener> listener = new ArrayList<BrettListener>();
	public BrettPanel(Brett brett,boolean viewWeiss)
	{
		this.setLayout(new GridLayout(8,8));
		for (int x=1;x<9;x++)
		{
			for (int y=1;y<9;y++)
			{
				int k = y;
				int l = 9 - x;
				Feld feld = factory.getFeld();
				feld.setX(k);
				feld.setY(l);
				Figur figur = brett.getFigur(feld);
				String name = feld.getImageName(figur);
				URL url = locator.getURL(name);
				Image img = Toolkit.getDefaultToolkit().getImage(url); 
				feldPanel[k-1][l-1] = new ImagePanel(img);
				feldPanel[k-1][l-1].addListener(this,feld);
			}
		}
		for (int x=1;x<9;x++)
		{
			for (int y=1;y<9;y++)
			{
				int k = y;
				int l = 9 - x;
				if (viewWeiss)
				{
					this.add(feldPanel[k-1][l-1]);
				}
				else
				{
					this.add(feldPanel[k-1][9-l-1]);
				}
			}
		}
	}
	public void setColor(int x,int y,Color color)
	{
		feldPanel[x-1][y-1].setColor(color);
	}
	public void addListener(BrettListener bl)
	{
		listener.add(bl);
	}
	@Override
	public void pressedOnImage(Feld feld) 
	{
		BrettEvent be = new BrettEvent(feld);
		for (int i=0;i<listener.size();i++)
		{
			BrettListener bl = listener.get(i);
			bl.event(be);
		}
	}
}
