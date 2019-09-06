<h1 align="center">
  <br>
  <a href="https://github.com/BiAtoms/PrefsHandler"><img src="https://raw.githubusercontent.com/BiAtoms/PrefsHandler/master/screenshots/PrefsLogo.png" alt="Neural Network Visualizer" width="500"></a>
</h1>

<h4 align="center">A useful library containing a helper methods and making easy to work with Shared Preferences.</h4>

<p align="center">
	<a href="#demo-app">Demo App</a> •
    <a href="#key-fearures">Key Features</a> •
  	<a href="#how-to-use">How To Use</a> •
  	<a href="#credits">Credits</a> •
  	<a href="#tools">Tools</a> •
  	<a href="#license">License</a>
</p>

### Demo App

<p align="center">
  <a href="https://github.com/DummyTeam/7-NeuralNetwork_OO">
    <img  width="250" src="https://raw.githubusercontent.com/BiAtoms/PrefsHandler/master/screenshots/sampleApp.gif" alt="Neural Network Visualizer">
  </a>
</p>


### Key Features

* Initialize PrefsHandler once
* Save values with keys
* Find values with keys
* Delete value associated with a key
* Save, Get and Delete List of Strings


### How To Use


```java
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate() {
        super.onCreate();

	//To initialize and set data at the same time!
     new PrefsHandler.Builder(this)
     	// Optional (default: Context.MODE_PRIVATE)
        .setSharedPrefsMode(Context.MODE_PRIVATE)
        // Optional (default: "MySharedPreferences")
        .setSharedPrefsTag("MySharedPreferences")
        .build();

	//To get data
	String dataStr = PrefsHandler.getValue("key1", String.class);
	Integer dataInt = PrefsHandler.getValue("key2", Integer.class);
	SomeModel someMode = PrefsHandler.getValue("key3", SomeModel.class);

	//To delete data
	PrefsHandler.clearData("key");

	//To clear all data
	PrefsHandler.clearAll();
    }
}
```


To store different objects in shared preferences, use same syntax:

````java
PrefsHandler
	.setValue("oneKey", myObject)
	.setValue("otherKey", myOtherObject)
````

Retrieving data can be as simple as:

````java
DataType data = PrefsHandler.getValue("oneKey", DataType.class);
OtherDataType otherData =
PrefsHandler.getValue("otherKey", OtherDataType.class);
````
If data does not exist, getValue method will return `null` value.

You can also add list of objects to Shared Preferences with the same way:

````java
// Creating Array list
ArrayList<String> myList = new ArrayList<>();

// Adding values
myList.add("First Strig");
myList.add("Second String");
myList.add("Third String");

// Saving list to the Shared Preferences
PrefsHandler.setValue("myList", myList);

// Getting list from Shared Preferences
List<String> listData = PrefsHandler.getListValue("myList");
````


### Usage

For detailed usage, check the sampleApp above.

### How to Setup
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.BiAtoms:PrefsHandler:v1.2'
	}
	


## License

MIT

---

> GitHub [BiAtoms](https://github.com/BiAtoms) &nbsp;&middot;&nbsp;
