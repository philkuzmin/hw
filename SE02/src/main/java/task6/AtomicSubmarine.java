package task6;

/**
 * Created by Air on 19/01/2017.
 */
@ClassInfo(info = "Atomic Submarine class", author = "Phil")
public class AtomicSubmarine {

    private AtomicSubmarineEngine engine;

    public AtomicSubmarine() {
        engine = new AtomicSubmarineEngine();
    }

    public void sailAway() {
        engine.startEngine();
        System.out.println("Submarine: Sail Away...");
    }

    private class AtomicSubmarineEngine {

        private String engineStatus;

        AtomicSubmarineEngine() {
            engineStatus = "Engine: Stop";
        }

        private void startEngine() {
            System.out.println(engineStatus = "Engine: Start!");
        }

        private void stopEngine() {
            System.out.println(engineStatus = "Engine: Stop");
        }

    }
}
