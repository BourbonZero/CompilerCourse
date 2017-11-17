import jdk.nashorn.internal.ir.Terminal;

import java.io.File;
import java.util.Stack;

/**
 * @author Bourbon
 * @date 2017/11/8
 * @description LL1文法分析
 */
public class Parser extends ParsingTable {

	//输入流
	private StringBuffer stringBuffer = new StringBuffer();

	public Parser() {
		FileIO.readFile(stringBuffer, "./input.txt");
		FileIO.clearFile();
		initialize();
		getTable();
	}

	/**
	 * @description 分析主方法
	 */
	public void parse() {
		FileIO.writeFile("Output derivations:");

		//初始化：栈，读头
		Stack<Character> stack = new Stack<>();
		stack.push('$');
		stack.push('S');
		int ptr = 0;//读头位置
		Production production = null;
		char input;

		//栈中非空时
		while (stack.peek() != '$') {
			input = stringBuffer.charAt(ptr);

			//获取匹配的产生式
			production = table[nonTerminals.indexOf(stack.peek())][terminals.indexOf(input)];

			if (production == null){
				FileIO.writeFile("Failure: 无法找到对应的产生式！");
			}

			//弹出栈顶非终结符
			stack.pop();
			String right = production.right;

			//将产生式右部压栈
			for (int i = right.length() - 1; i >= 0; i--) {
				if (right.charAt(i) != '`') {
					stack.push(right.charAt(i));
				}
			}

			//打印推导序列
			FileIO.writeFile(production.print());

			//判断栈顶是否和输入串匹配
			while (stack.peek() == stringBuffer.charAt(ptr)) {
				//匹配成功！
				if (stack.peek() == '$'){
					FileIO.writeFile("success!");
					return;
				}
				stack.pop();
				ptr++;
			}
		}

		//输入串有剩余
		FileIO.writeFile("Failure: 仍有未处理的输入字符！");
	}
}
