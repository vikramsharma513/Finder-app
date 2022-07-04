import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Whois extends JFrame
{
    JTextField t1;
    TextArea t2;
    JButton b1;
    FlowLayout f;
    JProgressBar pb;
    Timer timer;
    final static int interval = 1000;
    int cc=0;
    Whois(String s)
    { super(s);
        t1= new JTextField(30);
        t2=new TextArea();
        
        
        b1=new JButton("Submit");
        t2.setEditable(false);
        f=new FlowLayout();
        pb = new JProgressBar(0,1);
        pb.setValue(0);
        pb.setStringPainted(true);
        pb.setPreferredSize(new Dimension(350,20));
        setLayout(f);
       
        add(t1);
        add(b1);
        add(pb);
        add(t2);
        
        b1.addActionListener(new ButtonListener());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocation(250,250);
        resize(400,255);
        show();
    
    
    timer = new Timer(interval, new ActionListener() {
    public void actionPerformed(ActionEvent ae)
    {
        try{
            
    int c;
    Socket s=new Socket("whois.geektools.com",43);
    InputStream in=s.getInputStream();
    OutputStream out=s.getOutputStream();
    String args[]=new String[200];
    String st=t1.getText();
    args[0]=t1.getText();
    String str=(args.length==0?st:args[0])+"\n";
    byte buf[]=str.getBytes();
    out.write(buf);
    String st1="";
 while((c=in.read())!= -1)
{
	st1=st1+(char)c+""; 
	cc=cc+1;
	pb.setValue(cc);

}

   t2.setText(st1);
   timer.stop();
   b1.setEnabled(true);
    s.close();
    }
    catch(UnknownHostException e)
    { 
    }
    catch(IOException e)
    { 
    }
    
}});}

class ButtonListener implements ActionListener {
  public void actionPerformed(ActionEvent ae) {
  b1.setEnabled(false);
  pb.setValue(0);
  cc=0;
  timer.start();
  }
  }
      public static void main(String args[])
      {
      

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				try {
					UIManager.setLookAndFeel(UIManager.
							getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
				} catch (InstantiationException e) {
				} catch (IllegalAccessException e) {
				} catch (UnsupportedLookAndFeelException e) {
				} 
				
				Whois ob = new Whois("Who.is domain search application");
			}
		});

	}
      }
      

