package pack;
import kernel.*;
public class BrettEvent 
{
	private Feld feld = null;
	public BrettEvent(Feld feld)
	{
		this.feld = feld;
	}
	public Feld getFeld()
	{
		return feld;
	}
}
