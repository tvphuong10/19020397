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
}
