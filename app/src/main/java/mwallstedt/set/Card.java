package mwallstedt.set;

import android.util.Log;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int[][][][] sDrawableId = {
		{
			{
				{R.drawable.o1gf, R.drawable.o1gh, R.drawable.o1ge,},
				{R.drawable.o1rf, R.drawable.o1rh, R.drawable.o1re,},
				{R.drawable.o1pf, R.drawable.o1ph, R.drawable.o1pe,},
			},
			{
				{R.drawable.o2gf, R.drawable.o2gh, R.drawable.o2ge,},
				{R.drawable.o2rf, R.drawable.o2rh, R.drawable.o2re,},
				{R.drawable.o2pf, R.drawable.o2ph, R.drawable.o2pe,},
			},
			{
				{R.drawable.o3gf, R.drawable.o3gh, R.drawable.o3ge,},
				{R.drawable.o3rf, R.drawable.o3rh, R.drawable.o3re,},
				{R.drawable.o3pf, R.drawable.o3ph, R.drawable.o3pe,},
			},
		},
		{
			{
				{R.drawable.d1gf, R.drawable.d1gh, R.drawable.d1ge,},
				{R.drawable.d1rf, R.drawable.d1rh, R.drawable.d1re,},
				{R.drawable.d1pf, R.drawable.d1ph, R.drawable.d1pe,},
			},
			{
				{R.drawable.d2gf, R.drawable.d2gh, R.drawable.d2ge,},
				{R.drawable.d2rf, R.drawable.d2rh, R.drawable.d2re,},
				{R.drawable.d2pf, R.drawable.d2ph, R.drawable.d2pe,},
			},
			{
				{R.drawable.d3gf, R.drawable.d3gh, R.drawable.d3ge,},
				{R.drawable.d3rf, R.drawable.d3rh, R.drawable.d3re,},
				{R.drawable.d3pf, R.drawable.d3ph, R.drawable.d3pe,},
			},
		},
		{
			{
				{R.drawable.s1gf, R.drawable.s1gh, R.drawable.s1ge,},
				{R.drawable.s1rf, R.drawable.s1rh, R.drawable.s1re,},
				{R.drawable.s1pf, R.drawable.s1ph, R.drawable.s1pe,},
			},
			{
				{R.drawable.s2gf, R.drawable.s2gh, R.drawable.s2ge,},
				{R.drawable.s2rf, R.drawable.s2rh, R.drawable.s2re,},
				{R.drawable.s2pf, R.drawable.s2ph, R.drawable.s2pe,},
			},
			{
				{R.drawable.s3gf, R.drawable.s3gh, R.drawable.s3ge,},
				{R.drawable.s3rf, R.drawable.s3rh, R.drawable.s3re,},
				{R.drawable.s3pf, R.drawable.s3ph, R.drawable.s3pe,},
			},
		},
	};
	
	private static final int[][][][] sHDrawableId = {
		{
			{
				{R.drawable.ho1gf, R.drawable.ho1gh, R.drawable.ho1ge,},
				{R.drawable.ho1rf, R.drawable.ho1rh, R.drawable.ho1re,},
				{R.drawable.ho1pf, R.drawable.ho1ph, R.drawable.ho1pe,},
			},
			{
				{R.drawable.ho2gf, R.drawable.ho2gh, R.drawable.ho2ge,},
				{R.drawable.ho2rf, R.drawable.ho2rh, R.drawable.ho2re,},
				{R.drawable.ho2pf, R.drawable.ho2ph, R.drawable.ho2pe,},
			},
			{
				{R.drawable.ho3gf, R.drawable.ho3gh, R.drawable.ho3ge,},
				{R.drawable.ho3rf, R.drawable.ho3rh, R.drawable.ho3re,},
				{R.drawable.ho3pf, R.drawable.ho3ph, R.drawable.ho3pe,},
			},
		},
		{
			{
				{R.drawable.hd1gf, R.drawable.hd1gh, R.drawable.hd1ge,},
				{R.drawable.hd1rf, R.drawable.hd1rh, R.drawable.hd1re,},
				{R.drawable.hd1pf, R.drawable.hd1ph, R.drawable.hd1pe,},
			},
			{
				{R.drawable.hd2gf, R.drawable.hd2gh, R.drawable.hd2ge,},
				{R.drawable.hd2rf, R.drawable.hd2rh, R.drawable.hd2re,},
				{R.drawable.hd2pf, R.drawable.hd2ph, R.drawable.hd2pe,},
			},
			{
				{R.drawable.hd3gf, R.drawable.hd3gh, R.drawable.hd3ge,},
				{R.drawable.hd3rf, R.drawable.hd3rh, R.drawable.hd3re,},
				{R.drawable.hd3pf, R.drawable.hd3ph, R.drawable.hd3pe,},				
			},
		},
		{
			{
				{R.drawable.hs1gf, R.drawable.hs1gh, R.drawable.hs1ge,},
				{R.drawable.hs1rf, R.drawable.hs1rh, R.drawable.hs1re,},
				{R.drawable.hs1pf, R.drawable.hs1ph, R.drawable.hs1pe,},
			},
			{
				{R.drawable.hs2gf, R.drawable.hs2gh, R.drawable.hs2ge,},
				{R.drawable.hs2rf, R.drawable.hs2rh, R.drawable.hs2re,},
				{R.drawable.hs2pf, R.drawable.hs2ph, R.drawable.hs2pe,},				
			},
			{
				{R.drawable.hs3gf, R.drawable.hs3gh, R.drawable.hs3ge,},
				{R.drawable.hs3rf, R.drawable.hs3rh, R.drawable.hs3re,},
				{R.drawable.hs3pf, R.drawable.hs3ph, R.drawable.hs3pe,},
			},
		},
	};

	private static final String TAG = Card.class.getCanonicalName();

	public static enum Shape {
		OVAL, DIAMOND, SQUIGGLE;
	}

	public static enum Count {
		ONE, TWO, THREE;
	}
	
	public static enum Color {
		GREEN, RED, PURPLE;
	}
	
	public static enum Fill {
		FULL, HALF, EMPTY;
	}
	
	private final Shape mShape;
	private final Count mCount;
	private final Color mColor;
	private final Fill mFill;
	
	public Card(Shape shape, Count count, Color color, Fill fill) {
		mShape = shape;
		mCount = count;
		mColor = color;
		mFill = fill;
	}

	public Shape getShape() {
		return mShape;
	}

	public Count getCount() {
		return mCount;
	}

	public Color getColor() {
		return mColor;
	}

	public Fill getFill() {
		return mFill;
	}
	
	public boolean isBlank() {
		if (mShape == null) {
			return true;
		} else if (mCount == null) {
			return true;
		} else if (mColor == null) {
			return true;
		} else if (mFill == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getDrawableId() {
		if (isBlank()) {
			return R.drawable.blank;
		}
		return sDrawableId[mShape.ordinal()][mCount.ordinal()][mColor.ordinal()][mFill.ordinal()];
	}
	
	public int getHighlightedDrawableId() {
		if (isBlank()) {
			return R.drawable.hblank;
		}
		return sHDrawableId[mShape.ordinal()][mCount.ordinal()][mColor.ordinal()][mFill.ordinal()];
	}

    @Override
    public int hashCode() {
        int result = mShape.hashCode();
        result = 31 * result + mCount.hashCode();
        result = 31 * result + mColor.hashCode();
        result = 31 * result + mFill.hashCode();
        return result;
    }

    @Override
	public boolean equals(Object o) {
		Log.i(TAG, "Calling Card.equals");
		if (o == null) {
			return false;
		}
		Log.i(TAG, "Passed the null test");
		if (!(o instanceof Card)) {
			Log.i(TAG, "Not a Card");
			return false;
		}
		Log.i(TAG, "Passed the instaceof test");
		Card other = (Card) o;
		if (isBlank()) {
			return other.isBlank();
		}
		Log.i(TAG, "Comparing " + this + " with " + other);
		return (mShape == other.mShape) &&
				(mCount == other.mCount) &&
				(mColor == other.mColor) &&
				(mFill == other.mFill);
	}
	
	public Card getCompleter(Card c) {
		if (isBlank() || c.isBlank()) {
			return new Card(null,null,null,null);
		}
		Shape shape = null;
		Count count = null;
		Color color = null;
		Fill fill = null;
		if (mShape == c.mShape) {
			shape = mShape;
		} else {
			for (Shape s : Shape.values()) {
				if ((s != mShape) && (s != c.mShape)) {
					shape = s;
					break;
				}
			}
		}
		if (mCount == c.mCount){
			count = mCount;
		} else {
			for (Count cc : Count.values()) {
				if ((cc != mCount) && (cc != c.mCount)) {
					count = cc;
					break;
				}
			}
		}
		if (mColor == c.mColor) {
			color = mColor;
		} else {
			for (Color cc : Color.values()) {
				if ((cc != mColor) && (cc != c.mColor)) {
					color = cc;
					break;
				}
			}
		}
		if (mFill == c.mFill) {
			fill = mFill;
		} else {
			for (Fill f : Fill.values()) {
				if ((f != mFill) && (f != c.mFill)) {
					fill = f;
					break;
				}
			}
		}
		return new Card(shape, count, color, fill);
	}
	
	@Override
	public String toString() {
		if (isBlank()) {
			return "Blank";
		}
		StringBuilder sb = new StringBuilder();
		switch(mShape) {
		case OVAL:
			sb.append("o");
			break;
		case DIAMOND:
			sb.append("d");
			break;
		case SQUIGGLE:
			sb.append("s");
			break;
		}
		switch(mCount) {
		case ONE:
			sb.append("1");
			break;
		case TWO:
			sb.append("2");
			break;
		case THREE:
			sb.append("3");
			break;
		}
		switch(mColor) {
		case GREEN:
			sb.append("g");
			break;
		case RED:
			sb.append("r");
			break;
		case PURPLE:
			sb.append("p");
			break;
		}
		switch(mFill) {
		case FULL:
			sb.append("f");
			break;
		case HALF:
			sb.append("h");
			break;
		case EMPTY:
			sb.append("e");
			break;
		}
		return sb.toString();
	}
}
