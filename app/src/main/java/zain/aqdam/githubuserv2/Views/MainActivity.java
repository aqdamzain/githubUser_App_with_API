package zain.aqdam.githubuserv2.Views;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import zain.aqdam.githubuserv2.Models.SearchResources;
import zain.aqdam.githubuserv2.R;
import zain.aqdam.githubuserv2.ViewModels.SearchUserViewModel;
import zain.aqdam.githubuserv2.Views.Adapters.ListUserAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUsers;
    private SearchResources searchItemResource;
    private ProgressBar progressBar;
    private SearchUserViewModel searchUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        searchUserViewModel = new ViewModelProvider(this, new ViewModelProvider
                .NewInstanceFactory()).get(SearchUserViewModel.class);
        rvUsers = findViewById(R.id.rv_users);
        progressBar = findViewById(R.id.progressBar);
        rvUsers.setHasFixedSize(true);
        searchUserViewModel.getResourceItem().observe(MainActivity.this,
                new Observer<SearchResources>() {
                    @Override
                    public void onChanged(SearchResources searchResources) {
                        progressBar.setVisibility(View.GONE);
                        searchItemResource = searchResources;
                        showRecyclerList();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            final SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setIconified(false);
            searchView.setQueryHint("Cari User..");
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.onActionViewCollapsed();
                    rvUsers.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    searchUserViewModel.setResourceItem(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

    private void showRecyclerList() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        ListUserAdapter listUserAdapter = new ListUserAdapter(searchItemResource.getItems());
        rvUsers.setAdapter(listUserAdapter);
        rvUsers.setVisibility(View.VISIBLE);
    }

    /*private void searchRequest(String username) {
        GitHubApiService gitHubApiService = ApiClient.getClient().create(GitHubApiService.class);

        Call<SearchResources> call = gitHubApiService.getResource(username);

        call.enqueue(new Callback<SearchResources>() {
            @Override
            public void onResponse(Call<SearchResources> call, Response<SearchResources> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                progressBar.setVisibility(View.GONE);
                searchResource = response.body();
                showRecyclerList();

            }

            @Override
            public void onFailure(Call<SearchResources> call, Throwable t) {

            }
        });
    }*/
}
