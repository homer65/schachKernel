package test;
import kernel.*;
public class Test1 
{
	private static Factory factory = new Factory();
	public static void main(String[] args) 
	{
		Brett brett = factory.getBrett();
		for (int i=1;i<9;i++)
		{
			for (int j=1;j<9;j++)
			{
				Feld feld = factory.getFeld();
				feld.setX(i);
				feld.setY(j);
				Figur figur = brett.getFigur(feld);
				System.out.println(feld.getNotation() + ":" + figur.getNotation());
			}
		}
	}
}
