#PMU_Android_labs
```
import android.widget.Button;
import android.widget.TextView;

pubic class MainActivity extends AppCompatActivity {
private int buttonPressCount = О;     // Счетчик нажатий кнопки
TextView buttonLabel;
private long sTime = 0L;              // Счетчик времени
private TextView TimeLabel;
// Обработчик потока: обновляет сведения о времени
private Handler Handlerl = new Handler();

@Override
protected void onCreate(Bundle savedinstanceState)
super.onCreate(savedinstanceState);
setContentView(R.layout.activity_main);
if (sTime == 0L) {
sTime = SysternClock.uptimeMillis();
Handlerl.rernoveCallbacks(TirneUpdater);
// Добавляем RunnаЫе-объект TimerUpdater в очередь
// сообщений, объект будет запущен после задержки в 150 мс.
Handlerl.postDelayed(TimeUpdater, 150);
}
TimeLabel = (TextView) findViewByid(R.id.text);
buttonLaЬel = (TextView) findViewByid(R.id.start);
Button startButton = (Button) findViewByid (R. id. start) ·;
startвutton.setOnClickListaner (new View. OnClickListaner(){
// ОбраБотка нажатия кнопки
puЬlic void onClick (View view) {
ЬuttonLaЬel.setТext(++ЬuttonPressCount);
}
});
private Runnable TimeUpdater = new RunnaЬle() {
pubic void run() {
//Вычисляем время
final long start = sTime;
lonq millis • SystemClock. uptimeМillis() - start;
int seconds = (int) (millis / 1000) ;
int min = seconds / 60;
seconds = seoonds % 60;
// Выводим время
TimeLabel.setТext ("" + min + ":"
+ String.format("%02d",seconds));
// Задеркка в 300 мс
Handlerl.postDelayed(this, 300);
}// void run ()
};
```
