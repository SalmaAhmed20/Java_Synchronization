package JavaSync;

import java.io.FileWriter;
import java.io.IOException;

public class Semaphore {
    public static int Resource; //number of Shared Resources form connection port

    Semaphore(int num){
        Resource =num;
    }

    // it is wait function that pull from shared resources if
    // Resource > 0 there are available resource
    //Resource = 0 no available shared Resource
    // Resource < = 0 i have threads waiting
    public synchronized void P(Device device) throws IOException {
        Resource--;
        if(Resource <0) {
            try {
                System.out.println(device.getName() +" "+device.getType() + " arrived and waiting");
                //***********************Write on file****************************//
                FileWriter Wr = new FileWriter("logged.txt",true);
                Wr.write(device.getName() +" "+device.getType() + " arrived and waiting"+"\n");
                Wr.close();
                //****************************************************//
                wait();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(device.getName() +" "+ device.getType()+ " arrived");
            //****************************************************//
            FileWriter Wr = new FileWriter("logged.txt",true);
            Wr.write(device.getName() +" "+device.getType() + " arrived " +"\n");
            Wr.close();
            //****************************************************//
        }
    }
    //Release Resource  (signal function)
    public synchronized void V() {
        Resource++;
        if (Resource <= 0)
            notify();
    }
}
