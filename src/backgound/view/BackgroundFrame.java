package backgound.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import background.controller.BackgroundController;

public class BackgroundFrame extends JFrame
{
	private BackgroundController baseController;
	private BackgroundPanel panel;

	public BackgroundFrame(BackgroundController baseController)
	{
		this.baseController = baseController;
		this.panel = new BackgroundPanel(baseController);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setup();
	}
private void setup()
	{
		this.setTitle("Background Changer");
		this.setSize(this.getMaximumSize());
		this.setContentPane(panel);
		this.setVisible(true);
	}
public BackgroundPanel getPanel()
{
	return panel;
}

}
