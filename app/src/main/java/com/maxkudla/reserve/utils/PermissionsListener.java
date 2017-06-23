package com.maxkudla.reserve.utils;

import java.util.List;

/**
 * Created by Developer on 02.05.2017.
 */

public interface PermissionsListener {

    void onExplanationNeeded(List<String> permissionsToExplain);

    void onPermissionResult(boolean granted);

}
