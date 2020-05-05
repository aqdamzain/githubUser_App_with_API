package zain.aqdam.githubuserv2.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import zain.aqdam.githubuserv2.Key;
import zain.aqdam.githubuserv2.Models.User;
import zain.aqdam.githubuserv2.R;
import zain.aqdam.githubuserv2.ViewModels.UserViewModel;
import zain.aqdam.githubuserv2.Views.Adapters.SectionsPagerAdapter;

public class DetailUserActivity extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewPager;
    private String username;
    private TextView tvName, tvUserName, tvRepository, tvFollowers,
            tvFollowing;
    private ImageView ivPhoto;
    private ProgressBar progressBarDetail;
    private UserViewModel userViewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        progressBarDetail = findViewById(R.id.progressBarDetail);
        userViewModel = new ViewModelProvider(this, new ViewModelProvider
                .NewInstanceFactory()).get(UserViewModel.class);
        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        username = getIntent().getStringExtra(Key.USERNAME);
        tvName = findViewById(R.id.tv_detail_name);
        tvUserName = findViewById(R.id.tv_detail_username);
        ivPhoto = findViewById(R.id.img_detail_photo);
        tvRepository = findViewById(R.id.tv_detail_repository);
        tvFollowers = findViewById(R.id.tv_detail_followers);
        tvFollowing = findViewById(R.id.tv_detail_following);
        progressBarDetail.setVisibility(View.VISIBLE);
        userViewModel.setUserRequest(username);
        userViewModel.getUserRequest().observe(DetailUserActivity.this,
                new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user != null) {
                            progressBarDetail.setVisibility(View.GONE);
                            setView(user);
                            setFragment(user);
                        }
                    }
                });

    }

    private void setView(User user) {
        tvUserName.setText(user.getUsername());
        tvName.setText(user.getName());
        tvRepository.setText(String.valueOf(user.getRepository()));
        tvFollowers.setText(String.valueOf(user.getFollowers()));
        tvFollowing.setText(String.valueOf(user.getFollowing()));

        Glide.with(DetailUserActivity.this)
                .load(user.getAvatar())
                .apply(new RequestOptions().override(90, 90))
                .into(ivPhoto);
    }

    private void setFragment(User user) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                this, getSupportFragmentManager(), user);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}
