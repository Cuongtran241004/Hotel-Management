package Tools;

import CustomException.ValidationException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 *
 * @author Tran Quoc Cuong
 */
public class Inputter {

    /**
    * Function input a string 
    */
    public static String inputStr() {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        return str;
    }

    /**
    * Function input a number
    * @param: isEmpty: true --> this value can be blank
    * @param: msg: UI input message for end-user
    */
    public static int inputNumber(boolean isEmpty, String msg) {
        boolean check = true;
        int result = 0;
        while (check) {
            try {
                System.out.print(msg);
                String tmp = inputStr();
                if (isEmpty == false && tmp.isEmpty() == true) {
                    throw new ValidationException("Data must not blank");
                }
                if (isEmpty == true && tmp.isEmpty() == true) {
                    check = false;
                    break;
                }
                result = Integer.parseInt(tmp);
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Data must a number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    /**
    * Function input a string
    * @param: isEmpty: true --> this value can be blank
    * @param: regex --> value must be as specific form
    * @param: errorMsg --> UI error message for end-user 
    * @param: input --> UI input message for end-user
    */
    public static String inputString(boolean isEmpty, String regex, String errorMsg, String input) {
        boolean check = true;
        String str = "";
        while (check) {
            try {
                System.out.print(input);
                str = inputStr();
                if (isEmpty == false && str.isEmpty() == true) {
                    throw new ValidationException("Data must not blank");
                }

                if (!(isEmpty == true && str.isEmpty() == true)) {
                    if (!str.matches(regex)) {
                        throw new ValidationException(errorMsg);
                    }                    
                }
                if (isEmpty == true && str.isEmpty() == true) {
                    check = false;
                    break;
                }
                
                check = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return str;
    }
}
