package akku.timer;

import akku.Ram;

public class WatchDogTimer {

    int wdtCounter = 0;
    int wdtPrescaler = 0;

    public int getWatchDogCounter() {
        return wdtCounter;
    }

    public void watchDogCounter(Ram ram) {
        if (System.nanoTime() / 1000000 < 230) {

        } else {
            ram.reset(this);
        }
    }

    public void clearWdtCounter() {
        wdtCounter = 0;
    }

    public int getWdtPrescaler() {
        return wdtPrescaler;
    }

    public void increaseWdtPrescaler() {
        wdtPrescaler++;
    }

    public void clearWdtPrescaler() {
        wdtPrescaler = 0;
    }
}
