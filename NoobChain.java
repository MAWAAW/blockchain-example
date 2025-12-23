package noobchain;
import java.util.*;
import com.google.gson.GsonBuilder;

public class NoobChain {

	private static final List<Block> blockchain = new ArrayList<>();
    private static final int difficulty = 5;

	public static void main(String[] args) throws Exception {	
		System.out.println("⛏️ Mining local blocks...");
		
		System.out.println("Trying to Mine block 1... ");
		addBlock(new Block("Hi im the first block", "0"));
		
		System.out.println("Trying to Mine block 2... ");
		addBlock(new Block("Yo im the second block", blockchain.get(blockchain.size()-1).hash));
		
		System.out.println("Trying to Mine block 3... ");
		addBlock(new Block("Hey im the third block", blockchain.get(blockchain.size()-1).hash));	
		
		System.out.println("\nBlockchain valid? " + isChainValid());
		
		System.out.println("\nThe blockchain: " + StringUtil.getJson(blockchain));
	}
	
	private static Boolean isChainValid() {
		String hashTarget = StringUtil.getDifficultyString(difficulty);

        for (int i = 1; i < blockchain.size(); i++) {
            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);

            if (!current.getHash().equals(current.calculateHash())) {
                System.out.println("❌ Current hashes not equal");
                return false;
            }
            if (!previous.getHash().equals(current.getPreviousHash())) {
                System.out.println("❌ Previous hashes not equal");
                return false;
            }
            if (!current.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("❌ This block hasn't been mined properly");
                return false;
            }
        }
        return true;
	}
	
	private static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
