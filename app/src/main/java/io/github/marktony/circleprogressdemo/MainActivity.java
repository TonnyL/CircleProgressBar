package io.github.marktony.circleprogressdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.marktony.circleprogressbar.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    private CircleProgressBar progressBar1;
    private CircleProgressBar progressBar2;

    private int progress1;
    private int progress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = (CircleProgressBar) findViewById(R.id.cpb_1);
        progressBar2 = (CircleProgressBar) findViewById(R.id.cpb_defined);

        progress1 = progressBar1.getCurrentProgress();
        progress2 = progressBar2.getCurrentProgress();

        progressBar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1 += 5;
                progressBar1.setProgress(progress1);
            }
        });

        progressBar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress2 += 10;
                progressBar2.setProgress(progress2);
            }
        });

    }
}
