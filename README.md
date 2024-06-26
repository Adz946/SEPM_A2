# Ticket-Master User Guide

# User Manual

Welcome to Ticket-Master! <br>
The grand new Ticket Management System! Designed to feel simple and give anyone looking to open a ticket full control. <br><br>
 
This guide provides step-by-step instructions on how to: <br>
> Start the App <br>
> Log In <br>
> Register <br>
> Reset Passwords <br>
> View Tickets <br>
> Create Tickets <br>
> Generate Ticket Reports <br>

Whether you're a new user or returning, this guide will help <i>you</i> become a Ticket-Master!
 
## Table of Contents
 
1. [Getting Started](#getting-started)
2. [LogIn Menu](#loggin-menu)
3. [Home Menu](#home-menu)
4. [Troubleshooting](#troubleshooting)
 
## Getting Started
Using the [link](https://mydesktop.rmit.edu.au/) provided, follow these steps to open up <strong>RMIT Desktop</strong> and the <strong>Eclipse IDE</strong>

### Getting the SEPM_A2 Zip
On GitHub, open the [Repository](https://github.com/Adz946/SEPM_A2) and download a zip of the <i>Main</i> Branch onto your computer (Ensure it is saved to a path you know well)

### Open RMIT Desktop
1. Log In using your RMIT Credentials
2. Open up the <strong>Desktops</strong> Tab and find <i>RMIT Desktop</i>
3. Open Desktop in a new tab (Note: This will take time)

### Open Eclipse IDE
1. Stay Logged in with your RMIT Credentials
2. Open up the <strong>Apps</strong> Tab and find <i>Eclipse IDE 2022-12</i>
3. Open Eclipse IDE in a new tab (Note: This takes a while)

### Setting Up Your Files
1. Head to your RMIT Desktop Tab
2. Drag & Drop your SEPM_A2-Main zip file into the RMIT Desktop, where you will be prompted to select a save path
3. Select it and wait for your zip to copy over
4. Unzip your SEPM_A2-Main zip file into your preferred directory
5. Wait for this to finish

### Setting Up Eclipse
1. Go to your Eclipse IDE Tab and open up a new project using the top-left tab (File -> Open Projects From File System)
2. On the <strong>Import Source</strong> section press the Directory button on the right-hand side
3. Find the Unzipped folder located in the directory you've specified
4. Select that folder
5. Press <strong>Finish</strong> on the bottom-right

### Runtime 
There is the chance that Eclipse IDE needs to be configured to properly handle the app, to do so follow these steps <br>

1. Expand the folder
2. Right Click on the JRE System Library
3. Hover over the Build Path option and then select Configure Build Path
4. You will need to be on the <strong>Libraries</strong> Section
5. Press the 'Edit' Button on the right-hand side of the window
6. Select <strong>Execution Environment</strong>
7. Scroll through the list of JRE Versions to select <strong>JavaSE-1.8 (jre)</strong>
8. Select Finish
9. Select Apply & Close

### Run The Code
1. Expand the main folder to find the default package, where you will find <i>Main.java</i>
2. Double Click Main.java to select it
3. Press the Green Play Button on the top-left of the window
4. Manage Your Tickets!
 
## LogIn Menu

### Menu
When the app loads, you will be greeted by the LogIn Screen, <br>
---------- Log In ---------- <br>
[1] Log In <br>
[2] Register <br>
[3] Forgot Password <br>
[4] Exit

### Option 1: Logging In
The 1st option will enable a user with an existing account to log into the app and use its ticket managing features. <br>
To log in, you will need your associated email and the password linked to that email.

### Option 2: Registering an Account
The 2nd option will enable a user without an account to register to the app before allowing them to use its ticket managing features. <br>
To register, you will need to set up the following user details: <br>

#### Name
Format = First Name is required with the Middle and Last Names being optional and seperated by spaces <br>
#### Email
Format = example@gmail.com <br>
#### Mobile
Format = 04XX XXX XXX <br>
#### Password
Format = 20 Characters Minimum, with at least 1 uppercase charater, lowercase character, and number. <i>Special chars are encouraged!</i>

### Option 3: Password Resetting
If you've forgotten your password, you may be able to reset it by selecting the 3rd option, then you may be able to use the ticket managing features. <br>
To be able to reset your password, you will require your associated email and the mobile number linked to it.

### Option 4: Exit
When exiting the application, all changes (such as a password reset or a new user) will be saved onto our storage files. <br>
<strong>Remember to exit the app to save!</strong>
 
### Example Logins

<strong>Technician</strong> <br>
Email: z.malik@company.com <br>
Password: _Malik-Al-Igbeer_45? <br>

<strong>Staff</strong> <br>
Email: s.syne@company.com <br>
Password: 01234ABCDE56789fghij

## Home Menu

### Menu
After a successful LogIn, you will be greeted by the Home screen, <br>
#### Technicians
---------- Zayn Malik ---------- <br>
[1] View Tickets <br>
[2] Generate Report <br>
[3] Log Out <br>
[4] Exit 
#### Regular Staff
---------- Sam Syne ---------- <br>
[1] View Tickets <br>
[2] Open Ticket <br>
[3] Log Out <br>
[4] Exit 
 >> 

### Option 1: Viewing Tickets
#### Technician
A technician will be able to view not just their <b>Open</b> tickets, but also any closed / archived ticket.
#### Regular Staff
Any other staff member will only be able to see their <b>Open</b> tickets, meaning that any of their closed tickets will not be visible.
 
### Option 2: Generate a Report [Technician Only]
A technician will be able to generate a report of <b>All</b> tickets from a selected start and end period (from the beginning of the start date to the very end of the end date).

### Option 2: Opening a Ticket [Regular Staff Only]
Any regular staff can open a ticket, where they will be required to select the severity of that ticket (LOW, MEDIUM, HIGH) and describe what the issue is. <br>
<i>Note: The technician assigned to fix your issue will be random!</i>

### Option 3: Log Out
Logging out will take you back to the LogIn Screen

### Option 4: Exit
When exiting the application, all changes (such as new tickets or a modified ticket) will be saved onto our storage files. <br>
<strong>Remember to exit the app to save!</strong>

### Ticket Modification
Accessed via the 1st option (Ticket View), technicians are able to modify the severity level of a ticket (which may change the technician solving the issue) and they can also change the status of a ticket (from closing to opening, all is possible with Ticket-Master!). <br>
<i>Other staff members may cancel a ticket if they wish</i>
 
## Troubleshooting
If you encounter any issues or have questions about using the application, please refer to this guide or contact the support team for assistance.

