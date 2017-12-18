package task4;


import java.util.concurrent.RecursiveTask;


public class SumOfN extends RecursiveTask<Long> {

    private int from;
    private int to;
    private int[] arr;

    public SumOfN(int[] arr,int form, int to) {
        this.from = form;
        this.to = to;
        this.arr = arr;
    }

    @Override
    public Long compute() {


        if((to - from) < 20){
            long localSum = 0;
            for (int i = from; i < to; i++) {
               localSum += (long)arr[i];
            }
            return localSum;
        }else{
            int mid = from + (to - from) / 2;
            SumOfN fHalf = new SumOfN(arr,from,mid);
            fHalf.fork();
            SumOfN sHalf = new SumOfN(arr,mid + 1,to);
            sHalf.fork();
            return fHalf.join() + sHalf.join();
        }
    }
}
