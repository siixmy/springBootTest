package com.example.demo;

/**
 * Created by 19484 on 2023/8/28.
 */
public class Test {
  private int a=1;
    public Test(){
        System.out.println("父类无参构造");
    }
    public Test(int a){
        System.out.println("父类有参构造"+a);
    }
    public Test(int a,int b){
        System.out.println("父类有参构造"+a+b);
    }
}
 class TestSon extends  Test{
   public TestSon(){
   }
     public TestSon( int a){
//         super(1,2);
         System.out.println("子类无参构造");
     }
     public static void main(String[] args) {
//         System.out.println("a"+'q'+1+2.0f+2.22);
//         String a="()";
//         char c = a.charAt(0);
//         int i = a.indexOf('(');
//         System.out.println(c);
//         System.out.println('a'+i);
         new TestSon(1);

     }
}
