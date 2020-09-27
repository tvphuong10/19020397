public class DictionaryCommandline {
    public static void showAllWords(Dictionary dic) {
        dic.printAll();
    }

    public static void dictionaryBasic(Dictionary dic) {
        DictionaryManagement.insertFromCommandline(dic);
        DictionaryCommandline.showAllWords(dic);
    }

    public static void main (String[] ar) {
        Dictionary dic = new Dictionary();
        DictionaryCommandline.dictionaryBasic(dic);
    }
}
