// Name: Josh Liu and Jimmy Liu
// Date: February 16, 2021
// File name: Baggage.java
// Teacher: Ms. Krasteva
// Description: This is the driver class which will call the super and sub class 

public class Main {
   public static void main(String[] args) {
      Baggage backpack = new Baggage("black", 10, 20, 5, "leather", 10, "closed", 0, new String[10], "school");

      backpack.openBag();
      backpack.insertItem("pencil");
      backpack.insertItem("textbook");
      backpack.insertItem("laptop");
      backpack.closeBag();

      System.out.println("This backpack currently has "+backpack.listItems()+" and weighs "+backpack.getWeight()+" pounds.");

      backpack.openBag();
      backpack.removeItem("laptop");
      backpack.closeBag();
      
      System.out.println("This backpack currently has "+backpack.listItems()+" and weighs "+backpack.getWeight()+" pounds.");

      backpack.setLocation("home");

      System.out.println(backpack);

      System.out.println(backpack.getContents(0)+" is inside the first pocket.");
      backpack.setLocation("Living room");
      backpack.setLength(23.5);
      backpack.setWidth(9.5);
      backpack.setDepth(14.3);

      System.out.println("-----------Info-----------");
      System.out.println("Color: "+backpack.getColor());
      System.out.println("Length: "+backpack.getLength());
      System.out.println("Width: "+backpack.getWidth());
      System.out.println("Depth: "+backpack.getDepth());
      System.out.println("Material: "+backpack.getMaterial());
      System.out.println("Capacity: "+backpack.getCapacity());
      System.out.println("Pockets: "+backpack.getPockets());
      System.out.println("Status: "+backpack.getStatus());
      System.out.println("Weight: "+backpack.getWeight());
      System.out.println("Location: "+backpack.getLocation());


      MagicalSuitcase newtonsSuitcase = new MagicalSuitcase("brown", 10, 20, 5, "leather", 1, "closed", 10, new String[1], "subway", 1000000000, 0, false, 100, 0, false, 0, 0);
      newtonsSuitcase.openBag();
      System.out.println("Even though the dimensions of the suitcase is "+newtonsSuitcase.getLength()+" x "+newtonsSuitcase.getWidth()+" x "+newtonsSuitcase.getDepth()+", the capacity is "+newtonsSuitcase.getCapacity()+". Wow so magical!");
      newtonsSuitcase.setCapacity(1000.0);
      System.out.println("The suitcase has a capacity of "+newtonsSuitcase.getCapacity()+". Seems normal to me.");
      newtonsSuitcase.closeBag();
      System.out.println(newtonsSuitcase);

      MagicalSuitcase kingsManSuitcase = new MagicalSuitcase("orange", 10, 20, 5, "leather", 5, "closed", 10, new String[5], "a spy base", 10*20*5, 0, false, 100, 3, false, 250, 250);
      kingsManSuitcase.fuelUp();
      kingsManSuitcase.attack("Josh");
      kingsManSuitcase.fireMissle();
      kingsManSuitcase.reshape(170, 50, 1);
      kingsManSuitcase.setVisibility(0);
      System.out.println("The suitcase is now "+kingsManSuitcase.getVisibility()+"% visible");
      kingsManSuitcase.setVisibility(100);
      kingsManSuitcase.fly(100);
      kingsManSuitcase.setLocation("home");
      kingsManSuitcase.fly(0);
      System.out.println("Gas remaining: "+kingsManSuitcase.getGas()+"L");

      MagicalSuitcase fivesSuitcase = new MagicalSuitcase();
      fivesSuitcase.openBag();
      fivesSuitcase.insertItem("map");
      fivesSuitcase.insertItem("binoculars");
      fivesSuitcase.insertItem("gadget");
      fivesSuitcase.closeBag();
      fivesSuitcase.timeTravel("before the apocalypse");
      fivesSuitcase.timeTravel("1984");
      fivesSuitcase.openBag();
      System.out.println("Five's Suitcase currently has these items: " + fivesSuitcase.listItems());
      fivesSuitcase.removeItem("gadget");
      fivesSuitcase.closeBag();

      MagicalSuitcase magicalSuitcase = new MagicalSuitcase();
      magicalSuitcase.extendLegs();
      magicalSuitcase.setLength(20.1);
      magicalSuitcase.setLocation("home");
      magicalSuitcase.closeBag();
      magicalSuitcase.run(100);
      magicalSuitcase.retractLegs();

      magicalSuitcase.setLocation("Helicopter pad");
      magicalSuitcase.fly(100);
      System.out.println("The suitcase is currently " + magicalSuitcase.getHeight() + " high");
      magicalSuitcase.fly(0);
      System.out.println("The suitcase is currently " + magicalSuitcase.getHeight() + " high");
   }
}
