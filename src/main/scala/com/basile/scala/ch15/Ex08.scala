package com.basile.scala.ch15

/**
 * Created by basile.duplessis on 10/01/14.
 */
object Ex08 extends App {

  def allDifferent[@specialized T](x: T, y: T, z: T) = x != y && x != z && y != z

  /*
  public <T extends java/lang/Object> boolean allDifferent(T, T, T);
  public boolean allDifferent$mZc$sp(boolean, boolean, boolean);
  public boolean allDifferent$mBc$sp(byte, byte, byte);
  public boolean allDifferent$mCc$sp(char, char, char);
  public boolean allDifferent$mDc$sp(double, double, double);
  public boolean allDifferent$mFc$sp(float, float, float);
  public boolean allDifferent$mIc$sp(int, int, int);
  public boolean allDifferent$mJc$sp(long, long, long);
  public boolean allDifferent$mSc$sp(short, short, short);
  public boolean allDifferent$mVc$sp(scala.runtime.BoxedUnit, scala.runtime.BoxedUnit, scala.runtime.BoxedUnit);
   */

}
