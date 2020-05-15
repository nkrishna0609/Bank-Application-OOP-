package bank.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import javafx.stage.WindowEvent;

public class customerScene {
    
    public customerScene(){
        
    }
    public static Scene createScene(Stage currentStage, Scene scene, Customer cust){
        
        String oldMoney = cust.getOldMoney();
        
        GridPane gridCustomer = new GridPane();
        Scene sceneCustomer = new Scene(gridCustomer,700, 400);
        
        gridCustomer.setPadding(new Insets(20,20,20,20));
        gridCustomer.setVgap(10);
        gridCustomer.setHgap(10);
        
        Label labelWelcome = new Label("Welcome "+cust.getUsername()+".");
        GridPane.setConstraints(labelWelcome, 2,3);
        
        Label labelBalance = new Label("");
        GridPane.setConstraints(labelBalance, 3,5);
        
        Button balance = new Button("Get Balance");
        GridPane.setConstraints(balance, 2, 5);
        balance.setOnAction(e -> {
            double value = cust.getBankAccount().getBalance();
            labelBalance.setText("Balance: $" + value);
        
        });
        
        TextField depositText = new TextField();
        depositText.setPromptText("enter deposit amount");
        GridPane.setConstraints(depositText, 16, 5);
        
        Button deposit = new Button("Deposit");
        GridPane.setConstraints(deposit, 15, 5);
        deposit.setOnAction(e -> {
            try{
            double depositDoubleValue = Double.parseDouble(depositText.getText());
            cust.getBankAccount().depositMoney(depositDoubleValue);
            labelBalance.setText("");
            depositText.clear();
            }
            catch (NumberFormatException ex) {
                ErrorBox.createErrorBox("Error: Please enter an amount first.");
            }
            
        });
        
        TextField withdrawText = new TextField();
        withdrawText.setPromptText("enter withdraw amount");
        GridPane.setConstraints(withdrawText, 16, 6);
        
        Button withdraw = new Button("Withdraw");
        GridPane.setConstraints(withdraw, 15, 6);
        withdraw.setOnAction(e -> {
            try{
            double withdrawDoubleValue = Double.parseDouble(withdrawText.getText());
            cust.getBankAccount().withdrawMoney(withdrawDoubleValue);
            labelBalance.setText("");
            withdrawText.clear();
            }
            catch (NumberFormatException ex) {
                ErrorBox.createErrorBox("Error: Please enter an amount first.");
            }
            
        });
        
        TextField purchaseText = new TextField();
        purchaseText.setPromptText("enter purchase amount");
        GridPane.setConstraints(purchaseText, 16, 7);
        
        Button purchase = new Button("Online Purchase");
        GridPane.setConstraints(purchase, 15, 7);
        purchase.setOnAction(e -> {
            try {
            double purchaseDoubleValue = Double.parseDouble(purchaseText.getText());
            cust.getBankAccount().onlinePurchase(purchaseDoubleValue);
            labelBalance.setText("");
            purchaseText.clear();
            }
            catch (NumberFormatException ex) {
                ErrorBox.createErrorBox("Error: Please enter an amount first.");
            }
            
        });
        
        Button buttonLogoutCustomer = new Button("Logout");
        GridPane.setConstraints(buttonLogoutCustomer, 2, 20);
        buttonLogoutCustomer.setOnAction(e -> {
        updateFileMoney(cust, cust.getBankAccount().getBalance(), oldMoney);
            currentStage.setScene(scene);
            
        
        
        });
        
        currentStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,e-> {
            updateFileMoney(cust, cust.getBankAccount().getBalance(), oldMoney);
        
        });
        
        gridCustomer.getChildren().addAll(buttonLogoutCustomer, labelWelcome, balance, labelBalance, deposit, depositText, withdrawText, withdraw, purchaseText, purchase);
        
        return sceneCustomer;
    }
    public static void updateFileMoney(Customer cust, double newMoney, String oldMoneyString){
        try{
            cust.getBankAccount().setMoney(newMoney);
        String oldInfo = new String(Files.readAllBytes(Paths.get((cust.getUsername())+".txt")));
        String newMoneyString = String.valueOf(newMoney);
        String newInfo = oldInfo.replaceAll(oldMoneyString, newMoneyString);
        cust.getFile().delete();
        cust.setOldMoney(oldMoneyString);
        cust.getBankAccount().setMoney(newMoney);
        
        try
        {
        File file = new File(cust.getUsername()+".txt");
        file.createNewFile();
        FileWriter fwriter = new FileWriter(file);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        bwriter.write(newInfo);
        bwriter.close();
        fwriter.close();
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
