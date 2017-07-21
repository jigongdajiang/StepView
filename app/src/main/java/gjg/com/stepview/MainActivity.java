package gjg.com.stepview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepView = (StepView) findViewById(R.id.step_view);
        stepView.setMaxStep(5);
    }

    public void changeStep(View view) {
        int step = new Random().nextInt(5);
        stepView.changeStep(step);
    }
}
