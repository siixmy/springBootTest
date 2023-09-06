package com.example.demo;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 19484 on 2023/8/17.
 */
public class Test1 {
    public static Test1 instance = new Test1();
      String kk = "333";

    public Test1() {
    }

    public Test1 getInstance(Test1 t) {
        return instance;
    }

    public static Boolean containValue(int[] c, int value) {
        for (int i = 0; i < c.length; i++) {
            if (c[i] == value) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static int repeatValue(int[] a, int[] b) {
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[j] == a[i]) {
                    k++;
                }
            }
        }
        return k;
    }

    public static int[] orderValue(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }
   static  int arr[]=new int[10];
    public static void main(String[] args) {
        System.out.println(arr[1]);
        int[] a = {9, 1, 3, 5, 7};
        a = orderValue(a);
        Arrays.stream(a).forEach(t -> System.out.print(t + "\t"));
        System.out.println();
        int[] b = {1, 5, 7, 8};
        int[] c = new int[a.length + b.length - repeatValue(a, b)];
        int a1 = 0;
        int b1 = 0;
        int c1 = 0;
        while (a1 < a.length && b1 < b.length) {
            if (a[a1] < b[b1]) {
                if (!containValue(c, a[a1])) {
                    c[c1++] = a[a1++];
                } else {
                    a1++;
                }
            } else {
                if (!containValue(c, b[b1])) {
                    c[c1++] = b[b1++];
                } else {
                    b1++;
                }
            }
        }
        while (b1 < b.length) {
            c[c1++] = b[b1++];
        }
        while (a1 < a.length) {
            c[c1++] = a[a1++];
        }
        Arrays.stream(c).forEach(System.out::print);
        System.out.println();
        List<Integer> list = Stream.of(Arrays.stream(a).boxed().collect(Collectors.toList()), Arrays.stream(b).boxed().collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().filter(t -> t != 100).map(t -> t + 1).sorted((o1, o2) -> o2.compareTo(o1)).collect(Collectors.toList());
        int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println();
        String s = "44,22";
        Splitter.on(",").omitEmptyStrings().trimResults().splitToList(s).stream().forEach(t -> System.out.printf("%3s", t));
        System.out.println();
        double k = (0 + (double) 5d) / 2;
        System.out.println(k);
        // yh();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(new BigDecimal(2.3).multiply(new BigDecimal(5.2))));
        System.out.println(new BigDecimal(10.9).divide(new BigDecimal(2), 1, BigDecimal.ROUND_HALF_UP));
        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        map.put(4, 5);
        for (Map.Entry<Integer, Integer> map1 : map.entrySet()) {
        }
        map.forEach((o, v) -> {
            System.out.println(o);
        });
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
        }
        String z1="aaa";
        String z2="aaa";
        String z3=new String("aaa");
        System.out.println(z1==z2);
        System.out.println(z1==z3);
        System.out.println(z1=="aaa");
        System.out.println(""+'a'+1);
//        jiTu();
//        System.out.println(binarySearch(a, 7, 0, a.length - 1));
//        Collections.reverse(aa);
        //   new Scanner(System.in).nextInt();
//        Date time = new Calendar().getTime();
//        Date time1 = Calendar.getInstance().getTime();
//        char qq="c";
//        int qaa=10.0;
//        Boolean ff=null;
//        float ooo=3.14;
        System.out.println(System.getProperty("java.class.path"));
    }

    public static void jiTu() {
        int chicken;//鸡数
        int rabbit;//兔子数
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入有多少个头");
        int head = scanner.nextInt();
        System.out.println("请输入有多少个脚");
        int foot = scanner.nextInt();
        for (chicken = 1; chicken <= head; chicken++) {
            rabbit = head - chicken;
            if (rabbit * 4 + chicken * 2 == foot) {
                System.out.println("兔子" + rabbit + "只鸡" + chicken + "只");
                break;
            }
        }
    }

    private static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high);
        } else {
            return binarySearch(arr, target, low, mid - 1);
        }
    }

    public static void yh() {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入杨辉三角的层数：");//为了输出美观，层数不要超过13.若层数需要超过13，可以通过添加输出杨辉三角中的"\t"来运行。
        int row = s.nextInt();//输入一个杨辉三角的层数。
        //确定二维数组的长度
        /*
        这个二维数组中有row个一维数组;
        因为通过杨辉三角的规律，第零行有一个值，第一行有三个值（包含0），第二行有五个值以此类推，第row行有2*row+1个值；
        所以每个一维数组的长度为2*row+1；
         */
        int[][] arr = new int[row][2 * row + 1];

        //给第一行最中间赋值1
        arr[0][row] = 1;
        //将杨辉三角中的数表示出来    第一行arr[0][row]直接赋值，所以从第二行开始
        for (int i = 1; i < arr.length; i++) {
            //每一行从第二个数开始到倒数第二个结束，因为最左边没有左上的值，最右边没有右上的值都为0
            for (int j = 1; j < arr[i].length - 1; j++) {
                //杨辉三角中的值等于它的左上角的值+右上角的值。
                arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j + 1];

            }
        }
        //遍历输出杨辉三角
        //从第一个一维数组开始
        for (int i = 0; i < arr.length; i++) {
            //输出第i个一维数组中的值
            for (int j = 0; j < arr[i].length; j++) {
                //如果值不等于零时，将它输出
                if (arr[i][j] != 0) {
                    System.out.print(arr[i][j] + "\t");
                    //如果值等于零，用空格代替
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void yueBei() {
        System.out.println("请输入两个整数:");
        Scanner input1 = new Scanner(System.in);
        int a = input1.nextInt();
        int b = input1.nextInt();
        int u = a * b; //  用u来保存 a*b 的值
        while (b > 0) {
            if (a >= b && a % b != 0) {
                int t = b;
                b = a % b; // 利用了递归算法
                a = t;
            } else if (a % b == 0) {
                break; // a刚好是b的倍数
            } else {
                int s = a; // 当  a < b 时 ，a b的值交换
                a = b;
                b = s;
            }
        }
        System.out.println("两个数的最大公约数为:" + b);
        System.out.println("两个数的最小公倍数为:" + u / b);
    }

    public boolean iszhishu(int x) {
        for (int i = 2; i <= x / 2; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
class TT extends Test1{
    public static void main(String[] args) {
        Test1 tt = new TT();
        System.out.println(tt.kk);
        try {
            Date parse = DateFormat.getDateInstance(DateFormat.LONG).parse("2017年5月20日");
        }catch (Exception e){

        }
    }
}
