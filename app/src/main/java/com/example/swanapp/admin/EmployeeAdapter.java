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

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context context;
    private List<EmployeeRecord> employeeList;

    public EmployeeAdapter(Context context, List<EmployeeRecord> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adminemployeerecord, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        EmployeeRecord employee = employeeList.get(position);
        holder.tvEmployeeId.setText(employee.getId());
        holder.tvEmployeeName.setText(employee.getName());
        holder.tvEmployeeEmail.setText(employee.getEmail());
        holder.tvEmployeeLat.setText(employee.getLatitude());
        holder.tvEmployeeLong.setText(employee.getLongitude());
        holder.tvEmployeeDist.setText(employee.getDistrict());
        holder.tvEmployeePassword.setText(employee.getPassword());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmployeeId, tvEmployeeName, tvEmployeeEmail, tvEmployeeLat, tvEmployeeLong, tvEmployeeDist, tvEmployeePassword;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeId = itemView.findViewById(R.id.tvEmployeeId);
            tvEmployeeName = itemView.findViewById(R.id.tvEmployeeName);
            tvEmployeeEmail = itemView.findViewById(R.id.tvEmployeeEmail);
            tvEmployeeLat = itemView.findViewById(R.id.tvEmployeeLat);
            tvEmployeeLong = itemView.findViewById(R.id.tvEmployeeLong);
            tvEmployeeDist = itemView.findViewById(R.id.tvEmployeeDist);
            tvEmployeePassword = itemView.findViewById(R.id.tvEmployeePassword);
        }
    }
}
