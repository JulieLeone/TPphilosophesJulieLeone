package diningphilosophers;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    // ...
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
 	synchronized void take() throws InterruptedException {
		// Attendre que la baguette soit libre
		while (!iAmFree) {
			wait(); // Peut lever InterruptedException
		}
		assert !iAmFree; // on est sur que la baguette est libre 
		this.iAmFree = false;
		System.out.printf("la baguette"+myNumber+"est prise");
		notifyAll(); // Notifier que la baguette n'est plus libre  
        }
        
        synchronized void release() throws InterruptedException {
	// Attendre que la baguette soit prise
	while (iAmFree) {
		wait(); // Peut lever InterruptedException
	}
	assert iAmFree; // on est sur que la baguette est prise 
        this.iAmFree = false;	
        System.out.printf("la baguette"+myNumber+"est libre");
	notifyAll(); // Notifier que la baguette n'est plus libre  
        
        }
    
}
