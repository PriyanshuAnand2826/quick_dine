package com.example.quickdine.data_class;

public class food_data {
    public  String food_name;
    public  String restaursant_name;
    public String price;

    public food_data() {
    }

    public void setFood_name(String food_name) {
        this.food_name = "Crispy Corn Burger";
    }

    public void setRestaursant_name(String restaursant_name) {
        this.restaursant_name = "Hashtag Fast Food";
    }

    public void setPrice(String price) {
        this.price = "Rs 249";
    }

    public String getFood_name() {
        return food_name;
    }

    public String getRestaursant_name() {
        return restaursant_name;
    }

    public String getPrice() {
        return price;
    }
}
