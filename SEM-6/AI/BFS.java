package com.java.ai;

import java.util.*;

public class BFS {
	
	static Scanner scan = new Scanner(System.in);
	static int vertexCount, edgeCount;
	static int vistedVertex[];
	static int graph[][];
	Queue<Integer> neighbour = new LinkedList<>();
	static boolean visited[];
	int c=0;
	static int vistedVertexIndex=0;
	
	BFS()
	{
		System.out.print("Enter number of vertex : ");
		vertexCount= scan.nextInt();
		System.out.print("Enter number of edges : ");
		edgeCount= scan.nextInt();
		graph = new int[vertexCount][edgeCount];
		vistedVertex = new int[vertexCount+1];
		visited = new boolean[vertexCount+1];
		
		for(int i = 0;i<vertexCount;i++ )
		{
			for(int j =0;j<vertexCount;j++ )
			{
				graph[i][j] = 0;
			}
		}
		
		for(int i = 0;i<vertexCount;i++ )
		{
			visited[i] = false;
		}
	}
	
	void bfs()
	{
		//base condition for recursion (if all vertex are visited)
		for(int i=0;i<vertexCount;i++)
		{
			if(visited[i]) c++;
		}
		if(c==vertexCount && vistedVertexIndex==vertexCount)
		{
			return;
			
		}
		else {
			
			//finding neighbours of the visited vertex
			for(int i=0;i<=vistedVertexIndex;i++)
			{
				System.out.print(vistedVertex[i]+" ");
			}
		
			System.out.println();
			for(int i=0;i<vertexCount;i++)
			{
				if(graph[vistedVertex[vistedVertexIndex]][i]==1 && !visited[i])
				{
					neighbour.add(i);	
					visited[i]= true;
				}
			}
		

			System.out.print(neighbour);
			
			System.out.println();
			
			vistedVertexIndex+=1;
			vistedVertex[vistedVertexIndex] = neighbour.remove();
			c=0;
			bfs();
		}
	}
	
	public static void main(String args[])
	{
		BFS objBFS = new BFS();
		int vertexA ,vertexB;
		System.out.println("Enter edge to edge values");
		for(int i = 1;i<=edgeCount;i++ )
		{
			vertexA= scan.nextInt();
			vertexB = scan.nextInt();
			graph[vertexA][vertexB]=1;
			graph[vertexB][vertexA]=1;
		}

		System.out.println("\nConsidering start vertex at 0");
		vistedVertex[0] = 0;
		visited[0] = true;
		objBFS.bfs();
		
		System.out.println("\nBFS using recursion - ");
		for(int i = 0;i<vistedVertexIndex;i++ )
		{
			System.out.print(vistedVertex[i]+" ");
		}
	}

}
