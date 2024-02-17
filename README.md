I built an Android App that displays food trucks and dining halls at Penn along with relevant information. Additional features include a map of all the food trucks, 
sorting the trucks by distance and rating, and date/time integration to update dining hall status/hours.
### How to Run
This project can be used on desktop by opening it in Android Studio and running it on a virtual mobile device. Users will be prompted upon opening the app to approve a permission request to access their location. Please select **precise** 
tracking and approve it in order to use the sort-by-distance feature. Finally, due to some of the date and time features, it requires a minimum SDK of 26. 

### Implementation
I chose to make three fragments, one each for food trucks, map, and dining halls. 
The food truck fragment is the default. It uses a RecyclerView to display a list of food trucks which users can scroll through.
By going to the action bar and selecting the three dots at the top right, users can sort the food trucks by distance, increasing rating, and decreasing rating. Each food truck item can be clicked to 
switch to a new activity and get a full page on the food truck. We were only given certain details the page doesn't add much more besides some phone numbers and hours for some of the trucks. If I had access to the information, I would ideally want to add
some more information to fill up the page, like menus and pictures. To exit from the full page, simply use the back button built into Android.
The map fragment displays the locations of all the food trucks. It uses Google Maps API and displays markers for all the food trucks and the users. When the markers are clicked, you can see the food truck's name. Users can 
also zoom and move around the map.
The dining hall fragment uses the present time to display up-to-date information on the dining halls. I used Retrofit to fetch and make the HTTP requests to the API provided. Then, I used GSON to parse the JSON file and store all the information in 
a dining hall object. Each dining hall object had general fields like name and address and was connected to a list of dining day objects. The dining day objects corresponded to the dining hall's unique hours and statuses on each day of the week. Then,
each dining day object also contains a list of day part objects which contains info about the dining hall's hours during each part of the day. This information is then displayed along with a picture of the dining hall in a RecylerView.

