package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button followButton;
    private Button messageButton;

    private TextView username;
    private TextView description;

    private MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this, null, null, 1);

        username = findViewById(R.id.usernameTxt);
        description = findViewById(R.id.descriptionTxt);
        messageButton = findViewById(R.id.message_button);
        followButton = findViewById(R.id.follow_button);

        Intent receivingEnd = getIntent();
        User userData = (User) receivingEnd.getSerializableExtra("USER_DATA");

        this.username.setText(userData.getUserName());
        this.description.setText(userData.getDescription());

        if(userData.isFollowed()) {
            followButton.setText("Unfollow");
        } else {
            followButton.setText("follow");
        }

        followButton.setOnClickListener(view -> {
            if(userData.isFollowed()){
                userData.setFollowed(false);
                dbHandler.updateUser(userData);
                Toast.makeText(getApplicationContext(), "Follow", Toast.LENGTH_SHORT).show();
            }
            else {
                userData.setFollowed(true);
                dbHandler.updateUser(userData);
                Toast.makeText(getApplicationContext(), "Unfollow", Toast.LENGTH_SHORT).show();
            }
            finish();
            startActivity(getIntent());
        });

        messageButton.setOnClickListener(view -> {
            Intent activityMessage = new Intent(MainActivity.this, MessageGroup.class);
            startActivity(activityMessage);
        });

    }
}