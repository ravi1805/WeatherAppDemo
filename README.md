# WeatherAppDemo

Architecture (MVVM+Clean+RxJava+Dagger)
I have follow the MVVM clean architecture pattern, which will be generic to the application irrespective of how many API call
we have across the app. This Pattern will help us to segregate the code structure means independent all model like data, business logic, UI
MVVM help us to hold the information either app configuration change and also easy to write unit test independently.

## Requirement
- Android Studio
- Java 1.8

## Installation
Import the below project to the Android Studio or terminal of OS using git clone
"https://github.com/ravi1805/WeatherAppDemo.git"

## Skills
- Java
- Kotlin
- Android
- MVVM Pattern
- Retrofit
- RxJava2
- Dagger
- Clean architecture

# Enable developer mode in samsung device for install apk through terminal

Settings->About tablet->Software information-> Tap 5 times on Build Number. It will enable developer options.

Now In settings you can see developer options menu at bottom.
Tap on it and enable USB debugging options.
once tablet is connected with laptop using data cable then it will ask permission to access device, then click YES.

Device setup is done.

#Generate Android Build:
Steps 1:
Android Studio->Build->Clean Project
Android Studio->Build->Build Bundle(s)/APK(s)-> Build APK(s)

Steps 2: Once build generated

#For macbook ->
Go to terminal and run command -> brew install android-platform-tools
Now check adb devices, you can see list of devices.

#For Window ->
Go to android-platform-tools
Now check adb devices, you can see list of devices.

Now install apk using command adb install <path of apk>( you can drag and drop apk directly)



