/**
 * @author Bourbon
 * @date 2017/11/8
 * @description
 */
public class Production {

	public Character left;
	public String right;

	public Production(Character s1, String s2){
		left = s1;
		right = s2;
	}

	public String print(){
		String output = left+"->"+right;
		return output;
	}
}
