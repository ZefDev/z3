import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        /*boolean isError = false;
        do {
            if (!validateErrors(args)){
                isError = true;
            }
        } while (isError);*/
        if(!validateErrors(args)){
            return;
        }
        int errors =0;
        do {
            Computer computer = new Computer(args);
            byte[] secret = HMACGenerator.generateSecretKey();
            String move = computer.generateRandomMove();
            String HMAC = HMACGenerator.generateHmacWithMove(move,secret);
            System.out.println("HMAC: " + HMAC);

            printMenu(args);

            Player player = new Player();
            Scanner s = new Scanner(System.in);
            if(s.hasNextInt())
            {
                player.setMove(s.nextInt());
                errors = 0;
            }
            else{
                errors++;
                String temp = s.nextLine();
                if(temp.equals("help")) {
                    Helper.showRules(args);
                }
                continue;
            }
            int b = player.getMove();
            if (b == 0){
                break;
            }
            System.out.println("Your move:" + args[b-1]);
            computer.getResultGame(player.getMove());
            System.out.println("HMAC: "+Helper.bytesToHex(secret));
        } while(errors>0);

    }

    public static boolean validateErrors(String[] args){
        if(args.length<3){
            System.out.println("Ошибка: меньше 3ех элементов");
            return false;
        }
        if (args.length % 2 == 0){
            System.out.println("Ошибка: чётное число строк");
            return false;
        }
        if(Helper.checkDublicat(args)){
            System.out.println("Ошибка: в аргументе найдены дубликаты");
            return false;
        }
        return true;
    }

    public static boolean validateStep(String[] args,String move){
        if(args.length<3){
            System.out.println("Ошибка: меньше 3ех элементов");
            return false;
        }
        if (args.length % 2 == 0){
            System.out.println("Ошибка: чётное число строк");
            return false;
        }
        if(Helper.checkDublicat(args)){
            System.out.println("Ошибка: в аргументе найдены дубликаты");
            return false;
        }
        return true;
    }

    public static void printMenu(String[] args){
        System.out.println("Available moves:");
        for (int i=0; i<args.length; i++)
        {
            System.out.println(i+1 +" - " + args[i]);
        }
        System.out.println("0 - exit");
        System.out.print("Enter your move: ");
    }
}
