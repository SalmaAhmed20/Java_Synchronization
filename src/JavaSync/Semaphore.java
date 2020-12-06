package JavaSync;

import java.io.FileWriter;
import java.io.IOException;

public class Semaphore {
    public static int Requests;

    Semaphore(int num){
        Requests=num;
    }

    public synchronized void P(Device device) throws IOException {
        Requests--;
        if(Requests<0) {
            try {
                System.out.println(device.getName() +device.getType() + " arrived and waiting");
                FileWriter Wr = new FileWriter("logged.txt",true);
                Wr.write(device.getName() +device.getType() + " arrived and waiting");
                Wr.close();
                wait();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(device.getName() + device.getType()+ " arrived");
            FileWriter Wr = new FileWriter("logged.txt",true);
            Wr.write(device.getName() +device.getType() + " arrived and waiting");
            Wr.close();
        }
    }
    public synchronized void V() {
        Requests++;
        if (Requests == 0)
            notify();
    }
}
