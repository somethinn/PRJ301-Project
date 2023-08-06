# 1. Requirements
   ## ***System name***
   - 11-twellStudio
   
  ## ***Purpose of the system***
   - 11-twell Cinema is a website providing service for booking movie tickets.

  ## ***Object***
   - Users book movie tickets for 1 or many
   - Managers manage system of movies, sliders, posts, orders
   
  ## ***Features***
   1. **Public features**
      - a. **Homepage** 
         - Show new movies, some posts and banner of movies
         
      - b. **Movie list** 
         - Show new movies, upcoming movies, and filter by name, latest or oldest, show by new or upcoming movies
         - When user click 1 movie, details and booking button will show up for user 
         - Can contact with movie by like button
           
      - c. **Post details**
         - Show details of posts, events
         
  2. **User features**
      - a. **User login**
         - Login as user
         - Login with gmail
         - Register new account - Send mail to verify
         - Forgot password - user can reset password
         
      - b. **Account**
         - View and edit user profile details
         - Change user password
         
      - c. **Movie booking - Seat selection - Booking completion**
         - Booking movie by select day and time
         - Select seats(login is necessary) and check booking details 
         - Select payment method and complete purchase by clicking “Purchase” - Send mail with content of ticket
         - Back to movie list because reject previous seat seletion by clicking “Cancel”
         - User can add voucher code to get discount
         - Show a countdown clock if user do not pay within the time allowed, user will be returned to the move list page
         - Filter to check the user, if user are not logged in, user must log in before user can book the ticket
         
      - d. **My tickets**
         - View history of transactions including booking number, movie’s name, slot, seat, price
         - Optional to filter by latest or newest booking
         
  3. **Manager features**
       - a. Dashboard chart
         - Show data of total movies, total order, total revenue
         - Show top 3 most ticket-sold movies
         - Chart showing the Revenue of each day  - of 7 days before
         
       - b. **Manage movie**
         - Add new movies
         - Filter the movie’s name, status, premiere and genre
         - View all the information including movieID, genre, tittle, image, premiere, director, time(minutes), description, status and action of all movies that have been or are showing
         - Can use "switch" to switch status to "coming, showing, inactive"
         - "edit" for editing information of movie
         - "delete" for deleteing movie
         - Paging
         
       - c. **Manage order**
         - View all the information of all orders including booking number, movie id, customer name, phone number, mail, delivery date, price, status and action
         - Filter the orders by phone number, delivery date, status
         - Can delete order by Delete
         - Paging
           
       - d. **Manage show**
         - View all the information of all shows including showid, movie , date, slot, room, price of film of that show, status and action
         - Filter the show by name, status and date
         - Can delete show by Delete
         - Can edit show if show is inactive and no any order of this show
         - Can add show 
         - Paging
          
       - e. **Manage slider**
         - Filter the sliders  status
         - View all the information of all slider including slider  image,  status and action
         - Manager can switch status slider
         - Paging
         
       - f. **Manage post**
         - Filter the post by title and status
         - View all the information of all post including post id, title, image, back link, content, status and action
         - Manager can edit, delete, switch and add post
         - Paging
       - g. **Manage promo-code**
         - Filter the Promo-Code by code, status and date
         - View all the information of all Promo-code including id, code, discount, start date , end date , status and action
         - Manager can edit, delete, switch and add Promo-code
         - Paging
       - h. **Management Login**
         - Login as management
         - Filter to check if the role is admin or not? If it is admin, admin will be able to access the management page
# 2. Wireframe and sitemap 
   ## ***Sitemap***
![sitemap](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/5119186e-67ce-40b2-829a-82506d64d1fc)
   ## ***UI Design***
   1. ## PUBLIC FEATURES
   
      - ### Home Page
         - ***Function:***    Show some new movies, some posts and banner of movies
         
         - ***Description:***   The home page is where users can access items such as sliders, movies, events, registration, and login along with some information about the cinema
         
         - ***Screen Layout:***
         - ![homepage](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/4b66ed75-cc72-45ea-8cd9-d2a29eba4f96)

      - ### Movie List
          **#Movie List**
         - ***Function:***   Show movie list, filter, search movie name
         
         - ***Description:***    The screen displays a list of movies currently playing and upcoming movies, filters include the newest and the oldest. Users can also search for movies through the name of the movie and can select the currently playing or upcoming movie if the user wants
         
         - ***Screen Layout:***
         - ![movielist](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/90e6c297-fb2a-4e04-bc54-2a3e58f88fae)

         **#Movie Details**
         - ***Function:***   Show movie details
         
         - ***Description:***    The screen shows the movie information that the user has selected, here the user can see the movie information along with the movie trailer. At the same time, users can also book tickets
         
         - ***Screen Layout:***
         - ![moviedetails](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/d85ecdf1-391f-4abc-9ec4-679e8947374f)

       - ### Post Details
         - ***Function:***  Show event detail
         
         - ***Description:***   The screen displays the image of the event and the information and rules of participation in the event
         
         - ***Screen Layout:***
         - ![postdetails](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/ba0587e8-0c71-4adc-8b3f-cffe90f5a231)



   
   2. ## USER FEATURES
   
      - ### User Login
          **#Login**
         - ***Function:***   For user login
         - ***Description:***     A pop-up screen for user to enter email or phone number & password & captcha code. If user did not have account, he/she can also register a new account by “REGISTER”. Or if forgot password, can also reset password by clicking “Forgot Your Password ?”  
         
         - ***Screen Layout:***
         - ![userlogin](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/eae02e1d-0620-4b20-9423-c695da12ad65)
         

         **#Registration**
         - ***Function:***   For user register
         
         - ***Description:***    A pop-up screen for user to enter fields: name, phone, email, password, birthday, region, captcha. After that, the user needs to click the link sent in his/her mailbox

         - ***Screen Layout:***
         - ![register](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/75cda3e8-4613-475e-a9f9-a44f90392080)


         **#Forgot Password**
         - ***Function:***   For user reset password
         
         - ***Description:***     A pop-up screen for user to enter email & captcha. The link to reset password will be sent to the user’s mailbox. After clicking that, user can reset his/her new password

         - ***Screen Layout:***
         - ![forgotpassword](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/52cc93a3-c06b-4a7c-a113-78c054aa2778)
         - ![forgotpasswordsend](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/290e6d4d-f869-4ece-915f-dfd4153de31e)
         - ![resetpassword](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/6353a589-c61f-4c5c-a4e1-3bdcbeaf9156)


      - ### Account
          **#User Profile**
         - ***Function:***   Show and update user profile
         
         - ***Description:***     The screen displays the user’s information while allowing the user to update their own information
         
         - ***Screen Layout:***
         - ![userprofile](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/e13bf48f-d323-44f6-8dc1-0524823eda47)

         **#Change password**
         - ***Function:***   Change user password
         
         - ***Description:***    The user can change the password by entering the old password, then enter the new password and enter the captcha code, the user can change the password
         
         - ***Screen Layout:***
         - ![changepassword](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/dd8ecd6e-4b12-42c3-9c5b-cb5fe0c6e3af)


      - ### MovieBooking
          **#Time Selection**
         - ***Function:***   Choose time and theatre
         
         - ***Description:***    The screen shows a place where the user can choose the time and place of the theatre to book tickets
         
         - ***Screen Layout:***
         - ![booking](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/b4ec957a-e278-4bd6-b775-3ed4fb5119fc)

         **#Seat Selection**
         - ***Function:***   Choose seat
         
         - ***Description:***    The screen shows all the seats available in the cinema including user-selected seats, already-selected seats and empty seats. At the same time, the screen displays a caption about the type of seat and the user can see the details of the seats in the theater through the real image taken. The screen also displays a screen to watch movies so that users can choose a seat with a distance to suit the movie screen.
         
         - ***Screen Layout:***
         - ![seatselection](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/4afbb3aa-7737-4e24-84d4-bed1a9424b65)

         **#Payment**
         - ***Function:***  Show total payment and payment method
       
         - ***Description:***   The screen shows payment methods, users can choose suitable payment methods. At the same time, the screen displays the total amount so that the user can see the total amount that the user has to pay. Users can only pay when they have agreed to the terms offered by the cinema

         
         - ***Screen Layout:***
         - ![payment](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/677df342-570a-448b-bf0c-ae9e578aa97a)

      - ### My Tickets
         **#History of Transactions**
         - ***Function:***   Show purchase history
         
         - ***Description:***    The screen shows all information about all the tickets that the user has booked currently and previously. There are more latest and oldest filters for users to easily search for tickets
         
         - ***Screen Layout:***
         - ![mytickets](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/a18354e6-ac1b-4032-8c65-2e61b1294258)





   
   3. ## USER FEATURES
       - ### Login as admin
         - ***Function:***  Login for Admin

         
         - ***Description:***  A pop-up screen for admin to enter manager account and password
         
         - ***Screen Layout:***
         - ![managerlogin](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/0fa55ec6-81f8-4ecb-8e04-536115570806)


       - ### Dashboard Chart
         - ***Function:***   Show data of total movies, total order, total revenue, Show top 3 most bought movies, chart showing the number of orders in the last 7 days
          
         - ***Description:*** The screen displays a line chart so that the manager can see the number of orders in the last 7 days, the top left panel shows the top 3 movies with the most views. There is also data on total movies, total orders, total revenue
         
         - ***Screen Layout:***
         - ![dashboardchart](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/767b1641-d0f0-413c-88af-aaa3f645b102)

      - ### Movie Manage
         - ***Function:***   Add new movies, filter the movie’s by name, status, Premiere and genre, view all the information of all movie
          
         - ***Description:*** The screen shows all the information of all active and inactive movies, the manager can add movies and can edit, delete, switch movies. Besides, there is a filter so that the manager can easily search for movies
         
         - ***Screen Layout:***
         - ![moviemanagement](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/4b4ff8f5-9486-4699-93d6-4d2a44b95491)

      - ### Order Manage
         - ***Function:***    Filter the orders by phone number, delivery date, status, manager can confirm the orders, view all the information of all orders
          
         - ***Description:*** The screen shows all the information of all orders, Besides, there is a filter so that the manager can easily search for orders, manager can confirm the orders by clicking “confirm”
         
         - ***Screen Layout:***
         - ![ordermanagement](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/a58e90a7-6187-41ee-acf9-8ccde0f1dcdd)

       - ### Slider Manage
         - ***Function:***    Filter the sliders by title and status, edit, delete, switch slider, view all the information of all slider
          
         - ***Description:*** The screen shows all the information of the slider, the manager can add sliders and can edit, delete, switch movies. Besides, there is a filter so that the manager can easily search for slider
         
         - ***Screen Layout:***
         - ![slidermanagement](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/5b83f2a3-9e25-48ef-8094-072c91cd7b0d)

       - ### Post Manage
         - ***Function:***    Filter the post by title and status, view all the information of all post, edit, delete, switch(status) post
          
         - ***Description:*** The screen shows all the information of the post event, the manager can add posts-event and can edit, delete, switch event. Besides, there is a filter so that the manager can easily search for event
         
         - ***Screen Layout:***
         - ![postmanagement](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/55377a3c-79ee-4922-9cb0-15fce1c0fab1)

# 3. Database design

   ![database](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/b227a840-e13d-4b5b-963c-000fbdf5a721)

# 4. System design
   - ### Models:
      - ![genreModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/d63ad7c7-7a0b-4310-b9cd-59505a6888cd)
      - ![movieModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/b0b196e9-e7c0-4099-975f-09124ad167cd)
      - ![orderModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/d1aaa365-1240-4bc3-b211-72fa3ef7cdea)
      - ![postModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/043940ec-b2aa-45e4-96e6-29e426c2b83f)
      - ![promoModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/bcfd12b4-6edf-4f92-baba-221fc882db9d)
      - ![roomModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/87aafef9-5f5f-49dc-8b23-bfbe5f8ca059)
      - ![seatModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/3097f26d-dc47-4b1f-99be-6562063aa478)
      - ![showModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/4858a178-0e89-42b7-8b87-c8cdab716174)
      - ![slotModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/e9dd8a4e-0f82-41f6-844d-9ee34ffd3666)
      - ![userModel](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/5bd84aa8-823f-456f-9c74-31f6350fb621)
   - ### Views:
      - ![jsps](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/afc36dbd-5927-46e6-acd5-d0ddfe8a0eec)
   - ### Filters:
      - ![filter](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/96ca0ef0-c80c-4cac-8516-ef497d7853ca)
   - ### Controllers:
      - ![controller](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/1ae3b020-fe8d-465a-9668-236a4c2ad7a3)
   - ### Utils:
      - ![utils](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/e8ffc45f-ffa2-43f1-82f4-dfd730310b10)
      - ![utils2](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/620dcb96-f7d7-4d81-98e4-92a2c57f9895)
   - ### System design:
      - ![image](https://github.com/dunghuynh-teaching/prj301-se1726-01/assets/80157349/99f9e3c0-f62d-4fdf-9c39-551954909a50)



# 5. Conclusion and Discussion
   After finished this assignment, we realize that our skills has been improving a lot. We learn how to work in a group, how to use git basically, and how to code with servlet, jsp basic, know what is wireframe, how to design a basic database. That skill help us in PE test, FE test and most important is our coding skills. 
   
   Our web app is about cinema topic - 11-12 Cinema. This app includes common features of a simple cinema like: login, showing movie show, movie detail, show time, booking, manage movie, manage order, manage show. Furthermore, we develop promotion code for discounting, login with google, dashboard chart for management, send mail to user, number of like of movie, paging, filter, captcha code before login to avoid spam. But, we did not develop payment method function yet, and in management when edit image, we need to select image again, and promo code need to get from event by copy.
   
   Pros: Help user can booking movie for reservation. Instead of reaching to theater and buy ticket, nowadays people just need to access to internet, go to our page, pick movie - show - seat, and ting-ting!! So that when at the time of the show, users just need to show email of reservation ticket, and go to hall, and watching movie. 

   Cons: Do not have specific payment method, promotion isn't convenient for user. We just built 1 theater, 1 type of seat matrix.
      

   In future, we prevent to import payment method, clean our code, update function add promotion and add user management. We updating matrix of seat, contstruct more theaters of more places.. 

   Thank you very much. Because of your help, we can learn more knowledge and develop this project more completely.
   
      



