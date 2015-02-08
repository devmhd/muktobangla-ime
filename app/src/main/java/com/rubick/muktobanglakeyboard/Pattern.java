package com.rubick.muktobanglakeyboard;


public class Pattern {

	String engPhrase;
	String priBngPhrase;
	String secBngPhrase;
	
	public Pattern()
	{
		engPhrase = null;
		priBngPhrase = null;
		secBngPhrase = null;
	}
	
	public Pattern(String eng, String bng)
	{
		engPhrase = eng;
		priBngPhrase = bng;
	}
	
	public Pattern(String eng, String bng1, String bng2)
	{
		engPhrase = eng;
		priBngPhrase = bng1;
		secBngPhrase = bng2;
	}
	
	
	public String getEngPhrase() {
		return engPhrase;
	}
	public void setEngPhrase(String engPhrase) {
		this.engPhrase = engPhrase;
	}
	public String getPriBngPhrase() {
		return priBngPhrase;
	}
	public void setPriBngPhrase(String bngPhrase) {
		this.priBngPhrase = bngPhrase;
	}

	public String getSecBngPhrase() {
		return secBngPhrase;
	}

	public void setSecBngPhrase(String secBngPhrase) {
		this.secBngPhrase = secBngPhrase;
	}
	
	
	
}
