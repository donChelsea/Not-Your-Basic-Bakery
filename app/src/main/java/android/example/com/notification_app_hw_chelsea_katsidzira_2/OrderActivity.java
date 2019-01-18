package android.example.com.notification_app_hw_chelsea_katsidzira_2;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.example.com.notification_app_hw_chelsea_katsidzira_2.MainActivity.heart;
import static android.example.com.notification_app_hw_chelsea_katsidzira_2.MainActivity.sharedPref;

public class OrderActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Not Your Basic Bakery";
    private static final String DESSERT_NAME = "Dessert Name";
    private static final String DESSERT_ID = "Dessert ID";
    private static final String DESSERT_DESC = "Dessert Description";
    private String imageName;
    private Integer imageID;
    private String imageDesc;
    private NotificationManagerCompat notifyManager;
    ImageView dessertImage;
    TextView dessertName;
    TextView dessertDesc;
    public static String checkedMark = "\u2713";
    private Notification notification;
    private Intent activityIntent;
    private PendingIntent contentIntent;
    private static Bitmap largeIcon;
    private static String notifyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        imageID = extras.getInt("Image Extra");
        imageName = extras.getString("Name Extra");
        imageDesc = extras.getString("Description Extra");

        notifyManager = NotificationManagerCompat.from(getApplicationContext());

        dessertImage = findViewById(R.id.order_image);
        dessertName = findViewById(R.id.order_textview);
        dessertDesc = findViewById(R.id.dessert_desc);

        dessertImage.setImageResource(imageID);
        dessertName.setText(imageName);
        dessertDesc.setText(imageDesc);

        Bundle activityBundle = new Bundle();
        activityBundle.putString(DESSERT_NAME, imageName);
        activityBundle.putInt(DESSERT_ID, imageID);
        activityIntent = new Intent(this, MainActivity.class);
        activityIntent.putExtras(activityBundle);
        contentIntent = PendingIntent.getActivity(this,
                0,
                activityIntent,
                0);

        largeIcon = BitmapFactory.decodeResource(getResources(), imageID);
        notifyText = "Your " + imageName + " is on the way!";

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sharedPref.contains(imageName)) {
                    notification = new NotificationCompat.Builder(view.getContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.muffin)
                            .setContentTitle("Great choice! " + heart)
                            .setContentText(notifyText)
                            .setLargeIcon(largeIcon)
                            .setColor(getResources().getColor(R.color.colorLighterPurple))
                            .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(notifyText))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true)
                            .build();
                    notifyManager.notify(1, notification);

                    sharedPref.edit()
                            .putInt(imageName, imageID)
                            .apply();
                } else {
                    Toast.makeText(getApplicationContext(), "Already ordered " + checkedMark, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(DESSERT_NAME, imageName);
        outState.putInt(DESSERT_ID, imageID);
        outState.putString(DESSERT_DESC, imageDesc);
        super.onSaveInstanceState(outState);
    }
}
