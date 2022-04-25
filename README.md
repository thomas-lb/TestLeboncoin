Thomas Le Bihan

# Test Le bon coin
This project is an app displaying albums from an API.
Datas loaded by the API are stored in a local database and used if a network error happens on next launchs.
The lists scrolls are saved when configuration changed.
The app supports dark theme based on phone configuration.

## Architecture
The project is based on an MVVM/clean architecture architecture.

## Third party libraries
### App libraries
* Retrofit : Provide clean and convenient way to work API
* Room : Provide clean and convenient way to work with a local SQLite database
* Coil : An image loading library for Android backed by Kotlin Coroutines
* Koin : A easy to use and simple to setup dependency injection framework
* Navigation Component : A framework for navigating between 'destinations' within an Android application that provides a consistent API whether destinations are implemented as Fragments, Activities, or other components. 

### Test libraries
* Junit : A unit test framework 
* Mockk : A mock framework for test purpose
* Kluent : A “Fluent Assertions” library written specifically for Kotlin

### UI test libraries
* Kaspresso : a wrapper library of Espresso, Kakao and UIAutomator