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

        holder.textViewAttendanceId.setText(attendanceEntry.getId());
        holder.textViewAttendanceUserId.setText(attendanceEntry.getUsername());
        holder.textViewAttendanceEmail.setText(attendanceEntry.getEmail());
        holder.textViewAttendanceDate.setText(attendanceEntry.getDate());
        holder.textViewAttendanceLogin.setText(attendanceEntry.getLoginTime());
        holder.textViewAttendanceLogout.setText(attendanceEntry.getLogoutTime());
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
        TextView textViewAttendanceId;
        TextView textViewAttendanceUserId;
        TextView textViewAttendanceEmail;
        TextView textViewAttendanceDate;
        TextView textViewAttendanceLogin;
        TextView textViewAttendanceLogout;

        public AttendanceViewHolder(View itemView) {
            super(itemView);
            textViewAttendanceId = itemView.findViewById(R.id.tvAttendanceId);
            textViewAttendanceUserId = itemView.findViewById(R.id.tvAttendanceUserId);
            textViewAttendanceEmail = itemView.findViewById(R.id.tvAttendanceEmail);
            textViewAttendanceDate = itemView.findViewById(R.id.tvAttendanceDate);
            textViewAttendanceLogin = itemView.findViewById(R.id.tvAttendanceLogin);
            textViewAttendanceLogout = itemView.findViewById(R.id.tvAttendanceLogout);
        }
    }
}
