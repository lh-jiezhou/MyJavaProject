import java.util.ArrayList;
import java.util.List;

class MyTest {

    List<Integer> array;

    /** initialize your data structure here. */
    public MyTest() {
        array = new ArrayList<>();

    }

    public void addNum(int num) {
        // 插入排序维护 有序
        int pre_size = array.size();
        System.out.println(pre_size);
        for(int i=0; i<pre_size; i++){
            if(num < array.get(i)){
                array.add(i, num);
                break;
            }
        }
        if(pre_size == array.size()) array.add(num); // size

    }

    public double findMedian() {
        // 注意数量奇偶
        int count = array.size();
        if(count%2 == 1) return array.get(count/2);
        else {
            double res = (double)(array.get(count/2-1) + array.get(count/2))/2;
            return res;
        }

    }

    void test(){

//        array.add(-1);
//        for(int i=0; i<array.size(); i++){
//            System.out.println(array.get(i)+" ");
//        }
//        array.add(-2);
//        array.add(0, 10);

        for(int i=0; i<array.size(); i++){
            System.out.print(array.get(i)+" ");
        }
        System.out.println("Test "+array.size());

    }

    public static void main(String[] args) {
        MyTest obj = new MyTest();
        System.out.println("AA");
        obj.test();
        obj.addNum(-1);
        System.out.println(obj.findMedian());
        obj.addNum(-2);
        System.out.println(obj.findMedian());
        obj.addNum(-3);
        System.out.println(obj.findMedian());
        obj.addNum(-4);
        System.out.println(obj.findMedian());
//        obj.addNum(-5);
//        System.out.println(obj.findMedian());
        System.out.println("BB");
        obj.test();

//        double param_2 = obj.findMedian();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */