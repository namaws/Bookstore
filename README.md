# Bookstore
This project is an E-commerce Web application with Responsive Design and contains user history, server/client transaction validation, state manegement. 
It uses RESTful APIs to CRUD books and fetch data from the MySQL Database.  
Skills: JavaSript, HTML5, CSS3, Vue.js, Java, Gradle, RESTful APIS, Apache Tomcat, MySQL

## Diagram about the project structure
<p align="center">
  <img src="https://github.com/namaws/Bookstore/blob/main/sceenshot.png" width="700" title="hover text">
</p>

Future work: Deployment, add third-party payment API, add Google Map API

## Deployment
### Using AWS EC2 
### Install Apache Tomcat on your EC2 instance
use the following cmd to check Tomcat status
```
sudo systemctl status tomcat
```
### Install MySQL on your EC2 instance
There are a couple of methods that can install mysql on your EC2 instance, depends on which way you prefer, feel free to follow the instruction below as well.  
#### 1. Download the MySQL Installer using the following command:
```
wget https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
```
The above command is for downloading the MySQL 8.0 installer. If you want a different version, just replace mysql180 with the version you want.
#### 2. Install the MySQL Installer package
```
sudo rpm -ivh mysql80-community-release-el7-3.noarch.rpm
```
#### 3. Install MySQL
```
sudo yum install mysql-community-server
```
#### 4. Once the installation is complete, start the MySQL service
```
sudo systemctl start mysqld
```
#### 5. You can check if the service is running
```
sudo systemctl status mysqld
```

#### If you want to stop the service
```
sudo systemctl stop mysqld
```
