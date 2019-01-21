package com.fajaryga.doyog.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajaryga.doyog.R;
import com.fajaryga.doyog.room.Wisata;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<Wisata> myList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvTesti;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTesti = itemView.findViewById(R.id.tvTesti);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }

    public RecycleAdapter(Context mContext, List<Wisata> albumList) {
        this.mContext = mContext;
        this.myList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_testimoni, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Wisata album = myList.get(i);
        myViewHolder.tvNama.setText(album.getNama());
        myViewHolder.tvTesti.setText(album.getTesti());


        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                FragmentManager fm = ((Activity)mContext).getFragmentManager();
//                UpdateDialog dialogFragment = new UpdateDialog(onclickRecycler);
//                Bundle bundle = new Bundle();
//                bundle.putInt("id",myList.get(i).getId());
//                bundle.putInt("id_list",i);
//                dialogFragment.setArguments(bundle);
//                dialogFragment.show(fm," ");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


}
