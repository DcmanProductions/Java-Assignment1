package tk.dccraft.Assignment_2.bank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tk.dccraft.init.Main;
import tk.dccraft.utils.TextTransfer;

@SuppressWarnings("all")
public class SavingsAccountTester extends JFrame implements ActionListener, FocusListener {

	// private String title = "SignUp Form";
	private JTextField nameBox, balanceBox;
	private JPanel contentPane;
	private JButton confirm, close, getBalance, getUser, list;
	private int width, height;
	private double balance;
	private String name, initNameBox = "Enter Your Name", initBalanceBox = "Enter Your Initial Deposit";
	private NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
	private Main main;
	private JLabel title;

	public static List<SavingsAccount> accounts = new ArrayList<SavingsAccount>();

	public SavingsAccountTester() {
		// accounts.add(new SavingsAccount(300, "Corey"));
		// accounts.add(new SavingsAccount(2000, "Sofia", 2.5));

		ReadFromFile();

		main = new Main();
		Color bg = main.getBg(), fg = main.getFg();

		width = 800;
		height = 280;
		Dimension size = new Dimension(width, height);

		contentPane = new JPanel();
		contentPane.setSize(width - 50, width - 50);
		contentPane.setBackground(bg);
		contentPane.setForeground(fg);
		contentPane.setLayout(null);

		title = new JLabel("Test Bank title".toUpperCase());
		title.setLocation(25, -5);
		title.setForeground(main.getTitleFg());
		title.setFont(new Font("Impact", Font.BOLD, 28));
		title.setVisible(true);
		title.setSize(width, 100);
		contentPane.add(title);

		nameBox = new JTextField(initNameBox);
		nameBox.setSize(200, 25);
		nameBox.setVisible(true);
		nameBox.setLocation(150, height / 3);
		nameBox.addActionListener(this);
		nameBox.addFocusListener(this);
		nameBox.setLayout(null);
		contentPane.add(nameBox);

		balanceBox = new JTextField(initBalanceBox);
		balanceBox.setSize(200, 25);
		balanceBox.setVisible(true);
		balanceBox.setLocation(450, height / 3);
		balanceBox.addFocusListener(this);
		balanceBox.setLayout(null);
		contentPane.add(balanceBox);

		confirm = new JButton("Send");
		confirm.setToolTipText("This sends your information to the system");
		confirm.setSize(100, 50);
		confirm.setLocation(75, height - 100);
		confirm.addActionListener(this);
		confirm.setLayout(null);
		confirm.setBackground(bg);
		confirm.setForeground(fg);
		confirm.setBorderPainted(false);
		contentPane.add(confirm);

		list = new JButton("List All Accounts");
		list.setToolTipText("This gets information from the system");
		list.setSize(150, 50);
		list.setLocation(175, height - 100);
		list.addActionListener(this);
		list.setLayout(null);
		list.setBackground(bg);
		list.setForeground(fg);
		list.setBorderPainted(false);
		contentPane.add(list);

		getBalance = new JButton("Check Balance");
		getBalance.setToolTipText("Checks and Prints the Balance from a users profile");
		getBalance.setSize(150, 50);
		getBalance.setLocation(175 + 150, height - 100);
		getBalance.addActionListener(this);
		getBalance.setLayout(null);
		getBalance.setBackground(bg);
		getBalance.setForeground(fg);
		getBalance.setBorderPainted(false);
		contentPane.add(getBalance);

		getUser = new JButton("Check User");
		getUser.setToolTipText("Checks and Prints the Name of the person with that balance");
		getUser.setSize(100, 50);
		getUser.setLocation(175 + 150 + 150, height - 100);
		getUser.addActionListener(this);
		getUser.setBackground(bg);
		getUser.setForeground(fg);
		getUser.setBorderPainted(false);
		contentPane.add(getUser);

		close = new JButton("Close");
		close.setToolTipText("This Closes the window, but keeps the console open.");
		close.setSize(100, 50);
		close.setLocation(175 + 150 + 150 + 100, height - 100);
		close.addActionListener(this);
		close.setLayout(null);
		close.setBackground(bg);
		close.setForeground(fg);
		close.setBorderPainted(false);
		contentPane.add(close);

		setTitle("SignUp Form");
		setUndecorated(true);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(size);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(contentPane);
		main.updateFrame(this, getGraphics());

	}

	public boolean test(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			main.print("ERROR: Can't parse the text \"" + text + "\" as a double");
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean okBalance = false, okName = false;
		if (e.getSource().equals(list)) {
			main.print("Accounts: \n+-------------------------+");
			for (int i = 0; i < accounts.size(); i++) {
				main.print("+--Holder: " + accounts.get(i).getHolder() + "\n+-------- Balance of: " + moneyFormatter.format(accounts.get(i).getBalance()) + "\n+-------------------------+");
			}
		} else if (e.getSource().equals(getBalance)) {
			if (nameBox.getText() != initNameBox) {
				for (int i = 0; i < accounts.size(); i++) {
					if (accounts.get(i).getHolder().equalsIgnoreCase(nameBox.getText())) {
						main.print(accounts.get(i).getHolder() + " has a balance of " + moneyFormatter.format(accounts.get(i).getBalance()));
					}
				}
			} else {
				main.print("You need to type a username for this action!!!");
			}
		} else if (e.getSource().equals(getUser)) {
			if (balanceBox.getText() != initBalanceBox) {
				for (int i = 0; i < accounts.size(); i++) {
					if (accounts.get(i).getBalance() == Double.parseDouble(balanceBox.getText())) {
						main.print(accounts.get(i).getHolder() + " has a balance of " + accounts.get(i).getBalance() + ".  It's Creepy you know that!");
					}
				}
			} else {
				main.print("You need to type a username for this action!!!");
			}
		} else if (e.getSource().equals(confirm)) {
			if (nameBox.getText().equals(initNameBox)) {
				name = "";
				okName = false;
				main.print("You at least need to type a name");
			} else if (!nameBox.getText().equals("")) {
				name = nameBox.getText();
				okName = true;
			}
			if (balanceBox.getText().equals(initBalanceBox)) {
				balance = 0.00;
				okBalance = true;
				main.print("The Initial Deposit was empty so marking it as $0.00");
			} else if (!balanceBox.getText().equals("")) {
				balance = Double.parseDouble(balanceBox.getText());
				okBalance = true;
			} else if (balance > Double.MAX_VALUE) {
				main.print("Whooah, I'm sorry you make too much money we are going to have to refer you to another bank!");
				okBalance = false;
			}
			if (okBalance && okName) {
				accounts.add(new SavingsAccount(balance, name));
				main.print("Account created for " + name + " with a balance of " + moneyFormatter.format(balance));
			}
		} else if (e.getSource().equals(close)) {
			SaveToFile();
			dispose();
		}
	}

	public void SaveToFile() {
		List<String> content = new ArrayList<String>();
		TextTransfer tf = new TextTransfer();
		for (int i = 0; i < accounts.size(); i++) {
			main.print("Count = " + i);
			String holder = accounts.get(i).getHolder();
			content.add("Name" + i + ":" + holder + "\nBalance" + i + ":" + accounts.get(i).getBalance());
		}
		String finalContent = content.toString().replace("[", "").replace("]", "").replace(", ", "\n");
		main.print(finalContent);
		accounts.clear();
		try {
			tf.TextWriter("bankinfo.dat", finalContent, "DataBase/", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ReadFromFile() {
		TextTransfer tf = new TextTransfer();
		try {
			tf.TextReader("bankinfo.dat", "DataBase/", "bank");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(nameBox)) {
			if (nameBox.getText().equals("Enter Your Name"))
				nameBox.setText("");
		} else if (e.getSource().equals(balanceBox)) {
			if (balanceBox.getText().equals("Enter Your Initial Deposit"))
				balanceBox.setText("");
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(nameBox)) {
			if (nameBox.getText().equals(""))
				nameBox.setText(initNameBox);
		} else if (e.getSource().equals(balanceBox)) {
			if (balanceBox.getText().equals("") || !test(balanceBox.getText()))
				balanceBox.setText(initBalanceBox);
		}
	}

}
