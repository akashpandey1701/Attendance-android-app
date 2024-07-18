package com.example.swanapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swanapp.models.AttendanceEntry;
import com.example.swanapp.R;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<AttendanceEntry> attendanceList;
    private Context context;

    public AttendanceAdapter(Context context, List<AttendanceEntry> attendanceList) {
        this.context = context;
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attendance, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {

        AttendanceEntry attendanceEntry = attendanceList.get(position);
        holder.textViewClockInTime.setText(attendanceEntry.getClockInTime());
        holder.textViewClockOutTime.setText(attendanceEntry.getClockOutTime());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }


    public void setAttendanceList(List<AttendanceEntry> attendanceList) {
        this.attendanceList = attendanceList;
        notifyDataSetChanged();
    }

    public static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView textViewClockInTime;
        TextView textViewClockOutTime;

        public AttendanceViewHolder(View itemView) {
            super(itemView);
            textViewClockInTime = itemView.findViewById(R.id.textViewClockInTime);
            textViewClockOutTime = itemView.findViewById(R.id.Time);
        }
    }
}
