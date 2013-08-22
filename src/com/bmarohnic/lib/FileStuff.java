package com.bmarohnic.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.util.Log;

public class FileStuff {
	@SuppressWarnings("resource")
	public static Boolean storeStringFile(Context context, String filename, String content, Boolean external){
		try{
			File file;
			FileOutputStream fos;
			
			if(external){
				file = new File(context.getExternalFilesDir(null), filename);
				fos = new FileOutputStream(file);
			} else {
				fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			}
			fos.write(content.getBytes());
			fos.close();
		} catch (IOException e) {
			Log.e("Write Error", filename);
		}
		
		return true;
	}
	
	@SuppressWarnings("resource")
	public static Boolean storeObjectFile(Context context, String filename, Object content, Boolean external){
		try{
			File file;
			FileOutputStream fos;
			ObjectOutputStream oos;
			
			if(external){
				file = new File(context.getExternalFilesDir(null), filename);
				fos = new FileOutputStream(file);
			} else {
				fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			}
			oos = new ObjectOutputStream(fos);
			oos.writeObject(content);
			oos.close();
			fos.close();
		} catch (IOException e) {
			Log.e("Write Error", filename);
		}
		return true;
	}
}
