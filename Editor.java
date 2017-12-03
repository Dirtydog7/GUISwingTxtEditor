import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class SwingTextEditor extends JFrame implements ActionListener, ItemListener {

	private JMenuItem menuOpen;
	private JMenuItem menuSave;
	private JMenuItem menuSaveAs;
	private MyListener listener;
	private JTextArea textArea;
	private String clipboard;
	private JComboBox comboFonts;
	private JTextField changeThis;
	private JTextField changeTo;
	private JCheckBox checkBold;
	private JCheckBox checkItalic;
	private ButtonGroup button;
	private JRadioButton smallButton;
	private JRadioButton mediumButton;
	private JRadioButton largeButton;

	public SwingTextEditor() {
		setTitle("Swing Text Editor");
		Container localPane = getContentPane();
		Toolkit localToolkit = Toolkit.getDefaultToolkit();
		Dimension localSize = localToolkit.getScreenSize();
		int i = localSize.height;
		int j = localSize.width;
		setSize(j / 2, i / 2);
		setLocation(j / 2 - getWidth() / 2, i / 2 - getHeight() / 2);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent paramAnonymousWindowEvent) {
				int i = JOptionPane.showConfirmDialog(SwingTextEditor.this, "Are you sure you want to exit?", "Exit?",
						0);
				if (i == 0) {
					System.exit(3);
				}
			}
		});
		JPanel JPanelBottom = new JPanel();

		JPanelBottom.setLayout(new FlowLayout());
		JButton JButtonReplace;
		JPanelBottom.add(JButtonReplace = new JButton("Replace"));
		JButtonReplace.addActionListener(this);
		this.changeThis = new JTextField(10);
		JPanelBottom.add(this.changeThis);
		JPanelBottom.add(new JLabel("with"));
		this.changeTo = new JTextField(10);
		JPanelBottom.add(this.changeTo);
		setBackground(Color.lightGray);
		localPane.add("South", JPanelBottom);

		this.textArea = new JTextArea(8, 40);
		localPane.add(new JScrollPane(this.textArea), "Center");

		JMenu JMenu = new JMenu("File");
		this.menuOpen = new JMenuItem("Open");
		this.menuSave = new JMenuItem("Save");
		this.menuSaveAs = new JMenuItem("Save As");

		JMenu.add(this.menuOpen);
		JMenu.add(this.menuSave);
		JMenu.add(this.menuSaveAs);
		SwingTextEditor.FileSaver localFileSaver = new SwingTextEditor.FileSaver();
		this.menuOpen.addActionListener(new SwingTextEditor.FileOpener1());
		this.menuSave.addActionListener(localFileSaver);
		this.menuSaveAs.addActionListener(localFileSaver);
		JMenu.add(new JSeparator());
		JMenuItem JMenuItemExit = new JMenuItem("Exit");
		JMenu.add(JMenuItemExit);

		JMenu JMenu2 = new JMenu("Edit");
		JMenuItem JMenuItemCut = new JMenuItem("Cut");
		JMenu2.add(JMenuItemCut);
		JMenuItem JMenuItemCopy = new JMenuItem("Copy");
		JMenu2.add(JMenuItemCopy);
		JMenuItem JMenuItemPaste = new JMenuItem("Paste");
		JMenu2.add(JMenuItemPaste);
		JMenuItemExit.addActionListener(this);
		JMenuItemCut.addActionListener(this);
		JMenuItemCopy.addActionListener(this);
		JMenuItemPaste.addActionListener(this);

		JMenuBar JMenuBar = new JMenuBar();
		setJMenuBar(JMenuBar);
		JMenuBar.add(JMenu);
		JMenuBar.add(JMenu2);

		JPanel JPanelNorth = new JPanel();
		JPanel localJPanel3 = new JPanel();
		JPanel JButtonPanel = new JPanel();
		JPanelNorth.setLayout(new BorderLayout());
		JPanelNorth.add(localJPanel3, "North");
		JPanelNorth.add(JButtonPanel, "South");
		localPane.add(JPanelNorth, "North");

		this.comboFonts = new JComboBox(new String[] { "SansSerif", "Serif", "Monospaced", "Dialog", "DialogInput" });

		localJPanel3.add("North", this.comboFonts);
		this.comboFonts.addItemListener(this);
		this.listener = new MyListener(this.textArea);
		localJPanel3.add(this.checkBold = new JCheckBox("Bold"));
		this.checkBold.addItemListener(this.listener);
		localJPanel3.add(this.checkItalic = new JCheckBox("Italic"));
		this.checkItalic.addItemListener(this.listener);
		JPanelNorth.add("South", JButtonPanel);
		JButtonPanel.add(this.smallButton = new JRadioButton("Small", true));
		JButtonPanel.add(this.mediumButton = new JRadioButton("Medium", true));
		JButtonPanel.add(this.largeButton = new JRadioButton("Large", false));
		this.button = new ButtonGroup();
		this.button.add(this.smallButton);
		this.button.add(this.mediumButton);
		this.button.add(this.largeButton);
		this.smallButton.addItemListener(this);
		this.mediumButton.addItemListener(this);
		this.largeButton.addItemListener(this);
		this.mediumButton.doClick();
	}

	public static AbstractButton access$200(Object this$0) {

		return null;
	}

	public static void access$302(Object this$0, File localFile) {

	}

	public void itemStateChanged(ItemEvent itemEvent) {
		int i = 1;
		Font font = this.textArea.getFont();
		int j = font.getStyle();
		int k = font.getSize();
		String str = font.getName();
		if (itemEvent.getStateChange() == 2) {
			return;
		}
		if (itemEvent.getItemSelectable() == this.smallButton) {
			k = 12;
		} else if (itemEvent.getItemSelectable() == this.mediumButton) {
			k = 16;
		} else if (itemEvent.getItemSelectable() == this.largeButton) {
			k = 20;
		} else if (itemEvent.getItemSelectable() == this.comboFonts) {
			int m = -1;
			for (int n = 0; n < this.comboFonts.getItemCount(); n++) {
				if (this.comboFonts.getItemAt(n).equals(itemEvent.getItem())) {
					str = (String) this.comboFonts.getItemAt(n);
					n += m;
					break;
				}
			}
		}
		i = i == 0 ? 1 : 0;
		if (i == 0) {
			this.textArea.setFont(new Font(str, j, k));
		}
	}

	public void actionPerformed(ActionEvent ActionEvent) {
		if (ActionEvent.getActionCommand().equals("Exit")) {
			int i = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit?", 0);
			if (i == 0) {
				System.exit(0);
			}
		} else {
			String str;
			if (ActionEvent.getActionCommand().equals("Cut")) {
				this.clipboard = this.textArea.getSelectedText();
				str = this.textArea.getText();
				this.textArea.replaceRange("", this.textArea.getSelectionStart(), this.textArea.getSelectionEnd());
			} else if (ActionEvent.getActionCommand().equals("Copy")) {
				this.clipboard = this.textArea.getSelectedText();
			} else if (ActionEvent.getActionCommand().equals("Paste")) {
				this.textArea.replaceRange(this.clipboard, this.textArea.getSelectionStart(),
						this.textArea.getSelectionEnd());
			} else if (ActionEvent.getActionCommand().equals("Replace")) {
				str = this.changeThis.getText();
				int j = this.textArea.getText().indexOf(str);
				if ((j >= 0) && (str.length() > 0)) {
					this.textArea.replaceRange(this.changeTo.getText(), j, j + str.length());
				}
			}
		}
	}

	public static void main(String[] args) {
		SwingTextEditor localSwingTextEditor = new SwingTextEditor();
		localSwingTextEditor.setDefaultCloseOperation(0);
		localSwingTextEditor.setVisible(true);
		localSwingTextEditor.textArea.requestFocus();
	}

	private File currentFile = null;

	private class FileSaver implements ActionListener {
		private FileSaver() {
		}

		public void saveTheFile(File paramFile) {
			try {
				PrintWriter localPrintWriter = new PrintWriter(paramFile);
				localPrintWriter.print(SwingTextEditor.this.textArea.getText());
				localPrintWriter.close();
				SwingTextEditor.this.currentFile = paramFile;
				SwingTextEditor.this.setTitle("Swing Text Editor [" + paramFile.getName() + "]");
			} catch (IOException localIOException) {
				System.out.println(localIOException);
				localIOException.printStackTrace();
			}
		}

		public void actionPerformed(ActionEvent paramActionEvent) {
			if ((SwingTextEditor.this.currentFile == null)
					|| (paramActionEvent.getSource() == SwingTextEditor.this.menuSaveAs)) {
				JFileChooser localJFileChooser = new JFileChooser();
				localJFileChooser.setCurrentDirectory(new File("."));
				int i = localJFileChooser.showSaveDialog(null);
				if (i == 0) {
					File localFile = new File(localJFileChooser.getSelectedFile().getPath());
					if (localFile.exists()) {
						i = JOptionPane.showConfirmDialog(SwingTextEditor.this, "File exists. Overwrite?", "Overwrite?",
								0);
						if (i == 0) {
							saveTheFile(localFile);
						}
					} else {
						saveTheFile(localFile);
					}
				}
			} else {
				saveTheFile(SwingTextEditor.this.currentFile);
			}
		}
	}

	private class FileOpener1 implements ActionListener {
		private FileOpener1() {
		}

		public void actionPerformed(ActionEvent paramActionEvent) {
			JFileChooser localJFileChooser = new JFileChooser();
			localJFileChooser.setCurrentDirectory(new File("."));
			int i = localJFileChooser.showOpenDialog(null);
			if (i == 0) {
				File localFile = new File(localJFileChooser.getSelectedFile().getPath());
				try {
					SwingTextEditor.this.textArea.setText("");
					SwingTextEditor.this.currentFile = localFile;
					BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile));
					String str;
					while ((str = localBufferedReader.readLine()) != null) {
						SwingTextEditor.this.textArea.append(str + '\n');
					}
					SwingTextEditor.this
							.setTitle("Swing Text Editor [" + SwingTextEditor.this.currentFile.getName() + "]");
					SwingTextEditor.this.textArea.setCaretPosition(0);
				} catch (IOException localIOException) {
					localIOException.printStackTrace();
				}
			}
		}
	}
}

class MyListener implements ItemListener {
	private JTextArea textArea;
	private Font currentFont;
	private int fontMode;
	private int fontSize;
	private String fontName;

	public MyListener(JTextArea JTextArea) {
		this.textArea = JTextArea;
	}

	public void itemStateChanged(ItemEvent paramItemEvent) {
		this.currentFont = this.textArea.getFont();
		this.fontMode = this.currentFont.getStyle();
		this.fontSize = this.currentFont.getSize();
		this.fontName = this.currentFont.getName();

		String str = ((JCheckBox) paramItemEvent.getItem()).getActionCommand();
		if (str.equals("Bold")) {
			this.fontMode ^= 0x1;
			this.textArea.setFont(new Font(this.fontName, this.fontMode, this.fontSize));
		} else if (str.equals("Italic")) {
			this.fontMode ^= 0x2;
			this.textArea.setFont(new Font(this.fontName, this.fontMode, this.fontSize));
		}
	}
}

class SwingTextEditorFileOpener implements ActionListener {
     //??
	private Object this$0;

	private SwingTextEditorFileOpener(SwingTextEditor SwingTextEditor) {
	}

	public void actionPerformed(ActionEvent ActionEvent) {
		JFileChooser localJFileChooser = new JFileChooser();
		localJFileChooser.setCurrentDirectory(new File("."));
		int i = localJFileChooser.showOpenDialog(null);
		if (i == 0) {
			File localFile = new File(localJFileChooser.getSelectedFile().getPath());
			try {                  //??
				SwingTextEditor.access$200(this.this$0).setText("");
				SwingTextEditor.access$302(this.this$0, localFile);
				BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile));
				String str;
				while ((str = localBufferedReader.readLine()) != null) {
					((Appendable) SwingTextEditor.access$200(this.this$0)).append(str + '\n');
				}
				((Frame) this.this$0)
						.setTitle("Swing Text Editor [" + SwingTextEditor.access$200(this.this$0).getName() + "]");
				SwingTextEditor.access$200(this.this$0).setVerticalTextPosition(0);
			} catch (IOException localIOException) {
				localIOException.printStackTrace();
			}
		}
	}
}
