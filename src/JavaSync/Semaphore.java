package JavaSync;

public class Semaphore {
    public static int Requests;

    Semaphore(int num){
        Requests=num;
    }

    public synchronized void P(Device device){
        Requests--;
        if(Requests<0) {
            try {
                System.out.println(device.getName() +device.getType() + " arrived and waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(device.getName() + device.getType()+ " arrived");
        }
    }
    public synchronized void end() {
        Requests++;
        if (Requests == 0)
            notify();
    }
}
