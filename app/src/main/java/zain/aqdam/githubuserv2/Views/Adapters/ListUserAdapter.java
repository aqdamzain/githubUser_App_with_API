package zain.aqdam.githubuserv2.Views.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import zain.aqdam.githubuserv2.Key;
import zain.aqdam.githubuserv2.Models.UserItem;
import zain.aqdam.githubuserv2.R;
import zain.aqdam.githubuserv2.Views.DetailUserActivity;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListViewHolder> {
    private List<UserItem> listUser;

    public ListUserAdapter(List<UserItem> list) {
        this.listUser = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_user, parent, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final UserItem user = listUser.get(position);

        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(new RequestOptions().override(70, 70))
                .into(holder.imgAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), DetailUserActivity.class);
                mIntent.putExtra(Key.USERNAME, user.getUserName());
                v.getContext().startActivity(mIntent);
            }
        });
        holder.tvUsername.setText(user.getUserName());
        holder.tvId.setText("ID: " + user.getId());
        holder.tvUserUrl.setText("URL: " + user.getUserUrl());

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvUsername, tvId, tvUserUrl;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_item_photo);
            tvUsername = itemView.findViewById(R.id.tv_item_username);
            tvId = itemView.findViewById(R.id.tv_item_id);
            tvUserUrl = itemView.findViewById(R.id.tv_item_userUrl);
        }
    }
}
