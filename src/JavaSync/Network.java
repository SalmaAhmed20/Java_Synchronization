package JavaSync;

import java.util.ArrayList;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner (System.in);

        System.out.println("What is number of WI-FI Connections?");
        int WifiConnections = input.nextInt();
        Router SR = new Router(WifiConnections);

        System.out.println("What is number of devices Clients want to connect?");
        int nDev = input.nextInt();

        ArrayList <Device>devices = new  ArrayList<>(nDev);

        input=new Scanner(System.in); //to read input with new buffer
        //add devices
        for (int j = 0; j < nDev; j++)
        {
            String NameType = input.nextLine();
            devices.add(new Device(NameType,SR));

        }
        //start threads
        for (int i = 0; i < devices.size(); i++) {
            Thread.sleep(1000);
            devices.get(i).start();
        }
    }

}
