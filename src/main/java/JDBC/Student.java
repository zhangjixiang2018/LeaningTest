package JDBC;

public class Student {
    public String sNo;
    public String sName;
    public int sAge;
    public String sSex;
    public String sDept;

    public Student(String sNo, String sName, int sAge, String sSex, String sDept) {
        this.sNo = sNo;
        this.sName = sName;
        this.sAge = sAge;
        this.sSex = sSex;
        this.sDept = sDept;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public String getsDept() {
        return sDept;
    }

    public void setsDept(String sDept) {
        this.sDept = sDept;
    }
}
