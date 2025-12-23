package noobchain;

import java.util.Date;

public class Block {
	
	private String hash;
	private String previousHash; 
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;

	public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data
        );
    }
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("✅ Block Mined: " + hash);
	}

    // Getters
    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public String getData() { return data; }
    public long getTimeStamp() { return timeStamp; }
    public int getNonce() { return nonce; }
}
