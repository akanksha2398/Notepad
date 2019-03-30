import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ReplaceDlg extends JDialog{
	JLabel l1,l2,l3;
	JCheckBox jcb;
	JButton b1,b2,b3,b4;
	JTextField tf1,tf2;
	GridBagConstraints gbc;
	JRadioButton r1,r2;
	JPanel p1;
	ButtonGroup bg;
	My_Notepad notepad;
	
	
	ReplaceDlg(My_Notepad notepad){
		super(notepad,"Replace",false);
		setLayout(new GridBagLayout());
		l1=new JLabel("Find what:");
		l2=new JLabel("Replace with:");
		l3=new JLabel("Direction");
		 
		b1=new JButton("Find Next");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				find(notepad);
			}
		});
		b2=new JButton("Replace");    
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {//this function has bug
				int e=notepad.ta1.getSelectionEnd();
				int s=notepad.ta1.getSelectionStart();
				notepad.ta1.replaceRange(tf2.getText(),s,e);
				find(notepad);
			}
		});
		b3=new JButton("Replace All");
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notepad.ta1.setText(notepad.ta1.getText().replaceAll(tf1.getText(),tf2.getText()));
			}
		});
		b4=new JButton("Cancel");
		b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		
		jcb=new JCheckBox("Match Case");
		
		tf1=new JTextField(10);
		tf2=new JTextField(10);
		
		r1=new JRadioButton("Up",true);
		r2=new JRadioButton("Down");
		bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		
		p1=new JPanel();
		p1.add(r1);
		p1.add(r2);
		
		gbc=new GridBagConstraints();
		Insets ins=new Insets(5,5,5,5);
		gbc.insets=ins;
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(l1,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(tf1,gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(b1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(l2,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(tf2,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(b2,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(l3,gbc);
		
		gbc.gridx=2;
		gbc.gridy=2;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(b3,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(jcb,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(p1,gbc);
		
		gbc.gridx=2;
		gbc.gridy=3;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		add(b4,gbc);
		
		
		setBounds(100,100,350,200);
		setVisible(true);
	}
	
	public void find(My_Notepad notepad)
	{
		String str1,str2;
		boolean mc,dir;
		str1=My_Notepad.ta1.getText();
		str2=tf1.getText();
		mc=jcb.isSelected();
		dir=r1.isSelected();
		notepad.find(str1, str2, mc, dir);
	}
}
