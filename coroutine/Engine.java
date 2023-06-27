package coroutine;
import java.util.Iterator;

public class Engine {
    private Time time = new Time();
    private CoroutineSystem coroutineSystem = new CoroutineSystem();

    private int targetPulseInterval = 32;

    public void start() {
        while (!shouldQuit()) {
            update();
        }
    }

    private void update() {
        long startTime = System.currentTimeMillis();

        time.update();
        coroutineSystem.update();

        long elapsed = System.currentTimeMillis() - startTime;

        if (elapsed < targetPulseInterval) {
            try {
                Thread.sleep(targetPulseInterval - (int) elapsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean shouldQuit() {
        return coroutineSystem.getCoroutines().size() == 0;
    }

    public Coroutine startCoroutine(Iterator<Object> iterator) {
        return coroutineSystem.StartCoroutine(iterator);
    }

    public void stopCoroutine(Coroutine coroutine) {
        coroutineSystem.stopCoroutine(coroutine);
    }
}
