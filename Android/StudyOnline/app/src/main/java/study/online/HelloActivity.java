package study.online;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import study.online.Service.Chat_Socket_Service;

public class HelloActivity extends AppCompatActivity implements View.OnClickListener {
    private ServerStatusReceiver serverStatusReceiver;
    private String LoginId;
    private String LoginPassword;
    private EditText login_id;
    private EditText login_password;
    private LinearLayout login;
    private LinearLayout loading;
    private Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        init();
    }

    private void init(){
        login_id = findViewById(R.id.login_id);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        login = findViewById(R.id.login);
        loading = findViewById(R.id.loading);

        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button){
            LoginId = login_id.getText().toString();
            LoginPassword = login_password.getText().toString();
            if (LoginId.equals("") && LoginPassword.equals("")){
                login_button.setBackgroundResource(R.drawable.login_button_err);
            }else if (LoginPassword.equals("123456")){
                login.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                LoadAnimation();
                Receiver();
            }else{
                login_button.setBackgroundResource(R.drawable.login_button_err);
            }
        }
    }

    private void Receiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("server.connect.status");
        serverStatusReceiver = new ServerStatusReceiver();
        registerReceiver(serverStatusReceiver,intentFilter);
    }
    private void LoadAnimation(){
        Intent server_service = new Intent(this, Chat_Socket_Service.class);
        server_service.putExtra("id",LoginId);
        startService(server_service);
        Typeface font = Typeface.createFromAsset(getAssets(), "fa-solid-900.ttf");
        TextView loading1 = findViewById(R.id.loading1);
        TextView loading2 = findViewById(R.id.loading2);
        TextView loading3 = findViewById(R.id.loading3);
        TextView loading4 = findViewById(R.id.loading4);
        loading1.setTypeface(font);
        loading2.setTypeface(font);
        loading3.setTypeface(font);
        loading4.setTypeface(font);
        Animator(loading1, 2500,0);
        Animator(loading2, 2500,250);
        Animator(loading3, 2500,500);
        Animator(loading4, 2500,750);
    }
    private void Animator(TextView view,int time,long dtime){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "textSize", 0f, 20f, 20f, 0f);
        animator.setDuration(time);
        animator.setStartDelay(dtime);
        animator.setRepeatCount(-1);
        animator.start();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(serverStatusReceiver);
        super.onDestroy();
    }

    class ServerStatusReceiver extends BroadcastReceiver{
        private LinearLayout loading;
        private Context context;
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("status").toString();
            if(status.equals("Error")){
                Toast.makeText(context,"Server connect error",Toast.LENGTH_SHORT).show();
                this.context = context;
            }else{
                OK_animator();
            }
        }
        private void OK_animator(){
            loading = findViewById(R.id.loading);
            ObjectAnimator animator = ObjectAnimator.ofFloat(loading, "alpha",1f ,0f);
            animator.setStartDelay(4000);
            animator.setDuration(500);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    loading.setVisibility(View.GONE);
                    Intent intent = new Intent(HelloActivity.this,MainActivity.class);
                    intent.putExtra("loginid",LoginId);
                    startActivity(intent);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }
}
