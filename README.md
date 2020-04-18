# 애플 갬성 측정기 - 우당탕탕 프로젝트

[**애플 갬성 측정기**](https://play.google.com/store/apps/details?id=com.udtt.applegamsung)🍎🍏

갬성 마케팅 하면 가장 먼저 떠오르는 애플. 가지고 있는 애플 제품들을 등록해 당신의 갬성지수를 측정 해 보세요.

⛔️본 어플리케이션은 2020년 최신 업데이트된 뇌피셜 자료를 95%의 신뢰 수준으로 반영하여 제작되었습니다⛔️

<p align="left">
<a href='https://play.google.com/store/apps/details?id=com.udtt.applegamsung&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='다운로드하기 Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/ko_badge_web_generic.png' width="240px"/></a>
<a href="https://apps.apple.com/kr/app/%EC%95%B1%EB%93%B1%EB%A0%A5%EC%B8%A1%EC%A0%95%EA%B8%B0/id1505275100?mt=8"><img src='https://linkmaker.itunes.apple.com/en-us/badge-lrg.svg?releaseDate=2020-04-16&kind=iossoftware&bubble=apple_music' width="220px"/></a>
</p>

[프로젝트 회고](https://www.notion.so/Story-47aecf5a462d41dd813d06846c8c98bc)



## Screenshots

<p align="center">
<img src="/image/whole_flow.gif" width = "32%"/>
<img src="/image/apple_box_flow.gif" width = "32%"/>
<img src="/image/apple_power_flow.gif" width = "32%"/>
</p>



## 소개

  지인들과 재미로 만들어 본 사이드 프로젝트입니다. "재미로" 라고는 썼지만 이런 작은 프로젝트를 통해 공부했던 것들을 모두 적용해보기도 하고, 다듬어 보기도하고 새로운 것들을 공부하기 위해서 최대한 많은 것들을 담아냈습니다. 그래서 작은 프로젝트 치고 파일량도 많고 레이어도 세세히 나누어져 있습니다! 😄😄

  요즈음 코로나 시국이라 OO테스트가 유행이길래 갑자기 생각난 아이디어로 시작 되었습니다. 더 많은 사람들이 발견해서 테스트 해봤으면 좋겠네요! 웹으로 만들었으면 참 좋았을텐데, 웹을 할 줄 모르는게 참 한이되었습니다.😔

  [Google Architecture Sample ](https://github.com/android/architecture-samples/tree/todo-mvvm-live-kotlin)코드를 정말 많이 참고하였습니다. 거의 모방 수준이라고 봐도 무방할 정도입니다. 그만큼 이 샘플 코드에 대해 많은 이해를 할 수 있는 기회였습니다!  

  사용한 것들 중.. 난독화 툴인 proguard를 드디어 처음 적용해 보았습니다. 컴파일 에러시 a.a.a.a.b(26) 이런식으로 난독화가 진행된 걸 보았을 때 성공했다는걸 알게되었습니다. 생각보다 별거 없더군요! (사실 그렇지 않았다고한다.)

  ViewPager2를 사용해보았습니다! 아주 좋습니다... 이젠 ViewPager2 만 쓸겁니다..
  이걸 사용하면서 한 Activity에 4개의 Fragment가 물려있었는데, ViewModel 분리에서 좀 애먹었습니다. 하나의 ViewModel로 모든 Fragment의 데이터들을 관리하기에는 코드 길이가 너무 길어져 각 Fragment별로 ViewModel을 할당하고, 각각 Fragment의 데이터를 불러오고 초기화 해주는 코드는 각각의 ViewModel에 분리하였습니다. 그래도 각 Fragment에서 공유해야하는 데이터들이 생각보다 많았기에 MainViewModel 코드를 최대한 줄였지만 여전히 하는 일도 많고 코드도 길어졌습니다 😥😥 MainViewModel도 더 분리를 하면 좋았을거같다 라는 생각이 문득 드네요.

  테스트코드는 없습니다 ㅠㅠ 나중에 추가하려구요... 테스트 코드까지 짤 여유는 없었습니다 😥



## Tech Stack & Libraries Used

<p align="left">
	<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
	<a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/kotlin-1.3.61-blue"/></a>
	<a href="https://developer.android.com/studio"><img src="https://img.shields.io/badge/android%20studio-3.6.1-brightgreen"/></a>
	<a href="https://developer.android.com/studio/releases/platforms"><img src="https://img.shields.io/badge/android%20SDK-21%2B-green"/></a>
</p>

* **Proguard Enabled** (Minify & Shrink resources)
* **JetPack**
* **Data Binding**
  * **LiveData**
  * **Lifecycle**
  * **ViewModel**
  * **Room Persistence**
* **Architecture**
  * **MVVM Architecture** (View - ViewModel - Model)
  * **Repository pattern**
* **Third Party**
  * **Lottie** Animation
  * **Koin** Dependency Injection
  * **FireBase**
    * FireStore
    * Crashlytics
    * Analytics
  * **Gson**
  * **AdMob**



##  Architecture Diagram

<img src="/image/architecture/PNG"/>




## 💻Develop Environment⚙️

- IDE - **Android Studio**

- Language - **Kotlin**

- Compile SDK Version - **29**

- Optimized Device - **Galaxy s8+**

- Mininum SDK Version - **21**

  

## 🔧Dependencies🔨

* **Develop**


| Name                   | Gradle                                                 |
| ---------------------- | ------------------------------------------------------ |
| Room                   | androidx.room:room-runtime:2.2.5                       |
|                        | androidx.room:room-compiler:2.2.5                      |
| Koin                   | org.koin:koin-androidx-scope:2.0.1                     |
| Firebase - Analytics   | om.google.firebase:firebase-analytics:17.3.0           |
| Firebase - Crashlytics | com.google.firebase:firebase-crashlytics:17.0.0-beta04 |
| Firebase - FireStore   | com.google.firebase:firebase-firestore-ktx:21.4.2      |
| Admob                  | com.google.android.gms:play-services-ads:19.0.1        |
| Gson                   | com.google.code.gson:gson:2.8.6                        |
| Lottie Animation       | com.airbnb.android:lottie:3.4.0                        |
| ViewPager2             | androidx.viewpager2:viewpager2:1.0.0                   |
| Google Material        | com.google.android.material:material:1.1.0             |

* **Test**

| Name              | Gradle                                           |
| ----------------- | ------------------------------------------------ |
| Junit 4           | junit:junit:4.12                                 |
|                   | android.arch.core:core-testing:1.1.1             |
|                   | androidx.test.ext:junit:1.1.1                    |
|                   | androidx.test:rules:1.2.0                        |
| Mockito to Kotlin | com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0 |
| Mockito           | org.mockito:mockito-core:2.25.0                  |
|                   | org.mockito:mockito-inline:2.21.0                |
| Koin Test         | org.koin:koin-test:2.0.1                         |



# License

Copyright 2020 Yun Hyeok.

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.