// Name: Sitao Min
// USC NetID: sitaomin
// CS 455 PA4
// Spring 2018

/**
 * This contains the main method. 
 * This class will have a main that's responsible for processing the command-line argument, 
 * and handling any error processing. It will probably also have the main command loop. 
 * Most of the other functionality will be delegated to other object(s) created in main and their methods.
 * (1 point)-A, E, I, O, U, L, N, S, T, R
 * (2 points)-D, G
 * (3 points)-B, C, M, P
 * (4 points)-F, H, V, W, Y
 * (5 points)-K
 * (8 points)- J, X
 * (10 points)-Q, Z
 */
public class ScoreTable {
    
    private static final int TEN_POINTS = 10;
    private static final int EIGHT_POINTS = 8;
    private static final int FIVE_POINTS = 5;
    private static final int FOUR_POINTS = 4;
    private static final int THREE_POINTS = 3;
    private static final int TWO_POINTS = 2;
    private static final int ONE_POINT = 1;
    private static final int NUM_LETTER = 26;
    
    private  int[] scoreTable;                   //store the score of each letter
    
    public ScoreTable() {
        scoreTable = new int[NUM_LETTER];
        String s = "abcdefghigklmnoqprstuvwxyz";
        for(int i = 0; i< s.length();i++) {
            char c = s.charAt(i);
            if(c=='a'||c=='e'||c=='i'||c=='o'||c =='u'||c=='l'||c =='n'||c =='n'||c =='s'||c =='t' ||c == 'r') {
                scoreTable[c-'a'] = ONE_POINT;
            }
            else if(c == 'd'||c == 'g') {
                scoreTable[c-'a'] = TWO_POINTS;
            }
            else if(c == 'b' || c == 'c' || c == 'm'|| c == 'p') {
                scoreTable[c-'a'] = THREE_POINTS;
            }
            else if(c == 'f' || c == 'h'|| c == 'v'|| c == 'w'|| c == 'y') {
                scoreTable[c-'a'] = FOUR_POINTS;
            }
            else if(c == 'k') {
                scoreTable[c-'a'] = FIVE_POINTS;
            }
            else if(c == 'j'|| c == 'x') {
                scoreTable[c-'a'] = EIGHT_POINTS;
            }
            else {
                scoreTable[c-'a'] = TEN_POINTS;
            }
        }   
    }
    /**
     * compute the score of input word
     * @param s input word
     * @return score
     */
    public int getScore(String s) {
        int score = 0;
        for(int i = 0; i< s.length(); i++) {
            if(s.charAt(i)>='A'&&s.charAt(i)<='Z') {
                score += scoreTable[s.charAt(i)-'A'];
            }
            else {
                score += scoreTable[s.charAt(i)-'a'];
            }
        }
        return score;
    }

    
}
