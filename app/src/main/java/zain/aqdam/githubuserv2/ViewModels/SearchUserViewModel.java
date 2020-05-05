package zain.aqdam.githubuserv2.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zain.aqdam.githubuserv2.Models.ApiServices.ApiClient;
import zain.aqdam.githubuserv2.Models.ApiServices.GitHubApiService;
import zain.aqdam.githubuserv2.Models.SearchResources;

public class SearchUserViewModel extends ViewModel {
    private MutableLiveData<SearchResources> resourceItem = new MutableLiveData<>();

    public LiveData<SearchResources> getResourceItem(){
        return resourceItem;
    }
    public void setResourceItem(String username){
        GitHubApiService gitHubApiService = ApiClient.getClient().create(GitHubApiService.class);

        Call<SearchResources> call = gitHubApiService.getResource(username);

        call.enqueue(new Callback<SearchResources>() {
            @Override
            public void onResponse(Call<SearchResources> call, Response<SearchResources> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                resourceItem.postValue(response.body());

            }

            @Override
            public void onFailure(Call<SearchResources> call, Throwable t) {

            }
        });
    }
}
