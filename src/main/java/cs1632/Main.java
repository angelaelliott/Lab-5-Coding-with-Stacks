package cs1632;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main {
    private static Boolean endsSequence(Token tok) {
        if (tok == null) return true;
        if (tok instanceof OpToken) {
            if (((OpToken) tok).getOperator() == ';') return true;
            if (((OpToken) tok).getOperator() == '.') return true;
        }
        return false;
    }
    private static ArrayList<Token> getNextSequence(Tokenizer toknizr) throws IOException {
        ArrayList<Token> tokens = new ArrayList<>();
        Token tok;
        do {
            tok = toknizr.nextToken();
            if (tok != null) tokens.add(tok);
        } while (!endsSequence(tok));
        if (tok == null) return null;
        if (tok instanceof OpToken) {
            if (((OpToken) tok).getOperator() == '.') {
                return null;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        try {
            Tokenizer myScanner = new Tokenizer(System.in);
            ArrayList<Token> tokenSequence;
            ArrayList<Token> postfixSequence = null;
            do {
                System.out.print("Equation: ");
                tokenSequence = getNextSequence(myScanner);
                if (tokenSequence != null) {
                    // Replace the code starting here with code to evaluate the expression
                    //here is our stack
                    Deque<Integer> stack = new ArrayDeque<>();

                    // loop from right to left starting from the first token of the equation that isn't the semicolon
                    for (int i = tokenSequence.size() - 2; i >= 0; i--) {
                        // get the token as a string
                        String tokenStr = tokenSequence.get(i).getTokenString();
                        // switch statement for each operator
                        switch (tokenStr) {
                            case "+": {
                                int first = stack.pop();
                                int second = stack.pop();
                                stack.push(first + second);
                                break;
                            }
                            case "-": {
                                int first = stack.pop();
                                int second = stack.pop();
                                stack.push(first - second);
                                break;
                            }
                            case "*": {
                                int first = stack.pop();
                                int second = stack.pop();
                                stack.push(first * second);
                                break;
                            }
                            case "/": {
                                int first = stack.pop();
                                int second = stack.pop();
                                stack.push(first/second);
                                break;
                            }
                            default: {
                                // if it's not an operator, assume it's an integer (add that to the stack)
                                stack.push(Integer.parseInt(tokenStr));
                                break;
                            }
                        }
                    }

                    // result is the last item on the stack after calculations
                    System.out.println("Result: " + stack.pop());
                    // End where to replace
                }
            } while (tokenSequence != null);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}