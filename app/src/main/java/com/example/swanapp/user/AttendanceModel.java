package com.example.swanapp.user;
import com.google.gson.annotations.SerializedName;
public class AttendanceModel {
        @SerializedName("username")
        private String username;

        @SerializedName("email")
        private String email;

        @SerializedName("date")
        private String date;

        @SerializedName("logintime")
        private String logintime;

        @SerializedName("logouttime")
        private String logouttime;



        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLogintime() {
            return logintime;
        }

        public void setLogintime(String logintime) {
            this.logintime = logintime;
        }

        public String getLogouttime() {
            return logouttime;
        }

        public void setLogouttime(String logouttime) {
            this.logouttime = logouttime;
        }
    }
    
