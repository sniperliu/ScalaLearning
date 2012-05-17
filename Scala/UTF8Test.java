package com.citi.gta;


public class UTF8Test {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // This is correct encoded UTF-8 with bytes
        // 43,c3,b3,64,69
        byte[] bs = new byte[] {(byte) 0x43, (byte) 0xc3, (byte) 0xb3, (byte) 0x64, (byte) 0x69};
        // Encode with UTF-8
        String test1 = new String(bs, "UTF-8");
        System.out.println(test1);
        
        // Simulate the double encoding
        // Encode with ISO
        String test = new String(bs, "ISO-8859-1");
        System.out.println(test);
        
        // This simulate store in database, and double encoding
        byte[] bsISO = test.getBytes("UTF-8");
        for (byte b : bsISO) {
            String hexb = Integer.toHexString(b);
            System.out.print(hexb.substring(hexb.length() - 2, hexb.length()));
            System.out.print(",");
        }
        System.out.println();
    }

}
