package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
public class MessageGroup extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Button group1;
    private Button group2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);


        group1 = findViewById(R.id.button_1);

        group1.setOnClickListener(v -> {
            fragmentManager.beginTransaction()
                    .addToBackStack("Group 1")
                    .replace(R.id.fragmentContainerView, new Group1(), null)
                    .commit();
        });


        group2 = findViewById(R.id.button_2);

        group2.setOnClickListener(v -> {
            fragmentManager.beginTransaction()
                    .addToBackStack("Group 2")
                    .replace(R.id.fragmentContainerView, new Group2(), null)
                    .commit();
        });
    }
}