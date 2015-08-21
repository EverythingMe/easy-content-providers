# Easy Content Providers
Easy integration with Android's built-in and custom content providers data. It even comes with a Sample app and Stetho extension for debugging purposes.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-easy--content--providers-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2334)


- [Why and when](#why-and-when)
- [Example Usage](#example-usage)
- [Inspecting provider data](#inspecting-provider-data)
- [Sample app](#sample-app)

## Why and When
We often experiment with new features, new apps, new ideas or we just code through a hackathon project that's built on-top of Android's content providers.. In such cases, we need tools to get what we want with minimum friction. This is where easy-content-providers come in - we can get all android stored data very easily and we can debug results through Chrome dev tools (based on Stetho implementation). 

The library uses reflection and some additional operations to make the ORM implementation generic, this is great for flexibility and POCs but might not be sufficient for high performance production apps. Also, if you don't define the exact columns you want, the mapping will happen on all columns (select * from ...). Sometimes, this is not what you want.

## Example Usage

Gradle:
```
dependencies {
    compile 'me.everything:providers-android:1.0.1'
}
```

Getting all calendars:

```java
CalanderProvider provider = new CalendarProvider(context);
List<Calendar> calendars = provider.getCalendars().getList();
```

**Full documentation** can be found at [the wiki](https://github.com/EverythingMe/easy-content-providers/wiki).



## Inspecting provider data

Gradle:
```
dependencies {
    compile 'me.everything:providers-stetho:1.0.1' 
}
```

Leveraging Stetho's ability to inspect SQLite databases, you can visualize, explore and modify all provider data.

![Screenshot](https://github.com/EverythingMe/easy-content-providers/wiki/images/stetho-providers.png)

In the above example, all providers can be seen expanded, "provider-calendar" is selected and it's items appear in the table to the right.

### Adding your own providers
And of course, you can see your own providers by registring them. In our example (check sample app):

```java
... 
providersStetho.registerProvider("provider-custom", "posts", new ProvidersStetho.QueryExecutor<Post>() {
    @Override
    public Data<Post> onQuery(String query) {
        PostsProvider provider = new PostsProvider(getApplicationContext());
        return provider.getPosts();
    }
});	
...
```

Which looks like so:

![Screenshot](https://github.com/EverythingMe/easy-content-providers/wiki/images/stetho-providers-custom.png)

### SQL Quering

Stetho allows to query SQLite databases from within it's console. Currently, Android providers are 
interactable with sql, with the following limitations:

- Only all-inclusive queries are possible `select * from "tablename"`

- Results may take a few seconds to appear. 

- Querying tables in `provider-calendar` can only be done like so:

	- `#events:id=3` - select from events where calendar id = 3
	- `#instances:id=100` - select from instances where event id = 100
	- `#reminders:id=100` - select from reminders where event id = 100
	- `#attendees:id=100` - select from attendees where event id = 100

	![Screenshot](https://github.com/EverythingMe/easy-content-providers/wiki/images/stetho-providers-sql.png)
	
We are inclined to rid ourselves of these limitations. Stay tuned for upcoming developments.


## Sample App

Try it out with our sample app. [Download the apk](https://github.com/EverythingMe/easy-content-providers/wiki/sample-app/app-debug.apk), run it on a connected device, open `chrome://inspect` in Chrome and start inspecting the providers to your delight.

Notice the app lists items from each provider. Take a look at [the app's source code](https://github.com/EverythingMe/easy-content-providers/tree/master/app/src) to see how this was implemented.

![Screenshot](https://github.com/EverythingMe/easy-content-providers/wiki/images/sample-app.png)

<br/>

------------
<br/>

### Author
[Roman Kushnarenko - sromku](https://github.com/sromku)

### License
Apache License 2.0
