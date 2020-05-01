package com.example.vehicle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class FragmentDisplayRide extends Fragment {
    //////////////////Geri Sayım Countdown//////////////////////
    Bundle bundle2=new Bundle();

    private static final long START_TIME_IN_MILLIS = 60000; //3600000 = 1 saat
    private static final long START_TIME_IN_MILLIS2 = 120000; //7200000 = 2 saat

    private TextView txtCountdown, txtCostDetails;
    private Button btnStart;
    private Button btnSet, btnSet2;
    private Button btnReport, btnCalculateCost;

    private CountDownTimer countdownTimer;

    private boolean timerRunning;

    private long timeLeftInMillis;
    private long endTime;

    private NotificationCompat.Builder builder;

    ///////////////////İleri Sayım/////////////
    private Button mStartButton;
    private Button mPauseButton;
    private Button mResetButton;
    private Chronometer mChronometer;
    private long lastPause;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_displayride_layout, container, false);

         Bundle bundle=getArguments();


        final String token=bundle.getString("token");
        final int userid=bundle.getInt("userid");
        final int rideid=bundle.getInt("rideid");

        txtCountdown = view.findViewById(R.id.txtCountdown);
        txtCostDetails = view.findViewById(R.id.txtCostDetails);
        btnStart = view.findViewById(R.id.btnStart);
        btnSet = view.findViewById(R.id.btnSet);
        btnSet2 = view.findViewById(R.id.btnSet2);
        btnReport = view.findViewById(R.id.btnReport);
        btnCalculateCost = view.findViewById(R.id.btnCalculateCost);

//////////////////////////İleri Sayım/////////////////////////
        mStartButton = view.findViewById(R.id.btnStart2);
        mPauseButton = view.findViewById(R.id.btnPause);
        mResetButton = view.findViewById(R.id.btnReset);
        mChronometer = view.findViewById(R.id.chronometer);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastPause != 0){
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }

                mChronometer.start();
                mStartButton.setEnabled(false);
                mPauseButton.setEnabled(true);
            }
        });


        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastPause = SystemClock.elapsedRealtime();
                mChronometer.stop();
                mPauseButton.setEnabled(false);
                mStartButton.setEnabled(true);
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                mStartButton.setEnabled(true);
                mPauseButton.setEnabled(false);
            }
        });
    ///////////////////////////////////////////////////

        btnCalculateCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCostDetails.setText("Start Time :    Finish Time:    Duration:   Cost:     ");
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {
                    startTimer();
                    notificiation();
                }
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        btnSet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer2();
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentReport llf = new FragmentReport();

                bundle2.putString("token",token);
                bundle2.putInt("userid", userid);
                bundle2.putInt("rideid",rideid);
                llf.setArguments(bundle2);

                ft.replace(R.id.fragment_tutucu, llf);
                ft.commit();
            }
        });
        return view;
    }

    ////////////////GERİ SAYAN (COUNTDOWN TIMER)/////////////////////////////

    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftInMillis;

        countdownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateButtons();
            }
        }.start();

        timerRunning = true;
        updateButtons();
    }

    private void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }

    private void resetTimer2(){
        timeLeftInMillis = START_TIME_IN_MILLIS2;
        updateCountDownText();
        updateButtons();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtCountdown.setText(timeLeftFormatted);
    }

    private void updateButtons() {
        if (timerRunning) {
            btnSet.setVisibility(View.INVISIBLE);
            btnSet2.setVisibility(View.INVISIBLE);
            btnStart.setVisibility(View.INVISIBLE);
        } else {
            btnStart.setText("Start");

            if (timeLeftInMillis < 1000) {
                btnStart.setVisibility(View.INVISIBLE);
            } else {
                btnStart.setVisibility(View.VISIBLE);
            }

            if (timeLeftInMillis < START_TIME_IN_MILLIS) {
                btnSet.setVisibility(View.VISIBLE);
                btnSet2.setVisibility(View.VISIBLE);
            } else {
                btnSet.setVisibility(View.INVISIBLE);
                btnSet2.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences prefs = this.getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);

        editor.apply();

        if (countdownTimer != null) {
            countdownTimer.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = this.getActivity().getSharedPreferences("prefs", MODE_PRIVATE);

        timeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        timerRunning= prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateButtons();

        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();

            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }
    }

    private void notificiation(){
        NotificationManager bildirimYoneticisi = (NotificationManager) this.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getActivity(), FragmentDisplayRide.class);
        PendingIntent gidilecekIntent = PendingIntent.getActivity(this.getActivity(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //oreo ve üstü sürümlerde
            String kanalId = "kanalId";
            String kanalAd = "kanalAd";
            String kanalTanim = "kanalTanim";
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);
            if(kanal == null){
                kanal = new NotificationChannel(kanalId, kanalAd, kanalOnceligi);
                kanal.setDescription(kanalTanim);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(this.getActivity(), kanalId);
            builder.setContentTitle("i-Vehicle");
            builder.setContentText("Your ride is going to end in 3 minutes.");
            builder.setSmallIcon(R.drawable.bike);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
        }

        else{ //oreo altı sürümlerde

            builder = new NotificationCompat.Builder(this.getActivity());
            builder.setContentTitle("i-Vehicle");
            builder.setContentText("Your ride is going to end in 3 minutes.");
            builder.setSmallIcon(R.drawable.bike);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);

        }

        Intent broadcastIntent = new Intent(getActivity(), BildirimYakalayici.class);
        broadcastIntent.putExtra("nesne", builder.build());
        PendingIntent gidilecekBroadcast = PendingIntent.getBroadcast(this.getActivity(), 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long gecikme = SystemClock.elapsedRealtime() + 5000; //3420000 = 57 dakika, 7020000 = 60+57 dakika

        AlarmManager alarmManager = (AlarmManager) this.getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, gecikme, gidilecekBroadcast);
        bildirimYoneticisi.notify(1, builder.build());
    }
}


