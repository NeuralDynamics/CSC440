package app;

import java.util.*;

public class MemberManager {
	private static String filename = "MemMgrFile.txt";	//what is its purpose?
	private List<Member> memList;
	private IReader<List<Member>> reader;
	private IWriter<List<Member>> writer;
	
	public MemberManager() {
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
		//Saves member list to file
		writer.writeData(memList);
	}
	
	public void load() {
		//Tries to read member list from file, otherwise creates a new list
		memList = reader.readData();
		if (memList == null) memList = new ArrayList<Member>();
	}
	

}
