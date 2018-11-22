package com.test.push_notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyJobService extends JobService {
    JobParameters params;
    DoItTask doIt;
    NotificationCompat.Builder builder;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        this.params = jobParameters;
        doIt = new DoItTask();
        doIt.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (doIt != null) {
            doIt.cancel(true);
        }
        return true;
    }

    private class DoItTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            //전처리
            //조건문 if (푸시알림할 데이터가 있으면) {
            if (true) {

                //push notification
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel notificationChannel = new NotificationChannel(
                            "smartTour",
                            "smartTour",
                            NotificationManager.IMPORTANCE_HIGH
                    );
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder = new NotificationCompat.Builder(getApplicationContext(), notificationChannel.getId());
                } else {
                    builder = new NotificationCompat.Builder(getApplicationContext());
                }

                builder.setSmallIcon(R.drawable.logo)
                        .setContentTitle("수집 가능!")
                        .setContentText("근처에 수집 가능한 관광지가 있습니다!!")
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                notificationManager.notify(0, builder.build());
                Log.d("push", "푸시 알림 울림");
            }
            //}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            jobFinished(params, false);
            super.onPostExecute(aVoid);
        }
    }
}
