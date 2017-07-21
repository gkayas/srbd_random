package com.turbovnc.vncviewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Display a file system in a JTree view
 * 
 * @version $Id: FileTree.java,v 1.9 2004/02/23 03:39:22 ian Exp $
 * @author Ian Darwin
 */
public class FileTree extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2461603061301314565L;

	/** Construct a FileTree 
	 * @throws IOException */
	
	private Stack<DefaultMutableTreeNode> stack;
	
	public JTree tree;
	private DefaultMutableTreeNode root;
	private ArrayList<DefaultMutableTreeNode> allNodes = new ArrayList<DefaultMutableTreeNode>();
	public FileTree(File dir, CodeBox codeBox) {
		
		setLayout(new BorderLayout());

		// Make a tree list with all the nodes, and make it a JTree
	    root = null;
		try {
			root = new DefaultMutableTreeNode(new FileTreeElement(dir.getCanonicalPath()));
			allNodes.add(root);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		tree = new JTree(root);
		
		stack = new Stack<DefaultMutableTreeNode>();
		buildTree(root);
		tree.expandRow(0);
		tree.putClientProperty("JTree.lineStyle", "None");
		// Add a listener
		tree.addTreeSelectionListener(codeBox);

		// Lastly, put the JTree into a JScrollPane.
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.getViewport().add(tree);
		add(BorderLayout.CENTER, scrollpane);
		//tree.updateUI();
	}

	void buildTree(DefaultMutableTreeNode root) {
		stack.push(root);
		addNodeAll();
	}
	
	void addNodeAll() {
		
		while(!stack.isEmpty()) {
			DefaultMutableTreeNode parent = stack.pop();
			FileTreeElement ele = (FileTreeElement) parent.getUserObject();
			String absPath = ele.getfileName()+File.separator;
			if(ele.getFile().isDirectory()) {
				String [] children = ele.getFile().list();
				for (String string : children) {
					
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new FileTreeElement(absPath+string));
					parent.add(child);
					stack.push(child);
					allNodes.add(child);
				}
			} 
		}
	}

	public Dimension getMinimumSize() {
		return new Dimension(200, 400);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 400);
	}

	public void updateTree() {
		tree.updateUI();
	}

	public ArrayList<DefaultMutableTreeNode> getAllNodes() {
		return allNodes;
	}
	
}