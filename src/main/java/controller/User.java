

package controller;

import Library.Validate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import view.Menu;


public class User extends Menu<String> {
    private static String [] mc ={"Create a new account.","Login system.", "Exit."};
    Validate Validate ;
    public User(){
        super("====== USER MANAGEMENT SYSTEM ======",mc);
        Validate = new Validate();
    }
    @Override
    public void execute(int n) {
            switch (n) {
                case 1 -> createNewAccount();
                case 2 -> loginSystem();
                case 3 -> {
                    return;
            }
     }
    }
        //create a new account
    public void createNewAccount() {
        //check file data exist or not
        if (!Validate.checkFileExist()) {
            return;
        }
        String username = Validate.checkInputUsername();
        String password = Validate.checkInputPassword();
        //check username exist or not
        if (!Validate.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

    //login system
    public void loginSystem() {
        //check file data exist or not
        if (!Validate.checkFileExist()) {
            return;
        }
        String username = Validate.checkInputUsername();
        String password = Validate.checkInputPassword();
        //check username exist or not
        if (!Validate.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Password incorrect.");
            }
            else System.err.println("Login success.");
        } else {System.err.println("User is not exit.");}
    }

    //write new account to data
    public void addAccountData(String username, String password) {
        try {
            try (FileWriter fileWriter = new FileWriter("src/user.dat", true)) {
                fileWriter.write(username + ";" + password + "\n");
            }
            System.err.println("Create successly.");
        } catch (IOException ex) {
        }
    }

    //get password by username
    public String passwordByUsername(String username) {
        try {
            try (FileReader fileReader = new FileReader("src/user.dat"); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] account = line.split(";");
                    if (username.equalsIgnoreCase(account[0])) {
                        return account[1];
                    }
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return null;
    }

}
