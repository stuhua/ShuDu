package com.ss.ShuDu3;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MyView extends View {

	private float width;
	private float height;
	int selectedX;
	int selectedY;
	private Game game = new Game();

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		this.width = w / 9f;
		this.height = h / 9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// ���Ʊ�����ɫ
		Paint background_paint = new Paint();
		background_paint.setColor(getResources().getColor(R.color.background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background_paint);

		Paint white = new Paint();
		white.setColor(getResources().getColor(R.color.white));

		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.light));

		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.dark));

		for (int i = 0; i < 9; i++) {
			// �����������
			canvas.drawLine(0, i * height, getHeight(), i * height, light);
			canvas.drawLine(0, i * height + 1, getHeight(), i * height + 1,
					white);
			// �����������
			canvas.drawLine(i * width, 0, i * width, getHeight(), light);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), white);
		}
		for (int i = 0; i < 9; i++) {
			if (i % 3 != 0) {
				continue;
			}
			canvas.drawLine(0, i * height, getHeight(), i * height, dark);
			canvas.drawLine(0, i * height + 1, getHeight(), i * height + 1,
					white);
			// �����������
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), white);
		}

		Paint number_paint = new Paint();
		number_paint.setColor(Color.BLACK);
		number_paint.setStyle(Paint.Style.STROKE);
		number_paint.setTextSize(height * 0.75f);
		number_paint.setTextAlign(Paint.Align.CENTER);
		//�����־��У�
		FontMetrics fm = number_paint.getFontMetrics();
		float x = width / 2;
		float y = height / 2 - (fm.ascent + fm.descent) / 2;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				canvas.drawText(game.getTileString(i, j), width * i + x, height
						* j + y, number_paint);
			}
		}

		super.onDraw(canvas);
	}
//���㴥���¼�
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return super.onTouchEvent(event);
		}
		selectedX = (int) (event.getX() / width);
		selectedY = (int) (event.getY() / height);

		int used[] = game.getUsedTileByCoor(selectedX, selectedY);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < used.length; i++) {
			sb.append(used[i]);
			// System.out.println(used[i]);
		}

		// //����һ��LayoutInflater����
		// LayoutInflater layoutInflater=LayoutInflater.from(this.getContext());
		// //ʹ��LayoutInflater�������һ�������ļ�������һ��View
		// View layoutView=layoutInflater.inflate(R.layout.dialog, null);
		// //���ɺõ�TextView���У�ȡ����Ӧ�Ŀؼ�
		// TextView textView=(TextView)layoutView.findViewById(R.id.usedTextId);
		// //����Textview����
		// textView.setText(sb.toString());
		// //����һ���Ի����Builder����
		// AlertDialog.Builder builder=new
		// AlertDialog.Builder(this.getContext());
		// //���öԻ���Ĳ���
		// builder.setView(layoutView);
		// //����һ���Ի���
		// AlertDialog dialog=builder.create();
		// //��ʾ�Ի���
		// dialog.show();
		KeyDialog keyDialog = new KeyDialog(getContext(), used, this);
		keyDialog.show();
		return true;
	}
	//View�����KeyDialog���ݹ��������ݣ�����ҵ���߼�Game�࣬���д���  
	public void setSelectedTile(int tile) {
		// TODO Auto-generated method stub
		if (game.setTileIfValid(selectedX, selectedY, tile)) {
			invalidate();//���»���View���� 
		}
	}

}
