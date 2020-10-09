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

 [What shoud I do?]
 
 - JobIntentService :  Short, finite task witn no constraint
 - Work Manager : task that are loner, have constraints, or be repeated periodically
 - Backgroun Service : state management with focused with UI
                       When a service is started using startService API.
                       By default services are background, meaning that if the system needs to kill them to reclaim more memory,
                       they can be killed without too much harm.
                       
 - Foreground Service : Visual or auditory elements
                       When a service is started using startForeground API to put the service in a foreground state,
                       where the system considers it to be something the user is actively aware of 
                       and not a candidate for killing when low on memory. 
             
  
 [WorkManager]

![image](https://user-images.githubusercontent.com/53125879/79498789-b71fc000-7fde-11ea-8887-6ea51d55d828.png)


## In this Project

 -  OneTimeWorkRequest with passing input/output parameters
 -  OneTimeWorkRequest Progress Update with using setProgress
 -  PeriodicWorkRequest with constraints
      
   
      
![image](https://user-images.githubusercontent.com/53125879/79510099-9876f480-7ff1-11ea-92c4-b546368c76f9.png)



reference link : 

https://medium.com/@saquib3705/schedule-task-with-workmanager-an-android-jetpack-library-fb1b7798dfc8
https://medium.com/androiddevelopers/workmanager-periodicity-ff35185ff006
