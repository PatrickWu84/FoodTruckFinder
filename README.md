 ## Penn Labs Android Technical Challenge 
The requirements for this challenge was to build an Android App that displayed food trucks and dining halls at Penn along with relevant information. Additional features include a map of all the food trucks, 
sorting the trucks by distance and rating, and date/time integration in order to update dining hall status/hours. Coming into this challenge, I wouldn't say I was super familiar with Android development so one of my main goal was to learn and improve my skills
through the process of completing the challenge.
### How to Run
This project can be used on desktop by opening it in Android Studio and running it on a virtual mobile device. Users will be prompted upon opening the app to approve a permission request to access their location. Please select **precise** 
tracking and approve it in order to use the sort by distance feature. Finally, due to some of the date and time features, it requires a minimum SDK of 26. 

### Implementation
I chose to make three fragments, one each for food trucks, map, and dining halls. 
The food truck fragment is the default. It uses a RecyclerView to display a list of food trucks which users can scroll through.
By going to the action bar and selecting the three dots at the top right, users can sort the food trucks by distance, increasing rating, and decreasing rating. Each food truck item can be clicked in order to 
switch to a new activity and get a full page on the food truck. We were only given certain details the page doesn't add much more besides some phone numbers and hours for some of the trucks. If I had access to the information, I would ideally want to add
some more information to fill up the page, like menus and pictures. In order to exit from the full page, simply use the back button built into Android.
The map fragment displays the locations of all the food trucks. It uses google maps API and displays markers for all the food trucks and the users. When the markers are clicked, you can see the name of the food truck. Users can 
also zoom and move around the map.
The dining hall fragment uses the present time to display up-to-date information on the dining halls. I used Retrofit to fetch and make the HTTP requests to the API provided. Then, I used Gson to parse the JSON file and store all the information in 
a dining hall object. Each dining hall object had general fields like name and address, and were connected to a list of dining day objects. The dining day objects corresponded to the dining hall's unique hours and statuses on each day of the week. Then,
each dining day object also contains a list of day part object which contains info about the dining hall's hours during each part of the day. This information is then displayed along with a picture of the dining hall in a RecylerView.

Throughout the process, I mainly referred to online documentation and some YouTube tutorials whenever I encountered concepts I was unfamiliar with or got stuck. 

Design-wise, my main goal was just to not make the project look super ugly. I definitely spent more of my time working out the logic and learning how to impelment new features instead of thinking about the style. However, I like design a lot
so if I had more time I would want to make it look nicer. I basically just followed the screenshots that were given in the write up.

####Some notes:
- The project is named "justfoodtrucks" but this is not accurate. At first I thought I would only havae time for the food trucks part. I didn't want to go through hassle of all the steps to rename the project.
- There's a good amount of information that I processed which I didn't utilize, especially with the dining halls. If given more time, I wanted to make the dining halls also clickable, similar to the food trucks so that information like the address
address and weekly schedule could be displayed. I also would add some sort of sorting/filtering feature because currently, the dining halls are just in order they were given in the original file.
- I think I probably could have implemented the user location feature in a better way. I ran into a lot of bugs when doing this part and spent the most time on it. I think got it working but it still can be a bit buggy sometimes. I thought there should be a way to pass the user location directly into the
fragments, but I couldn't figure that out so I ended up having a new ViewPageAdapter being set up each time the location is updated. I would definitely focus on spending some more time trying to figure this out.


##Final Thoughts
Overall, this was definitely a time consuming challenge but I had fun doing it and enjoyed the learning process. I wish I had more time to flesh it out but I'm decently proud of the final outcome. I thought the food truck part
would actually be quite useful if it was fully implemented since food trucks often don't show up on Google Maps and have limited reviews. It would be cool to have an app updated with local food trucks where Penn students could leave reviews.
As an avid food truck enjoyer, I think I would probably use it. I hope to make more cool apps like this and further my mobile development skills. Thank you for considering my application!
