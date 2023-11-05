package Pack4;

public class Profile {

    public String name;
    public int age;
    public String phno;
    public String address;

    public Profile(String name, int age, String phno, String address) {
        this.name = name;
        this.age = age;
        this.phno = phno;
        this.address = address;
    }

    public void displayProfile(){
        System.out.println("Name: "+ name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: "+ phno);
        System.out.println("Address: "+ address);
    }
}
