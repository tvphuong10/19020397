public class Dictionary {
    private Word head;

    public Dictionary () {
        this.head = new Word("this just a null Word", "use less");
    }

    public void add (Word a) {
        a.Word_targer = a.Word_targer.toLowerCase();
        a.Word_explain = a.Word_explain.toLowerCase();
        Word pointer = findTheMostSimilar(a.Word_targer);
        if (pointer.next != null && pointer.next.Word_targer.equals(a.Word_targer)) {
            pointer.next.Word_explain = a.Word_explain;
        } else {
            a.next = pointer.next;
            pointer.next = a;
        }
    }

    public void printAll (){
        Word current = head.next;
        int count = 1;
        System.out.println("No   |                 English |                Vietnamese");
        while (current != null) {
            System.out.printf("%4d | %23s | %25s \n",count,current.Word_targer,current.Word_explain);
            current = current.next;
            count ++;
        }
    }

    public String printStringFromWord (Word pointer, String str) {
        StringBuilder ret = new StringBuilder();
        while (pointer != null) {
            if (pointer.Word_targer.length() < str.length()) {
                return ret.toString();
            }
            for (int i = 0; i < str.length(); i++) {
                if (pointer.Word_targer.charAt(i) != str.charAt(i)) {
                    return ret.toString();
                }
            }
            ret.append(pointer.Word_targer).append("\n");
            pointer = pointer.next;
        }
        return ret.toString();
    }

    public Word findTheMostSimilar (String tar) {
        Word current = head.next;
        Word before = head;
        boolean bool;
        int length = 1;
        Character a,b;
        while (current != null) {
            bool = true;
            for (int i = 0; i < length; i++) {
                if (i < tar.length())
                    a = tar.charAt(i);
                else a = 0;

                if (i < current.Word_targer.length())
                    b = current.Word_targer.charAt(i);
                else b = 0;

                if( a > b) {
                    bool = false;
                    break;
                } else if (a < b) return before;
            }
            //
            if (bool) {
                if (length == tar.length()) return before;
                length++;
            } else {
                current = current.next;
                before = before.next;
            }
        }
        return before;
    }
}
