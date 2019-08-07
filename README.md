# Zennex
Zennex entrance test application

*ENGLISH:*

*Прокрутите вниз, если вы говорите по-русски*

- **Working days**: 7 days
- **working time**: 4-5 hours average / day

**Libraries used**: 
- AndroidX
- Google Services (Maps)
- Room
- RxJava2
- Gson
- Dagger2
- Retrofit2

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

Zennex вступительное тестовое приложение

*РУССКИЙ:*

- **Рабочие дни**: 7 дней
- **рабочее время**: 4-5 часов в день в среднем

**Использованные библиотеки**:
- AndroidX
- Google Services (Maps)
- Room
- RxJava2
- Gson
- Dagger2
- Retrofit2

**Структура проекта**:

*Приложение для одной активности*
- **app**
  *содержит все классы и объекты, связанные с пользовательским интерфейсом*
  - **di** __ *модули и компоненты внедрения зависимостей*
  - **mvp** __ *базовые классы для презентации и контрактов*
  - **ui** __ *содержит все материалы, связанные со слоем представления, такие как приложение и активность*
    - **custom** __ *содержит все, что было сделано разработчиком для того, чтобы получить возможность или поведение*
    - **fragments** __ *содержит все фрагменты приложения*
      - **global** __ *содержит базовые классы, используемые для навигации*
        - **BaseFragment** __ *базовый фрагмент, который должен наследоваться каждым фрагментом*
        - **NavigationActivity** __ *менеджер навигации, который должен быть унаследован от активности хоста*
        - **NavigationContract** __ *содержит методы, которые используются для обмена данными между хостом и его фрагментами*
        - **TabContract** __ *содержит методы, которые используются для связи между хостом вкладок и его вкладками*
      - **main** __ содержит основной фрагмент и его подфрагменты*
        - ...
        - ...
    - **Application**
    - **ApplicationPreferences** __ *хранит пользовательские настройки, такие как язык*
    - **ContextWrapper** __ *переносит действие и контекст приложения для изменения локального на attachBaseContext*
    - **MainActivity**
- **model** __ *содержит модели проекта, такие как ListItem и Quote*
- **repository** __ *содержит все классы, относящиеся к слою хранилища*
- **utils** __ *вспомогательные классы для сокращения кода*
