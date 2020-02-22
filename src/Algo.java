import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Algo {
	private static Library[] librarySet;
	private static int[] bookSco;
	private static int days;
	private static ArrayList<Library> solSet;
	private static HashSet<Integer> sentBooks = new HashSet<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		parse();
		solve2();
		out();
	}
	
	public static void out() throws IOException {
		StringBuilder line = new StringBuilder();
		File file = new File("output.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		line.append(solSet.size());
		writer.append(line.toString());
		writer.newLine();
		
		for(Library a:solSet) {
			line = new StringBuilder();
			line.append(a.getId());
			line.append(" ");
			line.append(a.sendSet.size());
			writer.write(line.toString());
			writer.newLine();
			line = new StringBuilder();
			for(int i:a.sendSet) {
				line.append(i);
				line.append(" ");
			}
			writer.write(line.toString());
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}
	
	public static void solve() {
		Arrays.sort(librarySet);
		ArrayList<Library> remove = new ArrayList<>();
		int dayLeft = days;
		solSet = new ArrayList<>();
		for(int i=0;i<librarySet.length;i++) {
			if(dayLeft-librarySet[i].getSut()>=0) {
				dayLeft-=librarySet[i].getSut();
				librarySet[i].setSendTime(dayLeft);
				
				solSet.add(librarySet[i]);
			}
			else break;
		}
		sentBooks = new HashSet<>();
		for(Library a:solSet) {
			a.initSend();
			int lim = a.getSendTime()*a.getBpd();
			for(int i =0; i<a.bookSet.size();i++) {
				int book = a.bookSet.get(i);
				if(!sentBooks.contains(book)){
					a.sendSet.add(book);
					sentBooks.add(book);
					lim--;
				}
				if(lim<=0) break;
			}
			if(a.sendSet.isEmpty()) remove.add(a);
		}
		for(Library b:remove) {
			solSet.remove(b);
		}
	}
	public static void solve2() {
		Arrays.sort(librarySet);
		int dayLeft = days;
		solSet = new ArrayList<>();
		for(int i=0;i<librarySet.length;i++) {
			if(dayLeft-librarySet[i].getSut()>=0) {
				dayLeft-=librarySet[i].getSut();
				librarySet[i].setSendTime(dayLeft);
				if(!sent(librarySet[i])) dayLeft+=librarySet[i].getSut();
				
			}
			else break;
		}
	}
	public static boolean sent(Library a) {
			a.initSend();
			int lim = a.getSendTime()*a.getBpd();
			for(int j =0; j<a.bookSet.size();j++) {
				int book = a.bookSet.get(j);
				if(!sentBooks.contains(book)){
					a.sendSet.add(book);
					sentBooks.add(book);
					lim--;
				}
				if(lim<=0) break;
			}
			if(!a.sendSet.isEmpty()) {
				solSet.add(a);
				return true;
			}
			return false;
	}
	
	public static void parse() throws IOException{
		Scanner sc1 = new Scanner(new File("input.txt"));
		Scanner scLine = new Scanner(sc1.nextLine());
		bookSco = new int[scLine.nextInt()];
		librarySet = new Library[scLine.nextInt()];
		days = scLine.nextInt();
		
		scLine.close();
		scLine = new Scanner(sc1.nextLine());
		
		for(int i=0;i<bookSco.length;i++) {
			bookSco[i] = scLine.nextInt();
		}
		scLine.close();
		
		for(int j=0;j<librarySet.length;j++) {
			scLine = new Scanner(sc1.nextLine());
			scLine.nextInt();
			librarySet[j] = new Library(j, scLine.nextInt(), scLine.nextInt());
			scLine.close();
			scLine = new Scanner(sc1.nextLine());
			while(scLine.hasNextInt()) {
				librarySet[j].bookSet.add(scLine.nextInt());
			}
			scLine.close();
		}	
	}

}
