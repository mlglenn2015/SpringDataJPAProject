package prv.mark.project.common.exception;

/**
 * Specialized exception that translates to a SOAP Fault when thrown.
 *
 * <p>
 *  This exception MUST BE THROWN when catching an exception if a SOAP fault needs to
 *  be sent back to the caller of a given web service method.
 * </p>
 *
 * @author mlglenn.
 */
//@SoapFault(faultCode = FaultCode.CLIENT)
public class SOAPClientException extends SOAPException {

    public SOAPClientException(final String message) {
        super(message);
    }

}
