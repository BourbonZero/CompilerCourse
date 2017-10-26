package src;

/**
 * @author Bourbon
 * @date 2017/10/26
 * @description 词法分析类
 */
public class Analyzer extends TypeCheck {

	private StringBuffer stringBuffer = new StringBuffer();
	private int ptr = 0;//指针，指向下一位尚未读入的字符
	private char current;//当前读入的字符

	/**
	 * @description 构造器
	 * 初始化缓冲区，清空output文件
	 */
	public Analyzer(String inputPath) {
		FileIO.readFile(stringBuffer, inputPath);
		FileIO.clearFile();
	}

	/**
	 * @description 词法分析方法
	 * 输入文件input.txt
	 * 输出文件output.txt
	 */
	public void analyse() {
		while (ptr < stringBuffer.length()) {
			//初始化token，并读入一个字符
			String token = "";
			getOne();
			while (Character.isWhitespace(current)) {
				getOne();
			}//过滤掉空白符

			//字母开头的标识符（关键字）
			if (isLetter(current)) {
				//读入完整字符串
				while (isLetter(current) || isNum(current) || current == '_') {
					token += current;
					getOne();
				}
				//将指针归位
				resetPtr();
				//区分关键字
				if (isKeyword(token)) {
					generate("keyword", token);
				} else {
					generate("id", token);
				}
			}

			//数字开头的数字串
			else if (isNum(current)) {
				//读入完整的数字串
				while (isNum(current)) {
					token += current;
					getOne();
				}
				//此时current中为数字串的下一位字符
				//所以为字母或非运算符是非法的
				if (isLetter(current)) {
					generate("error", token);
					break;
				}
				//如果不是，则指针归位，正常生成
				else {
					resetPtr();
					generate("num", token);
				}
			}

			//运算符
			else if (isOperator(current)) {
				//识别可能的注释（依然有可能是除号）
				if (current == '/') {
					token += current;
					getOne();
					//段落注释/* */
					if (current == '*'){
						token += current;
						while(true){
							getOne();
							token += current;
							if(current=='*'){
								getOne();
								token += current;
								if(current=='/'){
									generate("doc",token);
									break;
								}
							}
						}
					}
					//整行注释//
					else if (current == '/'){
						while(current!=10){
							getOne();
						}
						//结束时current中为换行符
					}
					//非注释，单字符'/'
					else{
						resetPtr();
						generate("operator", token);
					}
				}else{
					generate("operator", current+"");
				}
			}

			//分界符
			else if (isSeparator(current)) {
				generate("separator", current+"");
			}
			//无法识别的字符，报错并停止分析
			else {
				generate("error", current + "");
			}
		}
	}

	/**
	 * @description 从stringBuffer中读入ptr指向的字符到current，并将ptr向后移一位
	 */
	private void getOne() {
		if (ptr<stringBuffer.length()){
			current = stringBuffer.charAt(ptr);
			ptr++;
		}
		else{
			current = ' ';
		}
	}

	/**
	 * @description 重置指针至前一位，并将current清空
	 */
	private void resetPtr(){
		ptr--;
		current =' ';
	}

	/**
	 * @description 生成对应的结果对并输出写回
	 * (type,target)
	 * e.g. (keyword,class)
	 * e.g. (operator,+)
	 */
	private void generate(String type, String target) {
		String temp = "(" + type + "," + target + ")";
		System.out.println(temp);
		FileIO.writeFile(temp);
	}
}
