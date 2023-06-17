package LLDProblems.TicTacToe;

public class Players {
    private String Name;
    private char symbol;

    public String getName() {
        return Name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Players(String name, char symbol) {
        setName(name);
        setSymbol(symbol);
    }
}
