import javax.print.DocFlavor;
import java.util.*;

/**
 * @author Bourbon
 * @date 2017/11/8
 * @description
 */
public class ParsingTable {

	private ArrayList<Production> productions = new ArrayList<Production>();
	ArrayList<Character> terminals = new ArrayList<Character>();
	ArrayList<Character> nonTerminals = new ArrayList<Character>();
	private int numOfTerminals;
	private int numOfNonTerminals;
	Production[][] table;

	/**
	 * @description 初始化CFG
	 * 获得所有产生式，终结符，非终结符，并构建空PPT
	 */
	public void initialize() {
		FileIO.getCFG(productions, terminals, nonTerminals);

		numOfTerminals = terminals.size();
		numOfNonTerminals = nonTerminals.size();
		table = new Production[numOfNonTerminals][numOfTerminals];
		for (int i = 0; i < numOfNonTerminals; i++) {
			Arrays.fill(table[i], null);
		}
	}

	/**
	 * @description 生成LL(1)分析表
	 * 返回true表示符合LL(1)文法
	 * 若待填单元格已有产生式，则引发冲突，说明不符合LL(1)文法，返回false
	 */
	public boolean getTable() {
		for (Production production : productions) {
			for (char a : firstOfString(production.right)) {
				if (a != '`') {
					//终结符
					if (table[nonTerminals.indexOf(production.left)][terminals.indexOf(a)] == null) {
						table[nonTerminals.indexOf(production.left)][terminals.indexOf(a)] = production;
					} else {
						return false;
					}

				} else {
					//空串
					for (char b : follow(production.left)) {
						//b包含$
						if (table[nonTerminals.indexOf(production.left)][terminals.indexOf(b)] == null) {
							table[nonTerminals.indexOf(production.left)][terminals.indexOf(b)] = production;
						}else {
							return false;
						}
					}
				}
			}
		}
		System.out.println("PPT:");
		//打印分析表
		for (int i= 0;i<nonTerminals.size();i++){
			for (int j = 0;j<terminals.size();j++){
				if (table[i][j]!=null){
					table[i][j].print();
				}else{
					System.out.print("null");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		return true;
	}

	/**
	 * @description 求一个symbol的follow
	 */
	private HashSet<Character> follow(char c) {
		HashSet<Character> result = new HashSet<>();
		if (c == 'S') {
			result.add('$');
		}
		for (Production production : productions) {
			String temp = production.right;
			while (temp.contains(c+"")) {
				int location = temp.indexOf(c+"");
				if (location == temp.length()-1){
					if (production.left!=c){
						result.addAll(follow(production.left));
					}
					break;
				}else {
					temp = temp.substring(location+1);
					HashSet<Character> right = firstOfString(temp);
					result.addAll(right);
					result.remove('`');
					if (right.contains('`')){
						result.addAll(follow(production.left));
					}
				}
			}
		}
		return result;
	}

	/**
	 * @description 求一个symbol的first
	 */
	private HashSet<Character> firstOfChar(char c) {
		HashSet<Character> result = new HashSet<Character>();
		if (c == '`'){
			result.add('`');
		}
		else if(terminals.contains(c)){
			result.add(c);
		}else if(nonTerminals.contains(c)){
			for (Production production : productions){
				if(production.left == c){
					String string = production.right;
					if(string == "`"){
						result.add('`');
					}else{
						result.addAll(firstOfString(string));
					}
				}
			}
		}
		return result;
	}

	/**
	 * @description 求任意表达式的first
	 */
	private HashSet<Character> firstOfString(String string) {
		HashSet<Character> result = new HashSet<Character>();
		int ptr = 0;

		//将第一个字符的first-{'`'}加入结果集
		HashSet<Character> current = firstOfChar(string.charAt(ptr));
		result.addAll(current);
		result.remove('`');

		//如果first(string[i])包含'`'，将first(string[i+1])-{'`'}加入result。
		while (ptr < string.length() - 1
				&& current.contains('`')) {
			ptr++;
			current = firstOfChar(string.charAt(ptr));
			result.addAll(current);
			result.remove('`');
		}

		//如果所有first(string[i])都包含'`'，则将'`'加入result。
		if (ptr == string.length() - 1
				&& current.contains('`')){
			result.add('`');
		}
		return result;
	}

}
