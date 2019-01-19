package rtphan.github.ideahacks2019;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LogFragment logFragment;
    private StatusFragment statusFragment;
    private CustomViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadStatusFragment();
                    return true;
                case R.id.navigation_log:
                    loadLogFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logFragment = new LogFragment();
        statusFragment = new StatusFragment();

        mViewPager = findViewById(R.id.fl_fragment_frame);
        ViewPagerAdapter adapter = new ViewPagerAdapter (MainActivity.this.getSupportFragmentManager());
        adapter.addFragment(statusFragment, "status");
        adapter.addFragment(logFragment, "log");
//		adapter.addFragment(new MapsActivity(), "title");
        mViewPager.setAdapter(adapter);

        loadStatusFragment();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button test = findViewById(R.id.bt_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void loadLogFragment(){
        mViewPager.setCurrentItem(1);
    }

    private void loadStatusFragment(){
        mViewPager.setCurrentItem(0);
    }

    public void createNotification() {

        /*Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);*/
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("ch_test", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Intent videoIntent = new Intent(this, VideoActivity.class);
        videoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    // Create the PendingIntent
        PendingIntent viewPendingIntent = PendingIntent.getActivity(
                this, 0, videoIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent dismissIntent = new Intent(this, DismissReceiver.class);
        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(
                this, 0, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ch_test")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("IDEA Hacks 2019")
                .setContentText("You've been hacked")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_notifications_black_24dp, getString(R.string.notif_view),
                        viewPendingIntent)
                .addAction(R.drawable.ic_notifications_black_24dp, getString(R.string.notif_dismiss),
                        dismissPendingIntent);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, builder.build());

    }

}
