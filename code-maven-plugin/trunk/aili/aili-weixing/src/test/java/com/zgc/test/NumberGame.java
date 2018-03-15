package com.zgc.test;
import java.util.Random;

public class NumberGame {

	/**
	 *新建一个随机数产生器，然后生成一个1到100之间的整数
	*/
	
	 public   static String GuessNumber(int param,String inputContent) {
             String outContent="";
	        Random rd = new Random();
	        System.out.println("猜数字！");
	        boolean isnotNum2 = false;
	        int arean = -1;
	        while (true)
	        {
	            if (isnotNum2)
	            {
	            	outContent="输入的内容错误，请重新输入数字的范围:  ";
	            }
	            else
	            {
	            	outContent="请输入猜测范围，内容为大于1的整数 ：";
	            }
	            try
	            {
	                arean = Integer.valueOf(inputContent);
	                if (arean >= 0)
	                {
	                    break;
	                }
	                else
	                {
	                    throw new Exception();
	                }
	            }
	            catch (Exception e)
	            {
	                isnotNum2 = true;
	            }
	        }

	        int guest = rd.nextInt(arean);
	        boolean flag = true;

	        boolean isNotNum1 = false;
	        int mnn = -1;

	        while (true)
	        {
	            if (isNotNum1)
	            {
	            	outContent="输入错误，请重新输入猜测次数 :";
	            }
	            else
	            {
	            	outContent="请输入猜测次数 ：";
	            }
	            
	            try
	            {
	                mnn = Integer.valueOf(inputContent);
	                if (mnn >= 0)
	                {
	                    break;
	                }
	                else
	                {
	                    throw new Exception();
	                }
	            }
	            catch (Exception e)
	            {
	                isNotNum1 = false;
	            }
	        }

	        int j = mnn;
	        int m = mnn;

	        for (int i = 1; i <= m; i++)
	        {
	            int leave = --j;

	            if (leave == 0)
	            {
	            	outContent="最后一次输入 ：";
	            }
	            else
	            {
	            	outContent="第" + i + "次输入，" + "还剩" + leave + "次机会";
	            }
	            boolean isnotNum = false;
	            int in = -1;
	            String n = "";
	            while (true)
	            {
	                if (isnotNum)
	                {
	                	outContent="输入内容错误，请从新输入：";
	                }
	                else
	                {
	                	outContent="请输入 ：";
	                }

	                
	                try
	                {
	                    in = Integer.valueOf(inputContent);
	                    if (in >= 0)
	                    {
	                        break;
	                    }
	                    else
	                    {
	                        throw new Exception();
	                    }
	                }
	                catch (Exception e)
	                {
	                    isnotNum = true;
	                }

	            }
	            if (in > guest)
	            {
	            	outContent="输入的值" + n + "过大.";
	             //system.out.println();
	            }
	            else if (in == guest)
	            {
	                flag = false;
	                outContent="正确!!\n";
	                outContent="输入次数 ：" + i;
	                break;
	            }
	            else
	            {
	            	outContent="输入的值" + n + "过小.";
	               // System.out.println();
	            }
	        }
	        if (flag)
	        {
	        	outContent="失败，值应该为 ：" + guest;
	        }
	        return outContent;
	    }
	 }
	 
	
