package com.turbovnc.vncviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.undo.UndoManager;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.srbd.ApiMap.ApiMapProvider;

public class CodeBox implements TreeSelectionListener{

	private CodeGenerator codegenerator;
	private JFrame frame;
	private RSyntaxTextArea textArea;
	private JEditorPane consoleText;
	protected JSplitPane splitPaneVerticalRight;
	protected JSplitPane splitPaneVerticalLeft;
	protected JSplitPane splitPaneMain;
	private JLabel cursorPosition;
	private JLabel tapOnText;
	private JCheckBox tapOnTextCheckBox;


	private ImagePanel imagePanel;
	private FileTree fileTree;


	private final ClassLoader cl = getClass().getClassLoader();
	final static int FRAME_WIDTH = 800;
	final static int FRAME_HEIGHT = 905;
	final int NUMBER_OF_MENUBUTTONS = 6;
	final int MENU_ICONS_WIDTH = 48;
	final int MENU_ICONS_HEIGHT = 24;
	final int CONSOLE_MENU_ICONS_WIDTH = 24;
	final int CONSOLE_MENU_ICONS_HEIGHT = 24;

	UndoManager undoManager = new UndoManager();

	static Toolkit tk = Toolkit.getDefaultToolkit();
	static String lastWord = "";
	static final String[] menuButtonTips = { "Run Code", "Launch App Command", "Area", "Capture Screen",	"TapOnScreen", "Mouse Position" };

	private JButton menuButtons[] = new JButton[4];

	private final ImageIcon[] toolbarIcons = { new ImageIcon(cl.getResource("icons/play_icon.png")),
			new ImageIcon(cl.getResource("icons/launch_icon.png")),
			new ImageIcon(cl.getResource("icons/area_icon.png")),
			new ImageIcon(cl.getResource("icons/rcptt_icon.png")) };

	private final Image[] toolbarImage = { toolbarIcons[0].getImage(), toolbarIcons[1].getImage(),
			toolbarIcons[2].getImage(),	toolbarIcons[3].getImage() };

	public CodeBox(CodeGenerator codegenerator, String type, int x, int y) {
		frame = new JFrame("Automation Script Generator");

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.codegenerator = codegenerator;
		createAndShowGUI(x,y);
	}

	public CodeGenerator getCodegenerator() {
		return codegenerator;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void close() {

		frame.dispose();
	}

	public void createAndShowGUI(int x, int y) {

		frame.setLocation(new Point(x, y));
		frame.setLayout(new BorderLayout());

		JPanel codeContent = new JPanel(new GridLayout(1, 1, 1, 2));
		RTextScrollPane scrollPanel ;

		textArea = new RSyntaxTextArea();

		textArea.setFont(textArea.getFont().deriveFont(14.0f));
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		CustomCompletionProvider provider = createCompletionProvider();
	    AutoCompletion ac = new AutoCompletion(provider);
	    ac.install(textArea);

		scrollPanel = new RTextScrollPane(textArea);
		codeContent.add(scrollPanel);

		splitPaneVerticalRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneVerticalRight.setLeftComponent(codeContent);
		splitPaneVerticalRight.setRightComponent(createConsole(frame));
		splitPaneVerticalRight.setDividerLocation(0.5);

		imagePanel = new ImagePanel(null);
		File currentFile = new File(System.getProperty("user.dir") + File.separator + "res" + File.separator + "ref_images");
		fileTree = new FileTree(currentFile, this);
		splitPaneVerticalLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneVerticalLeft.setLeftComponent(fileTree);
		splitPaneVerticalLeft.setRightComponent(imagePanel);

		splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneMain.setLeftComponent(splitPaneVerticalLeft);
		splitPaneMain.setRightComponent(splitPaneVerticalRight);

		frame.add(splitPaneMain);

		createMenu(frame);
		frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setResizable(true);
		frame.setVisible(true);
		restoreDefaults();

	}

	private void restoreDefaults() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				splitPaneVerticalRight.setDividerLocation(splitPaneVerticalRight.getSize().height * 2 / 3);
				splitPaneVerticalLeft.setDividerLocation(splitPaneVerticalLeft.getSize().height * 2 / 3);
			}
		});
	}

	private JPanel createConsole(JFrame frame) {
		JPanel console = new JPanel();
		// console.setSize(800, 250);
		console.setLayout(new BorderLayout());
		JMenuBar menuBar;
		menuBar = new JMenuBar();
		menuBar.add(new JLabel("Console"));
		menuBar.add(Box.createRigidArea(new Dimension(24, 0)));

		BufferedImage bi = new BufferedImage(CONSOLE_MENU_ICONS_WIDTH, CONSOLE_MENU_ICONS_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.drawImage(new ImageIcon(cl.getResource("icons/close_icon.png")).getImage(), 0, 0, CONSOLE_MENU_ICONS_WIDTH,
				CONSOLE_MENU_ICONS_HEIGHT, null);

		ImageIcon icon = new ImageIcon(tk.createImage(bi.getSource()));
		JButton closeButton;

		closeButton = new JButton(icon);
		closeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				consoleText.setText("");
			}
		});
		menuBar.add(closeButton);
		console.add(menuBar, BorderLayout.NORTH);

		JScrollPane scrollPanel = new JScrollPane();
		// scrollPanel.setSize(480, 250);
		consoleText = new JEditorPane();
		consoleText.setFont(consoleText.getFont().deriveFont(14.0f));
		consoleText.setEditable(false);
		consoleText.setText("Console Text......");

		scrollPanel.getViewport().add(consoleText);
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		console.add(scrollPanel, BorderLayout.CENTER);

		return console;

	}

	private void createMenu(JFrame frame) {
		JToolBar menuBar;

		// Create the menu bar.
		menuBar = new JToolBar();

		menuBar.add(new JSeparator(JSeparator.VERTICAL) {
			private static final long serialVersionUID = 1L;

			public Dimension getMaximumSize() {
				return new Dimension(getPreferredSize().width, Integer.MAX_VALUE);
			}
		});

		for (int i = 0; i < NUMBER_OF_MENUBUTTONS; i++) {

			if (i < NUMBER_OF_MENUBUTTONS - 2) {
				BufferedImage bi = new BufferedImage(MENU_ICONS_WIDTH, MENU_ICONS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = bi.createGraphics();
				g.drawImage(toolbarImage[i], 0, 0, MENU_ICONS_WIDTH, MENU_ICONS_HEIGHT, null);

				ImageIcon icon = new ImageIcon(tk.createImage(bi.getSource()));
				JButton button;

				button = new JButton(icon);
				button.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

				button.setToolTipText(menuButtonTips[i]);
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setFocusable(false);
				button.addActionListener(codegenerator);
				button.addMouseListener(new ButtonListener(button));
				button.setContentAreaFilled(false);
				menuButtons[i] = button;
				menuBar.add(button);
				menuBar.add(new JSeparator(JSeparator.VERTICAL) {
					private static final long serialVersionUID = 1190281313912617425L;

					public Dimension getMaximumSize() {
						return new Dimension(getPreferredSize().width, Integer.MAX_VALUE);
					}
				});

			} else {
				if (i == NUMBER_OF_MENUBUTTONS - 2) {
					tapOnText = new JLabel("Auto Generate (press/longPress/swipe): ");
					tapOnText.setSize(22, 66);
					Font font = tapOnText.getFont();
					Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
					tapOnText.setFont(boldFont);
					menuBar.add(tapOnText);

					tapOnTextCheckBox = new JCheckBox();
					tapOnTextCheckBox.setSelected(false);
					menuBar.add(tapOnTextCheckBox);
					menuBar.add(new JSeparator(JSeparator.VERTICAL) {
						private static final long serialVersionUID = 1190281313912617425L;

						public Dimension getMaximumSize() {
							return new Dimension(getPreferredSize().width, Integer.MAX_VALUE);
						}
					});

				} else {
					cursorPosition = new JLabel(" X: Y: ");
					cursorPosition.setSize(22, 66);
					Font font = cursorPosition.getFont();
					Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
					cursorPosition.setFont(boldFont);
					cursorPosition.setText(" X, Y: [0, 0] [0.000, 0.000]");
					menuBar.add(cursorPosition);
				}
			}
		}

		menuBar.add(new JSeparator(JSeparator.VERTICAL) {

			private static final long serialVersionUID = 4659353149448597610L;

			public Dimension getMaximumSize() {
				return new Dimension(getPreferredSize().width, Integer.MAX_VALUE);
			}
		});

		menuBar.setBackground(Color.WHITE);
		frame.add(menuBar, BorderLayout.PAGE_START);
	}

	 private CustomCompletionProvider createCompletionProvider() {

	      // A DefaultCompletionProvider is the simplest concrete implementation
	      // of CompletionProvider. This provider has no understanding of
	      // language semantics. It simply checks the text entered up to the
	      // caret position for a match against known completions. This is all
	      // that is needed in the majority of cases.

		 CustomCompletionProvider provider = new CustomCompletionProvider();

		 ApiMapProvider apiMapProvider = new ApiMapProvider();
		 HashMap<String, java.util.List<String>> apiMap = apiMapProvider.getApiMap();

		 Iterator<String> objects = apiMap.keySet().iterator();
		 while(objects.hasNext()) {
			 String object = objects.next();
			 java.util.List<String> apis = apiMap.get(object);
			 for (String api : apis) {
				 provider.addApi(object, api);
			}
		 }

	     return provider;
	}

	public void addJavaKeyWords(CustomCompletionProvider provider) {
		provider.addKeyWord("int");
		provider.addKeyWord("while");
		provider.addKeyWord("for");
		provider.addKeyWord("case");
		provider.addKeyWord("switch");
		provider.addKeyWord("void");
		provider.addKeyWord("boolean");
		provider.addKeyWord("double");
		provider.addKeyWord("try");
		provider.addKeyWord("catch");
		provider.addKeyWord("while");

	}

	public JButton getPlayButton() {
		return menuButtons[0];
	}

	public void setCursorposition(int x, int y, double scaledX, double scaledY) {

		cursorPosition.setText(" X, Y: [" + x +", "+y+"] ["+ String.format( "%.3f", scaledX ) +", "+String.format( "%.3f", scaledY )+"]");
	}

	public RSyntaxTextArea getCodeArea() {

		return textArea;
	}

	public JEditorPane getConsoleArea() {

		return consoleText;
	}

	public boolean isTapOnTextSelected() {
		return tapOnTextCheckBox.isSelected();
	}

	public void updateFileTree(String parent, String newNode) {

		DefaultTreeModel model = (DefaultTreeModel)fileTree.tree.getModel();
		ArrayList<DefaultMutableTreeNode> allNodes = fileTree.getAllNodes();
		for (DefaultMutableTreeNode node : allNodes) {
			FileTreeElement nodeElement = (FileTreeElement) node.getUserObject();
			if(nodeElement.getfileName().equals(parent)) {
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(new FileTreeElement(newNode));

				int index = getChildIndex(node, child);
				if(index == -1){
					node.add(child);
					//node.remove(index);
				}

				model.reload(node);
				break;
			}

		}
	}

    private int getChildIndex(DefaultMutableTreeNode node, DefaultMutableTreeNode child) {
    	int count = node.getChildCount();
		for(int index=0;index<count;index++){
			if(node.getChildAt(index).toString().equals(child.toString())){
				return index;
			}
		}
    	return -1;
	}

	private static String getFileExtension(File file) {
        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        } else {
        	return "";
        }
    }


	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

		FileTreeElement element = (FileTreeElement) node.getUserObject();
		String extension = getFileExtension(element.getFile());

		if(extension.equals("png") || extension.equals("jpg")) {
			splitPaneVerticalLeft.setRightComponent(new ImagePanel(element.getFile().getAbsolutePath()));

		}
		//setSplitPosition();
	}
}
