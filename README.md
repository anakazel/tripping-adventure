tripping-adventure
==================

Web services composition techniques - PoC

BpelRest usage:

1.Deploy the BPEL project to Apache Ode
```
rm -rf $ODE_HOME/WEB-INF/processes/BpelREST/;
sleep 3; 
cp -r $WORKSPACE/tripping-adventure/BpelREST/bpelContent/. $ODE_HOME/WEB-INF/processes/BpelREST
```
2.Create a request against the webservice

curl -X POST -d @request.xml http://localhost:8080/ode/processes/Travel?wsdl --header "Content-Type:text/xml"
