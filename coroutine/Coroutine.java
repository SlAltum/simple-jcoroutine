package coroutine;
import java.util.Stack;
import java.util.Iterator;

public class Coroutine {
    private Iterator<Object> iterator;
    private boolean isFinished;
    private Stack<Iterator<Object>> stack = new Stack<>();

    public Coroutine(Iterator<Object> iterator) {
        this.iterator = iterator;
    }

    public void update() {
        Object res = iterator.next();

        if (res instanceof Iterator) {
            stack.push(iterator);
            iterator = (Iterator<Object>) res;
        }

        boolean hasNext = iterator.hasNext();
        if(hasNext){
            iterator.next();
        }

        while (!hasNext && !stack.empty()) {
            iterator = stack.pop();
            hasNext = iterator.hasNext();
        }
        if(hasNext){
            iterator.next();
        }

        if (!hasNext) {
            isFinished = true;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
