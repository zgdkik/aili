// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MySecurity.java

package org.hbhk.rss.weixinapi.server.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MySecurity
{

	public static final String SHA_1 = "SHA-1";
	public static final String MD5 = "MD5";

	public MySecurity()
	{
	}

	public String encode(String strSrc, String encodeType)
	{
		MessageDigest md = null;
		String strDes = null;
		byte bt[] = strSrc.getBytes();
		try
		{
			if (encodeType == null || "".equals(encodeType))
				encodeType = "MD5";
			md = MessageDigest.getInstance(encodeType);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			return strSrc;
		}
		return strDes;
	}

	public String bytes2Hex(byte bts[])
	{
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++)
		{
			tmp = Integer.toHexString(bts[i] & 0xff);
			if (tmp.length() == 1)
				des = (new StringBuilder(String.valueOf(des))).append("0").toString();
			des = (new StringBuilder(String.valueOf(des))).append(tmp).toString();
		}

		return des;
	}

	public static void main(String args[])
	{
		MySecurity te = new MySecurity();
		String strSrc = "���Լ��ܺ���";
		System.out.println((new StringBuilder("Source String:")).append(strSrc).toString());
		System.out.println("Encrypted String:");
		System.out.println((new StringBuilder("Use MD5:")).append(te.encode(strSrc, null)).toString());
		System.out.println((new StringBuilder("Use MD5:")).append(te.encode(strSrc, "MD5")).toString());
		System.out.println((new StringBuilder("Use SHA:")).append(te.encode(strSrc, "SHA-1")).toString());
		System.out.println((new StringBuilder("Use SHA-256:")).append(te.encode(strSrc, "SHA-256")).toString());
	}
}
