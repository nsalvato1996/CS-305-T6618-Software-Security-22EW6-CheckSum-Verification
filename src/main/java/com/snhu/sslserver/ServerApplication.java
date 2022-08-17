package com.snhu.sslserver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name. 
	public static String calculateHash(String name) throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance("SHA-512");  
        byte[] hash =  md.digest(name.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32){  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
     
    } 
	
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Hello Nicholas Salvato!";
    	String hash = calculateHash(data);
       
    	return "<p>data:"+data+" : SHA-512 "+" : " + "CheckSum Value: "+hash;
    }
}
