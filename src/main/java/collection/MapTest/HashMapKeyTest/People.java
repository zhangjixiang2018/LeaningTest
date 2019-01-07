package collection.MapTest.HashMapKeyTest;

public class People {
    private String name ;
    private PK_people number ;

    public People(String name, PK_people number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PK_people getNumber() {
        return number;
    }

    public void setNumber(PK_people number) {
        this.number = number;
    }
}
