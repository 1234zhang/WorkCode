import org.json.JSONObject;

public class Person {
    private String name;
    private double height;
    private char gender;
    private String mind;
    private int faceValue;
    public Person(String name,double height, char gender, String mind, int faceValue){
        this.faceValue = faceValue;
        this.gender = gender;
        this.mind = mind;
        this.height = height;
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setGender(char gender){
        this.gender = gender;
    }
    public void setMind(String mind){
        this.mind = mind;
    }
    public void setFaceValue(int faceValue){
        this.faceValue = faceValue;
    }
    public void getAttribute(String attribute){
        switch(attribute){
            case "name":
                System.out.print("\"" + attribute + "\"" + ":" + "\"" + this.name + "\"" + ",");
                break;
            case "height":
                System.out.print("\"" + attribute + "\"" + ":" + "\"" + this.height + "\"" + ",");
                break;
            case "gender":
                System.out.print("\"" + attribute + "\"" + ":" + "\"" + this.gender + "\"" + ",");
                break;
            case "mind":
                System.out.print("\"" + attribute + "\""+ ":" + "\"" + this.mind + "\"" + ",");
                break;
            case "faceValue":
                System.out.print("\"" + attribute + "\"" + ":" + "\"" + this.faceValue + "\"");
                break;
                default:
                    System.out.print("该属性不存在");
        }
    }
    public static void main(String arg[]){
        Person newBoy = new Person("boy",178.0,'男',"abcdsdf",6);
        System.out.print("{[");
            newBoy.getAttribute("name");
            newBoy.getAttribute("height");
            newBoy.getAttribute("mind");
            newBoy.getAttribute("gender");
            newBoy.getAttribute("faceValue");
        System.out.println("]}");

    }
}
