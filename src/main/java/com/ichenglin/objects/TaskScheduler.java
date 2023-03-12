package com.ichenglin.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ichenglin.states.TaskKeyState;
import com.ichenglin.tasks.Task;
import com.ichenglin.tasks.TaskAwait;
import com.ichenglin.tasks.TaskKey;

public class TaskScheduler {

    private final JsonArray task_list;
    private       int       task_index = -1;

    public TaskScheduler(JsonArray task_list) {
        this.task_list = task_list;
    }

    public void perform_next() {
        this.task_index              = (this.task_index + 1) % this.task_list.size();
        JsonObject task_instructions = this.task_list.get(task_index).getAsJsonObject();
        Task       task_object       = null;
        switch (task_instructions.get("type").getAsString()) {
            case "key_press"   -> task_object = new TaskKey(task_instructions.get("keys").getAsJsonArray(), TaskKeyState.KEY_PRESS);
            case "key_release" -> task_object = new TaskKey(task_instructions.get("keys").getAsJsonArray(), TaskKeyState.KEY_RELEASE);
            case "await"       -> task_object = new TaskAwait(task_instructions.get("duration").getAsInt());
        }
        assert task_object != null;
        TaskLogger.log_send("Performing " + task_object);
        task_object.task_perform();
    }

}
