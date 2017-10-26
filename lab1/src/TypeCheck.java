/**
 * @author Bourbon
 * @date 2017/10/24
 * @description 判断字符类型
 */
public class TypeCheck {

	//关键字集合
	private final String[] keywords = {};
	private final char[] operators ={};

	/**
	 * @description 判断是否是数字
	 */
	public boolean isNum(char c) {
		return Character.isDigit(c);
	}

	/**
	 * @description 判断是否是字母
	 */
	public boolean isLetter(char c) {
		return Character.isLetter(c);
	}

	/**
	 * @description 判断是否是运算符
	 */
	public boolean isOperator(char c) {
		return false;
	}

	/**
	 * @description 判断是否为关键字
	 */
	public boolean isKeyword(String s) {

		return false;
	}
}