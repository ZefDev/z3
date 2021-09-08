import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class Computer {

    private byte[] secret;
    private String move;
    private String[] args;

    public Computer(String[] args) {
        this.args = args;
        //this.secret = generateSecretKey();
    }

    /*public byte[] generateSecretKey(){
        SecureRandom random = new SecureRandom();
        byte secret[] = new byte[16]; // 128 bits are converted to 16 bytes;
        random.nextBytes(secret);
        //this.secret = secret;
        return secret;
    }


    public String generateHmacWithMove(String move) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec s_key = new SecretKeySpec(secret,"HmacSHA256");
        sha256.init(s_key);
        return Helper.bytesToHex(sha256.doFinal(move.getBytes()));
    }*/
    public String generateRandomMove(){
        int a = (int) ( Math.random() * this.args.length );
        this.move = this.args[a];
        return this.args[a];
    }
    public void getResultGame(int b){
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(args));
        int curElem = list.size()/2;
        String elemsearch = list.get(b-1);
        ArrayList<String> winer = new ArrayList<>();
        ArrayList<String> looser = new ArrayList<>();
        winer = Helper.getLists(curElem,list,elemsearch,false);
        looser = Helper.getLists(curElem,list,elemsearch,true);
        System.out.println("Computer move: " + this.move);
        if (winer.indexOf(this.move)>=0){
            System.out.println("You win");
        }
        else if (looser.indexOf(this.move)>=0){
            System.out.println("You lose");
        }
        else {
            System.out.println("Draw");
        }
    }
    public byte[] getSecret() {
        return secret;
    }

    public void setSecret(byte[] secret) {
        this.secret = secret;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
