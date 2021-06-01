package study.online;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import study.online.fragment.Chat.Chat;
import study.online.fragment.Study.Study;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button study;
    private Button chat;
    private Button share;
    private Study study_fragment;
    private Chat chat_fragment;
    private ObjectAnimator scaleXAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        touch(study,R.color.blue,study_fragment);
    }
    private void init(){
        TextView loginid = findViewById(R.id.Me_MyId);
        loginid.setText(getIntent().getStringExtra("loginid").toString());

        study = findViewById(R.id.study);
        chat = findViewById(R.id.chat);
        share = findViewById(R.id.share);
        study.setOnClickListener(this);
        chat.setOnClickListener(this);
        share.setOnClickListener(this);

        study_fragment = new Study();
        chat_fragment = new Chat();

        //font
        Typeface font = Typeface.createFromAsset(getAssets(), "fa-solid-900.ttf");
        study.setTypeface(font);
        chat.setTypeface(font);
        share.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.study:
                touch(study,R.color.blue,study_fragment);
                break;
            case R.id.chat:
                touch(chat,R.color.red,chat_fragment);
                break;
            case R.id.share:
                touch(share,R.color.yellow,study_fragment);
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment,fragment);
        transaction.commit();
    }

    private void touch(Button button,Object colorid,Fragment fragment){
        replaceFragment(fragment);
        study.setBackgroundResource(R.color.white);
        study.setTextColor(0xff000000);
        chat.setBackgroundResource(R.color.white);
        chat.setTextColor(0xff000000);
        share.setBackgroundResource(R.color.white);
        share.setTextColor(0xff000000);
        button.setBackgroundResource((Integer) colorid);
        button.setTextColor(0xffffffff);
        scaleXAnimator = ObjectAnimator.ofFloat(button, "textSize", 15f, 20f, 15f);
        scaleXAnimator.setDuration(500);
        scaleXAnimator.start();
    }

}
