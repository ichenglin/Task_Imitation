package com.ichenglin;

import com.google.gson.JsonElement;
import com.ichenglin.objects.TaskConfiguration;
import com.ichenglin.objects.TaskLogger;
import com.ichenglin.objects.TaskScheduler;

import java.awt.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {

    public static final Path  PATH_EXECUTE = FileSystems.getDefault().getPath("");
    public static       Robot keytask_robot;

    public static void main(String[] args) {
        // initialize
        try {
            Main.keytask_robot = new Robot();
        } catch (AWTException exception) {
            TaskLogger.log_send("KeyTask initialization failure.");
        }
        // startup
        TaskLogger.log_send("KeyTask instance startup. (" + PATH_EXECUTE.toAbsolutePath() + ")");
        TaskConfiguration configuration_file   = new TaskConfiguration(PATH_EXECUTE.resolve("pattern.json"));
        JsonElement       configuration_object = configuration_file.configuration_get();
        TaskScheduler     task_scheduler       = new TaskScheduler(configuration_object.getAsJsonArray());
        for (int i = 0; i < configuration_object.getAsJsonArray().size(); i++) {
            task_scheduler.perform_next();
        }
    }
}