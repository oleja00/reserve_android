package com.maxkudla.reserve.presenter.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.Constants;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.components.DaggerActivityComponent;
import com.maxkudla.reserve.presenter.base.BaseActivity;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.presenter.socket.SocketActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

//import com.maxkudla.reserve.injection.LoginActivityComponent;


public class LoginActivity extends BaseActivity implements LoginContract.View, LoginRouter {

    @BindView(R.id.buttonEnter)
    Button bEnter;

    @BindView(R.id.editTextPhone)
    EditText etPhone;

    @Inject
    LoginPresenter mLoginPresenter;


    @OnClick(R.id.buttonEnter)
    void onEnterClick() {
        mLoginPresenter.login(etPhone.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        DaggerActivityComponent
                .builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
        mLoginPresenter.setView(this);
        mLoginPresenter.setRouter(this);


    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.onDetachView();
        super.onDestroy();
    }

    @Override
    public void launchToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void launchToSocketScreen(int type) {
        Intent intent = new Intent(this, SocketActivity.class);
        intent.putExtra(Constants.USER_TYPE, type);
        startActivity(intent);
        finish();
    }

    @Override
    public void launchToSocketScreen(int type, String serviceId) {
        Intent intent = new Intent(this, SocketActivity.class);
        intent.putExtra(Constants.USER_TYPE, type);
        intent.putExtra(Constants.SERVICE_ID, serviceId);
        startActivity(intent);
        finish();
    }

    @Override
    public void showNumberError() {
        Toast.makeText(this, R.string.message_correct_number, Toast.LENGTH_SHORT).show();
    }
}
