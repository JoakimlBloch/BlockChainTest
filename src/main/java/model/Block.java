package model;

import util.StringUtil;

import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; // data will be a simple message
    private long timeStamp; // as number of milliseconds since 1/1/1970
    private int nonce;

    // constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // calculate a specific blocks hash (digital footprint / information)
    public String calculateHash() {
        return
            StringUtil.applySha256(previousHash + timeStamp + nonce + data);
    }

    // takes int called difficulty - number of 0’s they must solve for - low difficulty like 1 or 2 can be solved nearly instantly - go for 4–6 for testing.
    public void mineBlock(int difficulty) {

        String target = new String(new char[difficulty]).replace('\0', '0'); // create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! : " + hash);
    }

}
