package com.example.blockdoku;

public class Board {
    private static Board main;
    private static final boolean [][] table = new boolean[9][9];
    private int score = 0;

    private Board(){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                table[i][j] = false;
    }

    public static Board getTable(){
        if(main == null) main = new Board();
        return main;
    }

    public enum Piece {
        L, DOWN_L, ROW, COL, DOWN_T, T, SHORT_L, DOWN_SHORT_L
    }

    public static void updateBoard(){
        if(checkSubsquares() || checkCols() || checkRows()){
            System.out.println("SCORE + 1");
        }
        if(fullBoard()) System.out.println("GAME OVER!");
    }

    public static boolean checkSubsquares(){
        return false;
    }

    public static boolean checkCols(){
        for(int i=0; i<9; i++){
            int j = 0;
            while(table[j][i] && j < 9)
                j++;
            if(j==9) return true;
        }
        return false;
    }

    public static boolean checkRows(){
        for(int i=0; i<9; i++){
            int j = 0;
            while(table[i][j] && j < 9)
                j++;
            if(j==9) return true;
        }
        return false;
    }

    public static boolean fullBoard(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++)
                if(!table[i][j]) return false;
        }
        return true;
    }

    public static boolean checkX(int x, int y, int dist){
        while(dist >= 0)
            if(table[x + (dist--)][y]) return false;
        return true;
    }

    public static void fillX(int x, int y, int dist) {
        while(dist >= 0)
            table[x + (dist--)][y] = true;
    }

    public static boolean checkY(int x, int y, int dist){
        while(dist <= 0)
            if(table[x][y + (dist++)]) return false;
        return true;
    }

    public static void fillY(int x, int y, int dist) {
        while(dist <= 0)
            table[x][y + (dist++)] = true;
    }

    public static boolean insertPiece(Piece p, int x, int y){
        boolean canBePlaced = false;
        switch(p){
            case L:
                canBePlaced = checkY(x,y,-2) && checkX(x,y-2,2);
                if(canBePlaced) {
                    fillY(x,y,-2);
                    fillX(x,y-2,2);
                }
                break;
            case DOWN_L:
                canBePlaced = checkX(x,y,2) && checkY(x+2,y,-2);
                if(canBePlaced) {
                    fillX(x,y,2);
                    fillY(x+2,y,-2);
                }
                break;
            case ROW:
                canBePlaced = checkX(x,y,2);
                if(canBePlaced) fillX(x,y,2);
                break;
            case COL:
                canBePlaced = checkY(x,y,-2);
                if(canBePlaced) fillY(x,y,-2);
                break;
            case DOWN_T:
                canBePlaced = checkY(x,y,-2) && checkX(x-1,y-2,2);
                if(canBePlaced){
                    fillY(x,y,-2);
                    fillX(x-1,y-2,2);
                }
                break;
            case T:
                canBePlaced = checkX(x,y,2); checkY(x+1,y,-2);
                if(canBePlaced){
                    fillX(x,y,2);
                    fillY(x+1,y,-2);
                }
                break;
            case SHORT_L:
                canBePlaced = checkY(x,y,2) && checkX(x,y,1);
                if(canBePlaced){
                    fillY(x,y,2);
                    fillX(x,y,1);
                }
                break;
            case DOWN_SHORT_L:
                canBePlaced = checkY(x,y,-2) && checkX(x,y+2,1);
                if(canBePlaced){
                    fillY(x,y,-2);
                    fillX(x,y+2,1);
                }
                break;
        }
        if(canBePlaced) updateBoard();
        return canBePlaced;
    }

    public static void printBoard(){
        for(int i=0; i<9; i++) {
            System.out.print('[' +  i + ']' + '\t');
            for (int j = 0; j < 9; j++)
                System.out.print(table[i][j] ?  " * " : "   ");
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<9; i++)
            System.out.print("   \t[" + i + ']');
    }
}
