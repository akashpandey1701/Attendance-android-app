package com.example.swanapp.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swanapp.R;
import com.example.swanapp.adapter.AttendanceAdapter;
import com.example.swanapp.models.AttendanceEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class homeFragment extends Fragment {

    private TextView textClockInTime;
    private TextView textClockOutTime;
    private RecyclerView recyclerView;
    private AttendanceAdapter adapter;
    private List<AttendanceEntry> dummyAttendanceList;

    private static final String PREFS_NAME = "AttendancePrefs";
    private static final String markedIn_time = "markInTime";
    private static final String markedOut_time = "lastMarkOutTime";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textClockInTime = view.findViewById(R.id.textClockInTime);
        textClockOutTime = view.findViewById(R.id.textClockOutTime);
        recyclerView = view.findViewById(R.id.recyclerViewPastAttendance);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        dummyAttendanceList = createDummyData(); // Create dummy data
        adapter = new AttendanceAdapter(requireContext(), dummyAttendanceList);
        recyclerView.setAdapter(adapter);


        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String markInTime = prefs.getString(markedIn_time, "");
        String markOutTime = prefs.getString(markedOut_time, "");


        textClockInTime.setText(markInTime);
        textClockOutTime.setText(markOutTime);

        return view;
    }

    private List<AttendanceEntry> createDummyData() {
        List<AttendanceEntry> dummyData = new ArrayList<>();
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("09:00 AM", "06:00 PM"));
        dummyData.add(new AttendanceEntry("10:00 AM", "07:00 PM"));
        dummyData.add(new AttendanceEntry("11:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("12:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("10:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("09:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("07:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("06:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("05:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("04:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("03:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("02:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));
        dummyData.add(new AttendanceEntry("08:00 AM", "05:00 PM"));




        return dummyData;
    }


    public void updateClockInTime() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Calendar calendar = Calendar.getInstance();
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.getTime());

        editor.putString(markedIn_time, currentTime);
        editor.apply();

        textClockInTime.setText(currentTime);
    }

    public void updateClockOutTime() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Calendar calendar = Calendar.getInstance();
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.getTime());

        editor.putString(markedOut_time, currentTime);
        editor.apply();

        textClockOutTime.setText(currentTime);
    }
}
