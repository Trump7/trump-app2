public class MineItemData {

    private String name;
    private String serial;
    private float value;

    public MineItemData(String name, Float value, String serial) {
        this.setName(name);
        this.setSerial(serial);
        this.setValue(value);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getValue(){
        return value;
    }

    public void setValue(Float value){this.value = value;}

    public String getSerial(){
        return serial;
    }

    public void setSerial(String serial){
        this.serial = serial;
    }
}
