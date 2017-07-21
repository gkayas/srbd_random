package com.turbovnc.vncviewer;


import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;

import com.turbovnc.rfb.Keysyms;


public class CustomFrame extends JFrame implements ActionListener {

	private CConn cc;
	protected CodeGenerator codeGenerator;

	public CustomFrame(Viewport ref, CodeGenerator cG) {
		super();
		codeGenerator = cG;
		cc = ref.cc;
		//JFrame.setDefaultLookAndFeelDecorated(true);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setResizable(false);
		removeMinMaxClose(this);
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel pane = new JPanel(new GridBagLayout());
		//	    JButton btn = new JButton("Exit");
		//	    p.add(btn,new GridBagConstraints());
		//////////////////////////////////////////////////
		JButton homeButton, backButton, volUpButton, volDownButton, exitButton, muteButton;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;



		// row 1
		homeButton = new JButton("Home");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(homeButton, c);
		homeButton.addActionListener(this);

		backButton = new JButton("Back");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(backButton, c);
		backButton.addActionListener(this);

		JButton powerButton = new JButton("Power");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(powerButton, c);
		powerButton.addActionListener(this);

		// row 2
		volUpButton = new JButton("Vol+");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(volUpButton, c);
		volUpButton.addActionListener(this);

		volDownButton = new JButton("Vol-");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(volDownButton, c);
		volDownButton.addActionListener(this);

		muteButton = new JButton("Mute");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(muteButton, c);
		muteButton.addActionListener(this);

		// row 3
		JButton upButton = new JButton("Up");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(upButton, c);
		upButton.addActionListener(this);

		JButton downButton = new JButton("Down");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(downButton, c);
		downButton.addActionListener(this);

		JButton rightButton = new JButton("Right");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		pane.add(rightButton, c);
		rightButton.addActionListener(this);

		// row 4
		JButton leftButton = new JButton("Left");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(leftButton, c);
		leftButton.addActionListener(this);

		JButton numericButton = new JButton("Ch+");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		pane.add(numericButton, c);
		numericButton.addActionListener(this);

		JButton dotButton = new JButton("Ch-");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 3;
		pane.add(dotButton, c);
		dotButton.addActionListener(this);


		// row 5
		JButton okButton = new JButton("Ok");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		pane.add(okButton, c);
		okButton.addActionListener(this);

		JButton forwardButton = new JButton(">>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		pane.add(forwardButton, c);
		downButton.addActionListener(this);

		JButton zeroButton = new JButton("0");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 4;
		pane.add(zeroButton, c);
		zeroButton.addActionListener(this);

		// row 6
		JButton oneButton = new JButton("1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		pane.add(oneButton, c);
		oneButton.addActionListener(this);

		JButton twoButton = new JButton("2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		pane.add(twoButton, c);
		twoButton.addActionListener(this);

		JButton threeButton = new JButton("3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 5;
		pane.add(threeButton, c);
		threeButton.addActionListener(this);

		// row 7
		JButton fourButton = new JButton("4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		pane.add(fourButton, c);
		fourButton.addActionListener(this);

		JButton fiveButton = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 6;
		pane.add(fiveButton, c);
		fiveButton.addActionListener(this);

		JButton sixButton = new JButton("6");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 6;
		pane.add(sixButton, c);
		sixButton.addActionListener(this);

		// row 8
		JButton sevenButton = new JButton("7");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		pane.add(sevenButton, c);
		sevenButton.addActionListener(this);

		JButton eightButton = new JButton("8");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 7;
		pane.add(eightButton, c);
		eightButton.addActionListener(this);

		JButton nineButton = new JButton("9");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 7;
		pane.add(nineButton, c);
		nineButton.addActionListener(this);
		
		// row 9
		JButton numberButton = new JButton("123");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 8;
		pane.add(numberButton, c);
		numberButton.addActionListener(this);

		JButton dotDotButton = new JButton("...");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 8;
		pane.add(dotDotButton, c);
		dotDotButton.addActionListener(this);		
		
		JButton playButton = new JButton("Play");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 8;
		pane.add(playButton, c);
		playButton.addActionListener(this);

		//exit row
		exitButton = new JButton("Exit.");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 10;       //nineth row
		pane.add(exitButton, c);
		exitButton.addActionListener(this);
		//////////////////////////////////////////////////
		tabbedPane.add("TV Remote", pane);
		tabbedPane.add("Empty Tab", new CustomComponent());
		getContentPane().add(tabbedPane);
		setSize(200,400);

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
	/*
	static int count = 120;
	static int testKey()
	{
		int[] anArray;
		anArray = new int[254];
		anArray[0]	=	Keysyms.XF86Back;
		anArray[1]	=	Keysyms.XF86Red;
		anArray[2]	=	Keysyms.XF86Green;
		anArray[3]	=	Keysyms.XF86Yellow;
		anArray[4]	=	Keysyms.XF86Blue;
		anArray[5]	=	Keysyms.XF86Home;
		anArray[6]	=	Keysyms.XF86Display;
		anArray[7]	=	Keysyms.XF86ChannelList;
		anArray[8]	=	Keysyms.XF86AudioMute;
		anArray[9]	=	Keysyms.XF86AudioLowerVolume;
		anArray[10]	=	Keysyms.XF86AudioRaiseVolume;
		anArray[11]	=	Keysyms.XF86LSGT;
		anArray[12]	=	Keysyms.XF86LowerChannel;
		anArray[13]	=	Keysyms.XF86RaiseChannel;
		//anArray[14]	=	Keysyms.XF86LFDPowerOff;
		anArray[14]	=	Keysyms.XF86RaiseChannel;
		//anArray[15]	=	Keysyms.XF86PowerOff;
		anArray[15]	=	Keysyms.XF86RaiseChannel;
		anArray[16]	=	Keysyms.XF86LFDMagicInfo;
		anArray[17]	=	Keysyms.XF86SysMenu;
		anArray[18]	=	Keysyms.XF86SimpleMenu;
		anArray[19]	=	Keysyms.XF86MBRRepeat;
		anArray[20]	=	Keysyms.XF86ChannelGuide;
		anArray[21]	=	Keysyms.XF86PictureSize;
		anArray[22]	=	Keysyms.XF86PictureMode;
		anArray[23]	=	Keysyms.XF86Open;
		anArray[24]	=	Keysyms.XF86Hdmi;
		anArray[25]	=	Keysyms.XF86UsbHub;
		anArray[26]	=	Keysyms.XF86Cut;
		anArray[27]	=	Keysyms.XF86EManual;
		anArray[28]	=	Keysyms.XF86LFDScreenLock;
		anArray[29]	=	Keysyms.XF86More;
		anArray[30]	=	Keysyms.XF86FactoryMode;
		anArray[31]	=	Keysyms.XF86Sleep;
		anArray[32]	=	Keysyms.XF86WakeUp;
		anArray[33]	=	Keysyms.XF86Explorer;
		anArray[34]	=	Keysyms.XF86TV;
		anArray[35]	=	Keysyms.XF86DTV;
		anArray[36]	=	Keysyms.XF86STBPower;
		anArray[37]	=	Keysyms.XF86ChannelAddDel;
		anArray[38]	=	Keysyms.XF86PanelDown;
		anArray[39]	=	Keysyms.XF86WWW;
		anArray[40]	=	Keysyms.XF86BTColorMecha;
		anArray[41]	=	Keysyms.XF86StillPicture;
		anArray[42]	=	Keysyms.XF86BTPairing;
		anArray[43]	=	Keysyms.XF86BTHotkey;
		anArray[44]	=	Keysyms.XF86CinemaMode;
		anArray[45]	=	Keysyms.XF86BTContentsBar;
		anArray[46]	=	Keysyms.XF86Game;
		anArray[47]	=	Keysyms.XF86LFDUnset;
		anArray[48]	=	Keysyms.XF86PIPChannelUp;
		anArray[49]	=	Keysyms.XF86PIPChannelDown;
		anArray[50]	=	Keysyms.XF86Antena;
		anArray[51]	=	Keysyms.XF86ChannelAutoTune;
		anArray[52]	=	Keysyms.XF86PanelEnter;
		anArray[53]	=	Keysyms.XF86MBRLink;
		anArray[54]	=	Keysyms.XF86PanelUp;
		anArray[55]	=	Keysyms.XF86AudioStop;
		anArray[56]	=	Keysyms.XF86AudioRecord;
		anArray[57]	=	Keysyms.XF86AudioRewind;
		anArray[58]	=	Keysyms.XF86Game3D;
		anArray[59]	=	Keysyms.XF86WheelLeftKey;
		anArray[60]	=	Keysyms.XF86WheelRightKey;
		anArray[61]	=	Keysyms.XF86HomePage;
		anArray[62]	=	Keysyms.XF86PanelExit;
		anArray[63]	=	Keysyms.XF86Exit;
		anArray[64]	=	Keysyms.XF86MBRTV;
		anArray[65]	=	Keysyms.XF86MBRSTBGuide;
		anArray[66]	=	Keysyms.XF86MBRBDPopup;
		anArray[67]	=	Keysyms.XF86MBRBDDVDPower;
		anArray[68]	=	Keysyms.XF86MBRSetupFailure;
		anArray[69]	=	Keysyms.XF86MBRSetup;
		anArray[70]	=	Keysyms.XF86MBRWatchTV;
		anArray[71]	=	Keysyms.XF86PreviousChannel;
		anArray[72]	=	Keysyms.XF86FavoriteChannel;
		anArray[73]	=	Keysyms.XF86Recommend;
		anArray[74]	=	Keysyms.XF86NumberPad;
		anArray[75]	=	Keysyms.XF86AspectRatio169;
		anArray[76]	=	Keysyms.XF86MTS;
		anArray[77]	=	Keysyms.XF86Info;
		anArray[78]	=	Keysyms.XF86SoundMode;
		anArray[79]	=	Keysyms.XF863XSpeed;
		anArray[80]	=	Keysyms.XF863D;
		anArray[81]	=	Keysyms.XF86TTXMIX;
		anArray[82]	=	Keysyms.XF86SRSSXT;
		anArray[83]	=	Keysyms.XF86WIFIPairing;
		anArray[84]	=	Keysyms.XF86AudioPlay;
		anArray[85]	=	Keysyms.XF86AudioPause;
		anArray[86]	=	Keysyms.XF86DualView;
		anArray[87]	=	Keysyms.XF86BTApps;
		anArray[88]	=	Keysyms.XF86FamilyMode;
		anArray[89]	=	Keysyms.XF86EnergySaving;
		anArray[90]	=	Keysyms.XF86MBRClear;
		anArray[91]	=	Keysyms.XF86Subtitle;
		anArray[92]	=	Keysyms.XF86AudioNext;
		anArray[93]	=	Keysyms.XF86TVSNS;
		anArray[94]	=	Keysyms.XF86DVR;
		anArray[95]	=	Keysyms.XF86Apps;
		anArray[96]	=	Keysyms.XF86Camera;
		anArray[97]	=	Keysyms.XF86Caption;
		anArray[98]	=	Keysyms.XF86ZoomIn;
		anArray[99]	=	Keysyms.XF86PanelPlus;
		anArray[100]	=	Keysyms.XF86BTVoice;
		anArray[101]	=	Keysyms.XF86Search;
		anArray[102]	=	Keysyms.XF86Go;
		anArray[103]	=	Keysyms.XF86PanelMinus;
		anArray[104]	=	Keysyms.XF86SoccerMode;
		anArray[105]	=	Keysyms.XF86Amazon;
		anArray[106]	=	Keysyms.XF86AudioDescription;
		anArray[107]	=	Keysyms.XF86PreviousChapter;
		anArray[108]	=	Keysyms.XF86NextChapter;
		anArray[109]	=	Keysyms.XF86Netflix;
		anArray[110]	=	Keysyms.XF86PIP;
		anArray[111]	=	Keysyms.XF86MBRWatchMovie;
		anArray[112]	=	Keysyms.XF86MBRMenu;
		anArray[113]	=	Keysyms.XF86MBRConfirm;
		anArray[114]	=	Keysyms.XF86FamilyHub;
		anArray[115]	=	Keysyms.XF86HDMICEC;
		anArray[116]	=	Keysyms.XF86BTDevice;
		anArray[117]	=	Keysyms.XF86Save;
		anArray[118]	=	Keysyms.XF86Documents;
		anArray[119]	=	Keysyms.XF86SoftWakeup;
		anArray[120]	=	Keysyms.XF86LFDSet;
		anArray[121]	=	Keysyms.XF86Wakeup;
		anArray[122]	=	Keysyms.XF86LFDBlank;
		anArray[123]	=	Keysyms.XF86LeftPage;
		anArray[124]	=	Keysyms.XF86RightPage;
		anArray[125]	=	Keysyms.XF86PlayBack;
		anArray[126]	=	Keysyms.XF86ExtraApp;
		//anArray[127]	=	Keysyms.XF86DiscretPowerOff;
		anArray[127]	=	Keysyms.XF86ExtraApp;
		anArray[128]	=	Keysyms.XF86DiscretPowerOn;
		anArray[129]	=	Keysyms.XF86DiscretSVideo1;
		anArray[130]	=	Keysyms.XF86DiscretSVideo2;
		anArray[131]	=	Keysyms.XF86DiscretSVideo3;
		anArray[132]	=	Keysyms.XF86DiscretComponent1;
		anArray[133]	=	Keysyms.XF86DiscretComponent2;
		anArray[134]	=	Keysyms.XF86DiscretComponent3;
		anArray[135]	=	Keysyms.XF86DiscretHDMI1;
		anArray[136]	=	Keysyms.XF86DiscretHDMI2;
		anArray[137]	=	Keysyms.XF86DiscretHDMI3;
		anArray[138]	=	Keysyms.XF86DiscretPC;
		anArray[139]	=	Keysyms.XF86DiscretDVI1;
		anArray[140]	=	Keysyms.XF86DiscretDVI2;
		anArray[141]	=	Keysyms.XF86DiscretZoom1;
		anArray[142]	=	Keysyms.XF86DiscretZoom2;
		anArray[143]	=	Keysyms.XF86DiscretPanorama;
		//anArray[144]	=	Keysyms.XF86Discret;
		anArray[144]	=	Keysyms.XF86DiscretPanorama;
		//anArray[145]	=	Keysyms.XF86Discret;
		anArray[145]	=	Keysyms.XF86DiscretPanorama;
		anArray[146]	=	Keysyms.XF86LFDPowerOn;
		//anArray[147]	=	Keysyms.XF86DiscretLFD;
		anArray[147]	=	Keysyms.XF86LFDPowerOn;
		anArray[148]	=	Keysyms.XF86DiscretSourceTV;
		anArray[149]	=	Keysyms.XF86Color;
		//anArray[150]	=	Keysyms.XF86DiscretLFD;
		//anArray[151]	=	Keysyms.XF86DiscretLFD;
		anArray[150]	=	Keysyms.XF86Color;
		anArray[151]	=	Keysyms.XF86Color;
		anArray[152]	=	Keysyms.XF86DiscretHDMI4;
		anArray[153]	=	Keysyms.XF86DiscretAV1;
		anArray[154]	=	Keysyms.XF86DiscretAV2;
		anArray[155]	=	Keysyms.XF86DiscretAV3;
		anArray[156]	=	Keysyms.XF86AVOpen;
		anArray[157]	=	Keysyms.XF86AVDiscMenu;
		anArray[158]	=	Keysyms.XF86AVTitleMenu;
		anArray[159]	=	Keysyms.XF86Test;
		anArray[160]	=	Keysyms.XF86ColdStart;
		anArray[161]	=	Keysyms.XF86Version;
		anArray[162]	=	Keysyms.XF86Undefined;
		anArray[163]	=	Keysyms.XF86ScreenFreeze;
		anArray[164]	=	Keysyms.XF86ScreenMute;
		anArray[165]	=	Keysyms.XF86Stop;
		anArray[166]	=	Keysyms.XF86Send;
		anArray[167]	=	Keysyms.XF86Phone;
		anArray[168]	=	Keysyms.XF86Menu;
		anArray[169]	=	Keysyms.XF86ListEnd;
		anArray[170]	=	Keysyms.XF86ListHome;
		anArray[171]	=	Keysyms.XF86VoiceControl;
		anArray[172]	=	Keysyms.XF86UpPage;
		anArray[173]	=	Keysyms.XF86DownPage;
		anArray[174]	=	Keysyms.XF86MBROption;
		anArray[175]	=	Keysyms.XF86RoomControl;
		anArray[176]	=	Keysyms.XF86TemperatureUp;
		anArray[177]	=	Keysyms.XF86TemperatureDown;
		anArray[178]	=	Keysyms.XF86LightOn;
		anArray[179]	=	Keysyms.XF86LightOff;
		anArray[180]	=	Keysyms.XF86DoNotDisturb;
		anArray[181]	=	Keysyms.XF86MakeUpRoom;
		anArray[182]	=	Keysyms.XF86Alarm;
		anArray[183]	=	Keysyms.XF86FuncUSB;
		anArray[184]	=	Keysyms.XF86FuncDIN;
		anArray[185]	=	Keysyms.XF86SoundEffect1;
		anArray[186]	=	Keysyms.XF86SoundEffect2;
		anArray[187]	=	Keysyms.XF86SoundEffect3;
		anArray[188]	=	Keysyms.XF86SoundEffect4;
		anArray[189]	=	Keysyms.XF86SoundEffect5;
		anArray[190]	=	Keysyms.XF86SoundEffect6;
		anArray[191]	=	Keysyms.XF86SoundEffect7;
		anArray[192]	=	Keysyms.XF86SoundEffect8;
		anArray[193]	=	Keysyms.XF86WooferPlus;
		anArray[194]	=	Keysyms.XF86WooferMinus;
		anArray[195]	=	Keysyms.XF86AnyNet;
		anArray[196]	=	Keysyms.XF86AutoPowerLink;
		anArray[197]	=	Keysyms.XF86SoftAP;
		anArray[198]	=	Keysyms.XF86AddSpeaker;
		anArray[199]	=	Keysyms.XF86SCReconnect;
		anArray[200]	=	Keysyms.XF86NetworkStandBy;
		anArray[201]	=	Keysyms.XF86SurroundOff;
		anArray[202]	=	Keysyms.XF86SurroundOn;
		anArray[203]	=	Keysyms.XF86BluetoothOnOff;
		anArray[204]	=	Keysyms.XF86AutoPowerOnOff;
		anArray[205]	=	Keysyms.XF86SoundControl;
		anArray[206]	=	Keysyms.XF86NightMode;
		anArray[207]	=	Keysyms.XF86UserEQ;
		anArray[208]	=	Keysyms.XF86EQLevelDown;
		anArray[209]	=	Keysyms.XF86EQLevelUp;
		anArray[210]	=	Keysyms.XF86DRCOn;
		anArray[211]	=	Keysyms.XF86DRCOff;
		anArray[212]	=	Keysyms.XF86SpeakerLevel;
		anArray[213]	=	Keysyms.XF86WooferSet;
		anArray[214]	=	Keysyms.XF86MiracastOn;
		anArray[215]	=	Keysyms.XF86MiracastOff;
		anArray[216]	=	Keysyms.XF86FuncRadio;
		anArray[217]	=	Keysyms.XF86SoundShareReset;
		anArray[218]	=	Keysyms.XF86TouchMode;
		anArray[219]	=	Keysyms.XF86Group;
		anArray[220]	=	Keysyms.XF86UnGroup;
		anArray[221]	=	Keysyms.XF86DeepSleep;
		anArray[222]	=	Keysyms.XF86RMLog;
		anArray[223]	=	Keysyms.XF86AuxDetect;
		anArray[224]	=	Keysyms.XF86EQ;
		anArray[225]	=	Keysyms.XF86GigaEQ;
		anArray[226]	=	Keysyms.XF86DJBeat;
		anArray[227]	=	Keysyms.XF86Panning;
		anArray[228]	=	Keysyms.XF86MICConnect;
		anArray[229]	=	Keysyms.XF86MICDisconnect;
		anArray[230]	=	Keysyms.XF86MICVolumeUp;
		anArray[231]	=	Keysyms.XF86MICVolumeDown;
		anArray[232]	=	Keysyms.XF86GyroA;
		anArray[233]	=	Keysyms.XF86GyroB;
		anArray[234]	=	Keysyms.XF86FanetOK;
		anArray[235]	=	Keysyms.XF86QuickStartPower;
		anArray[236]	=	Keysyms.XF86ShopMode;
		anArray[237]	=	Keysyms.XF86WIFIOnOff;
		anArray[238]	=	Keysyms.XF86OpticalDet;
		anArray[239]	=	Keysyms.XF86TVRemote;
		anArray[240]	=	Keysyms.XF86FuncAux;
		anArray[241]	=	Keysyms.XF86RaiseChannelCard1;
		anArray[242]	=	Keysyms.XF86LowerChannelCard1;
		anArray[243]	=	Keysyms.XF86RaiseChannelCard2;
		anArray[244]	=	Keysyms.XF86LowerChannelCard2;
		anArray[245]	=	Keysyms.XF86VolumeUpBathroom;
		anArray[246]	=	Keysyms.XF86VolumeDownBathroom;
		anArray[247]	=	Keysyms.XF86SelectDevice;
		anArray[248]	=	Keysyms.XF86SmartSoundOn;
		anArray[249]	=	Keysyms.XF86SmartSoundOff;
		anArray[250]	=	Keysyms.XF86Bluray4xFF;
		anArray[251]	=	Keysyms.XF86Bluray4xRewind;
		anArray[252]	=	Keysyms.XF86Bluray4xStop;
		anArray[253]	=	Keysyms.XF86DiscPlay;
		return anArray[count];
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int k = testKey();

		System.out.println(count + "\n");
		cc.writeKeyEvent(k, true);
		cc.writeKeyEvent(k, false);
		

		JButton source = (JButton)e.getSource();
		if(source.getText().equals("Vol+") && count< 253) {
			count++;
		}
		else if(source.getText().equals("Vol-") && count > 0) {
			count--;
		}
			
	}
	*/

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton)e.getSource();
		System.out.println(source.getText());

		if(source.getText().equals("Exit")) {
			this.dispose();

		}else if(source.getText().equals("Back")) {
			cc.writeKeyEvent(Keysyms.XF86XK_Back, true);
			cc.writeKeyEvent(Keysyms.XF86XK_Back, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressBack();
		    }
		}else if(source.getText().equals("Home")) {
			cc.writeKeyEvent(Keysyms.XF86Home, true);
			cc.writeKeyEvent(Keysyms.XF86Home, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressHome();
		    }
		}else if(source.getText().equals("Vol+")) {
			cc.writeKeyEvent(Keysyms.XF86AudioRaiseVolume, true);
			cc.writeKeyEvent(Keysyms.XF86AudioRaiseVolume, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressVolPlus();
		    }

		}else if(source.getText().equals("Vol-")) {
			cc.writeKeyEvent(Keysyms.XF86AudioLowerVolume, true);
			cc.writeKeyEvent(Keysyms.XF86AudioLowerVolume, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressVolMinus();
		    }

		}else if(source.getText().equals("Mute")) {
			System.out.println("Pressed: " + Keysyms.XF86AudioMute);
			cc.writeKeyEvent(Keysyms.XF86AudioMute, true);
			cc.writeKeyEvent(Keysyms.XF86AudioMute, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressMute();
		    }
		}else if(source.getText().equals("Ch+")) {
			cc.writeKeyEvent(Keysyms.XF86RaiseChannel, true);
			cc.writeKeyEvent(Keysyms.XF86RaiseChannel, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressChannelPlus();
		    }

		}else if(source.getText().equals("Ch-")) {
			cc.writeKeyEvent(Keysyms.XF86LowerChannel, true);
			cc.writeKeyEvent(Keysyms.XF86LowerChannel, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressChannelMinus();
		    }

		}else if(source.getText().equals("Up")) {
			cc.writeKeyEvent(Keysyms.Up, true);
			cc.writeKeyEvent(Keysyms.Up, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressUp();
		    }

		}else if(source.getText().equals("Down")) {
			cc.writeKeyEvent(Keysyms.Down, true);
			cc.writeKeyEvent(Keysyms.Down, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressDown();
		    }

		}else if(source.getText().equals("Right")) {
			cc.writeKeyEvent(Keysyms.Right, true);
			cc.writeKeyEvent(Keysyms.Right, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressRight();
		    }

		}else if(source.getText().equals("Left")) {
			cc.writeKeyEvent(Keysyms.Left, true);
			cc.writeKeyEvent(Keysyms.Left, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressLeft();
		    }

		}else if(source.getText().equals("Power")) {
			cc.writeKeyEvent(Keysyms.XF86PowerOff, true);
			cc.writeKeyEvent(Keysyms.XF86PowerOff, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressPower();
		    }

		}else if(source.getText().equals("0")) {
			cc.writeKeyEvent(Keysyms.KP_0, true);
			cc.writeKeyEvent(Keysyms.KP_0, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressZero();
		    }

		}else if(source.getText().equals("1")) {
			cc.writeKeyEvent(Keysyms.KP_1, true);
			cc.writeKeyEvent(Keysyms.KP_1, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressOne();
		    }

		}else if(source.getText().equals("2")) {
			cc.writeKeyEvent(Keysyms.KP_2, true);
			cc.writeKeyEvent(Keysyms.KP_2, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressTwo();
		    }

		}else if(source.getText().equals("3")) {
			cc.writeKeyEvent(Keysyms.KP_3, true);
			cc.writeKeyEvent(Keysyms.KP_3, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressThree();
		    }

		}else if(source.getText().equals("4")) {
			cc.writeKeyEvent(Keysyms.KP_4, true);
			cc.writeKeyEvent(Keysyms.KP_4, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressFour();
		    }

		}else if(source.getText().equals("5")) {
			cc.writeKeyEvent(Keysyms.KP_5, true);
			cc.writeKeyEvent(Keysyms.KP_5, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressFive();
		    }

		}else if(source.getText().equals("6")) {
			cc.writeKeyEvent(Keysyms.KP_6, true);
			cc.writeKeyEvent(Keysyms.KP_6, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressSix();
		    }

		}else if(source.getText().equals("7")) {
			cc.writeKeyEvent(Keysyms.KP_7, true);
			cc.writeKeyEvent(Keysyms.KP_7, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressSeven();
		    }

		}else if(source.getText().equals("8")) {
			cc.writeKeyEvent(Keysyms.KP_8, true);
			cc.writeKeyEvent(Keysyms.KP_8, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressEight();
		    }

		}else if(source.getText().equals("9")) {
			cc.writeKeyEvent(Keysyms.KP_9, true);
			cc.writeKeyEvent(Keysyms.KP_9, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressNine();
		    }

		}else if(source.getText().equals("123")) {
			cc.writeKeyEvent(Keysyms.XF86More, true);
			cc.writeKeyEvent(Keysyms.XF86More, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressOneTwoThree();
		    }

		}else if(source.getText().equals("...")) {
			cc.writeKeyEvent(Keysyms.XF86ExtraApp, true);
			cc.writeKeyEvent(Keysyms.XF86ExtraApp, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressDotDotDot();
		    }

		}else if(source.getText().equals("Play")) {
			cc.writeKeyEvent(Keysyms.XF86PlayBack, true);
			cc.writeKeyEvent(Keysyms.XF86PlayBack, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressPlay();
		    }

		}else if(source.getText().equals("Ok")) {
			cc.writeKeyEvent(Keysyms.XF86Info, true);
			cc.writeKeyEvent(Keysyms.XF86Info, false);
			
			if(!codeGenerator.isCodeRunning()) {
		    	codeGenerator.pressOk();
		    }

		}

	}

	static int i = 0;
	int getKeySym(int a)
	{

		if(a == 1){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Back));return Keysyms.XF86Back;}
		else if(a == 12){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.minus));return Keysyms.minus;}
		else if(a == 13){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.equal));return Keysyms.equal;}
		else if(a == 14){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.BackSpace));return Keysyms.BackSpace;}
		else if(a == 15){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Tab));return Keysyms.Tab;}
		else if(a == 16){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.q));return Keysyms.q;}
		else if(a == 17){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.w));return Keysyms.w;}
		else if(a == 18){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.e));return Keysyms.e;}
		else if(a == 19){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.r));return Keysyms.r;}
		else if(a == 20){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.t));return Keysyms.t;}
		else if(a == 21){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.y));return Keysyms.y;}
		else if(a == 22){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.u));return Keysyms.u;}
		else if(a == 23){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.i));return Keysyms.i;}
		else if(a == 24){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.o));return Keysyms.o;}
		else if(a == 25){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.p));return Keysyms.p;}
		else if(a == 26){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.bracketleft));return Keysyms.bracketleft;}
		else if(a == 27){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.bracketright));return Keysyms.bracketright;}
		else if(a == 28){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Return));return Keysyms.Return;}
		else if(a == 29){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Control_L));return Keysyms.Control_L;}
		else if(a == 30){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.a));return Keysyms.a;}
		else if(a == 31){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.s));return Keysyms.s;}
		else if(a == 32){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.d));return Keysyms.d;}
		else if(a == 33){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.f));return Keysyms.f;}
		else if(a == 34){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.g));return Keysyms.g;}
		else if(a == 35){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.h));return Keysyms.h;}
		else if(a == 36){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.j));return Keysyms.j;}
		else if(a == 37){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.k));return Keysyms.k;}
		else if(a == 38){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.l));return Keysyms.l;}
		else if(a == 39){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.semicolon));return Keysyms.semicolon;}
		else if(a == 40){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.apostrophe));return Keysyms.apostrophe;}
		else if(a == 41){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.grave));return Keysyms.grave;}
		else if(a == 42){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Shift_L));return Keysyms.Shift_L;}
		else if(a == 43){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.backslash));return Keysyms.backslash;}
		else if(a == 44){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.z));return Keysyms.z;}
		else if(a == 45){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.x));return Keysyms.x;}
		else if(a == 46){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.c));return Keysyms.c;}
		else if(a == 47){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.v));return Keysyms.v;}
		else if(a == 48){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.b));return Keysyms.b;}
		else if(a == 49){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.n));return Keysyms.n;}
		else if(a == 50){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.m));return Keysyms.m;}
		else if(a == 51){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.comma));return Keysyms.comma;}
		else if(a == 52){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.period));return Keysyms.period;}
		else if(a == 53){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.slash));return Keysyms.slash;}
		else if(a == 54){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Shift_R));return Keysyms.Shift_R;}
		else if(a == 55){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Multiply));return Keysyms.KP_Multiply;}
		else if(a == 56){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Alt_L));return Keysyms.Alt_L;}
		else if(a == 57){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.space));return Keysyms.space;}
		else if(a == 58){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Caps_Lock));return Keysyms.Caps_Lock;}
		else if(a == 59){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Red));return Keysyms.XF86Red;}
		else if(a == 60){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Green));return Keysyms.XF86Green;}
		else if(a == 61){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Yellow));return Keysyms.XF86Yellow;}
		else if(a == 62){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Blue));return Keysyms.XF86Blue;}
		else if(a == 63){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Home));return Keysyms.XF86Home;}
		else if(a == 64){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Display));return Keysyms.XF86Display;}
		else if(a == 65){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ChannelList));return Keysyms.XF86ChannelList;}
		else if(a == 66){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioMute));return Keysyms.XF86AudioMute;}
		else if(a == 67){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioLowerVolume));return Keysyms.XF86AudioLowerVolume;}
		else if(a == 68){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioRaiseVolume));return Keysyms.XF86AudioRaiseVolume;}
		else if(a == 69){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Num_Lock));return Keysyms.Num_Lock;}
		else if(a == 70){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Scroll_Lock));return Keysyms.Scroll_Lock;}
		else if(a == 71){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Home));return Keysyms.KP_Home;}
		else if(a == 72){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Up));return Keysyms.KP_Up;}
		else if(a == 73){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Prior));return Keysyms.KP_Prior;}
		else if(a == 74){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Subtract));return Keysyms.KP_Subtract;}
		else if(a == 75){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Left));return Keysyms.KP_Left;}
		else if(a == 76){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Begin));return Keysyms.KP_Begin;}
		else if(a == 77){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Right));return Keysyms.KP_Right;}
		else if(a == 78){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Add));return Keysyms.KP_Add;}
		else if(a == 79){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_End));return Keysyms.KP_End;}
		else if(a == 80){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Down));return Keysyms.KP_Down;}
		else if(a == 81){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Next));return Keysyms.KP_Next;}
		else if(a == 82){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Insert));return Keysyms.KP_Insert;}
		else if(a == 83){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Delete));return Keysyms.KP_Delete;}
		else if(a == 84){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.ISO_Level3_Shift));return Keysyms.ISO_Level3_Shift;}
		else if(a == 85){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LSGT));return Keysyms.XF86LSGT;}
		else if(a == 86){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LowerChannel));return Keysyms.XF86LowerChannel;}
		else if(a == 87){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86RaiseChannel));return Keysyms.XF86RaiseChannel;}
		else if(a == 88){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Katakana));return Keysyms.Katakana;}
		else if(a == 89){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Hiragana));return Keysyms.Hiragana;}
		else if(a == 90){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Henkan_Mode));return Keysyms.Henkan_Mode;}
		else if(a == 91){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Hiragana_Katakana));return Keysyms.Hiragana_Katakana;}
		else if(a == 92){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Muhenkan));return Keysyms.Muhenkan;}
		else if(a == 93){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Enter));return Keysyms.KP_Enter;}
		else if(a == 94){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Control_R));return Keysyms.Control_R;}
		else if(a == 95){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Divide));return Keysyms.KP_Divide;}
		else if(a == 96){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Print));return Keysyms.Print;}
		else if(a == 97){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Alt_R));return Keysyms.Alt_R;}
		else if(a == 98){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Linefeed));return Keysyms.Linefeed;}
		else if(a == 99){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Home));return Keysyms.Home;}
		else if(a == 100){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Up));return Keysyms.Up;}
		else if(a == 101){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Prior));return Keysyms.Prior;}
		else if(a == 102){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Left));return Keysyms.Left;}
		else if(a == 103){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Right));return Keysyms.Right;}
		else if(a == 104){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.End));return Keysyms.End;}
		else if(a == 105){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Down));return Keysyms.Down;}
		else if(a == 106){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Next));return Keysyms.Next;}
		else if(a == 107){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Insert));return Keysyms.Insert;}
		else if(a == 108){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Delete));return Keysyms.Delete;}
		else if(a == 109){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDPowerOff));return Keysyms.XF86LFDPowerOff;}
		else if(a == 110){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PowerOff));return Keysyms.XF86PowerOff;}
		else if(a == 111){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Equal));return Keysyms.KP_Equal;}
		else if(a == 112){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.plusminus));return Keysyms.plusminus;}
		else if(a == 113){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Pause));return Keysyms.Pause;}
		else if(a == 114){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDMagicInfo));return Keysyms.XF86LFDMagicInfo;}
		else if(a == 115){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.KP_Decimal));return Keysyms.KP_Decimal;}
		else if(a == 116){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Hangul));return Keysyms.Hangul;}
		else if(a == 117){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Hangul_Hanja));return Keysyms.Hangul_Hanja;}
		else if(a == 118){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SysMenu));return Keysyms.XF86SysMenu;}
		else if(a == 119){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Super_R));return Keysyms.Super_R;}
		else if(a == 120){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SimpleMenu));return Keysyms.XF86SimpleMenu;}
		else if(a == 121){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRRepeat));return Keysyms.XF86MBRRepeat;}
		else if(a == 122){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ChannelGuide));return Keysyms.XF86ChannelGuide;}
		else if(a == 123){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Undo));return Keysyms.Undo;}
		else if(a == 124){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PictureSize));return Keysyms.XF86PictureSize;}
		else if(a == 125){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PictureMode));return Keysyms.XF86PictureMode;}
		else if(a == 126){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Open));return Keysyms.XF86Open;}
		else if(a == 127){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Hdmi));return Keysyms.XF86Hdmi;}
		else if(a == 128){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86UsbHub));return Keysyms.XF86UsbHub;}
		else if(a == 129){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Cut));return Keysyms.XF86Cut;}
		else if(a == 130){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86EManual));return Keysyms.XF86EManual;}
		else if(a == 131){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDScreenLock));return Keysyms.XF86LFDScreenLock;}
		else if(a == 132){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86More));return Keysyms.XF86More;}
		else if(a == 133){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FactoryMode));return Keysyms.XF86FactoryMode;}
		else if(a == 134){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Sleep));return Keysyms.XF86Sleep;}
		else if(a == 135){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WakeUp));return Keysyms.XF86WakeUp;}
		else if(a == 136){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Explorer));return Keysyms.XF86Explorer;}
		else if(a == 137){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TV));return Keysyms.XF86TV;}
		else if(a == 138){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DTV));return Keysyms.XF86DTV;}
		else if(a == 139){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86STBPower));return Keysyms.XF86STBPower;}
		else if(a == 140){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ChannelAddDel));return Keysyms.XF86ChannelAddDel;}
		else if(a == 141){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PanelDown));return Keysyms.XF86PanelDown;}
		else if(a == 142){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WWW));return Keysyms.XF86WWW;}
		else if(a == 143){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTColorMecha));return Keysyms.XF86BTColorMecha;}
		else if(a == 144){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86StillPicture));return Keysyms.XF86StillPicture;}
		else if(a == 145){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTPairing));return Keysyms.XF86BTPairing;}
		else if(a == 146){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTHotkey));return Keysyms.XF86BTHotkey;}
		else if(a == 147){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86CinemaMode));return Keysyms.XF86CinemaMode;}
		else if(a == 148){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTContentsBar));return Keysyms.XF86BTContentsBar;}
		else if(a == 149){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Game));return Keysyms.XF86Game;}
		else if(a == 150){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDUnset));return Keysyms.XF86LFDUnset;}
		else if(a == 151){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PIPChannelUp));return Keysyms.XF86PIPChannelUp;}
		else if(a == 152){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PIPChannelDown));return Keysyms.XF86PIPChannelDown;}
		else if(a == 153){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Antena));return Keysyms.XF86Antena;}
		else if(a == 154){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ChannelAutoTune));return Keysyms.XF86ChannelAutoTune;}
		else if(a == 155){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PanelEnter));return Keysyms.XF86PanelEnter;}
		else if(a == 156){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRLink));return Keysyms.XF86MBRLink;}
		else if(a == 157){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PanelUp));return Keysyms.XF86PanelUp;}
		else if(a == 158){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioStop));return Keysyms.XF86AudioStop;}
		else if(a == 159){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioRecord));return Keysyms.XF86AudioRecord;}
		else if(a == 160){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioRewind));return Keysyms.XF86AudioRewind;}
		else if(a == 161){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Game3D));return Keysyms.XF86Game3D;}
		else if(a == 162){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WheelLeftKey));return Keysyms.XF86WheelLeftKey;}
		else if(a == 163){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WheelRightKey));return Keysyms.XF86WheelRightKey;}
		else if(a == 164){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86HomePage));return Keysyms.XF86HomePage;}
		else if(a == 165){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PanelExit));return Keysyms.XF86PanelExit;}
		else if(a == 166){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Exit));return Keysyms.XF86Exit;}
		else if(a == 167){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRTV));return Keysyms.XF86MBRTV;}
		else if(a == 168){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRSTBGuide));return Keysyms.XF86MBRSTBGuide;}
		else if(a == 169){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRBDPopup));return Keysyms.XF86MBRBDPopup;}
		else if(a == 170){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRBDDVDPower));return Keysyms.XF86MBRBDDVDPower;}
		else if(a == 171){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRSetupFailure));return Keysyms.XF86MBRSetupFailure;}
		else if(a == 172){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRSetup));return Keysyms.XF86MBRSetup;}
		else if(a == 173){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRWatchTV));return Keysyms.XF86MBRWatchTV;}
		else if(a == 174){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PreviousChannel));return Keysyms.XF86PreviousChannel;}
		else if(a == 175){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FavoriteChannel));return Keysyms.XF86FavoriteChannel;}
		else if(a == 176){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Recommend));return Keysyms.XF86Recommend;}
		else if(a == 177){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86NumberPad));return Keysyms.XF86NumberPad;}
		else if(a == 178){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AspectRatio169));return Keysyms.XF86AspectRatio169;}
		else if(a == 179){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MTS));return Keysyms.XF86MTS;}
		else if(a == 180){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Info));return Keysyms.XF86Info;}
		else if(a == 181){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundMode));return Keysyms.XF86SoundMode;}
		else if(a == 182){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF863XSpeed));return Keysyms.XF863XSpeed;}
		else if(a == 183){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF863D));return Keysyms.XF863D;}
		else if(a == 184){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TTXMIX));return Keysyms.XF86TTXMIX;}
		else if(a == 185){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SRSSXT));return Keysyms.XF86SRSSXT;}
		else if(a == 186){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WIFIPairing));return Keysyms.XF86WIFIPairing;}
		else if(a == 187){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Mode_switch));return Keysyms.Mode_switch;}
		else if(a == 188){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioPlay));return Keysyms.XF86AudioPlay;}
		else if(a == 189){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioPause));return Keysyms.XF86AudioPause;}
		else if(a == 190){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DualView));return Keysyms.XF86DualView;}
		else if(a == 191){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTApps));return Keysyms.XF86BTApps;}
		else if(a == 192){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FamilyMode));return Keysyms.XF86FamilyMode;}
		else if(a == 193){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86EnergySaving));return Keysyms.XF86EnergySaving;}
		else if(a == 194){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRClear));return Keysyms.XF86MBRClear;}
		else if(a == 195){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Subtitle));return Keysyms.XF86Subtitle;}
		else if(a == 196){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioNext));return Keysyms.XF86AudioNext;}
		else if(a == 197){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TVSNS));return Keysyms.XF86TVSNS;}
		else if(a == 198){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DVR));return Keysyms.XF86DVR;}
		else if(a == 199){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Apps));return Keysyms.XF86Apps;}
		else if(a == 200){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Camera));return Keysyms.XF86Camera;}
		else if(a == 201){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Caption));return Keysyms.XF86Caption;}
		else if(a == 202){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ZoomIn));return Keysyms.XF86ZoomIn;}
		else if(a == 203){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PanelPlus));return Keysyms.XF86PanelPlus;}
		else if(a == 204){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTVoice));return Keysyms.XF86BTVoice;}
		else if(a == 205){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Search));return Keysyms.XF86Search;}
		else if(a == 206){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Go));return Keysyms.XF86Go;}
		else if(a == 207){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PanelMinus));return Keysyms.XF86PanelMinus;}
		else if(a == 208){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoccerMode));return Keysyms.XF86SoccerMode;}
		else if(a == 209){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Amazon));return Keysyms.XF86Amazon;}
		else if(a == 210){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AudioDescription));return Keysyms.XF86AudioDescription;}
		else if(a == 211){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.Cancel));return Keysyms.Cancel;}
		else if(a == 212){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PreviousChapter));return Keysyms.XF86PreviousChapter;}
		else if(a == 213){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86NextChapter));return Keysyms.XF86NextChapter;}
		else if(a == 214){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Netflix));return Keysyms.XF86Netflix;}
		else if(a == 215){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PIP));return Keysyms.XF86PIP;}
		else if(a == 216){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRWatchMovie));return Keysyms.XF86MBRWatchMovie;}
		else if(a == 217){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRMenu));return Keysyms.XF86MBRMenu;}
		else if(a == 218){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBRConfirm));return Keysyms.XF86MBRConfirm;}
		else if(a == 219){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FamilyHub));return Keysyms.XF86FamilyHub;}
		else if(a == 220){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86HDMICEC));return Keysyms.XF86HDMICEC;}
		else if(a == 221){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BTDevice));return Keysyms.XF86BTDevice;}
		else if(a == 222){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Save));return Keysyms.XF86Save;}
		else if(a == 223){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Documents));return Keysyms.XF86Documents;}
		else if(a == 224){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoftWakeup));return Keysyms.XF86SoftWakeup;}
		else if(a == 225){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDSet));return Keysyms.XF86LFDSet;}
		else if(a == 226){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Wakeup));return Keysyms.XF86Wakeup;}
		else if(a == 227){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDBlank));return Keysyms.XF86LFDBlank;}
		else if(a == 228){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LeftPage));return Keysyms.XF86LeftPage;}
		else if(a == 229){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86RightPage));return Keysyms.XF86RightPage;}
		else if(a == 230){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86PlayBack));return Keysyms.XF86PlayBack;}
		else if(a == 231){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ExtraApp));return Keysyms.XF86ExtraApp;}
		else if(a == 232){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretPowerOff));return Keysyms.XF86DiscretPowerOff;}
		else if(a == 233){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretPowerOn));return Keysyms.XF86DiscretPowerOn;}
		else if(a == 234){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretSVideo1));return Keysyms.XF86DiscretSVideo1;}
		else if(a == 235){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretSVideo2));return Keysyms.XF86DiscretSVideo2;}
		else if(a == 236){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretSVideo3));return Keysyms.XF86DiscretSVideo3;}
		else if(a == 237){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretComponent1));return Keysyms.XF86DiscretComponent1;}
		else if(a == 238){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretComponent2));return Keysyms.XF86DiscretComponent2;}
		else if(a == 239){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretComponent3));return Keysyms.XF86DiscretComponent3;}
		else if(a == 240){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretHDMI1));return Keysyms.XF86DiscretHDMI1;}
		else if(a == 241){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretHDMI2));return Keysyms.XF86DiscretHDMI2;}
		else if(a == 242){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretHDMI3));return Keysyms.XF86DiscretHDMI3;}
		else if(a == 243){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretPC));return Keysyms.XF86DiscretPC;}
		else if(a == 244){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretDVI1));return Keysyms.XF86DiscretDVI1;}
		else if(a == 245){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretDVI2));return Keysyms.XF86DiscretDVI2;}
		else if(a == 246){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretZoom1));return Keysyms.XF86DiscretZoom1;}
		else if(a == 247){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretZoom2));return Keysyms.XF86DiscretZoom2;}
		else if(a == 248){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretPanorama));return Keysyms.XF86DiscretPanorama;}
		else if(a == 249){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Discret_4_3));return Keysyms.XF86Discret_4_3;}
		else if(a == 250){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Discret_16_9));return Keysyms.XF86Discret_16_9;}
		else if(a == 251){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LFDPowerOn));return Keysyms.XF86LFDPowerOn;}
		else if(a == 252){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretLFD_DP));return Keysyms.XF86DiscretLFD_DP;}
		else if(a == 253){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretSourceTV));return Keysyms.XF86DiscretSourceTV;}
		else if(a == 254){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Color));return Keysyms.XF86Color;}
		else if(a == 255){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretLFD_HDBT_RX));return Keysyms.XF86DiscretLFD_HDBT_RX;}
		else if(a == 256){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretLFD_OPS));return Keysyms.XF86DiscretLFD_OPS;}
		else if(a == 257){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretHDMI4));return Keysyms.XF86DiscretHDMI4;}
		else if(a == 258){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretAV1));return Keysyms.XF86DiscretAV1;}
		else if(a == 259){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretAV2));return Keysyms.XF86DiscretAV2;}
		else if(a == 260){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscretAV3));return Keysyms.XF86DiscretAV3;}
		else if(a == 261){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AVOpen));return Keysyms.XF86AVOpen;}
		else if(a == 262){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AVDiscMenu));return Keysyms.XF86AVDiscMenu;}
		else if(a == 263){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AVTitleMenu));return Keysyms.XF86AVTitleMenu;}
		else if(a == 264){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Test));return Keysyms.XF86Test;}
		else if(a == 265){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ColdStart));return Keysyms.XF86ColdStart;}
		else if(a == 266){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Version));return Keysyms.XF86Version;}
		else if(a == 267){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Undefined));return Keysyms.XF86Undefined;}
		else if(a == 268){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ScreenFreeze));return Keysyms.XF86ScreenFreeze;}
		else if(a == 269){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ScreenMute));return Keysyms.XF86ScreenMute;}
		else if(a == 270){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Stop));return Keysyms.XF86Stop;}
		else if(a == 271){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Send));return Keysyms.XF86Send;}
		else if(a == 272){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Phone));return Keysyms.XF86Phone;}
		else if(a == 273){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Menu));return Keysyms.XF86Menu;}
		else if(a == 274){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ListEnd));return Keysyms.XF86ListEnd;}
		else if(a == 275){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ListHome));return Keysyms.XF86ListHome;}
		else if(a == 276){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86VoiceControl));return Keysyms.XF86VoiceControl;}
		else if(a == 277){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86UpPage));return Keysyms.XF86UpPage;}
		else if(a == 278){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DownPage));return Keysyms.XF86DownPage;}
		else if(a == 279){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MBROption));return Keysyms.XF86MBROption;}
		else if(a == 280){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86RoomControl));return Keysyms.XF86RoomControl;}
		else if(a == 281){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TemperatureUp));return Keysyms.XF86TemperatureUp;}
		else if(a == 282){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TemperatureDown));return Keysyms.XF86TemperatureDown;}
		else if(a == 283){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LightOn));return Keysyms.XF86LightOn;}
		else if(a == 284){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LightOff));return Keysyms.XF86LightOff;}
		else if(a == 285){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DoNotDisturb));return Keysyms.XF86DoNotDisturb;}
		else if(a == 286){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MakeUpRoom));return Keysyms.XF86MakeUpRoom;}
		else if(a == 287){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Alarm));return Keysyms.XF86Alarm;}
		else if(a == 288){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FuncUSB));return Keysyms.XF86FuncUSB;}
		else if(a == 289){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FuncDIN));return Keysyms.XF86FuncDIN;}
		else if(a == 290){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect1));return Keysyms.XF86SoundEffect1;}
		else if(a == 291){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect2));return Keysyms.XF86SoundEffect2;}
		else if(a == 292){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect3));return Keysyms.XF86SoundEffect3;}
		else if(a == 293){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect4));return Keysyms.XF86SoundEffect4;}
		else if(a == 294){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect5));return Keysyms.XF86SoundEffect5;}
		else if(a == 295){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect6));return Keysyms.XF86SoundEffect6;}
		else if(a == 296){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect7));return Keysyms.XF86SoundEffect7;}
		else if(a == 297){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundEffect8));return Keysyms.XF86SoundEffect8;}
		else if(a == 298){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WooferPlus));return Keysyms.XF86WooferPlus;}
		else if(a == 299){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WooferMinus));return Keysyms.XF86WooferMinus;}
		else if(a == 300){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AnyNet));return Keysyms.XF86AnyNet;}
		else if(a == 301){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AutoPowerLink));return Keysyms.XF86AutoPowerLink;}
		else if(a == 302){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoftAP));return Keysyms.XF86SoftAP;}
		else if(a == 303){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AddSpeaker));return Keysyms.XF86AddSpeaker;}
		else if(a == 304){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SCReconnect));return Keysyms.XF86SCReconnect;}
		else if(a == 305){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86NetworkStandBy));return Keysyms.XF86NetworkStandBy;}
		else if(a == 306){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SurroundOff));return Keysyms.XF86SurroundOff;}
		else if(a == 307){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SurroundOn));return Keysyms.XF86SurroundOn;}
		else if(a == 308){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86BluetoothOnOff));return Keysyms.XF86BluetoothOnOff;}
		else if(a == 309){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AutoPowerOnOff));return Keysyms.XF86AutoPowerOnOff;}
		else if(a == 310){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundControl));return Keysyms.XF86SoundControl;}
		else if(a == 311){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86NightMode));return Keysyms.XF86NightMode;}
		else if(a == 312){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86UserEQ));return Keysyms.XF86UserEQ;}
		else if(a == 313){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86EQLevelDown));return Keysyms.XF86EQLevelDown;}
		else if(a == 314){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86EQLevelUp));return Keysyms.XF86EQLevelUp;}
		else if(a == 315){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DRCOn));return Keysyms.XF86DRCOn;}
		else if(a == 316){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DRCOff));return Keysyms.XF86DRCOff;}
		else if(a == 317){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SpeakerLevel));return Keysyms.XF86SpeakerLevel;}
		else if(a == 318){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WooferSet));return Keysyms.XF86WooferSet;}
		else if(a == 319){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MiracastOn));return Keysyms.XF86MiracastOn;}
		else if(a == 320){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MiracastOff));return Keysyms.XF86MiracastOff;}
		else if(a == 321){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FuncRadio));return Keysyms.XF86FuncRadio;}
		else if(a == 322){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SoundShareReset));return Keysyms.XF86SoundShareReset;}
		else if(a == 323){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TouchMode));return Keysyms.XF86TouchMode;}
		else if(a == 324){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Group));return Keysyms.XF86Group;}
		else if(a == 325){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86UnGroup));return Keysyms.XF86UnGroup;}
		else if(a == 326){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DeepSleep));return Keysyms.XF86DeepSleep;}
		else if(a == 327){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86RMLog));return Keysyms.XF86RMLog;}
		else if(a == 328){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86AuxDetect));return Keysyms.XF86AuxDetect;}
		else if(a == 329){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86EQ));return Keysyms.XF86EQ;}
		else if(a == 330){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86GigaEQ));return Keysyms.XF86GigaEQ;}
		else if(a == 331){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DJBeat));return Keysyms.XF86DJBeat;}
		else if(a == 332){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Panning));return Keysyms.XF86Panning;}
		else if(a == 333){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MICConnect));return Keysyms.XF86MICConnect;}
		else if(a == 334){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MICDisconnect));return Keysyms.XF86MICDisconnect;}
		else if(a == 335){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MICVolumeUp));return Keysyms.XF86MICVolumeUp;}
		else if(a == 336){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86MICVolumeDown));return Keysyms.XF86MICVolumeDown;}
		else if(a == 337){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86GyroA));return Keysyms.XF86GyroA;}
		else if(a == 338){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86GyroB));return Keysyms.XF86GyroB;}
		else if(a == 339){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FanetOK));return Keysyms.XF86FanetOK;}
		else if(a == 340){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86QuickStartPower));return Keysyms.XF86QuickStartPower;}
		else if(a == 341){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86ShopMode));return Keysyms.XF86ShopMode;}
		else if(a == 342){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86WIFIOnOff));return Keysyms.XF86WIFIOnOff;}
		else if(a == 343){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86OpticalDet));return Keysyms.XF86OpticalDet;}
		else if(a == 344){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86TVRemote));return Keysyms.XF86TVRemote;}
		else if(a == 345){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86FuncAux));return Keysyms.XF86FuncAux;}
		else if(a == 346){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86RaiseChannelCard1));return Keysyms.XF86RaiseChannelCard1;}
		else if(a == 347){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LowerChannelCard1));return Keysyms.XF86LowerChannelCard1;}
		else if(a == 348){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86RaiseChannelCard2));return Keysyms.XF86RaiseChannelCard2;}
		else if(a == 349){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86LowerChannelCard2));return Keysyms.XF86LowerChannelCard2;}
		else if(a == 350){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86VolumeUpBathroom));return Keysyms.XF86VolumeUpBathroom;}
		else if(a == 351){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86VolumeDownBathroom));return Keysyms.XF86VolumeDownBathroom;}
		else if(a == 352){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SelectDevice));return Keysyms.XF86SelectDevice;}
		else if(a == 353){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SmartSoundOn));return Keysyms.XF86SmartSoundOn;}
		else if(a == 354){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86SmartSoundOff));return Keysyms.XF86SmartSoundOff;}
		else if(a == 355){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Bluray4xFF));return Keysyms.XF86Bluray4xFF;}
		else if(a == 356){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Bluray4xRewind));return Keysyms.XF86Bluray4xRewind;}
		else if(a == 357){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86Bluray4xStop));return Keysyms.XF86Bluray4xStop;}
		else if(a == 358){ System.out.println("Pressed: " +Integer.toHexString(+Keysyms.XF86DiscPlay));return Keysyms.XF86DiscPlay;}
	    return 0;
	    
	}
	
}
