package background.controller;

import backgound.view.BackgroundFrame;
import background.model.FileGrabber;

public class BackgroundController
{
	BackgroundFrame baseFrame;
	public BackgroundController()
	{
		baseFrame = new BackgroundFrame(this);
	}
	
	public void start()
	{
		
		
	}

	public void getPictureAndSet(String path)
	{
		FileGrabber grab = new FileGrabber(path);
		baseFrame.getPanel().setPicture(grab.grabPic());
		
		
	}

}
