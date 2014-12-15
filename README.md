PDF Box REST Demonstrator
=========================

*Dropwizard based PDF Box REST Services*

Introduction
------------
This represents a development prototype, there's little in the way of exception handling and unit testing.
It's a self contained server app that wraps some bits of PDFBox and Preflight functionality as REST services.
The services are capable of serving up XML or JSON dependent upon the content type requested.
The app also serves also a single page HTML based client that can be used to test the services.

###Technologies
The project's a Maven based Java affair, the application is based on [DropWizard](http://dropwizard.io/index.html),
this brings together a set of reliable libraries, the following are most used and may prove informative if your reading the code:

 * [Jetty](http://www.eclipse.org/jetty/) as a lean HTTP server,
 * [Jersey](http://jersey.java.net/) for REST services and associated, and
 * [Jackson](http://jersey.java.net/) for JSON and XML serialisation.
 
A good place to get going is the [Dropwizard getting started guide](http://dropwizard.io/getting-started.html).
The [Dropwizard core documentation](http://dropwizard.io/manual/core.html) covers the features used in the code base.
 
Building and running
--------------------
###Project structure
This is a little rough but it's broken into 3 parts plus a parent but only 2 parts are used.

 * **pdfbox-rest**: The main projects, running ```mvn clean package``` here builds the entire project.
 * **pdfbox-rest-api**: Contains the representations, it's effectively the object model and needs a refactor.
 * **pdfbox-rest-application**: The Jetty server and Jersey resources, plus the HTML client code all in one.
 
###Want to try?
First clone this project, ```cd``` into it and then trigger the Maven package build.
```
git clone git@github.com:verapdf/pdfbox-rest.git
cd pdfbox-rest
mvn clean package
```
That should build but with fewer unit tests than we'd like. Next start up the server:
```
java -jar ./ pdfbox-rest-application/target/pdfbox-rest-application-0.0.1-SNAPSHOT.jar server
```
Go to [localhost:8080](http://localhost:8080) and try the (basic) client.

Testing with curl
-----------------
There are a few services available, that aren't all tried by the client, you can test a few things with ```curl```.

###SHA1 digest service
```POST``` a file and get the SHA1 Digest and the length returned. A client JavaScript client calculates this before
transmitting the file. This service can be used to check that the file hasn't been corrupted. Hash integrity checking's
important in the Library and Archive world.

To get the default SHA1 of an empty byte sequence:
```
curl localhost:8080/sha1/null
``` 
to post a file and get the result returned:
```
curl -F "file=@path/to/a/file/myfile.txt" localhost:8080/sha1
```
which returns the result in JSON, to get XML try:
```
curl -F "file=@path/to/a/file/myfile.txt" localhost:8080/sha1 -H "Accept:text/xml" 
```

###PDF DocInfo only
There's a service that just returns the PDF Document Info data, it works on any valid PDF:
```
curl -F "file=@path/to/a/file/myfile.txt" localhost:8080/pdf/docinfo
```
again this returns JSON, to get XML try:
```
curl -F "file=@path/to/a/file/myfile.txt" localhost:8080/pdf/docinfo -H "Accept:text/xml" 
```

###PDF/A Validation
PDFBox Preflight PDF/A 1b Validation
```
curl -F "file=@path/to/a/file/myfile.txt" localhost:8080/pdf/validate
```
and for XML try:
```
curl -F "file=@path/to/a/file/myfile.txt" localhost:8080/pdf/validate -H "Accept:text/xml" 
```

