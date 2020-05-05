package zain.aqdam.githubuserv2.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zain.aqdam.githubuserv2.Models.ApiServices.ApiClient;
import zain.aqdam.githubuserv2.Models.ApiServices.GitHubApiService;
import zain.aqdam.githubuserv2.Models.User;

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();

    public LiveData<User> getUserRequest() {
        return user;
    }

    public void setUserRequest(final String username) {

        GitHubApiService gitHubApiService = ApiClient.getClient().create(GitHubApiService.class);

        Call<User> call = gitHubApiService.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                user.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
