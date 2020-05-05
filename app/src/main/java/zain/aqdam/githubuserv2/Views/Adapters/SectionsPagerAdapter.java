package zain.aqdam.githubuserv2.Views.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import zain.aqdam.githubuserv2.Models.User;
import zain.aqdam.githubuserv2.R;
import zain.aqdam.githubuserv2.Views.FollowFragment;
import zain.aqdam.githubuserv2.Views.OverviewFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    private final User mUser;

    public SectionsPagerAdapter(Context context, FragmentManager fm, User user) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        mUser = user;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = OverviewFragment.newInstance(mUser);
                break;
            case 1:
                fragment = FollowFragment.newInstance("followers", mUser.getUsername());
                break;
            case 2:
                fragment = FollowFragment.newInstance("following", mUser.getUsername());
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
