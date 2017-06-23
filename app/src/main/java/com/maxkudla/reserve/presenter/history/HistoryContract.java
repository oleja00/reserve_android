package com.maxkudla.reserve.presenter.history;

import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.ReserveService;

/**
 * Created by Oleja on 05.06.2017.
 */

public interface HistoryContract {
    interface View {
        void launchToFragment(int type);
        void clientFragment(ReserveClient client);
        void serviceFragment(ReserveService service);
    }

    interface EventDelegate {

    }
}
