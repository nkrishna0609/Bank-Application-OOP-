package bank.project;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class managerScene {
    
    public managerScene(){
        
    }
    public static Scene createScene(Stage currentStage, Scene scene, Manager manager){
        
        GridPane gridManager = new GridPane();
        Scene sceneManager = new Scene(gridManager, 1000, 1000);
        
        gridManager.setPadding(new Insets(20,20,20,20));
        gridManager.setVgap(10);
        gridManager.setHgap(10);
        
        Label labelWelcomeManager = new Label("Welcome Manager.");
        GridPane.setConstraints(labelWelcomeManager, 4,0);
            
        TableColumn <Customer, String>usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        TableColumn <Customer, String>passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        
        TableView <Customer>customerTable=new TableView<Customer>();
        customerTable.getColumns().addAll(usernameColumn, passwordColumn);
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GridPane.setConstraints(customerTable, 20,0);
        
        refreshList(customerTable, manager);
        
        TextField addUsername = new TextField();
        addUsername.setPromptText("create username");
        GridPane.setConstraints(addUsername, 20, 20);
        
        PasswordField addPassword = new PasswordField();
        addPassword.setPromptText("create password");
        GridPane.setConstraints(addPassword, 21, 20);
        
        Button buttonAddCustomer = new Button("Add Customer");
        GridPane.setConstraints(buttonAddCustomer, 22, 20);
        buttonAddCustomer.setOnAction(e -> {
        
        boolean check=false;
        check=manager.checkUserDuplicate(addUsername.getText());
        
        if (!check){
            if ((!addUsername.getText().trim().isEmpty())&&(!addPassword.getText().trim().isEmpty())){
                Customer c=new Customer(addUsername.getText(),addPassword.getText(),"customer", 100.0);
                customerTable.getItems().add(c);
                manager.addCustomer(addUsername.getText(),addPassword.getText(),"customer", 100.0);
                addUsername.clear();
            }
            else {
                ErrorBox.createErrorBox("Error: Incorrect Customer Addition. Try Again.");
            }
        }
        else {
                ErrorBox.createErrorBox("Error: Incorrect Customer Addition. Try Again.");
            }
        addPassword.clear();
        });
        
        Button buttonLogoutManager = new Button("Logout");
        GridPane.setConstraints(buttonLogoutManager, 4, 20);
        buttonLogoutManager.setOnAction(e ->{
        
            addUsername.clear();
            currentStage.setScene(scene);
        
        } );
        
        Button buttonDeleteCustomer = new Button("Delete Customer");
        GridPane.setConstraints(buttonDeleteCustomer, 21, 0);
        buttonDeleteCustomer.setOnAction(e -> {
            
            try {
            Customer selection = customerTable.getSelectionModel().getSelectedItem();
            customerTable.getItems().remove(selection);
            
            manager.deleteCustomer((selection.getUsername()),(selection.getPassword()));
            }
            catch(NullPointerException ex){
                ErrorBox.createErrorBox("Error: Incorrect Deletion of Passenger. Try Again.");
            }
        });
        
        gridManager.getChildren().addAll(labelWelcomeManager, buttonLogoutManager, customerTable, addUsername, addPassword, buttonDeleteCustomer, buttonAddCustomer);
        
        return sceneManager;
    }
    public static void refreshList(TableView tableview, Manager manager){
        for (int i=0;i<manager.getListSize();i++){
               tableview.getItems().add(manager.getListItems(i));
            }
    }
}
