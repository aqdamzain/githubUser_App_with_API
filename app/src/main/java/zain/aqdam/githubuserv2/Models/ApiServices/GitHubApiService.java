package zain.aqdam.githubuserv2.Models.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import zain.aqdam.githubuserv2.Models.SearchResources;
import zain.aqdam.githubuserv2.Models.User;
import zain.aqdam.githubuserv2.Models.UserItem;

public interface GitHubApiService {
    @GET("search/users")
    @Headers("Authorization: token c926092f87ef400b2dd5d439341092567543ddd2")
    Call<SearchResources> getResource(@Query("q") String username);

    @GET("users/{username}")
    @Headers("Authorization: token c926092f87ef400b2dd5d439341092567543ddd2")
    Call<User> getUser(@Path("username") String username);

    @GET("users/{username}/followers")
    @Headers("Authorization: token c926092f87ef400b2dd5d439341092567543ddd2")
    Call<List<UserItem>> getFollowers(@Path("username") String username);

    @GET("users/{username}/following")
    @Headers("Authorization: token c926092f87ef400b2dd5d439341092567543ddd2")
    Call<List<UserItem>> getFollowing(@Path("username") String username);
}
