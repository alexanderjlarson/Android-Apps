package edu.lewisu.cs.alexlarson.simplegame;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	
	private Paint circlePaint;
	private Point myCircle;
	private int direction;
	private int radius;
	private GameThread gameThread;
	private int screenWidth;
	private boolean touchCircle = false;
	private GestureDetector gestureDetector;
	private SoundPool soundPool;
	private ArrayList<Integer> soundArray;
	private Activity parent;
	private int score;
	private double totalElapsedTime;
	private Paint textPaint;
	
	public GameView(Context context) {
		super(context);
		getHolder().addCallback(this);
		circlePaint = new Paint();
		circlePaint.setColor(Color.BLUE);
		circlePaint.setAntiAlias(true);
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
		textPaint.setAntiAlias(true);
		myCircle = new Point();
		myCircle.x=0;
		myCircle.y=300;
		direction = 1;
		radius = 30;
		GestureListener listener = new GestureListener();
		gestureDetector = new GestureDetector(getContext(), listener);
		parent = (Activity)context;
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundArray = new ArrayList<Integer>();
		soundArray.add(soundPool.load(parent, R.raw.boing2,0));
		soundArray.add(soundPool.load(parent, R.raw.gong,0));
		
	}
	
	private void showGameOver(){
		final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("Game Over");
		builder.setCancelable(false);
		String message = String.format("Total Time: %1$.1f", totalElapsedTime/1000);
		builder.setMessage(message);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		parent.runOnUiThread(new Runnable(){
			public void run(){
				builder.show();
			}
		});
		soundPool.play(soundArray.get(1),1,1,0,1,1);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventX = (int)event.getX();
		int eventY = (int)event.getY();
		
		switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(inCircle(eventX, eventY))
				touchCircle = true;
			break;
		case MotionEvent.ACTION_UP:
			touchCircle=false;
			break;
			default:
				return false;
		}
		gestureDetector.onTouchEvent(event);
		return true;
	}

	public void moveCircle(int x, int y){
		myCircle.x=x;
		myCircle.y=y;
	}
	
	public boolean inCircle(int x, int y){
		double square_dist = Math.pow(myCircle.x - x, 2) + Math.pow(myCircle.y - y, 2);
		return square_dist <= radius * radius;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		screenWidth=w;
		textPaint.setTextSize(w/20);
		radius = w/30;
	}
	private void updatePosition(double elapsedTime){
		myCircle.x += elapsedTime/5 * direction;
		if(myCircle.x > screenWidth){
			myCircle.x = screenWidth;
			direction = -1;
			soundPool.play(soundArray.get(0), 1, 1, 0, 1, 1);
			score++;
		}
		else if(myCircle.x <= 0){
			myCircle.x = 0;
			direction = 1;
			soundPool.play(soundArray.get(0), 1, 1, 0, 1, 1);
			score++;
		}
		if(score >= 5){
			gameThread.setRunning(false);
			showGameOver();
		}
	}
	
	public void drawGameElements(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		canvas.drawCircle(myCircle.x, myCircle.y, radius, circlePaint);
		canvas.drawText(String.format("Score: %1$d", score),30, 50, textPaint);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameThread = new GameThread(holder);
		gameThread.setRunning(true);
		gameThread.start();
		
	}
	public void stopGame(){
		if(gameThread!=null)
			gameThread.setRunning(false);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameThread.setRunning(false);
		
	}
	private class GameThread extends Thread{
		private SurfaceHolder surfaceHolder;
		private boolean threadRunning=true;
		
		public GameThread(SurfaceHolder holder){
			surfaceHolder = holder;
		}
		public void setRunning(boolean state){
			threadRunning = state;
		}
		public void run(){
			long previousFrameTime = System.currentTimeMillis();
			long currentTime;
			double elapsedTimeMS;
			Canvas canvas = null;
			
			while(threadRunning){
				try{
					canvas = surfaceHolder.lockCanvas(null);
					synchronized(surfaceHolder){
						currentTime = System.currentTimeMillis();
						elapsedTimeMS = currentTime - previousFrameTime;
						previousFrameTime = currentTime;
						updatePosition(elapsedTimeMS);
						drawGameElements(canvas);
						totalElapsedTime += elapsedTimeMS;
					}
				}finally{
					if(canvas != null){
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}
	}
	private class GestureListener extends SimpleOnGestureListener{
		public boolean onDoubleTap(MotionEvent e){
			int xLoc = (int) e.getX();
			int yLoc = (int) e.getY();
			moveCircle(xLoc, yLoc);
			return true;
		}
	}

}
