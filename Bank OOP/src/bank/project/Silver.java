package bank.project;

public class Silver extends Level{

    private BankAccount bankAccount;
    
    public Silver(BankAccount bankAcc){
        bankAccount = bankAcc;
    }
    
    @Override
    public void onlinePurchase(double cost) {
        if ((bankAccount.getBalance() >= (cost + 20.0)) && (cost >= 50)){
            bankAccount.setMoney(bankAccount.getBalance()-(cost+20.0));
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
        if ((bankAccount.getBalance()>=10000.0)&&(bankAccount.getBalance()<20000.0)){
            bankAccount.setLevel(bankAccount.getGoldLevel());
        }
         else      if ((bankAccount.getBalance()>=20000.0)){
            bankAccount.setLevel(bankAccount.getPlatinumLevel());
        }
        }
        else{
            ErrorBox.createErrorBox("Error: invalid amount entered.");
        }
    }

    @Override
    public void withdrawMoney(double withdrawAmount) {
        if (bankAccount.getBalance() >= withdrawAmount &&(withdrawAmount>0)){
            bankAccount.setMoney(bankAccount.getBalance()-withdrawAmount);
        }
        else if(withdrawAmount<=0){
            ErrorBox.createErrorBox("Error: invalid amount entered.");
        }
        else{
            ErrorBox.createErrorBox("Error: not enough money in account.");
    }
    }
        
    
    
}
