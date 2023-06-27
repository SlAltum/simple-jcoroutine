package coroutine;
import java.util.Iterator;

public class WaitForSeconds implements Iterator<Object> {
    private float seconds;
    private float elapsed;

    public WaitForSeconds(float seconds) {
        this.seconds = seconds;
    }

    public Object next() {
        return seconds - elapsed;
    }

    public boolean hasNext() {
        elapsed += Time.getDeltaTime();
        return elapsed < seconds;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}