package android.example.com.notification_app_hw_chelsea_katsidzira_2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Not Your Basic Bakery";
    private List<Integer> dessertImagesList;
    private List<String> dessertNameList;
    private List<String> dessertDescList;
    protected static final String SHARED_PREFS = "Not Your Basic Bakery";
    protected static SharedPreferences sharedPref;
    public static String heart = "\u2665";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.bakery_name);

        // call to populate the lists
        createLists();

        // create recyclerview and assign adapter and layout manager
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        DessertAdapter adapter = new DessertAdapter(this, dessertImagesList, dessertNameList, dessertDescList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        createNotificationChannel();

        //initializing shared preferences
        sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
}

    // create notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nybbChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Not Your Basic Bakery",
                    NotificationManager.IMPORTANCE_HIGH
            );
            nybbChannel.setDescription("Not Your Basic Bakery Notifications");

            NotificationManager notifyManager = getSystemService(NotificationManager.class);
            notifyManager.createNotificationChannel(nybbChannel);
        }
    }

    // create lists for adapter to parse
    private void createLists() {
        dessertImagesList = new ArrayList<>(Arrays.asList(R.drawable.ic_drawable_candy_cupcake,
                R.drawable.ic_drawable_fried_pastry,
                R.drawable.ic_drawable_chocolate_peanutbutter_icebox_cake,
                R.drawable.ic_drawable_coconut_chocolate_panna_cotta,
                R.drawable.ic_drawable_brazilian_pie,
                R.drawable.ic_drawable_brownie_drizzled_chocolate,
                R.drawable.ic_drawable_banana_ice_cream_cake,
                R.drawable.ic_drawable_cookies,
                R.drawable.ic_drawable_lemon_cake,
                R.drawable.ic_drawable_lemon_icecream, R.drawable.ic_drawable_macarons,
                R.drawable.ic_drawable_mardi_gras_fried_icecream,
                R.drawable.ic_drawable_no_bake_cake,
                R.drawable.ic_drawable_no_bake_turtle_pie,
                R.drawable.ic_drawable_oreo_pastry,
                R.drawable.ic_drawable_pumpkin_pie,
                R.drawable.ic_drawable_raspberry_macarons,
                R.drawable.ic_drawable_strawberry_cake_slice,
                R.drawable.ic_drawable_sugar_cookies_pastry,
                R.drawable.ic_drawable_vanilla_cake_slice));

        dessertNameList = new ArrayList<>(Arrays.asList("Candy Drunk Cupcakes",
                "Vanilla Delight",
                "Chocolate Peanut Butter \nIcebox Cake",
                "Coconut Chocolate \nPanna Cotta",
                "Dulce de Leche Gateau",
                "Caramel Chocolate \nDrizzled Brownie",
                "Caramel Banana \nMini Trifle Cake",
                "Grandma's Cookies",
                "Let Them Eat Lemon Cake",
                "Alice in Wonderland \nIce Cream",
                "Princess Peach's Macarons",
                "Mardi Gras \nFried Ice Cream",
                "No Bake Vanilla Cake",
                "No Bake Turtle Pie",
                "Oreo Fantasy",
                "Southern Bumpkin \nPumpkin Pie",
                "Raspberry White \nTruffle Macarons",
                "Strawberry \nMarshmallow Pie",
                "Godzilla's Eclair",
                "Gingerbread Cookie Cheesecake"));

        dessertDescList = new ArrayList<>(Arrays.asList("Intoxicatingly delicious ;)",
                                                        "Sweet, warm, and incredibly addictive!",
                                                        "There's nothing cold about sweet milk chocolate and smooth, succulent peanut butter!",
                                                        "Grab this and bae then you'll be more than okay ;)",
                                                        "Caramel, vanille, crème fouettée, oh mon dieu!",
                                                        "The perfect combination of caramel, chocolate, and love " + heart,
                                                        "Who doesn't like some fruit in their dessert? ;)",
                                                        "Homemade, just like you like them " + heart,
                                                        "Made from Marie Antoinette's kitchen!",
                                                        "Who in the world am I? Ah, that's the great puzzle. - Alice",
                                                        "Mario likes them. We're sure you will too ;)",
                                                        "Get your beads in the morning and this for dessert",
                                                        "Sometimes an oven just isn't necessary!",
                                                        "Don't you love it when dessert is easy and delicious? " + heart,
                                                        "Who knew Oreo's could be even more delicious ;)",
                                                        "She said 'I don't have an accent, y'all do'",
                                                        "The greatest macaron of all macarons. We can say that with all confidence!",
                                                        "Soft, sweet, and tempting to eat ;)",
                                                        "Bigger than anything you've ever seen!",
                                                        "Cheesecake. That's all you need to know ;)"));
    }
}
