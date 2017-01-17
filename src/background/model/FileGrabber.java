package background.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

public class FileGrabber
{
	private String filePath;
	public FileGrabber(String filePath)
	{
		this.filePath = filePath;
	}
	
	public ImageIcon grabPic()
	{
		try
		{
			return new ImageIcon(new File(filePath).toURI().toURL());
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void switchDesktop()
	{
		
	}
	public void setPath(String filePath)
	{
		
	}
	
}
