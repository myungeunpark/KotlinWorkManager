# WorkManager

## What is WorkManager in Android?

Google released Android Jetpack in I/O 2018. WorkManager is part of Android Jetpack and an Architecture Component 
for scheduling the background task that guaranteed execution even if you navigate away from your app. 
It is much more useful api for scheduling the task and you will no more think or worry about what logic 
we should implement in different cases while scheduling task(periodic or one time).
WorkManager will do that for you. These APIs let you create a task and hand it off to WorkManager 
to run immediately or at an appropriate time.


 [Previous Backround Tasking]
  
![image](https://user-images.githubusercontent.com/53125879/79498728-a1aa9600-7fde-11ea-8e61-e0b2d8c3479f.png)

 [WorkManager]

![image](https://user-images.githubusercontent.com/53125879/79498789-b71fc000-7fde-11ea-8887-6ea51d55d828.png)


## In this Project

 -  OneTimeWorkRequest with passing input/output parameters
 -  OneThttps://medium.com/androiddevelopers/workmanager-periodicity-ff35185ff006imeWorkRequest Progress Update with using setProgress
 -  PeriodicWorkRequest with constraints
       
![image](https://user-images.githubusercontent.com/53125879/79502699-d28dc980-7fe4-11ea-9ded-04d113441173.png)



reference link : 

https://medium.com/@saquib3705/schedule-task-with-workmanager-an-android-jetpack-library-fb1b7798dfc8
https://medium.com/androiddevelopers/workmanager-periodicity-ff35185ff006
