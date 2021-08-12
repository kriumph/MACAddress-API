<h1>SCD2_2021_Exam MacAddress with SendGrid</h1> 
(Simple Extension Required)

## Assigned API for Exam

Input: MAC address https://macaddress.io/api

Output: SendGrid https://sendgrid.com/solutions/email-api/

## Commit URLs for RED-GREEN-REFACTOR Processes

**Process of TDD: Construct all features and database for model**

1. Red Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/7fb1092bfa60f266ea00af9bca0aabc4acf3989d 

   Type: Red test for database and all features (Compile but failed)

2. Green Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/8e253b288fc5f24887ca4d24c2158144db3dbfcd 

   Type: Green test for database and all features (Compile and passed)

**Process of TDD: Add update SQL feature**

1. Red Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/88e7d38f8871b2286687d496d80ac7a7133f423f

   Type: Red test for updateInfo method  (Compile but failed)

2. Green Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/5fae585046fb74adf4daea52acc6a54b5b2e2eae

   Type: Green test for updateInfo method (Compile and passed）

3. Refactor Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/db446a28316f78ae9f8b78126b293b8bcfcf40d0

   Type: Refactor code for Database's initialize method (Compile and passed)
   
**Process of TDD: Extension feature for exam**

1. Red Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/76938056709ed536a809ae5cb42437465362fcce

   Type: Red test for extension method  (Compile but failed)

2. Green Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/1e0904758ea1b72dbb86751621f7d4c99e687f74 

   Type: Green test for extension method (Compile and passed）

3. Refactor Process: https://github.sydney.edu.au/kpen8119/SCD2_2021_Exam/commit/4c9aebdabb26b4403412d20e6ebef5103fb93531 

   Type: Refactor code for extension method (Compile and passed)

## QUIRKS for Marker

**QURIK 1:** 

This application is requestd by the latest version of API, and it should be running with Java 11. You should change your java version to JAVA 11 before you run the program. My working version is under java 11.0.8-amzn, and  Gradle 6.4. 

Simply, the marker can input "sdk use java 11.0.8-amzn" to change your versoin. (If you do not have this, you can download first)

**QUIRK 2:**

 The marker should have own API key for input API and output API both. 

For input API key, the marker is expected to change the first line of input.txt (file is in resources)to his own API key of MacAddress API.

 For output API key, the marker is expected to change the first line of output.txt  (file is in resources) to his own API key of SendGrid API.

**QUIRK 3:**

The sender email address input in GUI is expected be matched the sender's email address in SendGrid API.

**QUIRK 4:**

Database will be generated after the marker run the program for first time.

The program will check whether the db file is existing or not, and create a new table for the first time. If the db file is existing, the program will not generate a new database again. The database is persist for different runs.

## Configuration Files

1. src/main/java/MacAddress/View/prework.fxml  (DO NOT modify)

The GUI of this APP is constructed by Scene Builder, which will generate a fxml file to perform the user interaction. This is loaded by main method in APP.java.

2. src/main/java/resources/input.txt (Need to modify to marker's API key)

The input API requires a API key to access its functionality, such as get the credit balance and search information for a certain MAC address. The API key file should be modified by the marker. The marker should change the first line of input.txt file to marker's own API key.

3. src/main/java/resources/output.txt (Need to modify to marker's API key)

The output API requires a API key to access its functionality, such as send the email. The API key file should be modified by the marker. The marker should change the first line of output.txt file to marker's own API key.

## **Guide for Marker**

There are four modes for program.

1. The offline input and offline output mode with running 

    gradle run --args="offline offline"

2. The offline input and online output mode with running 

    gradle run --args="offline online"

3. The online input and offline output mode with running 

    gradle run --args="online offline"

4. The online input and online output mode with running 

    gradle run --args="online online"

**Home Page - For choosing type of input request**

Buttons:

`Search Mac Address` - After click the button, the page will turn to Search page.

`Check Credits Balance`  - After click the button, the page will turn to Check page.

**Check Page - For checking credit balance**

Buttons:

`Check` - After click the button, the page will send the request to check its credit balance for its API key.

`Home`  - After click the button, the page will trun to Home page.

**Search Page - For searching information of MAC address**

Input Parameters:

`Mac Address / OUI` - The marker is expected to input a valid MAC address of OUI address. If the API return a error message, the error message will be parsed to readble string and be displayed. (123 is one of invalid MAC address, but the API will not return a error message)

Buttons:

`Search from Database` - After click the button, the GUI will search and display the information from the database.

`Search from Web-API` - After click the button, the GUI will search and display the information from the Web-API and store the cached information in database (update if the cached data is different). 

`Clear Search History` - After click the button, the displayed information will be cleared. 

`Collect all information` - After click the button, it will collect the displayed information and turn to the Output page to display it again in Output page.

`Home`  - After click the button, the page will trun to Home page.

**Output Page - For sending information of MAC address by email**

Input Parameters:

`Sender Email Address` - The marker is expected to input a exsiting sender email address of SendGrid account. If the API return a error message, the error message will  be displayed. 

`Receiver Email Address` - The marker is expected to input a valid receiver email address. If the API return a error message, the error message will  be displayed. 

`Subject` - The marker is expected to input a subject title. If the API return a error message, the error message will  be displayed. 

Buttons:

`Send Email` - After click the button, the GUI will send the displayed information to receiver email.

`Show Content`- After click the button, no matter what is displayed in the content field, it will show the information that will be sent again.

`Home`  - After click the button, the page will trun to Home page.

`Back`  - After click the button, the page will trun to Search page again.

## Level of Completion of Features  `(Distinction Ceiling)`

**Pass (Completed)**

1. Display information about the given entity (Mac Address) in GUI
2. Output the result that get from input (SendGrid - Sending Email)
3. Parsing the data from JSON, which makes the data reasily readable

**Credit (Completed)**

1. Construct the database with SQLite in local environment.
2. Cache the data that is retrieved from the API.
3. Provide two ways for users to request the matching item. 
   - Use the cached version from database as the result
   - Grab a fresh copy from the API by requesting (Update the database)

**Distinction (Completed)**

1. Use JavaFX's concurrent package to achieve concurrency, which can separate out the GUI thread from API requests/ database access thread. The  GUI will remain live and responsive during API request.

## Citations

httpGet() method in RequestMakerOnline :

Citation: use the same structure in client class of task3 in this unit (SOFT3202), but changed for current usage

postData() method in RequestMakerOnline :

Citation: use the same structure in client class of task3 in this unit (SOFT3202), but changed for current usage

DatabaseManager's three methods:

Citation: refer the https://github.com/xerial/sqlite-jdbc, which is provided in the specification. Use the structure of code in github, but changed for current usage
