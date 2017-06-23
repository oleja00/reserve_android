package com.maxkudla.reserve.data.api;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.models.OnLineModel;
import com.maxkudla.reserve.models.book.BookData;
import com.maxkudla.reserve.models.book.ResponseBook;
import com.maxkudla.reserve.models.cancel.CancelRequest;
import com.maxkudla.reserve.models.categories.CategoriesResponse;
import com.maxkudla.reserve.models.cities.CitiesResponse;
import com.maxkudla.reserve.models.client.ClientListRequestModel;
import com.maxkudla.reserve.models.login.LoginRequest;
import com.maxkudla.reserve.models.login.LoginResponse;
import com.maxkudla.reserve.models.options.Datum;
import com.maxkudla.reserve.models.options.OptionsResponse;
import com.maxkudla.reserve.models.options.RequestOptions;
import com.maxkudla.reserve.models.parametrs.ParametrsResponse;
import com.maxkudla.reserve.models.search.SearchResponse;
import com.maxkudla.reserve.models.service.ServiceListRequestModel;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;
import com.maxkudla.reserve.models.token.TokenResponse;
import com.maxkudla.reserve.utils.RetrofitException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Developer on 22.04.2017.
 */

public class RetrofitManager {

    private Retrofit mRetrofit;
    private ApiService retrofitService;

    @Inject
    public RetrofitManager(Retrofit retrofit) {
        mRetrofit = retrofit;
        retrofitService = mRetrofit.create(ApiService.class);
    }

    public Completable sendClosed(String queryId){
        CancelRequest cancelRequest = new CancelRequest();
        cancelRequest.setQuery_id(queryId);
        return retrofitService.sendClosed(getHeader(), cancelRequest);
    }

    public Single<ServiceListRequestModel> getAllExistingService(String status) {
        return retrofitService.getAllExistingService(getHeader(), status)
                .flatMap(responseBookResponse -> {
                    if (responseBookResponse != null && !responseBookResponse.isSuccessful()) {
                        return Single.error(
                                asRetrofitException(new HttpException(responseBookResponse)));
                    }
                    return Single.just(responseBookResponse.body());
                });
    }


    public Single<ClientListRequestModel> getAllExistingClient(String status) {
        return retrofitService.getAllExistingClient(getHeader(), status)
                .flatMap(responseBookResponse -> {
                    if (responseBookResponse != null && !responseBookResponse.isSuccessful()) {
                        return Single.error(
                                asRetrofitException(new HttpException(responseBookResponse)));
                    }
                    return Single.just(responseBookResponse.body());
                });
    }

    public Maybe<List<Object>> getHistory(String status) {
        return null;
    }

    public Single<BookData> getServiceById(String id) {
        return retrofitService.getServiceById(getHeader(), id)
                .flatMap(responseBookResponse -> {
                    if (responseBookResponse != null && !responseBookResponse.isSuccessful()) {
                        return Single.error(
                                asRetrofitException(new HttpException(responseBookResponse)));
                    }
                    return Single.just(responseBookResponse.body());
                })
                .map(ResponseBook::getData);
    }

    public Single<List<Datum>> getOptions(String category) {
        return retrofitService.getOptions(getHeader(), category)
                .flatMap(options -> {
                    if (options != null && !options.isSuccessful()) {
                        return Single.error(
                                asRetrofitException(new HttpException(options)));
                    }
                    return Single.just(options.body());
                })
                .map(OptionsResponse::getData);
    }

    public Single<SearchResponse> sendSearchParam(RequestOptions requestOptions) {
        // TODO: 07.05.2017 make param
        return retrofitService.sendSearchParam(getHeader(), requestOptions)
                .flatMap(searchResponseResponse -> {
                    if (searchResponseResponse != null && !searchResponseResponse.isSuccessful()) {
                        return Single.error(
                                asRetrofitException(new HttpException(searchResponseResponse)));
                    }
                    return Single.just(searchResponseResponse.body());
                });
    }

    public Observable<CitiesResponse> getCitiesRemote() {
        return getCities()
                .flatMap(new Function<Response<CitiesResponse>, ObservableSource<CitiesResponse>>() {
                    @Override
                    public ObservableSource<CitiesResponse> apply(@NonNull Response<CitiesResponse> voidResponse) throws Exception {
                        if (voidResponse != null && !voidResponse.isSuccessful()) {
                            return Observable.error(
                                    asRetrofitException(new HttpException(voidResponse)));
                        }
                        return Observable.fromArray(voidResponse.body());
                    }
                });
    }

    public Observable<CategoriesResponse> getCategoriesRemote() {
        return getCategories()
                .flatMap(new Function<Response<CategoriesResponse>, ObservableSource<CategoriesResponse>>() {
                    @Override
                    public ObservableSource<CategoriesResponse> apply(@NonNull Response<CategoriesResponse> voidResponse) throws Exception {
                        if (voidResponse != null && !voidResponse.isSuccessful()) {
                            return Observable.error(
                                    asRetrofitException(new HttpException(voidResponse)));
                        }
                        return Observable.fromArray(voidResponse.body());
                    }
                });
    }

    public Observable<ParametrsResponse> getParamRemote() {
        return getParam()
                .flatMap(new Function<Response<ParametrsResponse>, ObservableSource<ParametrsResponse>>() {
                    @Override
                    public ObservableSource<ParametrsResponse> apply(@NonNull Response<ParametrsResponse> voidResponse) throws Exception {
                        if (voidResponse != null && !voidResponse.isSuccessful()) {
                            return Observable.error(
                                    asRetrofitException(new HttpException(voidResponse)));
                        }
                        return Observable.fromArray(voidResponse.body());
                    }
                });
    }

    public Single<TokenResponse> checkTokenRemote() {
        return checkToken()
                .flatMap(new Function<Response<TokenResponse>, SingleSource<TokenResponse>>() {
                    @Override
                    public SingleSource<TokenResponse> apply(@NonNull Response<TokenResponse> voidResponse) throws Exception {
                        if (voidResponse != null && !voidResponse.isSuccessful()) {
                            return Single.error(
                                    asRetrofitException(new HttpException(voidResponse)));
                        }
                        return Single.just(voidResponse.body());
                    }
                });
    }


    public Observable<LoginResponse> registerUserRemote(LoginRequest loginRequest) {
        return registerUser(loginRequest)
                .flatMap(new Function<Response<LoginResponse>, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(@NonNull Response<LoginResponse> voidResponse) throws Exception {
                        if (voidResponse != null && !voidResponse.isSuccessful()) {
                            return Observable.error(
                                    asRetrofitException(new HttpException(voidResponse)));
                        }

                        return Observable.fromArray(voidResponse.body());

                    }
                });
    }

    private Observable<Response<LoginResponse>> registerUser(LoginRequest loginRequest) {
        return retrofitService.registerUser(loginRequest);
    }

    private Single<Response<TokenResponse>> checkToken() {
        return retrofitService.checkToken(getHeader());
    }

    private Observable<Response<CitiesResponse>> getCities() {
        return retrofitService.getCities(getHeader());
    }

    private Observable<Response<CategoriesResponse>> getCategories() {
        return retrofitService.getCategories(getHeader());
    }

    private Observable<Response<ParametrsResponse>> getParam() {
        return retrofitService.getParametrs(getHeader());
    }

    private RetrofitException asRetrofitException(Throwable throwable) {
        // We had non-200 http error

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response response = httpException.response();
            return RetrofitException.httpError(
                    response.raw().request().url().toString(),
                    response,
                    mRetrofit);
        }
        // A network error happened
        if (throwable instanceof java.io.IOException) {
            return RetrofitException.networkError((java.io.IOException) throwable);
        }

        // We don't know what happened. We need to simply convert to an unknown error

        return RetrofitException.unexpectedError(throwable);
    }

    private String getHeader() {
        return App.getAuthTokenHolder().getToken();
    }

    public Completable sendInfoToSocket(SendOfferModel sendOfferModel) {
        return retrofitService.sendInfoToSocket(getHeader(), sendOfferModel);
    }

    public Completable setOnline(OnLineModel onLineModel) {

        return retrofitService.setOnline(getHeader(), onLineModel.getServiceId(), onLineModel.isOnline());
    }


//    private GsonConverterFactory createGsonConverter() {
//        GsonBuilder builder = new GsonBuilder();
//        builder.serializeNulls();
//        builder.setExclusionStrategies(new ExclusionStrategy() {
//            @Override
//            public boolean shouldSkipField(FieldAttributes f) {
//                return f.getDeclaringClass().equals(RealmObject.class);
//            }
//
//            @Override
//            public boolean shouldSkipClass(Class<?> clazz) {
//                return false;
//            }
//        });
//        builder.registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
//        }.getType(), new TypeAdapter<RealmList<RealmString>>() {
//            @Override
//            public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
//
//            }
//
//            @Override
//            public RealmList<RealmString> read(JsonReader in) throws IOException {
//                RealmList<RealmString> list = new RealmList<RealmString>();
//                try {
//                    in.beginArray();
//                    while (in.hasNext()) {
//                        list.add(new RealmString(in.nextString()));
//                    }
//                    in.endArray();
//
//                } catch (java.io.IOException e) {
//                    e.printStackTrace();
//                }
//
//                return list;
//
//            }
//        });
//
//
//        return GsonConverterFactory.create(builder.create());
//    }
}
