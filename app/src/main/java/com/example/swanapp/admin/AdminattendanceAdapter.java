package com.example.swanapp.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swanapp.R;

import java.util.List;

public class AdminattendanceAdapter extends RecyclerView.Adapter<AdminattendanceAdapter.AttendanceViewHolder> {

    private Context context;
    private List<AdminattendanceRecord> dataList;

    public AdminattendanceAdapter(Context context, List<AdminattendanceRecord> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adminattendancehome, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        AdminattendanceRecord record = dataList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvMarkInTime, tvMarkOutTime;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvMarkInTime = itemView.findViewById(R.id.tvMarkInTime);
            tvMarkOutTime = itemView.findViewById(R.id.tvMarkOutTime);
        }

        void bind(AdminattendanceRecord record) {
            if (record != null) {
                if (tvId != null) {
                    tvId.setText(record.getId());
                }
                if (tvName != null) {
                    tvName.setText(record.getName());
                }
                if (tvMarkInTime != null) {
                    tvMarkInTime.setText(record.getMarkInTime());
                }
                if (tvMarkOutTime != null) {
                    tvMarkOutTime.setText(record.getMarkOutTime());
                }
            } else {

            }
        }

    }
}
