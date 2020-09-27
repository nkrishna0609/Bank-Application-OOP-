# Bank Application (OOP in Java)
A simple GUI-based bank application enabling the user to be a bank manager who can control the bank accounts of their customers. 
It uses object oriented principles in Java and utilizes the state pattern.

## Instructions ##
Please run the GUI.java file within the project folder in your IDE.

## Description of Functionalities ##
You can login as the manager by entering "admin" as the username and password, and type in "manager" for the role. Once you login, 
you have the capability of adding customers by entering their username and password in the respective textfields. Every customer 
who gets added receives an initial balance of $100. You can also delete customers by selecting their names in the list. You may 
logout after completing your duties as manager.

To login as a registered customer, enter the customer's credentials in the login screen and type "customer" for the role. After 
logging in, the customer can deposit, withdraw, retrieve their balance or complete an online purchase. An online purchase must be 
greater than $50. Every customer's bank account can be at one of three possible levels; silver, gold or platinum. To be at the 
silver level, the customer must have less than $10000 in their account, and must pay a service charge of $20 when doing an online 
purchase. To be at the gold level, the customer must have less than $20000 but a balance greater than or equal to $10000 in their 
account, and must pay a service charge of $10 when doing an online purchase. Finally, to be at the platinum level, the customer 
must have a balance greater than or equal to $20000 in their account, and will not need to pay a service charge when doing an 
online purchase.

This application retains the information of its customers' bank accounts even after exiting the program.
