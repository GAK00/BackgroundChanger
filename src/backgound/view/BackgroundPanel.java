package backgound.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

import background.controller.BackgroundController;

public class BackgroundPanel extends JPanel
{
	private BackgroundController baseController;
	private JFileChooser fileChooser;
	private JButton openFiles;
	private JButton setBackground;
	private JTextField passwordField;
	private JLabel Background;
	private SpringLayout layout;
	String Path;

	public BackgroundPanel(BackgroundController baseController)
	{
		this.baseController = baseController;

		fileChooser = new JFileChooser();
		openFiles = new JButton("Select File");
		setBackground = new JButton("Set");
		passwordField = new JTextField("Password");
		Background = new JLabel();
		layout = new SpringLayout();
		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.add(openFiles);
		this.add(setBackground);
		this.add(passwordField);
		this.add(Background);
	}

	private void setupLayout()
	{

		layout.putConstraint(SpringLayout.NORTH, Background, 120, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, Background, 174, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, openFiles, 0, SpringLayout.NORTH, setBackground);
		layout.putConstraint(SpringLayout.WEST, openFiles, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, passwordField, 288, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, passwordField, 134, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, passwordField, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, setBackground, -85, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, setBackground, -56, SpringLayout.EAST, this);
	}

	private void setupListeners()
	{
		openFiles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent openFiles)
			{
				openFIleManeger();
			}
		});
		setBackground.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent setBackground)
			{
				setDesktopBackground();
			}

		});
		this.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				String[] array = {"",""};
			}
			
		});
	}
	

	private void openFIleManeger()
	{
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File selected = fileChooser.getSelectedFile();
			Path = selected.getPath();
			System.out.println(Path);
			baseController.getPictureAndSet(Path);
		}
	}

	private void setDesktopBackground()
	{
		if (Files.exists(Paths.get(new File(Path).toURI())))
		{
			
			try
			{
				Process process = Runtime.getRuntime().exec("/usr/bin/sudo -S sudo cp " + Path + " /System/Library/CoreServices/DefaultDesktop.jpg");
				System.out.println(Path);
				Writer toSudo = new OutputStreamWriter(process.getOutputStream());
				String password = passwordField.getText();

				toSudo.write(password);
				toSudo.write('\n');  // sudo's docs demand a newline after the password
				toSudo.close();      // but closing the stream might be sufficient
				JOptionPane.showMessageDialog(this, "Operation copmpleted");
				setPicture(new ImageIcon(new File("/System/Library/CoreServices/DefaultDesktop.jpg").toURI().toURL()));
				int response = JOptionPane.showConfirmDialog(this, "is this your image");
				if(response == JOptionPane.YES_OPTION)
				{
					
						Process process2 = Runtime.getRuntime().exec("/usr/bin/sudo -S sudo killall Dock");

						toSudo.write(password);
						toSudo.write('\n');  // sudo's docs demand a newline after the password
						toSudo.close();      // but closing the stream might be sufficient
						System.exit(0);
					
				}
				else{JOptionPane.showMessageDialog(this, "make sure your password is correct with no extra spaces or caps");
				System.exit(0);}
			} catch (IOException e)
			{
				System.out.println("failed operation is your password right");
				e.printStackTrace();
				System.exit(0);
			}
		} else
		{
			System.out.println("Select a Valid file");
			System.exit(0);
		}

	}

	public void setPicture(ImageIcon pic)
	{
		if(pic != null){
			this.remove(Background);
		Background = new JLabel(pic);
		this.add(Background);
		this.repaint();
		this.validate();}
		else
		{
			System.out.println("noooo");
			
		}
		
	}

}
