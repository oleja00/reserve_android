package com.maxkudla.reserve.presenter.socket;

public interface SocketContract {

    interface View {
        void closeDrawer();
        void showServiceFragment(String serviceId);
        void showClientFragment();
        void showClientFragment(String status);
        void showGuest(String sId, long lId);
        void historyActivity();
        void settingsActivity();
        void logout();

    }

    interface EventDelegate {
    }

}
