package assignment2_f20;

public interface TMCell { // these will be used by the grader to 
                          // examine your data structure contents
  public void setKey(String newKey);
  public String getKey();
  public void setValue(Value newValue);
  public Value getValue();
  public void setLeft(TMCell newLeft);
  public TMCell getLeft();
  public void setRight(TMCell newRight);
  public TMCell getRight();
}