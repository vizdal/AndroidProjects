# Let's Meet

Let's Meet is an application that is developed with the vision of simplifying the way events are organized. The application allows the user to create and manage both public and private events. Events can range from a small private meeting to a big gathering. Events are also provided with a map functionality to track the location of attendees.

## Libraries

**firebase-database and firebase-core:** Used to provide a real-time database support to the application. At the moment we are storing event details in the database. Source [here](https://firebase.google.com/docs/database/).

**firebase-auth:**  Firebase provides easy to use login and sign up features through the firebase authorization library. Source  [here](https://firebase.google.com/docs/auth/).

**play-services-places and google-services:** Events should be put up in a place. To make it easier for the user we have used the places library given by Google to list the places near users current location. Source [here](https://developers.google.com/places/android-api/).

**com.github.yesterselga:password-strength-checker-android:v1.0:** When a user is trying to sign up we set criteria for passwords. This is achieved with the help of this amazing library. Source [here](https://github.com/yesterselga/password-strength-checker-android)

**com.github.igalata:Bubble-Picker:v0.1.1:** Every user selects a set of preferences to subscribe to events of those types. This selection is made fancy with the help of these bubbles. Source [here](https://github.com/igalata/Bubble-Picker)

**com.github.broakenmedia:MultiContactPicker:1.6:** while creating groups a user is asked to select contacts to add to that group. This is facilitated by the Multi-Contact Picker. Source [here](https://github.com/broakenmedia/MultiContactPicker)

**com.android.volley:volley:1.1.0:** HTTP requests and responses are managed by Volley. Source [here](https://developer.android.com/training/volley/index.html)

**com.sdsmdg.tastytoast:tastytoast:0.1.1:** We replaced normal toasts with the fancy TastyToasts. Source [here](https://github.com/yadav-rahul/TastyToast)

**com.github.jd-alexander:library:1.1.0** We tried to implement Google Directions to show the route between the users location and the event location. But that could not be completed. We have this library here for future use  by this project. Source: [here](https://github.com/jd-alexander/Google-Directions-Android)

## Installation Notes
The project was run and set up in the following environment. Please use this for best results.

  * **Firebase:** All firebase libraries used in this project are in 11.6.0 version.
  * **play-services-places:** version 11.6.0
  * **firebase-client-android**: version 2.5.0
  * **Android Studio**: version 2.5.0
  * **JRE**:  1.8.0
  * **Emulator Version**: 27.1.10


## Code Examples
**Problem 1: We needed a map and live location tracking**

We were using a library for getting Latitude and Longitude. We wanted to get rid of that for flexibility. We tried using FusedLocationProviderClient.
```
    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        } else {
            mobLocationRequest = new LocationRequest();
            mobLocationCallBack = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    for (Location location : locationResult.getLocations()) {
                        // Update UI with location data
                        System.out.println("Location Received"+location.getLongitude()+","+location.getLatitude());
                    }
                }
                };
        }
        mFusedLocationClient.requestLocationUpdates(mobLocationRequest, mobLocationCallBack,null);
        // Source: https://stackoverflow.com/questions/44992014/how-to-get-current-location-in-googlemap-using-fusedlocationproviderclient
```
**Problem 2: We needed a method to calculate the distance between two points** 

To get the distance between current location and event location, we had to use one request for every person in the event. We wanted to make an approximation. We used **Haversine Formula** to calculate approximate distance given the latitude and longitude. This was done to avoid unwanted requests.

```
    public static final int RADIUS_OF_EARTH = 6373;
    public static double distanceBetweenLatitudePoints(double lat1, double long1,Double lat2,Double long2){
        double diffLon = Math.toRadians(long2-long1);
        double diffLat = Math.toRadians((lat2-lat1));
        double a = ((Math.pow(Math.sin(diffLat/2),2)) + (Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * (Math.pow(Math.sin(diffLon/2),2))));
        double c = 2* Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return c*RADIUS_OF_EARTH;
        //Source: https://www.movable-type.co.uk/scripts/latlong.html
```

**Problem 2: We needed a method to Push Notification**

Initially, we were not able to push notification through firebase. It required server implementation which was not possible. Then we made use of **NotificationCompat.Builder** to get over this problem.
```
   private NotificationCompat.Builder notifyBuilder;
        public void NotifyMe(String m, String t, Class target) {
            notifyBuilder.setSmallIcon(R.mipmap.ic_launcher);
            notifyBuilder.setTicker("Hello");
            notifyBuilder.setWhen(System.currentTimeMillis());
            notifyBuilder.setContentTitle(t);
            notifyBuilder.setContentText(m);
            // notifyBuilder.setColor();
            //notifyBuilder.setSound();
            Intent intent = new Intent(this, target);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notifyBuilder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1, notifyBuilder.build());
        }
        //Source: https://developer.android.com/reference/android/app/NotificationManager.html
```
## Features 

### SignUp and Login

   A user can SignUp and Login to our application. We are using firebase authentication to facilitate SignUp and Login in our application. We have implemented basic validation for email and password. The length of the password must be at least 8 characters, it should have at least one special character, at least one lower case, upper case letter and a number. On signing up a confirmation mail is sent to the user. We allow the user to continue the first time. But to Login again one should verify the account.

### Create and View Events

  When the user navigates to the events tab we list a set of events that were already created by the user. On clicking on the add event button user is allowed to enter details in a form. On submitting that form we create a new event.

### Create and View Groups

   A group is a set of contacts for which an event is created. A user is allowed to create and view groups. The user is allowed to import a set of contacts from their phonebook and add them to a group.
   
### Help & FAQ

   Any application requires help to guide the user out of roadblocks in the application. This page has 3 sample questions and answers to these questions.
    
### Maps and Location Tracking

   We show the current location of the user in google maps with the help of the onboard GPS system.This feature can be triggered from the details page of an event.

### Profile Settings
 
   We list the users' username, sample image. The user will be able to edit all these details. We have given a feature to use the camera to upload images.
  
### Notifications

   The application pushes a notification on creating an event. This is done just to demonstrate its working. We are planning to use this to push notifications to few hours before the event.

### Search
    
   For any application with a huge amount of data, a search is required. In our application Events and Groups can have a lot of data. We have implemented a search for these features.
   
### Invitation

   On adding a contact to a group, an invitation message is sent to those contact thro SMS.

### RSVP

   After a user is invited to an event, they are given a way to communicate to the event owner about their availability through the RSVP feature.
   
#### Feedback to the Event Owner
 
   After an event is completed, the attendees of the event are given a way to provide a feedback to the event owner. These feedbacks are typically on the scale of 0 to 5.
   
#### Error handling and Logging
   
   With the extensive use of error handling functionalities, we have made sure that the application will not crash in most cases. Even if there is an exception we redirect to initial page instead of crashing the application.
   
#### Preference settings

   The user is allowed to set up their preferred events immediately after they sign up. These preferences are used to decide the recommended events.

#### Recommended Events

   Based on the event preferences chosen by a user, public events show up on the homepage. These filtered set of public events are called recommended events. 
   
#### Minimum Functionality

- Feature 1 Log in and Sign Up using email and password (Completed)
- Feature 2 Create events (Completed)

#### Expected Functionality

- Feature 1 Create New Events and Groups and manage the existing ones (Completed)
- Feature 2 Push Notifications (Completed)
- Feature 3 Maps (Completed)
- Feature 4 Groups (Completed)
- Feature 5 Setting up profile (Completed)
- Feature 6 Help & FAQ (Completed)
- Feature 7 Tracking (Completed)
- Feature 8 Search (Completed)

#### Bonus Functionality

- Feature 1 Invitation (Completed)
- Feature 2 RSVP (Completed)
- Feature 3 Feedback to the Event Owner (Completed)
- Feature 4 Error handling and Logging (Completed)
- Feature 5 Recommended Events (Completed)
- Feature 6 Preference settings (Completed)

## Sources

[1] “Icons,” Material Design. [Online]. Available: https://material.io/icons/#ic_supervisor_account.

[2] “android pick images from gallery,” Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/5309190/android-pick-images-from-gallery. [Accessed: 28-Feb-2018].

[3] dhivya-jayaraman, “dhivya-jayaraman/Firebase_Workshop,” GitHub, 07-Feb-2018. [Online]. Available: https://github.com/dhivya-jayaraman/Firebase_Workshop. [Accessed: 28-Feb-2018].

[4] “Capture Image from Camera and Display in Activity,” android - Capture Image from Camera and Display in Activity - Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/5991319/capture-image-from-camera-and-display-in-activity. [Accessed: 28-Feb-2018].

[5] “android custom Dialog with Button onClick event,” Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/38476045/android-custom-dialog-with-button-onclick-event. [Accessed: 28-Feb-2018].

[6] “Free high quality photos · Pexels,” Free Stock Photos. [Online]. Available: https://www.pexels.com/. [Accessed: 28-Feb-2018].

[7] www.movable-type.co.uk C. Veness, “Movable Type Scripts,” Calculate distance and bearing between two Latitude/Longitude points using haversine formula in JavaScript. [Online]. Available: https://www.movable-type.co.uk/scripts/latlong.html. [Accessed: 28-Feb-2018].

[8] M. Luella, L. 66 Samir, K. C. Rylan, and V. Sam, “The #1 Free Online Logo Maker - Used By 1,241,627 Start-Ups,” Logojoy. [Online]. Available: https://logojoy.com/. [Accessed: 07-Feb-2018].

[9] “Android AlertDialog How to Block Second Click on the Ok Button?” Java - Android AlertDialog How to Block Second Click on the Ok Button? - Stack Overflow, stackoverflow.com/questions/13768518/android-alertdialog-how-to-block-second-click-on-the-ok-button. [Accessed: 08-Apr-2018].

[10] “How to Clear Rate of Ratingbar in Android?” Stack Overflow, stackoverflow.com/questions/27565455/how-to-clear-rate-of-ratingbar-in-android?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa. [Accessed: 08-Apr-2018].


[11] “ANDROID UIUX KIT.” ANDROID UIUX KIT: wsdesign.in/home/detail/12. [Accessed: 08-Apr-2018].

[12] “Yadav-Rahul/TastyToast.” GitHub, 3 Apr. 2017, github.com/yadav-rahul/TastyToast. [Accessed: 08-Apr-2018].

[13] “ANDROID UIUX KIT.” ANDROID UIUX KIT: wsdesign.in/home/detail/97. [Accessed: 08-Apr-2018].

[14] “Fragment transaction animation: slide in and slide out,” android - Fragment transaction animation: slide in and slide out - Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/21026409/fragment-transaction-animation-slide-in-and-slide-out. [Accessed: 08-Apr-2018].

[15] “How to add colored border on cardview?,” android - How to add colored border on cardview? - Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/35369691/how-to-add-colored-border-on-cardview. [Accessed: 08-Apr-2018].

[16] E. Lüleci, “Android: Card View – AndroidPub,” AndroidPub, 14-Mar-2017. [Online]. Available: https://android.jlelse.eu/android-card-view-edb905e67cd6. [Accessed: 08-Apr-2018].

[17] “yesterselga/password-strength-checker-android,” GitHub. [Online]. Available: https://github.com/yesterselga/password-strength-checker-android. [Accessed: 08-Apr-2018].

[18] “igalata/Bubble-Picker,” GitHub, 24-Aug-2017. [Online]. Available: https://github.com/igalata/Bubble-Picker. [Accessed: 08-Apr-2018].

[19] “Maven Repository: com.wefika » flowlayout » 0.3.0,” MavenRepository. [Online]. Available: https://mvnrepository.com/artifact/com.wefika/flowlayout/0.3.0. [Accessed: 08-Apr-2018].

[20] “broakenmedia/MultiContactPicker,” GitHub. [Online]. Available: https://github.com/broakenmedia/MultiContactPicker. [Accessed: 08-Apr-2018].

[21] “jd-alexander/Google-Directions-Android,” GitHub, 04-Mar-2018. [Online]. Available: https://github.com/jd-alexander/Google-Directions-Android. [Accessed: 08-Apr-2018].

[22]“broakenmedia/MultiContactPicker,” GitHub. [Online]. Available: https://github.com/broakenmedia/MultiContactPicker. [Accessed: 09-Apr-2018].

[23] “MiguelCatalan/MaterialSearchView,” GitHub, 18-Jan-2017. [Online]. Available: https://github.com/MiguelCatalan/MaterialSearchView. [Accessed: 09-Apr-2018].

[24]. “How to validate Password Field in android,” Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/36574183/how-to-validate-password-field-in-android. [Accessed: 09-Apr-2018].

[25].“Android Notifications,” www.tutorialspoint.com, 08-Jan-2018. [Online]. Available: https://www.tutorialspoint.com/android/android_notifications.htm. [Accessed: 09-Apr-2018].
