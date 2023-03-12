package com.ichenglin.tasks;

import com.google.gson.JsonObject;
import com.ichenglin.Main;

public class TaskMouseMove implements Task {

    private final int mouse_x;
    private final int mouse_y;

    public TaskMouseMove(JsonObject mouse_destination) {
        this.mouse_x = mouse_destination.get("x").getAsInt();
        this.mouse_y = mouse_destination.get("y").getAsInt();
    }

    @Override
    public void task_perform() {
        Main.keytask_robot.mouseMove(mouse_x, mouse_y);
    }

    @Override
    public String toString() {
        return "Mouse Move Task (X=" + this.mouse_x + ",Y=" + this.mouse_y + ")";
    }

}
