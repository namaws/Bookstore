# Bookstore
Web application contains User History, Server/Client transaction validation, state manegement. 
Skills: JavaSript, HTML5, CSS3, Vue.js, Java, Gradle, RESTful APIS, Apache Tomcat, MySQL

Future work: Deployment, add third-party payment API, add Google Map API

#### Deployment
##### Using AWS EC2 
##### Install Apache Tomcat on your EC2 instance
use the following cmd to check Tomcat status
```
sudo systemctl status tomcat
```
##### Install MySQL on your EC2 instance
** AWS EC2 doesn't have apt or apt-get -> using yum instead
```
sudo yum install mysql-server -y
```
A common issue would have is 
```
The GPG keys listed for the "MySQL 5.7 Community Server" repository are already installed but they are not correct for this package.
Check that the correct key URLs are configured for this repository.


 Failing package is: mysql-community-libs-compat-5.7.41-1.el7.x86_64
 GPG Keys are configured as: file:///etc/pki/rpm-gpg/RPM-GPG-KEY-mysql
 ```
 
