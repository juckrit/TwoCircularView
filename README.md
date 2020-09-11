# TwoCircularView

Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.juckrit:TwoCircularView:Tag'
	}
  
  
  Step 3. add to layout

	<com.example.twocircularview.TwoCircularView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:drawable1="@drawable/goku"
        app:drawable2="@drawable/ball" />
  
  
  