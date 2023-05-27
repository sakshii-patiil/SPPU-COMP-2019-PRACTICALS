package com.java.ai;

import java.util.*;

public class nQueen_bound_branch {
static int n;
static int count=0;

public void solve(boolean[][] chessboard,int row,boolean[] cols,boolean[] rdia,boolean[] ldia)
{
if(row==chessboard.length)
{
count++;
for(int i=0;i<chessboard.length;i++)
{
for(int j=0;j<chessboard.length;j++)
{
if(chessboard[i][j])
System.out.print("Q ");
else
System.out.print(". ");
}
System.out.println();
}
System.out.println();
System.out.println();

return;
}

for(int col=0;col<chessboard.length;col++)
{
if(!cols[col] && !rdia[row+col] && !ldia[row-col+(n-1)])
{
chessboard[row][col] = true;
cols[col] = true;
rdia[row+col] = true;
ldia[row-col+(n-1)] = true;

solve(chessboard,row+1,cols,rdia,ldia);

chessboard[row][col] = false;
cols[col] = false;
rdia[row+col] = false;
ldia[row-col+(n-1)] = false;

}
}
}

public static void main(String args[])
{
	nQueen_bound_branch obj = new nQueen_bound_branch();
Scanner scan = new Scanner(System.in);
System.out.print("Enter size of the chessboard : ");
n = scan.nextInt();
scan.close();
boolean[][] chessboard = new boolean[n][n];
boolean[] cols = new boolean[n];
boolean[] rdia = new boolean[2*n-1];
boolean[] ldia = new boolean[2*n-1];

obj.solve(chessboard,0,cols,rdia,ldia);

System.out.println("Number of posibilities for "+n+"Queen is : "+count);

}
}
