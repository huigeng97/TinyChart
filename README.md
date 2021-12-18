# Tiny Treemap

## Tools Needed to build and Run

Java version 1.8+;

## List of Tools and Libraries we use
#### Java(Spring Boot) to connect frontend and backend
#### HTML, CSS, JavaScript to build the website
#### D3.js to build the Treemap 



## Start the project

#### 1.Download the whole project into a local PC. 
#### 2.Import the project as a Maven project into a IDE (IntelliJ or Eclipse, etc).
#### 3.Find the application at the src/main/java/com/example/demo/DemoApplication.java.
#### 4.Build the project and run the code by Running the  DemoApplication.java. If there is anything wrong when building the project, use Maven reimport the pom.xml file at the root folder.  
#### 5. Wait until the springBoot is builted, then open the browser with the site " http://localhost:8000/home " and play the tools.

(If you have the trouble building the project, please emailto hgeng7@wisc.edu)

## How to Use:
#### User can click the data source box to choose the data source
#### User can scroll the bar to choose the treemap size 
#### User can customize the area threshold to combine the square(when to combine squares)

## Try your own data

#### 1. Learn the data format from the src/main/java/com/example/demo/data2.json 
####        or https://www.d3-graph-gallery.com/graph/treemap_json.html
        The imformation field is the children, name, and value.
#### 2. Create your own data and store at src/main/java/com/example/demo and name it as data5.json
#### 3. Choose the data as data5 in the home page and then visualize it.

## Ongoing 
We are working on host a public webpage for the users to play it.
