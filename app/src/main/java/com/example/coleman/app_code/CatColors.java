package com.example.coleman.app_code;

import android.graphics.Color;

/**
 * Created by coleman on 4/22/16.
 */
public enum CatColors
{
    GREEN (Color.GREEN),
    BLUE (Color.BLUE),
    YELLOW (Color.YELLOW),
    MAGENTA (Color.MAGENTA),
    WHITE (Color.WHITE);

    public final int id;

    CatColors(int color)
    {
        id = color;
    }
}
