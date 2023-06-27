package coroutine;

public class Time {
    static final float MAX_DELTA_TIME = 0.33f;
    private static long lastTime = System.currentTimeMillis();
    private static long currentTime = System.currentTimeMillis();
    private static float deltaTime;

    public void update() {
        currentTime = System.currentTimeMillis();
        deltaTime = Math.min(MAX_DELTA_TIME, (currentTime - lastTime) / 1000.0f);
        lastTime = currentTime;
    }

    public static float getDeltaTime(){
        return deltaTime;
    }
}
