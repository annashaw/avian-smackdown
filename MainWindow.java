import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.sound.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainWindow extends JPanel implements ActionListener, WindowListener, KeyListener{
	
	JPanel menu, startScreen, enterInfo;
	
	JPanel battle, topBattleClear, bottomBattle, battleSelctMenu, fMenu, iMenu; 
	JLayeredPane topBattle;
	JButton fight, item, run, item1, item2, hit;
	JTextArea charHP, enemyHP;
	JTextPane instructions;
	
	BufferedImage image2;
	BufferedImage startImage;

	Image image3;
	
	String parrotSquwak = "parrotSquwak.wav";
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(parrotSquwak).getAbsoluteFile());
	Clip parrotClip = AudioSystem.getClip();
	
	String footsteps = "footsteps on concrete.wav";
	AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File(footsteps).getAbsoluteFile());
	Clip footClip = AudioSystem.getClip();
	
	JPanel map, topMap, middleMap, bottomMap;
	JButton map1;
	
	JButton ss1, menu1, battlemap1, eiGender, eiMajor, readyToPlay;
	JButton right, left, up, down;
	
	JRadioButton male, female;
	JTextField eiEnterName;
	
	JTextArea charXP, enemyXP;
	
	char KeyChar;
	
	String charName = "";
	char gen = 'a';
	char maj = 'b';
	
	Image img23; 
	
	Sector sector1 = new Sector (15, 15, "Security Gate"); 
	
	public MainWindow() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		map = new JPanel();
		menu = new JPanel();
		startScreen = new JPanel();
		enterInfo = new JPanel();
		
		
		ImagePanel startPic = new ImagePanel("Homescreen 2.png", 700, 700);
		startPic.setPreferredSize(new Dimension(700, 700));
		
		ss1 = new JButton("Eco-Friendly Avian Smackdown");
		ss1.addActionListener(this);
		ss1.setActionCommand("push to start");
		startPic.add(ss1);
		startScreen.add(startPic);
		
		
		add(startScreen);
		
		//take data
		eiEnterName = new JTextField(20);
		eiEnterName.addKeyListener(this);
		enterInfo.add(eiEnterName);
		
		
		male = new JRadioButton("male");
		male.addActionListener(this);
		male.setActionCommand("is male");
		enterInfo.add(male);
		
		female = new JRadioButton("female");
		female.addActionListener(this);
		female.setActionCommand("is female");
		enterInfo.add(female);
		
		readyToPlay = new JButton("click to begin");
		readyToPlay.addActionListener(this);
		readyToPlay.setActionCommand("begin game");
		enterInfo.add(readyToPlay);
		
		add(enterInfo);
		enterInfo.setLayout(new GridLayout(0,1)); 
		enterInfo.setVisible(false);
		
		//menu
		menu.setPreferredSize(new Dimension(700, 700));
		menu1 = new JButton("START GAME");
		menu1.addActionListener(this);
		menu1.setActionCommand("go to map");
		menu.add(menu1);
		
		instructions = new JTextPane();
		instructions.setText("As the sun set on this fair campus, "
				+ "\nthe avians made their move. "
				+ "\nFirst, came their mischievous crows, which darkened "
				+ "\nour nights and darkened our thoughts. "
				+ "\nThen the sea-birds arrived, with their terrible cries impeding our thinking, "
				+ "\nsending us into mass disarray"
				+ "\nLed by the Great Ceasar"
				+ "\nTheir full battalions flocked to the campus"
				+ "\n And the devastation began"
				+ "\n."
				+ "\n."
				+ "\n."
				+ "\n."
				+ "\nBut a young warrior, wielding the magic Trident of the Tritons"
				+ "\nstood in the way of the imminent bird victory."
				+ "\nAnd gave us the first glimmer of hope."
				+ "\nThis warrior brought on the age that is now called"
				+ "\nThe Eco-Friendly Avian Smack-Down"
				+ "\nYou’re a new student. A wide-eyed, inexperienced, "
				+ "\nfreshman but you show much promise in "
				+ "\nbecoming adept in the field of bird defence. "
				+ "\nWhat will truly happen, well, that remains to be seen…"
				+ "\n"
				+ "\nHow To Play:"
				+ "\nUse the directional buttons on the screen to move your avatar around the map. "
				+ "\nBeware, for birds will attack you as you make your merry way around campus. "
				+ "\nFight the birds off by "
				+ "\nselecting your attacks using the provided buttons. "
				+ "\nIf you die you lose, but if you kill the bird you will collect a feather. "
				+ "\nAfter collecting five feathers you must fight Ceasar, leader of all avian invasions. "
				+ "\nFight well, and good luck on your journey"
				+ "\n");
		StyledDocument doc = instructions.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		battlemap1 = new JButton("go to battle");
		battlemap1.addActionListener(this);
		battlemap1.setActionCommand("start battle");
		menu.add(battlemap1);
		menu.add(instructions);
		add(menu);
		menu.setVisible(false);
		
		//map screen
		
		map.setPreferredSize(new Dimension(650, 650));
		map.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		topMap = new JPanel();
		topMap.setPreferredSize(new Dimension(650, 50));
		middleMap = new JPanel();
		middleMap.setPreferredSize(new Dimension(650, 500));
		bottomMap = new JPanel();
		bottomMap.setPreferredSize(new Dimension(650, 75));
		
		ImagePanel middleMap2 = new ImagePanel("Sector Beach Pond area.jpg", 650, 500);
		middleMap2.setPreferredSize(new Dimension(650,500));
		
		map.add(topMap);
		map.add(middleMap2);
		map.add(bottomMap);
		
		map1 = new JButton("Instructions");
		map1.addActionListener(this);
		map1.setActionCommand("go to menu");
		topMap.add(map1);
		
		footClip.open(audioInputStream2);
		
		right = new JButton("right");
		right.addActionListener(this);
		right.setActionCommand("go right");
		left = new JButton("left");
		left.addActionListener(this);
		left.setActionCommand("go left");
		up = new JButton("up");
		up.addActionListener(this);
		up.setActionCommand("go up");
		down = new JButton("down");
		down.addActionListener(this);
		down.setActionCommand("go down");
		bottomMap.add(right);
		bottomMap.add(left);
		bottomMap.add(up);
		bottomMap.add(down);
		  
		sector1.addKeyListener(this);
		sector1.setFocusable(true);
		sector1.requestFocusInWindow();
	    middleMap2.add(sector1.mySectorPanel);
	    add(map);
	    map.setVisible(false);
	    
	    
	    
	    battle = new JPanel();
		battle.setPreferredSize(new Dimension(650, 650));
		battle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		topBattle = new JLayeredPane();
		
		topBattle.setLayout(new BoxLayout(topBattle, BoxLayout.PAGE_AXIS));
		
		topBattle.setPreferredSize(new Dimension(650, 500));
		battle.add(topBattle);
		
		ImagePanel battleImage = new ImagePanel("Go Pavilion Battle.jpg", 650, 500);
		battleImage. setPreferredSize(new Dimension(650, 500));
		battleImage.setLayout(new FlowLayout());
		topBattle.add(battleImage, new Integer(0), 0);
		
		JPanel hpBars = new JPanel();
		charHP = new JTextArea("character HP", 1, 10);
		enemyHP = new JTextArea("enemy HP", 1, 10);
		hpBars.add(charHP);
		hpBars.add(enemyHP);
		hpBars.setOpaque(false);
		battleImage.add(hpBars);
		
		bottomBattle = new JPanel();
		//bottomBattle.setBackground(Color.black);
		bottomBattle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		bottomBattle.setPreferredSize(new Dimension(650, 75));
		battle.add(bottomBattle);
		
		battleSelctMenu = new JPanel();
		battleSelctMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 0 ,0));
		//battleSelctMenu.setBackground(Color.blue);
		battleSelctMenu.setPreferredSize(new Dimension(150,75));
		
		fight = new JButton("fight");
		fight.addActionListener(this);
		fight.setActionCommand("fight");
		battleSelctMenu.add(fight);
		
		item = new JButton("item");
		item.addActionListener(this);
		item.setActionCommand("item");
		battleSelctMenu.add(item);
		
		run = new JButton("run");
		run.addActionListener(this);
		run.setActionCommand("run");
		battleSelctMenu.add(run);
		
		bottomBattle.add(battleSelctMenu);
		
		fMenu = new JPanel();
		hit = new JButton("hit bird");
		hit.addActionListener(this);
		hit.setActionCommand("hit");
		parrotClip.open(audioInputStream);
		fMenu.add(hit);
		
		bottomBattle.add(fMenu);
		fMenu.setVisible(false);
		
		
		iMenu = new JPanel();
		item1 = new JButton("use item 1");
		item1.addActionListener(this);
		item1.setActionCommand("item 1");
		iMenu.add(item1);
		item2 = new JButton("use item 2");
		item2.addActionListener(this);
		item2.setActionCommand("item 2");
		iMenu.add(item2);
		bottomBattle.add(iMenu);
		iMenu.setVisible(false);
		
		
		add(battle);
		battle.setVisible(false);
	   
	}
	
	
	public void actionPerformed(ActionEvent e){
		if("push to start".equals(e.getActionCommand())){
			enterInfo.setVisible(true);
			startScreen.setVisible(false);
		}
		
		
		if("is male".equals(e.getActionCommand())){
			gen = 'm';
		}
		
		if("is female".equals(e.getActionCommand())){
			gen = 'f';
		}
		
		
		if("begin game".equals(e.getActionCommand())){
			Character char1 = new Character(charName, gen, maj); //uppercase returns ? in front of name
			menu.setVisible(true);
			enterInfo.setVisible(false);
		}
		
		if("go to map".equals(e.getActionCommand())){
			add(map);
			menu.setVisible(false);
			map.setVisible(true);
		}
		
		if("go to menu".equals(e.getActionCommand())){
			map.setVisible(false);
			menu.setVisible(true);
		}
		
		if("start battle".equals(e.getActionCommand())){
			menu.setVisible(false);
			battle.setVisible(true);
		}
		
		if("fight".equals(e.getActionCommand())){
			battleSelctMenu.setVisible(false);
			bottomBattle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			fMenu.setVisible(true);
			
		}
		
		if("item".equals(e.getActionCommand())){
			battleSelctMenu.setVisible(false);
			bottomBattle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			iMenu.setVisible(true);
		}
		
		if("run".equals(e.getActionCommand())){
			battle.setVisible(false);
			map.setVisible(true);
		}
		
		if("go right".equals(e.getActionCommand())){
			footClip.loop(1);
			sector1.moveRight();
		}
		
		if("go left".equals(e.getActionCommand())){
			footClip.loop(1);
			sector1.moveLeft();
		}
		
		if("go up".equals(e.getActionCommand())){
			footClip.loop(1);
			sector1.moveUp();
		}
		if("go down".equals(e.getActionCommand())){
			footClip.loop(1);
			sector1.moveDown();
		}
		
		if("hit".equals(e.getActionCommand())){
			battleSelctMenu.setVisible(true);
			parrotClip.loop(1);
			bottomBattle.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
			fMenu.setVisible(false);
			
		}
		
		if("item 1".equals(e.getActionCommand())){
			battleSelctMenu.setVisible(true);
			bottomBattle.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
			iMenu.setVisible(false);
		}
		if("item 2".equals(e.getActionCommand())){
			battleSelctMenu.setVisible(true);
			bottomBattle.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
			iMenu.setVisible(false);
		}
	}
	
	public void keyPressed(KeyEvent ke) {
		KeyChar = ke.getKeyChar();
		if(KeyChar == 8) //quick fix
			charName = "";
		else
			charName += KeyChar;
		if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
			System.out.println("right");
		}
		
	}
	
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} 
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public static void CreateAndShowGUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		JFrame f1 = new JFrame("Eco-Friendly Avian Smackdown");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow w1 = new MainWindow();
		w1.setOpaque(true);
		f1.setContentPane(w1);
		
		
		f1.pack();
		f1.setSize(700,700);
        f1.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					CreateAndShowGUI();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
            }
        });
	}
	
			

	public void keyTyped(KeyEvent kt) {}
	public void keyReleased(KeyEvent arg0) {}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	
	
}