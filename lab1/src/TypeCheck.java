package src;

/**
 * @author Bourbon
 * @date 2017/10/24
 * @description 判断字符类型
 */
public class TypeCheck {

	//关键字集合
	private final String[] keywords = {"abstract", "boolean", "break", "byte",
			"case", "catch", "char", "class", "continue", "default", "do",
			"double", "else", "extends", "final", "finally", "float", "for",
			"if", "implements", "import", "instanceof", "int", "interface",
			"long", "native", "new", "package", "private", "protected",
			"public", "return", "short", "static", "super", "switch",
			"synchronized", "this", "throw", "throws", "transient", "try",
			"void", "volatile", "while", "strictfp", "enum", "goto", "const", "assert"};
	private final char[] operators = {'+', '-', '*', '/', '=', '>', '<', '&', '|',
			'!'};
	private final char[] separators = {',', ';', '{', '}', '(', ')', '[', ']', '_',
			':', '.', '"', '\\'};

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
		for (int i = 0; i < operators.length; i++) {
			if (c == operators[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @description 判断是否是分界符
	 */
	public boolean isSeparator(char c) {
		for (int i = 0; i < separators.length; i++) {
			if (c == separators[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @description 判断是否为关键字
	 */
	public boolean isKeyword(String s) {
		for (int i = 0; i < keywords.length; i++) {
			if (s.equals(keywords[i])) {
				return true;
			}
		}
		return false;
	}
}