package bank.project;

public abstract class Level {
    
    public abstract void onlinePurchase(double cost);
    public abstract void depositMoney(double depositAmount);
    public abstract void withdrawMoney(double withdrawAmount);
    
}
