package com.hk.test.batch;

import java.io.File;

public class batchMain {

	public static void remove(){
		
		System.out.println("batch start");
		
		File path_tmp = new File(System.getProperty("catalina.base") + "/uploads_tmp/");
		
		File[] fileNameList = path_tmp.listFiles();
		
		for( File file : fileNameList){
			System.out.println("delete file : " + file.getAbsolutePath());
			System.out.println("last modified : " + file.lastModified());
			file.delete();
		}
		
		System.out.println("batch end");
		
		
	}

}
