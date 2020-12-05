package JavaSync;

public class Device extends Thread {
    public static  Router CommonRouter;
    public boolean State = false;
    private String Type;


    public void setType(String dtype) {
        Type=dtype;
    }
    public String getType() {
        return Type;
    }
}
