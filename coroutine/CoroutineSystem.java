package coroutine;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CoroutineSystem {
    private List<Coroutine> coroutines = new ArrayList<>();
    private List<Integer> finishedCoroutines = new ArrayList<>();

    public List<Coroutine> getCoroutines() {
        return this.coroutines;
    }
    public void update() {
        finishedCoroutines.clear();

        for (int i = 0; i < coroutines.size(); i++) {
            coroutines.get(i).update();
            if (coroutines.get(i).isFinished()) {
                finishedCoroutines.add(i);
            }
        }

        for (int i = finishedCoroutines.size() - 1; i >= 0; i--) {
            coroutines.remove((int) finishedCoroutines.get(i));
        }
    }

    public Coroutine StartCoroutine(Iterator<Object> iterator) {
        Coroutine coroutine = new Coroutine(iterator);
        coroutines.add(coroutine);
        return coroutine;
    }

    public void stopCoroutine(Coroutine coroutine) {
        System.out.println("[CoroutineSystem] before StopCouroutine: " + coroutines.size());
        coroutines.remove(coroutine);
        System.out.println("[CoroutineSystem] after StopCouroutine: " + coroutines.size());
    }
}
