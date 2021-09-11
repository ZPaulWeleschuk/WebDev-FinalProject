# Final project for Adv. Web Development (CPRG352) at SAIT Spring 2021
## Assignment: 
Create a full stack web application that will allow users to keep track of their household items for insurance purposes.

## Features: 
#### Authentication
* user can log in if account is stored within DB and set to active
#### User Registration and Account Management
* user can register a new account
* new accounts will be sent an email with a unique link to activate their account
* user can edit their account information
* user can deactivate their own account but not delete their account
* user can reset password. Account assigned UUID and email sent with link to change their password. Password updated and UUID  reset
#### Manage Inventory
* home inventory item consists of a name, price, and category
* users can view, add, delete, and edit their own items
#### Admin account Management
* admin can view and manage all aspects of system
* only admin can reactivate account
* admin can view, create, delete, and edit all accounts
* deleting account deletes all associated items
#### Manage Categories
* admin can view, add, and edit the list of item categories
#### Usability, Testing, and Stability
* all UI element clearly labeled and function intuitively
* all required fcunctionality is operational
* user input has robust error checking to ensure valid entries
* filters are in place to ensure users only have access to their account 

## Internal Structure:
* full stack Java web application following n-tier MVC application architecture
* Apache Tomcat web container
* MySQL database accessed with JPA to provide object-relational mapping


### Note: developing a pretty UI was not required for assignment, hence the simplistic UI
