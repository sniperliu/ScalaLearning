package com.citi.gta;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class KeyTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        
//        TreeMap<SortableKey, Integer> map = new TreeMap<SortableKey, Integer>();
//        
//        for (int i = 0; i < 2; i++) {
//            SortableKey key = new SortableKey("OT1.5", 10);
//            if (map.get(key) == null) {
//                map.put(key, 10);
//            } else {
//                map.put(key, map.get(key) + 10);
//            }
//        }
//
//        Iterator<SortableKey> itr = map.keySet().iterator();
//        while (itr.hasNext()) {
//            KeyTest.SortableKey sortableKey = (KeyTest.SortableKey) itr.next();
//            System.out.println("Key: " + sortableKey + ", Value: " + map.get(sortableKey));
//        }
        //1111 1111 1111 1111 1111 1111 1110 0110
//        char i = '\uffe6';
//        char c = '\uC3A1';        
//        byte[] bs = new byte[] {(byte) 0x43, (byte) 0xc3, (byte) 0xb3, (byte) 0x64, (byte) 0x69};
//        String test = new String(bs, "ISO-8859-1");
//        System.out.println(test);
//        byte[] bsISO = test.getBytes("UTF-8");
//        for (byte b : bsISO) {
//            System.out.println(Integer.toHexString(b));
//        }
//        System.out.println(Boolean.TRUE.toString());
//        Charset charset = Charset.forName("UTF-8");
//        ByteBuffer bb = charset.encode(new String(bs, "UTF-8"));
//        for (byte b : bb.array()) {
//            System.out.println(Integer.toHexString(b));
//        }
        
//        byte[] bs1 = new byte[] {(byte) 0x43, (byte) 0xc3, (byte) 0x83, (byte) 0xc2, (byte) 0xb3, (byte) 0x64, (byte) 0x69};
//        String test1 = new String(bs1, "UTF-8");
//        byte[] bsISO1 = test.getBytes("ISO-8859-1");
//        for (byte b : bsISO1) {
//            System.out.println(Integer.toHexString(b));
//        }
        
        MathContext mathCxt = new MathContext(2, RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP));
        System.out.println(BigDecimal.valueOf(1.01d).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(0.25d).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(0.5d).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(0.75d).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue());
    }

    private static final class SortableKey implements Comparable<SortableKey> {
        public String name;
        public int sortOrder;

        public SortableKey(String name, int sortOrder) {
            this.name = name;
            this.sortOrder = sortOrder;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SortableKey))
                return false;
            SortableKey sk = (SortableKey) obj;
            return (this.name.equals(sk.name))
                    && (this.sortOrder == sk.sortOrder);
        }

        public int hashCode() {
            return this.sortOrder;
        }

        public int compareTo(SortableKey sk) {
            if (equals(sk))
                return 0;
            if (this.sortOrder > sk.sortOrder) {
                return 1;
            }
            if (this.sortOrder < sk.sortOrder) {
                return -1;
            }
            return -1;
        }
    }

}
