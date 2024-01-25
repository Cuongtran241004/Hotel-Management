package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tran Quoc Cuong
 */
public class HotelList extends ArrayList<Hotel> {

    //Constructor
    public HotelList() {
        super();
    }

    /**
     * Convert Hotel HashMap to ArrayList
     *
     * @return ArrayList of Hotel
     */
    /**
     * Read binary flow and convert to object
     *
     * @param filePath
     * @return true/ false
     */
    public boolean loadDataFromFile(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
            if (f.length() == 0) {
                System.out.println("File is empty");
            }

            // Tạo FileInputStream để đọc file
            FileInputStream fis = new FileInputStream("Hotel.dat");
            // Tạo ObjectInputStream để deserialize object từ file
            // Deserialize: chuyển luồng byte --> object
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    // Read an object from the file
                    Hotel hotel = (Hotel) ois.readObject();
                    this.add(hotel);
                } catch (ClassNotFoundException e) {
                    // Handle the case where the class of the object is not found
                    e.printStackTrace();
                } catch (IOException e) {
                    // Reached end of file (EOFException), break the loop
                    break;
                }
            }

            // Đóng file
            ois.close();
            fis.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Write object to file
     *
     * @param filePath
     * @return true/ false
     */
    public boolean saveDataToFile(String filePath) {
        try {
            File f = new File(filePath);
            // Tạo FileOutputStream để ghi object
            FileOutputStream fos = new FileOutputStream(filePath);
            // Tạo ObjectOutputStream để serialize và ghi vào file
            // Serialize: chuyển object --> mảng byte (đại diện cho class, version và status)
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Ghi object vào file           
            for (Hotel item : this) {
                oos.writeObject(item);
            }
            // Đóng file
            oos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * add Hotel to ArrayList
     *
     * @param hotel
     * @return true if add successful false if fail
     */
    public boolean addHotel(Hotel hotel) {
        boolean result = true;
        for (Hotel item : this) {
            if (item.getId().equals(hotel.getId()) 
                    || item.getAddress().equalsIgnoreCase(hotel.getAddress())
                    || item.getPhoneNumber().equals(hotel.getPhoneNumber())) {
                return false;
            }
        }
        this.add(hotel);
        return result;
    }

   

    /**
     * Check if hotel exist
     *
     * @param id
     * @return true if it's exist, false if it's not exist
     */
    public Hotel isHotelExist(String id) {
        Hotel result = null;
        for (Hotel item : this) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Update Hotel information by ID
     *
     * @return true if update successful false if update fail
     */
    public Hotel updateHotel(Hotel hotel) {
        Hotel result = null;
        for (Hotel item : this) {
            if (item.getId().equals(hotel.getId())) {
                item.setName(hotel.getName());
                item.setAvailableRoom(hotel.getAvailableRoom());
                item.setAddress(hotel.getAddress());
                item.setPhoneNumber(hotel.getPhoneNumber());
                item.setRating(hotel.getRating());
                result = item;
            }
        }
        return result;
    }

    /**
     * delete Hotel from ArrayList
     *
     * @param hotel
     * @return true if delete successful false if fail
     */
    public Hotel deleteHotel(Hotel hotel) {
        Hotel result = null;
        for (Hotel item : this) {
            if (item.getId().equals(hotel.getId())) {
                this.remove(item);
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Search for Hotel in HashMap
     *
     * @param id
     * @return object Hotel if it's exist, null if it's not exist
     */
    public ArrayList<Hotel> searchHotelById(String id) {
        ArrayList<Hotel> result = new ArrayList<Hotel>();
        for (Hotel item : this) {
            if (item.getId().contains(id.toUpperCase())) {
                result.add(item);
            }
        }
        Collections.sort(result, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return h1.getId().compareTo(h2.getId()) * (-1);
            }
        });
        
        return result;
    }

    public Hotel searchHotelByName(String name) {
        for (Hotel item : this) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void displayHotelList() {
        if (this.isEmpty()) {
            System.out.println("Hotel List is empty");
        } else {
            for (Hotel hotel : this) {
                System.out.println(hotel.toString());
            }
        }
    }
}
