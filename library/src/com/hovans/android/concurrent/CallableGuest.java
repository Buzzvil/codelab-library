package com.hovans.android.concurrent;

import java.util.concurrent.Callable;

/**
 * 우선순위 큐의 동작을 위한 Callable 래핑.
 *
 * @author Arngard
 */
@SuppressWarnings("UnusedDeclaration")
abstract class CallableGuest<T> implements Callable<T>, Comparable<CallableGuest<T>> {

    ThreadGuest mGuest;

    public CallableGuest(ThreadGuest guest) {
        mGuest = guest;
    }

    @Override
    public int compareTo(CallableGuest<T> another) {
        return mGuest.compareTo(another.mGuest);
    }

}
