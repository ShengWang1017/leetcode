package com.leetcode.leecodeLinkedList;

import java.util.*;

public class Solution {

    //ListNode

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 160. 相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * 图示两个链表在节点 c1 开始相交：
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*  //1.哈希表法 时间：O(m+n)
        if (headA == null || headB == null) {
            return null;
        }

        HashSet<ListNode> visited = new HashSet<>();
        while (headA != null) {
            visited.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (visited.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;*/

        //2.双指针法
        //关键是，两者同时从头触发，每次同时向后进一个，那么它们走过的路程s永远一样，那么如果共同路段是c，a+c +b =s ; b+c + a=s;

        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;

        }
        return pA;
    }

    /**
     * LC206. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
     */
    public static ListNode reverseList(ListNode head) {
        //reverse(head) = reverse(head.next) + F(head)

        //  基线条件：停止时机
        //  是空的链表 || 最终停止条件，即找到最后一个节点为新的头节点
        if (head == null || head.next == null) {
            return head;
        }

        //递归
        //最终的reverseList（head）
        //当找到最终的节点时，其实这个newHead一直等于这个最终节点
        ListNode newHead = reverseList(head.next);

        //F(head)
        head.next.next = head;
        head.next = null;   //不让形成环

        return newHead;

    }
    /*public static ListNode reverseList(ListNode head) {
        //1. 迭代法
        ListNode next = reverseList(head.next)
        next.next = head;

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            // 暂存当前节点的下一个节点
            ListNode n = current.next;
            // 反转当前节点的指针
            current.next = prev;
            // 移动 prev 和 current 指针
            prev = current;
            current = n;
        }

        // prev 最终会指向新的头节点
        return prev;

    }*/


    /**
     * 234. 回文链表
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        //1. 创建一个新的数组来比较
        ArrayList<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }

        for (int i = 0; i < nodes.size() / 2; i++) {
            if (nodes.get(i)!=nodes.get(nodes.size()-i-1))
                return false;
        }
        return true;
        }


    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            fast =fast.next;
            if (fast ==null) break;
            if (fast == slow) {
                return true;
            }
            fast = fast.next;
            if (fast==null) break;
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * 1. 使用哈希表记录走过的节点
     * 2. 使用快慢指针，发现a = (n-1)(b+c) + c .所以快慢指针相遇时，再出发一个慢指针，最终两个慢指针会再cycle起点相遇
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) break;
            visited.add(head);
            head = head.next;
        }
        return head;
    }

    /**
     * 21. 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1==null?list2:list1;
        }
        ListNode head;
        if (list1.val < list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }

        ListNode pre = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            } else {
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }
        }
        head.next = list1 == null?list2:list1;
        return pre;
    }


    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0) ;
        //利用current，记录链表的链接
        ListNode current = pre;
        //用carry来记录上次计算的进位，因为需要记录到下个循环，所以要循环之外创建
        int carry=0;
        while (l1 != null || l2 != null||carry!=0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1=l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2=l2.next;
            }
            carry=sum/10;
            ListNode newNode = new ListNode(sum % 10);
            current.next=newNode;
            current = newNode;
        }
        return pre.next ;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            listNodes.add(head);
            head=head.next;
        }
        int size = listNodes.size();
        if (size<2) return null;
        if (size==n)return listNodes.get(1);
        ListNode preN = listNodes.get( size- n - 1);
        preN.next=preN.next.next;
        return listNodes.get(0);
    }

    /**
     *
     * 1. 迭代
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head.next;
        ListNode current = head;
        ListNode pre = new ListNode(0);
        while (current!=null&&current.next!=null) {
            ListNode post = current.next;
            current.next=post.next;
            post.next = current;
            pre.next = post;
            pre = current;
            current=current.next;
        }
        return dummy;
    }

    public ListNode swapPairs2(ListNode head) {
        //3. 什么时候终止，或者最后一步是什么样的
        if (head==null||head.next==null)return head;

        //1. 先想递归的下一步呗
        ListNode newNode = head.next;
        ListNode nextPair = swapPairs2(newNode.next);

        //2. 函数的功能是什么？
        newNode.next = head;
        head.next = nextPair;

        //4. 每一层返回什么，此处是交换后的第一个节点
        return newNode;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = head;

        for (int i = 0; i < k; i++) {
            if (head==null) return dummy;
            head = head.next;
        }
        head = dummy;
        //函数的功能，翻转k个数
        ListNode pre =null;
        for (int i = 0; i < k; i++) {
            ListNode post = head.next;
            head.next = pre;
            pre = head;
            head = post;
        }

        //递归
        dummy.next = reverseKGroup(head, k);

        return pre;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 138. 随机链表的复制
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Node, Integer> nodeMap = new HashMap<>();
        int count = 0;
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            nodes.add(newNode);
            //！！！要把原链表的节点放进来，后续查找对应的索引
            nodeMap.put(current, count++);
            current = current.next;
        }

        nodes.add(null);
        current = head;
        count=0;
        while (current != null) {
            //当前List中的node，next指向list中的下一个
            nodes.get(count).next = nodes.get(count + 1);
            //当前原链表即current的random指向赋值给random变量
            Node random = current.random;
            //random很可能是null,如果不判断，会出现get（null）异常
            if (random == null) {
                nodes.get(count).random = null;
            } else {
                Integer index = nodeMap.get(random);
                nodes.get(count).random = nodes.get(index) ;
            }
            count++;
            current=current.next;
        }
        return nodes.get(0);
    }

    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        //停止条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode newHead = sortList(head);
        ListNode newMid = sortList(mid);

        ListNode mergeHead = merge(newHead, newMid);
        return mergeHead;

    }
    //两链表升序合并
    private ListNode merge(ListNode newHead, ListNode newMid) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (newHead != null && newMid != null) {
            if (newHead.val < newMid.val) {
                current.next = newHead;
                current = current.next;
                newHead = newHead.next;
            } else {
                current.next = newMid;
                current = current.next;
                newMid = newMid.next;
            } 
        }
        //某个链表全排完之后
        if (newHead == null) current.next=newMid;
        else current.next = newHead;

        return dummy.next;
    }
    public ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre =null;//用来切断指针的
        while (fast != null && fast.next != null) {
            pre =slow;
            fast=fast.next.next;
            slow = slow.next;
        }

        //返回slow作为下一段的起点，需要切断上一段到这一段的链接，所以前面要一个pre来记录
        if (pre != null) {
            pre.next =null;
        }

        return slow;
    }


    /**
     * 23. 合并 K 个升序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        if (lists.length==0) return null;

        for (ListNode list : lists) {
            if (list!=null) pq.add(list);
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            if (minNode.next!=null) pq.add(minNode.next);
            current.next = minNode;
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * 146. LRU 缓存
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     */
    class LRUCache {
        private TreeMap<Integer,Integer> map;
        private int capacity;

        public LRUCache(int capacity) {
            this.map = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return map.get(o1)-map.get(o2);
                }
            });
            this.capacity = capacity;
        }

        public int get(int key) {
            return map.get(key);
        }

        public void put(int key, int value) {
            if (map.size() == capacity) {
                map.pollFirstEntry();
                map.put(key, value);
            } else {
                capacity++;
                map.put(key,value);
            }
        }
    }
}
