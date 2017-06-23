package com.maxkudla.reserve.presenter.main;

interface MainContract {

    interface View {
        void closeDrawer();
        void socketActivity(String queryId);
        void settingsActivity();
        void historyActivity();
        void logout();
        void shareApp();
        void intercomChat();
    }

}
