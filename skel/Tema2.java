import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Tema2 {
    public static void main(String[] args) throws IOException, RejectedExecutionException, ExecutionException, InterruptedException {
        if (args.length < 3) {
            System.err.println("Usage: Tema2 <workers> <in_file> <out_file>");
            return;
        }
        int length = 0;
        int nrFiles;
        Dictionary d = new Dictionary();
        List<File> files = new ArrayList<>();
        File file = new File(args[1]);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            if (line != null) {
                length = Integer.parseInt(line);
                System.out.println(length);
            }
            line = br.readLine();
            if (line != null) {
                nrFiles = Integer.parseInt(line);
                System.out.println(nrFiles);
            }
            while ((line = br.readLine()) != null) {
                files.add(new File(line));
            }
            System.out.println(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AtomicInteger inQueue = new AtomicInteger(0);
        ExecutorService exe = Executors.newFixedThreadPool(Integer.parseInt(args[0]));

        for(File f : files) {
            long size = f.length();
            int offset = 0;
            while(true) {
                if (size > length) {
                    inQueue.incrementAndGet();
                    TaskMap t = new TaskMap(f.toString(), offset, length, d, exe, inQueue);
                    Future<Dictionary> future = t.calculate();
                    d = future.get();
                    System.out.println(d);
                    size -= length;
                    offset += length;
                } else {
                    inQueue.incrementAndGet();
                    TaskMap t = new TaskMap(f.toString(), offset, size, d, exe, inQueue);
                    Future<Dictionary> future = t.calculate();
                    d = future.get();
                    System.out.println(d);

                    break;
                }
            }
        }
        exe.shutdown();
    }
}
