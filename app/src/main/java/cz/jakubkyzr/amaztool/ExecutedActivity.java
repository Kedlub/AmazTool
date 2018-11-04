package cz.jakubkyzr.amaztool;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.GONE;

public class ExecutedActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executed);
        context = this;

        /*(findViewById(R.id.sending_bar)).setVisibility(GONE);
        ImageView done = findViewById(R.id.sent_anim);
        done.setVisibility(View.VISIBLE);
        ((AnimationDrawable) done.getDrawable()).setOneShot(true);
        ((AnimationDrawable) done.getDrawable()).start();*/

        Thread thread = new Thread(executed);

        thread.start();
    }

    // Extracted from my Amazfit SMS app

    Runnable executed = new Runnable() {
        @Override
        public void run() {
            try {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        (findViewById(R.id.sending_bar)).setVisibility(GONE);
                        ImageView done = findViewById(R.id.sent_anim);
                        done.setVisibility(View.VISIBLE);
                        ((AnimationDrawable) done.getDrawable()).setOneShot(true);
                        ((AnimationDrawable) done.getDrawable()).start();
                    }
                });

                Thread.sleep(1100);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation a = AnimationUtils.loadAnimation(context, R.anim.fade_out);
                        a.reset();
                        TextView tv = findViewById(R.id.sending_text);
                        tv.clearAnimation();
                        tv.setAnimation(a);
                    }
                });


                //Log.d("SendingActivity","ranOnUiThread");

                //SmsService.instance.sent = false;

                Thread.sleep(1000);

                //Log.d("SendingActivity","finishing");

                finish();
                //overridePendingTransition(R.anim.fade_out, R.anim.fade_out);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
