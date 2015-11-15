# Getting Started #
To get started with using open-dashboard, you need to understand a couple of points regarding OpenDashboard
  1. Open-dashboard works on a Publisher Subscriber model(i.e. there will be someone publishing specific data, and someone subscribing to that data. It is just like twitter in a way, the difference is subscriber is actually observing the visual representation of the publisher data)
  1. To achieve this model, OpenDashboard is implemented as two part
    1. A web server, which will receive publisher data. (Currently) The same web server will be used to serve subscribers
    1. A publisher application, which will be used by publishers to publish data.
  1. So for a complete working setup of OpenDashboard in intranet environment, you need both components, ODBServer(the web server) and the ODBPublisher(the publisher client). Now we will discuss the installation and startup guide for both


## Installation Guide ##
  1. Before installation, you must have latest java version installed on your machine. You can download one from [here](http://java.com/en/download/index.jsp)
### ODBServer ###
  1. Download the latest ODBserver release from [Downloads](http://code.google.com/p/open-dashboard/downloads/list)
  1. Extract the contents of the zip file on to your file system.

This is it, you've just finished the installation of ODBServer.

---


To start the application now, you have to start the following processes,

  1. Browse to ./odbserver/bin/
  1. For windows
    * startup.bat  (This will start the webserver so you can see your charts in web browser)
    * runServer.bat  (This will start a very light-weight database)

  1. For Unix
    * startup.sh   (This will start the webserver so you can see your charts in web browser)
    * runServer.bat (This will start a very light-weight database)



### ODBPublisher ###
  1. Download the latest ODBPublisher release from [Downloads](http://code.google.com/p/open-dashboard/downloads/list)
  1. Extract the contents of the zip file on to your file system.
  1. Edit config/config.props. The details on how to configure are available in the same file.
  1. Edit PublisherConfiguration.xml. The details are provided in the same file
  1. Ensure that the ODBServer is up and running by opening http://localhost:8080/odbserver from your browser. If this step fails and you are sure that you executed the above mentioned process then you check the logs from odbserver/logs/localhost-[date](date.md).log for detailed study
  1. Once ODBServer is running fine, start the application using start.bat in the home directory

And that's it.

Now the interesting part,
> ### ODBSubscriber ###
    1. Well you dont need any installation for this part, you should be able to hit your browser at http://localhost:8080/odbserver and see the login page.
    1. Login on the page using 'admin','admin' and it will take you the page where you can start adding charts for your view.

Hope this guide will be helpful.