package JavaSync;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Router {

    public boolean [] connectplaces;  /* I have number of connection ports for example the array of boolean is
                                        what used and what is free , true for reserved , false for free */
    public int nConnectplaces;        /* Total connection port that allowed to use*/
    public Semaphore connect;         /*Control of make and release connections*/
    public int numOccupy;             /* total number of connections that set*/

    //constructor
    Router (int num)
    {
        this.nConnectplaces=num;
        connectplaces = new boolean[num];
        connect = new Semaphore(num);
    }
    //occupy function that take device to Router and connect it if there is place in connect places array
    //synchronized to be same value in all threads and no two devices connect together
    //InterruptedException because sleep , we use sleep here to give a time to do its activity
    public synchronized int occupy(Device dv) throws InterruptedException, IOException {

        for (int i = 0; i < nConnectplaces; i++) {
            if (!connectplaces[i]) {
                numOccupy++;
                dv.setConnect_port( i+1 );
                System.out.println("Connection " + dv.getConnect_port() + ": (" + dv.getName()+ ") ("+dv.getType() + ") Occupied");
                /**************************************************************/
                FileWriter Wr = new FileWriter("logged.txt",true);
                Wr.write("Connection " + dv.getConnect_port() + ": (" + dv.getName()+
                        ") ("+dv.getType() + ") Occupied");
                Wr.close();
                /**************************************************************/
                connectplaces[i] = true;
                sleep(1000);
                break;
            }
        }
        return dv.getConnect_port();
    }
    //simulate to doing something on the router
    public String performsOnlineActivity () throws InterruptedException {
        String activity = "Performs Online Activity";
        sleep(2000);
        return activity;

    }
    //to make all threads with same number of occupy
    public synchronized String logOut(@NotNull Device dv) {

        {
            numOccupy--; //leave device the router
            connectplaces[dv.getConnect_port()-1] = false; //free alloc
        }
        return "Log out";
    }
}
