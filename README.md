# PMU_Android_labs
```
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int buttonPressCount = О;     // Счетчик нажатий кнопки
    TextView buttonLabel;
    private long sTime = 0L;              // Счетчик времени
    private TextView TimeLabel;
    // Обработчик потока: обновляет сведения о времени
    private Handler Handler1 = new Handler();

    @Override
    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_main);
        if (sTime == 0L) {
            sTime = SystemClock.uptimeMillis();
            Handler1.removeCallbacks(TimeUpdater);
            // Добавляем Runnаblе-объект TimerUpdater в очередь
            // сообщений, объект будет запущен после задержки в 150 мс.
            Handler1.postDelayed(TimeUpdater, 150);
        }
        TimeLabel = (TextView) findViewById(R.id.text);
        buttonLabel = (TextView) findViewById(R.id.start);
        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListaner() {
            // Обработка нажатия кнопки
            public void onClick(View view) {
                buttonLabel.setТext(++buttonPressCount);
            }
        });
    }
    private Runnable TimeUpdater = new RunnaЬle() {
        public void run() {
            //Вычисляем время
            final long start = sTime;
            long millis = SystemClock.uptimeМillis() - start;
            int seconds = (int) (millis / 1000) ;
            int min = seconds / 60;
            seconds = seconds % 60;
            // Выводим время
            TimeLabel.setТext ("" + min + ":"
                    + String.format("%02d",seconds));
            // Задеркка в 300 мс
            Handler1.postDelayed(this, 300);
        }// void run ()
    };        
    @Override
    protected  void onPause(){
        //Удаляем Runnable-объект
        Handler1.removeCallbacks(TimeUpdater);
        super.onPause();
    }
    @Override
    protected  void onResume(){
        super.onResume();
        //Добавляем Runnable-объект
        Handler1.postDelayed(TimeUpdater, 150);
    }
}
```
Кроме метода postDelayed() вы можете использовать метод `postAtTime()`:
`postAtTime(RunnaЬle r, long uptimeMillis)`
В этом случае объект r добавляется в очередь сообщений, запуск объекта производится
во время, заданное вторым параметром (в миллисекундах).
Самый простой способ помещения объекта в очередь - метод `post()`, когда указывается
только помещаемый объект, но не указывается время выполнения объекта:
`post (RunnaЫe r)`
Подробно об обработчиках потока вы можете прочитать в руководстве разработчика:
http://developer.android.com/reference/android/os/llandler.html
