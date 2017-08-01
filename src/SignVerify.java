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

import Decoder.BASE64Encoder;


public class SignVerify {
	private Signature _sign;
	private static String error = "";
	private byte[] _msgSign;
	//initialization parameters
	public static SignVerify init(String Mes, String Sign, String PublicKeyPath){
		SignVerify verify = new SignVerify();
		try{
			verify._sign = Signature.getInstance("SHA1withDSA");
			PublicKey publicKey = SignVerify.getPublic(PublicKeyPath);
			verify._sign.initVerify(publicKey);
			verify._sign.update(Mes.getBytes());
			System.out.println("Generating public key from file: OK");
			
			Base64.Decoder decoder = Base64.getDecoder();
			verify._msgSign = decoder.decode(Sign);
			
			System.out.println("Key initialization: OK"); 
			return verify;
		}catch (Exception e){
			if (verify.error == "")
				error = "Wrong public key format!";
		}

		
		return verify;
	}
	//verify sign
	public boolean Verify() {
		boolean isValid;
		try {
			isValid = _sign.verify(_msgSign);
			return isValid;
		}catch (Exception e){
			if (error == "")
				error = "Invalid encoding for signature!";
		}
		return false;
	}
	//get public key from file
	public static PublicKey getPublic(String filename) throws Exception {
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		try {
	    	FileInputStream is = new FileInputStream (filename);
	    	try {
	    	System.out.println("File reading: OK");
	    	X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
	    	PublicKey key = cer.getPublicKey();
		    return key;
	    	}catch (Exception e){
	    		if (error == "")
	    			error = "Wrong structure, ñouldn't parse certificate";
	    	}
	    }catch (Exception e){
	    	if (error == ""){
	    		error = "File Not Found!";}
	    }                                                                                                                                                                                                                                                        
	    return null;
		
	}
	public void getLastError() {
		System.out.println(error);
	}
	 
	
}
