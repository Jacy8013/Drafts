package io.jacy.drafts.arith;

import java.util.Arrays;

public class SingleLinkReverse {
    public static void main(String[] args) {
        Link link1 = new Link(1);
        Link link2 = new Link(2);
        Link link3 = new Link(3);
        Link link4 = new Link(4);
        Link link5 = new Link(5);
        Link link6 = new Link(6);
        Link link7 = new Link(7);

        link1.next = link2;
        link2.next = link3;
        link3.next = link4;
        link4.next = link5;
        link5.next = link6;
        link6.next = link7;

        Link next = reverse2(link1, 1, 1);
        do {
            System.out.print(next.value + ",");
        } while ((next = next.next) != null);
        System.out.println();
    }

    static Link reverse2(Link link, int left, int right){
        if (link == null || left >= right) {
            return link;
        }
        left = Math.max(left, 0);

        Link head = link;
        Link leftNode = null;
        Link preLeft = null;
        Link cur = link;
        Link next;
        for (int i = 0; cur != null; i++) {
            // 记录下一个节点
            next = cur.next;

            if (i == left - 1) {
                preLeft = cur;
            }
            if (i == left) {
                leftNode = cur;
            }

            if (left < i && i <= right) {
                if (preLeft == null) {
                    cur.next = head;
                    leftNode.next = next;
                    head = cur;
                } else {
                    Link tmp = preLeft.next;
                    preLeft.next = cur;
                    cur.next = tmp;
                    leftNode.next = next;
                }
            }

            cur = next;
        }

        return head;
    }

    static Link reverse(Link link, int left, int right) {
        if (link == null || left >= right) {
            return link;
        }

        int length = 0;
        Link next = link;
        do {
            length++;
        } while ((next = next.next) != null);
        Link[] linkArray = new Link[length];

        next = link;
        int index = 0;
        do {
            linkArray[index++] = next;
        } while ((next = next.next) != null);

        for (int i = 0; i <= right - left; i++) {
            swap(linkArray, left + i, right - i);
        }

        Link head = linkArray[0];
        Link nx = head;
        for (int i = 1; i < linkArray.length; i++) {
            nx.next = linkArray[i];
            nx = nx.next;
        }

        return head;
    }

    static void swap(Link[] links, int left, int right) {
        if (left >= right) {
            return;
        }
        Link tmp = links[left];
        tmp.next = null;
        links[left] = links[right];
        links[left].next = null;
        links[right] = tmp;
        links[right].next = null;
    }

    static class Link {
        int value;
        Link next;

        public Link(int value) {
            this.value = value;
        }


        @Override
        public String toString() {
//            StringBuilder builder = new StringBuilder("[");
//            Link nx = this;
//            do {
//                builder.append(nx.value).append(", ");
//            } while ((nx = nx.next) != null);
//            builder.append("]");
//            return builder.toString();
            return this.value + "";
        }
    }
}
