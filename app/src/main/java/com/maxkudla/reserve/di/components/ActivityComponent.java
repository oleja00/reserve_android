package com.maxkudla.reserve.di.components;


import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.di.modules.DomainModule;
import com.maxkudla.reserve.di.modules.NavigationModule;
import com.maxkudla.reserve.presenter.common.drawer.RecyclerDrawer;
import com.maxkudla.reserve.presenter.common.socket_drawer.RecyclerDrawerSocket;
import com.maxkudla.reserve.presenter.history.HistoryActivity;
import com.maxkudla.reserve.presenter.history.history_client.HistoryClientFragment;
import com.maxkudla.reserve.presenter.history.history_service.HistoryServiceFragment;
import com.maxkudla.reserve.presenter.login.LoginActivity;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.presenter.main.categories.CategoriesFragment;
import com.maxkudla.reserve.presenter.main.map.MapSearchFragment;
import com.maxkudla.reserve.presenter.main.options.OptionsFragment;
import com.maxkudla.reserve.presenter.main.parametrs.ParametrsFragment;
import com.maxkudla.reserve.presenter.settings.SettingsActivity;
import com.maxkudla.reserve.presenter.settings.settings_details.SettingsFragment;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.book.BookATableFragment;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelDialog;
import com.maxkudla.reserve.presenter.socket.guest.GuestFragment;
import com.maxkudla.reserve.presenter.socket.request.RequestFragment;
import com.maxkudla.reserve.presenter.socket.service.ServiceFragment;
import com.maxkudla.reserve.presenter.socket.service.request_history.RequestHistoryFragment;
import com.maxkudla.reserve.presenter.socket.service.socket_service.SocketServiceFragment;
import com.maxkudla.reserve.presenter.socket.socket_client.SocketClientFragment;
import com.maxkudla.reserve.presenter.splash.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {DomainModule.class, NavigationModule.class})
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(SettingsFragment settingsFragment);

    void inject(CategoriesFragment categoriesFragment);

    void inject(ParametrsFragment parametrsFragment);

    void inject(MapSearchFragment mapFragment);

    void inject(BookATableFragment bookATableFragment);

    void inject(MainActivity mainActivity);

    void inject(SocketActivity socketActivity);

    void inject(OptionsFragment optionsFragment);

    void inject(SocketServiceFragment socketFragment);

    void inject(SocketClientFragment socketClientFragment);

    void inject(ServiceFragment serviceFragment);

    void inject(RequestHistoryFragment requestHistoryFragment);

    void inject(RequestFragment requestFragment);

    void inject(RecyclerDrawer recyclerDrawer);

    void inject(RecyclerDrawerSocket recyclerDrawerSocket);

    void inject(GuestFragment guestFragment);

    void inject(HistoryActivity historyActivity);

    void inject(HistoryClientFragment historyClientFragment);

    void inject(HistoryServiceFragment historyServiceFragment);

    void inject(CancelDialog cancelDialog);


}
