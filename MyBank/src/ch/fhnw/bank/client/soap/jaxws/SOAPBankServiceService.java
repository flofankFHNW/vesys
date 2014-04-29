
package ch.fhnw.bank.client.soap.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SOAPBankServiceService", targetNamespace = "http://soap.server.bank.fhnw.ch/", wsdlLocation = "http://localhost:9876/soapBank?wsdl")
public class SOAPBankServiceService
    extends Service
{

    private final static URL SOAPBANKSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException SOAPBANKSERVICESERVICE_EXCEPTION;
    private final static QName SOAPBANKSERVICESERVICE_QNAME = new QName("http://soap.server.bank.fhnw.ch/", "SOAPBankServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9876/soapBank?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPBANKSERVICESERVICE_WSDL_LOCATION = url;
        SOAPBANKSERVICESERVICE_EXCEPTION = e;
    }

    public SOAPBankServiceService() {
        super(__getWsdlLocation(), SOAPBANKSERVICESERVICE_QNAME);
    }

    public SOAPBankServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPBANKSERVICESERVICE_QNAME, features);
    }

    public SOAPBankServiceService(URL wsdlLocation) {
        super(wsdlLocation, SOAPBANKSERVICESERVICE_QNAME);
    }

    public SOAPBankServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOAPBANKSERVICESERVICE_QNAME, features);
    }

    public SOAPBankServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOAPBankServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SOAPBankService
     */
    @WebEndpoint(name = "SOAPBankServicePort")
    public SOAPBankService getSOAPBankServicePort() {
        return super.getPort(new QName("http://soap.server.bank.fhnw.ch/", "SOAPBankServicePort"), SOAPBankService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOAPBankService
     */
    @WebEndpoint(name = "SOAPBankServicePort")
    public SOAPBankService getSOAPBankServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.server.bank.fhnw.ch/", "SOAPBankServicePort"), SOAPBankService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPBANKSERVICESERVICE_EXCEPTION!= null) {
            throw SOAPBANKSERVICESERVICE_EXCEPTION;
        }
        return SOAPBANKSERVICESERVICE_WSDL_LOCATION;
    }

}