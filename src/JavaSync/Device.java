package JavaSync;

import java.io.FileWriter;
import java.io.IOException;

public class Device extends Thread {
    public static  Router CommonRouter; //Shared Router between All devices
    private String Type;    //type of device
    private int connect_port;   //connected to which port

    //constructor the name of device will be the name of our thread
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
    public void run()
    {
        //To make connection and do activity you should first see if there any chance to connect to Router
        try {
            CommonRouter.connect.P(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //send device to get port
            connect_port=CommonRouter.occupy(this);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        /***********Online Activity***************/
        try {
            String S ="Connection"+connect_port+": " +this.getName()+" "+ CommonRouter.performsOnlineActivity()+"\r\n";
            System.out.println(S);
            FileWriter Wr = new FileWriter("logged.txt",true);
            Wr.write(S);
            Wr.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        /************Logout**************/
        String S ="Connection"+connect_port+": " +this.getName()+" "+ CommonRouter.logOut(this)+"\r\n";
        System.out.println(S);
        try {
            FileWriter Wr = new FileWriter("logged.txt",true);
            Wr.write(S);
            Wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**********************/
        CommonRouter.connect.V();
    }
}
