import java.util.BitSet;

/**
 * @Author: gq
 * @Date: 2020/7/24 16:15
 */
public class BloomFilter {

    public static final int DEFAULT_SIZE = 2<<24;

    public static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    public BitSet bitSet = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] hashFun= new SimpleHash[SEEDS.length];

    public BloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            hashFun[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    public void add(Object o){
        for (SimpleHash hash : hashFun){
            bitSet.set(hash.hash(o), true);
        }
    }

    public boolean contains(Object o){
        boolean isContains = true;
        for (SimpleHash hash : hashFun){
            isContains = isContains && bitSet.get(hash.hash(o));
        }
        return isContains;
    }


    public static class SimpleHash{
        private int capability;
        private int seed;

        public SimpleHash(int capability, int seed) {
            this.capability = capability;
            this.seed = seed;
        }

        public int hash(Object o){
            int h;
            return (o == null) ? 0 : Math.abs(seed * (capability - 1) & ((h = o.hashCode()) & h >>> 16));
        }
    }
}
