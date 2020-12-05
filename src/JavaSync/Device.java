package JavaSync;

public class Device extends Thread {
    public static  Router CommonRouter;
    public boolean State = false;
    private String Type;
    private int connect_port;

    Device (String Str , Router SR)
    {
        super (Str.substring(0,Str.indexOf(" ")));
        Type = Str.substring(Str.indexOf(" ")+1);
        CommonRouter = SR;

    }


    public void setType(String dtype) {
        Type=dtype;
    }
    public String getType() {
        return Type;
    }

    public int getConnect_port() {
        return connect_port;
    }

    public void setConnect_port(int connect_port) {
        this.connect_port = connect_port;
    }
}
