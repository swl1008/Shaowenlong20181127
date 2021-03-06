package swl.bwie.com.zuoye2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZhuceActivity extends AppCompatActivity {

    private EditText moblie, pwd;
    private Button updata;
    private String updatamobile;
    private String updatapassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        moblie = findViewById(R.id.moblie);
        pwd = findViewById(R.id.pwd);

        updata = findViewById(R.id.updata);

        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatamobile = moblie.getText().toString();
                updatapassword = pwd.getText().toString();
                initData();
            }


        });

    }
    private void initData() {

        HttpUtil.getRequest("http://120.27.23.105/user/reg?mobile=" + updatamobile + "&password=" + updatapassword,
                MobileBean.class, new HttpUtil.CallBack<MobileBean>() {
                    @Override
                    public void getdata(MobileBean mobileBean) {
                        if(mobileBean.getMsg().equals("注册成功")){
                            Toast.makeText(ZhuceActivity.this, mobileBean.getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ZhuceActivity.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(ZhuceActivity.this,"请重新验证注册信息！！", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }

}
