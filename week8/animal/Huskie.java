package animal;

public class Huskie extends Animal {
    public Huskie(String species, String name, String feedFood, int age){
        this.species = species;
        this.age = age;
        this.feedFood = feedFood;
        this.name = name;
    }

    @Override
    public void likeFood() {
        System.out.println(this.name + " like eating " + this.feedFood);
    }

    @Override
    public void doThing() {
        System.out.println(this.name + " likes playing with its master");
    }

    @Override
    public void getAge() {
        System.out.println(this.name + "`s age is " + this.age);
    }
}
