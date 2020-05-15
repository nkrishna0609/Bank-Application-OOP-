package bank.project;

public class Platinum extends Level{

    private BankAccount bankAccount;
    
    public Platinum(BankAccount bankAccount){
        this.bankAccount=bankAccount;
    }
    
    @Override
    public void onlinePurchase(double cost) {
        if ((bankAccount.getBalance() >= cost) && (cost >= 50)){
            bankAccount.setMoney(bankAccount.getBalance()-cost);
            if ((bankAccount.getBalance()>=10000.0)&&(bankAccount.getBalance()<20000.0)){
            bankAccount.setLevel(bankAccount.getGoldLevel());
        }
            else   if ((bankAccount.getBalance()<10000.0)){
            bankAccount.setLevel(bankAccount.getSilverLevel());
        }
        }
        else if (cost<50){
            ErrorBox.createErrorBox("Error: cost must be greater than 50 dollars.");
        }
        else{
            ErrorBox.createErrorBox("Error: not enough money in account.");
    }
    }

    @Override
    public void depositMoney(double depositAmount) {
        if (depositAmount>0){
        bankAccount.setMoney(bankAccount.getBalance()+depositAmount);
        }
        else{
            ErrorBox.createErrorBox("Error: invalid amount entered.");
        }
    }

    @Override
    public void withdrawMoney(double withdrawAmount) {
        if (bankAccount.getBalance() >= withdrawAmount&&(withdrawAmount>0) ){
            bankAccount.setMoney(bankAccount.getBalance()-withdrawAmount);
            if ((bankAccount.getBalance()>=10000.0)&&(bankAccount.getBalance()<20000.0)){
            bankAccount.setLevel(bankAccount.getGoldLevel());
        }
             else  if ((bankAccount.getBalance()<10000.0)){
            bankAccount.setLevel(bankAccount.getSilverLevel());
        }
        }
        else if(withdrawAmount<=0){
            ErrorBox.createErrorBox("Error: invalid amount entered.");
        }
        else{
            ErrorBox.createErrorBox("Error: not enough money in account.");
    }
    }
        
    
    
}

