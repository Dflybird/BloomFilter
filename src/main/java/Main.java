/**
 * @Author: gq
 * @Date: 2020/7/24 16:28
 */
public class Main {
    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter();
        String mark = "assg";
        int range = 100000;
        for (int i = 0; i < 100000; i++) {
            bloomFilter.add(mark+i);
        }
        int start = 90000;
        int end = 110000;
        int count = 0;
        long startTime = System.nanoTime();
        for (int i = start; i < end; i++) {
            count += bloomFilter.contains(mark+i)?1:0;
        }
        long endTime = System.nanoTime();
        System.out.println("time: "+((endTime-startTime)/1000000)+"ms\n"+"contains: " + count + " , not contains: "+ (end-start-count) + " real not contains: " + (end - range));
        System.out.println("missing: " + ((double)(end-start-count)/(end-start)));
        for (int i = 0; i < bloomFilter.bitSet.size(); i++) {
            System.out.println(bloomFilter.bitSet.get(i) + " i: "+i);
        }
    }
}
