package com.rubick.muktobanglakeyboard;

import java.util.ArrayList;
public class PhoneticParser
{

	static ArrayList<Pattern> patternList;
	static ArrayList<String> engVowelList, bngVowelList;
	static char[] consonants = {'b','c','d','f','g','h',
								'j','k','l','m','n','p',
								'q','r','s','t','v','w',
								'x','y','z','B','C','D',
								'F','G','H','J','K','L',
								'M','N','P','Q','R','S',
								'T','V','W','X','Y','Z'};
	
	
	static char[] punctuations = {'!','@','#','%','&','*','(',')','-','_','+','=','\\','|','{','[','}',']','/','\"',';','<','>','?'};
	public static void initialize()
	{
		patternList = PatternLoader.loadPatterns();
		engVowelList = new ArrayList<String>();
		bngVowelList = new ArrayList<String>();
		
		engVowelList.add("a");		bngVowelList.add("আ");		
		engVowelList.add("e");		bngVowelList.add("এ");
		engVowelList.add("i");		bngVowelList.add("ই");
		engVowelList.add("o");		bngVowelList.add("অ");
		engVowelList.add("u");		bngVowelList.add("উ");
		engVowelList.add("A");		bngVowelList.add("আ");
		engVowelList.add("E");		bngVowelList.add("এ");
		engVowelList.add("I");		bngVowelList.add("ঈ");
		engVowelList.add("O");		bngVowelList.add("ও");
		engVowelList.add("U");		bngVowelList.add("ঊ");
		engVowelList.add("OU");		bngVowelList.add("ঔ");
		engVowelList.add("OI");		bngVowelList.add("ঐ");
		engVowelList.add("rri");	bngVowelList.add("ঋ");
			
		
	}




/*	public static void main(String[] args)
	{
		
		patternList = XMLLoader.loadXML("src//phonetic.xml");

		english.add("a"); bangla.add("আ");
		english.add("m"); bangla.add("ম");
		english.add("r"); bangla.add("র");
		english.add("ma"); bangla.add("মা");
		english.add("o"); bangla.add("অ");

		System.out.println(parseWord("shamim"));


	}
	
*/

	public static int match(String token)
	{
		for(int i = 0; i<patternList.size(); i++)
		{
			if(patternList.get(i).getEngPhrase().equals(token))
			return i;

		}

		return -1;

	}

	public static String parse(String engWord)
	{
		String bngWord = new String("");
		char[] tmp;
		int res, inc = 3;

		for(int i = 0; i<engWord.length(); i++)
		{

			if(engWord.length()-i<3) inc = engWord.length()-i;

			tmp = new char[inc];
			engWord.getChars(i, inc+i, tmp, 0);

			String a = new String(tmp);
			
			if(isPunctuation(engWord.charAt(i)))
			{
				bngWord += engWord.charAt(i);
				continue;
			}
			
			if(a.equals("`")) continue;
			
			if(a.equals("rr") && engWord.length()>i+2)
			{
				if(isConsonant(engWord.charAt(i+2)))
					bngWord += "র্";
				else
					bngWord += "রর";
				i++;
				
				continue;
				
			}
			
			if(engVowelList.contains(a))
			{
				if(i!=0)
				{
					if(a.equals("o") && isConsonant(engWord.charAt(i-1)))
						continue;
					
				}
				if(check(a,engWord,i))
				{
					bngWord += bngVowelList.get(engVowelList.indexOf(a));
					i += a.length()-1;
					continue;
				}
				
				
			}
			
			res = match(a);

			if(res==-1) res = match(a.toLowerCase());
			
			if(res!=-1)
			{
//				bngWord += bangla.get(res);
				
				if((a.equals("w") || a.equals("r") || a.equals("y")) && i!=0)
				{
					if(isConsonant(engWord.charAt(i-1)))
					{
						bngWord += patternList.get(res).getSecBngPhrase();
						continue;
					}
				}
				
				bngWord += patternList.get(res).getPriBngPhrase();
				i+=(inc-1);
			}
			else
			{
				tmp = new char[2];
				engWord.getChars(i, 2+i, tmp, 0);

				a = new String(tmp);
			
				if(isPunctuation(engWord.charAt(i)))
				{
					bngWord += engWord.charAt(i);
					continue;
				}
				
				if(a.equals("`")) continue;
				
				if(a.equals("rr") && engWord.length()>i+2)
				{
					if(isConsonant(engWord.charAt(i+2)))
						bngWord += "র্";
					else
						bngWord += "রর";
					i++;
					
					continue;
					
				}
				
				if(engVowelList.contains(a))
				{
					if(i!=0)
					{
						if(a.equals("o") && isConsonant(engWord.charAt(i-1)))
							continue;
						
					}
					
					if(check(a,engWord,i))
					{
						bngWord += bngVowelList.get(engVowelList.indexOf(a));
						i += a.length()-1;
						continue;
					}
					
					
				}
				
				res = match(a);
				
				if(res==-1) res = match(a.toLowerCase());

				if(res!=-1)
				{
//					bngWord += bangla.get(res);
					
					if((a.equals("w") || a.equals("r") || a.equals("y")) && i!=0)
					{
						if(isConsonant(engWord.charAt(i-1)))
						{
							bngWord += patternList.get(res).getSecBngPhrase();
							continue;
							
						}
					}
					
					bngWord += patternList.get(res).getPriBngPhrase();
					i++;
				}
				else
				{

					
					tmp = new char[1];
					engWord.getChars(i, 1+i, tmp, 0);

					a = new String(tmp);
				
					if(isPunctuation(engWord.charAt(i)))
					{
						bngWord += engWord.charAt(i);
						continue;
					}
					
					if(a.equals("`")) continue;
					
					if(engVowelList.contains(a))
					{
						if(i!=0)
						{
							if(a.equals("o") && isConsonant(engWord.charAt(i-1)))
								continue;
							
						}
						
						if(check(a,engWord,i))
						{
							bngWord += bngVowelList.get(engVowelList.indexOf(a));
							i += a.length()-1;
							continue;
						}
						
						
					}
					
					res = match(a);
					
					if(res==-1) res = match(a.toLowerCase());
	
					if(res!=-1)
					{
						//bngWord += bangla.get(res);
						
						if((a.equals("w") || a.equals("r") || a.equals("y")) && i!=0)
						{
							if(isConsonant(engWord.charAt(i-1)))
							{
								bngWord += patternList.get(res).getSecBngPhrase();
								continue;
								
							}
						}
						
						bngWord += patternList.get(res).getPriBngPhrase();
					}

				}
			}
		}


		

		return bngWord;

	}
	
	public static boolean isConsonant(char c)
	{
		for(int i = 0; i<consonants.length; i++)
			if(consonants[i] == c) return true;
		
		return false;
		
	}
	
	public static boolean check(String engPhrase, String engWord, int curIndex)
	{
		if(curIndex == 0) return true;
		
		if(engWord.charAt(curIndex-1) == '`' || !isConsonant(engWord.charAt(curIndex-1)))
		{
			return true;
			
		}
		
		//if(engWord.charAt(curIndex-1) == '`')
		
		return false;
		
		
	}
	
	public static boolean isPunctuation(char ch)
	{
		for(int i = 0; i<punctuations.length; i++)
		{
			if(punctuations[i]==ch) return true;
		}
		
		return false;
		
	}
	
	

}


