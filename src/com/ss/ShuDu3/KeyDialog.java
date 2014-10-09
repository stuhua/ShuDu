package com.ss.ShuDu3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

//��������ʵ��Dialog��ʵ���Զ���ĶԻ�����
public class KeyDialog extends Dialog{
	//������Ŵ���Ի����а�ť�Ķ���
	private final View keys[]=new View[9];
	private final int used[];
	private MyView myView;
	//���캯���ĵڶ����������б����ŵ�ǰ��Ԫ���Ѿ�ʹ�ù�������
	public KeyDialog(Context context,int[] used,MyView myView) {
		super(context);
		this.used=used;
		this.myView=myView;
	}
	//��һ��Dialog��һ����ʾ��ʱ�򣬻����Oncreate����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//���ñ���
		setTitle("KeyDialog");
		//����Ϊ��Dialog���ò����ļ�
		setContentView(R.layout.keypad);
		findViews();
		
		//��������used����
		for(int i=0;i<used.length;i++)
		{
			if(used[i]!=0)
			{
				keys[used[i]-1].setVisibility(View.INVISIBLE);
			}
		}
		setListeners();
	}

	private void findViews()
	{
		keys[0]=findViewById(R.id.keypad_1);
		keys[1]=findViewById(R.id.keypad_2);
		keys[2]=findViewById(R.id.keypad_3);
		keys[3]=findViewById(R.id.keypad_4);
		keys[4]=findViewById(R.id.keypad_5);
		keys[5]=findViewById(R.id.keypad_6);
		keys[6]=findViewById(R.id.keypad_7);
		keys[7]=findViewById(R.id.keypad_8);
		keys[8]=findViewById(R.id.keypad_9);
		
	}
	//֪ͨMyView����ˢ�������Ź�����ʾ������
	private void returnResult(int tile)
	{
		myView.setSelectedTile(tile);
		//ȡ���Ի�����ʾ
		dismiss();
	}
	
	private void setListeners(){
		//��������keys����
		for(int i=0;i<keys.length;i++)
		{
			final int t=i+1;
			keys[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					returnResult(t);
				}
			});
		}
	}
}

