### Interaction-with-the-processing-center
This application implements interaction with the processing center LeoGaming.
sing Https connection
there are 3 operations: Verify, Payment and Status.
each operation makes a POST request to the address https://test.lgaming.net/external/extended[https://test.lgaming.net/external/extended]. and posted their data in xml body.
each request sends with digital signature in Base64 format ("PayLogic-Signature" Header).

Each RESPONCE verify by digital signature, and their data also in xml body.


### How to launch:
To start application, use one of the following steps:
#### in Linux
 - use jar packajing: (need installed maven) 
    - download or ```git clone https://github.com/oshi192/interaction-with-the-processing-center.git```
    - open terminal in sourse folder (where pom file is lying) and type
    - mvn package
    - java -jar target/TechTest-1.0-SNAPSHOT.jar  
 - import to IntelliJ: 
 - import to Eclipse: 
#### in Windows
 - use jar packajing:
 - import to IntelliJ: 
 - import to Eclipse: 

