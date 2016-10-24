package iospicker.lib.lib;


import iospicker.lib.OnItemSelectedListener;

final class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    OnItemSelectedRunnable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        for (OnItemSelectedListener listener : loopView.onItemSelectedListeners){

            listener.onItemSelected(loopView.getCurrentItem());
        }
    }
}
