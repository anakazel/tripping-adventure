tripping-adventure
==================

Web services composition techniques - PoC

Usage:
1.Deploy the BPEL project to Apache Ode.
Example:
alias deploy='rm -rf ~/tools/tomcat/webapps/ode/WEB-INF/processes/BpelOrchestrator/; sleep 3; cp -r ~/projects/tripping-adventure/BpelOrchestrator/bpelContent/. ~/tools/tomcat/webapps/ode/WEB-INF/processes/BpelOrchestrator'
2.curl -X POST -d @request.xml http://localhost:8080/ode/processes/Composer?wsdl --header "Content-Type:text/xml"
