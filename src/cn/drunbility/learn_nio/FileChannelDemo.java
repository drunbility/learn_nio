package cn.drunbility.learn_nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;



public class FileChannelDemo {
		public static void readChannel() throws IOException{
			RandomAccessFile aFile=new RandomAccessFile("nio-data.txt", "rw");
			FileChannel inChannel=aFile.getChannel();//获得通道
			ByteBuffer buf=ByteBuffer.allocate(48); //分配缓冲区大小
			int bytesRead=inChannel.read(buf);//关键一步，从通道中读入数据到缓冲区
			while (bytesRead!=-1) {
				System.out.println("read:"+bytesRead);
				buf.flip();//准备从缓冲区取数据
//				buf.put("insert".getBytes());
				while (buf.hasRemaining()) {
					System.out.println((char)buf.get());//取数据
				}
				buf.clear();//通道中还有更多的数据，先清空缓冲区
				bytesRead=inChannel.read(buf);//重复
			}
			aFile.close();//关闭通道
		}
}
