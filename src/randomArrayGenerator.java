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
            a[i] = (int) (Math.random() * 100);
            while(dict.contains(a[i])){
                a[i] = (int) (Math.random() * 100);
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



}
