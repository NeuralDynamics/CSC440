package app;

import java.util.*;

public class MemberManager {
	private String filename;	//what is its purpose?
	private List<Member> memList;
	private IReader reader;
	private IWriter writer;
	
	public MemberManager() {
		reader = new MemMgrFileReader<Member>();
		writer = new MemMgrFileWriter<Member>();
		load();
	}
	
	public void addMember(Member m) {
		memList.add(m);
	}
	
	public Member findMember(long memNum) {
		for (Member mbr: memList) {
			if (mbr.getMemberNumber() == memNum) {
				return mbr;
			}
		}
		return null;
	}
	
	public void save() {
		writer.writeData(memList);
	}
	
	public void load() {
		memList = reader.readData();
	}
	

}
