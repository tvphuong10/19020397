public class Word {
    public String Word_targer;
    public String Word_explain;
    public Word next;

    Word (Word a) {
        this.Word_targer = a.Word_targer;
        this.Word_explain = a.Word_explain;
        this.next = null;
    }

    Word (String tar, String exp) {
        this.Word_targer = tar;
        this.Word_explain = exp;
        this.next = null;
    }
}