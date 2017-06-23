package com.maxkudla.reserve.data.api;

import com.maxkudla.reserve.models.book.ResponseBook;
import com.maxkudla.reserve.models.cancel.CancelRequest;
import com.maxkudla.reserve.models.categories.CategoriesResponse;
import com.maxkudla.reserve.models.cities.CitiesResponse;
import com.maxkudla.reserve.models.client.ClientListRequestModel;
import com.maxkudla.reserve.models.login.LoginRequest;
import com.maxkudla.reserve.models.login.LoginResponse;
import com.maxkudla.reserve.models.options.OptionsResponse;
import com.maxkudla.reserve.models.options.RequestOptions;
import com.maxkudla.reserve.models.parametrs.ParametrsResponse;
import com.maxkudla.reserve.models.search.SearchResponse;
import com.maxkudla.reserve.models.service.ServiceListRequestModel;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;
import com.maxkudla.reserve.models.token.TokenResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Developer on 22.04.2017.
 */

public interface ApiService {

    @POST("token")
    Single<Response<TokenResponse>> checkToken(@Header("access_key") String header);

    @POST("login")
    Observable<Response<LoginResponse>> registerUser(@Body LoginRequest request);

    @GET("cities")
    Observable<Response<CitiesResponse>> getCities(@Header("access_key") String header);

    @GET("categories")
    Observable<Response<CategoriesResponse>> getCategories(@Header("access_key") String header);

    @GET("cooks")
    Observable<Response<ParametrsResponse>> getParametrs(@Header("access_key") String header);

    @GET("options")
    Single<Response<OptionsResponse>> getOptions(@Header("access_key") String header, @Query("category") String category);

    @PUT("requests")
    Single<Response<SearchResponse>> sendSearchParam(@Header("access_key") String header, @Body RequestOptions requestOptions);

    @GET("services/{service_id}")
    Single<Response<ResponseBook>> getServiceById(@Header("access_key") String header, @Path("service_id") String service_id);

    @GET("requests")
    Single<Response<ServiceListRequestModel>> getAllExistingService(@Header("access_key") String header, @Query("status") String status);

    @GET("requests")
    Single<Response<ClientListRequestModel>> getAllExistingClient(@Header("access_key") String header, @Query("status") String status);

    @POST("requests")
    Completable sendInfoToSocket(@Header("access_key") String header, @Body SendOfferModel model);

    @POST("requests/cancel")
    Completable sendClosed(@Header("access_key") String header, @Body CancelRequest queryId);

    @POST("services/{service_id}")
    Completable setOnline(@Header("access_key") String header, @Path("service_id")String serviceId, @Query("available") boolean isOnline);
}
