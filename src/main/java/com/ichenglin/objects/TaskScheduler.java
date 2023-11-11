package com.ichenglin.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ichenglin.states.TaskKeyState;
import com.ichenglin.tasks.*;

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
            case "key_press"     -> task_object = new TaskKeyPress(task_instructions.get("keys").getAsJsonArray(), TaskKeyState.KEY_PRESS);
            case "key_release"   -> task_object = new TaskKeyPress(task_instructions.get("keys").getAsJsonArray(), TaskKeyState.KEY_RELEASE);
            case "mouse_press"   -> task_object = new TaskMousePress(task_instructions.get("keys").getAsJsonArray(), TaskKeyState.KEY_PRESS);
            case "mouse_release" -> task_object = new TaskMousePress(task_instructions.get("keys").getAsJsonArray(), TaskKeyState.KEY_RELEASE);
            case "mouse_move"    -> task_object = new TaskMouseMove(task_instructions.get("destination").getAsJsonObject());
            case "open_url"      -> task_object = new TaskOpenURL(task_instructions.get("url").getAsString());
            case "await"         -> task_object = new TaskAwait(task_instructions.get("duration").getAsInt());
        }
        if (task_object == null) return;
        TaskLogger.log_send("Performing " + task_object);
        task_object.task_perform();
    }

}
