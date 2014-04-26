tripping-adventure
==================

Web services composition techniques - PoC

#####BpelREST usage:

1.Deploy the BPEL project to Apache Ode
```
rm -rf $ODE_HOME/WEB-INF/processes/BpelREST/;
sleep 3; 
cp -r $WORKSPACE/tripping-adventure/BpelREST/bpelContent/. $ODE_HOME/WEB-INF/processes/BpelREST
```
2.Create a POST request against the webservice
```
curl -X POST -d @request.xml http://localhost:8080/ode/processes/Travel?wsdl --header "Content-Type:text/xml"
```
#####EsbREST usage:

1. Deploy the project using MuleStudio
2. Create a GET request: 
```http://localhost:8084/?origin=Iasi&destination=Bacau&travelMode=DRIVING&objectiveRadius=1000&objectiveTypes=food&forecastUnits=metric&forecastDays=3&images=3```
