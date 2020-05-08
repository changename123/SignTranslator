package c.adrianwozniak.singtranslator.util;

import java.util.HashMap;

import c.adrianwozniak.singtranslator.R;

public class SignAlphabetImagesMap {
    private static HashMap<String, Integer> MAP;

    public SignAlphabetImagesMap(){
        MAP = new HashMap<>();
        if(MAP.isEmpty()){
            addItems();
        }
    }

    private final static void addItems(){
        MAP.put(" ", R.drawable.space);
        MAP.put("a", R.drawable.a);
        MAP.put("b", R.drawable.b);
        MAP.put("c", R.drawable.c);
        MAP.put("d", R.drawable.d);
        MAP.put("e", R.drawable.e);
        MAP.put("f", R.drawable.f);
        MAP.put("g", R.drawable.g);
        MAP.put("h", R.drawable.h);
        MAP.put("i", R.drawable.i);
        MAP.put("j", R.drawable.j);
        MAP.put("k", R.drawable.k);
        MAP.put("l", R.drawable.l);
        MAP.put("m", R.drawable.m);
        MAP.put("n", R.drawable.n);
        MAP.put("o", R.drawable.o);
        MAP.put("p", R.drawable.p);
        MAP.put("q", R.drawable.q);
        MAP.put("r", R.drawable.r);
        MAP.put("s", R.drawable.s);
        MAP.put("t", R.drawable.t);
        MAP.put("u", R.drawable.u);
        MAP.put("v", R.drawable.v);
        MAP.put("w", R.drawable.w);
        MAP.put("x", R.drawable.x);
        MAP.put("y", R.drawable.y);
        MAP.put("z", R.drawable.z);
    }

    public final static HashMap<String, Integer> getMap(){
        return MAP;
    }

}
