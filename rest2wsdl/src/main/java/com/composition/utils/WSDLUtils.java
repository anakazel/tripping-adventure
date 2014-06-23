package com.composition.utils;

import com.composition.Deployer;
import com.composition.model.Operation;
import com.generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.*;

import static com.composition.Deployer.OPERATIONS;

/**
 * @author alexg
 */
public enum WSDLUtils {
    ; //singleton

    private static final String WSDL_PROXY_NAMESPACE_URI = "http://www.wscomposition.org/WSDLProxy/";

    public static final void generateWSDL() throws JAXBException {

        final ObjectFactory o = new ObjectFactory();
        final TDefinitions wsdl = new TDefinitions();
        final TDocumentation wsdlDocInfo = new TDocumentation();
        wsdlDocInfo.getContent().add("WSDL 1.1 Document for REST Web Services, generated using rest2wsdl, " + new Date());
        wsdl.setDocumentation(wsdlDocInfo);
        wsdl.setTargetNamespace(WSDL_PROXY_NAMESPACE_URI);
        wsdl.setName("WSDLProxy");

        final TMessage requestMessage = new TMessage();
        requestMessage.setName("Request");

        final TPart requestPart = new TPart();
        requestPart.setName("requestString");
        requestPart.setType(new QName("xsd:string"));
        requestMessage.getPart().add(requestPart);
        wsdl.getAnyTopLevelOptionalElement().add(requestMessage);

        final TMessage responseMessage = new TMessage();
        responseMessage.setName("Response");

        final TPart responsePart = new TPart();
        responsePart.setName("responseString");
        responsePart.setName("responseString");
        responsePart.setType(new QName("xsd:string"));
        responseMessage.getPart().add(responsePart);
        wsdl.getAnyTopLevelOptionalElement().add(responseMessage);

        // portTypes
        Set<String> services = getServices();
        Iterator<String> it = services.iterator();

        while(it.hasNext()){
            String serviceName = it.next();
            final List<String> operationNames = getOperationNames(serviceName);

            final TPortType portType = new TPortType();
            portType.setName(serviceName);
            final TDocumentation portTypeDoc = new TDocumentation();
            String docString = "Interface for the " + operationNames.size() + " operations: ";

            for(int i = 0; i < operationNames.size(); ++i){
                docString += operationNames.get(i) + " ";
                final TOperation operation = new TOperation();
                final TParam inputOperation = new TParam();
                inputOperation.setName("jsonRequest");
                inputOperation.setMessage(new QName("tns:Request"));
                final JAXBElement<TParam> input = o.createTOperationInput(inputOperation);

                final TParam outputOperation = new TParam();
                outputOperation.setName("jsonResponse");
                outputOperation.setMessage(new QName("tns:Response"));
                final JAXBElement<TParam> output = o.createTOperationInput(outputOperation);

                operation.getRest().add(input);
                operation.getRest().add(output);
                operation.setName(operationNames.get(i));

                portType.getOperation().add(operation);
            }

            portTypeDoc.getContent().add(docString);
            portType.setDocumentation(portTypeDoc);
            wsdl.getAnyTopLevelOptionalElement().add(portType);
        }

        // bindings
        services = getServices();
        Iterator<String> it2 = services.iterator();
        while(it2.hasNext()){
            final String serviceName = it2.next();
            final TBinding binding = new TBinding();

            binding.setName(serviceName + "HTTP");
            binding.setType(new QName("tns:" + serviceName));
            wsdl.getAnyTopLevelOptionalElement().add(binding);

            final List<String> operationNames = getOperationNames(serviceName);
            int cnt = 0;
            for(int i = 0; i < operationNames.size(); i++){
                if(!operationNames.get(i).contains("GET")){
                    cnt++;
                }
            }
            final BindingType bindingType = new BindingType();
            final JAXBElement<BindingType> bindingTypeVerb = o.createBinding(bindingType);
            if(cnt == operationNames.size()){
                bindingType.setVerb("POST");
            }else{
                bindingType.setVerb("GET");
            }
            binding.getAny().add(bindingTypeVerb);

            for(int i = 0; i < operationNames.size(); i++){
                final String operationName = operationNames.get(i);
                final TBindingOperation operation = o.createTBindingOperation();
                operation.setName(operationName);
                binding.getOperation().add(operation);

                final OperationType operationHttp = new OperationType();
                String location = getLocation(operationName.substring(0, operationName.indexOf("Operation")));
                if(location.contains("/" + serviceName)){
                    location = location.replace("/" + serviceName, "");
                }
                if(location.contains("/")){
                    location = location.replace("/", "");
                }
                operationHttp.setLocation(location);
                final JAXBElement<OperationType> operationHttpJaxb = o.createOperation(operationHttp);
                operation.getAny().add(operationHttpJaxb);

                final TBindingOperationMessage input = new TBindingOperationMessage();
                input.setName("jsonRequest");
                operation.setInput(input);

                final ContentType contentTypeRequest = new ContentType();
                contentTypeRequest.setPart("requestString");
                contentTypeRequest.setType("application/x-www-form-urlencoded");
                final JAXBElement<ContentType> contentTypeReqJaxb = o.createContent(contentTypeRequest);

                final ContentType contentTypeResponse = new ContentType();
                contentTypeResponse.setPart("responseString");
                contentTypeResponse.setType("text/xml");
                final JAXBElement<ContentType> contentTypeRespJaxb = o.createContent(contentTypeResponse);

                final TBindingOperationMessage output = new TBindingOperationMessage();
                output.setName("jsonResponse");
                operation.setOutput(output);

                input.getAny().add(contentTypeReqJaxb);
                output.getAny().add(contentTypeRespJaxb);
            }
        }

        final TService service = new TService();
        service.setName("WSDLProxy");
        wsdl.getAnyTopLevelOptionalElement().add(service);

        services = getServices();
        Iterator<String> it3 = services.iterator();
        while(it3.hasNext()){
            final String serviceName = it3.next();
            final TPort port = new TPort();
            port.setName(serviceName + "HTTP");
            port.setBinding(new QName("tns:" + serviceName + "HTTP"));

            final String url = getUrl(serviceName);
            final AddressType addressType = new AddressType();
            addressType.setLocation(url);
            final JAXBElement<AddressType> addressTypeJaxb = o.createAddress(addressType);
            port.getAny().add(addressTypeJaxb);
            service.getPort().add(port);
        }
        marshallLogic(wsdl);

    }

    private static final void marshallLogic(final TDefinitions wsdl) throws JAXBException {
        final File file = new File("/tmp/wsdl.xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(TDefinitions.class);
        final QName qName = new QName("http://schemas.xmlsoap.org/wsdl/", "definitions", "wsdl");

        final JAXBElement<TDefinitions> jaxbElement = new JAXBElement<TDefinitions>(qName, TDefinitions.class, wsdl);
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new CustomNamespacePrefixMapper());
        jaxbMarshaller.marshal(jaxbElement, file);
        jaxbMarshaller.marshal(jaxbElement, System.out);
    }

    private static final Set <String> getServices(){
        final Set services = new TreeSet();
        for(int i = 0; i < OPERATIONS.size(); ++i){
            services.add(OPERATIONS.get(i).getServiceName());
        }
        return services;
    }

    private static final List<String> getOperationNames(final String serviceName){
        final List<String> operationsNames = new ArrayList<String>();
        for(int i = 0; i < OPERATIONS.size(); ++i){
            final Operation o = OPERATIONS.get(i);
            if(o.getServiceName().equals(serviceName)){
                operationsNames.add(o.getName() + "Operation_" + o.getHttpMethod());
            }
        }
        return operationsNames;
    }

    private static final String getUrl(final String serviceName){
        for(int i = 0; i < OPERATIONS.size(); ++i){
            final Operation o = OPERATIONS.get(i);
            if(o.getServiceName().equals(serviceName)){
                return o.getUrl();
            }
        }
        return "";
    }

    private static final String getLocation(final String operationName){
        for(int i = 0; i < OPERATIONS.size(); ++i){
            final Operation o = OPERATIONS.get(i);
            if(o.getName().equals(operationName)){
                return o.getLocation();
            }
        }
        return "";
    }
}
