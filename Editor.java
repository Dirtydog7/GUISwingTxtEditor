import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Working on this
public class Editor {

	public static void main(String[] args) {
		
		JFrame frame = new ButtonFrame();
		  frame.setVisible(true);
	}

}
class ButtonPanel extends JPanel implements ActionListener
{
  	private static final long serialVersionUID = 1L;
	private JButton timButton;
    private JButton suzyButton;
    private JButton ednaButton;

    public ButtonPanel()
    {
        timButton = new JButton("Tim (5 sec)");
        suzyButton = new JButton("Suzy (3 sec)");
        ednaButton = new JButton("Edna (2 sec)");

        add(timButton);
        add(suzyButton);
        add(ednaButton);

        timButton.addActionListener(this);
        suzyButton.addActionListener(this);
        ednaButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt)
    {
        Object source = evt.getSource();
        Color color = getBackground();
        if (source == timButton) color = Color.yellow;
        else if (source == suzyButton) color = Color.blue;
        else if (source == ednaButton) color = Color.red;
        setBackground(color);
        repaint();
    }
}


class ButtonFrame extends JFrame
{
   
	private static final long serialVersionUID = 1L;

	public ButtonFrame()
    {
        setTitle("Threaded Workers Program");
        setSize(975, 575);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new ButtonPanel());
    }
}

/*class MyMenuBar extends JMenuBar
{
	Private ButtonPanel bp = new ButtonPanel();	
private JMenu fileMenu = new JMenu(“File”); 
private JMenu colorMenu = new JMenu(“Color”);
private JMenuItem openMenuItem = new JMenuItem(“Open”);
private JMenuItem saveMenuItem = new JMenuItem(“Save”);
private JMenuItem saveAsMenuItem = new JMenuItem(“Save as”);
private JMenuItem exitMenuItem = new JMenuItem(“Exit”);
private JMenuItem redMenuItem = new JMenuItem(“Red”);
public ButtonFrame()
	{
publicMyMenuaBar()
{
	FileMenu.add(openMenuItem);
	FileMenu.add(saveMenuItem);
	FileMenu.add(saveAsMenuItem);
	FileMenu.add.Seperator();
	FileMenu.add(exitMenuItem);
	colorMenu.add(redMenuItem);
	this.add(fileMenu):
	this.add(colorMenu);
	
	re.addActionListener ( e ->
{
		setBackground(Color.RED);
		repaint();
});
	openMenuItem.addActionListener(e - >
	{
		System.out.println(“Open menu chosen”);
	});
}
}

  //INSTANCE
Public class ButtonTest
{
	Public stat void main(String[] args)
	{
	JFrame frame = new ButtonFrame();
Frame.setJMenuBar(new MyMenuBar());
Frame.setVisible(true);
}
}

//ADDING JMENU ITEMS TO MYMENUBAR
//Jmenu items go on jmenu
//Jmenu go jmenu bar
//Jmenubar goes on JFrame


Class ButtonFrame extends JFrame
[
	This.setJMenuBar(new MyMenuBar());
	setTitle(“ButtonTest”);
	setSize(300, 200);
	setDefaultCloseOperation(JFrame.Exit_on_Close);
	this.add(new ButtonPanel());
}




//Move menuBar inside of the JFrame now move
 //ButtonPanel inside the JFrame

//Replacing method

Public String replaceFirst(String regex, String replacement)
{	
String s = “This is my exciting string”;
s = s.replaceFirst(“exciting” , “wonderful”);
System.out.println(s);
}

//COMBO BOX 
 
 import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ComboBoxFrame extends JFrame
{
   private JComboBox<String> faceCombo;
   private JLabel label;
   private static final int DEFAULT_SIZE = 24;

   public ComboBoxFrame()
   {
      // add the sample text label

      label = new JLabel("The quick brown fox jumps over the lazy dog.");
      label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
      add(label, BorderLayout.CENTER);

      // make a combo box and add face names

      faceCombo = new JComboBox<>();
      faceCombo.addItem("Serif");
      faceCombo.addItem("SansSerif");
      faceCombo.addItem("Monospaced");
      faceCombo.addItem("Dialog");
      faceCombo.addItem("DialogInput");

      // the combo box listener changes the label font to the selected face name

      faceCombo.addActionListener(event ->
         label.setFont(
            new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()), 
               Font.PLAIN, DEFAULT_SIZE)));

      // add combo box to a panel at the frame's southern border

      JPanel comboPanel = new JPanel();
      comboPanel.add(faceCombo);
      add(comboPanel, BorderLayout.SOUTH);
      pack();
   }
}

MORE NOTES EARLY DEMO OF PROGRAM
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class Lab2 {
	public static void main(String[] args){
		JPanel p = new JPanel(new BorderLayout());
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
	}
}

//jframe
//jpanel north
//jpanel north
//combo box left
//combo box needs listener
//faceCombo.addActionListener(ActionEvent event ->
//label.setFont(
	//newFont(faceCombo.getItemAt(faceCombo.getSelectedIndex))
		//Font.PLAIN, DEFAULT_SIZE)));

//labeling the faceCombo Box
//public ComboBoxFrame()
//{
  

  // label = new JLabel("The quick brown fox jumps over the lazy dog.");
   //label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
  // add(label, BorderLayout.CENTER);

   // make a combo box and add face names

  // faceCombo = new JComboBox<>();
  // faceCombo.addItem("Serif");
  // faceCombo.addItem("SansSerif");
  // faceCombo.addItem("Monospaced");
  // faceCombo.addItem("Dialog");
   //faceCombo.addItem("DialogInput");
//jpanel south

//jpanel center text are and wrap in scrolling text

//jpanel south with JCenter button jtextarea jlabel jtextarea

 */

