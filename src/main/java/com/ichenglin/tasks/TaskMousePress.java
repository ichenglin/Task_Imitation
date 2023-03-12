package com.ichenglin.tasks;

import com.google.gson.JsonArray;
import com.ichenglin.Main;
import com.ichenglin.states.TaskKeyState;

import java.awt.event.KeyEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class TaskMousePress implements Task {

    private final ArrayList<String> task_keys;
    private final TaskKeyState      task_state;

    private final Map<String, Integer> mouse_events = Map.ofEntries(
        new AbstractMap.SimpleEntry<String, Integer>("left", KeyEvent.BUTTON1_MASK),
        new AbstractMap.SimpleEntry<String, Integer>("right", KeyEvent.BUTTON2_MASK)
    );

    public TaskMousePress(JsonArray task_keys, TaskKeyState task_state) {
        ArrayList<String> task_keys_new = new ArrayList<String>();
        for (int key_index = 0; key_index < task_keys.size(); key_index++) {
            String key_string = task_keys.get(key_index).getAsString();
            task_keys_new.add(key_string);
        }
        this.task_keys  = task_keys_new;
        this.task_state = task_state;
    }

    @Override
    public void task_perform() {
        for (int key_index = 0; key_index < this.task_keys.size(); key_index++) {
            String key_code = this.task_keys.get(key_index);
            if (this.task_state == TaskKeyState.KEY_PRESS) Main.keytask_robot.mousePress(this.mouse_events.get(key_code));
            else                                           Main.keytask_robot.mouseRelease(this.mouse_events.get(key_code));
        }
    }

    @Override
    public String toString() {
        return "Mouse Press Task (Keys=" + this.task_keys + ",State=" + (this.task_state == TaskKeyState.KEY_PRESS ? "Press" : "Release") + ")";
    }

}
