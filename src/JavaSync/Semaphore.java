package JavaSync;

public class Semaphore {
    public static int Requests;

    Semaphore(int num){
        Requests=num;
    }

    public synchronized void P(Device Device){
        Requests--;
        if(Requests<0) {
            try {
                System.out.println(Device.getName() + " arrived and waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(Device.getName() + " arrived");
        }
    }
    public synchronized void V() {
        Requests++;
        if (Requests <= 0)
            notify();
    }
}
