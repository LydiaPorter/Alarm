package com.example.sodaadvisor;
import java.util.ArrayList;
import java.util.List;

public class DrinkExpert {
    List<String>getBrands(String color) {
        List<String> brands = new ArrayList<>();
        if (color.equals("Clear")){
            brands.add("Sprite");
            brands.add("7Up");
            brands.add("Ginger Ale");
            brands.add("Club Soda");
            brands.add("Water");
            brands.add("Canada Dry");
            brands.add("Propel");

        }
        else if (color.equals ("Dark Cola")){
            brands.add("Pepsi");
            brands.add("Coke");
            brands.add("Rootbeer");
        }
        else if(color.equals ("Punch")){
            brands.add("Fruit Punch");
            brands.add("Grape");
            brands.add("Pineapple");
            brands.add("Cherry");
        }
        else if(color.equals("Diet")){
            brands.add("Diet Dr. Pepper");
            brands.add("Diet Coke");
            brands.add("Diet Pepsi");
            brands.add("Diet Mountain Dew");
        }
        else if(color.equals("Zero Sugar")){
            brands.add("Coke Zero");
            brands.add("Sprite Zero");
        }
        else if(color.equals("Monster Energy Drinks")){
            brands.add("Green & Black");
            brands.add("Ultra White");
            brands.add("Pipeline Punch");
            brands.add("Mango Loco");
            brands.add("Ultra Sunrise");
        }

        else if(color.equals("Tea")){
            brands.add("Tea");
        }
        else if(color.equals("Coffee")){
            brands.add("Coffee");
        }
        else if(color.equals("Caffeinated")){
            brands.add("Dr. Pepper");
            brands.add("Coke");
            brands.add("Pepsi");
            brands.add("Mountain Dew");
        }
        else if(color.equals("Caffeine Free")){
            brands.add("Sprite");
            brands.add("Fanta");
            brands.add("Rootbeer");
            brands.add("Lemonade");
            brands.add("Caffeine Free Coke");
            brands.add("Powerade");
        }
        else if(color.equals("Non-Carbonated")){
            brands.add("Lemonade");
            brands.add("Powerade");
            brands.add("Water");
        }

        return brands;
    }

}
