package zain.aqdam.githubuserv2.Models;

import com.google.gson.annotations.SerializedName;

public class UserItem {
    @SerializedName("login")
    private String userName;
    private int id;
    @SerializedName("avatar_url")
    private String avatar;
    @SerializedName("html_url")
    private String userUrl;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
