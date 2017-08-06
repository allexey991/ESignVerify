import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;

//import Decoder.BASE64Encoder;


public class SignVerify {
	private Signature _sign;
	private String _error = "";
	private byte[] _msgSign;
	private boolean _debugMode = false;
	//initialization parameters
	public void init(String Mes, String Sign, String PublicKeyPath,boolean mode){
		//SignVerify verify = new SignVerify();
		setDebugMode(mode);
		try{
			_sign = Signature.getInstance("SHA1withDSA");
			PublicKey publicKey = getPublic(PublicKeyPath);
			if (publicKey == null)
				return;
			_sign.initVerify(publicKey);
			_sign.update(Mes.getBytes());
			if(_debugMode)
			System.out.println("Generating public key from file: OK");
			
			Base64.Decoder decoder = Base64.getDecoder();
			_msgSign = decoder.decode(Sign);
			if(_debugMode)
			System.out.println("Key initialization: OK"); 
			
		}catch (Exception e){
			_error = "Wrong public key format!";
			
		}

		
		
	}
	//verify sign
	public boolean Verify() {
		boolean isValid;
		try {
			isValid = _sign.verify(_msgSign);
			return isValid;
		}catch (Exception e){
			_error = "Invalid encoding for signature!";
		}
		return false;
	}
	//get public key from file
	public PublicKey getPublic(String filename) throws Exception {
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		try {
	    	FileInputStream is = new FileInputStream (filename);
	    	try {
		    	if(_debugMode)
		    	System.out.println("File reading: OK");
		    	X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
		    	PublicKey key = cer.getPublicKey();
			    return key;
	    	}catch (Exception e){
	    		_error = "Wrong structure, couldn't parse certificate";
	    	}
	    }catch (Exception e){
	    	_error = "File Not Found!";
	    }                                                                                                                                                                                                                                                        
	    return null;
		
	}
	public String getLastError() {
		return _error;
	}
	public void setDebugMode(boolean mode) {
		_debugMode = mode;
	}
	public boolean getDebugMode() {
		return _debugMode;
	}
	 
	
}