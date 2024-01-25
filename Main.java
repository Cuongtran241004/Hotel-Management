package Application;

import Controller.HotelListController;
import Model.HotelList;
import java.util.ArrayList;

/**
 *
 * @author Tran Quoc Cuong
 */
public class Main {

    public static String[] mainOptions = {"Add new hotel",
        "Check exist hotel",
        "Update hotel information",
        "Delete a hotel",
        "Search a hotel",
        "Display hotel list",
        "Exit"};

    public static String[] searchOptions = {"Search by ID",
        "Search by name",
        "Quit"};

    public static void main(String[] args) {
        // Create Menu
        Menu menu = new Menu();

        // Create HotelList 
        HotelList hotelList = new HotelList();
        hotelList.loadDataFromFile("Hotel.dat");
        
        // Create HotelList Controller
        HotelListController services = new HotelListController();
        services.addTestData(hotelList);
       // hotelList.clear();
        ArrayList<String> mainMenu = new ArrayList<>();
        for (String option : mainOptions) {
            mainMenu.add(option);
        }

        ArrayList<String> searchMenu = new ArrayList<>();
        for (String option : searchOptions) {
            searchMenu.add(option);
        }

        int choice;
        do {
            choice = menu.getChoice(mainMenu, "Hotel Manager");
            switch (choice) {
                case 1:
                    int con1;
                    do {
                        con1 = 0;
                        services.addHotel(hotelList);
                        con1 = menu.isContinue();
                    } while (con1 == 1);
                    break;

                case 2:
                    int con2;
                    do {
                        con2 = 0;
                        services.checkExist(hotelList);
                        con2 = menu.isContinue();
                    } while (con2 == 1);
                    break;
                    
                case 3:
                    services.updateHotel(hotelList);
                    break;
                    
                case 4:
                    services.deleteHotel(hotelList);
                    break;
                    
                case 5:
                    int find = 0;
                    do {
                        find = menu.getChoice(searchMenu, "Search Menu");
                        switch (find) {
                            case 1:
                                services.searchById(hotelList);
                                break;
                            case 2:
                                services.searchByName(hotelList);
                                break;
                            case 3:
                                break;
                        }
                    } while (find <= 0 && find > searchMenu.size());
                    break;
                    
            case 6:
                    services.displayHotelList(hotelList);
                    break;
                    
                case 7:
                    break;
            }
        } while (choice >= 1 && choice <= mainMenu.size() - 1);
        hotelList.saveDataToFile("Hotel.dat");

    }
}
