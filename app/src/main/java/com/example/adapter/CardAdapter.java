package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dataBase.RoomDB;
import com.example.dataBase.UserMainData;
import com.example.mysampleshadi.R;
import com.example.mysampleshadi.databinding.GenreItemBinding;


import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.SingleItemRowHolder> {

    private List<UserMainData> genereModels;

    private Context context;

    private final GenreListener genreListener;
    private String currentLanguage;

    public CardAdapter(Context ctx, List<UserMainData> list, GenreListener genreListener) {
        this.genereModels = list;
        this.context = ctx;
        this.genreListener = genreListener;
        notifyDataSetChanged();
        ///this.genreListener = genreListener;

    }

/*    public  void update(Activity ctx, List<GenereModel> list){
        this.genereModels = list;
        this.context = ctx;

        notifyDataSetChanged();


    }*/

    @Override
    public CardAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        GenreItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.genre_item, parent, false);
        return new CardAdapter.SingleItemRowHolder(binding);

    }

    @Override
    public void onBindViewHolder(CardAdapter.SingleItemRowHolder holder, int position) {
        RoomDB roomDB = RoomDB.getInstance(context);

        if (genereModels.get(position).isAccept()) {
            holder.commonItemBinding.tvAccept.setBackgroundColor(context.getResources().getColor(R.color.green));

        } else {
            holder.commonItemBinding.tvAccept.setBackgroundColor(context.getResources().getColor(R.color.grey));

        }
        if (genereModels.get(position).isDecline()) {
            holder.commonItemBinding.tvDecline.setBackgroundColor(context.getResources().getColor(R.color.red));

        } else {
            holder.commonItemBinding.tvDecline.setBackgroundColor(context.getResources().getColor(R.color.grey));

        }
        holder.commonItemBinding.title.setText("NAME :" + " " + genereModels.get(position).getName());
        holder.commonItemBinding.tvId.setText("ID :" + " " + genereModels.get(position).getUid());
        holder.commonItemBinding.tvEmail.setText("EMAIL :" + " " + genereModels.get(position).getEmail());
        holder.commonItemBinding.tvPhone.setText("MOBILE :" + " " + genereModels.get(position).getPhone());

        if (genereModels.get(position).getImage() != null) {

            Glide.with(context)
                    .load(genereModels.get(position).getImage())
                    .placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_background))
                    .into(holder.commonItemBinding.roundImage);
        }

        holder.commonItemBinding.tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genereModels.get(position).isAccept()) {
                    genereModels.get(position).setAccept(false);
                    UserMainData userMainDataModel = new UserMainData();
                    userMainDataModel.setAccept(false);
                } else {
                    genereModels.get(position).setAccept(true);
                    UserMainData userMainDataModel = new UserMainData();
                    userMainDataModel.setAccept(true);
                    genereModels.get(position).setDecline(false);
                    userMainDataModel.setDecline(false);
                    genreListener.setAccept(genereModels.get(position).getName());

                }
                roomDB.roomDao().insert(genereModels);
                notifyDataSetChanged();



            }
        });
        holder.commonItemBinding.tvDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genereModels.get(position).isDecline()) {
                    genereModels.get(position).setDecline(false);
                    UserMainData userMainDataModel = new UserMainData();
                    userMainDataModel.setDecline(false);
                } else {
                    genereModels.get(position).setDecline(true);
                    UserMainData userMainDataModel = new UserMainData();
                    userMainDataModel.setDecline(true);
                    genereModels.get(position).setAccept(false);
                    userMainDataModel.setAccept(false);
                    genreListener.setDecline(genereModels.get(position).getName());

                }
                roomDB.roomDao().insert(genereModels);
                notifyDataSetChanged();


            }
        });

    }

    @Override
    public int getItemCount() {
        return genereModels.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {
        public final GenreItemBinding commonItemBinding;

        public SingleItemRowHolder(GenreItemBinding flightItemLayoutBinding) {
            super(flightItemLayoutBinding.getRoot());
            commonItemBinding = flightItemLayoutBinding;
        }
    }

    public interface GenreListener {
        void setAccept( String name);

        void setDecline(String name);
    }

}


