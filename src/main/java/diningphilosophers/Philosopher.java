package diningphilosophers;

import java.util.Random;
import java.util.logging.Logger;


public class Philosopher
        extends Thread {

    private static int seed = 1;
    // Un générateur aléatoire pour tirer au sort les durées
    private final Random myRandom = new Random(System.currentTimeMillis() + seed++);
    private final static int DELAY = 1000;
    private final ChopStick myLeftStick;
    private final ChopStick myRightStick;
    private ChopStick take;
    private ChopStick release;
    private boolean running = true;

    public Philosopher(String name, ChopStick left, ChopStick right) {
        super(name);
        myLeftStick = left;
        myRightStick = right;
    }

    @Override
    public void run() {
        while(running) { 
            try{
                // tant qu’il ne quitte pas la table
                think();
                // Prendre les 2 baguettes
                myLeftStick.take();
                myRightStick.take();
                // Il peut manger
                eat();
                // Il relacheles baguettes
                myLeftStick.release();
                myRightStick.release();
                think();
            }catch (InterruptedException ex){
                    Logger.getLogger(Philosopher.class.getName()).got(level.SEVERE, null, ex);
                    }

    }

    public void leaveTable() {
        running = false;
    }

    private void think() throws InterruptedException {
        int delay = myRandom.nextInt(500 + DELAY);
        System.out.println(this.getName() + " Starts Thinking for: " + delay + " ms");
        sleep(delay); // Le thread peut être interrompu ici
        System.out.println(this.getName() + " Stops Thinking");
    }

    private void eat() throws InterruptedException {
        int delay = myRandom.nextInt(100 + DELAY);
        System.out.println(this.getName() + " Starts Eating for:" + delay + " ms");
        sleep(delay); // Le thread peut être interrompu ici
        System.out.println(this.getName() + " Stops Eating");
    }
}
