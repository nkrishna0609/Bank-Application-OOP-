package bank.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class GUI extends Application{
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage window) throws Exception {
        Manager manager = Manager.getInstance();
        Customer loggedinCustomer=new Customer(null,null,null,0);
        loggedinCustomer.getFile().delete();
        
        File folder = new File(".");
        File[] listCustomers = folder.listFiles();
        CustomerListFilter filter = new CustomerListFilter();
        
        for (int i=0;i<listCustomers.length;i++){
            if (filter.accept(listCustomers[i])){
        String custUser=null;
        String custPass=null;
        String stringMoney = null;
        double money=0;
        int line1 = 0;
        int line2 = 1;
        int line3=2;
        try {
            custUser = Files.readAllLines(Paths.get(listCustomers[i].getName())).get(line1);
            custPass = Files.readAllLines(Paths.get(listCustomers[i].getName())).get(line2);
            stringMoney = Files.readAllLines(Paths.get(listCustomers[i].getName())).get(line3);
        } catch (IOException e) {
            
        }
        try {
        money = DecimalFormat.getNumberInstance().parse(stringMoney).doubleValue();
        }
        catch (Exception e){
            System.out.println("error");
            
        }
            manager.addCustomer(custUser, custPass, "customer", money);
            }
        }
 
        window.setTitle("Niranjan Krishnaswamy's Online Banking App");
        
        GridPane gridLogin = new GridPane();
        
        Scene sceneLogin = new Scene(gridLogin, 350, 300);
        
        gridLogin.setPadding(new Insets(20,20,20,20));
        gridLogin.setVgap(10);
        gridLogin.setHgap(10);
       
        Label labelHeading = new Label ("Sign on | Online Banking");
        GridPane.setConstraints(labelHeading, 4,0);
        
        Label labelName = new Label ("Username: ");
        GridPane.setConstraints(labelName, 3, 10);
        
        TextField enterName = new TextField();
        enterName.setPromptText("username");
        
        GridPane.setConstraints(enterName, 4, 10);
        Label labelPass = new Label ("Password: ");
        
        GridPane.setConstraints(labelPass, 3, 11);
        PasswordField enterPass = new PasswordField();
        
        enterPass.setPromptText("password");
        GridPane.setConstraints(enterPass, 4,11);
        
        Label labelRole = new Label ("Role: ");
        GridPane.setConstraints(labelRole, 3, 12);
        
        TextField enterRole = new TextField();
        enterRole.setPromptText("role");
        GridPane.setConstraints(enterRole, 4,12);

        Button buttonLogin = new Button("Login");
        GridPane.setConstraints(buttonLogin, 4, 13);
        buttonLogin.setOnAction(e -> {
            
            
            if (manager.managerLogin(enterName.getText(), enterPass.getText(), enterRole.getText())){
                window.setScene(managerScene.createScene(window, sceneLogin, manager));
                
        }
            else if (manager.identifyCustomer(enterName.getText(), enterPass.getText(), enterRole.getText())==null){
            ErrorBox.createErrorBox("Error: Incorrect Login. Try Again.");
        }
            else {
                double money=0;
                String username=enterName.getText();
                String password=enterPass.getText();
                String role=enterRole.getText();
                loggedinCustomer.setUsername(username);
                loggedinCustomer.setPassword(password);
                loggedinCustomer.setRole(role);
                
                for (int i=0; i<manager.getListSize();i++){
                    if (manager.getListItems(i).getUsername().equals(username)){
                        money = Double.valueOf(manager.getListItems(i).getOldMoney());
                    }
                }
                loggedinCustomer.getBankAccount().setMoney(money);
                window.setScene(customerScene.createScene(window, sceneLogin, loggedinCustomer));
        }
                enterName.clear();
                enterPass.clear();
                enterRole.clear();
                });
  
        gridLogin.getChildren().addAll(labelName,labelHeading, enterName, labelPass, enterPass,labelRole, enterRole, buttonLogin);
        
        window.setScene(sceneLogin);
        window.show();
        
        
        
    }
    
}