import java.util.Scanner;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
    	Scanner s = new Scanner (System.in);
    	System.out.print("Enter your fraction equation or type quit to quit");
    	String userResponse = s.nextLine();
        // Checkpoint 2: Accept user input multiple times.
    	while (!userResponse.equalsIgnoreCase("quit"))
    	{
    		System.out.println(produceAnswer(userResponse));
    		System.out.print("Enter a fraction equation or type quit to quit");
    		userResponse = s.nextLine();
    		
    	}
    }
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
    	Scanner s =new Scanner(input);
    	String operand1 = s.next();// will just read to space or end of section
    	String operator = s.next();// + or - or*
    	System.out.println("operator is " + operator);
    	String operand2= s.next();
    
    	
    	
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
    	String op2Whole = findWhole(operand2); //find whole
    	int newop2Whole=Integer.parseInt(op2Whole);

    	
    	String op2Num = findNum(operand2); // find numerator of user input
    	int newop2Num=Integer.parseInt(op2Num);
    	
    	String op2Denom = findDenom(operand2);// find denominator
    	int newop2Denom=Integer.parseInt(op2Denom);
    	
    	String chk2Answer = "2ND OPERAND whole:" + newop2Whole + " numerator:" + newop2Num + " denominator:" + newop2Denom;
    	String op1Whole = findWhole(operand1); //separate user input into whole, num and denom
    	int newop1Whole=Integer.parseInt(op1Whole);
    	//System.out.println(chk2Answer);
    	
    	String op1Num=findNum(operand1);
    	int newop1Num=Integer.parseInt(op1Num);
    	
    	String op1Denom= findDenom(operand1);
    	int newop1Denom=Integer.parseInt(op1Denom);
    	String op1Answer = "1ST OPERAND whole:" + newop1Whole +" numberator:" + newop1Num + " denominator:" + newop1Denom;
    	
    	//System.out.println(op1Answer);
    			
    	
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
    
    	int impropernum1 = ((Math.abs(newop1Whole) * newop1Denom)+Math.abs(newop1Num));//changes first fraction to improper fraction
    	if (newop1Whole <0 || newop1Num <0)
    	{
    		impropernum1 *= -1;
    	}
    	//System.out.println(impropernum1 + "this is impropernum1");// tests impropernum1
    	
    	int impropernum2 = ((Math.abs(newop2Whole) * newop2Denom)+ Math.abs(newop2Num));// changes second fraction to improper fraction
    	if (newop2Whole <0 || newop2Num <0)
    	{
    		impropernum2 *= -1;
    	}
    	//System.out.println(impropernum2 + "this is impropernum1");// tests impropernum2
    	int sclimpropernum1 = impropernum1*newop2Denom; //scales the numerator of the first fraction so it matches the denominator
    	//System.out.println("this is scaled num1: " + sclimpropernum1);
    	int sclimpropernum2= impropernum2*newop1Denom; //scales the denominator of the second fraction so it matches the denominator
    	//System.out.println("this is scaled num2: " + sclimpropernum2);
    	int improperdenom=(newop1Denom*newop2Denom); // scales denominator so they're the same 
    	
    	//System.out.println("this is scaled improper frac1: " + sclimpropernum1 + "/" + improperdenom);
    	//System.out.println("this is scaled improper frac2:" + sclimpropernum2 + "/"+ improperdenom);
    	String answer= "";
    	int answernum=0;
    	int answerdenom=0;
    	
    	if (operator.equals("+")) //adds the two scaled improper fractions
    	{
    		answernum = sclimpropernum1 + sclimpropernum2;
    		
    		answerdenom= improperdenom;

    		answer = answernum + "/" + answerdenom;
    		//System.out.println(answer);
    	}
    	if (operator.equals("-"))
    	{
    		answernum = sclimpropernum1 - sclimpropernum2;
    		Integer.toString(answernum);
    		answerdenom = improperdenom;
    		Integer.toString(answerdenom);
    		answer=answernum + "/" + answerdenom;	
    	}
    	if (operator.equals("*"))
    	{
    		answernum = sclimpropernum1 * sclimpropernum2;
    		Integer.toString(answernum);
    		answerdenom = improperdenom * improperdenom;
    		Integer.toString(answerdenom);
    		answer = answernum + "/" + answerdenom;
    	}
    	if (operator.equals("/"))
    	{
    		answernum = sclimpropernum1 * improperdenom;
    		Integer.toString(answernum);
    		answerdenom = improperdenom * sclimpropernum2;
    		answer = answernum+ "/" + answerdenom;
    	}
    	System.out.println("here's the improper answer: " + answer);
    	int gcd = greatestCommonDivisor(answernum, answerdenom);
    	System.out.println("this is the greatest common divisor: " + gcd);
    	int reducnum = 0;
    	int reducdenom=0;
    	int reducwhole =0;
    	int reducansnum = 0;
    	if (Math.abs(answernum)>answerdenom)
    	{
    		System.out.println("entered line 141");
    		reducnum = answernum/gcd;
    		reducdenom= answerdenom/gcd;
    		System.out.println("here's the reduced fraction"+ reducnum + "/" + reducdenom);
    		
    		reducwhole = reducnum/reducdenom;
   			reducansnum = reducnum % reducdenom;
   			if (reducnum% reducdenom ==0)
   			{
   				answer=Integer.toString(reducwhole);
   			}
   			else if (Math.abs(reducwhole)>0)
   	   			{
   	   				answer = reducwhole + "_" + reducansnum + "/" + reducdenom;
   	   			}
   	    		else
   	    		{
   	   				answer = reducansnum + "/" + reducdenom;
   	   			}
   		}
   				
   	return answer;		
   	}
    
    		
    
    	
    
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        
   
    
    // if mixed number, there'll be an underscore
    // TODO: Fill in the space below with helper methods
    
    public static int findimpropernum(int newop1Whole, int newop1Num, int newop1Denom )
    {
    	int impnum=((newop1Whole * newop1Denom) + newop1Num);
    	return impnum;
    }
    
    public static String findWhole (String str)//operand 2 can be mixed, fraction or whole. We're finding the whole number part here.
    {
    	if (str.contains("_")) //sees if input is mixed number 
    	{
    		return str.substring(0, str.indexOf('_'));
    	}
    	else if (str.contains("/")) // no need to test for _ for mixed number because of order. tests if input is fraction
    	{
    		return "0"; // 0 because a fraction can't be a whole number
    	}
    	else 
    		return str; //only possibility left is to be just a whole number;
    }
    public static String findNum(String str)//again, could be mixed number, fraction or whole number.
    {
    	if (str.contains("_"))
    	{
    		return str.substring(str.indexOf('_')+1, str.indexOf('/')); // assuming user input is 1_ 3/4. So we take substring from right after _ up till the /
    	}
    	else if (str.contains("/"))
    	{
    		return str.substring(0, str.indexOf('/'));//take substring from beginning up till /
    	}
    	else
    		return "0";
    }
    public static String findDenom(String str)
    {
    	if (str.contains("/"))
    	{
    		return str.substring(str.indexOf("/")+1); // will go to end of the string since only one index is given
    	}
    	else 
    		return "1";
    }
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
