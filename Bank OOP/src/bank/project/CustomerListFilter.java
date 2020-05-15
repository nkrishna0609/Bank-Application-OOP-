package bank.project;

import java.io.File;

public class CustomerListFilter implements FileFilter {
    @Override
    public boolean accept (File f){
        boolean check = false;
        
        if (f.getName().endsWith(".txt")){
            check=true;
        }
        
        return check;
    }
}
