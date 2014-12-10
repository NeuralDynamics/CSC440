package app;

import java.util.*;

public class MemberManager {
	private String filename;	//what is its purpose?
	private List<Member> memList;
	private IReader reader;
	private IWriter writer;
	
	public MemberManager() {
		//Since the reader and writer are specific for a data model are generics really necessary for them?
		//Also reader doesn't even seem to use the generic typing in its main method implementation
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
		//Reader returns an improper List<String> instead of List<Member>
		writer.writeData(memList);
	}
	
	public void load() {
		memList = reader.readData();
	}
	

}
