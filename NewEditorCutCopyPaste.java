import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class NewEditor {

	public static void main(String[] args) {

		JFrame frame = new EditorFrame();
		frame.pack();
		frame.setVisible(true);
	}
}

class EditorFrame extends JFrame {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private String title = "Editor";
	private EditorMenu menubar = new EditorMenu();
	TextControls textControlPanel = new TextControls();
	JTextPane textPane = new JTextPane();
	JPanel replacePane = new ReplaceEditor();
	private File currentFile = null;
	private JFrame thisFrame = null;
	
	public EditorFrame() {

		thisFrame = this;
		setTitle(title);
		setJMenuBar(menubar);
		buildLayout();
		setActions();

		// setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	private void buildLayout() {

		Container pane = this.getContentPane();
		pane.add(textControlPanel, BorderLayout.NORTH);
		pane.add(textPane, BorderLayout.CENTER);
		pane.add(replacePane, BorderLayout.SOUTH);
	}

		private void setActions() {

			String clipboard;
		// Set the font type
		textControlPanel.getFontList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// Set the font style to Bold

		// https://docs.oracle.com/javase/7/docs/api/javax/swing/JCheckBox.html

		textControlPanel.getBoldCheckBox().addItemListener(new ItemListener() {

			private ItemSelectable smallButton;
			private ItemSelectable mediumButton;
			private ItemSelectable largeButton;
			private ItemSelectable comboFonts;
			@Override

			public void itemStateChanged(ItemEvent itemEvent) {
				int i = 1;
				Font font = textPane.getFont();
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
					for (int n = 0; n < ((JMenu) this.comboFonts).getItemCount(); n++) {
						if (((JComboBox<String>) this.comboFonts).getItemAt(n).equals(itemEvent.getItem())) {
							str = (String) ((JComboBox<String>) this.comboFonts).getItemAt(n);
							n += m;
							break;
						}
					}
				}
				i = i == 0 ? 1 : 0;
				if (i == 0) {
					textPane.setFont(new Font(str, j, k));
				}
			}
			public void itemStateChanged1(ItemEvent event) {

				// If the box is checked, we want to bold the text
				boolean setItalic = event.getStateChange() == ItemEvent.SELECTED;
				Font font = textPane.getFont();
				textPane.setFont(new Font(font.getName(), setItalic ? Font.ITALIC : Font.PLAIN, font.getSize()));
			}
		}};

		// Set the font size
		ActionListener pasteListener = new ActionListener(){
			
		String clipboard;
		
			public void actionPerformed(ActionEvent e) {
			e.getActionCommand().equals("Paste") {
			textPane.replaceRange( clipboard, textPane.getSelectionStart(),textPane.getSelectionEnd());
		}
			}};
			menubar.getPasteMenuItem().addActionListener(pasteListener);
		
		ActionListener copyListener = new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				e.getActionCommand().equals("Copy") {
					String clipboard = textPane.getSelectedText();
			}
		}};
		menubar.getCopyMenuItem().addActionListener(copyListener);
		
		ActionListener cutListener = new ActionListener() {
		
		
			public void actionPerformed(ActionEvent e) {
		
				
				e.getActionCommand().equals("Cut")) {
				clipboard = textPane.getSelectedText();
				String	str = textPane.getText();
					textPane.replaceRange("", textPane.getSelectionStart(), textPane.getSelectionEnd());
				
			}
		}};
		
		menubar.getCutMenuItem().addActionListener(cutListener);
	
		// Save the file

		ActionListener saveListener = new ActionListener() {

			private void saveTheFile(File file) {
				try {

				PrintWriter localPrintWriter = new PrintWriter(file);
				localPrintWriter.print(textPane.getText());
				localPrintWriter.close();
				currentFile = file;
				setTitle("Swing Text Editor [" + file.getName() + "]");
				} catch (IOException localIOException) {
					System.out.println(localIOException);
					localIOException.printStackTrace();
				}
			}
			@Override
			public void actionPerformed(ActionEvent e) {

				if ((currentFile == null) || (e.getSource() == menubar.getSaveAsMenuItem())) {
					JFileChooser localJFileChooser = new JFileChooser();
					localJFileChooser.setCurrentDirectory(new File("."));
					int i = localJFileChooser.showSaveDialog(null);
					if (i == 0) {
						File localFile = new File(localJFileChooser.getSelectedFile().getPath());
						if (localFile.exists()) {
							i = JOptionPane.showConfirmDialog(thisFrame, "File exists. Overwrite?", "Overwrite?", 0);
							if (i == 0) {
								saveTheFile(localFile);
							}
						} else {
							saveTheFile(localFile);
						}
					}
				} else {
					saveTheFile(currentFile);
				}
			}
		};

		menubar.getSaveMenuItem().addActionListener(saveListener);
		menubar.getSaveAsMenuItem().addActionListener(saveListener);



		ActionListener fileOpener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Create a file chooser
				JFileChooser localJFileChooser = new JFileChooser();

				// Set the base director for the director we are running from
				// within
				localJFileChooser.setCurrentDirectory(new File("."));
				// Check if there's a look and feel open dialog
				int i = localJFileChooser.showOpenDialog(null);
				if (i == 0) {
					File localFile = new File(localJFileChooser.getSelectedFile().getPath());
					try {
						// Read in all the details from the file.
						textPane.setText("");
						currentFile = localFile;
						BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile));
						String str = null;
						StringBuilder sb = new StringBuilder();
						while ((str = localBufferedReader.readLine()) != null) {
							sb.append(str + '\n');
						}
						textPane.setText(sb.toString());
						localBufferedReader.close();
						setTitle("Swing Text Editor [" + currentFile.getName() + "]");
						textPane.setCaretPosition(0);
					} catch (IOException localIOException) {
						localIOException.printStackTrace();
					}
				}
			}
		};

		menubar.getOpenMenuItem().addActionListener(fileOpener);
	}
		
	public void saveTheFile(File paramFile) {

		try {
			PrintWriter localPrintWriter = new PrintWriter(paramFile);
			localPrintWriter.print(textPane.getText());
			localPrintWriter.close();
			currentFile = paramFile;
			// SwingTextEditor.this.setTitle("Swing Text Editor [" +
			// paramFile.getName() + "]");
		} catch (IOException localIOException) {
			System.out.println(localIOException);
			localIOException.printStackTrace();
		}
	}
}

class ReplaceEditor extends JPanel {

	// TODO: Create constants

	private static final String REPLACE_LABEL = "Replace";
	private static final int TEXT_FIELD_WIDTH = 10;

	JButton replaceButton = new JButton(REPLACE_LABEL);
	JTextField findField = new JTextField(TEXT_FIELD_WIDTH);
	JTextField replacementField = new JTextField(TEXT_FIELD_WIDTH);

	public ReplaceEditor() {

		buildLayout();



		// For debugging, to ensure layout is correct

		// setBackground(Color.BLACK);

	}



	private void buildLayout() {

		add(replaceButton);
		add(findField);
		add(new JLabel("with"));
		add(replacementField);
	}

	public JButton getReplaceButton() {

		return replaceButton;
	}

	public JTextField getFindField() {

		return findField;
	}

	public JTextField getReplacementField() {

		return replacementField;
	}
}

class TextControls extends JPanel {

// TODO: Create constants

	String[] fonts = { "Sans Serif", "Serif", "Monospaced", "Dialog", "Dialog Input" };
	JComboBox<String> fontList = new JComboBox<String>(fonts);
	JCheckBox boldCheckBox = new JCheckBox("Bold");
	JCheckBox italicCheckBox = new JCheckBox("Italic");
	JRadioButton smallRadioButton = new JRadioButton("Small");
	JRadioButton mediumRadioButton = new JRadioButton("Medium");
	JRadioButton largeRadioButton = new JRadioButton("Large");
	ButtonGroup fontSizeGroup = new ButtonGroup();

	public TextControls() {

		setLayout(new BorderLayout());
		buildLayout();
		// For debugging, to ensure layout is correct

		// setBackground(Color.BLACK);
	}

	private void buildLayout() {

		JPanel top = new JPanel();
		// For debugging, to ensure layout is correct

		// top.setBackground(Color.RED);

		top.add(fontList);
		top.add(boldCheckBox);
		top.add(italicCheckBox);
		add(top, BorderLayout.NORTH);
		JPanel bottom = new JPanel();
		bottom.add(smallRadioButton);
		bottom.add(mediumRadioButton);
		bottom.add(largeRadioButton);
		add(bottom, BorderLayout.SOUTH);
		fontSizeGroup.add(smallRadioButton);
		fontSizeGroup.add(mediumRadioButton);
		fontSizeGroup.add(largeRadioButton);
	}

	public JComboBox<String> getFontList() {

		return fontList;
	}

	public void setFontList(JComboBox<String> fontList) {

		this.fontList = fontList;
	}

	public JCheckBox getBoldCheckBox() {

		return boldCheckBox;
	}

	public JCheckBox getItalicCheckBox() {

		return italicCheckBox;
	}

	public JRadioButton getSmallRadioButton() {

		return smallRadioButton;
	}

	public JRadioButton getMediumRadioButton() {

		return mediumRadioButton;
	}

	public JRadioButton getLargeRadioButton() {

		return largeRadioButton;
	}

	public ButtonGroup getFontSizeGroup() {

		return fontSizeGroup;
	}
}

class EditorMenu extends JMenuBar {

	// Static Strings for labels

	private static final String FILE_MENU_LABEL = "File";
	private static final String EDIT_MENU_LABEL = "Edit";
	private static final String OPEN_MENU_LABEL = "Open";
	private static final String SAVE_MENU_LABEL = "Save";
	private static final String SAVE_AS_MENU_LABEL = "Save As";
	private static final String EXIT_MENU_LABEL = "Exit";
	private static final String CUT_MENU_LABEL = "Cut";
	private static final String COPY_MENU_LABEL = "Copy";
	private static final String PASTE_MENU_LABEL = "Paste";

	// Top level menus

	private JMenu fileMenu;
	private JMenu editMenu;

	// File Menu Items

	private JMenuItem openMenuItem = new JMenuItem(OPEN_MENU_LABEL);
	private JMenuItem saveMenuItem = new JMenuItem(SAVE_MENU_LABEL);
	private JMenuItem saveAsMenuItem = new JMenuItem(SAVE_AS_MENU_LABEL);
	private JMenuItem exitMenuItem = new JMenuItem(EXIT_MENU_LABEL);

	// Edit Menu Items

	private JMenuItem cutMenuItem = new JMenuItem(CUT_MENU_LABEL);
	private JMenuItem copyMenuItem = new JMenuItem(COPY_MENU_LABEL);
	private JMenuItem pasteMenuItem = new JMenuItem(PASTE_MENU_LABEL);



	/*

	 * Default Constructor

	 */

	public EditorMenu() {

		fileMenu = buildFileMenu();
		editMenu = buildEditMenu();
		add(fileMenu);
		add(editMenu);
	}

	/*

	 * Build File Menu

	 */

	public JMenu buildFileMenu() {

		JMenu menu = new JMenu(FILE_MENU_LABEL);
		menu.add(openMenuItem);
		menu.add(saveMenuItem);
		menu.add(saveAsMenuItem);
		menu.addSeparator();
		menu.add(exitMenuItem);
		return menu;
	}

	/*

	 * Build Edit Menu

	 */

	public JMenu buildEditMenu() {

		JMenu menu = new JMenu(EDIT_MENU_LABEL);
		menu.add(cutMenuItem);
		menu.add(copyMenuItem);
		menu.add(pasteMenuItem);
		return menu;
	}

	public JMenu getFileMenu() {

		return fileMenu;
	}

	public JMenu getEditMenu() {

		return editMenu;
	}

	public JMenuItem getOpenMenuItem() {

		return openMenuItem;
	}

	public JMenuItem getSaveMenuItem() {

		return saveMenuItem;
	}

	public JMenuItem getSaveAsMenuItem() {

		return saveAsMenuItem;
	}

	public JMenuItem getCutMenuItem() {

		return cutMenuItem;
	}

	public JMenuItem getCopyMenuItem() {

		return copyMenuItem;
	}

	public JMenuItem getPasteMenuItem() {

		return pasteMenuItem;
	}
}