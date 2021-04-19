package org.example.logick;

public class Colcul {
    Integer a;
    Integer b;
    Character c;

    public Colcul(Integer a, Integer b, Character c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public Integer answer(){
        Integer answer=0;
        if (c=='p'){
            answer=a+b;
        }else if (c=='-'){
            answer=a-b;
        }else if (c=='/'){
            answer=a/b;
        }else if (c=='*'){
            answer=a*b;
        }else if (c=='o'){
            answer=a%b;
        }
        return answer;
    }
}
