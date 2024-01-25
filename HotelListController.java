package Controller;

import CustomException.ValidationException;
import Model.Hotel;
import Model.HotelList;
import Tools.Inputter;
import Tools.StringStandardLize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Quoc Cuong
 */
public class HotelListController {
    /**
     * Add 4 hotel into file to use for test function
    */
    public void addTestData(HotelList hotelList){
        Hotel h1 = new Hotel("H01", "Seraton", 10, "189 Ung Van Khiem, Ward 25, Binh Thanh District, Ho Chi Minh City", "0911796099", 4);
        Hotel h2 = new Hotel("H02", "Vinstar", 5, "200 Ung Van Khiem, Ward 25, Binh Thanh District, Ho Chi Minh City", "0918940111", 5);
        Hotel h3 = new Hotel("H03", "OutString", 7, "300 D1, Ward 24, Binh Thanh District, Ho Chi Minh City", "0988940222", 6);
        Hotel h4 = new Hotel("H04", "Betigar", 8, "189 Duong Quang Ham, Ward 5, Go Vap District, Ho Chi Minh City", "0977940100", 3);
        hotelList.addHotel(h1);
        hotelList.addHotel(h2);
        hotelList.addHotel(h3);
        hotelList.addHotel(h4);
    }
    
    /**
    * Add a hotel into hotelList
    * If ID is duplicate, add fail
    */
    public void addHotel(HotelList hotelList) {
        String id = Inputter.inputString(false, "^[Hh]\\d{2}$", "ID format: Hxx", "Enter ID: ").toUpperCase();
        String name = Inputter.inputString(false, "^.{1,50}$", "Name must less than 50 characters", "Enter name: ");
        int availableRoom = Inputter.inputNumber(false, "Enter room: ");
        String address = Inputter.inputString(false, "^.{1,100}$", "Address must less than 100 chracters", "Enter address: ");
        String phoneNumber = Inputter.inputString(false, "(84|0[3|5|7|8|9])+([0-9]{8})", "Error phone number format", "Enter phone: ");
        int rate = Inputter.inputNumber(false, "Enter rate (star):");

        Hotel nHotel = new Hotel(id, name, availableRoom, StringStandardLize.nameStandardize(address), phoneNumber, rate);
        boolean success = hotelList.addHotel(nHotel);

        if (success) {
            System.out.println("Add Success!");
        } else {
            System.out.println("Add Fail!");
        }
    }

    /**
    * Enter ID: Hxx or hxx
    * Print result if hotel is exist or not
    */
    public void checkExist(HotelList hotelList) {
        String id = Inputter.inputString(false, "^[Hh]\\d{2}$", "ID format: Hxx", "Enter ID: ").toUpperCase();
        Hotel exist = hotelList.isHotelExist(id);
        if (exist != null) {
            System.out.println("Exist Hotel!");
        } else {
            System.out.println("No Hotel Found!");
        }
    }

    /** 
    * Enter ID
    * If hotel exists, update hotel 
    */
    public void updateHotel(HotelList hotelList) {
        boolean check = true;
        Hotel hotel = null;
        String id = "";
        while (check) {
            id = Inputter.inputString(false, "^[Hh]\\d{2}$", "ID format: Hxx", "Enter ID: ").toUpperCase();
            hotel = hotelList.isHotelExist(id);
            if (hotel != null) {
                check = false;
            } else {
                System.out.println("Hotel Does Not Exist!");
            }
        }
        String name = Inputter.inputString(true, "^.{0,50}$", "Name must less than 50 characters", "Enter name: ");
        int availableRoom = Inputter.inputNumber(true, "Enter room: ");
        String address = Inputter.inputString(true, "^.{0,100}$", "Address must less than 100 chracters", "Enter address: ");
        String phoneNumber = Inputter.inputString(true, "(84|0[3|5|7|8|9])+([0-9]{8})", "Error phone number format", "Enter phone: ");
        int rate = Inputter.inputNumber(true, "Enter rate (star):");

        Hotel updatedHotel = new Hotel(id, name, availableRoom, StringStandardLize.nameStandardize(address), phoneNumber, rate);
        Hotel result = hotelList.updateHotel(updatedHotel);
        System.out.println(result.toString());
    }

    /**
    * Enter ID
    * If hotel exists, delete this hotel
    */
    public void deleteHotel(HotelList hotelList) {
        hotelList.displayHotelList();
        boolean check = true;
        Hotel hotel = null;
        String id = "";
        while (check) {
            id = Inputter.inputString(false, "^[Hh]\\d{2}$", "ID format: Hxx", "Enter ID: ").toUpperCase();
            hotel = hotelList.isHotelExist(id);
            if (hotel != null) {
                check = false;
            } else {
                System.out.println("Hotel Does Not Exist!");
            }
        }
        System.out.print("Do you ready want to delete this hotel?");
        String tmp = Inputter.inputString(true, "^.$", "", " (y for Yes, others for No) ");
        if (tmp.equals("y")) {
            hotelList.deleteHotel(hotel);
            System.out.println("Delete success");
        } else {
            System.out.println("Delete fail");
        }
    }

    /**
    *  Enter ID
    *  @return list of hotel if id contains input ID
    */
    public void searchById(HotelList hotelList) {
        String id = Inputter.inputString(false, "^.{1,50}$", "ID format: Hxx", "Enter ID: ");
        ArrayList<Hotel> result = hotelList.searchHotelById(id);
        if (!result.isEmpty()) {
            for (Hotel hotel : result) {
                System.out.println(hotel.toString());
            }
        }else{
            System.out.println("No hotel found!");
        }
    }

    /**
    * Enter name
    * @return a hotel if name equals input name
    */
    public void searchByName(HotelList hotelList) {
        String name = Inputter.inputString(true, "^.{1,50}$", "Name must less than 50 characters", "Enter name: ");
        Hotel result = hotelList.searchHotelByName(name);
        if (result != null) {
            System.out.println(result.toString());
        }else{
            System.out.println("No hotel found!");
        }
    }

    /**
    * Display all hotel and sort by name DESC
    */
    public void displayHotelList(HotelList hotelList) {
        Collections.sort(hotelList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return h1.getName().compareTo(h2.getName()) * (-1);
            }
        });
        for (Hotel hotel : hotelList) {
            System.out.println(hotel.toString());
        }
    }
}
