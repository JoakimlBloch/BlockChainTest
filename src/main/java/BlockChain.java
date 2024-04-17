import com.google.gson.stream.JsonToken;
import model.Block;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {

    // mining difficulty - low difficulty like 1 or 2 can be solved nearly instantly - go for 4–6 for testing
    public static int difficulty = 5;

    // list capable of containing blockchains
    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {

        // add our blocks to the blockchain ArrayList
        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        // checking if our blockchain is valid
        System.out.println("\nBlockchain is valid: " + isChainValid());

        // printing of the blockchain in json format
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }


    public static Boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // loop through blockchain to check each individual blocks hash
        for (int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            // compare registered hash and current calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }

            // compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            // check if hash has been solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}