package src;

import java.io.*;

/**
 * @author Bourbon
 * @date 2017/10/26
 * @description 负责文件的输入输出
 */
public class FileIO {

	/**
	 * @description 从文件路径读入输入文件到缓冲区
	 * 读入成功返回true，否则返回false
	 */
	public static boolean readFile(StringBuffer buffer, String filePath) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String inputString = null;
			while ((inputString = bufferedReader.readLine()) != null) {
				buffer.append(inputString);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @description 清空output.txt
	 */
	public static void clearFile(){
		try {
			File file = new File("./output.txt");
			if (file.exists()) {
			} else {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bufferedWriter.write("");
			bufferedWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}

	}

	/**
	 * @description 将结果写回output.txt
	 */
	public static void writeFile(String outputString) {
		try {
			File file = new File("./output.txt");
			if (file.exists()) {
			} else {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
			bufferedWriter.write(outputString);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
