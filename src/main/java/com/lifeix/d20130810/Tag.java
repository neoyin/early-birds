package com.lifeix.d20130810;

public class Tag {  
    
    public Tag(String value, int beginPos, int endPos) {  
        super();  
        this.value = value;  
        this.beginPos = beginPos;  
        this.endPos = endPos;  
    }  
    private String value;  
    private int beginPos;  
    private int endPos;  
    public String getValue() {  
        return value;  
    }  
    public void setValue(String value) {  
        this.value = value;  
    }  
    public int getBeginPos() {  
        return beginPos;  
    }  
    public void setBeginPos(int beginPos) {  
        this.beginPos = beginPos;  
    }  
    public int getEndPos() {  
        return endPos;  
    }  
    public void setEndPos(int endPos) {  
        this.endPos = endPos;  
    }  
      
} 