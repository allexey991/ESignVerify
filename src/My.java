import java.security.InvalidKeyException;
import java.security.SignatureException;

public class My {
    
    public static void main(String[] args) throws InvalidKeyException, SignatureException, Exception {
    	 
    	try{
    	/*String msg = args[0];//"123";
    	String sign = args[1];//"MC0CFCBdfucJYMJPFAuYV47R/Dwh6LjWAhUAlumKS/UTreOuZ/aTL+Cx4GkgJ44=";
    	String pubKey = args[2];//"C:/Users/allexey991/Desktop/SignForm-master/HTML5/data/cert.pem";
    	if (args[3] == 1)
    		boolean mode = true;
    	else
    		boolean mode = false;
    	 */    	
    	String msg = "123";
        String sign = "MCwCFAVpJy+6qVN+v77hNon8zfs8LkDgAhQbfDtAA/BzVjZPPju2MLSengfhZA==";
        String pubKey = "C:/Users/allexey991/Desktop/SignForm-master/HTML5/data/cert.pem";
    	boolean mode = true;
    	boolean isValid = false;
    	
    	SignVerify verify = new SignVerify();
    	verify.init(msg, sign, pubKey,mode);
    	if (verify.getLastError() == "")
    	{
    		verify.Verify();
    	
	    	if(verify.getDebugMode()==true)
	    	{
	    		if(isValid)
	    		{
	    			System.out.println("Signature valid");
	    		}else
	    			System.out.println("Signature NOT valid");
	    		verify.getLastError();
	    	} 
    	}	    		
    	}catch (Exception e){
    		System.out.println("Wrong input parameters!");
    	}
    	
    }
}
