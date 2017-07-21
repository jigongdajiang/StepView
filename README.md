# StepView 记步指示器:

### 使用示例代码:
```Java
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
```
### 使用效果图:
![示例](https://github.com/jigongdajiang/StepView/raw/master/raw/example.gif)  
