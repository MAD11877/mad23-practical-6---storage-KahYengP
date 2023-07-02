package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final Context context;
    private final List<User> users;

    private MyDBHandler dbHandler;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row, viewGroup, false);
            return new UserViewHolder(view);
        }
        if (viewType == 1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row_bigimage, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, final int position) {
        String username = users.get(position).getUserName();
        String description = users.get(position).getDescription();

        userViewHolder.bindUserData(users.get(position));

//         userViewHolder.getNameTxt().setText(username);
//         userViewHolder.getDescTxt().setText(description);

        System.out.println(username);
    }

    @Override
    public int getItemViewType(int position) {
        // logic return 1 or 0
        String username = users.get(position).getUserName();

        if (username.charAt(username.length() - 1) != '7') {
            return 0;
        }
        return 1;

    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        public User rowUser;

        private final ImageView bigImage;
        private final TextView nameTxt;
        private final TextView descTxt;


        public UserViewHolder(View view) {
            super(view);

            ImageView clickableImage = view.findViewById(R.id.clickable_image);
            bigImage = view.findViewById(R.id.big_image2);
            nameTxt = view.findViewById(R.id.name);
            descTxt = view.findViewById(R.id.description);

            clickableImage.setOnClickListener(parentView -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
                builder.setTitle("Profile");
                builder.setMessage(nameTxt.getText());
                builder.setPositiveButton("VIEW", (dialog, id) -> {
                    // Need to implement the moving to new activity
                    Intent activity = new Intent(context, MainActivity.class);
                    activity.putExtra("USER_DATA", rowUser);
                    context.startActivity(activity);
                });
                builder.setNegativeButton("CLOSE", (dialog, id) -> dialog.dismiss());
                AlertDialog alert = builder.create();
                alert.show();
            });
        }

        public void bindUserData(User user) {
            rowUser = user;
            nameTxt.setText(user.getUserName());
            descTxt.setText(user.getDescription());
        }
    }
}
