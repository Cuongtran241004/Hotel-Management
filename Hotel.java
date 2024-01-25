
package Model;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Tran Quoc Cuong
 */
public class Hotel implements Serializable{
    
    private String id;
    private String name;
    private int availableRoom;
    private String address;
    private String phoneNumber;
    private int rating;
    public Hotel(){
        id = "";
        name = "";
        availableRoom = 0;
        address = "";
        phoneNumber = "";
        rating = 0;
    }
        
    public Hotel(String id, String name, int availableRoom, String address, String phoneNumber, int rating) {
        this.id = id;
        this.name = name;
        this.availableRoom = availableRoom;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
         if(!id.isEmpty()){
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty()){
            this.name = name;
        }
    }

    public int getAvailableRoom() {
        return availableRoom;
    }

    public void setAvailableRoom(int availableRoom) {
         if(availableRoom > 0){
            this.availableRoom = availableRoom;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(!address.isEmpty()){
            this.address = address;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!phoneNumber.isEmpty()){
            this.phoneNumber = phoneNumber;
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
         if(rating > 0){
            this.rating = rating;
        }
    }
      
    @Override
    public String toString(){
        return String.format("%5s|%15s|%3d|%70s|%11s|%2d star|", 
                getId(), getName(),getAvailableRoom(), getAddress(), getPhoneNumber(), getRating());
    }
    
}
