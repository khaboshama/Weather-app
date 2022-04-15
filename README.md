# Weather-app
A simple android application to display current weather conditions. This app will show
- Search for a city by name.
- Detect the weather of current user location incase the user enabled device GPS. 

## project structure
This project implements clean architecture, MVVM structure pattern, Retrofit for APIs, Coroutines, LiveData and DataBinding.

## Screenshots resources
<br>
<p align="start">
   <img width="250" src="https://user-images.githubusercontent.com/5102649/142738832-f303ef85-0be4-4105-9317-654de472deb3.png">
   <img width="250" src="https://user-images.githubusercontent.com/5102649/142738868-739eb9e8-2cf1-4782-86ed-525d408bb279.png">  
</p>


## Guide to app architecture
<br>
<p align="center">
  <img src="https://user-images.githubusercontent.com/5102649/133154141-724fc256-a806-410b-bde0-0402ea0fd5a1.PNG">
</p>
<br>

## The app has the following packages
1. **common**: It contains all the base classes of the application.
2. **data**: It contains the database, shared preference, network classes of the application.
3. **di**: It contains setup of koin dependency injection.
4. **feature**: It contains the packages of each feature in the application. Each feature contains two packages, one for module and one for screens.
5. **utils**: It contains the utils classes of the application.
6. **constants**: It contains any constants values in the application.


## Library reference resources
1. Coroutines: https://codelabs.developers.google.com/codelabs/kotlin-coroutines/
2. Retrofit: https://square.github.io/retrofit/
3. DataBinding: https://developer.android.com/topic/libraries/data-binding
4. Koin: https://insert-koin.io/docs/quickstart/android/
5. Glide: https://github.com/bumptech/glide

## License
```
   Copyright (C) 2019 khaled Aboshama

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
