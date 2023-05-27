package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private boolean following = false;
    private Button followButton;
    private Button messageButton;

    private TextView username;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameTxt);
        description = findViewById(R.id.descriptionTxt);
        messageButton = findViewById(R.id.message_button);
        followButton = findViewById(R.id.follow_button);

        Intent receivingEnd = getIntent();
        String username = receivingEnd.getStringExtra("USERNAME");
        String description = receivingEnd.getStringExtra("DESCRIPTION");

        this.username.setText(username);
        this.description.setText(description);

        followButton.setOnClickListener(view -> {
            if(!following){
                followButton.setText("Unfollow");
                Toast.makeText(getApplicationContext(), "Follow", Toast.LENGTH_SHORT).show();

            }
            else {
                followButton.setText("follow");
                Toast.makeText(getApplicationContext(), "Unfollow", Toast.LENGTH_SHORT).show();
            }

            following = !following;

        });

        messageButton.setOnClickListener(view -> {
            Intent activityMessage = new Intent(MainActivity.this, MessageGroup.class);
            startActivity(activityMessage);
        });

    }
}