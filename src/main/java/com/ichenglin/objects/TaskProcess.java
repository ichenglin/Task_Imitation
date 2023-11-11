package com.ichenglin.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TaskProcess {

	public static ArrayList<String> process_find(String program_name) {
		try {
			// execute command
			String         task_command = System.getenv("windir") +"/system32/tasklist.exe /nh /fi \"Imagename eq " + program_name + "\"";
			Process        task_execute = Runtime.getRuntime().exec(task_command);
			BufferedReader task_result  = new BufferedReader(new InputStreamReader(task_execute.getInputStream()));
			// collect result
			ArrayList<String> task_processes = new ArrayList<String>();
			String            task_result_line = task_result.readLine();
			while (task_result_line != null) {
				task_processes.add(task_result_line);
				task_result_line = task_result.readLine();
			}
			task_result.close();
			return task_processes;
		} catch (IOException e) {
			return new ArrayList<String>();
		}
	}

}
