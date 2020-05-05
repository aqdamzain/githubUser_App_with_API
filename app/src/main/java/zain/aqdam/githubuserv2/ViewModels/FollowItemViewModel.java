package zain.aqdam.githubuserv2.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zain.aqdam.githubuserv2.Models.ApiServices.ApiClient;
import zain.aqdam.githubuserv2.Models.ApiServices.GitHubApiService;
import zain.aqdam.githubuserv2.Models.UserItem;

public class FollowItemViewModel extends ViewModel {
    private MutableLiveData<List<UserItem>> users = new MutableLiveData<>();

    public LiveData<List<UserItem>> getFollowRequest() {
        return users;
    }

    public void setFollowRequest(final String tabState, final String username) {
        GitHubApiService gitHubApiService = ApiClient.getClient().create(GitHubApiService.class);


        Call<List<UserItem>> call = (tabState.equals("followers")) ?
                gitHubApiService.getFollowers(username) : gitHubApiService.getFollowing(username);

        call.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                users.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {

            }
        });
    }
}
