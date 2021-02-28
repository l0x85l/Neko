package neko.utils;

public final class TimerUtils
{
    private long time;
    
    public TimerUtils() {
        this.time = System.nanoTime() / 1000000L;
    }
    
    public boolean reach(final long time) {
        return this.time() >= time;
    }
    
    public void reset() {
        this.time = System.nanoTime() / 1000000L;
    }
    
    public boolean sleep(final long time) {
        if (this.time() >= time) {
            this.reset();
            return true;
        }
        return false;
    }
    
    public long time() {
        return System.nanoTime() / 1000000L - this.time;
    }
}
