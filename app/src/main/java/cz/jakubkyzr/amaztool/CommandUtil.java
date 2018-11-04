package cz.jakubkyzr.amaztool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Kubik on 02.11.2018.
 */

public class CommandUtil {

    // Execute command
    public static void executeCommand(final Context context, final String command) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer output = new StringBuffer();
                Log.d("AmazTool", "Running command:" + command);
                Process p;
                try {
                    p = Runtime.getRuntime().exec(command);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        output.append(line + "n");
                    }

                    context.startActivity(new Intent(context, ExecutedActivity.class));

                } catch (Exception e) {
                    Log.d("AmazTool", "Command failed");
                    e.printStackTrace();

                }
                String response = output.toString();
                //return response;
                Log.d("AmazTool", response);
            }}).start();
    }

}
