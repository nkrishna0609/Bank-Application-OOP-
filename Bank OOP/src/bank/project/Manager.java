package bank.project;

import java.util.ArrayList;
import java.util.Iterator;

public class Manager{
    private String username;
    private String password;
    private String role;
    private static Manager instance=null;
    private ArrayList<Customer> custList;
    private Iterator<Customer> iterator;
    
    private Manager(String username, String password, String role){
        this.username=username;
        this.password=password;
        this.role=role;
        custList = new ArrayList<Customer>();
    }
    
    public static Manager getInstance(){
        if (instance ==null)
            instance=new Manager("admin", "admin", "manager");
        return instance;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    
    public boolean managerLogin(String username, String password, String role){
        boolean check=false;
        
        if ((username.equals(getUsername())) && (password.equals(getPassword())) && (role.equals(getRole()))){
            check= true;
        }
        return check;
    }
    
    public void addCustomer(String username, String password, String role, double money){
        if (!(username==null && password == null && role == null)){
            Customer c = new Customer (username, password, role,money);
        custList.add(c);
        c.getBankAccount().setMoney(money);
        
        }
    }
    
    public boolean checkUserDuplicate(String username){
        boolean check=false;
            for (Customer c: custList){
                if (c.getUsername().equals(username)){
                    check=true;
                }
            }
        return check;
        
    }
    
    public Customer identifyCustomer(String username, String password, String role){
        Customer identify = null;
        for (Customer c: custList){
                if (c.validateCustomerLogin(username, password, role)){
                    identify = c;
                    break;
                }
        }
        return identify;
    }
    
    public Customer getListItems(int i){
        return custList.get(i);
    }
    
    public int getListSize(){
        return custList.size();
    }
    
    public void deleteCustomer(String username, String password){
        iterator= custList.iterator();
        while (iterator.hasNext()){
            Customer check = iterator.next();
                if ((check.getUsername().equals(username))&&(check.getPassword().equals(password))){
                    check.getFile().delete();
                    iterator.remove();
                }
        }   
    }
    
}
