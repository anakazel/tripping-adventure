tripping-adventure
==================

Web services composition techniques - PoC

#####ode-bpel-rest usage:

1.Deploy the BPEL project to Apache Ode
```
rm -rf $ODE_HOME/WEB-INF/processes/ode-bpel-rest/;
sleep 3; 
cp -r $WORKSPACE/tripping-adventure/ode-bpel-rest/bpelContent/. $ODE_HOME/WEB-INF/processes/ode-bpel-rest
```
2.Create a POST request against the webservice
```
curl -X POST -d @request.xml http://localhost:8080/ode/processes/Travel?wsdl --header "Content-Type:text/xml"
```
#####mule-esb-rest usage:

1. Deploy the project using MuleStudio
2. Create a GET request: 
```
curl "http://localhost:8084/?origin=Iasi&destination=Bacau&travelMode=DRIVING&objectiveRadius=1000&objectiveTypes=food&forecastUnits=metric&forecastDays=3&images=3"
```
