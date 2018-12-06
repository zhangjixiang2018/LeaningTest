package collection.MapTest.HashMapKeyTest;

public class PK_people {
    private String prefix; //主键前缀
    private int number ;//主键编号

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPK(){
        return this.prefix + "_" + this.number;
    }

    public void setPK(String pk){
        int i = pk.indexOf("_");
        this.prefix = pk.substring(0,i);
        this.number = new Integer(pk.substring(i));
    }

    public int hashCode(){//重写hashCode()
        return number + prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)//是否为空
            return false;
        if(getClass()!=obj.getClass())//是否为同一类型的实例
            return false;
        if(this == obj){//是否为同一实例
            return true;
        }
        final PK_people other = (PK_people)obj;
        if(this.hashCode() != other.hashCode()){//判断哈希表是否相等
            return false;
        }
        return true;
    }
}
