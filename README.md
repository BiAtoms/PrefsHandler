# PrefsHandler

Makes easy to work with Shared Preferences

A useful library containing a helper methods for the shared preferences in Android.

```Java
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate() {
        super.onCreate();
	
	//To initialize and set data at the same time!
                new PrefsHandler(this)
                .setValue("key1", "MyStringData")
                .setValue("key2", 12)
                .setValue("key3", new SomeModel());
		
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


## Usage

To store different objects in shared preferences, use same syntax:

````Java
PrefsHandler
	.setValue("oneKey", myObject)
	.setValue("otherKey", myOtherObject)
````
 
Retrieving data can be as simple as:

````Java
DataType data = PrefsHandler.getValue("oneKey", DataType.class);
OtherDataType otherData = 
PrefsHandler.getValue("otherKey", OtherDataType.class);
````
If data does not exist, getValue method will return `null` value.

You can also add list of objects to Shared Preferences with the same way:

````Java
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

For detailed usage, check the sampleApp above.

# How to Setup
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.emiraslan:SharedPreferencesManager:v1.0.1'
	}
	
