package com.example.c4q.materialcrossword.game;

/**
 * Created by C4Q on 11/3/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.example.c4q.materialcrossword.crossword.model.Cell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public  class Word
        implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static class Builder {
        private int mNumber;
        private String mHint;
        private int mStartRow;
        private int mStartColumn;
        private int mDir;
        private String mHintUrl;
        private String mCitation;
        private List<Cell> mCells;

        public Builder() {
            mCells = new ArrayList<>();
        }

        public Builder setNumber(int number) {
            mNumber = number;
            return this;
        }

        public Builder setHint(String hint) {
            mHint = hint;
            return this;
        }

        public Builder setStartRow(int startRow) {
            mStartRow = startRow;
            return this;
        }

        public Builder setStartColumn(int startColumn) {
            mStartColumn = startColumn;
            return this;
        }

        public Builder setDirection(int dir) {
            mDir = dir;
            return this;
        }

        public Builder setHintUrl(String hintUrl) {
            mHintUrl = hintUrl;
            return this;
        }

        public Builder setCitation(String citation) {
            mCitation = citation;
            return this;
        }

        public Builder addCell(char ch, int attrFlags) {
            return addCell(String.valueOf(ch), attrFlags);
        }

        public Builder addCell(String chars, int attrFlags) {
            Cell cell = new Cell();
//            cell.mAttrFlags = (byte) attrFlags;
//            cell.mChars = chars;

            mCells.add(cell);
            return this;
        }

        public Word build() {
            Word word = new Word();
//            mCells.toArray(word.mCells);
            return word;
        }
    }
}