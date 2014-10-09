package com.ss.ShuDu3;

public class Game {
	private final String str="360000000004230800000004200"
			+"070460003820000014500013020"
			+"001900000007048300000000045";
	private int sudoku[]=new int [9*9];
	
	private int used[][][]=new int[9][9][];
	public Game()
	{
		sudoku=fromPuzzleString(str);
		//���ڼ������е�Ԫ���Ӧ�Ĳ���������
		calculateAllUesdTiles();
	}

//	���ݾŹ����е����꣬���ظ����꣬��Ӧ�÷��ص�����
	public int getTile(int x,int y){
		return sudoku[y*9+x];
	}
			
	public String getTileString(int x,int y)
	{
		int v=getTile(x,y);
		if(v==0)
		{
			return "";
		}
		else
		{
			return String.valueOf(v);
		}
	}
	
	//����һ���ַ������ݣ�����һ���������飬��Ϊ������Ϸ�ĳ�ʼ������
	protected int[] fromPuzzleString(String src)
	{
		int []sudo=new int [src.length()];
		for(int i=0;i<sudo.length;i++)
		{
			sudo[i]=src.charAt(i)-'0';
		}
		return sudo;
	}
	
	//���ڼ������е�Ԫ���Ӧ�Ĳ���������
	public void calculateAllUesdTiles()
	{
		for(int x=0;x<9;x++)
		{
			for(int y=0;y<9;y++)
			{
				used[x][y]=calculateUesdTiles(x,y);
			}
		}
	}
	
	//ȡ��ĳһ��Ԫ�����Ѿ������õ�����
	public int[] getUsedTileByCoor(int x, int y)
	{
		return used[x][y];
	}

	//����ĳһ��Ԫ���в����õ�����
	private int[] calculateUesdTiles(int x,int y) {
		// TODO Auto-generated method stub
		int c[]=new int[9];
		for (int i=0;i<9;i++)
		{
			if(i==y)
				continue;
			//����ÿ�����ù�������
			int t=getTile(x,i);
			if(t!=0)
				c[t-1]=t;
		}
		
		for (int i=0;i<9;i++)
		{
			if(i==x)
				continue;
			int t=getTile(i,y);
			if(t!=0)
				c[t-1]=t;
		}
		int startx=(x/3)*3;
		int starty=(y/3)*3;
		for(int i=startx;i<startx+3;i++)
		{
			for(int j=starty;j<starty+3;j++)
			{
				if(i==x&&j==y)
					continue;
				int t=getTile(i,j);
				if(t!=0)
					c[t-1]=t;
			}
		}
		int nused=0;
		//��t����c
		for(int t:c)
		{
			if(t!=0)
				nused++;
		}
		int c1[]=new int[nused];
		nused=0;
		for(int t:c)
		{
			if(t!=0)
				c1[nused++]=t;
		}
		return c1;
	}
	 //����KeyDialog�е��������  
	protected boolean setTileIfValid(int x,int y,int value)
	{
		int tiles[]=getUsedTiles(x,y);
		if(value !=0)
		{
			for(int tile:tiles)
			{
				if(tile==value)
					return false;
			}
		}
		setTile(x,y,value); //����KeyDialog�е��������  
		calculateAllUesdTiles();//���¼������и��ӵĲ���������  
		return true;
		
	}

	private int[] getUsedTiles(int x, int y) {
		return used[x][y];
	}
	private void setTile(int x,int y,int value)
	{
		sudoku[y*9+x]=value;
	}
}
