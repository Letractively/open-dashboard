OpenDashboard is essentially is a web-based charting application based on publisher-subscriber model. This means publishers will be publishing some data in realtime on periodic/aperiodic basis and subscribers will observe the updated data in a time-series graph within there web browsers.

The publisher does the following actions,
  1. Registers itself
  1. Registers a data source
  1. Starts sending data to the odbserver

The Subscriber can do the following actions,
  1. Registers (Not Implemented yet)
  1. Subscribe to different data sources published

The odbserver is sitting in the center of publisher and subscriber. The job of odbserver is to notify the subscriber(through Reverse AJAX) call whenever new data is available from the publisher.

The purpose of the dashboard is to be able to monitor any kind of time-series real-time data visually in a web browser. This makes it extremely useful for
  * periodic monitoring of any SQL (e.g. select count(1) from employee)
  * periodic monitoring of any operating system call (e.g. memfree)
  * periodic monitoring of stock rates
  * well essentially any real-time time-series data and sky is the limit.