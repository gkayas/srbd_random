package com.turbovnc.vncviewer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.turbovnc.rfb.Keysyms;

public class UIAutomatorControlsFrame extends JFrame implements ActionListener {

	private CConn cc;


	private JButton livemodeButton, tapImageButton;


	private JButton homeButton;


	private JButton verifyTextButton;


	private JButton tapTextButton;


	private JButton typeTextButton;


	private JButton swipeButton;


	private JButton settingButton;

	public UIAutomatorControlsFrame(Viewport ref) {
		super();
		cc = ref.cc;
		Viewport viewPort = cc.viewport;
		//JFrame.setDefaultLookAndFeelDecorated(true);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setResizable(false);
		removeMinMaxClose(this);
		JPanel pane = new JPanel(new GridBagLayout());
		//	    JButton btn = new JButton("Exit");
		//	    p.add(btn,new GridBagConstraints());
		//////////////////////////////////////////////////

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		// row 1
		livemodeButton = new JButton("Change Mode");
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(livemodeButton, c);
		livemodeButton.addActionListener(this);


		// row 1
		homeButton = new JButton("Verify Image");
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(homeButton, c);
		homeButton.addActionListener(this);

		// row 2
		tapImageButton = new JButton("Tap Image");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(tapImageButton, c);
		tapImageButton.addActionListener(this);

		// row 3
		verifyTextButton = new JButton("Verify Text");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(verifyTextButton, c);
		verifyTextButton.addActionListener(this);

		// row 4
		tapTextButton = new JButton("Tap Text");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		pane.add(tapTextButton, c);
		tapTextButton.addActionListener(this);

		// row 5
		typeTextButton = new JButton("Type Text");
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 5;
		pane.add(typeTextButton, c);
		typeTextButton.addActionListener(this);

		// row 6
		swipeButton = new JButton("Swipe");
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 6;
		pane.add(swipeButton, c);
		swipeButton.addActionListener(this);

		//row 7
		settingButton = new JButton("Settings");
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 7;
		pane.add(settingButton, c);
		settingButton.addActionListener(this);


		//row 8
		JButton exitButton = new JButton("Exit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;       //aligned with button 2
		c.gridy = 8;       //eight row
		pane.add(exitButton, c);
		exitButton.addActionListener(this);
		//////////////////////////////////////////////////
		getContentPane().add(pane);
		setSize(150,400);

		//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//	    pack();
		setLocationRelativeTo(null);
	}

	public void removeMinMaxClose(Component comp)
	{
		if(comp instanceof AbstractButton)
		{
			comp.getParent().remove(comp);
		}
		if (comp instanceof Container)
		{
			Component[] comps = ((Container)comp).getComponents();
			for(int x = 0, y = comps.length; x < y; x++)
			{
				removeMinMaxClose(comps[x]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		CodeGenerator codeGenerator = cc.viewport.getCodeGenerator();
		Object s = e.getSource();
		JButton source = (JButton)e.getSource();
		System.out.println(source.getText());
		String[] buttonTips = Toolbar.buttonTips;
		if(source.getText().equals("Exit")) {
			this.dispose();

		}if (((AbstractButton) s).getText() == Toolbar.buttonTips[0] || ((AbstractButton) s).getText() == "Live Mode") {
			cc.viewMode = !cc.viewMode;
			if (cc.viewMode) {
				livemodeButton.setText("Live Mode");
				int x = cc.viewport.getLocation().x;
				int y = cc.viewport.getLocation().y;
				codeGenerator.createCodeBox(x, y);
			} else {

				livemodeButton.setText("Change Mode");
				codeGenerator.closeCodeBox();
			}
//			toggleOperationButtons();

		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[1]) {
//			buttons[0].setEnabled(false);
//			toggleOperationButtons();
			CaptureImage captureImage = new CaptureImage(codeGenerator, cc.viewport.tb);
			captureImage.setVerify(true);
			captureImage.start();

		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[2]) {
//			buttons[0].setEnabled(false);
//			toggleOperationButtons();
			CaptureImage captureImage = new CaptureImage(codeGenerator, cc.viewport.tb);
			captureImage.setVerify(false);
			captureImage.start();
		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[3]) {
			System.out.println("calling verifyText");
			codeGenerator.verifyText();
		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[4]) {
			System.out.println("calling tapText");
			codeGenerator.tapText();
		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[5]) {
			System.out.println("calling typeText");
			codeGenerator.typeText();
		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[6]) {
			System.out.println("calling swipe");
			codeGenerator.swipe();
		} else if (((AbstractButton) s).getText() == Toolbar.buttonTips[7]) {
			System.out.println("calling createSetupPopup");
			createSetupPopup();
		}

	}

	private void createSetupPopup() {
		ReadConfigUtil.readDeviceConfig();

		final JDialog frame = new JDialog(cc.viewport);
		frame.setTitle("Device Config Window");

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel setupPanel = new JPanel();
	    Border border = setupPanel.getBorder();
	    Border margin = new EmptyBorder(10, 10, 10, 10);
	    setupPanel.setBorder(new CompoundBorder(border, margin));
	    final JTextArea rcpttTextArea = new JTextArea();
	    rcpttTextArea.setText(ReadConfigUtil.getLiveMode());

	    GridBagLayout panelGridBagLayout = new GridBagLayout();
	    panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
	    panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
	    panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	    panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	    setupPanel.setLayout(panelGridBagLayout);

	    final JTextField liveModeField = new JTextField();
	    final JTextField platformVersionField = new JTextField();
	    final JTextField profileNameField = new JTextField();
	    final JTextField profileTypeField = new JTextField();
	    final JTextField screenWidthField = new JTextField();
	    final JTextField screenHeightField = new JTextField();
	    final JTextField emulatorTypeField = new JTextField();
	    final JTextField assertModeField = new JTextField();
	    final JTextField refreashTimeField = new JTextField();
	    final JTextField searchTimeField = new JTextField();
	    final JTextField showLogMsgField = new JTextField();
	    final JTextField captureRefImagePathField = new JTextField();

	    liveModeField.setEnabled(false);
	    platformVersionField.setEnabled(false);
	    profileNameField.setEnabled(false);
	    profileTypeField.setEnabled(false);
	    screenWidthField.setEnabled(false);
	    screenHeightField.setEnabled(false);
	    emulatorTypeField.setEnabled(false);
	    refreashTimeField.setEnabled(false);
	    searchTimeField.setEnabled(false);

	    addLabelAndTextField(Constants.LIVE_MODE, ReadConfigUtil.getLiveMode(), 0, setupPanel, liveModeField);
	    addLabelAndTextField(Constants.PLATFORM_VERSION, ReadConfigUtil.getPlatformVersion(), 1, setupPanel, platformVersionField);
	    addLabelAndTextField(Constants.PROFILE_NAME, ReadConfigUtil.getProfileName(), 2, setupPanel, profileNameField);
	    addLabelAndTextField(Constants.PROFILE_TYPE, ReadConfigUtil.getProfileType(), 3, setupPanel, profileTypeField);
	    addLabelAndTextField(Constants.SCREEN_WIDTH, ReadConfigUtil.getScreenWidth(), 4, setupPanel, screenWidthField);
	    addLabelAndTextField(Constants.SCREEN_HEIGHT, ReadConfigUtil.getScreenHeight(), 5, setupPanel, screenHeightField);
	    addLabelAndTextField(Constants.EMULATOR_TYPE, ReadConfigUtil.getEmulatorType(), 6, setupPanel, emulatorTypeField);
	    addLabelAndTextField(Constants.ASSERT_MODE, ReadConfigUtil.getAssertMode(), 7, setupPanel, assertModeField);
	    addLabelAndTextField(Constants.REFRESH_TIME, ReadConfigUtil.getRefreshTime(), 8, setupPanel, refreashTimeField);
	    addLabelAndTextField(Constants.SEARCH_TIME, ReadConfigUtil.getSearchTime(), 9, setupPanel, searchTimeField);
	    addLabelAndTextField(Constants.SHOW_LOG_MESSAGE, ReadConfigUtil.getShowSysMes(), 10, setupPanel, showLogMsgField);
	    addLabelAndTextField(Constants.CAPTURE_REF_IMAGE_PATH, ReadConfigUtil.getCaptureRefImagePath(), 11, setupPanel, captureRefImagePathField);


	    JButton saveButton = new JButton("Save");
	    saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReadConfigUtil.setValueInDevInfo(Constants.LIVE_MODE, liveModeField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.PLATFORM_VERSION, platformVersionField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.PROFILE_NAME, profileNameField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.PROFILE_TYPE, profileTypeField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.SCREEN_WIDTH, screenWidthField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.SCREEN_HEIGHT, screenHeightField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.EMULATOR_TYPE, emulatorTypeField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.ASSERT_MODE, assertModeField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.REFRESH_TIME, refreashTimeField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.SEARCH_TIME, searchTimeField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.SHOW_LOG_MESSAGE, showLogMsgField.getText().trim());
				ReadConfigUtil.setValueInDevInfo(Constants.CAPTURE_REF_IMAGE_PATH, captureRefImagePathField.getText().trim());
				ReadConfigUtil.writeIntoDeviceConfig();

				JOptionPane.showMessageDialog(frame, "Device configuation settings saved successfully.");
			}
		});

	    GridBagConstraints gridBagConstraintForSave = new GridBagConstraints();
	    gridBagConstraintForSave.anchor = GridBagConstraints.SOUTHEAST;
	    gridBagConstraintForSave.insets = new Insets(0, 0, 0, 5);
	    gridBagConstraintForSave.gridx = 1;
	    gridBagConstraintForSave.gridy = 12;
	    gridBagConstraintForSave.gridwidth = 1;

	    setupPanel.add(saveButton, gridBagConstraintForSave);

	    JButton cancelButton = new JButton("Close");
	    cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	    GridBagConstraints gridBagConstraintForCancel = new GridBagConstraints();
	    gridBagConstraintForCancel.anchor = GridBagConstraints.SOUTHEAST;
	    gridBagConstraintForCancel.insets = new Insets(0, 0, 0, 0);
	    gridBagConstraintForCancel.gridx = 2;
	    gridBagConstraintForCancel.gridy = 12;
	    gridBagConstraintForCancel.gridwidth = 1;
	    setupPanel.add(cancelButton, gridBagConstraintForCancel);

		frame.add(setupPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(CodeBox.FRAME_WIDTH-450, CodeBox.FRAME_HEIGHT - 595);
		frame.setPreferredSize(new Dimension(CodeBox.FRAME_WIDTH-450, CodeBox.FRAME_HEIGHT - 595));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	private void addLabelAndTextField(String labelText, String valueText, int yPos, Container setupPanel, JTextField textField) {

	    JLabel faxLabel = new JLabel(labelText);
	    GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
	    gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
	    gridBagConstraintForLabel.gridx = 0;
	    gridBagConstraintForLabel.gridy = yPos;
	    setupPanel.add(faxLabel, gridBagConstraintForLabel);

	    textField.setText(valueText);
	    GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
	    gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
	    gridBagConstraintForTextField.gridwidth = 2;
	    gridBagConstraintForTextField.gridx = 1;
	    gridBagConstraintForTextField.gridy = yPos;
	    setupPanel.add(textField, gridBagConstraintForTextField);
//	    textField.setColumns(20);
	}
}
