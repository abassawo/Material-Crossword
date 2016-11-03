package com.example.c4q.materialcrossword.game;

/**
 * Created by C4Q on 11/3/16.
 */

import android.view.View;
import android.view.inputmethod.BaseInputConnection;


public class CrosswordInputConnection
        extends BaseInputConnection
{
    public interface OnInputEventListener
    {
        void onWordEntered(CharSequence text);
        void onWordCancelled();
        void onEditorAction(int actionCode);
    }

    private OnInputEventListener mInputEventListener;

    public CrosswordInputConnection(View targetView)
    {
        super(targetView, false);
    }

    public void setOnInputEventListener(OnInputEventListener listener)
    {
        mInputEventListener = listener;
    }

    @Override
    public boolean commitText(CharSequence text, int newCursorPosition)
    {
        if (mInputEventListener != null && text.length() == 0) {
            mInputEventListener.onWordCancelled();
        }

        return super.commitText(text, newCursorPosition);
    }

    @Override
    public boolean performEditorAction(int actionCode)
    {
        if (mInputEventListener != null) {
            mInputEventListener.onEditorAction(actionCode);
        }

        return super.performEditorAction(actionCode);
    }

    @Override
    public boolean setComposingText(CharSequence text, int newCursorPosition)
    {
        if (mInputEventListener != null) {
            mInputEventListener.onWordEntered(text);
        }

        return super.setComposingText(text, newCursorPosition);
    }
}