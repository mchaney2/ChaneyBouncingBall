package chaneyBouncingBall;
import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new JFrame("Matt Chaney's Gravity App");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BallWorld(700, 650));
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}