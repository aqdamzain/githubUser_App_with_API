package zain.aqdam.githubuserv2.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import zain.aqdam.githubuserv2.Key;
import zain.aqdam.githubuserv2.Models.User;
import zain.aqdam.githubuserv2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {
    private TextView tvEmail, tvUserUrl, tvCompany, tvLocation;

    public static OverviewFragment newInstance(User user) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Key.USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvEmail = view.findViewById(R.id.tv_detail_email);
        tvUserUrl = view.findViewById(R.id.tv_detail_userUrl);
        tvCompany = view.findViewById(R.id.tv_detail_company);
        tvLocation = view.findViewById(R.id.tv_detail_location);

        User user = getArguments().getParcelable(Key.USER);

        if ((user.getEmail() != null)) {
            tvEmail.setText(user.getEmail());
        } else {
            tvEmail.setText("--");
        }
        tvUserUrl.setText(user.getUserUrl());
        if ((user.getCompany() != null)) {
            tvCompany.setText(user.getCompany());
        } else {
            tvCompany.setText("--");
        }
        if ((user.getLocation() != null)) {
            tvLocation.setText(user.getLocation());
        } else {
            tvLocation.setText("--");
        }

    }
}
