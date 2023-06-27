import coroutine.*;
import java.util.Iterator;

public class Main {
    private static Engine engine = new Engine();

    public static void main(String[] args) {
        engine.startCoroutine(new TestCoroutine1());
        engine.startCoroutine(new TestCoroutine2());
        engine.startMinuteTask(new TestMinuteTask());
        engine.start();
    }
}
class TestCoroutine1 implements Iterator<Object>{
    private int step = 0;
    public Object next() {
        switch (step) {
            case 0:
                System.out.println("[TestCoroutine 1] TestCoroutine 1 started.");
                step++;
                return new WaitForSeconds(1f);
            case 1:
                System.out.println("[TestCoroutine 1] This should be logged in 1 second.");
                step++;
                return new WaitForSeconds(0.5f);
            case 2:
                System.out.println("[TestCoroutine 1] TestCoroutine 1 finished.");
                step++;
                return null;
            default:
                return null;
        }
    }

    public boolean hasNext() {
        switch (step) {
            case 0:
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

class TestCoroutine2 implements Iterator<Object>{
    private int step = 0;
    public Object next() {
        switch (step) {
            case 0:
                System.out.println("[TestCoroutine 2] TestCoroutine2 Started");
                step++;
                return new WaitForSeconds(0.5f);
            case 1:
                System.out.println("[TestCoroutine 2] Message from TestCoroutine2 after 0.5 second.");
                step++;
                return new WaitForSeconds(0.5f);
            case 2:
                System.out.println("[TestCoroutine 2] Message from TestCoroutine2 after 1 second.");
                step++;
                return new WaitForSeconds(0.5f);
            case 3:
                System.out.println("[TestCoroutine 2] Message from TestCoroutine2 after 1.5 second.");
                step++;
                return new WaitForSeconds(0.5f);
            case 4:
                System.out.println("[TestCoroutine 2] Message from TestCoroutine2 after 2 second.");
                step++;
                return new WaitForSeconds(0.5f);
            case 5:
                System.out.println("[TestCoroutine 2] Message from TestCoroutine2 after 2.5 second.");
                step++;
                return null;
            default:
                return null;
        }
    }

    public boolean hasNext() {
        switch (step) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

class TestMinuteTask implements Iterator<Object>{
    public Object next() {
        System.out.println("[TestMinuteTask] minute pulse!");
        return null;
    }

    public boolean hasNext() {
        return true;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
