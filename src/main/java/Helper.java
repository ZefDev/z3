

import de.vandermeer.asciitable.AsciiTable;

import java.util.*;

public class Helper {
    public static boolean checkDublicat(String[] data)
    {
        Set<String> mySet = new LinkedHashSet<String>(Arrays.asList(data));
        if (mySet.size() == data.length){
            return false;
        }
        return true;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for(byte b: bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    public static ArrayList<String> getLists(int count, ArrayList<String> list, String elemsearch, boolean reverse){
        boolean isFind = false;
        ArrayList<String> tempList = new ArrayList<String>();
        if (reverse){
            Collections.reverse(list);
        }
        for (Iterator<String> iter = list.listIterator(); iter.hasNext(); ) {
            String element = iter.next();
            if (element == elemsearch){
                isFind = true;
            }
            if (count == 0){
                break;
            }
            if (isFind && element!=elemsearch) {
                tempList.add(element);
                count--;
            }
            if (iter.hasNext()==false){
                iter = list.listIterator();
            }
        }
        return tempList;
    }

    public static void showRules(String[] args){

        AsciiTable at = new AsciiTable();
        String[] temp_header =  new String[args.length +1];
        temp_header[0] = "";
        for (int i=0;i<args.length;i++) {
            temp_header[i+1] = args[i];
        }
        at.addRule();
        at.addRow(temp_header);
        String[] temp_row =  new String[args.length +1];
        for (int i=0;i<args.length;i++) {
            temp_row[0] = args[i];
            for (int b=0;b<args.length;b++) {
                String move = args[i];
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(args));
                int curElem = list.size() / 2;
                String elemsearch = list.get(b);
                ArrayList<String> winer = new ArrayList<>();
                ArrayList<String> looser = new ArrayList<>();
                winer = Helper.getLists(curElem, list, elemsearch, false);
                looser = Helper.getLists(curElem, list, elemsearch, true);
                if (winer.indexOf(move) >= 0) {
                    temp_row[b+1] = "Win";
                } else if (looser.indexOf(move) >= 0) {
                    temp_row[b+1] = "Lose";
                } else {
                    temp_row[b+1] = "Draw";
                }
            }
            at.addRule();
            at.addRow(temp_row);

        }
        String rend = at.render();
        System.out.println(rend);
    }
}
