import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
 /* Note i use Format with 2 decimal place "#,##" , #:Number , not 1 decimal place 
 because convert to Fahrenheit contain a fraction from 2 decimal place '.15' , 
 occurred many times have showe fraction from 2 decimal place in  convert , 
 so i use Format with 2 decimal place not Format with 1 decimal place  to get  best display*/

// i use JFrame as Object waithout extend it

// element i want add to panel then add to frame
class Element
 {
 		JLabel label_;
    	JLabel label_fah;
 		JLabel label_cel;
		JLabel label_kel;

		JTextField text_;
		JTextField text_fah;
		JTextField text_cel;
		JTextField text_kel;

		JButton button_clc;
		JButton button_cls;
 }

// extends Element to use it in this class
class TemperatureConverter extends Element
 {
	JFrame frame_; // private object frame

	final int frame_width;
	final int frame_height;

	final int panel_width;
	final int panel_height;

 

	public TemperatureConverter()// default class constructor 
	{
		frame_width=450; frame_height=430;
		panel_width=415; panel_height=360;

		String title="Temperature Converter";
	 	frame_=createWindow(title,frame_width,frame_height);
	 	JPanel panel=createPanel(10,25,panel_width,panel_height);

	 	frame_.add(panel);// add panel to frame
	 	addElement(panel);// add element to panel

	 	panel.setLayout(null);
	 		
	 	//frame_.pack(); // delete space to best form
	 	frame_.setVisible(true);// to be the window visible 

	 }

	 public TemperatureConverter(String title)// class constructor with parameterized
	  {
		  frame_width=450; frame_height=430;
		  panel_width=415; panel_height=355;

	 	  frame_=createWindow(title,frame_width,frame_height);
	 	  JPanel panel=createPanel(10,30,panel_width,panel_height);

	 	  frame_.add(panel);// add panel to frame
	 	  addElement(panel);// add element to panel

	 	  panel.setLayout(null);
	 		
	 	  //frame_.pack(); // delete space to best form
	 	  frame_.setVisible(true);// to be the window visible 

	  }

	private JFrame createWindow(String title,int w,int h)  //  create window 
	{
        JFrame frame = new JFrame(title); //  frame constructor with title ...
        frame.setSize(w,h); // set size of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default close window button 
        frame.setLayout(null); // no layout because i use my special layout in use function setBounds(x,y,width, height) 

        //frame.setResizable(false); // for stop resizable windo
        //frame_.setVisible(true);

       //JFrame Center in screen
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        return frame;
	}


	private JPanel createPanel(int x,int y,int w,int h) // create new panel
	{
		 JPanel panel=new JPanel(null); // panel constructor with no layout
		 //panel.setBorder(BorderFactory.createTitledBorder(" TemperatureConverter ")); // for create border to panel
		 panel.setBackground(Color.LIGHT_GRAY);// Background of panel
		 panel.setBounds(x,y,w,h); // special layout to panel

		 return panel;
    }

	private void addElement(JPanel panel)
 	{

 		label_=new JLabel("Celsius"); // label constructor with title
		text_=new JTextField(); // text feild constructor
		label_.setFont(new Font("Courier New", Font.BOLD, 24));// font type and size to label
		label_.setBounds(25, 13, 150, 20); // layout label
		text_.setBounds(150, 10, 256, 28); // layout text feild
		panel.add(label_); // add  label  to panel
		panel.add(text_); // add text feild to panel

		label_fah=new JLabel("Fahrenheit");
	    text_fah=new JTextField();
		label_fah.setFont(new Font("Courier New", Font.BOLD, 15));
		label_fah.setBounds(40, 123, 150, 20);
		text_fah.setBounds(150, 120, 256, 28);
		panel.add(label_fah);
		panel.add(text_fah);


	    label_cel=new JLabel("Celsius");
	    text_cel=new JTextField();
		label_cel.setFont(new Font("Courier New", Font.BOLD, 15));
		label_cel.setBounds(65, 163, 150, 20);
		text_cel.setBounds(150, 160, 256, 28);
		panel.add(label_cel);
		panel.add(text_cel);

		label_kel=new JLabel("Kelvin");
	    text_kel=new JTextField();
		label_kel.setFont(new Font("Courier New", Font.BOLD, 15));
		label_kel.setBounds(70, 203, 150, 20);
		text_kel.setBounds(150, 200, 256, 28);
		panel.add(label_kel);
		panel.add(text_kel);


		button_clc = new JButton ("Calculate"); // button constructor with title 
		button_clc.setBounds(160, 240,90,27); // button layout 
		button_clc.addActionListener(new Calculate(this)); // add action to button after Listen to event
		panel.add(button_clc); // add button to panel

		button_cls = new JButton ("Clear All");
		button_cls.setBounds(275, 240,90,27);
		button_cls.addActionListener(new ClearALL(this));
        panel.add(button_cls);

 	}

 }

 // 2 separate class  with static Method return value to use this Method in addActionListener
 class Kelvine
 {
	 public static double kelvine(double val){return (val+273.15); }
 }

 class Fahrenheit
 {
	 public static double fahrenheit(double val){ return ( ( (9.0/5.0)*val ) + 32.0); }
 }

 class Calculate implements ActionListener 
 //  implements from ActionListener to use function actionPerformed 'to add action to button after Listen to event' after overwrite it
 {
	 TemperatureConverter obj;

	 public  Calculate(TemperatureConverter obj1){ this.obj=obj1; } // constructor with reference obj

	 public void actionPerformed(ActionEvent event)
	 {  double value=0;  int bool=0;
		 try{
			 	value=Double.parseDouble(obj.text_.getText()); // get value from text feild then parse it
	 		}
	 	catch(Exception e)
	 	{
			bool=1;
			JOptionPane.showMessageDialog(obj.frame_ ,"Sorry bad input -> reinput valid input");
		}  // display message in frame if occurred an error in get value fromm text feild

 
		finally
		{
			if(bool == 0)
			{
			// Format with 2 decimal place "#,##" , #:Number
			obj.text_fah.setText(String.format("%.2f", ( Fahrenheit.fahrenheit(value) ) ) );
			obj.text_cel.setText(String.format("%.2f", ( value) ) );
			obj.text_kel.setText(String.format("%.2f", ( Kelvine.kelvine(value) ) ) );
			}

			else
			{
				ClearALL a=new ClearALL(obj);
				a.clear();
			}

			bool=0;

		}

	 }

 }




 class ClearALL implements ActionListener
 {
	 TemperatureConverter obj;

	 public ClearALL(TemperatureConverter obj1){ this.obj=obj1; } // constructor with reference obj

	 public  void clear()
	 {
		 try
		 {

		 obj.text_.setText("");
		 obj.text_fah.setText("");
		 obj.text_cel.setText("");
		 obj.text_kel.setText("");
	 		
	 	}

	 	catch(Exception e) {JOptionPane.showMessageDialog(obj.frame_ ,"sorry unexpected error"); }
	 	// display message in frame if occurred an error in clear operation

	 }

	 public  void actionPerformed(ActionEvent event)
	 {
	 	clear();
	 }

 }

 
public class Main
{

	public static void main(String[] args)

	{
		String titlE="Temperature Converter";

		TemperatureConverter window=new TemperatureConverter(titlE);

    }

}

