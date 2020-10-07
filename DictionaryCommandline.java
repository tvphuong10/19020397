import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords(Dictionary dic) {
        dic.printAll();
    }

    public static void dictionaryBasic(Dictionary dic) {
        DictionaryManagement.insertFromCommandline(dic);
        DictionaryCommandline.showAllWords(dic);
    }

    public static void dictionaryAdvanced(Dictionary dic) {
        DictionaryManagement.insertFromFile(dic);
        DictionaryCommandline.showAllWords(dic);
        DictionaryManagement.dictionaryLookup(dic);
    }

    public static void dictionarySearcher(Dictionary dic) {
        System.out.println("Nhập từ khóa :");
        Scanner in = new Scanner(System.in);
        String search = in.nextLine();
        Word w = dic.findTheMostSimilar(search);
        System.out.println(dic.printStringFromWord(w.next, search));
        in.close();
    }

    public static void main (String[] ar) {
        Dictionary dic = new Dictionary();
        DictionaryManagement.insertFromFile(dic);
        DictionaryCommandline.dictionarySearcher(dic);
    }
}
