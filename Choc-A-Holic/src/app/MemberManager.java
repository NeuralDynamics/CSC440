package app;

import java.util.*;

public class MemberManager {
	private String filename = "MemMgrFile.txt";	//what is its purpose?
	private List<Member> memList;
	private IReader<List<Member>> reader;
	private IWriter<List<Member>> writer;
	
	public MemberManager() {
		//Since the reader and writer are specific for a data model are generics really necessary for them?
		//Also reader doesn't even seem to use the generic typing in its main method implementation
		reader = new FileReader<List<Member>>(filename);
		writer = new FileWriter<List<Member>>(filename);
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
		//Writer unsafely clears out list by calling remove() method on list for every item!
		writer.writeData(memList);
	}
	
	public void load() {
		//Reader returns an improper List<String> instead of List<Member>
		memList =  reader.readData();
	}
	

}
