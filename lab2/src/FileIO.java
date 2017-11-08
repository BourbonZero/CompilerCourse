import java.io.*;
import java.util.ArrayList;

/**
 * @author Bourbon
 * @date 2017/11/8
 * @description 文件输入输出处理
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
			bufferedReader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @description 清空output.txt
	 */
	public static boolean clearFile() {
		try {
			File file = new File("./output.txt");
			if (file.exists()) {
			} else {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bufferedWriter.write("");
			bufferedWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @description 将结果写回output.txt
	 */
	public static boolean writeFile(String outputString) {
		try {
			File file = new File("./output.txt");
			if (file.exists()) {
			} else {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));
			bufferedWriter.write(outputString);
			bufferedWriter.newLine();
			bufferedWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @description 读入CFG
	 */
	public static boolean getCFG(ArrayList<Production> productions){
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./cfg.txt")));
			String line = null;
			while((line = bufferedReader.readLine())!=null){
				String[] temp = line.split(",");
				Production production = new Production(temp[0],temp[1]);
				productions.add(production);
			}
			bufferedReader.close();
			return true;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
