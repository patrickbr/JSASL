package com.patrickbr.saslcompiler.client.tokens;

public class TokenNum extends Token {
	private long num;

	public TokenNum(long num, int position, int line) {
		super(position,line);
		this.num=num;
	}

	public long getNum() {
		return num;
	}
}