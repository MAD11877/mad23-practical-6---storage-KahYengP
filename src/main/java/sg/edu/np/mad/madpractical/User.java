package sg.edu.np.mad.madpractical;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String description;

    private int id;
    private boolean followed;

    public User(String userName, String description, int id, boolean followed) {
        this.userName = userName;
        this.description = description;
        this.id = id;
        this.followed = followed;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }


}
