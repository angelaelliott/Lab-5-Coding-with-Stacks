package cs1632;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class PrefixExpressionEvaluator {
    public static int evaluateExpression(ArrayList<Token> tokenSequence) {
        //here is our stack
        Deque<Integer> stack = new ArrayDeque<>();

        // loop from right to left starting from the first token of the equation that isn't the semicolon
        for (int i = tokenSequence.size() - 2; i >= 0; i--) {
            // get the token as a string
            String tokenStr = tokenSequence.get(i).getTokenString();
            // switch statement for each operator
            switch (tokenStr) {
                case "+": {
                    stack.push(stack.pop()+stack.pop());
                    break;
                }
                case "-": {
                    stack.push(stack.pop()-stack.pop());
                    break;
                }
                case "*": {
                    stack.push(stack.pop()*stack.pop());
                    break;
                }
                case "/": {
                    stack.push(stack.pop()/stack.pop());
                    break;
                }
                default: {
                    // if it's not an operator, assume it's an integer (add that to the stack)
                    stack.push(Integer.parseInt(tokenStr));
                    break;
                }
            }
        }
        return stack.pop();
    }
}
