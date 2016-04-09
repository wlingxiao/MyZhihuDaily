package me.xiaoye.daily.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaoye.daily.app.R;
import me.xiaoye.daily.app.model.LatestModel;


public class TitleListAdapter extends RecyclerView.Adapter<TitleListAdapter.MyViewHolder> {
    private Context context;
    public OnClickListener onClickListener;
    private List<LatestModel.Stories> stringList = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_list_card, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(stringList.get(position).getTitle());
        String image = stringList.get(position).getImage();
        Glide.with(context)
                .load(image)
                .placeholder(R.drawable.bunny)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view, position, stringList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_item_iv)
        ImageView imageView;

        @Bind(R.id.card_item_tv)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setList(List<LatestModel.Stories> list) {
        stringList.addAll(list);
    }

    public interface OnClickListener {
        void onClick(View view, int itemId, int imageId);
    }
}
