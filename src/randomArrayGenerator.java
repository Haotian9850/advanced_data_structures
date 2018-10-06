import java.util.HashMap;
import java.util.HashSet;

public class randomArrayGenerator {
    public int[] result;
    public randomArrayGenerator(){
        this.result = new int[5];   //default: filled with 0
    }

    public randomArrayGenerator(int len){
        this.result = new int[len];
        HashSet<Integer> dict = new HashSet<Integer>();
        for(int i = 0; i < len; i ++){
            this.result[i] = (int) (Math.random() * 100);
            while(dict.contains(result[i])){
                result[i] = (int) (Math.random() * 100);
            }
            dict.add(result[i]);
        }
    }

    public int[] generateRandomArray(int len){
        int[] a = new int[len];
        HashSet<Integer> dict = new HashSet<Integer>();
        for (int i = 0; i < len; i ++){
            a[i] = (int) (Math.random() * 1000);
            while(dict.contains(a[i])){
                a[i] = (int) (Math.random() * 1000);
            }
            dict.add(a[i]);
        }
        return a;
    }

    public int[] generateRandomArray(int len, int uppLim){
        int[] a = new int[len];
        HashSet<Integer> dict = new HashSet<Integer>();
        for (int i = 0; i < len; i ++){
            a[i] = (int) (Math.random() * uppLim);
            while(dict.contains(a[i])){
                a[i] = (int) (Math.random() * uppLim);
            }
            dict.add(a[i]);
        }
        return a;
    }

    public void generateRandomArrayAndPrint(int len){
        int[] a = new int[len];
        HashSet<Integer> dict = new HashSet<Integer>();
        for (int i = 0; i < len; i ++){
            a[i] = (int) (Math.random() * 1000);
            while(dict.contains(a[i])){
                a[i] = (int) (Math.random() * 1000);
            }
            dict.add(a[i]);
        }
        printArray(a);
    }

    public void printArray(int[] a){
        System.out.println();   //new line separator
        System.out.print("{");
        for(int i = 0; i < a.length - 1; i ++){
            System.out.print(a[i] + ", ");
        }
        System.out.print(a[a.length - 1] + "}");
        System.out.println();   //new line separator
    }



}
