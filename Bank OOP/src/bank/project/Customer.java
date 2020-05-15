package bank.project;

import java.io.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

    // Overview: Customers are mutable objects which each have parameters of String username, String password, String role, and double money.  
    // Each Customer also has its own BankAccount object and its own File stored in the main project directory.  
    //
    // The abstraction function is:
    // a representation of a Customer of a Bank Application with parameters of: a username, password, role and amount of money in the account
    //
    // The rep invariant is:
    //  for Customer c, c.username is a String object &&
    //  (!(c.username.equals(null))) &&
    //  c.password is a String object &&
    //  (!(c.password.equals(null))) &&
    //  c.role is a String object &&
    //  (c.role.equals("customer")) &&
    //  c.money is of type double &&
    //  (c.money>=0.0) &&
            // Informally:
            // Username of Customer must be a String object which is not equal to null AND
            // Password of Customer must be a String object which is not equal to null AND
            // Role of Customer must be a String object AND
            // Role of Customer must be equal to "customer" AND
            // Amount of money of Customer object must be of type double AND
            // Amount of money of Customer obejct must be greater than or equal to 0.0

public class Customer {
    private String username, password, role;
    private File file;
    private FileWriter fwriter;
    private BufferedWriter bwriter;
    private File[] listFiles;
    private BankAccount bankAccount;
    private double money;
    private String oldMoneyString;
    
    //Requires: none
    //Modifies: the instance variables of this Customer object
    //Effects: Initializes this Customer object's fields and creates a new text file in the current directory containing the 
    //         Customer object's information
    public Customer(String username, String password, String role, double money){
        this.username=username;
        this.password=password;
        this.role=role;
        this.money=money;
        bankAccount = new BankAccount();
        try
        {
        file = new File(username+".txt");
        file.createNewFile();
        fwriter = new FileWriter(file);
        bwriter = new BufferedWriter(fwriter);
        bwriter.write(username +"\n"+password+"\n"+money+"\n");
        bwriter.close();
        fwriter.close();
        bankAccount.setMoney(money);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Requires: this Customer object's text file must be located in the current directory and have the Customer's username 
    //          in the first line and the Customer's password in the second line
    //Modifies: none
    //Effects: returns a boolean varialble if the String username and password parameters equal the information in the Customer 
    //         object's respective text file and if the String role parameter equals "customer"
    public boolean validateCustomerLogin(String username, String password, String role){
        Boolean check=false;
        String custUser=null;
        String custPass=null;
        int line1 = 0;
        int line2 = 1;
        try {
            custUser = Files.readAllLines(Paths.get(getUsername()+".txt")).get(line1);
            custPass = Files.readAllLines(Paths.get(getUsername()+".txt")).get(line2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if ((username.equals(custUser)) && (password.equals(custPass)) && (role.equals("customer"))){
            check= true;
        }     
        return check;
    }
    //Requires: none
    //Modifies: none
    //Effects: returns this Customer object's bankAccount object
    public BankAccount getBankAccount(){
        return bankAccount;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: returns this Customer object's text file
    public File getFile() {
        return file;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: returns this Customer object's username of type String
    public String getUsername() {
        return username;
    }
    
    //Requires: none
    //Modifies: this Customer object
    //Effects: modifies this Customer object's String username instance variable
    public void setUsername(String username) {
        this.username = username;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: returns this Customer object's password of type String
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: returns this Customer object's role of type String
    public String getRole() {
        return role;
    }

    //Requires: none
    //Modifies: this Customer object
    //Effects: modifies this Customer object's String role instance variable
    public void setRole(String role) {
        this.role = role;
    }
    
    //Requires: this Customer object's text file must be located in the current directory and have the Customer's money amount 
    //          in the third line
    //Modifies: none
    //Effects: returns the money amount of type double, found in this Customer's text file 
    public String getOldMoney(){
        String oldMoney=null;
        int line = 2;
        try {
            oldMoney = Files.readAllLines(Paths.get(getUsername()+".txt")).get(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oldMoney;
    }
    
    //Requires: none
    //Modifies: this Customer object
    //Effects: modifies this Customer object's String oldMoneyString instance variable
    public void setOldMoney(String s){
        oldMoneyString = s;
    }
    //Requires: none
    //Modifies: none
    // Effects: Returns a string that contains this Customer object's username, password, and role (all of type String) 
    //          and money value (of type double). Implements the abstraction function         
    public String toString(){
        String output = "The customer's username is: " + getUsername() + "\nThe customer's password is: "+getPassword() + "\nThe customer's role is: "+getRole() +"\nThe amount of money in the customer's bank account: "+ getBankAccount().getBalance() +"\n";
        return output;
    }
    
    // Requires: none
    // Modifies: none
    // Effects: Returns true if the rep invariant holds for this    
    //          object; otherwise returns false
    public boolean repOK() {
    boolean check = false;
    if ((getUsername() instanceof String)||(getUsername()==(null)) || (getPassword() instanceof String)||(getPassword()==(null)) || (getRole() instanceof String) || (!(getRole().equals("customer"))) ||(getBankAccount().getBalance() == Math.floor(getBankAccount().getBalance())) || (getBankAccount().getBalance()>=0.0)   ){
    check= false;
    }
    return check;
}
}
