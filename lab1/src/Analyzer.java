/**
 * @author Bourbon
 * @date 2017/10/26
 * @description 词法分析类
 */
public class Analyzer {

	private StringBuffer stringBuffer = new StringBuffer();

	public Analyzer(String inputPath){
		FileIO.readFile(stringBuffer, inputPath);
		FileIO.clearFile();
	}

	/**
	 * @description 词法分析方法
	 * 输入文件input.txt
	 * 输出文件output.txt
	 */
	public void analyse(){
		String token = "";

	}

	/**
	 * @description 生成对应的结果对并输出写回
	 * (type,target)
	 * e.g. (keyword,class)
	 * e.g. (operator,+)
	 */
	private void generateAndWrite(String type,String target){
		String temp = "(" + type + "," + target + ")";
		System.out.println(temp);
		FileIO.writeFile(temp);
	}
}
