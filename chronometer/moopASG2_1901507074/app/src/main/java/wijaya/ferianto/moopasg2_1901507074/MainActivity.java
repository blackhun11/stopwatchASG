package wijaya.ferianto.moopasg2_1901507074;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private long timeWhenStopped = 0;
    private boolean stopClicked;
    private Chronometer timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeElapsed = (Chronometer) findViewById(R.id.chronometer);


    }
    // the method for when we press the 'reset' button
    public void resetButtonClick(View v) {
        timeElapsed.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        TextView secondsText = (TextView) findViewById(R.id.hmsTekst);
        secondsText.setText("0 seconds");
    }

    // the method for when we press the 'start' button
    public void startButtonClick(View v) {
        timeElapsed.setOnChronometerTickListener(new OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase() ;
                int h   = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String hh = h < 10 ? "0"+h: h+"";
                String mm = m < 10 ? "0"+m: m+"";
                String ss = s < 10 ? "0"+s: s+"";
                cArg.setText(hh+":"+mm+":"+ss);
            }
        });
        timeElapsed.setBase(SystemClock.elapsedRealtime()+ timeWhenStopped );
        timeElapsed.start();
        stopClicked = false;

    }

    // the method for when we press the 'stop' button
    public void stopButtonClick(View v){
        if (!stopClicked)  {
            TextView secondsText = (TextView) findViewById(R.id.hmsTekst);
            timeWhenStopped = timeElapsed.getBase() - SystemClock.elapsedRealtime();
            int seconds = (int) timeWhenStopped / 1000;
            secondsText.setText( Math.abs(seconds) + " seconds");
            timeElapsed.stop();
            stopClicked = true;
        }
    }

}