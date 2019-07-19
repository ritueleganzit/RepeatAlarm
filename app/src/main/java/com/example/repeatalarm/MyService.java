package com.example.repeatalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    SharedPreferences pref;
    private static final String PREF_NAME = "VKC";
    int PRIVATE_MODE = 0;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {

        pref = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        // Toast.makeText(this, " MyService Created ", Toast.LENGTH_LONG).show();

        //compareDate(pref.getString(USER_START_TIME, null),pref.getString(USER_END_TIME, null));

    }

    @Override
    public void onStart(Intent intent, int startId) {
        // Toast.makeText(this, " MyService Started", Toast.LENGTH_LONG).show();
        compareDate("08:00:00","20:00:00");

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Toast.makeText(this, "Servics Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


    private void compareDate(String start,String end) {
        /*Intent intent1 = new Intent(MyService.this, ReminderActivity.class);
        startActivity(intent1);*/
        //if(DateUtils.isHourInInterval(DateUtils.getCurrentHour()+":00",start,end)){
        if (start!=null && !start.isEmpty())
        {
            if(DateUtils.isHourInInterval(DateUtils.getCurrentHour()+":00",start,end)){
                Log.d("eeeeeeeeeeee",""+DateUtils.getCurrentHour()+":00"+" ");
                Log.d("eeeeeeeeeeee",""+start);
                Log.d("eeeeeeeeeeee",""+end);
            /*Calendar wakeUpTime = Calendar.getInstance();
            wakeUpTime.add(Calendar.SECOND, 1000 * 60 * 60);
*/
                //Create a new PendingIntent and add it to the AlarmManager
                Intent intent = new Intent(MyService.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Log.d("DateeeeeDate","ifffff     "+DateUtils.getCurrentHour()+":00  ---  "+start+"  ---   "+end);
/*PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this,
                    12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am =
                    (AlarmManager)MyService.this.getSystemService(Activity.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP,
                    wakeUpTime.getTimeInMillis(),                pendingIntent);*/
            }
            else
            {
                Log.d("DateeeeeDate","elseeee      "+DateUtils.getCurrentHour()+":00  ---  "+start+"  ---   "+end);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, myIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.cancel(pendingIntent);
            }
        }

     /*   Calendar wakeUpTime = Calendar.getInstance();
        wakeUpTime.add(Calendar.SECOND, 30 * 60);

        //Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(getActivity(), ReminderActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),
                12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am =
                (AlarmManager)getActivity().getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,
                wakeUpTime.getTimeInMillis(),                pendingIntent);*/
    }

}
