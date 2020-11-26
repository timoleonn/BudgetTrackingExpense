package com.example.budgettrackingexpense;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AdService extends Service {

    Timer timer = new Timer();
    MainActivity mainActivity = new MainActivity();
    Random rand = new Random();
    int random_range = 4;
    int int_random;
    String[] adArray = new String[] {
            "Java's Original name was OAK",
            "Java was made by an accident!",
            "Java is the second most popular language and is very popular among the developers",
            "In one year Java gets downloaded one billion times!"
    };

    public AdService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent in, int flag, int startId) {
        TimerTask hourlyTask = new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("SERVICE STARTED");
                        int_random = rand.nextInt(random_range);
                        StyleableToast.makeText(getApplicationContext(), adArray[int_random], Toast.LENGTH_LONG, R.style.adToast).show();
                    }
                });
            }
        };

        timer.schedule(hourlyTask,0l, 120000);
        return super.onStartCommand(in, flag, startId);
    }

    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            System.out.println("AD SERVICE HAS STOPPED");
        }
        super.onDestroy();
    }
}