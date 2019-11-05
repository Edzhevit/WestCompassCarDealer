# WestCompassCarDealer

West Compass Dealer Shop is an web application where you can add your car offers. There are two main entities, Users and Cars .
## 1.Database Requirements
The Database of the application supports 2 entities:
### User
* Has a Username
* Has a Password
* Has an Email
* Has Cars (a collection of cars)
### Car
* Has a Brand
* Has a Model
* Has a Year
* Has an Engine
* Has User


## 2.Functional Requirements
The Functionality Requirements describe the functionality that the Application must support.
For Index using exactly "/index" as URL, not "/".
The application provides Guest (not logged in) users with the functionality to:
* Login
* Register
* View the Index page.
The application provides Users (logged in) with the functionality to:
* Logout
* Home
* View all Cars (All Cars page)
* Create a Car.
The application stores its data into a MySQL database, using Hibernate native.
## 3.Security Requirements
The Security Requirements are mainly access requirements. Configurations about which users can access specific functionalities and pages.
* Guest (not logged in) users can access Index page.
* Guest (not logged in) users can access Login page.
* Guest (not logged in) users can access Register page.
* Users (logged in) can access Home page.
* Users (logged in) can access Cars Create page.
* Users (logged in) can access Cars All page.
* Users (logged in) can access Logout functionality.
