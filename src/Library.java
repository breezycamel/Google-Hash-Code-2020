import java.util.ArrayList;

public class Library implements Comparable<Library> {
	private int id;
	private int sut;
	private int bpd;
	private int sendTime;
	private float eff;
	public ArrayList<Integer> sendSet;
	public ArrayList<Integer> bookSet;
	public int getSendTime() {
		return sendTime;
	}

	public void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

	public void initSend() {
		sendSet = new ArrayList<>();
	}
	
	public Library(int id, int sut, int bpd) {
		this.id = id;
		this.sut = sut;
		this.bpd = bpd;
		eff=bpd/sut; 
		bookSet = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSut() {
		return sut;
	}

	public void setSut(int sut) {
		this.sut = sut;
	}

	public int getBpd() {
		return bpd;
	}

	public void setBpd(int bpd) {
		this.bpd = bpd;
	}
	
	public float getEff() {
		return eff;
	}


	@Override
	public int compareTo(Library o) {
		return (int) ((o.getEff()-this.eff)*1000);
	}
	
}
