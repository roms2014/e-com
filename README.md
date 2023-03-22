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
`postAtTime(Runnable r, long uptimeMillis)`
В этом случае объект r добавляется в очередь сообщений, запуск объекта производится
во время, заданное вторым параметром (в миллисекундах).
Самый простой способ помещения объекта в очередь - метод `post()`, когда указывается
только помещаемый объект, но не указывается время выполнения объекта:
`post (Runnable r)`
Подробно об обработчиках потока вы можете прочитать в руководстве разработчика:
http://developer.android.com/reference/android/os/llandler.html

## 10.2 Службы

Служба (сервис, от англ. service) - компонент Аndrоid-приложения, запускаемый
в фоновом режиме без всякого интерфейса пользователя. Служба может быть за­
пущена или остановлена любым компонентом приложения. Пока служба запущена,
любой компонент может воспользоваться предоставляемыми службой возмож­
ностями. Приведем несколько примеров использования служб.\
Первая деятельность запускает службу, загружающую изображения на веб-сайт.
Вторая деятельность подключается к этой службе с целью узнать, сколько файлов
уже загружено, чтобы отобразить эту информацию пользователю.\
Аналогично можно использовать сервисы при копировании файлов с одной
SD-карты на другую - деятельность может подключаться к службе копирования
файла, чтобы узнать, сколько байтов скопировано. В случае с музыкальным проиг­
рывателем деятельность может подключаться к сервису, чтобы узнать позицию
воспроизводимого трека.\
Сервисы представляют собой особую организацmо приложения. В отличие от
активности (activity) они не требуют наличия визуального интерфейса. Сервисы
позволяют выполнять долговременные задачи без вмешательства пользователя.\
Все сервисы наследуются от класса service и проходят следующие этапы жизнен­
ного цикла:\
□ метод `oncreate()` - вызывается при создании сервиса;\
□ метод `onStartCoпrnand()` - вызывается при получении сервисом команды, от­
правленной с помощью метода `startService()`;\
□ метод `onВind()` - вызывается при закреплении клиента за сервисом с помощью
метода `bindService()`;\
□ метод `onDestroy()` - вызывается по завершении работы сервиса.\
Службы отлично подходят для выполнения постоянных или регулярных операций
и для обработки различных событий. Службы запускаются, останавливаются и кон­
тролируются разными компонентами приложения, в том числе другими службами,
деятельностями (активностями), а также приемниками широковещательных наме­
рений. Если вам нужно выполнять задачи, которые не зависят от взаимодействия
с пользователем, то службы - это лучший выбор.\
У запущенных служб приоритет выше, чем у невидимых деятельностей, поэтому
менее вероятно, что служба будет при распределении ресурсов завершена прежде­
временно. Даже если такое произойдет, то, в отличие от деятельности, ваша служба
автоматически перезапустится, как только окажется достаточно доступных ресур­
сов.\
Если служба взаимодействует с пользователем, то нужно повысить ее приоритет до
уровня деятельностей, которые работают на переднем плане. Это гарантирует, что
служба завершится только в крайнем случае, но при этом ваше приложение может
немного подтормаживать, что испортит от него впечатление. Жизненный цикл
службы представлен на рис. 10.2.\
Иногда вместо создания собственной службы проще использовать уже имеющиеся
системные службы (табл. 10.1 ). Все зависит от того, какую задачу вам нужно
решить.\
| Служба        | Описание           |
| ------------- |:------------------:|
| Account Service | Управляет пользовательскими учетными записями |
| Activity Service | Управляет деятельностями |
| Alarm Service | Отправляет разовые или периодические оповещения |
| Clipboard Service | Используется для управления буфером обмена |
| Connectivity Service | Управляет сетевыми соединениями |
| Download Service | Управляет загрузками |
| Input Method Service | Управляет текстовым вводом |
| Layout Inflater Service | Управляет компоновкой экрана |
| Location Service | Занимается отслеживанием координат |
| NFC Service | Управляет NFC |
| Notification Service | Управляет уведомлениями |
| Power Service | Управляет энергопотреблением |
| Search Service | Управляет поиском |
| Sensor Service | Используется для доступа к датчикам |
| Telephony Service | Управляет телефонными функциями |
| Vibrator Service | Управляет доступом к виброзвонку |
| Wallpaper Service | Управляет обоями домашнего экрана |
| WiFi Service | Управляет соединениями Wi-Fi |
![image](https://user-images.githubusercontent.com/79161746/226843251-0f28ed23-0169-4dd0-a423-fcaefd0dc0d3.png)\
Рассмотрим общий алгоритм создания службы. Первым делом нужно создать
класс, расширяющий класс `android.app.Service`. В Android Studio для этого нужно
щелкнуть правой кнопкой мыши на проекте и выбрать команду New | Java Class
(рис. 10.3).\
![image_2023-03-22_11-33-54](https://user-images.githubusercontent.com/79161746/226845160-49d75f31-ca64-45de-bcb0-96a20cc470f4.png)
По умолчанию будет создана заготовка класса сервиса, представленная в листинге 10.4, а.\
```
package com.example.den.myfirstservice;
puЬlic class MyService {
}
```
Изменим полученную «болванку», как показано в листинге 10.4, б.\
```
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
public class MyService extends Service{
@Override
     pubic IBinder onВind(Intent arg0){
         // TODO Auto-generated rnethod stuЬ
         return null;
     }
}
```
В файле манифеста приложения вам нужно описать сервис:\
`<service android:name=".MyService"></service>`\
Элемент `<service>` добавляется в элемент `<application>`. Пример файла манифеста
представлен в листинге 10.5.\
```
<?xml version="l.0" encoding="utf-8"?>
«manifest xmlns:android="http://schemas.android.com/apk/res/android" 
package="com.example.den.myfirstservice">
<application
android:allowBackup="true" 
android:icon="@mipmap/ic_launcher" 
android:label="@string/app_name" 
android:supportsRt1="true" 
android:theme="@style/AppTheme">
<service android:name=".MyService"></service>
<activity android:name=".MainActivity">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
</application>
</manifest>
```
Теперь нужно переопределить методы `onCreate()` и `onDestroy()`. Для этого щелкни­
те на имени класса в исходном коде и выберите команду Code | Override Methods
(рис. 10.4). Конечно, переопределить методы можно и без этого окна, но нужно же
продемонстрировать, что таковое в Android Studio имеется.\
![image_2023-03-22_12-02-04](https://user-images.githubusercontent.com/79161746/226852542-7a7973c6-c333-447c-b073-90188f6bd5ec.png)\
Метод `onВind()` нужно переопределять в том случае, если новый компонент привя­
зывается к этой службе после его создания.\
Запустите службу функцией `startservice()`. Остановить сервис можно функцией
`stopService()`. Представим, что у нас есть деятельность `MainActivity` с кнопками
Start и Stop, при этом кнопка Start запускает сервис `MyService`, описанный в классе
`MyService` (файл MyService.java). Обработчик нажатия этой кнопки будет выглядеть
так:\
```
startButton.setOnClickListener(new View.OnClickListener(){
 pubic void onClick(View view) {
  startService(new Intent(MainActivity.this,
   MyService.class));
 }
});
```
Обработчик кнопки Stop будет таким:
```
stopButton.setOnClickListener(new View.OnClickListener(){
 pubic void onClick(View v) {
  stopService(new Intent(MainActivity.this,
   MyService.class));
 }
});
```
