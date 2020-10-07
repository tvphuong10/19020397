import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline(Dictionary dic) {
        System.out.println("How many words do you want to add ?");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print( "Word " + (i + 1) + "\n English : ");
            String tar  = in.nextLine();
            System.out.print("Vietnamese : ");
            String exp = in.nextLine();
            Word out = new Word(tar,exp);
            dic.add(out);
        }
        in.close();
    }

    public static void insertFromFile(Dictionary dic) {
        BufferedReader input = null;
        String[] data;
        String line;
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream("dictionaries.txt"), StandardCharsets.UTF_8));
            while ((line = input.readLine()) != null) {
                data = line.split("\t");
                Word out = new Word(data[0], data[data.length - 1]);
                dic.add(out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert input != null;
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dictionaryLookup(Dictionary dic) {
        System.out.println("Nhập từ muốn tra cứu");
        Scanner in = new Scanner(System.in);
        String search = in.nextLine().toLowerCase();
        Word w = dic.findTheMostSimilar(search);
        in.close();
        if (w.Word_targer.equals(search)) {
            System.out.println(search + "       :" + w.Word_explain + ".");
        } else {
            System.out.println("opp... từ bạn nhập không có trong từ điển.");
        }
    }

    public static boolean addWord(Dictionary dic) {
        System.out.println("Nhập từ bạn muốn thêm:");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().toLowerCase();
        Word w = dic.findTheMostSimilar(str);
        if (w.Word_targer.equals(str)) {
            System.out.println(str + "  :" + w.Word_targer + ".\n");
            System.out.println("Từ này đã tồn tại.");
            in.close();
            return false;
        } else {
            System.out.println("Nhập nghĩa của " + str + ":\n" );
            String exp = in.nextLine();
            dic.add(new Word(str,exp));
            in.close();
            return true;
        }
    }

    public static boolean UpdateWord(Dictionary dic) {
        System.out.println("Nhập từ bạn muốn sửa:");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().toLowerCase();
        Word w = dic.findTheMostSimilar(str);
        if (w.Word_targer.equals(str)) {
            System.out.println(str + "  :" + w.Word_targer + ".\n");
            System.out.println("Sửa lại " + str + ":\n" );
            w.Word_explain = in.nextLine();
            in.close();
            return true;
        } else {
            System.out.println("Từ này không tồn tại.");
            in.close();
            return false;
        }
    }

    public static boolean removeWord(Dictionary dic) {
        System.out.println("Nhập từ bạn muốn xóa:");
        Scanner in = new Scanner(System.in);
        in.close();
        String str = in.nextLine().toLowerCase();
        Word w = dic.findTheMostSimilar(str);
        if (w.next.Word_targer.equals(str)) {
            w = w.next;
            if (w.next == null) {
                w = null;
            } else {
                Word wnext = w.next;
                w.Word_targer = wnext.Word_targer;
                w.Word_explain = wnext.Word_explain;
                w.next = wnext.next;
                wnext = null;
            }
            return true;
        } else {
            System.out.println("từ này không tồn tại.");
            return false;
        }
    }
}
