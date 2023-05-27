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

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final Context context;
    private final ArrayList<User> users;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final ImageView clickableImage;

        private final ImageView bigImage;
        private final TextView nameTxt;
        private final TextView descTxt;


        public UserViewHolder(View view) {
            super(view);
            clickableImage = view.findViewById(R.id.clickable_image);
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
                    activity.putExtra("USERNAME", nameTxt.getText());
                    activity.putExtra("DESCRIPTION", descTxt.getText());
                    context.startActivity(activity);
                });
                builder.setNegativeButton("CLOSE", (dialog, id) -> dialog.dismiss());
                AlertDialog alert = builder.create();
                alert.show();
            });
        }

        public TextView getNameTxt() {
            return nameTxt;
        }

        public TextView getDescTxt() {
            return descTxt;
        }

        public ImageView getBigImage() {
            return bigImage;
        }
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

        userViewHolder.getNameTxt().setText(username);
        userViewHolder.getDescTxt().setText(description);
        System.out.println(username);
    }

    @Override
    public int getItemViewType(int position) {

        String username = users.get(position).getUserName();
        String description = users.get(position).getDescription();

        if (username.charAt(username.length() - 1) != '7') {
            return 0;
        }
        return 1;

    }
//logic return 1 or 0

    @Override
    public int getItemCount() {
        return users.size();
    }
}
