package animal;

public class Main {
    public static void main(String[] org){
        Animal animal = new Tiger("tiger", "abd", 12, "meat");
        Tiger tiger = new Tiger("tiger", "sdf",123,"meat");
        tiger.doThing();
        animal.doThing();
    }
}
