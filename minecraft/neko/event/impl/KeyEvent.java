package neko.event.impl;


import neko.event.Event;

public class KeyEvent extends Event {
    private int key;

    public void fire(int key) {
        this.key = key;
        super.fire();
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
