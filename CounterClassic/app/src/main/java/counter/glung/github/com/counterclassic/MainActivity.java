package counter.glung.github.com.counterclassic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView counterView = (TextView) findViewById(R.id.counter);
        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                updateCounter(counterView);
            }
        });

        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                updateCounter(counterView);
            }
        });
        updateCounter(counterView);
    }

    private void updateCounter(TextView counterView) {
        counterView.setText(Integer.toString(counter));
    }
}
