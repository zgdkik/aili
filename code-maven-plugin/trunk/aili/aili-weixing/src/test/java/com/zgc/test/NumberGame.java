package com.zgc.test;
import java.util.Random;

public class NumberGame {

	/**
	 *�½�һ���������������Ȼ������һ��1��100֮�������
	*/
	
	 public   static String GuessNumber(int param,String inputContent) {
             String outContent="";
	        Random rd = new Random();
	        System.out.println("�����֣�");
	        boolean isnotNum2 = false;
	        int arean = -1;
	        while (true)
	        {
	            if (isnotNum2)
	            {
	            	outContent="��������ݴ����������������ֵķ�Χ:  ";
	            }
	            else
	            {
	            	outContent="������²ⷶΧ������Ϊ����1������ ��";
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
	            	outContent="�����������������²���� :";
	            }
	            else
	            {
	            	outContent="������²���� ��";
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
	            	outContent="���һ������ ��";
	            }
	            else
	            {
	            	outContent="��" + i + "�����룬" + "��ʣ" + leave + "�λ���";
	            }
	            boolean isnotNum = false;
	            int in = -1;
	            String n = "";
	            while (true)
	            {
	                if (isnotNum)
	                {
	                	outContent="�������ݴ�����������룺";
	                }
	                else
	                {
	                	outContent="������ ��";
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
	            	outContent="�����ֵ" + n + "����.";
	             //system.out.println();
	            }
	            else if (in == guest)
	            {
	                flag = false;
	                outContent="��ȷ!!\n";
	                outContent="������� ��" + i;
	                break;
	            }
	            else
	            {
	            	outContent="�����ֵ" + n + "��С.";
	               // System.out.println();
	            }
	        }
	        if (flag)
	        {
	        	outContent="ʧ�ܣ�ֵӦ��Ϊ ��" + guest;
	        }
	        return outContent;
	    }
	 }
	 
	
