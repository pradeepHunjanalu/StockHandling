# StockHandling
Java application with HTTP API for putting and getting the data and developed using spring and REST API

It contains 3 endpoints

1. /updateStock

 This end point updates the stock for a particular product.

It will return 201 status code when it is added sucessfully.

It will return 204 status code when it already contains the product.

2. /stock?productId={productId}

This end point will return the information about the product.

It returns 204 status code if it does not finds the product.

3. /statistics?time={today or lastmonth}

This end point will retun the statistic infomration related to the products like top available products and top sold products.

 

Steps to build:

You can build it using maven, Please use "clean install" command.

 

Steps to run:
select run on server and use below base URL
http://localhost:8080/StockHandling/

Softwares used:
Server : Apache Tomcat 7.0
Eclipse Luna
