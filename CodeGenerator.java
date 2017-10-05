import java.io.File;
import java.util.Scanner;

public class CodeGenerator {
	
	static String subjectName = "";
	static String subjectCode = "";
	static String topicName = "";
	static String chapterName = "";
	static String unitName = "";
	static String moduleNo = "";
	static String sem = ""; 
	static int lineTraker = 0;
	static boolean flag = false;
	
	public static void main(String[] args){

		Scanner scaned = readingFile();
		
		while(scaned.hasNextLine()){
			
			Scanner presentLine = new Scanner(scaned.nextLine());
		
			if(lineTraker%11 == 0){
				
				String nextLine = presentLine.nextLine();
				subjectCode = nextLine.substring(nextLine.length()-7);
				subjectName = nextLine.substring(0, nextLine.length()-7);
				flag = true;
			}else if(flag){
				
				String unit = presentLine.nextLine();
				moduleNo = unit.substring(0, 8);
				unitName = unit.substring(9,unit.length()-1);
				flag = false;
			
			}else{
				
				String temp = "";
				while(presentLine.hasNext()){
					
					String word = presentLine.next();
					temp = temp + " " + word;
					char chr = temp.charAt(temp.length()-1);
					switch(chr){
					
					case ':':
						chapterName = temp;
						break;
						
					case ',':
						topicName = temp;
						String full = subjectCode + " : " + subjectName + " - " + moduleNo + " - "+ unitName + " - " + chapterName + " - " +topicName;
						System.out.println(full.trim());
						temp = "";
						break;
					case'.':
						temp.replace('.', ',');
						break;
					case'(':
						do{
							temp = temp + " " + presentLine.next(); 
						}while(temp.charAt(temp.length()-1) !=')');
						break;
					default:
					
					}
					
				}
				flag = true;
			}
				
				/*
				String nextWord = nextLine.next();
				String next = scaned.next();
				System.out.println(next);
				if(next.isEmpty()){
					
				}*/
				lineTraker++;
			}
		}
		
		//System.out.println("done");
	
	
	public static Scanner readingFile(){
		
		Scanner scanedFile = new Scanner("");
		try{
			
			scanedFile = new Scanner(new File("rowData.txt"));
			
		}catch(Exception e) {
			System.out.println("File Not Found");
		}
		return scanedFile;
	}

}
