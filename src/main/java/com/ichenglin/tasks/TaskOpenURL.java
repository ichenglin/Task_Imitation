package com.ichenglin.tasks;

import com.ichenglin.objects.TaskLogger;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TaskOpenURL implements Task {

	private final String task_url;

	public TaskOpenURL(String task_url) {
		this.task_url = task_url;
	}

	@Override
	public void task_perform() {
		if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) return;
		try {
			Desktop.getDesktop().browse(new URI(this.task_url));
		} catch (URISyntaxException | IOException exception) {
			TaskLogger.log_send("Open URL task failed to exception.");
		}
	}

	@Override
	public String toString() {
		return "Open URL Task (URL=" + this.task_url + ")";
	}

}
