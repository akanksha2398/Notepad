import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
class My_Notepad extends JFrame implements ActionListener{
	JMenuBar mbar;
	JMenu mnufile,mnuedit,mnuformat,mnuview,mnuhelp;
	JMenuItem mitnew,mitopen,mitsave,mitsaveas,mitexit,mitcut,mitcopy,mitpaste,mitundo,mitfind,mitfindnext,mitreplace,mitdelete,mittime_date,mitselectall,mitfont,mitviewhelp,mitabout,mitstatusbar;
	JCheckBoxMenuItem wordwrap;
	static JTextArea ta1;
	JScrollPane jsp;
	ImageIcon ii;
	File currentFile=null;
	String filename="",filepath="";
	Font defaultfnt;
	String str1,str2;
	Boolean mc,dir;
	int pos;
	Boolean saveflag=true;
	
	My_Notepad(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ta1=new JTextArea();
		jsp=new JScrollPane(ta1);
		mbar=new JMenuBar();
		mnufile=new JMenu("File");
		mnufile.addActionListener(this);
		mnuedit=new JMenu("Edit");
		mnuedit.addActionListener(this);
		mnuformat=new JMenu("Format");
		mnuformat.addActionListener(this);
		mnuview=new JMenu("View");
		mnuview.addActionListener(this);
		mnuhelp=new JMenu("Help");
		mnuhelp.addActionListener(this);
		
		mitnew=new JMenuItem("New");
		mitnew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		mitnew.setMnemonic(KeyEvent.VK_N);
		mitnew.addActionListener(this);
		mitopen=new JMenuItem("Open");
		mitopen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		mitopen.setMnemonic(KeyEvent.VK_O);
		mitopen.addActionListener(this);
		mitsave=new JMenuItem("Save");
		mitsave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		mitsave.setMnemonic(KeyEvent.VK_S);
		mitsave.addActionListener(this);
		mitsaveas=new JMenuItem("Save As..");
		mitsaveas.setMnemonic(KeyEvent.VK_A);
		mitsaveas.addActionListener(this);
		mitexit=new JMenuItem("Exit");
		mitexit.setMnemonic(KeyEvent.VK_X);
		mitexit.addActionListener(this);
		
		mitundo=new JMenuItem("Undo");
		mitundo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		mitundo.setMnemonic(KeyEvent.VK_U);
		mitundo.addActionListener(this);
		mitcut=new JMenuItem("Cut");
		mitcut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		mitcut.setMnemonic(KeyEvent.VK_T);
		mitcut.addActionListener(this);
		mitcopy=new JMenuItem("Copy");
		mitcopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		mitcopy.setMnemonic(KeyEvent.VK_C);
		mitcopy.addActionListener(this);
		mitpaste=new JMenuItem("Paste");
		mitpaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		mitpaste.setMnemonic(KeyEvent.VK_V);
		mitpaste.addActionListener(this);
		mitdelete=new JMenuItem("Delete");
		mitdelete.setMnemonic(KeyEvent.VK_D);
		mitdelete.addActionListener(this);
		mitfind=new JMenuItem("Find");
		mitfind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		mitfind.setMnemonic(KeyEvent.VK_F);
		mitfind.addActionListener(this);
		mitfindnext=new JMenuItem("Find Next..");
		mitfindnext.setMnemonic(KeyEvent.VK_N);
		mitfindnext.addActionListener(this);
		mitreplace=new JMenuItem("Replace");
		mitreplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mitreplace.setMnemonic(KeyEvent.VK_R);
		mitreplace.addActionListener(this);
		mitselectall=new JMenuItem("Select All");
		mitselectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK ));
		mitselectall.addActionListener(this);
		mittime_date=new JMenuItem("Time/Date");
		mittime_date.setMnemonic(KeyEvent.VK_F5);
		mittime_date.addActionListener(this);
		
		wordwrap=new JCheckBoxMenuItem("Wordwrap",true);
		wordwrap.setMnemonic(KeyEvent.VK_W);
		wordwrap.addActionListener(this);
		mitfont=new JMenuItem("Font..");
		mitfont.setMnemonic(KeyEvent.VK_F);
		mitfont.addActionListener(this);
		
		mitstatusbar=new JMenuItem("status bar");
		mitstatusbar.addActionListener(this);
		
		mitviewhelp=new JMenuItem("View Help");
		mitviewhelp.setMnemonic(KeyEvent.VK_H);
		mitviewhelp.addActionListener(this);
		mitabout=new JMenuItem("About Notepad");
		mitabout.setMnemonic(KeyEvent.VK_A);
		mitabout.addActionListener(this);
		
		mnufile.add(mitnew);
		mnufile.add(mitopen);
		mnufile.add(mitsave);
		mnufile.add(mitsaveas);
		mnufile.addSeparator();
		mnufile.add(mitexit);
		
		mnuedit.add(mitundo);
		mnuedit.addSeparator();
		mnuedit.add(mitcut);
		mnuedit.add(mitcopy);
		mnuedit.add(mitpaste);
		mnuedit.add(mitdelete);
		mnuedit.addSeparator();
		mnuedit.add(mitfind);
		mnuedit.add(mitfindnext);
		mnuedit.add(mitreplace);
		mnuedit.addSeparator();
		mnuedit.add(mitselectall);
		mnuedit.add(mittime_date);
		
		mnuformat.add(wordwrap);
		mnuformat.add(mitfont);
		
		mnuview.add(mitstatusbar);
		
		mnuhelp.add(mitviewhelp);
		mnuhelp.addSeparator();
		mnuhelp.add(mitabout);
		
		
		mbar.add(mnufile);
		mbar.add(mnuedit);
		mbar.add(mnuformat);
		mbar.add(mnuview);
		mbar.add(mnuhelp);
		
	    ii=new ImageIcon("images/notepad.jpg");
	    setIconImage(ii.getImage());
	    setTitle("untitled-Notepad");
		
		add(mbar,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
		try {
		FileInputStream fis=new FileInputStream("documents/notepad.cfg");
		Properties p1= new Properties();
		p1.load(fis);
		String fntname=p1.getProperty("fntname");
		int fntsize=Integer.parseInt(p1.getProperty("fntsize"));
		int fntstyle=Integer.parseInt(p1.getProperty("fntstyle"));
		defaultfnt=new Font(fntname,fntstyle,fntsize);
		}
		catch(FileNotFoundException e)
		{
			defaultfnt=new Font(Font.SERIF,Font.PLAIN,24);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		ta1.setFont(defaultfnt);
		ta1.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				saveflag=false;
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				saveflag=false;
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				saveflag=false;
				
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				close();
			}
		});
		setSize(400,400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);	
	}
	
	
	public static void main(String args[])
	{
		new My_Notepad();
	}
	public void actionPerformed(ActionEvent ae) {
		String s1=ae.getActionCommand();
		if(s1.equalsIgnoreCase("new"))
		{
			if(saveflag==true)
			{
				ta1.setText("");
				setTitle("untitled-Notepad");
				saveflag=true;
			}
			else
			{
				int ans=JOptionPane.showConfirmDialog(My_Notepad.this,"Do you want to save changes?","My-Notepad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
				if(ans==JOptionPane.YES_OPTION) {
					save();
					ta1.setText("");
					setTitle("untitled-Notepad");
					saveflag=true;
				}
				else if(ans==JOptionPane.NO_OPTION)
				{
					ta1.setText("");
					setTitle("untitled-Notepad");
					saveflag=true;
				}
			}
		}
		else if(s1.equalsIgnoreCase("open"))
		{
			JFileChooser jfc=new JFileChooser("H:\\New folder (2)");
			FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text Files", "txt");
			jfc.addChoosableFileFilter(filter1);
			jfc.setFileFilter(filter1);
			int code=jfc.showOpenDialog(this);
			if(code==jfc.APPROVE_OPTION)
			{
				try {
					currentFile=jfc.getSelectedFile();
					filename=currentFile.getName();
					filepath=currentFile.getParent();
					FileInputStream fis=new FileInputStream(currentFile);
    				int l=(int)currentFile.length();
    				byte b[]=new byte[l];
    				fis.read(b);
					ta1.setText(new String(b));
					setTitle(filename+"-Notepad");
				}
				catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				saveflag=true;
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No File selected");
			}
			
		}
		else if(s1.equalsIgnoreCase("save"))
		{
			save();
		}
		else if(s1.equalsIgnoreCase("save as.."))
		{
			saveas();
		}
		else if(s1.equalsIgnoreCase("exit"))
		{
			close();
		}
		else if(s1.equalsIgnoreCase("cut")) 
		{
			ta1.cut();
		}
		else if(s1.equalsIgnoreCase("copy"))
		{
			ta1.copy();
		}
		else if(s1.equalsIgnoreCase("paste"))
		{
			ta1.paste();
		}
		else if(s1.equalsIgnoreCase("delete"))
		{
			ta1.setText(new StringBuffer(ta1.getText()).delete(ta1.getSelectionStart(),ta1.getSelectionEnd()).toString());
		}
		else if(s1.equalsIgnoreCase("select all"))
		{
			ta1.setSelectionStart(0);
			ta1.setSelectionEnd(ta1.getText().length());
		}
		else if(s1.equalsIgnoreCase("Time/Date"))
		{
			Date d1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("hh:mm dd-MM-yyyy");
			ta1.insert(sdf.format(d1),ta1.getCaretPosition());
		}
		else if(s1.equalsIgnoreCase("find"))
		{
			new DlgFind(this);
		}
		else if(s1.equalsIgnoreCase("find next.."))
		{
			find(str1,str2,mc,dir);
		}
		else if(s1.equalsIgnoreCase("replace"))
		{
			new ReplaceDlg(this);
		}
		else if(s1.equalsIgnoreCase("wordwrap"))
		{
			ta1.setLineWrap(wordwrap.isSelected());
		}
		else if(s1.equalsIgnoreCase("font"))
		{
			
		}
		else if(s1.equalsIgnoreCase("about notepad"))
		{
			new AboutDlg(this);
		}
		else if(s1.equalsIgnoreCase("view help"))
		{
			new HelpDlg(this);
		}
	}
	
	public void save()
	{
		if(currentFile==null)//if we are saving file for first time
			saveas();
		else
		{
			try {
			FileOutputStream fos=new FileOutputStream(currentFile);
			fos.write(ta1.getText().getBytes());
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			setTitle(filename+"-Notepad");
			saveflag=true;
		}
	}
	public void saveas()
	{
		JFileChooser jfc= new JFileChooser("H:\\New folder (2)");
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text Files","txt");
		jfc.addChoosableFileFilter(filter1);
		jfc.setFileFilter(filter1);
		int code=jfc.showSaveDialog(this);
		if(code==jfc.APPROVE_OPTION)
		{
			try {
				currentFile=jfc.getSelectedFile();
				filename=currentFile.getName();
				filepath=currentFile.getPath();
				FileOutputStream fos=new FileOutputStream(currentFile);
				fos.write(ta1.getText().getBytes());
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			setTitle(filename+"-Notepad");
			saveflag=true;
		}
	}
	
	public void close()
	{
		if(saveflag==false)
		{
			int ans=JOptionPane.showConfirmDialog(My_Notepad.this,"Do you want to save changes?","My_Notepad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(ans==JOptionPane.YES_OPTION) {
				save();
				dispose();
			}
			else if(ans==JOptionPane.NO_OPTION)
			{
				dispose();
			}
		}
		else
		{
			dispose();
		}
	}
	
	
	public void find(String str1,String str2,boolean mc,boolean dir) //
	{
		this.str1=str1;
		this.str2=str2;
		this.mc=mc;
		this.dir=dir;
		 pos=My_Notepad.ta1.getCaretPosition();
		boolean flag=false;
		if(mc==false)
		{
			str1=str1.toUpperCase();
			str2=str2.toUpperCase();
		}
		if(dir==true)// up
		{
			if(flag==false)
			{
				pos-=(str2.length()+1);
				flag=true;
			}
			pos=str1.lastIndexOf(str2, pos);
		}
		else  //down
		{
			pos=str1.indexOf(str2,pos);
		}
		if(pos==-1)
		{
			JOptionPane.showMessageDialog(this,"String not found");
		}
		else
		{
			My_Notepad.ta1.setSelectionStart(pos);
			My_Notepad.ta1.setSelectionEnd(pos+str2.length());
		}
	}
}
