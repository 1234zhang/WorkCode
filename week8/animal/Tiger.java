package animal;

public class Tiger extends Animal{
    public Tiger(String species, String name, int age, String feedFood){
        this.species = species;
        this.name = name;
        this.age = age;
        this.feedFood = feedFood;
    }

    @Override
    public void likeFood() {
        System.out.println(this.name + " likes eating " + this.feedFood);
    }

    @Override
    public void doThing() {
        System.out.println(this.name + " likes staying alone");
    }
    public void getAge(){
        System.out.println(this.name + "`s age is " + this.age);
    }
}
