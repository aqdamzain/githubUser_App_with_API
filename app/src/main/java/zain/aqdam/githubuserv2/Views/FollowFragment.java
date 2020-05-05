package zain.aqdam.githubuserv2.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zain.aqdam.githubuserv2.Key;
import zain.aqdam.githubuserv2.Models.UserItem;
import zain.aqdam.githubuserv2.R;
import zain.aqdam.githubuserv2.ViewModels.FollowItemViewModel;
import zain.aqdam.githubuserv2.Views.Adapters.ListUserAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FollowFragment extends Fragment {
    private RecyclerView rvUsers;
    private List<UserItem> users;
    private ProgressBar progressBarDetailFragment;
    private FollowItemViewModel followItemViewModel;

    public static FollowFragment newInstance(String state, String username) {
        FollowFragment followFragment = new FollowFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Key.FOLLOW_STATE, state);
        bundle.putString(Key.USERNAME, username);
        followFragment.setArguments(bundle);
        return followFragment;
    }


    private void showRecyclerList() {
        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListUserAdapter listUserAdapter = new ListUserAdapter(users);
        rvUsers.setAdapter(listUserAdapter);
        rvUsers.setVisibility(View.VISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        followItemViewModel = new ViewModelProvider(this, new ViewModelProvider
                .NewInstanceFactory()).get(FollowItemViewModel.class);
        String username = getArguments().getString(Key.USERNAME);
        String tabState = getArguments().getString(Key.FOLLOW_STATE);
        progressBarDetailFragment = view.findViewById(R.id.progressBarDetailFragment);
        rvUsers = view.findViewById(R.id.rv_detail_fg_users);
        rvUsers.setVisibility(View.GONE);
        progressBarDetailFragment.setVisibility(View.VISIBLE);
        followItemViewModel.setFollowRequest(tabState, username);
        followItemViewModel.getFollowRequest().observe(getViewLifecycleOwner(),
                new Observer<List<UserItem>>() {
                    @Override
                    public void onChanged(List<UserItem> userItems) {
                        progressBarDetailFragment.setVisibility(View.GONE);
                        users = userItems;
                        showRecyclerList();
                    }
                });

    }
}
