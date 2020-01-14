# MOBLIMA-Movie-Information-and-Booking-System
The MOvie-Booking-and-LIsting-Management-Application or MOBLIMA system for short. This is a personal project on object-oriented programming concepts. This project is an adaptation of a school project (You can see the original work [here](https://github.com/wilsonteng97/MOBLIMA-An-Object-Oriented-Project-for-CZ2002)) which I have worked with my fellow group mates. After the project, I decided to develop my own MOBLIMA system to test my understanding of object-oriented programming. In addition, I developed my own algorithm to do the processing of movie information and booking of movie tickets.

MOBLIMA is a system for customers to view and book cinema tickets on remote without the need to travel to the physical cinemas and book the tickets. This system is similar to the online ticket booking websites of major cinema operators. For the staff on the other hand, they can manage the system by adding or removing the movies, showtimes and cinemas for operation.

# Features of the system

## 1. Web Crawling to get the latest movie details
<img width="800" alt="image" src="https://user-images.githubusercontent.com/48685014/72345733-606cc680-370f-11ea-9212-5e282b1a4d55.png">
With python modules of Beautiful Soup and Regular Expression, extracted the latest movie on show in Singapore from this [website](http://www.cinemaonline.sg/movies/nowshowing.aspx). This allows the administrator from spending time in manually adding the movies. However, the staff can also manually add and remove the movies if necessary. 

## 2. Easy access of staff and customer accounts
<img width="800" alt="image" src="https://user-images.githubusercontent.com/48685014/72338966-7aeb7380-3700-11ea-9cb5-8d55ebbbac23.png">
With the Java modules of FileInputStream, this program is able to store the accounts in String data type, thereafter into txt files for easy access and reading. In addition, the other objects such as cinemas and movies are stored in the similar fashion with FileInputStream in the relevant txt files.

## 3. Management of Cinemas and Showtimes
<img width="800" alt="image" src="https://user-images.githubusercontent.com/48685014/72346520-1f75b180-3711-11ea-8a8e-536cc16929dd.png">
Add or remove cinemas and showtimes to manage the movie screening in the cinemas.

## 4. Booking for customers
<img width="800" alt="image" src="https://user-images.githubusercontent.com/48685014/72346923-e4c04900-3711-11ea-9721-73d269cc9ae1.png">
Allow customers to choose the movie they want to watch and the preferred seats in the cinema theatre that are available for purchase.

## Default staff accounts
Username: *admin1*, Password: *password*
