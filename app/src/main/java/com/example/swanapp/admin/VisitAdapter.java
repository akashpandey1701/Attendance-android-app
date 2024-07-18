package com.example.swanapp.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.swanapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.VisitViewHolder> {

    private Context context;
    private List<VisitRecord> dataList;

    public VisitAdapter(Context context, List<VisitRecord> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public VisitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adminvisit, parent, false);
        return new VisitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitViewHolder holder, int position) {
        VisitRecord record = dataList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class VisitViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvName, tvDate, tvDistance;

        public VisitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvVisitId);
            tvName = itemView.findViewById(R.id.tvVisitName);
            tvDate = itemView.findViewById(R.id.tvVisitData);
            tvDistance = itemView.findViewById(R.id.tvVisitDistance);
        }

        public void bind(VisitRecord record) {
            tvId.setText(record.getId());
            tvName.setText(record.getName());
           // tvDate.setText(record.getDate());
            tvDistance.setText(record.getDistance());
        }
    }
}
