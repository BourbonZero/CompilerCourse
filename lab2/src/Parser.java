/**
 * @author Bourbon
 * @date 2017/11/8
 * @description
 */
public class Parser extends ParsingTable{

	private StringBuffer stringBuffer = new StringBuffer();

	public Parser(){
		FileIO.readFile(stringBuffer, "./input.txt");
		FileIO.clearFile();
		initialize();
		getTable();
	}

	/**
	 * @description 分析主方法
	 */
	public void parse(){

	}
}
