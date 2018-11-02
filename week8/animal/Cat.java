package animal;

public class Cat extends Animal{
    public Cat(String species, String name, int age, String feedFood){
        this.species = species;
        this.age = age;
        this.feedFood = feedFood;
        this.name = name;
    }

    @Override
    public void likeFood() {
        System.out.println(this.name + " likes eating " + this.feedFood);
    }

    @Override
    public void doThing() {
        System.out.println(this.name + " likes play with its toys");
    }

    @Override
    public void getAge() {
        System.out.println(this.age + "`s age is " + this.age);
    }
}
