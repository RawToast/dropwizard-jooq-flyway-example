# backend-poc

!Dropwizard backend proof of concept!

Organizing Your Project
=======================

In general, we recommend you separate your projects into three Maven modules: 

* ``project-api`` : should contain your JSON representations and service interfaces
* ``project-client`` : should contain client code used to get data from external rest service. Can be excluded, if you don't have any.
* ``project-application`` : should provide the actual application implementation, including resources

Modules are often broken down like this:

* ``com.example.myapplication``:

  * ``api``: JSON Representations
  * ``client``: Client implementation for your application
  * ``core``: Domain implementation
  * ``jdbi``: DB Access
  * ``health``: Healthchecks
  * ``resources``: Resources
  * ``MyApplication``: The application class
  * ``MyApplicationConfiguration``:The configuration class
