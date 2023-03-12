package com.ichenglin.objects;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Path;

public class TaskConfiguration {

    private final Path configuration_location;

    public TaskConfiguration(Path configuration_location) {
        this.configuration_location = configuration_location;
    }

    public JsonElement configuration_get() {
        try {
            File           configuration_file   = new File(this.configuration_location.toAbsolutePath().toString());
            BufferedReader configuration_buffer = new BufferedReader(new FileReader(configuration_file));
            StringBuilder  configuration_result = new StringBuilder();
            String         configuration_line;
            while ((configuration_line = configuration_buffer.readLine()) != null) configuration_result.append(configuration_line);
            return new Gson().fromJson(configuration_result.toString(), JsonElement.class);
        } catch (IOException ignored) {
            TaskLogger.log_send("Configuration not found, returned empty object. (" + this.configuration_location.toAbsolutePath() + ")");
            return new JsonObject();
        }
    }

    public void configuration_set(JsonObject configuration_new) {
        try {
            String     configuration_content = new Gson().toJson(configuration_new);
            FileWriter configuration_writer  = new FileWriter(this.configuration_location.toAbsolutePath().toString());
            configuration_writer.write(configuration_content);
            configuration_writer.close();
        } catch (IOException ignored) {
            TaskLogger.log_send("Configuration failed to save, skipped saving task. (" + this.configuration_location.toAbsolutePath() + ")");
        }
    }

    public boolean configuration_exist() {
        File configuration_file   = new File(this.configuration_location.toAbsolutePath().toString());
        return configuration_file.isFile();
    }

}
