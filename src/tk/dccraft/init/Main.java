package tk.dccraft.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import tk.dccraft.Assignment_1.part_1.MealTester;
import tk.dccraft.Assignment_1.part_2.CalendarTester;
import tk.dccraft.Assignment_2.bank.SavingsAccountTester;
import tk.dccraft.http.updater.Download;
import tk.dccraft.utils.TextTransfer;
import tk.dccraft.utils.settings.PreferenceWindow;

/**
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Main implements ActionListener {

	public static JTextArea console;
	public static JFrame consoleWindow;
	private static DefaultCaret caret;
	private static JMenuBar menuBar;
	private static JMenu file, edit, assign_1, assign_2;
	private static JMenuItem pos, dob, exit, sat, update, preference;
	private static Color bg, fg;
	private static int fontSize = 12;
	private static boolean isDefaultConsole;

	public static Font font;

	// Initializes the Custom Console Window
	public static void initConsoleWindow() {
		// Initializing Frame
		consoleWindow = new JFrame("Console");
		consoleWindow.setSize(new Dimension(500, 300));
		consoleWindow.setUndecorated(true);
		consoleWindow.setLocation(50, 100);
		consoleWindow.setResizable(true);
		consoleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consoleWindow.setLayout(new FlowLayout());
		consoleWindow.setBackground(Color.BLACK);

		// Initializing UI
		// Menu Items
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		menuBar.add(file);

		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		edit.addActionListener(new Main());
		menuBar.add(edit);

		exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(new Main());
		menuBar.add(exit);

		// Assignment 1 Menu
		assign_1 = new JMenu("Assignment 1");
		file.add(assign_1);

		dob = new JMenuItem("CalendarTester");
		dob.addActionListener(new Main());
		assign_1.add(dob);

		pos = new JMenuItem("MealTester");
		pos.addActionListener(new Main());
		assign_1.add(pos);

		// Assignment 2 Menu
		assign_2 = new JMenu("Assignment 2");
		file.add(assign_2);

		sat = new JMenuItem("Savings Account Tester");
		sat.addActionListener(new Main());
		assign_2.add(sat);

		update = new JMenuItem("Update");
		update.addActionListener(new Main());
		file.add(update);

		// Working on Edit Menu
		preference = new JMenuItem("Preferences");
		preference.addActionListener(new Main());
		edit.add(preference);

		// Console
		console = new JTextArea("");
		console.setSize(new Dimension(consoleWindow.getWidth() - 200, consoleWindow.getHeight() - 50));
		console.setBackground(Color.BLACK);
		console.setForeground(Color.WHITE);
		console.setEditable(false);

		GridBagLayout gbl = new GridBagLayout();
		consoleWindow.setLayout(gbl);

		// console.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		caret = (DefaultCaret) console.getCaret();
		caret.setUpdatePolicy(2);
		scroll.setForeground(Color.LIGHT_GRAY);
		scroll.setBackground(Color.black);
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
		scrollConstraints.fill = 1;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.weightx = 1.0;
		scrollConstraints.weighty = 1.0;
		scrollConstraints.insets = new Insets(0, 5, 0, 0);
		consoleWindow.add(scroll, scrollConstraints);
		consoleWindow.setJMenuBar(menuBar);

	}

	// This will print to the custom console
	public void print(Object message) {
		if (isDefaultConsole) {
			console.append(message.toString() + "\n");
		} else {
			System.out.println(message + "\n");
		}
	}

	public static void main(String[] args) {
		if (System.console() == null) {
			initConsoleWindow();
			TextTransfer tf = new TextTransfer();
			try {
				tf.TextReader("Colors.ini", "Settings/", "style");
				setBg(new Color(Integer.decode(tf.bg)));
				setFg(new Color(Integer.decode(tf.fg)));
				setFontSize(Integer.parseInt(tf.ft));
			} catch (IOException e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(() -> {
				consoleWindow.setVisible(true);
			});
			isDefaultConsole = true;
		} else {
			isDefaultConsole = false;
			// Everything Below is for standard Command Line Launch
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (args[0].equalsIgnoreCase("dob")) {
					new CalendarTester();
				} else {
					System.out.println("Enter pos for MealTester or dob for CalendarTester.  Not " + args[0]);
				}
			} else {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter pos for MealTester or dob for CalendarTester");
				String input = sc.nextLine();
				if (input.equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (input.equalsIgnoreCase("dob")) {
					new CalendarTester();
				} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
					System.exit(0);
				} else {
					System.out.println("Enter pos for MealTester or dob for CalendarTester.  Not " + input);
				}
			}
		}
	}

	// IF NEEDED it will update the Graphics on the JFrame
	public void updateFrame(JFrame frame, Graphics g) {
		frame.update(g);
		print(frame.getTitle() + " Window Updated");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(preference)) {
			new PreferenceWindow();
		} else if (e.getSource().equals(update)) {
			new Download();
		} else if (e.getSource().equals(exit)) {
			System.exit(0);
		} else if (e.getSource().equals(dob)) {
			new CalendarTester();
		} else if (e.getSource().equals(pos)) {
			new MealTester();
		} else if (e.getSource().equals(sat)) {
			new SavingsAccountTester();
		}
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static void setFontSize(int size) {
		fontSize = size;
		font = new Font("Arial", Font.PLAIN, size);
		console.setFont(font);
	}

	public Font getFont() {
		return font;
	}

	public static Color getTitleFg() {
		int r = 0, g = 0, b = 0, scd = 110;
		if (bg.getRed() > scd)
			r = scd;
		else
			r = Color.white.getRed();
		if (bg.getGreen() > scd)
			g = scd;
		else
			g = Color.white.getGreen();
		if (bg.getBlue() > scd)
			b = scd;
		else
			b = Color.white.getBlue();
		if (b == Color.white.getBlue() && g == Color.white.getGreen() && r == Color.white.getRed())
			return new Color(r, g, b);
		else
			return new Color(bg.getRed() - r, bg.getGreen() - g, bg.getBlue() - b);
	}

	public static Color getBg() {
		return bg;
	}

	public static void setBg(Color bg) {
		Main.bg = bg;
	}

	public static Color getFg() {
		return fg;
	}

	public static void setFg(Color fg) {
		Main.fg = fg;
	}

}
