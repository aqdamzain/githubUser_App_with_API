package zain.aqdam.githubuserv2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResources {
    @SerializedName("total_count")
    private int totalCount;
    private List<UserItem> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<UserItem> getItems() {
        return items;
    }

    public void setItems(List<UserItem> items) {
        this.items = items;
    }
}
