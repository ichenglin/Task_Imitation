package com.ichenglin.tasks;

import com.google.gson.JsonArray;
import com.ichenglin.Main;
import com.ichenglin.states.TaskKeyState;

import java.awt.event.KeyEvent;

public class TaskKey implements Task {

    private final JsonArray    task_keys;
    private final TaskKeyState task_state;

    public TaskKey(JsonArray task_keys, TaskKeyState task_state) {
        this.task_keys = task_keys;
        this.task_state = task_state;
    }

    @Override
    public void task_perform() {
        for (int key_index = 0; key_index < this.task_keys.size(); key_index++) {
            String key_string = this.task_keys.get(key_index).getAsString();
            int    key_code   = KeyEvent.getExtendedKeyCodeForChar(key_string.charAt(0));
            if (this.task_state == TaskKeyState.KEY_PRESS) Main.keytask_robot.keyPress(key_code);
            else                                           Main.keytask_robot.keyRelease(key_code);
        }
    }

    @Override
    public String toString() {
        return "Key Task (Keys=" + this.task_keys.asList().toString() + ",State=" + (this.task_state == TaskKeyState.KEY_PRESS ? "Press" : "Release") + ")";
    }

}