### CircleProgressBar

#### Usage
##### Gradle

1. Add the JCenter repository to your root build.gradle at the end of repositories.
```
allprojects {
    repositories {
        jcenter()
    }
}
```
2. Add the dependency
```
compile 'io.github.marktony:circle-progress-bar:1.1'
```


##### Maven
```
<dependency>
  <groupId>io.github.marktony</groupId>
  <artifactId>circle-progress-bar</artifactId>
  <version>1.1</version>
  <type>pom</type>
</dependency>
```

Attributes | Type | Default Value | For
---------- | ---- | ------------- | ------
circleWidth | dimension | 10 | width of the ring
maxProgress | integer | 100 | the max progress value
initProgress | integer | 0 | the initial value of progress
centreColor | color | 0 | color to fill the blank area of circle
circleColor | color | 0xff50c0e9 | the color of ring where is not in progress
progressColor | color | 0xffffc641 | the color of progress
textColor | color | 0xff000000 | the color of percent text
textSize | dimension | 25 | the size of percent text
startAngle | integer | -90 | the angle of beginning progress, -90 is the direction of 12 o'clock
textProgressEnable | boolean | false | show the percent text or not

##### Edit your layout XML
Add app namespace to your layout XML
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```
```XML
<io.github.marktony.circleprogressbar.CircleProgressBar
        android:layout_width="96dp"
        android:layout_height="96dp"
        android:id="@+id/circle_progress_bar"
        app:initProgress="5"
        app:textSize="16sp"
        app:textColor="@color/colorAccent"
        app:circleWidth="8dp"
        app:startAngle="0"
        app:circleColor="#009688"
        app:centreColor="#000000"
        app:textProgressEnable="true"
        app:maxProgress="100"
        app:progressColor="@color/colorAccent"/>
```

##### Or add it to your java file
```
CircleProgressBar circleProgressBar = new CircleProgressBar(this);
circleProgressBar.setmCircleWidth(24);
circleProgressBar.setMaxProgress(100);
circleProgressBar.setProgress(10);
circleProgressBar.setmCentreColor(R.color.colorPrimaryDark);
circleProgressBar.setmCircleColor(R.color.colorAccent);
circleProgressBar.setmTextColor(android.R.color.white);
circleProgressBar.setmTextSize(24);
circleProgressBar.setmStartAngle(0);
circleProgressBar.setmTextProgressEnable(true);
```

#### Screenshot
![screenshot](https://github.com/marktony/CircleProgressBar/blob/master/screenshots/screenshot1.png)

## Other repository of mine
+ [Paper Plane](https://github.com/marktony/ZhiHuDaily) -- MVP Architecture, Including the Content of Zhihu Daily, Guokr Handpick and Douban Handpick
+ [Zhihu Zhuanlan](https://github.com/marktony/zhuanlan) -- Analysis of Zhihu Zhuanlan's API + Unofficial Android App of Zhihu Zhuanlan
+ [Fanfou Handpick](https://github.com/marktony/FanfouHandpick) -- A Fanfou Handpick Client Developed by Kotlin
+ [Translator](https://github.com/marktony/Translator) -- A translation App Based on Android
+ [Reader](https://github.com/marktony/Reader) -- MVP + Volley + Gson, the content comes from Qiubai, Jiandan and Neihanduanzi

## Support Me
If you think, I deserve to get paid for my work, you can leave me a little money here by scanning the QR code through Wechat.

![Wechat_QRCode](https://github.com/marktony/ZhiHuDaily/blob/master/screenshots/wechat_qrcode.png)

#### License
```
Copyright 2016 lizhaotailang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
