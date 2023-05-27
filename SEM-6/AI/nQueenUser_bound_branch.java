package com.java.ai;

import java.util.*;

public class nQueenUser_bound_branch {
static int n;
static int count=0;

public void solve(boolean[][] chessboard,int row,boolean[] cols,boolean[] rdia,boolean[] ldia)
{
if(row==chessboard.length)
{
for(int i=0;i<chessboard.length;i++)
{
for(int j=0;j<chessboard[i].length;j++)
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
count++;
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
	nQueenUser_bound_branch obj = new nQueenUser_bound_branch();
Scanner scan = new Scanner(System.in);
System.out.print("Enter size of the chessboard : ");
n = scan.nextInt();
int col;
boolean[][] chessboard = new boolean[n][n];
boolean[] cols = new boolean[n];
boolean[] rdia = new boolean[2*n-1];
boolean[] ldia = new boolean[2*n-1];

System.out.print("Enter column to place the queen in the first row : ");
col = scan.nextInt();
scan.close();
chessboard[0][col] = true;
cols[col] = true;
rdia[0+col] = true;
ldia[0+col+n-1] = true;
obj.solve(chessboard,1,cols,rdia,ldia);

if(count==0)
{
System.out.println("No possiblities found");
}else
{
System.out.println("Number of posibilities for "+n+"Queen with starting [0]["+col+"]: "+count);
}


}
}
