package me.xiaoye.daily.app.service;

import android.os.AsyncTask;

import java.util.HashSet;
import java.util.Iterator;


public abstract class BaseTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    private static HashSet<BaseTask> hashSet = new HashSet<>();

    public static void remove(BaseTask task) {
        for (Iterator<BaseTask> it = hashSet.iterator(); it.hasNext(); ) {
            if (task.equals(it.next())) {
                it.remove();
            }
        }
    }

    public static void add(BaseTask task) {
        hashSet.add(task);
    }

    public static void cancelAll() {
        for (Iterator<BaseTask> it = hashSet.iterator(); it.hasNext(); ) {
            it.next().cancel(true);
        }
    }
}
