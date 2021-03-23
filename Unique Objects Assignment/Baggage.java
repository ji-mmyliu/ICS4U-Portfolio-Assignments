// Name: Josh Liu and Jimmy Liu
// Date: February 16, 2021
// File name: Baggage.java
// Teacher: Ms. Krasteva
// Description: This is a superclass that represents a generic baggage object

public class Baggage {
   private String color;
   private double length;
   private double width;
   private double depth;
   private String material;
   private int pockets;
   private String status;
   private double weight;
   private String contents[];
   private String location;

   // Default constructor for Baggage. Sets fields to default values
   public Baggage() {
      color = "black";
      length = 11.0;
      width = 11.0;
      depth = 11.0;
      material = "polyester";
      pockets = 3;
      status = "closed";
      weight = 0.0;

      contents = new String[pockets];
   }

   public Baggage(String color, double length, double width, double depth, String material, int pockets, String status,
                  double weight, String contents[], String location) {
      this.color = color;
      this.length = length;
      this.width = width;
      this.depth = depth;
      this.material = material;
      this.pockets = pockets;
      this.status = status;
      this.weight = weight;
      this.contents = contents;
      this.location = location;
   }

   // Inserts the item into an empty pocket
   // If no pockets are vacant, the item is not inserted and false is returned
   public boolean insertItem(String item) {
      for (int i = 0; i < contents.length; i++) {
         if (contents[i] == null) {
            contents[i] = item;

            // Add specific weight to backpack according to item
            switch (contents[i]) {
               case "pencil":
                  weight += 1.2;
                  break;
               case "textbook":
                  weight += 10.8;
                  break;
               case "paper":
                  weight += 0.2;
                  break;
               case "laptop":
                  weight += 18.5;
                  break;
               case "waterbottle":
                  weight += 4.7;
                  break;
               default:
                  weight += 3.0;
            }

            return true; // successfully inserted item
         }
      }

      return false; // no space for item
   }

   public boolean removeItem(String item) {
      for (int i = 0; i < contents.length; i++) {
         if (contents[i] != null && contents[i].equals(item)) {
            
            // Remove specific weight to backpack according to item
            switch (contents[i]) {
               case "pencil":
                  weight -= 1.2;
                  break;
               case "textbook":
                  weight -= 10.8;
                  break;
               case "paper":
                  weight -= 0.2;
                  break;
               case "laptop":
                  weight -= 18.5;
                  break;
               case "waterbottle":
                  weight -= 4.7;
                  break;
               default:
                  weight -= 3.0;
            }

            contents[i] = null; // remove the item from the array
            return true; // successfully removed item from backpack
         }
      }
      return false;
   }

   public String listItems() {
      String items = "";
      for(int i=0; i<contents.length; i++) {
         if(contents[i] != null) {
            items += contents[i]+", ";
         }
      }
      if(items.length()==0) {
         return "No items found";
      }else {
         return items.substring(0, items.length()-2)+".";
      }
   }

   public void openBag() {
      status = "open";
   }

   public void closeBag() {
      status = "closed";
   }

   // Some setter methods to set encapsulated methods
   public void setLocation(String location) {
      this.location = location;
   }

   protected void setLength(double length) {
      // Protected since this should be accessible in subclasses but not in main
      this.length = length;
   }

   protected void setWidth(double width) {
      // Protected since this should be accessible in subclasses but not in main
      this.width = width;
   }

   public void setDepth(double depth) {
      this.depth = depth;
   }
   
   // Some getter methods to get encapsulated fields
   public String getContents(int pocket) {
      if (pocket < 0 || pocket >= contents.length) {
         return null;
      }
      return contents[pocket];
   }

   public String getColor() {
      return color;
   }

   public double getLength() {
      return length;
   }

   public double getWidth() {
      return width;
   }

   public double getDepth() {
      return depth;
   }

   public String getMaterial() {
      return material;
   }

   public double getCapacity() {
      // Setup like this so we have a reason to overload this function in a subclass
      return length*width*depth;
   }

   public int getPockets() {
      return pockets;
   }

   public String getStatus() {
      return status;
   }

   public double getWeight() {
      return weight;
   }

   public String getLocation() {
      return location;
   }

   public String toString() {
      return "This "+color+" baggage is being carried at " + location + ".";
   }

}