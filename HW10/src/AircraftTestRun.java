public class AircraftTestRun extends Aircraft implements Runnable{

    public AircraftTestRun(Position pos) {
        super(pos);
    }

    public void run() {
        for(int i=0;i<10;i++){
            this.setPosition(this.getPosition().changeLat(this.getPosition().getLatitude()+10));
            this.setPosition(this.getPosition().changeLon(this.getPosition().getLongitude()+20));
            this.setPosition(this.getPosition().changeAlt(this.getPosition().getAltitude()+30));
            System.out.println(Thread.currentThread().getName()+": "+this.getPosition());
        }


    }

    public static void main(String[] args) throws InterruptedException {
        AircraftTestRun r = new AircraftTestRun(new Position(0,0,0));

        for(int i=0;i<3;i++){
            Thread t = new Thread(r);
            t.start();
        }
        Thread.sleep(1000);
    }
}
