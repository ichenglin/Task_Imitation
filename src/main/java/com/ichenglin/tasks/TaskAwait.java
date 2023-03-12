package com.ichenglin.tasks;

import com.ichenglin.objects.TaskLogger;

public class TaskAwait implements Task {

    private final int task_duration;

    public TaskAwait(int task_duration) {
        this.task_duration = task_duration;
    }

    @Override
    public void task_perform() {
        try {
            Thread.sleep(this.task_duration);
        } catch (InterruptedException ignored) {
            TaskLogger.log_send("Await task failed to exception.");
        }
    }

    @Override
    public String toString() {
        return "Await Task (Duration=" + this.task_duration + ")";
    }

}
