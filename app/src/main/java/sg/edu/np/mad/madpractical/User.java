package sg.edu.np.mad.madpractical;

import android.media.Image;

public class User {
    private Image userImage;
    private String userName;
    private String description;

    public User(String userName, String description) {
        this.userName = userName;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
