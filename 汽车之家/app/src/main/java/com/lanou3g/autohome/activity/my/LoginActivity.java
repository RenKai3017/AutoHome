package com.lanou3g.autohome.activity.my;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.lanou3g.autohome.base.BaseActivity;
import com.lanou3g.autohome.bean.db.QQBean;
import com.lanou3g.autohome.tool.InterfaceValues;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/8/18.
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
    private EditText loginName, loginPassword;
    private ImageView clearIv;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        findView(R.id.activity_login_return).setOnClickListener(this);
        loginName = findView(R.id.activity_login_user_et);
        loginPassword = findView(R.id.login_password);
        findView(R.id.activity_login_btn).setOnClickListener(this);
        clearIv = findView(R.id.clear);
        clearIv.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        loginName.addTextChangedListener(textWatcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_return:
                finish();
                break;
            case R.id.activity_login_btn:
                if (loginName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (loginPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    String name = loginName.getText().toString();
                    Intent loginIntent = new Intent(InterfaceValues.BROAD_LOGIN);
                    loginIntent.putExtra("login", 1);
                    loginIntent.putExtra("name", name);
                    sendBroadcast(loginIntent);
                    QQBean bean = new QQBean();
                    bean.setQQName(name);
                    bean.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(AutoApplication.getContext(), "添加数据成功:" + s, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AutoApplication.getContext(), "失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    finish();
                }
                break;
            case R.id.clear:
                loginName.setText("");
                break;
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (loginName.getText().toString() != null && !loginName.getText().toString().equals("")) {
                clearIv.setVisibility(View.VISIBLE);
            } else {
                clearIv.setVisibility(View.INVISIBLE);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
