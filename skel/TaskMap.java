import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskMap {
    String nameFile;
    int offset;
    long length;
    Dictionary dictionary;
    ExecutorService exe;
    AtomicInteger inQueue;
    String delim = "~';:/?˜\\.,><‘\\[\\]{}()!@#$%ˆ&\\- +=*\\”| \r\t\n";

    public TaskMap(String nameFile,  int offset, long length,Dictionary dictionary, ExecutorService exe, AtomicInteger inQueue) {
        this.nameFile = nameFile;
        this.offset = offset;
        this.length = length;
        this.dictionary = dictionary;
        this.exe = exe;
        this.inQueue = inQueue;
    }

    public Future<Dictionary> calculate(){
        return exe.submit(() -> {
            File f = new File(nameFile);
            FileReader fr = null;
            try {
                fr = new FileReader(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader brr = null;
            if (fr != null) {
                brr = new BufferedReader(fr);
            }
            char[] content = new char[(int)f.length()];
            try {
                brr.read(content,0, (int)f.length());
                //System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int count;
            int j = 0;
            char[] aux = new char[(int)f.length()];
            for(int i = offset; i < offset + length; i++) {
                /*if(offset == 0) {
                    aux[j] = content[i];
                    j++;*/
                    //aici am scris cuvantul de la offset 0
                /*}*//*else {
                    if (Character.isLetterOrDigit(content[offset - 1])) {
                        count = 0;
                        while ((count < length) && Character.isLetterOrDigit(content[offset + count])) {
                            if (count < length) {
                                count++;
                            }
                            //System.out.println("i ul tau e " + i);
                            i++;
                        }
                    }*/
                    /*if (Character.isLetterOrDigit(content[(int)(offset + length)])) {
                        count = 0;
                        *//*i = (int)length + 1;*//*
                        while (Character.isLetterOrDigit(content[(int) (offset + length) + 1 + count])) {
                            aux[j] = content[(int) (offset + length) + 1 + count];
                            count++;
                            i++;
                        }
                    }*/
                    aux[j] = content[i];
                    j++;
                /*}*/
            }
//            if (Character.isLetterOrDigit(content[(int)(offset + length)])) {
//                count = 0;
//                j = (int)length + 1;
//                while (Character.isLetterOrDigit(content[(int)(offset + length) + 1 + count])) {
//                    aux[j] = content[(int)(offset + length) + 1 + count];
//                    count++;
//                    j++;
//                }
//            }
            String s = String.valueOf(aux);
            String[] arrOfStr = s.split("[~';:/?˜\\.,><‘\\[\\]{}()!@#$%ˆ&\\- +=*\\”|\r\t\n\0]+");
            int lengthmaxim = 0;
            lengthmaxim = arrOfStr[0].length();
            for (int i = 0; i < arrOfStr.length; i++) {

                System.out.println("cuvantu = "+  arrOfStr[i] + " si numarul = " + arrOfStr[i].length());
                if (lengthmaxim < arrOfStr[i].length()) {
                    lengthmaxim = arrOfStr[i].length();
                }
                if (dictionary.sacks.containsKey(arrOfStr[i].length())) {
                    int v;
                    v = dictionary.sacks.get(arrOfStr[i].length());
                    v++;
                    dictionary.sacks.put(arrOfStr[i].length(), v);
                } else {
                    dictionary.sacks.put(arrOfStr[i].length(), 1);
                }
            }
            /*System.out.println("am nevoie de " + String.valueOf(aux));
            String s = String.valueOf(aux);
            StringTokenizer st1 = new StringTokenizer(s, delim);
            String[] clone = new String[s.length()];
            count = 0;

            int lengthmaxim = 0;
            while (st1.hasMoreTokens()) {
                String staux = st1.nextToken();
                clone[count] = staux;
                byte[] test = clone[count].getBytes(StandardCharsets.UTF_8);


                for (int i = 0; i < clone[count].length(); i++) {
                    System.out.println("asta e ce trebuie" + test[i] + " " + ((int)test[i]));
                }
                //System.out.println("cuvantu = "+  clone[count] + " si numarul = " + clone[count].length());

                if (lengthmaxim < clone[count].length()) {
                    lengthmaxim = clone[count].length();
                }
                if (dictionary.sacks.containsKey(clone[count].length())) {
                    int v;
                    v = dictionary.sacks.get(clone[count].length());
                    v++;
                    dictionary.sacks.put(clone[count].length(), v);
                } else {
                    dictionary.sacks.put(clone[count].length(), 1);
                }
                count++;
            }*/
            return dictionary;
        });
    }

    @Override
    public String toString() {
        return "TaskMap{" +
                "nameFile='" + nameFile + '\'' +
                ", offset=" + offset +
                ", length=" + length +
                ", dictionary=" + dictionary +
                ", exe=" + exe +
                ", inQueue=" + inQueue +
                ", delim='" + delim + '\'' +
                '}';
    }
}