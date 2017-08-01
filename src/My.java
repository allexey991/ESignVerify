import java.security.InvalidKeyException;
import java.security.SignatureException;

public  class My {
    
    public static void main(String[] args) throws InvalidKeyException, SignatureException, Exception {
    	String msg = args[0];//"123";
    	String sign = args[1];//"MC0CFCBdfucJYMJPFAuYV47R/Dwh6LjWAhUAlumKS/UTreOuZ/aTL+Cx4GkgJ44=";
    	String pubKey = args[2];//"C:/Users/allexey991/Desktop/SignForm-master/HTML5/data/cert.pem";
    	/*String msg = "123";
    	String sign = "MC0CFCBdfucJYMJPFAuYV47R/Dwh6LjWAhUAlumKS/UTreOuZ/aTL+Cx4GkgJ44=";
    	String pubKey = "C:/Users/allexey991/Desktop/SignForm-master/HTML5/data/cert.pem"; */
    	
    	SignVerify verify = SignVerify.init(msg, sign, pubKey);
    	boolean isValid = verify.Verify();
    	
	    	if(isValid)
	    	{
	    		System.out.println("Signature valid");
	    	} else
	    	{
	    		System.out.println("Signature NOT valid");
	    		
	    	}
	    	verify.getLastError();
    	
    	
    }
}
