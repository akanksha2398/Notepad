import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class AboutDlg extends JDialog{
	JLabel l1,l2,l3,l4;
	JButton b1;
	ImageIcon ii;
	Font f;
	
	AboutDlg(My_Notepad notepad)
	{
		super(notepad,"About",false);
		setLayout(null);
		ii=new ImageIcon("images/winlogo3.png");
		l1=new JLabel(ii,SwingConstants.CENTER);
		l2=new JLabel("_____________________________________________________________");
		l3 = new JLabel("<html>Notepad is a word processing program"
		 		+ "<br>which allows changing of text in a computer file."
		 		+ "<br>Notepad was created by the Microsoft corporation"
		 		+ "<br>It is a very simple word processor."
		 		+ "<br>It has been a part of Microsoft Windows since 1985"
		 		+ "<br>The program has options such as changing the font"
		 		+ "<br>the font size, and the font style."
		 		+ "<br>The most common use for Notepad is to view"
		 		+ "<br>or change (edit) text (.txt) files, though .dat"
		 		+ "<br>and .ini files can be changed in Notpad as well."
		 		+ "<br>Many users find Notepad a simple program for creating"
		 		+ "<br>webpages."
		 		+ "</html>");
		f=new Font(Font.SANS_SERIF,Font.ITALIC,15);
		l3.setFont(f);
		l4=new JLabel("Created By- Akanksha Khurana");
		l4.setFont(new Font(Font.SERIF,Font.BOLD,33));
		l4.setForeground(Color.red);
		
		b1=new JButton("OK");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		
		l1.setBounds(0, 0, 500, 100);
		l2.setBounds(20,60,500,50);
		l3.setBounds(60,80,500,300);
		l4.setBounds(20,340,500,50);
		b1.setBounds(200,400,80,40);
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(b1);
		
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
