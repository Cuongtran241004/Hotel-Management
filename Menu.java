
package Application;

import Tools.Inputter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tran Quoc Cuong
 */
public class Menu {
    public static int getChoice(ArrayList<String> options, String menuName){
        int userChoice = 0;
        int size = options.size();
        
        System.out.println(String.format("-------------------- %s --------------------", menuName));
        for(int i = 0; i < size; i++){
            System.out.println(String.format("%d. %s", i + 1, options.get(i)));
        }
        System.out.println("------------------------------------------------------");
        
        
        try {
            userChoice = Inputter.inputNumber(false, ">>> Your choice: ");
        } catch (NumberFormatException e) {
            userChoice = 0;
            System.out.println("Invalid data");
        }
        return userChoice;
    }  
    
    public static int isContinue(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to go back to the main menu? (y for Yes, others for No)  ");
        String tmp = scan.nextLine();
        if(tmp.equals("y") || tmp.equals("Y")){
            return 0;
        }
        return 1;
    }
    
    
}
