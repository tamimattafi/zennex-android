# Zennex
Zennex entrance test application

*ENGLISH:*

- **Working days**: 7 days
- **working time**: 4-5 hours average / day

**Libraries used**: 
- AndroidX
- Google Services (Maps)
- Room
- RxJava2
- Gson
- Dagger2
- Retrofit

**Project Structure**:

*Single-Activity App*
- **app**
  *contains all classes and objects related to the user interface*
  - **di** __ *dependency injection modules and components*
  - **mvp** __ *base classes for presentation and contracts*
  - **ui** __ *contains all stuff related to the view layer such as Application and Activity*
    - **custom** __ *contains everything was made by the developer in order to acheive a feature or a behaviour*
    - **fragments** __ *contains all app fragments*
      - **global** __ *contails base classes used for navigation*
        - **BaseFragment** __ *the base fragment that must be inherited from by every fragment*
        - **NavigationActivity** __ *the navigation manager that must be inherited from by the host activity*
        - **NavigationContract** __ *contains the methods that are used for communications between the host activity and its fragments*
        - **TabContract** __ *contains the methods that are used for communication between the tabs host and its tabs*
      - **main** __ contains main fragment and its sub fragments*
        - ...
        - ...
    - **Applicaion**
    - **ApplicationPreferences** __ *stores user preferences such as Language*
    - **ContextWrapper** __ *wraps activity and application context to change local on attachBaseContext*
    - **MainActivity**
- **model** __ *contains project models such as ListItem and Quote*
- **repository** __ *contains all the classes related to the repository layer*
- **utils** __ *helper classes to reduce code*
