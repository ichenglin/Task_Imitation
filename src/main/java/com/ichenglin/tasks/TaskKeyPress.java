package com.ichenglin.tasks;

import com.google.gson.JsonArray;
import com.ichenglin.Main;
import com.ichenglin.states.TaskKeyState;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TaskKeyPress implements Task {

    private final ArrayList<Integer> task_keys;
    private final TaskKeyState       task_state;

    public TaskKeyPress(JsonArray task_keys, TaskKeyState task_state) {
        ArrayList<Integer> task_keys_new = new ArrayList<Integer>();
        for (int key_index = 0; key_index < task_keys.size(); key_index++) {
            String key_string = task_keys.get(key_index).getAsString();
            task_keys_new.add(KeyEvent.getExtendedKeyCodeForChar(key_string.charAt(0)));
        }
        this.task_keys  = task_keys_new;
        this.task_state = task_state;
    }

    @Override
    public void task_perform() {
        for (int key_index = 0; key_index < this.task_keys.size(); key_index++) {
            int key_code = this.task_keys.get(key_index);
            if (this.task_state == TaskKeyState.KEY_PRESS) Main.keytask_robot.keyPress(key_code);
            else                                           Main.keytask_robot.keyRelease(key_code);
        }
    }

    @Override
    public String toString() {
        return "Key Press Task (Keys=" + this.task_keys + ",State=" + (this.task_state == TaskKeyState.KEY_PRESS ? "Press" : "Release") + ")";
    }

}