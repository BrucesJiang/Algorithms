package fundamentals.bagQueueStack;

import util.api.In;
import util.api.StdOut;


/**
 *  从标准输入读取一串字符， 使用链表保存这些字符并清除重复字符。当读取到一个从未见过的字符，将它插入表头。
 *  当读取一个重复字符，将其重链表中删除并再次插入到表头。
 *  这就是著名的：前移编码策略，基于最近访问的元素很可能会再次访问。因此可以用于缓存，数据压缩等场景
 *
 * @auther Bruce Jiang
 */
public class MoveToFront<I> {
    private Node<I> head; // the head of the list
    private int size; // the size of the list

    private class Node<I>{
        private I item;
        private Node<I> next;
        public Node(I item, Node<I> next){
            this.item = item;
            this.next = next;
        }
    }

    public MoveToFront(){
        clear();
    }

    public void clear(){
        this.head = new Node<I>(null, null);
        this.size = 0;
    }

    public void add(I item){
        existAndRemove(item);
        Node<I> node = new Node<I>(item, head.next);
        head.next = node;
        size ++;
    }

    private void existAndRemove(I item){
        Node<I> current = head;
        while(current.next != null && current.next.item.equals(item));
        if(current.next != null){
            current = current.next;
            size --;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<I> current = head.next;
        while(current != null){
            sb.append(current.item + " ");
            current = current.next;
        }
        return sb.toString();
    }

    /**
     * Unit test {@code MoveToFront} data type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        MoveToFront<Integer> mtf = new MoveToFront<Integer>();
        String fileName = "MoveToFront.txt";
        In in = new In(fileName);
        while(!in.isEmpty()){
            StdOut.println("haod");
            //StdOut.println(in.readInt());
            mtf.add(in.readInt());
            StdOut.println(mtf.toString());
        }
    }
}
