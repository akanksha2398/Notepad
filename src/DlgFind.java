import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class DlgFind extends JDialog{

	JLabel l1,l2;
	JTextField tf1;
	JCheckBox jcb;
	JRadioButton r1,r2;
	JButton b1,b2;
	ButtonGroup bg;
	GridBagConstraints gbc;
	My_Notepad notepad;
	
	DlgFind(My_Notepad notepad)
	{
		super(notepad,"Find",false);//true if  we want modality dialog and vice versa
		setLayout(new GridBagLayout());
		l1=new JLabel("Find what:");
		l2=new JLabel("Direction");
		b1=new JButton("Find Next");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str1=notepad.ta1.getText();
				String str2=tf1.getText();
				boolean mc=jcb.isSelected();
				boolean dir=r1.isSelected();
				notepad.find(str1, str2, mc, dir);
			}
		});
		b2=new JButton("Cancel");
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		tf1=new JTextField(10);
		jcb=new JCheckBox("Match Case");
		r1=new JRadioButton("Up",true);
		r2=new JRadioButton("Down");
		bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		JPanel p1=new JPanel();
		p1.add(r1);
		p1.add(r2);
		
		gbc=new GridBagConstraints();
		Insets i=new Insets(5, 5, 5, 5);
		gbc.insets=i;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(l1,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(tf1,gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(b1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(jcb,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(l2,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(b2,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		add(p1,gbc);
		
		setBounds(100, 100, 350, 200);
		setVisible(true);
	}
	/*public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		if(b==b1)
		{
			String str=My_Notepad.ta1.getText();
			String s1=tf1.getText();
			boolean mc=jcb.isSelected();
			boolean dir=r1.isSelected();
			notepad.find(str,s1,mc,dir);
		}
		else if(b==b2)
		{
			dispose();
		}
	}*/
}
