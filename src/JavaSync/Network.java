package JavaSync;

import java.util.Scanner;

public class Network {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner (System.in);

        System.out.println("What is number of WI-FI Connections?");
        int WifiConnections = input.nextInt();
        Router SR = new Router(WifiConnections);

        System.out.println("What is number of devices Clients want to connect?");
        int nDev = input.nextInt();

        Device [] devices = new  Device[nDev];

        input=new Scanner(System.in); //to read input with new buffer
        for (int j = 0; j < nDev; j++)
        {
            String NameType = input.nextLine();
            devices[j] = new Device(NameType,SR);
        }
        for (int i = 0; i < nDev; i++) {
            Thread.sleep(1000);
            devices[i].start();}
    }

}
