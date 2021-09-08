import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HMACGenerator {
    public static byte[] generateSecretKey(){
        SecureRandom random = new SecureRandom();
        byte secret[] = new byte[16]; // 128 bits are converted to 16 bytes;
        random.nextBytes(secret);
        //this.secret = secret;
        return secret;
    }
    public static String generateHmacWithMove(String move,byte[] secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec s_key = new SecretKeySpec(secret,"HmacSHA256");
        sha256.init(s_key);
        return Helper.bytesToHex(sha256.doFinal(move.getBytes()));
    }
}
