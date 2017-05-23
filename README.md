# SharedPreferencesManager
===============

Makes easy to work with Shared Preferences


A small library containing a wrapper/helper for the shared preferences of Android.

With this library you can initialize the shared preference inside the onCreate of the Application class of your app.

```Java
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate() {
        super.onCreate();
	
	//To initialize and set data at the same time!
        SharedPreferencesManager sharedPreferencesManager = 
                new SharedPreferencesManager(this)
                .setValue(SharedPrefKeys.TRAIN.toString(), "MyTrainData")
                .setValue(SharedPrefKeys.BICYCLE.toString(), "MyBicycleData")
                .setValue(SharedPrefKeys.CAR.toString(), "MyCarData");
		
	//To get data
	String data = sharedPreferencesManager.getValue(SharedPrefKeys.BICYCLE.toString(), String.class);
	
	//To delete data
	sharedPreferencesManager.clearData(SharedPrefKeys.BICYCLE.toString());
	
	//To clear all data
	sharedPreferencesManager.clearAll();
    }
}
```

To store keys in an appropriate way, Enum can be used:
````Java
public enum SharedPrefKeys {
    TRAIN(0, "Train"),
    CAR(1, "Car"),
    PLANE(3, "Plane"),
    BICYCLE(4, "Bicycle");

    private int intValue;
    private String stringValue;

    private SharedPrefKeys(int value, String name) {
        this.intValue = value;
        this.stringValue = name;
    }

    public int toInt() {
        return intValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
    
}
````

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
	        compile 'com.github.emiraslan:SharedPreferencesManager:v1.0.3'
	}
