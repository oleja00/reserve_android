package com.maxkudla.reserve;

/**
 * Created by Developer on 22.04.2017.
 *
 */

public class Constants {
    public class Realm {
        public static final String STORAGE_MAIN = "MainStorage.realm";
    }
    public class ApiConstants{
        public final static String BASE_URL = "http://207.154.254.225:3000/api/v1/";
        public final static String PHOTO_URL = "http://207.154.254.225:3000";
        public final static String PHOTO_URL_BOOK = "http://207.154.254.225:3000/services/$PIC_TO_SHOW$/logo.jpg";
    }

    public class MapsConstants{
        public static final String LAT_LNG = "LAT_LNG";
        public static final String SERVICE_NAME = "SERVICE_NAME";
    }

    public  static final int SUCCESS_RESULT=0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME = Constants.class.getPackage().getName();
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String ADDRESS_DATA_KEY1 = PACKAGE_NAME +
            ".ADDRESS_DATA_KEY1";
    public static final String ADDRESS_DATA_KEY2 = PACKAGE_NAME +
            ".ADDRESS_DATA_KEY2";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";

    public static final String USER_TYPE = "USER_TYPE";
    public static final int USER_CLIENT = 1;
    public static final int USER_SERVICE = 0;
    public static final String QUERY_ID = "QUERY_ID";
    public static final String STATUS = "STATUS";

    public static final String SERVICE_ID = "SERVICE_ID";
}
