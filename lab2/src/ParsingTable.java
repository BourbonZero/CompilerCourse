import java.util.ArrayList;

/**
 * @author Bourbon
 * @date 2017/11/8
 * @description
 */
public class ParsingTable {

	private ArrayList<Production> productions = new ArrayList<Production>();

	/**
	 * @description 初始化CFG
	 */
	public void initialize(){
		productions.add(new Production("S-","S"));
		FileIO.getCFG(productions);
	}

	/**
	 * @description 生成LR（1）分析表
	 */
	public void getTable(){

	}
}
