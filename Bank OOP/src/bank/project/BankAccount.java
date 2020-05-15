package bank.project;

public class BankAccount {
    private Level silver;
    private Level gold;
    private Level platinum;
    
    private Level level;
    
    private double money;
    
    public BankAccount(){
       silver = new Silver(this);
       gold = new Gold(this);
       platinum = new Platinum(this);
       
       level = silver;
       
       if (money>=10000.0 && money < 20000.0){
           level = gold;
       }
       
       if (money >= 20000.0){
           level = platinum;
       }
       
    }
    
    public void setLevel(Level newLevel){
        level = newLevel;
    }
    
    public void setMoney(double initMoney){
        money = initMoney;
    }
    
    public void withdrawMoney(double withdrawAmount){
        level.withdrawMoney( withdrawAmount);
    }
    
    public void depositMoney(double depositAmount){
        level.depositMoney( depositAmount);
    }
    
    public void onlinePurchase(double cost){
        level.onlinePurchase(cost);
    }
    
    public Level getSilverLevel(){
        return silver;
    }
    
    public Level getGoldLevel(){
        return gold;
    }
    
    public Level getPlatinumLevel(){
        return platinum;
    }

    public double getBalance() {
        return money;
    }
}
