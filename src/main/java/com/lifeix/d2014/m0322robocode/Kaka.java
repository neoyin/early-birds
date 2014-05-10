//package li;
//import robocode.*;
//
///**
// * Kaka - a robot by (your name here)
// */
//public class Kaka extends Robot
//{
//	public static boolean go = true;
//	public static int dist = 200;
//	/**
//	 * run: Kaka's default behavior
//	 */
//	public void run() {
//		while(true) {
//			if(go){
//				go = false;
//				turnGunLeft(450);
//			}else{
//				go = true;
//				turnGunRight(360);
//			}
//		}
//	}
//	
//	/**
//	 * onScannedRobot: What to do when you see another robot
//	 */
//	public void onScannedRobot(ScannedRobotEvent e) {
//		// Replace the next line with any behavior you would like
//		fire(3);
//	}
//
//	/**
//	 * onHitByBullet: What to do when you're hit by a bullet
//	 */
//	public void onHitByBullet(HitByBulletEvent e) {
//		// Replace the next line with any behavior you would like
////		if(turn){
////			turn = false;
////			turnLeft(120);
////		}else{
////			turn = true;
////			turnRight(90);
////		}
////		if(fangxiang){
////			fangxiang = false;
////			back(100);
////		}else{
////			fangxiang = true;
////			ahead(100);
////		}
//		turnRight(bearingToParallel(90 - (getHeading() - e.getHeading())));  
//	    ahead(dist); 
//	    dist *= -1; 
//	    scan(); 
//	}
//	public static double calculate(double du){
//		while(du > 180){
//			du -= 360;
//		}
//		while(du< - 180){
//			du += 360;
//		}
//		return du;
//	}
//	public double bearingToParallel(double angle) { 
//	    // all we had to change was the values: divided all by 2! 
//	    if (angle > -90 && angle <= 90) 
//	        return angle; 
//	    double fixedAngle = angle; 
//	    while (fixedAngle <= -90) 
//	        fixedAngle += 180; 
//	    while (fixedAngle > 90) 
//	        fixedAngle -= 180; 
//	    return fixedAngle; 
//	}  
//	/**
//	 * onHitWall: What to do when you hit a wall
//	 */
//	public void onHitWall(HitWallEvent e) {
//		if(dist > 0){
//			dist *= -1; 
//			back(100);
//		}else{
//			dist *= -1; 
//			ahead(100);
//		}
//	}	
//	public void onHitRobot(HitRobotEvent e) {
//		if(dist > 0){
//			dist *= -1; 
//			back(100);
//		}else{
//			dist *= -1; 
//			ahead(100);
//		}
//	}
//}
