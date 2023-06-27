package coroutine;
import java.util.Iterator;

public class Engine {
    private Time time = new Time();
    private CoroutineSystem coroutineSystem = new CoroutineSystem();
    private CoroutineSystem minutePulse = new CoroutineSystem();
    private CoroutineSystem hourPulse = new CoroutineSystem();
    private CoroutineSystem dailyPulse = new CoroutineSystem();

    private int targetPulseInterval = 32;
    private final int minutePulseInterval = 60000;
    private final int hourPulseInterval = 3600000;
    private final int dailyPulseInterval = 86400000;
    long minuteLastTimeStamp = System.currentTimeMillis();;
    long hourLastTimeStamp = System.currentTimeMillis();;
    long dailyLastTimeStamp = System.currentTimeMillis();;

    public void start() {
        while (!shouldQuit()) {
            update();
        }
    }

    private void update() {
        long startTime = System.currentTimeMillis();

        time.update();
        coroutineSystem.update();
        long now = System.currentTimeMillis();

        long elapsed = now - startTime;

        if (elapsed < targetPulseInterval) {
            try {
                Thread.sleep(targetPulseInterval - (int) elapsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(now - minuteLastTimeStamp > minutePulseInterval){
            minuteLastTimeStamp = now;
            minutePulse.update();
        }
        if(now - hourLastTimeStamp > hourPulseInterval){
            hourLastTimeStamp = now;
            hourPulse.update();
        }
        if(now - dailyLastTimeStamp > dailyPulseInterval){
            dailyLastTimeStamp = now;
            dailyPulse.update();
        }
    }

    private boolean shouldQuit() {
//        return coroutineSystem.getCoroutines().size() == 0;
        return false;
    }

    public Coroutine startCoroutine(Iterator<Object> iterator) {
        return coroutineSystem.StartCoroutine(iterator);
    }
    public Coroutine startMinuteTask(Iterator<Object> iterator) {
        return minutePulse.StartCoroutine(iterator);
    }
    public Coroutine startHourlyTask(Iterator<Object> iterator) {
        return hourPulse.StartCoroutine(iterator);
    }
    public Coroutine startDailyTask(Iterator<Object> iterator) {
        return dailyPulse.StartCoroutine(iterator);
    }

    public void stopCoroutine(Coroutine coroutine) {
        coroutineSystem.stopCoroutine(coroutine);
    }
}
