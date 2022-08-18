## Github App

An android app built using Kotlin that consumes the Github API to search for Github profiles and more information on the user. It has been built following Clean Architecture Principles, MVVM Architecture/Pattern and Modern Jetpack components.

## Architecture (Clean Architecture).
Clean Architecture combines a group of practices that produce systems with the following characteristics:
- Testable.
- UI-independent (the UI can easily be changed without changing the system).
- Independent of databases, frameworks, external agencies, and libraries.

The following scheme presents the architecture from the UI to the backend or database:

<p align="center"><img src="screenshots/clean_arch.png" alt="Clean Architecture Diagram"></p>

As you can see in the image, an Android application with Clean Architecture gathers the layers into three modules:
1. Presentation - presents data to a screen and handle user interactions.
    - Components:
        - Activities/ Fragments
        - ViewModels

2. Domain - which contains business logic
    - Components:
        - Models (Entities)
        - UseCases
        - Repositories Implementation

3. Data - which manages application data eg. retrieve data from the network, manage data cache.
    - Components:
        - Local data sources
        - Remote data sources
        - Repositories Interfaces

## Testing 

### Local Database
To test the deletion, insertion and getting of user profile and repository
- <p align="center"><img src="screenshots/daotest.png" alt="Clean Architecture Diagram"></p>

### UseCases
To test the domain's use-cases and repositories and if they make the right call
- <p align="center"><img src="screenshots/usecasetest.png" alt="Clean Architecture Diagram"></p>

### ViewModels
To test the logic of the app
- <p align="center"><img src="screenshots/viewmodeltest.png" alt="Clean Architecture Diagram"></p>

### Github API and Fragments
This integration test was done under an Android 11 version device
Did some integration tests to test the Github API is called successfully
Did some integration test to test if the search input layout is shown and the recyclerview holding the list of repositories is shown. Also if the search button is is clickable
- <p align="center"><img src="screenshots/intergrationtest.png" alt="Clean Architecture Diagram"></p>

### ScreenShots
<img src="screenshots/screenshot1.png" width="260">&emsp;
<img src="screenshots/screenshot2.png" width="260">
<img src="screenshots/screenshot3.png" width="260">
