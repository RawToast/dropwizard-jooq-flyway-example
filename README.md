# backend-poc

Organizing Your Project
=======================

In general, we recommend you separate your projects into three Maven modules: 

* ``project-api`` : should contain your representations
* ``project-client`` : should use those classes and a HTTP client to implement a full-fledged client for your application
* ``project-application`` : should provide the actual application implementation, including resources

Modules are often broken down like this:

* ``com.example.myapplication``:

  * ``api``: :ref:`man-core-representations`.
  * ``cli``: :ref:`man-core-commands`
  * ``client``: :ref:`Client <man-client>` implementation for your application
  * ``core``: Domain implementation
  * ``jdbi``: :ref:`Database <man-jdbi>` access classes
  * ``health``: :ref:`man-core-healthchecks`
  * ``resources``: :ref:`man-core-resources`
  * ``MyApplication``: The :ref:`application <man-core-application>` class
  * ``MyApplicationConfiguration``: :ref:`configuration <man-core-configuration>` class
