

import java.awt.*;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList; 

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Menu extends JFrame{
	
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
	private ArrayList<Admin> adminList = new ArrayList<Admin>();
	private ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
    private int position = 0;
	private String StrPassword;
	private Customer customer = null;
	private CustomerAccount acc = new CustomerAccount();
	private CustomerAccount customerAccount = new CustomerAccount();
	private JFrame frame, frame1;
	private JLabel fName, sName, pps, dob, customerId, password, username;
	private JTextField fNameTxt, sNameTxt, ppsTxt, dobTxt, customerIdTxt, passwordTxt, usernameTxt;	
	private Container content;
	private Customer cust;
	private CustomerCurrentAccount customerCurrentAccount;
	private CustomerDepositAccount customerDepositAccount;
	private Admin admin;
	private JPanel panel, panel2;
	private JButton add, cancel;
	private String PPS,firstName,surname,DOB,CustomerID;
	private File customerFile= new File("customers.txt");
	private boolean loop = true;
	private boolean found = false;
	private double AccountBalance = 0;
	private String euro = "€";
	private double balance = 0;
	
	public static void main(String[] args)
	{
		Menu driver = new Menu();
		driver.menuStart();
	}
	
	public void menuStart()
	{
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/
		
			
		
			
			frame = new JFrame("User Type");
			frame.setSize(400, 300);
			frame.setLocation(200, 200);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});

			JPanel userTypePanel = new JPanel();
			final ButtonGroup userType = new ButtonGroup();
			JRadioButton radioButton;
			userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
			radioButton.setActionCommand("Customer");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("Administrator"));
			radioButton.setActionCommand("Administrator");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("New Customer"));
			radioButton.setActionCommand("New Customer");
			userType.add(radioButton);

			JPanel continuePanel = new JPanel();
			JButton continueButton = new JButton("Continue");
			continuePanel.add(continueButton);

			Container content = frame.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(userTypePanel);
			content.add(continuePanel);



			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					String user = userType.getSelection().getActionCommand(  );
					
					//if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
					if(user.equals("New Customer"))
					{		
						newCustomer();
					}										
					//if user select ADMIN----------------------------------------------------------------------------------------------
					if(user.equals("Administrator")	)
					{
						adminLogin();
					}
					//if user selects CUSTOMER ---------------------------------------------------------------------------------------- 
					if(user.equals("Customer")	)
					{
						customerLogin();
					}
					
					if(user.equals("New Admin")	)
					{
						newAdmin();
						
					}
				}
			});frame.setVisible(true);	
	}
	
	//Adds user details to the file
		public void addUserToFile() {
			File fileName = new File("customers1.txt");
		    try{
		        FileWriter fw = new FileWriter(fileName, true);
		        Writer writer = new BufferedWriter(fw);
		        int entry = customerList.size();
		        for (int i = 0; i < entry; i++) {
		        	writer.write(customerList.get(i).toString() + "\n"); 
		        }
		        writer.close();
		    }
		    catch(Exception exception) {
		        JOptionPane.showMessageDialog(null,"File cannot be created");
		    }
		}
		
		//Reads the information from the file
		public void readUserFile() {
			
	        try {
	            Scanner input = new Scanner(new FileReader("customers1.txt"));
	            
	            while(input.hasNextLine()) {
	            	String line = input.nextLine();
	            	if(line.contains("PPS number")) {
	            		System.out.println(line);
	            	}
	            	if(line.contains("Surname")) {
	            		System.out.println(line);
	            	}
	            	if(line.contains("First Name")) {
	            		System.out.println(line);
	            	}
	            	if(line.contains("Date of Birth")) {
	            		System.out.println(line);
	            	}
	            	if(line.contains("Customer ID")) {
	            		System.out.println(line);
	            	}
	            	if(line.contains("Password")) {
	            		System.out.println(line);
	            	}
	            }
	           
	        } catch (IOException e) {
	           
	        }
	      
		}
	
	//Adds panel information
		public void addPanelInfo() {
			panel2.add(add);
			panel2.add(cancel);
			content.add(panel, BorderLayout.CENTER);
			content.add(panel2, BorderLayout.SOUTH);

			frame1.setVisible(true);
		}
		
		//Frame size
		public void frameSize() {
			frame.setSize(400, 300);
			frame.setLocation(200, 200);
		}
		
		public void windowListener() {
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});
		}
	
	//Creates a new customer	
		public void newCustomer() {
			
			frame.dispose();		
			frame1 = new JFrame("Create New Customer");
			frameSize();
			windowListener();
			
			Container content = frame1.getContentPane();
			content.setLayout(new BorderLayout());
				
			fName = new JLabel("First Name:", SwingConstants.RIGHT);
			sName = new JLabel("Surname:", SwingConstants.RIGHT);
			pps = new JLabel("PPS Number:", SwingConstants.RIGHT);
			dob = new JLabel("Date of birth", SwingConstants.RIGHT);
			fNameTxt = new JTextField(20);
			sNameTxt = new JTextField(20);
			ppsTxt = new JTextField();
			dobTxt = new JTextField();
			
			panel = new JPanel(new GridLayout(6, 2));
			panel.add(fName);
			panel.add(fNameTxt);
			panel.add(sName);
			panel.add(sNameTxt);
			panel.add(pps);
			panel.add(ppsTxt);
			panel.add(dob);
			panel.add(dobTxt);
					
			panel2 = new JPanel();
			add = new JButton("Add");
			cancel = new JButton("Cancel");
				
			add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
				}
			});
				
			cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
				}
			});
				
				addPanelInfo();	
			
		}
		
		//Adds customer to the arraylist
		public void addCustomer() {
			
			String PPS = ppsTxt.getText();
			String firstName = fNameTxt.getText();
			String surname = sNameTxt.getText();
			String DOB = dobTxt.getText();
			StrPassword =  passwordCheck();

			String CustomerID = "ID"+PPS ;
			
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame1.dispose();
				    ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
					customer = new Customer(PPS, surname, firstName, DOB, CustomerID, StrPassword, accounts);
					//Ensures that the accounts are unique
					if(customerList.contains(CustomerID))
					{
						JOptionPane.showMessageDialog(frame, "Account already exists",  CustomerID, JOptionPane.INFORMATION_MESSAGE);
						menuStart();
						frame.dispose();
					
					}else {
						
						customerList.add(customer);
						JOptionPane.showMessageDialog(frame, "CustomerID = " + CustomerID +"\n Password = " + StrPassword  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
						addUserToFile();
						menuStart();
						frame.dispose();
					}
									
					
				}
			});	
		}
		
		//Creates a new admin
		public void newAdmin() {
			
			frame.dispose();		
			frame1 = new JFrame("Create New Admin");
			frameSize();
			windowListener();
			
			Container content = frame1.getContentPane();
			content.setLayout(new BorderLayout());
				
			username = new JLabel("Username:", SwingConstants.RIGHT);
			usernameTxt = new JTextField(20);
			JPanel panel = new JPanel(new GridLayout(6, 2));
			panel.add(username);
			panel.add(usernameTxt);
					
			panel2 = new JPanel();
			add = new JButton("Add");
			cancel = new JButton("Cancel");
				
				add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addAdmin();
					}
				});
				
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				
				addPanelInfo();
			
			
		}
		
		//Adds admin to the arraylist
		public void addAdmin() {
			
			String username = usernameTxt.getText();
			StrPassword = passwordCheck();
			
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame1.dispose();			
					admin = new Admin(username, StrPassword);
					if(adminList.contains(username))
					{
						JOptionPane.showMessageDialog(frame, "Account already exists",  username, JOptionPane.INFORMATION_MESSAGE);
						menuStart();
						frame.dispose();
					
					}else {
						
						adminList.add(admin);
						JOptionPane.showMessageDialog(frame, "Username = " + username +"\n StrPassword = " + StrPassword  ,"Admin created.",  JOptionPane.INFORMATION_MESSAGE);
						addUserToFile();
						admin();
						frame.dispose();
					}
					
				}
			});	
		}

		
		public void cancel() {
			
			frame1.dispose();
			menuStart();
			
		}
		
		//Checks password to ensure it fits criteria
		private String passwordCheck() {
			String StrPassword = "";
			boolean loop = true;
			while(loop){
			 StrPassword = JOptionPane.showInputDialog(frame, "Enter 7 character StrPassword;");
			
			 if(StrPassword.length() != 7)//Making sure StrPassword is 7 characters
			    {
			    	JOptionPane.showMessageDialog(null, null, "StrPassword must be 7 charatcers long", JOptionPane.OK_OPTION);
			    }
			 else
			 {
				 loop = false;
			 }
			 
			}
			return StrPassword;
			
		}

		
		//Allows  admin to login with their username
		public void adminLogin() {
			
			boolean loop = true, loop2 = true;
			boolean cont = false;
		    while(loop)
		    {
		    Object adminUsername = JOptionPane.showInputDialog(frame, "Enter Administrator Username:");

		    if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
		    {
		    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
		    	if (reply == JOptionPane.YES_OPTION) {
		    		loop = true;
		    	}
		    	else if(reply == JOptionPane.NO_OPTION)
		    	{
		    		frame1.dispose();
		    		loop = false;
		    		loop2 = false;
		    		menuStart();
		    	}
		    }
		    else
		    {
		    	loop = false;
		    }				    
		    }
		    
		    while(loop2)
		    {
		    	Object adminPassword = JOptionPane.showInputDialog(frame, "Enter Administrator StrPassword;");
		    	
		    	   if(!adminPassword.equals("admin11"))//search admin list for admin with matching admin StrPassword
				    {
				    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect StrPassword. Try again?", JOptionPane.YES_NO_OPTION);
				    	if (reply == JOptionPane.YES_OPTION) {
				    		
				    	}
				    	else if(reply == JOptionPane.NO_OPTION){
				    		frame1.dispose();
				    		loop2 = false;
				    		menuStart();
				    	}
				    }
		    	   else
		    	   {
		    		   loop2 =false;
		    		   cont = true;
		    	   }
		    }
		    	
		    if(cont)
		    {
			frame1.dispose();
		    loop = false;
		    admin();					    
		    }					    
		
			
		}
		
		//Allows customer to login
		public void customerLogin() {
			
			boolean loop = true, loop2 = true;
			boolean cont = false;
			boolean found = false;
			Customer customer = null;
		    while(loop)
		    {
		    Object customerID = JOptionPane.showInputDialog(frame, "Enter Customer ID:");
		    
		    for (Customer aCustomer: customerList){
		    	
		    	if(aCustomer.getCustomerID().equals(customerID))//search customer list for matching customer ID
		    	{
		    		found = true;
		    		customer = aCustomer;
		    	}					    	
		    }
		    
		    if(found == false)
		    {
		    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
		    	if (reply == JOptionPane.YES_OPTION) {
		    		loop = true;
		    	}
		    	else if(reply == JOptionPane.NO_OPTION)
		    	{
		    		frame.dispose();
		    		loop = false;
		    		loop2 = false;
		    		menuStart();
		    	}
		    }
		    else
		    {
		    	loop = false;
		    }
		   
		    }
		    
		    while(loop2)
		    {
		    	Object customerPassword = JOptionPane.showInputDialog(frame, "Enter Customer StrPassword;");
		    	
		    	   if(!customer.getPassword().equals(customerPassword))//check if custoemr StrPassword is correct
				    {
				    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect StrPassword. Try again?", JOptionPane.YES_NO_OPTION);
				    	if (reply == JOptionPane.YES_OPTION) {
				    		
				    	}
				    	else if(reply == JOptionPane.NO_OPTION){
				    		frame.dispose();
				    		loop2 = false;
				    		menuStart();
				    	}
				    }
		    	   else
		    	   {
		    		   loop2 =false;
		    		   cont = true;
		    	   }
		    }
		    	
		    if(cont)
		    {
			frame.dispose();
		    	loop = false;
		    	customer(customer);				    
		    }				    	
		}
	
	public void admin()
	{
		dispose();
		
		frame = new JFrame("Administrator Menu");
		frame.setSize(400, 400);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		frame.setVisible(true);
		
		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");	
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);
		
		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));	
		deleteAccountPanel.add(deleteAccount);
		
		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));	
		bankChargesPanel.add(bankChargesButton);
		
		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");
		
		content = frame.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);	
		content.add(deleteCustomerPanel);
	//	content.add(deleteAccountPanel);
		content.add(returnPanel);
		
		
		bankChargesButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customerInformation();
			    
			    if(found == false){
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
			    if (reply == JOptionPane.YES_OPTION){
			    		loop = true;
			    }
			    else if(reply == JOptionPane.NO_OPTION){
			    	frame.dispose();
			    	loop = false;
			    	admin();
			    	}
			    }  
			    else
			    {
			    	frame.dispose();
			    	frame = new JFrame("Administrator Menu");
			    	frameSize();
					windowListener();   
					frame.setVisible(true);
				
				    JComboBox<String> box = new JComboBox<String>();
				    for (int i =0; i < customer.getAccounts().size(); i++)
				    {	
				     box.addItem(customer.getAccounts().get(i).getNumber());
				    }
					
				    box.getSelectedItem();
				
				    JPanel boxPanel = new JPanel();
					boxPanel.add(box);
					
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Charge");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = frame.getContentPane();
					content.setLayout(new GridLayout(2, 1));
					
					content.add(boxPanel);
					content.add(buttonPanel);
					
			
					if(customer.getAccounts().isEmpty()){
							JOptionPane.showMessageDialog(frame, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
							admin();
					}
					else{
						
					for(int i = 0; i < customer.getAccounts().size(); i++){
				    	if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem()){
				    		customerAccount = customer.getAccounts().get(i);
				    	}
				    }
										
					continueButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							String euro = "€";
				
							if(customerAccount instanceof CustomerDepositAccount){
						
							JOptionPane.showMessageDialog(frame, "25" + euro + " deposit account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
							customerAccount.setBalance(customerAccount.getBalance()-25);
							JOptionPane.showMessageDialog(frame, "New balance = " + customerAccount.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
							}

							if(customerAccount instanceof CustomerCurrentAccount){
							JOptionPane.showMessageDialog(frame, "15" + euro + " current account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
							customerAccount.setBalance(customerAccount.getBalance()-25);
							JOptionPane.showMessageDialog(frame, "New balance = " + customerAccount.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
							}
							frame.dispose();				
							admin();				
						}		
				     });
					
					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							frame.dispose();		
							menuStart();				
										}
				     				});	
								}
			    			}
			    		}
			    	});
		
		interestButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {

				boolean loop = true;
				
				boolean found = false;
			
				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					admin();
					
				}
				else
				{
			    while(loop)
			    {
			    Object customerID = JOptionPane.showInputDialog(frame, "Customer ID of Customer You Wish to Apply Interest to:");
			    
			    for (Customer aCustomer: customerList){
			    	
			    	if(aCustomer.getCustomerID().equals(customerID))
			    	{
			    		found = true;
			    		customer = aCustomer; 
			    		loop = false;
			    	}					    	
			    }
			    
			    if(found == false)
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		loop = true;
			    	}
			    	else if(reply == JOptionPane.NO_OPTION)
			    	{
			    		frame.dispose();
			    		loop = false;
			    	
			    		admin();
			    	}
			    }  
			    else
			    {
			    	frame.dispose();
			    	frame = new JFrame("Administrator Menu");
					frame.setSize(400, 300);
					frame.setLocation(200, 200);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { System.exit(0); }
					});          
					frame.setVisible(true);
				
				
				    JComboBox<String> box = new JComboBox<String>();
				    for (int i =0; i < customer.getAccounts().size(); i++)
				    {
				    	
				    	
				     box.addItem(customer.getAccounts().get(i).getNumber());
				    }
					
				    
				    box.getSelectedItem();
				
				    JPanel boxPanel = new JPanel();
					
					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = frame.getContentPane();
					content.setLayout(new GridLayout(2, 1));
					
					content.add(boxPanel);
					content.add(buttonPanel);
					
			
						if(customer.getAccounts().isEmpty())
						{
							JOptionPane.showMessageDialog(frame, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
							admin();
						}
						else
						{
						
					for(int i = 0; i < customer.getAccounts().size(); i++)
				    {
				    	if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
				    	{
				    		acc = customer.getAccounts().get(i);
				    	}
				    }
										
					continueButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							String euro = "\u20ac";
						 	double interest = 0;
						 	boolean loop = true;
						 	
						 	while(loop)
						 	{
							String interestString = JOptionPane.showInputDialog(frame, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n cust.g: If you wish to apply 8% interest, enter '8'");//the isNumeric method tests to see if the string entered was numeric. 
							if(isNumeric(interestString))
							{
								
								interest = Double.parseDouble(interestString);
								loop = false;
								
								acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest/100)));
								
								JOptionPane.showMessageDialog(frame, interest + "% interest applied. \n new balance = " + acc.getBalance() + euro ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
							}
								
							
							else
							{
								JOptionPane.showMessageDialog(frame, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							}
							
							
						 	}
							
							frame.dispose();				
						admin();				
						}		
				     });
					
					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							frame.dispose();		
							menuStart();				
						}
				     });	
					
						}
			    }
			    }
			    }
			    
			}	
	     });
		
		editCustomerButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				
				boolean loop = true;
			
				boolean found = false;
			
				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					admin();
					
				}
				else
				{
				
			    while(loop)
			    {
			    Object customerID = JOptionPane.showInputDialog(frame, "Enter Customer ID:");
			    
			    for (Customer aCustomer: customerList){
			    	
			    	if(aCustomer.getCustomerID().equals(customerID))
			    	{
			    		found = true;
			    		customer = aCustomer;
			    	}					    	
			    }
			    
			    if(found == false)
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		loop = true;
			    	}
			    	else if(reply == JOptionPane.NO_OPTION)
			    	{
			    		frame.dispose();
			    		loop = false;
			    	
			    		admin();
			    	}
			    }
			    else
			    {
			    	loop = false;
			    }
			   
			    }
				
				frame.dispose();
				
				frame.dispose();
				frame = new JFrame("Administrator Menu");
				frame.setSize(400, 300);
				frame.setLocation(200, 200);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});       
				
				fName = new JLabel("First Name:", SwingConstants.LEFT);
				sName = new JLabel("Surname:", SwingConstants.LEFT);
				pps = new JLabel("PPS Number:", SwingConstants.LEFT);
				dob = new JLabel("Date of birth", SwingConstants.LEFT);
				customerId = new JLabel("CustomerID:", SwingConstants.LEFT);
				password = new JLabel("StrPassword:", SwingConstants.LEFT);
				fNameTxt = new JTextField(20);
				sNameTxt = new JTextField(20);
				ppsTxt = new JTextField(20);
				dobTxt = new JTextField(20);
				customerIdTxt = new JTextField(20);
				passwordTxt = new JTextField(20);
				
				JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
				JPanel cancelPanel = new JPanel();
				
				textPanel.add(fName);
				textPanel.add(fNameTxt);
				textPanel.add(sName);
				textPanel.add(sNameTxt);
				textPanel.add(pps);
				textPanel.add(ppsTxt);
				textPanel.add(dob);
				textPanel.add(dobTxt);
				textPanel.add(customerId);
				textPanel.add(customerIdTxt);
				textPanel.add(password);
				textPanel.add(passwordTxt);

				fNameTxt.setText(customer.getFirstName());
				sNameTxt.setText(customer.getSurname());
				ppsTxt.setText(customer.getPPS());
				dobTxt.setText(customer.getDOB());
				customerIdTxt.setText(customer.getCustomerID());
				passwordTxt.setText(customer.getPassword());	
				
				//JLabel label1 = new JLabel("Edit customer details below. The save");
				
			
				JButton saveButton = new JButton("Save");
				JButton cancelButton = new JButton("Exit");
				
				cancelPanel.add(cancelButton, BorderLayout.SOUTH);
				cancelPanel.add(saveButton, BorderLayout.SOUTH);
			//	content.removeAll();
				Container content = frame.getContentPane();
				content.setLayout(new GridLayout(2, 1));
				content.add(textPanel, BorderLayout.NORTH);
				content.add(cancelPanel, BorderLayout.SOUTH);
				
				frame.setContentPane(content);
				frame.setSize(340, 350);
				frame.setLocation(200, 200);
				frame.setVisible(true);
				frame.setResizable(false);
				
				saveButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
					
						customer.setFirstName(fNameTxt.getText());
						customer.setSurname(sNameTxt.getText());
						customer.setPPS(ppsTxt.getText());
						customer.setDOB(dobTxt.getText());
						customer.setCustomerID(customerIdTxt.getText());
						customer.setPassword(passwordTxt.getText());		
						
						JOptionPane.showMessageDialog(null, "Changes Saved.");
							}		
					     });
				
				cancelButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						frame.dispose();
						
						admin();				
					}		
			     });		
				}}
	     });
		
		summaryButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				
				
				frame = new JFrame("Summary of Transactions");
				frame.setSize(400, 700);
				frame.setLocation(200, 200);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});          
				frame.setVisible(true);
				
				JLabel label1 = new JLabel("Summary of all transactions: ");
				
				JPanel returnPanel = new JPanel();
				JButton returnButton = new JButton("Return");
				returnPanel.add(returnButton);
				
				JPanel textPanel = new JPanel();
				
				textPanel.setLayout( new BorderLayout() );
				JTextArea textArea = new JTextArea(40, 20);
				textArea.setEditable(false);
				textPanel.add(label1, BorderLayout.NORTH);
				textPanel.add(textArea, BorderLayout.CENTER);
				textPanel.add(returnButton, BorderLayout.SOUTH);
				
				JScrollPane scrollPane = new JScrollPane(textArea);
				textPanel.add(scrollPane);
				
			for (int a = 0; a < customerList.size(); a++)//For each customer, for each account, it displays each transaction.
				{
					for (int b = 0; b < customerList.get(a).getAccounts().size(); b ++ )
					{
						acc = customerList.get(a).getAccounts().get(b);
						for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++)
						{
							
							textArea.append(acc.getTransactionList().get(c).toString());
							//Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use this to keep a running total but I couldnt get it  working.
							
						}				
					}				
				}
				
				
				
				
				textPanel.add(textArea);
				content.removeAll();
				
				
				Container content = frame.getContentPane();
				content.setLayout(new GridLayout(1, 1));
			//	content.add(label1);
				content.add(textPanel);
				//content.add(returnPanel);
				
				returnButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						frame.dispose();			
					admin();				
					}		
			     });	
			}	
	     });
		
		navigateButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				
				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					admin();
				}
				else
				{
		
				JButton first, previous, next, last, cancel;
				JPanel gridPanel, buttonPanel, cancelPanel;			
	
				Container content = getContentPane();
				
				content.setLayout(new BorderLayout());
				
				buttonPanel = new JPanel();
				gridPanel = new JPanel(new GridLayout(8, 2));
				cancelPanel = new JPanel();
								
				fName = new JLabel("First Name:", SwingConstants.LEFT);
				sName = new JLabel("Surname:", SwingConstants.LEFT);
				pps = new JLabel("PPS Number:", SwingConstants.LEFT);
				dob = new JLabel("Date of birth", SwingConstants.LEFT);
				customerId = new JLabel("CustomerID:", SwingConstants.LEFT);
				password = new JLabel("StrPassword:", SwingConstants.LEFT);
				fNameTxt = new JTextField(20);
				sNameTxt = new JTextField(20);
				ppsTxt = new JTextField(20);
				dobTxt = new JTextField(20);
				customerIdTxt = new JTextField(20);
				passwordTxt = new JTextField(20);
				
				first = new JButton("First");
				previous = new JButton("Previous");
				next = new JButton("Next");
				last = new JButton("Last");
				cancel = new JButton("Cancel");
				
				fNameTxt.setText(customerList.get(0).getFirstName());
				sNameTxt.setText(customerList.get(0).getSurname());
				ppsTxt.setText(customerList.get(0).getPPS());
				dobTxt.setText(customerList.get(0).getDOB());
				customerIdTxt.setText(customerList.get(0).getCustomerID());
				passwordTxt.setText(customerList.get(0).getPassword());
				
				fNameTxt.setEditable(false);
				sNameTxt.setEditable(false);
				ppsTxt.setEditable(false);
				dobTxt.setEditable(false);
				customerIdTxt.setEditable(false);
				passwordTxt.setEditable(false);
				
				gridPanel.add(fName);
				gridPanel.add(fNameTxt);
				gridPanel.add(sName);
				gridPanel.add(sNameTxt);
				gridPanel.add(pps);
				gridPanel.add(ppsTxt);
				gridPanel.add(dob);
				gridPanel.add(dobTxt);
				gridPanel.add(customerId);
				gridPanel.add(customerIdTxt);
				gridPanel.add(password);
				gridPanel.add(passwordTxt);
				
				buttonPanel.add(first);
				buttonPanel.add(previous);
				buttonPanel.add(next);
				buttonPanel.add(last);
				
				cancelPanel.add(cancel);
		
				content.add(gridPanel, BorderLayout.NORTH);
				content.add(buttonPanel, BorderLayout.CENTER);
				content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
				first.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						position = 0;
						fNameTxt.setText(customerList.get(0).getFirstName());
						sNameTxt.setText(customerList.get(0).getSurname());
						ppsTxt.setText(customerList.get(0).getPPS());
						dobTxt.setText(customerList.get(0).getDOB());
						customerIdTxt.setText(customerList.get(0).getCustomerID());
						passwordTxt.setText(customerList.get(0).getPassword());				
							}		
					     });
				
				previous.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
								
						if(position < 1)
						{
							//don't do anything
						}
						else
						{
							position = position - 1;
							
						fNameTxt.setText(customerList.get(position).getFirstName());
						sNameTxt.setText(customerList.get(position).getSurname());
						ppsTxt.setText(customerList.get(position).getPPS());
						dobTxt.setText(customerList.get(position).getDOB());
						customerIdTxt.setText(customerList.get(position).getCustomerID());
						passwordTxt.setText(customerList.get(position).getPassword());
						}			
							}		
					     });
				
				next.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
					
						if(position == customerList.size()-1)
						{
							//don't do anything
						}
						else
						{
							position = position + 1;
							
						fNameTxt.setText(customerList.get(position).getFirstName());
						sNameTxt.setText(customerList.get(position).getSurname());
						ppsTxt.setText(customerList.get(position).getPPS());
						dobTxt.setText(customerList.get(position).getDOB());
						customerIdTxt.setText(customerList.get(position).getCustomerID());
						passwordTxt.setText(customerList.get(position).getPassword());
						}		
						
						
												
							}		
					     });
				
				last.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
					
						position = customerList.size() - 1;
				
						fNameTxt.setText(customerList.get(position).getFirstName());
						sNameTxt.setText(customerList.get(position).getSurname());
						ppsTxt.setText(customerList.get(position).getPPS());
						dobTxt.setText(customerList.get(position).getDOB());
						customerIdTxt.setText(customerList.get(position).getCustomerID());
						passwordTxt.setText(customerList.get(position).getPassword());								
							}		
					     });
				
				cancel.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {				
						dispose();
						admin();
							}		
					     });			
				setContentPane(content);
				setSize(400, 300);
			       setVisible(true);
					}		
			}
		});
		
		accountButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				
				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					admin();
				}
				else
				{
				boolean loop = true;
				
				boolean found = false;
			
			    while(loop)
			    {
			    Object customerID = JOptionPane.showInputDialog(frame, "Customer ID of Customer You Wish to Add an Account to:");
			    
			    for (Customer aCustomer: customerList){
			    	
			    	if(aCustomer.getCustomerID().equals(customerID))
			    	{
			    		found = true;
			    		customer = aCustomer; 	
			    	}					    	
			    }
			    
			    if(found == false)
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		loop = true;
			    	}
			    	else if(reply == JOptionPane.NO_OPTION)
			    	{
			    		frame.dispose();
			    		loop = false;
			    	
			    		admin();
			    	}
			    }
			    else
			    {
			    	loop = false;
			    	//a combo box in an dialog box that asks the admin what type of account they wish to create (deposit/current)
				    String[] choices = { "Current Account", "Deposit Account" };
				    String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
				        "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); 
				    
				    if(account.equals("Current Account"))
				    {
				    	//create current account
				    	boolean valid = true;
				    	double balance = 0;
				    	String number = String.valueOf("C" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
				    	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
				    	int randomPIN = (int)(Math.random()*9000)+1000;
				           String pin = String.valueOf(randomPIN);
				    
				           ATMCard atm = new ATMCard(randomPIN, valid);
				    	
				    	CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
				    	
				    	customer.getAccounts().add(current);
				    	JOptionPane.showMessageDialog(frame, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
				    	
				    	frame.dispose();
				    	admin();
				    }
				    
				    if(account.equals("Deposit Account"))
				    {
				    	//create deposit account
				    	
				    	double balance = 0, interest = 0;
				    	String number = String.valueOf("D" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
				    	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
				        	
				    	CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
				    	
				    	customer.getAccounts().add(deposit);
				    	JOptionPane.showMessageDialog(frame, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
				    	
				    	frame.dispose();
				    	admin();
				    }
			    
			    }			   
			    }
				}
			}
	     });		

		deleteCustomer.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean found = true, loop = true;
				
				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					dispose();
					admin();
				}
				else
				{
					 {
						    Object customerID = JOptionPane.showInputDialog(frame, "Customer ID of Customer You Wish to Delete:");
						    
						    for (Customer aCustomer: customerList){
						    	
						    	if(aCustomer.getCustomerID().equals(customerID))
						    	{
						    		found = true;
						    		customer = aCustomer; 
						    		loop = false;
						    	}					    	
						    }
						    
						    if(found == false)
						    {
						    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						    	if (reply == JOptionPane.YES_OPTION) {
						    		loop = true;
						    	}
						    	else if(reply == JOptionPane.NO_OPTION)
						    	{
						    		frame.dispose();
						    		loop = false;
						    		
						    		admin();
						    	}
						    }  
						    else
						    {
						    	if(customer.getAccounts().size()>0)
						    	{
						    		JOptionPane.showMessageDialog(frame, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						    	}
						    	else
						    	{
						    		customerList.remove(customer);
						    		JOptionPane.showMessageDialog(frame, "Customer Deleted " ,"Success.",  JOptionPane.INFORMATION_MESSAGE);
						    	}
						    }
						    
						    
				}}
			}
	     });		
		
		deleteAccount.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
	boolean found = true, loop = true;
				
				
				
				
					 {
						    Object customerID = JOptionPane.showInputDialog(frame, "Customer ID of Customer from which you wish to delete an account");
						    
						    for (Customer aCustomer: customerList){
						    	
						    	if(aCustomer.getCustomerID().equals(customerID))
						    	{
						    		found = true;
						    		customer = aCustomer; 
						    		loop = false;
						    	}					    	
						    }
						    
						    if(found == false)
						    {
						    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						    	if (reply == JOptionPane.YES_OPTION) {
						    		loop = true;
						    	}
						    	else if(reply == JOptionPane.NO_OPTION)
						    	{
						    		frame.dispose();
						    		loop = false;
						    	
						    		admin();
						    	}
						    }  
						    else
						    {
						    	//Here I would make the user select a an account to delete from a combo box. If the account had a balance of 0 then it would be deleted. (I do not have time to do this)
						    }
						    
						    
				}}
			
	     });		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				menuStart();				
			}
	     });		
	}
	
public void customerInformation() {
		
		readUserFile();
	
		if(customerList.isEmpty())
		{
			JOptionPane.showMessageDialog(frame, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
			admin();
			
		}
		else
		{
		
	    while(loop)
	    {
	    Object customerID = JOptionPane.showInputDialog(frame, "Enter Customer ID:");
	    
	    for (Customer aCustomer: customerList){
	    	
	    	if(aCustomer.getCustomerID().equals(customerID))
	    	{
	    		found = true;
	    		customer = aCustomer;
	    			}					    	
	    		}
	    	}
		}
	}

	public void customer(Customer e1)
	{	
		frame = new JFrame("Customer Menu");
		e1 = cust;
		frame.setSize(400, 300);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		frame.setVisible(true);
		
		if(cust.getAccounts().size() == 0)
		{
			JOptionPane.showMessageDialog(frame, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();				
			menuStart();
		}
		else
		{
		JPanel buttonPanel = new JPanel();
		JPanel boxPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		
		JLabel label = new JLabel("Select Account:");
		labelPanel.add(label);
		
		JButton returnButton = new JButton("Return");
		buttonPanel.add(returnButton);
		JButton continueButton = new JButton("Continue");
		buttonPanel.add(continueButton);
		
		JComboBox<String> box = new JComboBox<String>();
	    for (int i =0; i < cust.getAccounts().size(); i++)
	    {
	     box.addItem(cust.getAccounts().get(i).getNumber());
	    }
		
	    
	   
	    for(int i = 0; i<cust.getAccounts().size(); i++)
	    {
	    	if(cust.getAccounts().get(i).getNumber() == box.getSelectedItem() )
	    	{
	    		acc = cust.getAccounts().get(i);
	    	}
	    }
	    
	    
	    
	
	    
		boxPanel.add(box);
		content = frame.getContentPane();
		content.setLayout(new GridLayout(3, 1));
		content.add(labelPanel);
		content.add(boxPanel);
		content.add(buttonPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
			frame.dispose();				
			menuStart();				
			}		
	     });
		
		continueButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				
		frame.dispose();
		
		frame = new JFrame("Customer Menu");
		frame.setSize(400, 300);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		frame.setVisible(true);
		
		JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton statementButton = new JButton("Display Bank Statement");
		statementButton.setPreferredSize(new Dimension(250, 20));
		
		statementPanel.add(statementButton);
		
		JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton lodgementButton = new JButton("Lodge money into account");
		lodgementPanel.add(lodgementButton);
		lodgementButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton withdrawButton = new JButton("Withdraw money from account");
		withdrawalPanel.add(withdrawButton);
		withdrawButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Customer Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");
		
		content = frame.getContentPane();
		content.setLayout(new GridLayout(5, 1));
		content.add(label1);
		content.add(statementPanel);
		content.add(lodgementPanel);
		content.add(withdrawalPanel);
		content.add(returnPanel);
		
		statementButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				frame = new JFrame("Customer Menu");
				frame.setSize(400, 600);
				frame.setLocation(200, 200);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});          
				frame.setVisible(true);
				
				JLabel label1 = new JLabel("Summary of account transactions: ");
				
				JPanel returnPanel = new JPanel();
				JButton returnButton = new JButton("Return");
				returnPanel.add(returnButton);
				
				JPanel textPanel = new JPanel();
				
				textPanel.setLayout( new BorderLayout() );
				JTextArea textArea = new JTextArea(40, 20);
				textArea.setEditable(false);
				textPanel.add(label1, BorderLayout.NORTH);
				textPanel.add(textArea, BorderLayout.CENTER);
				textPanel.add(returnButton, BorderLayout.SOUTH);
				
				JScrollPane scrollPane = new JScrollPane(textArea);
				textPanel.add(scrollPane);
				
				for (int i = 0; i < acc.getTransactionList().size(); i ++)
				{
					textArea.append(acc.getTransactionList().get(i).toString());
					
				}
				
				textPanel.add(textArea);
				content.removeAll();
				
				
				Container content = frame.getContentPane();
				content.setLayout(new GridLayout(1, 1));
			//	content.add(label1);
				content.add(textPanel);
				//content.add(returnPanel);
				
				returnButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						frame.dispose();			
					customer(cust);				
					}		
			     });										
			}	
	     });
		
		lodgementButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
			boolean loop = true;
			boolean on = true;
			double balance = 0;

			if(acc instanceof CustomerCurrentAccount)
			{
				int count = 3;
				int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
				loop = true;
				
				while(loop)
				{
					if(count == 0)
					{
						JOptionPane.showMessageDialog(frame, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
						((CustomerCurrentAccount) acc).getAtm().setValid(false);
						customer(cust); 
						loop = false;
						on = false;
					}
					
					String Pin = JOptionPane.showInputDialog(frame, "Enter 4 digit PIN;");
					int i = Integer.parseInt(Pin);
					
				   if(on)
				   {
					if(checkPin == i)
					{
						loop = false;
						JOptionPane.showMessageDialog(frame, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else
					{
						count --;
						JOptionPane.showMessageDialog(frame, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);					
					}
				
				}
				}
		
				
			}		if(on == true)
					{
				String balanceTest = JOptionPane.showInputDialog(frame, "Enter amount you wish to lodge:");//the isNumeric method tests to see if the string entered was numeric. 
				if(isNumeric(balanceTest))
				{
					
					balance = Double.parseDouble(balanceTest);
					loop = false;
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
				}
				
			
			String euro = "\u20ac";
			 acc.setBalance(acc.getBalance() + balance);
			// String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			 Date date = new Date();
			 String date2 = date.toString();
			 String type = "Lodgement";
				double amount = balance;
				
				
				
				
				AccountTransaction transaction = new AccountTransaction(date2, type, amount);
				acc.getTransactionList().add(transaction);
				
			 JOptionPane.showMessageDialog(frame, balance + euro + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
			 JOptionPane.showMessageDialog(frame, "New balance = " + acc.getBalance() + euro ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
			}

			}	
	     });
		
		withdrawButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean on = true;
				double withdraw = 0;

				if(acc instanceof CustomerCurrentAccount)
				{
					int count = 3;
					int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
					loop = true;
					
					while(loop)
					{
						if(count == 0)
						{
							JOptionPane.showMessageDialog(frame, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
							((CustomerCurrentAccount) acc).getAtm().setValid(false);
							customer(cust); 
							loop = false;
							on = false;
						}
						
						String Pin = JOptionPane.showInputDialog(frame, "Enter 4 digit PIN;");
						int i = Integer.parseInt(Pin);
						
					   if(on)
					   {
						if(checkPin == i)
						{
							loop = false;
							JOptionPane.showMessageDialog(frame, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
							
						}
						else
						{
							count --;
							JOptionPane.showMessageDialog(frame, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);		
						
						}
					
					}
					}
		
				    	
				    	
				    
					
					
				}		if(on == true)
						{
					String balanceTest = JOptionPane.showInputDialog(frame, "Enter amount you wish to withdraw (max 500):");//the isNumeric method tests to see if the string entered was numeric. 
					if(isNumeric(balanceTest))
					{
						
						withdraw = Double.parseDouble(balanceTest);
						loop = false;
						
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					}
					if(withdraw > 500)
					{
						JOptionPane.showMessageDialog(frame, "500 is the maximum you can withdraw at a time." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						withdraw = 0;
					}
					if(withdraw > acc.getBalance())
					{
						JOptionPane.showMessageDialog(frame, "Insufficient funds." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						withdraw = 0;					
					}
				
				String euro = "\u20ac";
				 acc.setBalance(acc.getBalance()-withdraw);
				   //recording transaction:
			//		String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				 Date date = new Date();
				 String date2 = date.toString();
				 
				 String type = "Withdraw";
					double amount = withdraw;
					
		
					AccountTransaction transaction = new AccountTransaction(date2, type, amount);
					acc.getTransactionList().add(transaction);
				 
				 
					
				 JOptionPane.showMessageDialog(frame, withdraw + euro + " withdrawn." ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
				 JOptionPane.showMessageDialog(frame, "New balance = " + acc.getBalance() + euro ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
				}
				 
					
					
			}	
	     });
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();		
				menuStart();				
			}
	     });		}		
	     });
	}
	}
	
	public static boolean isNumeric(String str)  // a method that tests if a string is numeric
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}

